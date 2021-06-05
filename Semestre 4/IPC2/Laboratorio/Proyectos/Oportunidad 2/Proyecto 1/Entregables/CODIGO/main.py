import sys
import os
from xml.etree import ElementTree


#################################################
# COSAS IMPORTANTES                             #
#################################################

#################################################
# LISTA SIMPLE FILAS
class linked_list_fila:
    def __init__(self):
        self.head = None

    def insertar(self, id, Fila):
        if not self.head:
            self.head = nodeLSF(id=id, Fila=Fila)
            return
        current = self.head
        while current.next:
            current = current.next
        current.next = nodeLSF(id=id, Fila=Fila)

    def mostrar(self):
        nodeLSF = self.head
        while nodeLSF != None:
            print(nodeLSF.Fila, end="=>")
            nodeLSF = nodeLSF.next

    def eliminar(self, id):
        current = self.head
        previous = None

        while current and current.id != id:
            previous = current
            current = current.next

            if previous is None:
                self.head = current.next
            elif current:
                previous.next = current.next
                current.next = None


# Nodo Lista Simple Fila
class nodeLSF:
    def __init__(self, id=None, Fila=None, next=None):
        self.id = id
        self.Fila = Fila
        self.next = next


#################################################
# LISTA SIMPLE DATOS
class linked_list_datos:
    def __init__(self):
        self.head = None

    def insertar(self, id, Dato):
        if not self.head:
            self.head = nodeLSD(id=id, Dato=Dato)
            return
        current = self.head
        while current.next:
            current = current.next
        current.next = nodeLSD(id=id, Dato=Dato)

    def mostrar(self):
        nodeLSD = self.head
        while nodeLSD != None:
            print(nodeLSD.Dato, end="=>")
            nodeLSD = nodeLSD.next

    def eliminar(self, Dato):
        current = self.head
        previous = None

        while current and current.Dato != Dato:
            previous = current
            current = current.next

            if previous is None:
                self.head = current.next
            elif current:
                previous.next = current.next
                current.next = None


# Nodo Lista Simple
class nodeLSD:
    def __init__(self, id=None, Dato=None, next=None):
        self.id = id
        self.Dato = Dato
        self.next = next


#################################################
# LISTA CIRCULAR

class linked_list_circular:
    def __init__(self, head=None):
        self.head = head
        self.size = 0

    def insertar(self,idMatriz, NombreMatriz, MatrizN, MatrizM, MatrizReal):
        if self.size == 0:
            self.head = nodeLC(idMatriz=idMatriz, NombreMatriz=NombreMatriz, MatrizN=MatrizN, MatrizM=MatrizM, MatrizReal=MatrizReal)
            self.head.next = self.head
        else:
            new_node = nodeLC(idMatriz=idMatriz, NombreMatriz=NombreMatriz, MatrizN=MatrizN, MatrizM=MatrizM, MatrizReal=MatrizReal, next=self.head.next)
            self.head.next = new_node
        self.size += 1

    def imprimir(self):
        if self.head is None:
            return
        nodeLC = self.head
        print(nodeLC.idMatriz, end=") ")
        print(nodeLC.NombreMatriz)
        while nodeLC.next != self.head:
            nodeLC = nodeLC.next
            print(nodeLC.idMatriz, end=") ")
            print(nodeLC.NombreMatriz)

    def eliminar(self, idMatriz):
        nodeLC = self.head
        previous = None

        while True:
            if nodeLC.idMatriz == idMatriz:
                if previous is not None:
                    previous.next = nodeLC.next
                else:
                    while nodeLC.next != self.head:
                        nodeLC = nodeLC.next
                    nodeLC.next = self.head.next
                    self.head = self.head.next
                self.size -= 1
                return True
            elif nodeLC.next == self.head:
                return False
            previous = nodeLC
            nodeLC = nodeLC.next


# Nodo Lista Circular
class nodeLC:
    def __init__(self, idMatriz=None, NombreMatriz=None, MatrizN=None, MatrizM=None, MatrizReal=None, next=None):
        self.idMatriz = idMatriz
        self.NombreMatriz = NombreMatriz
        self.MatrizN = MatrizN
        self.MatrizM = MatrizM
        self.MatrizReal = MatrizReal
        self.next = next

#VariablesGlobales
ListaMatrices = linked_list_circular()

#################################################
# EL RESTO DEL PROGRAMA                         #
#################################################
def CargarArchivo():
    print("Cargar Archivo: ")
    print('')

    # CargarArchivo en sí
    RutaArchivo = input(
        r"Ingrese la ruta del archivo .xml que deseea cargar al programa y este le mostará los resultados: ")
    if RutaArchivo.lower().endswith(".xml"):
        Archivo = ElementTree.parse(RutaArchivo)
        print("Archivo Cargado ;v")

        Matrices = Archivo.findall('matriz')
        print("Matrices Identificadas")

        # CrearListaCircularMatrices
        idMatriz=0

        #Analisis del XML
        for Matriz in Matrices:

            # Identificar los valores de cada matriz
            NombreMatriz = Matriz.get('nombre')
            CantidadN = int(Matriz.get('n'))
            CantidadM = int(Matriz.get('m'))

            print("El Nombre de la Matriz es= " + NombreMatriz)
            print("El Valor de N es= " + str(CantidadN))
            print("El Valor de M es= " + str(CantidadM))
            print("Matriz Ingresada a la Lista Circular")

            # Muestra el Inicio de la Matriz
            print("Inicio Matriz")

            # CrearListadeFilasPorCadaMatriz
            FilasDeMatriz = linked_list_fila()

            # Ingresar cada Matriz a la Lista Circular
            idMatriz=idMatriz+1

            global ListaMatrices
            ListaMatrices.insertar(idMatriz, NombreMatriz, CantidadN, CantidadM, FilasDeMatriz)

            # CrearListaDeDatosPorCadaFila
            for i in range(0, int(CantidadN)):
                ColumnasMatriz = linked_list_datos()
                FilasDeMatriz.insertar(i + 1, ColumnasMatriz)

            # Identificar cada Dato
            Datos = Matriz.findall('dato')
            IndexColumna = 0
            for Dato in Datos:
                Filas = int(Dato.get('x'))
                Columnas = int(Dato.get('y'))
                if Filas > CantidadN:
                    print("Error este valor de N es más grande que el de la Matriz")
                elif Columnas > CantidadM:
                    print("Error este valor de M es más grande que el de la Matriz")

                # Comprobar Si esta en la Fila y Columna Correcta
                ActualFila = FilasDeMatriz.head
                ActualColumna = ColumnasMatriz.head

                # Fila Correcta
                IndexColumna = IndexColumna + 1
                while int(ActualFila.id) != Filas:
                    ActualFila = ActualFila.next

                ActualFila.Fila.insertar(IndexColumna, Dato)
                if IndexColumna >= CantidadM:
                    IndexColumna = 0

                print("Ultimo Dato Guardado: ")
                print(Dato.text)
            # Muestra el fin de la Matriz
            print("Fin Matriz")
            print("")

        # Mostrar qué hay en la lista:
        print("Las Matrices han sido guardadas, las matrices en la lista son: ")
        ListaMatrices.imprimir()

        # Volver al Menú
        print('')
        input("Archivo Cargado, ingrese cualquier valor para volver al Menú Principal: ")
        Menu()
    else:
        input(
            "Solo puede ingresar un archivo con extensión '.xml', ingrese cualquier valor para volver al Menú Principal: ")
        Menu()


def ProcesarArchivo():
    print("Procesar Archivo: ")
    print('')

    # Volver al Menú
    print('')
    input("Archivo Procesado, ingrese cualquier valor para volver al Menú Principal: ")
    Menu()


def EscribirArchivoSalida():
    print("Escribir Archivo Salida: ")
    print('')

    # Volver al Menú
    print('')
    input("Archivo Salida Escrito, ingrese cualquier valor para volver al Menú Principal: ")
    Menu()


def MostrarDatosEstudiante():
    print("Generar Gráfica: ")
    print('')

    # Mostrar Los Datos
    print('Nombre: Bryan Steve Montepeque Santos')
    print('Carnet: 201700375')
    print('Curso: Introducción a la Programación y Computación 2 Sección \"E\"')
    print('Carrera: Ingeniería en Ciencias Y Sistemas')
    print('Semestre: 4to Semestre')

    # Volver al Menú
    print('')
    input("Gráfica Generada, ingrese cualquier valor para volver al Menú Principal: ")
    Menu()

def GenerarGrafica():
    print("Las Matrices Guardadas en la Lista Circular son: ")
    print('')

    #Imprimir Lista Circular
    ListaMatrices.imprimir()

    #Pedir una opción al usuario
    OpcionMatrices = int(input("Ingrese el número de una Matriz para ver su gráfica: "))

    #Buscar la Matriz Elegida e Iniciar el String del Archivo
    Actual = ListaMatrices.head
    while not Actual.idMatriz == OpcionMatrices:
        Actual=Actual.next
    if Actual.idMatriz == OpcionMatrices:
        NombreMatriz = str(Actual.NombreMatriz)
        NMatriz = str(Actual.MatrizN)
        MMatriz = str(Actual.MatrizM)
        CodigoGraphViz = 'digraph D {\n rankdir = TB;\n 1[label=\"'+ NombreMatriz + '\"];\n 2[label=\"n=' + NMatriz + '\"];\n 3[label=\"m=' + MMatriz + '\"];\n'

        #Conexiones Iniciales
        CodigoGraphViz = CodigoGraphViz + 'Matrices -> 1\n 1 -> 2 \n 1 -> 3'

        #Agregar La Matriz Al Archivo
        NumeroNodo = 4
        #Filas
        FilaActualDeMatriz = Actual.MatrizReal.head.id
        NodoFilaActual = Actual.MatrizReal.head
        while NodoFilaActual != None:
            #Columnas
            ColumnaActualDeMatriz = NodoFilaActual.Fila.head
            while ColumnaActualDeMatriz != None:
                CodigoGraphViz = CodigoGraphViz + '\n'+str(NumeroNodo)+'[label=\"' + str(ColumnaActualDeMatriz.Dato.text)+'\"];\n'
                if NumeroNodo < (Actual.MatrizM)+3:
                  CodigoGraphViz = CodigoGraphViz + '1 -> ' + str(NumeroNodo) + '\n'
                elif NumeroNodo == (Actual.MatrizM) + 3:
                      CodigoGraphViz = CodigoGraphViz + '1 -> ' + str(Actual.MatrizM + 3) + '\n'
                elif NumeroNodo < ((Actual.MatrizM)+3)*FilaActualDeMatriz:
                    CodigoGraphViz = CodigoGraphViz + str(NumeroNodo-(Actual.MatrizM)) + '->' + str(NumeroNodo) + '\n'
                else:
                    CodigoGraphViz = CodigoGraphViz + str(NumeroNodo) + ' -> ' + str(NumeroNodo + Actual.MatrizM) + '\n'
                NumeroNodo=NumeroNodo+1
                ColumnaActualDeMatriz = ColumnaActualDeMatriz.next
            FilaActualDeMatriz = FilaActualDeMatriz + 1
            NodoFilaActual = NodoFilaActual.next


    #Generar El Archivo de GraphViz:
    with open("MatrizGraficada.dot",'w') as ArchivoGraphViz:
        #TerminarElStringDelCodigo
        CodigoGraphViz = CodigoGraphViz + "\n}"

        ArchivoGraphViz.write(CodigoGraphViz)
    ArchivoGraphViz.close()
    print('')
    print('Ahorita te lo abro ;v')
    os.system("dot -Tjpg MatrizGraficada.dot -o MatrizGraficadaReal.jpg")




    #Volver al Menú
    print('')
    input("Ingrese cualquier valor para volver al Menú Principal: ")
    Menu()


def Menu():
    # Caja Opciones Menú
    # Linea Superior
    for x in range(0, 42):
        print("-", end="")

    # Un Salto de Linea
    print()

    # Partes Intermedias

    print("| 1. Cargar Archivo                      |")
    print("| 2. Procesar Archivo                    |")
    print("| 3. Escribir Archivo Salida             |")
    print("| 4. Mostrar Datos del Estudiante        |")
    print("| 5. Generar Gráfica                     |")
    print("| 6. Salida                              |")

    # Linea Inferior
    for x in range(0, 42):
        print("-", end="")

    # Un Salto de Linea
    print()

    Valido = False
    while not Valido:
        # Texto e Input con Opciones y Try Except
        try:
            OpcionMenu = int(input("Bienvenido, Ingrese el Número de la Operación que desee realizar: "))
            if OpcionMenu == 1:
                CargarArchivo()
                Valido = True
            elif OpcionMenu == 2:
                ProcesarArchivo()
                Valido = True
            elif OpcionMenu == 3:
                EscribirArchivoSalida()
                Valido = True
            elif OpcionMenu == 4:
                MostrarDatosEstudiante()
                Valido = True
            elif OpcionMenu == 5:
                GenerarGrafica()
                Valido = True
            elif OpcionMenu == 6:
                sys.exit()
            else:
                raise ValueError
        except ValueError:
            print(
                "Solo Puede Ingresar el Número de la función, por ejemplo, ingrese '1' si desea cargar un archivo, Intentelo de nuevo ")
        print()


# Funcion que inicia el programa
Menu()
