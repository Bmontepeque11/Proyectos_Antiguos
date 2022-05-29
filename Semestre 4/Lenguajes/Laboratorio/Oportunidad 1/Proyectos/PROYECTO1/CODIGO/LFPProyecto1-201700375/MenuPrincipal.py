import sys
import csv
from graphviz import Digraph

ListaTokens = []  # Esto es para la tabla, cada nodo de la lista es un Token
ListaErrores = []  # Esto es para la tabla, cada nodo de la lista es un Error
ListaRutas = []  # Esto es para la Grafica del mapa o de las rutas, cada nodo es una Ruta
ListaEstaciones = []  # Esto es para la Grafica del mapa o de las rutas, cada nodo es una Estacion
RutaArchivoReal = ''
NombreMapa = ''


##########################################
#Estacion
##########################################

class Estacion:
    def __init__(self, Nombre, Estado, Color):
        self.Nombre = Nombre
        self.Estado = Estado
        self.Color = Color


##########################################
#Ruta
##########################################

class Ruta:
    def __init__(self, Nombre, Peso, Inicio, Fin):
        self.Nombre = Nombre
        self.Peso = Peso
        self.Inicio = Inicio
        self.Fin = Fin


##########################################
# Errores
##########################################

class Error:
    def __init__(self, Fila, Columna, Caracter, Descripcion):
        self.Fila = Fila
        self.Columna = Columna
        self.Caracter = Caracter
        self.Descripcion = Descripcion

##########################################
# TOKENS
##########################################

class Token:
    def __init__(self, Lexema, Fila, Columna, Token):
        self.Lexema = Lexema
        self.Fila = Fila
        self.Columna = Columna
        self.Token = Token

###########################################
# ANALIZADOR LEXICO
###########################################
def AnalizadorLexico(TextoProcesado):
    #DefinirEstados
    #E0 = ParteInicialEtiqueta
    #E1 = EntreEtiquetas
    #E2 = ParteFinalEtiqueta

    Estado = 0
    CaracterActual = ""
    SiguienteCaracter = ""
    Lexema = ""
    ValorEtiqueta = ''
    TokenActual = ""
    Fila = 1
    Columna = 0
    ColumnaInicioToken = ''


    #########################################################################################
    #Variables Guardadas a Mostrar
    ##########################################################################################
    global ListaTokens  # Esto es para la tabla, cada nodo de la lista es un Token
    global ListaErrores  # Esto es para la tabla, cada nodo de la lista es un Error
    global ListaRutas  # Esto es para la Grafica del mapa o de las rutas, cada nodo es una Ruta
    global ListaEstaciones  # Esto es para la Grafica del mapa o de las rutas, cada nodo es una Estacion

    ############################################################
    # Variables que guarden el valor de las etiquetas de Ruta
    ############################################################

    NombreRutaActual = ''
    PesoRutaActual = ''
    InicioRutaActual = ''
    FinRutaActual = ''

    ##############################################################
    # Variables que guarden el valor de las etiquetas de Estacion
    ##############################################################
    NombreEstacionActual = ''
    EstadoEstacionActual = ''
    ColorEstacionActual = ''

    #################################################################
    # Variables que guarden el valor de las etiquetas de Nombre Mapa
    #################################################################
    global NombreMapa

    for i in range(len(TextoProcesado)):
        CaracterActual = TextoProcesado[i]
        Columna = Columna + 1
        try:
            SiguienteCaracter = TextoProcesado[i+1] #Este es un pequeño "Scout", puede ver valores que den error
        except:
            SiguienteCaracter = ""
        if (Estado == 0): # <, /
            if(CaracterActual == "<"):
                if(SiguienteCaracter.isalpha()):
                    Estado = 1
                    TokenActual = TokenActual + CaracterActual
                    ColumnaInicioToken = Columna
                    print("Este es el TokenActual ", end = '')
                    print(TokenActual)
                elif (SiguienteCaracter == "/"):
                    Estado = 1
                    TokenActual = TokenActual + CaracterActual
            elif (CaracterActual.isspace()):
                Estado = 0
                if (CaracterActual == "\n"):
                    Fila = Fila + 1
                    Columna = 0
        elif (Estado == 1): #Entrelos < >
            if (CaracterActual.isalpha() or CaracterActual == "/"):
                if (SiguienteCaracter != ">"):
                    Lexema = Lexema + CaracterActual
                    print("Este es el Lexema ", end='')
                    print(Lexema)
                elif (SiguienteCaracter == ">"):
                    Estado = 2
                    Lexema = Lexema + CaracterActual
                    print("Este es el Lexema ", end='')
                    print(Lexema)
                    TokenActual = TokenActual + Lexema.lower()
                    print("Este es el Token Actual ", end='')
                    print(TokenActual)

        elif (Estado == 2):
            if (CaracterActual == ">" and (SiguienteCaracter == "\n" or SiguienteCaracter == "<" or SiguienteCaracter.isalpha() or SiguienteCaracter.isdigit())):
                Estado = 0
                TokenActual = TokenActual + CaracterActual
                print("Este es el Token Actual ", end='')
                print(TokenActual)
                ListaTokens.append(Token(Lexema, Fila, ColumnaInicioToken, TokenActual))
                #Vamos a definir nuevos estados para cada una de las 3 posibles "Etiquetas Padre"
                #Ruta, Estacion y NombreMapa son los nombres de los estados.
                if (Lexema.lower() == "ruta"):
                    Estado = "Ruta"
                    Lexema = ''
                    TokenActual = ''
                elif (Lexema.lower() == "estacion"):
                    Estado = "Estacion"
                    Lexema = ''
                    TokenActual = ''
                elif (Lexema.lower() == "nombre"):
                    Estado = "Recolectar Valores Nombre Mapa"
                    Lexema = ''
                    TokenActual = ''
                elif (Lexema.lower() == "/ruta"):
                    Estado = 0
                    Lexema = ''
                    TokenActual = ''
                elif (Lexema.lower() == "/estacion"):
                    Estado = 0
                    Lexema = ''
                    TokenActual = ''
                elif (Lexema.lower() == "/nombre"):
                    Estado = 0
                    Lexema = ''
                    TokenActual = ''
        elif (Estado == "Ruta"): #Etiquetas Entre <ruta> y </ruta>
            if (CaracterActual == "<"):#busca los "<" y "</"
                if SiguienteCaracter.isalpha():
                    Estado = "Ruta 1"
                    TokenActual = TokenActual + CaracterActual;
                    ColumnaInicioToken = Columna
                    print("Este es el token de ruta actual: ", end ='')
                    print(TokenActual)
                elif (SiguienteCaracter == "/"):
                    Estado = "Ruta 1"
                    TokenActual = TokenActual + CaracterActual
            elif (CaracterActual.isspace()):
                Estado = "Ruta"
                if (CaracterActual == "\n"):
                    Fila = Fila + 1
                    Columna = 0
        elif (Estado == "Ruta 1"):#nombre de Etiquetas adentro de Ruta
            if (CaracterActual.isalpha() or CaracterActual == "/"):
                if (SiguienteCaracter != ">"):
                    Lexema = Lexema + CaracterActual
                    print("Este es el lexema de ruta: ", end='')
                    print(Lexema)
                elif (SiguienteCaracter == ">"):
                    Estado = "Ruta 2"
                    Lexema = Lexema + CaracterActual
                    print("Este es el lexema de ruta: ", end='')
                    print(Lexema)
                    TokenActual = TokenActual + Lexema.lower()
                    print("Este es el Token de Ruta Actual", end = '')
                    print(TokenActual)
        elif (Estado == "Ruta 2"): #busca los ">" adentro de ruta
             if (CaracterActual == ">" and (SiguienteCaracter == "\n" or SiguienteCaracter == "<" or SiguienteCaracter.isalpha() or SiguienteCaracter.isdigit())):
                TokenActual = TokenActual+ CaracterActual
                print("Este es el Token de Ruta Actual: ", end='')
                print(TokenActual)
                ListaTokens.append(Token(Lexema, Fila, ColumnaInicioToken, TokenActual))

                # Definir Estados Para Las Posibles Etiquetas de Adentro de Estacion
                # Establecemos estados para cada estado de las sub etiquetas que puede tener estacion
                # Estado Estacion Nombre, Estado Estacion Estado y Estado Estacion Color son los nombres de los estados
                if (Lexema.lower() == "nombre"):
                    Estado = "Recolectar Valores Ruta"

                elif (Lexema.lower() == "peso"):
                    Estado = "Recolectar Valores Ruta"

                elif (Lexema.lower() == "inicio"):
                    Estado = "Recolectar Valores Ruta"

                elif (Lexema.lower() == "fin"):
                    Estado = "Recolectar Valores Ruta"

                elif (Lexema.lower() == "/nombre"):
                    Estado = "Ruta"
                    Lexema = ''
                    TokenActual = ''
                elif (Lexema.lower() == "/peso"):
                    Estado = "Ruta"
                    Lexema = ''
                    TokenActual = ''
                elif (Lexema.lower() == "/inicio"):
                    Estado = "Ruta"
                    Lexema = ''
                    TokenActual = ''
                elif (Lexema.lower() == "/fin"):
                    Estado = "Ruta"
                    Lexema = ''
                    TokenActual = ''
                elif (Lexema.lower() == "/ruta"):
                    Estado = 0
                    Lexema = ''
                    TokenActual = ''
                    ListaRutas.append(Ruta(NombreRutaActual, PesoRutaActual, InicioRutaActual, FinRutaActual))
                    NombreRutaActual = ''
                    PesoRutaActual = ''
                    InicioRutaActual = ''
                    FinRutaActual = ''
        elif (Estado == "Recolectar Valores Ruta"):

            if (SiguienteCaracter != "<"):
                ValorEtiqueta = ValorEtiqueta + CaracterActual
                print("Este es el Value de la etiqueta: ", end='')
                print(ValorEtiqueta)
            elif (SiguienteCaracter == "<"):
                Estado = "Ruta"
                ValorEtiqueta = ValorEtiqueta + CaracterActual
                print("Este es el Value de la etiqueta: ", end='')
                print(ValorEtiqueta)
                if Lexema.lower() == "nombre":
                    NombreRutaActual = NombreRutaActual + ValorEtiqueta
                    print("Este es el Nombre de la etiqueta actual: ", end='')
                    print(NombreRutaActual)
                    ValorEtiqueta = ''
                    Lexema = ''
                    TokenActual = ''
                elif Lexema.lower() == "peso":
                    PesoRutaActual = PesoRutaActual + ValorEtiqueta
                    print("Este es el Peso de la etiqueta actual: ", end='')
                    print(PesoRutaActual)
                    ValorEtiqueta = ''
                    Lexema = ''
                    TokenActual = ''
                elif Lexema.lower() == "inicio":
                    InicioRutaActual = InicioRutaActual + ValorEtiqueta
                    print("Este es el Inicio de la etiqueta actual: ", end='')
                    print(InicioRutaActual)
                    ValorEtiqueta = ''
                    Lexema = ''
                    TokenActual = ''
                elif Lexema.lower() == "fin":
                    FinRutaActual = FinRutaActual + ValorEtiqueta
                    print("Este es el Fin de la etiqueta actual: ", end='')
                    print(FinRutaActual)
                    ValorEtiqueta = ''
                    Lexema = ''
                    TokenActual = ''
        elif (Estado == "Estacion"): #Etiquetas entre <estacion> y </estacion>
            if (CaracterActual == "<"):#busca los "<" y "</"
                if SiguienteCaracter.isalpha():
                    Estado = "Estacion 1"
                    TokenActual = TokenActual + CaracterActual;
                    ColumnaInicioToken = Columna
                    print("Este es el token de estacion actual: ", end ='')
                    print(TokenActual)
                elif (SiguienteCaracter == "/"):
                    Estado = "Estacion 1"
                    TokenActual = TokenActual + CaracterActual
            elif (CaracterActual.isspace()):
                Estado = "Estacion"
                if (CaracterActual == "\n"):
                    Fila = Fila + 1
                    Columna = 0
        elif (Estado == "Estacion 1"): #Etiquetas dentro de Estacion
            if (CaracterActual.isalpha() or CaracterActual == "/"):
                if (SiguienteCaracter != ">"):
                    Lexema = Lexema + CaracterActual
                    print("Este es el lexema de estacion: ", end='')
                    print(Lexema)
                elif (SiguienteCaracter == ">"):
                    Estado = "Estacion 2"
                    Lexema = Lexema + CaracterActual
                    print("Este es el lexema de Estacion: ", end='')
                    print(Lexema)
                    TokenActual = TokenActual + Lexema.lower()
                    print("Este es el Token de Estacion Actual", end = '')
                    print(TokenActual)
        elif (Estado == "Estacion 2"): #Busca los ">" dentro de la etiqueta
            if (CaracterActual == ">" and (
                    SiguienteCaracter == "\n" or SiguienteCaracter == "<" or SiguienteCaracter.isalpha() or SiguienteCaracter.isdigit() or SiguienteCaracter == "#")):

                TokenActual = TokenActual + CaracterActual
                print("Este es el Token de Estacion Actual: ", end='')
                print(TokenActual)
                ListaTokens.append(Token(Lexema, Fila, ColumnaInicioToken, TokenActual))

                # Definir Estados Para Las Posibles Etiquetas de Adentro de Estacion
                # Establecemos estados para cada estado de las sub etiquetas que puede tener estacion
                # Estado Estacion Nombre, Estado Estacion Estado y Estado Estacion Color son los nombres de los estados
                if (Lexema.lower() == "nombre"):
                    Estado = "Recolectar Valores Estacion"

                elif (Lexema.lower() == "estado"):
                    Estado = "Recolectar Valores Estacion"

                elif (Lexema.lower() == "color"):
                    Estado = "Recolectar Valores Estacion"

                elif (Lexema.lower() == "/nombre"):
                    Estado = "Estacion"
                    Lexema = ''
                    TokenActual = ''
                elif (Lexema.lower() == "/estado"):
                    Estado = "Estacion"
                    Lexema = ''
                    TokenActual = ''
                elif (Lexema.lower() == "/color"):
                    Estado = "Estacion"
                    Lexema = ''
                    TokenActual = ''
                elif (Lexema.lower() == "/estacion"):
                    Estado = 0
                    Lexema = ''
                    TokenActual = ''
                    ListaEstaciones.append(Estacion(NombreEstacionActual, EstadoEstacionActual, ColorEstacionActual))
                    NombreEstacionActual = ''
                    EstadoEstacionActual = ''
                    ColorEstacionActual = ''
        elif (Estado == "Recolectar Valores Estacion"):

            if (SiguienteCaracter != "<"):
                ValorEtiqueta = ValorEtiqueta + CaracterActual
                print("Este es el Value de la etiqueta: ", end='')
                print(ValorEtiqueta)
            elif (SiguienteCaracter == "<"):
                Estado = "Estacion"
                ValorEtiqueta = ValorEtiqueta + CaracterActual
                print("Este es el Value de la etiqueta: ", end='')
                print(ValorEtiqueta)
                if Lexema.lower() == "nombre":
                    NombreEstacionActual = NombreEstacionActual + ValorEtiqueta
                    print("Este es el Nombre de la etiqueta actual: ", end='')
                    print(NombreEstacionActual)
                    ValorEtiqueta = ''
                    Lexema = ''
                    TokenActual = ''
                elif Lexema.lower() == "estado":
                    EstadoEstacionActual = EstadoEstacionActual + ValorEtiqueta
                    print("Este es el Estado de la etiqueta actual: ", end='')
                    print(EstadoEstacionActual)
                    ValorEtiqueta = ''
                    Lexema = ''
                    TokenActual = ''
                elif Lexema.lower() == "color":
                    ColorEstacionActual = ColorEstacionActual + ValorEtiqueta
                    print("Este es el Color de la etiqueta actual: ", end='')
                    print(ColorEstacionActual)
                    ValorEtiqueta = ''
                    Lexema = ''
                    TokenActual = ''
        elif (Estado == "NombreMapa"): #Buscar la etiqueta de cierre
            if (CaracterActual == "<"):#busca los "<" y "</"
                if SiguienteCaracter.isalpha():
                    Estado = "NombreMapa 1"
                    TokenActual = TokenActual + CaracterActual;
                    ColumnaInicioToken = Columna
                    print("Este es el token de nombreMapa actual: ", end ='')
                    print(TokenActual)
                elif (SiguienteCaracter == "/"):
                    Estado = "NombreMapa 1"
                    TokenActual = TokenActual + CaracterActual
            elif (CaracterActual.isspace()):
                Estado = "NombreMapa"
                if (CaracterActual == "\n"):
                    Fila = Fila + 1
                    Columna = 0
        elif (Estado == "NombreMapa 1"): #datos entre < y >
            if (CaracterActual.isalpha() or CaracterActual == "/"):
                if (SiguienteCaracter != ">"):
                    Lexema = Lexema + CaracterActual
                    print("Este es el lexema de NombreMapa: ", end='')
                    print(Lexema)
                elif (SiguienteCaracter == ">"):
                    Estado = "NombreMapa 2"
                    Lexema = Lexema + CaracterActual
                    print("Este es el lexema de Nombre Mapa: ", end='')
                    print(Lexema)
                    TokenActual = TokenActual + Lexema.lower()
                    print("Este es el Token de Nombre Mapa Actual", end = '')
                    print(TokenActual)
        elif (Estado == "NombreMapa 2"): #Busca los ">" dentro de la etiqueta
            if (CaracterActual == ">" and (
                    SiguienteCaracter == "\n" or SiguienteCaracter == "<" or SiguienteCaracter.isalpha() or SiguienteCaracter.isdigit())):
                TokenActual = TokenActual + CaracterActual
                print("Este es el Token de NombreMapa Actual: ", end='')
                print(TokenActual)
                ListaTokens.append(Token(Lexema, Fila, ColumnaInicioToken, TokenActual))

                # Definir Estados Para Las Posibles Etiquetas de Adentro de NombreMapa
                # Estado Estacion Nombre, Estado Estacion Estado y Estado Estacion Color son los nombres de los estados
                if (Lexema.lower() == "nombre"):
                    Estado = "Recolectar Valores Nombre Mapa"

                elif (Lexema.lower() == "/nombre"):
                    Estado = 0
                    Lexema = ''
                    TokenActual = ''
                    ValorEtiqueta = ''
        elif (Estado == "Recolectar Valores Nombre Mapa"):
            if (SiguienteCaracter != "<"):
                ValorEtiqueta = ValorEtiqueta + CaracterActual
                print("Este es el Value de la etiqueta: ", end='')
                print(ValorEtiqueta)
            elif (SiguienteCaracter == "<"):
                Estado = "NombreMapa"
                ValorEtiqueta = ValorEtiqueta + CaracterActual
                print("Este es el Value de la etiqueta: ", end='')
                print(ValorEtiqueta)
                if Lexema.lower() == "nombre":
                    NombreMapa = NombreMapa + ValorEtiqueta
                    print("Este es el NombreMapa de la etiqueta actual: ", end='')
                    print(NombreMapa)
                    ValorEtiqueta = ''
                    Lexema = ''
                    TokenActual = ''
    with open('ReporteTokens.csv', 'w', newline='') as f:
        Escritor = csv.writer(f)
        Escritor.writerow(['No.', 'Lexema', 'Fila', 'Columna', 'Token'])
        for i in range(len(ListaTokens)):
            Escritor.writerow([i+1, ListaTokens[i].Lexema, ListaTokens[i].Fila, ListaTokens[i].Columna, ListaTokens[i].Token])


def CargarArchivo():
    print("\n")
    RutaArchivo = input(
        r"Ingrese la ruta del archivo .txt que deseea cargar al programa y este le mostará los resultados: ")
    global RutaArchivoReal
    RutaArchivoReal = RutaArchivo
    if RutaArchivo.lower().endswith(".txt"):
        Archivo = open(RutaArchivo, "r")
        AnalizadorLexico(Archivo.read())
    else:
        input(
            "Solo puede ingresar un archivo con extensión '.txt', ingrese cualquier valor para volver al Menú Principal: ")

def GraficarMapa():
    global ListaEstaciones
    global NombreMapa
    global RutaArchivoReal
    if (RutaArchivoReal == ''):
        input("Error, no se ha cargado ningun archivo, vaya a la opcion 1 y cargue un archivo primero, ingrese valor para voler al Menu: ")
    elif (RutaArchivoReal != ''):
        MapaCiudad = Digraph(format='png', name='MapaCiudad')
        MapaCiudad.attr(rankdir='LR')
        MapaCiudad.attr('Titulo', shape='plaintext')
        NombreMapaGraph = NombreMapa
        MapaCiudad.Titulo('NombreMapaGraph',label=NombreMapaGraph)
        MapaCiudad.attr('Estacion', shape='circle')
        for i in range(len(ListaEstaciones)):
            idEstacion = i+1;
            NombreEstacionGraph = ListaEstaciones[i].Nombre
            EstadoEstacionGraph = ListaEstaciones[i].Estado
            ColorEstacionGraph = ListaEstaciones[i].Color
            LabelCadaEstacion = "Nombre: "+NombreEstacionGraph+"\n Estado: "+ EstadoEstacionGraph
            MapaCiudad.Estacion(idEstacion, label=LabelCadaEstacion, color=ColorEstacionGraph)
        MapaCiudad.view()


def GUI():
  #Caja Datos Alumno
    # Linea Superior
    for x in range(0, 42):
      print("-", end="")

    #Un Salto de Línea
    print()

    #Datos
    print("| Lenguajes Formales y de Programación   |")
    print("| Sección: A-                            |")
    print("| Nombre: Bryan Steve Montepeque Santos  |")
    print("| Carnet: 201700375                      |")

    # Linea Inferior
    for x in range(0, 42):
     print("-", end="")


  #Caja Opciones Menú

    #Un Salto de Linea
    print()

    #Partes Intermedias

    print("| 1. Cargar Archivo                      |")
    print("| 2. Graficar Ruta                       |")
    print("| 3. Graficar Mapa                       |")
    print("| 4. Salir                               |")

    #Linea Inferior
    for x in range (0,42):
        print("-", end = "")

    # Un Salto de Linea
    print()




    #Texto e Input con Opciones y Try Except
    try:
     OpcionMenu = int (input("Bienvenido, Ingrese el Número de la Operación que desee realizar: "))
     if OpcionMenu == 1:
         CargarArchivo()
         GUI()
     elif OpcionMenu == 2:
         print("Graficar Ruta")
         GUI()
     elif OpcionMenu == 3:
         GraficarMapa()
         print("como que no? >:v")
         GUI()
     elif OpcionMenu == 4:
         sys.exit()
    except ValueError:
        print("Solo Puede Ingresar el Número de la función, por ejemplo, ingrese '1' si desea cargar un archivo, Intentelo de nuevo ")
        print()
        GUI()
