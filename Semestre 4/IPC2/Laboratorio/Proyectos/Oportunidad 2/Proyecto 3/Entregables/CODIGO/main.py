from flask import Flask, request, Response
import re
import requests

class Evento:
    def __init__(self, Fecha, Reportador, CodigoError, Error, Afectados = []):
        self.Fecha = Fecha
        self.Reportador = Reportador
        self.Afectados = Afectados
        self.CodigoError = CodigoError
        self.Error = Error
#Variables Globales
ArchivoLeido = ''
app = Flask(__name__)

@app.route('/', methods=['POST'])
def Eventos():

    data = open("prueba.xml", 'r')
    global ArchivoLeido
    ArchivoLeido = data.read()
    data.close()
    return Response(response=ArchivoLeido, mimetype='text/plain', content_type='text/plain')

@app.route('/', methods=['GET'])

def get_events():
    #ObjetosGenerales
    ListaEventos = []

    #Procesar el XML
    data = open('prueba.xml', 'r+', encoding='utf-8')
    ArchivoLeido = data.read()

    with open('prueba.xml', "r") as Archivo:
        #Obtener cada evento parte 1, quitar las etiquetas de apertura:
        XMLPartidoEnEventos = ArchivoLeido.split("<EVENTO>")

        for EventoSinApertura in range(1, len(XMLPartidoEnEventos)):
            #Terminar de Aislar cada evento (Es decir, quitando las etiquetas de fin también)
            EventosAislados = XMLPartidoEnEventos[EventoSinApertura].split("</EVENTO>")

            #Los Eventos Aislados aún tienen los "\t" y los "\n", los Eventos Puros ya no lo tienen
            EventosPuros = EventosAislados[0]

            #Procedemos a partir los eventos puros Linea por Linea
            EventosPurosPartidosPorLineas = EventosPuros.split("\n")

            FechaActual = ''
            ReportadorActual = ''
            AfectadosActuales = []
            CodigoErrorActual = ''
            ErrorActual = ''

            #En cada Linea hay un "\n", ahora ignoramos ese "\n"
            for LineaInfoReal in range(1, len(EventosPurosPartidosPorLineas) - 1):


                #Quitamos el "/t"
                LineasDeInformacionValida = EventosPurosPartidosPorLineas[LineaInfoReal].split("\t")

                if LineaInfoReal == 1:
                    #Partir Linea Pais, Fecha
                    LineaActual = LineasDeInformacionValida[1].split(",")
                    PatronFecha = re.search(r'(\d+/\d+/\d+)', LineaActual[1])
                    FechaActual = PatronFecha.group()

                elif LineaInfoReal == 2:
                    #Partir Linea Reportado por: Usuario
                    LineaActual = LineasDeInformacionValida[1].split(":")
                    PatronReportador = re.search(r'(\d+|\w+)[@](\d+|\w+)(\.|\d+|\w+)+', LineaActual[1])
                    ReportadorActual = PatronReportador.group()

                elif LineaInfoReal == 3:
                    #Partir Linea Afectados: Usuario o Usuarios
                    LineaActual = LineasDeInformacionValida[1].split(":")
                    ListaUsuariosAfectados = LineaActual[1].split(",")
                    for Usuario in range(len(ListaUsuariosAfectados)):
                        PatronAfectados = re.search(r'(\d+|\w+)[@](\d+|\w+)(\.|\d+|\w+)+', ListaUsuariosAfectados[Usuario])
                        AfectadoActual = PatronAfectados.group()
                        #Agregar los usuarios Afectados a la lista
                        AfectadosActuales.append(AfectadoActual)


                elif LineaInfoReal == 4:
                    #Partir Linea Error: Desrcipcion Error
                    LineaActual = LineasDeInformacionValida[1].split(":")
                    ErrorActual = LineaActual[1] #Primera Parte String

                    if EventosPurosPartidosPorLineas[5] != "\t":
                        #Quitarle el "\t" a la segunta parte del string
                        CasiSegundaParteError = EventosPurosPartidosPorLineas[5].split("\t")

                        SegeundaParteError = CasiSegundaParteError[1]
                        ErrorActual = ErrorActual + " " + SegeundaParteError

                    #Conseguir el Codigo del Error
                    ErrorActualPartidoEnElGuion = ErrorActual.split("-")
                    CasiCodigoDeError = ErrorActualPartidoEnElGuion[0]

                    PatronCodigoError = re.search(r'(\d+)', CasiCodigoDeError)
                    CodigoErrorActual = PatronCodigoError.group()



            #Guardar Cosas en la lista
            print("")
            print("Esta es la Fecha Actual: " + FechaActual)
            print("Este es el Reportador Actual: " + ReportadorActual)
            print("Esta es la lista de Afectados Actuales: ")
            print(AfectadosActuales)
            print("Este es el Codigo del Error Actual: " + CodigoErrorActual)
            print("Este es el error actual: " + ErrorActual)

            ListaEventos.append(Evento(FechaActual, ReportadorActual, CodigoErrorActual, ErrorActual, AfectadosActuales))

    #Escribir el Nuevo XML
    StringArchivoXML = '<ESTADISTICAS>\n\t<ESTADISTICA>\n\t'

    # #Recorrer La Lista para juntar las Fechas
    # ListaFechasDiferentes = []
    # ListaOcurrenciasCadaFecha = []
    # #Esta cuenta sirve para cada fecha individual
    # for i in range(len(ListaEventos)):
    #     OcurrenciasCadaFecha = 1
    #     #Esta cuenta sirve para recorrer la lista para ver si la fecha individual es igual a cualquiera de la lista
    #     if i+1 < len(ListaEventos):
    #         if ListaEventos[i].Fecha == ListaEventos[i+1].Fecha:
    #             OcurrenciasCadaFecha = OcurrenciasCadaFecha + 1
    #         elif ListaEventos[i].Fecha != ListaEventos[i+1].Fecha:
    #             ListaFechasDiferentes.append(ListaEventos[i].Fecha)
    #     ListaOcurrenciasCadaFecha.append(OcurrenciasCadaFecha)
    # print("Esta es la lista de Fechas Diferentes: ")
    # print(ListaFechasDiferentes)
    # print("Esta es la lista de Ocurrencias de Cada Fecha: ")
    # print(ListaOcurrenciasCadaFecha)

    for i in range(len(ListaEventos)):
        StringArchivoXML = StringArchivoXML + "\t<FECHA>" + str(ListaEventos[i].Fecha) + "</FECHA>\n"

        #Reportado Por
        StringArchivoXML = StringArchivoXML + "\t\t<REPORTADO_POR>\n"
        StringArchivoXML = StringArchivoXML + "\t\t\t<USUARIO>\n"
        StringArchivoXML = StringArchivoXML + "\t\t\t<EMAIL>" + ListaEventos[i].Reportador + "</EMAIL>\n"
        StringArchivoXML = StringArchivoXML + "\t\t\t</USUARIO>\n"
        StringArchivoXML = StringArchivoXML + "\t\t</REPORTADO_POR>\n"

        #Afectados
        StringArchivoXML = StringArchivoXML + "\t\t<AFECTADOS>\n"
        for j in range(len(ListaEventos[i].Afectados)):
            StringArchivoXML = StringArchivoXML + "\t\t\t<AFECTADO>"+ ListaEventos[i].Afectados[j] + "</AFECTADO>\n"
        StringArchivoXML = StringArchivoXML + "\t\t</AFECTADOS>\n"

        #Errores
        StringArchivoXML = StringArchivoXML + "\t\t<ERRORES>\n"
        StringArchivoXML = StringArchivoXML + "\t\t\t<ERROR>\n"
        StringArchivoXML = StringArchivoXML + "\t\t\t\t<CODIGO>" + ListaEventos[i].CodigoError + "</CODIGO>\n"
        StringArchivoXML = StringArchivoXML + "\t\t\t\t<DESCRIPCION>" + ListaEventos[i].Error + "</DESCRIPCION>\n"
        StringArchivoXML = StringArchivoXML + "\t\t\t</ERROR>\n"
        StringArchivoXML = StringArchivoXML + "\t\t</ERRORES>\n"

    #Cerrar Archivo
    StringArchivoXML = StringArchivoXML + "\t</ESTADISTICA>\n"
    StringArchivoXML = StringArchivoXML + "</ESTADISTICAS>"

    #Generar el Archivo
    with open("estadistica.xml", 'w') as EstadisticaXML:
        EstadisticaXML.write(StringArchivoXML)
    EstadisticaXML.close()

    #LeerElArchivoParaLaResponse
    LeerNuevoXML = open("estadistica.xml", "r")
    RespuestaNuevoXML = LeerNuevoXML.read()
    return Response(response=RespuestaNuevoXML, mimetype='text/plain', content_type='text/plain')

if __name__ == '__main__':
    app.run(debug=True, port=5000)