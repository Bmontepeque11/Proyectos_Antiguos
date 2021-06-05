import webbrowser
import os
from xml.etree import ElementTree
import tkinter as tk
from tkinter import *
from tkinter import messagebox
from tkinter import filedialog
from tkinter import ttk
from PIL import Image, ImageTk


##########################################
#Matriz Ortogonal
##########################################

#Nodo de la Matriz
class Nodo:
    def __init__(self, fila, columna, valor):
        self.fila = fila
        self.columna = columna
        self.valor = valor
        self.izquierda = None
        self.arriba = None
        self.derecha = None
        self.abajo = None

#Listas de Encabezados (Filas y Columnas)
class NodoEncabezado:
    def __init__(self, id):
        self.id = id
        self.anterior = None
        self.siguiente = None
        self.accesoNodo = None

class ListaEncabezado:
    def __init__(self, primero=None):
        self.primero = primero

    def InsertarEncabezado(self, nuevo):
        if self.primero == None:
            self.primero = nuevo
        elif nuevo.id < self.primero.id:
            nuevo.siguiente = self.primero
            self.primero.anterior = nuevo
            self.primero = nuevo
        else:
            actual = self.primero
            while actual.siguiente != None:
                if nuevo.id < actual.siguiente.id:
                    nuevo.siguiente = actual.siguiente
                    actual.siguiente.anterior = nuevo
                    nuevo.anterior = actual
                    actual.siguiente = nuevo
                    break
                actual = actual.siguiente

            if actual.siguiente == None:
                actual.siguiente = nuevo
                nuevo.anterior = actual

    def MostrarEncabezado(self, id):
        actual = self.primero
        while actual != None:
            if actual.id == id:
                return actual
            actual = actual.siguiente
        return None

#Crear Matriz en sí

class Matriz:
    def __init__(self):
        self.eFilas = ListaEncabezado()
        self.eColumnas = ListaEncabezado()

    def Insertar(self, fila, columna, valor):
        nuevo = Nodo(fila, columna, valor)

        #Insercion en Filas
        eFila = self.eFilas.MostrarEncabezado(fila)
        if eFila == None:
            eFila = NodoEncabezado(fila)
            eFila.accesoNodo = nuevo
            self.eFilas.InsertarEncabezado(eFila)
        else:
            if nuevo.columna < eFila.accesoNodo.columna:
                nuevo.derecha = eFila.accesoNodo
                eFila.accesoNodo.izquierda = nuevo
                eFila.accesoNodo = nuevo
            else:
                actual = eFila.accesoNodo
                while actual.derecha != None:
                    if nuevo.columna < actual.derecha.columna:
                        nuevo.derecha = actual.derecha
                        actual.derecha.izquierda = nuevo
                        nuevo.izquierda = actual
                        actual.derecha = nuevo
                        break
                    actual = actual.derecha

                if actual.derecha == None:
                    actual.derecha = nuevo
                    nuevo.izquierda = actual


        # Insercion en Columnas
        eColumna = self.eColumnas.MostrarEncabezado(columna)
        if eColumna == None:
            eColumna = NodoEncabezado(columna)
            eColumna.accesoNodo = nuevo
            self.eColumnas.InsertarEncabezado(eColumna)
        else:
            if nuevo.fila < eColumna.accesoNodo.fila:
                nuevo.abajo = eColumna.accesoNodo
                eColumna.accesoNodo.arriba = nuevo
                eColumna.accesoNodo = nuevo
            else:
                actual = eColumna.accesoNodo
                while actual.abajo != None:
                    if nuevo.fila < actual.abajo.fila:
                        nuevo.abajo = actual.abajo
                        actual.abajo.arriba = nuevo
                        nuevo.arriba = actual
                        actual.abajo = nuevo
                        break
                    actual = actual.abajo

                if actual.abajo == None:
                    actual.abajo = nuevo
                    nuevo.arriba = actual

    def MostrarNodoMatriz(self, fila, columna):
        #Actuales para recorrer matriz
        actualFilas = self.eFilas.primero

        while actualFilas != None:
            actualColumnas = actualFilas.accesoNodo
            while actualColumnas != None:
                if actualColumnas.fila == fila and actualColumnas.columna == columna:
                    return actualColumnas
                actualColumnas = actualColumnas.derecha
            actualFilas = actualFilas.siguiente

    def recorrerFilas(self):
        efila = self.eFilas.primero
        print('**** Esta Fila****')
        while efila != None:
            actual = efila.accesoNodo
            print("\nFila" + str(actual.fila))
            print("Columna       Valor")
            while actual != None:
                print(str(actual.columna)+ "       " + str(actual.valor))
                actual = actual.derecha
            efila = efila.siguiente
        print('**** Fin de Esta Fila****')

    def recorrerColumnas(self):
        eColumna = self.eColumnas.primero
        print('**** Esta Columna ****')
        while eColumna != None:
            actual = eColumna.accesoNodo
            print("\nColumna" + str(actual.columna))
            print("Columna       Valor")
            while actual != None:
                print(str(actual.fila)+ "       " + str(actual.valor))
                actual = actual.abajo
            eColumna = eColumna.siguiente
        print('**** Fin de Esta Columna ****')


##########################################
#Variables Globales                      #
##########################################
#Componentes Tkinter

#RotacionHorizontal
###########################################
#Combobox
MatrizElegidaRH = ttk.Combobox
MatrizElegidaRHReal = ''

#RotacionVertical
###########################################
#Combobox
MatrizElegidaRV = ttk.Combobox
MatrizElegidaRVReal = ''

#Transpuesta
###########################################
#Combobox
MatrizElegidaT = ttk.Combobox
MatrizElegidaTReal = ''

#Linea Horizontal
###########################################
#TextboxFila
txtbxFilaILH = Entry
FilaILH = ''
#TextboxColumna
txtbxColumnaILH = Entry
ColumnaILH = ''

#TextboxNEspacios
txtbxNEspaciosLH = Entry
NEspaciosLH = ''

#Combobox
MatrizElegidaLH = ttk.Combobox
MatrizElegidaLHReal = ''

#Linea Vertical
###########################################
#TextboxFila
txtbxFilaILV = Entry
FilaILV = ''
#TextboxColumna
txtbxColumnaILV = Entry
ColumnaILV = ''

#TextboxNEspacios
txtbxNEspaciosLV = Entry
NEspaciosLV = ''

#Combobox
MatrizElegidaLV = ttk.Combobox
MatrizElegidaLVReal = ''

#Rectangulo
###########################################
#TextboxFila
txtbxFilaIR = Entry
FilaIR = ''
#TextboxColumna
txtbxColumnaIR = Entry
ColumnaIR = ''

#TextboxNEspaciosXR
txtbxNEspaciosXR = Entry
NEspaciosXR = ''

#TextboxNEspaciosYR
txtbxNEspaciosYR = Entry
NEspaciosYR = ''

#Combobox
MatrizElegidaR = ttk.Combobox
MatrizElegidaIRReal = ''

#Truangulo
###########################################
#TextboxFila
txtbxFilaIT = Entry
FilaIT = ''
#TextboxColumna
txtbxColumnaIT = Entry
ColumnaIT = ''

#TextboxNEspacios
txtbxNEspaciosIT = Entry
NEspaciosIT = ''

#Combobox
MatrizElegidaIT = ttk.Combobox
MatrizElegidaITReal = ''

#Matrices
###########################
MatrizOrtogonal1 = Matriz()
MatrizOrtogonal2 = Matriz()

#Nombres Matrices
NombreMO1 = ''
NombreMO2 = ''

#Dimensiones Matrices

#MO1
FilasMO1 = ''
ColumnasMO1 = ''

#MO2
FilasMO2 = ''
ColumnasMO2 = ''

#MOR
FilasMOR = ''
ColumnasMOR = ''

#Imagen Matrices

#MO1
ImagenMO1 = ''

#MO2
ImagenMO2 = ''

#####################################################
#CREAR FRAME INICIAL                                #
#####################################################
Ventana = tk.Tk()
Ventana.geometry("1200x500")
Ventana.title("IPC2_Proyecto2_201700375")

def nada():
    x = 0
#####################################################
#METODOS CARGAR ARCHIVO                             #
#####################################################
def CargarArchivo():
    filters = (("Archivos de XML", "*.xml"), ("Todos los Archivos", "*.*"))
    ArchivoAbierto = tk.filedialog.askopenfilename(filetypes=filters)
    Archivo = ElementTree.parse(ArchivoAbierto)

    #Analizar el XML
    Matrices = Archivo.findall('matriz')
    print("Matrices Identificadas")
    print('')

    #Contador de Matrices
    CMatrices = 0

    for Matriz in Matrices:
        CMatrices = CMatrices + 1

        ###############################################
        #Variables Globales
        ###############################################

        #Matriz Ortogonal 1:
        global MatrizOrtogonal1
        global NombreMO1
        global FilasMO1
        global ColumnasMO1
        global ImagenMO1

        #Matriz Ortogonal 2:
        global MatrizOrtogonal2
        global NombreMO2
        global FilasMO2
        global ColumnasMO2
        global ImagenMO2

        #Verificar que haya reconocido bien los datos de las matrices:
        if CMatrices == 1:
            #Nombre
            NombreMO1 = Matriz.find('nombre').text
            print('Nombre Matriz 1: ' + str(NombreMO1))

            #Filas
            FilasMO1 = Matriz.find('filas').text
            print('Filas Matriz 1: ' + str(FilasMO1))

            #Columnas
            ColumnasMO1 = Matriz.find('columnas').text
            print('Columnas Matriz 1: ' + str(ColumnasMO1))

            #Imagen
            ImagenMO1 = Matriz.find('imagen').text
            print('Imagen Matriz 1: ' + str(ImagenMO1))

            #Analizar La Imagen Caracter por caracter (Mini Analizador Lexcico)
            Fila = 0
            Columna = 0
            CaracterActual = ''
            for i in range(len(ImagenMO1)):
                Columna = Columna + 1
                CaracterActual = ImagenMO1[i]
                if CaracterActual == "\n":
                    Fila = Fila + 1
                    Columna = 0
                elif CaracterActual == "\t":
                    Columna = Columna - 1
                elif CaracterActual == "-":
                    print('Fila Actual: ' + str(Fila))
                    print('Columna Actual: ' + str(Columna))
                    print('Celda Vacia')
                    print('')
                elif CaracterActual == "*":
                    print('Fila Actual: ' + str(Fila))
                    print('Columna Actual: ' + str(Columna))
                    print('Celda Llena')

                    #Insertar Nodo en Matriz Ortogonal
                    MatrizOrtogonal1.Insertar(Fila, Columna, CaracterActual)
                    print('Dato Almacenado')
                    print('')

            #Graficar La Matriz en GraphViz:
            print('')
            print('Generando la Imagen en Graphviz...')
            CodigoGraphViz = "graph G {\n subgraph tabla {\n node [shape = square]\n" + str(NombreMO1) +  "[label=<\n<TABLE border = \"1\">\n"

            #Fila Titulos Columnas:

            #Nombre Matriz
            CodigoGraphViz = CodigoGraphViz + '<TR>\n<TD border = \"1\">'+ str(NombreMO1) + "</TD>\n"

            #Numeros Columnas
            for i in range(1, int(FilasMO1)+1):
                CodigoGraphViz = CodigoGraphViz + '<TD border = \"1\">' + str(i) + "</TD>\n"

            #Cerrar Filas
            CodigoGraphViz = CodigoGraphViz + "</TR>\n"

            #Crear Matriz Vacia:
            for i in range(1, int(FilasMO1) + 1):
                CodigoGraphViz = CodigoGraphViz + "<TR>\n"

                #Numero Fila
                CodigoGraphViz = CodigoGraphViz + "<TD border=\"1\">" + str(i) + "</TD>\n"

                for j in range(1, int(ColumnasMO1) + 1):
                    if MatrizOrtogonal1.MostrarNodoMatriz(i, j) == None:
                        CodigoGraphViz = CodigoGraphViz + "<TD border=\"1\"> </TD>\n"
                    elif MatrizOrtogonal1.MostrarNodoMatriz(i, j).valor == "*":
                        CodigoGraphViz = CodigoGraphViz + "<TD border=\"1\">*</TD>\n"

                #Cerrar Fila
                CodigoGraphViz = CodigoGraphViz + "</TR>\n"

            #Cerrar Tabla
            CodigoGraphViz = CodigoGraphViz + "</TABLE>>];\n}"

            # Generar el archivo GraphViz
            with open("MatrizOrtogonal1.dot", 'w') as ArchivoGraphViz:
                # TerminarElStringDelCodigo
                CodigoGraphViz = CodigoGraphViz + "\n}"
                ArchivoGraphViz.write(CodigoGraphViz)

            ArchivoGraphViz.close()
            print('')
            print('Ahorita pongo la imagen en Matriz Ortogonal 1 ;v')
            os.system("dot -Tjpg MatrizOrtogonal1.dot -o MO1Actual.jpg")

            # Poner Imagen en el Label
            FotoLabel1 = Image.open("MO1Actual.jpg").resize((350, 350), Image.ANTIALIAS)
            Ventana.FotoLabel1Real = ImageTk.PhotoImage(FotoLabel1)

            Matriz1 = Label(Panel, image=Ventana.FotoLabel1Real)
            Matriz1.place(x=10, y=5, height=350, width=350)

            print('')

        if CMatrices == 2:
            NombreMO2 = Matriz.find('nombre').text
            print('Nombre Matriz 2: ' + str(NombreMO2))

            #Filas
            FilasMO2 = Matriz.find('filas').text
            print('Filas Matriz 2: ' + str(FilasMO2))

            #Columnas
            ColumnasMO2 = Matriz.find('columnas').text
            print('Columnas Matriz 2: ' + str(ColumnasMO2))

            #Imagen
            ImagenMO2 = Matriz.find('imagen').text
            print('Imagen Matriz 2: ' + str(ImagenMO2))

            # Analizar La Imagen Caracter por caracter (Mini Analizador Lexcico)
            Fila = 0
            Columna = 0
            CaracterActual = ''
            for i in range(len(ImagenMO2)):
                Columna = Columna + 1
                CaracterActual = ImagenMO2[i]
                if CaracterActual == "\n":
                    Fila = Fila + 1
                    Columna = 0
                elif CaracterActual == "\t":
                    Columna = Columna - 1
                elif CaracterActual == "-":
                    print('Fila Actual: ' + str(Fila))
                    print('Columna Actual: ' + str(Columna))
                    print('Celda Vacia')
                    print('')
                elif CaracterActual == "*":
                    print('Fila Actual: ' + str(Fila))
                    print('Columna Actual: ' + str(Columna))
                    print('Celda Llena')

                    # Insertar Nodo en Matriz Ortogonal
                    MatrizOrtogonal2.Insertar(Fila, Columna, CaracterActual)
                    print('Dato Almacenado')
                    print('')

            #Graficar La Matriz en GraphViz:
            print('')
            print('Generando la Imagen en Graphviz...')
            CodigoGraphViz = "graph G {\n subgraph tabla {\n node [shape = square]\n" + str(NombreMO2) + "[label=<\n<TABLE border = \"1\">\n"

            # Fila Titulos Columnas:

            #Nombre Matriz
            CodigoGraphViz = CodigoGraphViz + '<TR>\n<TD border = \"1\">' + str(NombreMO2) + "</TD>\n"

            #Numeros Columnas
            for i in range(1, int(FilasMO2) + 1):
                CodigoGraphViz = CodigoGraphViz + '<TD border = \"1\">' + str(i) + "</TD>\n"

            #Cerrar Filas
            CodigoGraphViz = CodigoGraphViz + "</TR>\n"

            #Crear Matriz Vacia:
            for i in range(1, int(FilasMO2) + 1):
                CodigoGraphViz = CodigoGraphViz + "<TR>\n"

                #Numero Fila
                CodigoGraphViz = CodigoGraphViz + "<TD border=\"1\">" + str(i) + "</TD>\n"

                for j in range(1, int(ColumnasMO2) + 1):
                    if MatrizOrtogonal2.MostrarNodoMatriz(i, j) == None:
                        CodigoGraphViz = CodigoGraphViz + "<TD border=\"1\"> </TD>\n"
                    elif MatrizOrtogonal2.MostrarNodoMatriz(i, j).valor == "*":
                        CodigoGraphViz = CodigoGraphViz + "<TD border=\"1\">*</TD>\n"

                # Cerrar Fila
                CodigoGraphViz = CodigoGraphViz + "</TR>\n"

            # Cerrar Tabla
            CodigoGraphViz = CodigoGraphViz + "</TABLE>>];\n}"

            # Generar el archivo GraphViz
            with open("MatrizOrtogonal2.dot", 'w') as ArchivoGraphViz:
                # TerminarElStringDelCodigo
                CodigoGraphViz = CodigoGraphViz + "\n}"
                ArchivoGraphViz.write(CodigoGraphViz)

            ArchivoGraphViz.close()
            print('')
            print('Ahorita pongo la imagen en Matriz Ortogonal 2 ;v')
            os.system("dot -Tjpg MatrizOrtogonal2.dot -o MO2Actual.jpg")

            # Poner Imagen en el Label
            FotoLabel2 = Image.open("MO2Actual.jpg").resize((350, 350), Image.ANTIALIAS)
            Ventana.FotoLabel2Real = ImageTk.PhotoImage(FotoLabel2)

            Matriz2 = Label(Panel, image=Ventana.FotoLabel2Real)
            Matriz2.place(x=400, y=5, height=350, width=350)

            print('')
#####################################################
#METODOS OPERACIONES UNA MATRIZ                     #
#####################################################

#Rotacion Horizontal
def RotacionHorizontal():
    # Variables Globales
    global MatrizElegidaRH
    global MatrizElegidaRHReal
    global MatrizOrtogonal1
    global MatrizOrtogonal2
    MatrizOrtogonalR = Matriz()

    # Cual Matriz?
    if MatrizElegidaRH.get() == "1":
        MatrizElegidaRHReal = '1'
        tk.messagebox.showinfo(title="Info", message="Eligió la Matriz 1")

        # Pasar Matriz Original a la Resultado
        for i in range(1, int(FilasMO1) + 1):
            for j in range(1, int(ColumnasMO1) + 1):
                if MatrizOrtogonal1.MostrarNodoMatriz(i, j) == None:
                    print("Celda " + str(i) + ", " + str(j) + " Vacia")
                elif MatrizOrtogonal1.MostrarNodoMatriz(i, j).valor == "*":
                    MatrizOrtogonalR.Insertar(i, j, "*")

        # Graficar La Matriz en GraphViz:
        print('')
        print('Generando la Imagen en Graphviz...')
        CodigoGraphViz = "graph G {\n subgraph tabla {\n node [shape = square]\n" + "M_Res" + "[label=<\n<TABLE border = \"1\">\n"

        # Fila Titulos Columnas:

        # Nombre Matriz
        CodigoGraphViz = CodigoGraphViz + '<TR>\n<TD border = \"1\">' + "M_Res" + "</TD>\n"

        # Numeros Columnas
        for i in range(1, int(FilasMO1) + 1):
            CodigoGraphViz = CodigoGraphViz + '<TD border = \"1\">' + str(i) + "</TD>\n"

        # Cerrar Filas
        CodigoGraphViz = CodigoGraphViz + "</TR>\n"

        # Crear Matriz Vacia:
        for i in range(int(FilasMO1), 0, -1):
            CodigoGraphViz = CodigoGraphViz + "<TR>\n"

            # Numero Fila
            CodigoGraphViz = CodigoGraphViz + "<TD border=\"1\">" + str(i) + "</TD>\n"

            for j in range(1, int(ColumnasMO1) + 1):
                if MatrizOrtogonalR.MostrarNodoMatriz(i, j) == None:
                    CodigoGraphViz = CodigoGraphViz + "<TD border=\"1\"> </TD>\n"
                elif MatrizOrtogonalR.MostrarNodoMatriz(i, j).valor == "*":
                    CodigoGraphViz = CodigoGraphViz + "<TD border=\"1\">*</TD>\n"

            # Cerrar Fila
            CodigoGraphViz = CodigoGraphViz + "</TR>\n"

        # Cerrar Tabla
        CodigoGraphViz = CodigoGraphViz + "</TABLE>>];\n}"

        # Generar el archivo GraphViz
        with open("MatrizOrtogonalR.dot", 'w') as ArchivoGraphViz:
            # TerminarElStringDelCodigo
            CodigoGraphViz = CodigoGraphViz + "\n}"
            ArchivoGraphViz.write(CodigoGraphViz)

        ArchivoGraphViz.close()
        print('')
        print('Ahorita pongo la imagen en Matriz Ortogonal R ;v')
        os.system("dot -Tjpg MatrizOrtogonalR.dot -o MORActual.jpg")

        # Poner Imagen en el Label
        FotoLabelR = Image.open("MORActual.jpg").resize((350, 350), Image.ANTIALIAS)
        Ventana.FotoLabelRReal = ImageTk.PhotoImage(FotoLabelR)

        MatrizR = Label(Panel, image=Ventana.FotoLabelRReal)
        MatrizR.place(x=800, y=5, height=350, width=350)

        print('')
    elif MatrizElegidaRH.get() == "2":
        print("Eligio la 2")
        MatrizElegidaRHReal = '2'
        tk.messagebox.showinfo(title="Info", message="Eligió la Matriz 2")

        # Pasar Matriz Original a la Resultado
        for i in range(1, int(FilasMO2) + 1):
            for j in range(1, int(ColumnasMO2) + 1):
                if MatrizOrtogonal2.MostrarNodoMatriz(i, j) == None:
                    print("Celda " + str(i) + ", " + str(j) + " Vacia")
                elif MatrizOrtogonal2.MostrarNodoMatriz(i, j).valor == "*":
                    MatrizOrtogonalR.Insertar(i, j, "*")

        # Graficar La Matriz en GraphViz:
        print('')
        print('Generando la Imagen en Graphviz...')
        CodigoGraphViz = "graph G {\n subgraph tabla {\n node [shape = square]\n" + "M_Res" + "[label=<\n<TABLE border = \"1\">\n"

        # Fila Titulos Columnas:

        # Nombre Matriz
        CodigoGraphViz = CodigoGraphViz + '<TR>\n<TD border = \"1\">' + "M_Res" + "</TD>\n"

        # Numeros Columnas
        for i in range(1, int(FilasMO2) + 1):
            CodigoGraphViz = CodigoGraphViz + '<TD border = \"1\">' + str(i) + "</TD>\n"

        # Cerrar Filas
        CodigoGraphViz = CodigoGraphViz + "</TR>\n"

        # Crear Matriz Vacia:
        for i in range(int(FilasMO2), 0, -1):
            CodigoGraphViz = CodigoGraphViz + "<TR>\n"

            # Numero Fila
            CodigoGraphViz = CodigoGraphViz + "<TD border=\"1\">" + str(i) + "</TD>\n"

            for j in range(1, int(ColumnasMO2) + 1):
                if MatrizOrtogonalR.MostrarNodoMatriz(i, j) == None:
                    CodigoGraphViz = CodigoGraphViz + "<TD border=\"1\"> </TD>\n"
                elif MatrizOrtogonalR.MostrarNodoMatriz(i, j).valor == "*":
                    CodigoGraphViz = CodigoGraphViz + "<TD border=\"1\">*</TD>\n"

            # Cerrar Fila
            CodigoGraphViz = CodigoGraphViz + "</TR>\n"

        # Cerrar Tabla
        CodigoGraphViz = CodigoGraphViz + "</TABLE>>];\n}"

        # Generar el archivo GraphViz
        with open("MatrizOrtogonalR.dot", 'w') as ArchivoGraphViz:
            # TerminarElStringDelCodigo
            CodigoGraphViz = CodigoGraphViz + "\n}"
            ArchivoGraphViz.write(CodigoGraphViz)

        ArchivoGraphViz.close()
        print('')
        print('Ahorita pongo la imagen en Matriz Ortogonal R ;v')
        os.system("dot -Tjpg MatrizOrtogonalR.dot -o MORActual.jpg")

        # Poner Imagen en el Label
        FotoLabelR = Image.open("MORActual.jpg").resize((350, 350), Image.ANTIALIAS)
        Ventana.FotoLabelRReal = ImageTk.PhotoImage(FotoLabelR)

        MatrizR = Label(Panel, image=Ventana.FotoLabelRReal)
        MatrizR.place(x=800, y=5, height=350, width=350)

        print('')

def VentanaRotacionHorizontal():
    VentanaRH = tk.Tk()
    VentanaRH.geometry("275x150")
    VentanaRH.title("Rotación Horizontal")

    LabelSaludo = Label(VentanaRH, text="Bienvenido, indique cuál matriz desea Rotar")
    LabelSaludo.place(x=3,y=5, height=30, width=275)

    #ComboBox
    n = tk.StringVar()
    global MatrizElegidaRH
    MatrizElegidaRH = ttk.Combobox(VentanaRH, width=27, textvariable=n)
    MatrizElegidaRH['values'] = ("1", "2")
    MatrizElegidaRH.place(x=35, y=40, height=20, width=200)

    #Boton Confirmacion
    btnOk = Button(VentanaRH, text="Ok", command = RotacionHorizontal)
    btnOk.place(x=130, y=65, height=20, width=30)

    VentanaRH.mainloop()

#Rotacion Vertical
def RotacionVertical():
    # Variables Globales
    global MatrizElegidaRV
    global MatrizElegidaRVReal
    global MatrizOrtogonal1
    global MatrizOrtogonal2
    MatrizOrtogonalR = Matriz()

    # Cual Matriz?
    if MatrizElegidaRV.get() == "1":
        MatrizElegidaRVReal = '1'
        tk.messagebox.showinfo(title="Info", message="Eligió la Matriz 1")

        # Pasar Matriz Original a la Resultado
        for i in range(1, int(FilasMO1) + 1):
            for j in range(1, int(ColumnasMO1) + 1):
                if MatrizOrtogonal1.MostrarNodoMatriz(i, j) == None:
                    print("Celda " + str(i) + ", " + str(j) + " Vacia")
                elif MatrizOrtogonal1.MostrarNodoMatriz(i, j).valor == "*":
                    MatrizOrtogonalR.Insertar(i, j, "*")

        # Graficar La Matriz en GraphViz:
        print('')
        print('Generando la Imagen en Graphviz...')
        CodigoGraphViz = "graph G {\n subgraph tabla {\n node [shape = square]\n" + "M_Res" + "[label=<\n<TABLE border = \"1\">\n"

        # Fila Titulos Columnas:

        # Nombre Matriz
        CodigoGraphViz = CodigoGraphViz + '<TR>\n<TD border = \"1\">' + "M_Res" + "</TD>\n"

        # Numeros Columnas
        for i in range(1, int(FilasMO1) + 1):
            CodigoGraphViz = CodigoGraphViz + '<TD border = \"1\">' + str(i) + "</TD>\n"

        # Cerrar Filas
        CodigoGraphViz = CodigoGraphViz + "</TR>\n"

        # Crear Matriz Vacia:
        for i in range(1, int(FilasMO1) + 1):
            CodigoGraphViz = CodigoGraphViz + "<TR>\n"

            # Numero Fila
            CodigoGraphViz = CodigoGraphViz + "<TD border=\"1\">" + str(i) + "</TD>\n"

            for j in range(int(ColumnasMO1), 0, -1):
                if MatrizOrtogonalR.MostrarNodoMatriz(i, j) == None:
                    CodigoGraphViz = CodigoGraphViz + "<TD border=\"1\"> </TD>\n"
                elif MatrizOrtogonalR.MostrarNodoMatriz(i, j).valor == "*":
                    CodigoGraphViz = CodigoGraphViz + "<TD border=\"1\">*</TD>\n"

            # Cerrar Fila
            CodigoGraphViz = CodigoGraphViz + "</TR>\n"

        # Cerrar Tabla
        CodigoGraphViz = CodigoGraphViz + "</TABLE>>];\n}"

        # Generar el archivo GraphViz
        with open("MatrizOrtogonalR.dot", 'w') as ArchivoGraphViz:
            # TerminarElStringDelCodigo
            CodigoGraphViz = CodigoGraphViz + "\n}"
            ArchivoGraphViz.write(CodigoGraphViz)

        ArchivoGraphViz.close()
        print('')
        print('Ahorita pongo la imagen en Matriz Ortogonal R ;v')
        os.system("dot -Tjpg MatrizOrtogonalR.dot -o MORActual.jpg")

        # Poner Imagen en el Label
        FotoLabelR = Image.open("MORActual.jpg").resize((350, 350), Image.ANTIALIAS)
        Ventana.FotoLabelRReal = ImageTk.PhotoImage(FotoLabelR)

        MatrizR = Label(Panel, image=Ventana.FotoLabelRReal)
        MatrizR.place(x=800, y=5, height=350, width=350)

        print('')
    elif MatrizElegidaRV.get() == "2":
        print("Eligio la 2")
        MatrizElegidaRVReal = '2'
        tk.messagebox.showinfo(title="Info", message="Eligió la Matriz 2")

        # Pasar Matriz Original a la Resultado
        for i in range(1, int(FilasMO2) + 1):
            for j in range(1, int(ColumnasMO2) + 1):
                if MatrizOrtogonal2.MostrarNodoMatriz(i, j) == None:
                    print("Celda " + str(i) + ", " + str(j) + " Vacia")
                elif MatrizOrtogonal2.MostrarNodoMatriz(i, j).valor == "*":
                    MatrizOrtogonalR.Insertar(i, j, "*")

        # Graficar La Matriz en GraphViz:
        print('')
        print('Generando la Imagen en Graphviz...')
        CodigoGraphViz = "graph G {\n subgraph tabla {\n node [shape = square]\n" + "M_Res" + "[label=<\n<TABLE border = \"1\">\n"

        # Fila Titulos Columnas:

        # Nombre Matriz
        CodigoGraphViz = CodigoGraphViz + '<TR>\n<TD border = \"1\">' + "M_Res" + "</TD>\n"

        # Numeros Columnas
        for i in range(1, int(FilasMO2) + 1):
            CodigoGraphViz = CodigoGraphViz + '<TD border = \"1\">' + str(i) + "</TD>\n"

        # Cerrar Filas
        CodigoGraphViz = CodigoGraphViz + "</TR>\n"

        # Crear Matriz Vacia:
        for i in range(1, int(FilasMO2) + 1):
            CodigoGraphViz = CodigoGraphViz + "<TR>\n"

            # Numero Fila
            CodigoGraphViz = CodigoGraphViz + "<TD border=\"1\">" + str(i) + "</TD>\n"

            for j in range(int(ColumnasMO2), 0, -1):
                if MatrizOrtogonalR.MostrarNodoMatriz(i, j) == None:
                    CodigoGraphViz = CodigoGraphViz + "<TD border=\"1\"> </TD>\n"
                elif MatrizOrtogonalR.MostrarNodoMatriz(i, j).valor == "*":
                    CodigoGraphViz = CodigoGraphViz + "<TD border=\"1\">*</TD>\n"

            # Cerrar Fila
            CodigoGraphViz = CodigoGraphViz + "</TR>\n"

        # Cerrar Tabla
        CodigoGraphViz = CodigoGraphViz + "</TABLE>>];\n}"

        # Generar el archivo GraphViz
        with open("MatrizOrtogonalR.dot", 'w') as ArchivoGraphViz:
            # TerminarElStringDelCodigo
            CodigoGraphViz = CodigoGraphViz + "\n}"
            ArchivoGraphViz.write(CodigoGraphViz)

        ArchivoGraphViz.close()
        print('')
        print('Ahorita pongo la imagen en Matriz Ortogonal R ;v')
        os.system("dot -Tjpg MatrizOrtogonalR.dot -o MORActual.jpg")

        # Poner Imagen en el Label
        FotoLabelR = Image.open("MORActual.jpg").resize((350, 350), Image.ANTIALIAS)
        Ventana.FotoLabelRReal = ImageTk.PhotoImage(FotoLabelR)

        MatrizR = Label(Panel, image=Ventana.FotoLabelRReal)
        MatrizR.place(x=800, y=5, height=350, width=350)

        print('')

def VentanaRotacionVertical():
    VentanaRV = tk.Tk()
    VentanaRV.geometry("275x150")
    VentanaRV.title("Rotación Vertical")

    LabelSaludo = Label(VentanaRV, text="Bienvenido, indique cuál matriz desea Rotar")
    LabelSaludo.place(x=3,y=5, height=30, width=275)

    #ComboBox
    n = tk.StringVar()
    global MatrizElegidaRV
    MatrizElegidaRV = ttk.Combobox(VentanaRV, width=27, textvariable=n)
    MatrizElegidaRV['values'] = ("1", "2")
    MatrizElegidaRV.place(x=35, y=40, height=20, width=200)

    #Boton Confirmacion
    btnOk = Button(VentanaRV, text="Ok", command = RotacionVertical)
    btnOk.place(x=130, y=65, height=20, width=30)

    VentanaRV.mainloop()

#Transpuesta
def Transpuesta():
    #Variables Globales
    global MatrizElegidaT
    global MatrizElegidaTReal
    global MatrizOrtogonal1
    global MatrizOrtogonal2
    MatrizOrtogonalR = Matriz()

    #Cual Matriz?
    if MatrizElegidaT.get() == "1":
        MatrizElegidaTReal = '1'
        tk.messagebox.showinfo(title="Info", message="Eligió la Matriz 1")

        # Pasar Matriz Original a la Resultado
        for i in range(1, int(FilasMO1) + 1):
            for j in range(1, int(ColumnasMO1) + 1):
                if MatrizOrtogonal1.MostrarNodoMatriz(i, j) == None:
                    print("Celda " + str(i) + ", " + str(j) + " Vacia")
                elif MatrizOrtogonal1.MostrarNodoMatriz(i, j).valor == "*":
                    MatrizOrtogonalR.Insertar(j, i, "*")

        # Graficar La Matriz en GraphViz:
        print('')
        print('Generando la Imagen en Graphviz...')
        CodigoGraphViz = "graph G {\n subgraph tabla {\n node [shape = square]\n" + "M_Res" + "[label=<\n<TABLE border = \"1\">\n"

        # Fila Titulos Columnas:

        # Nombre Matriz
        CodigoGraphViz = CodigoGraphViz + '<TR>\n<TD border = \"1\">' + "M_Res" + "</TD>\n"

        # Numeros Columnas
        for i in range(1, int(FilasMO1) + 1):
            CodigoGraphViz = CodigoGraphViz + '<TD border = \"1\">' + str(i) + "</TD>\n"

        # Cerrar Filas
        CodigoGraphViz = CodigoGraphViz + "</TR>\n"

        # Crear Matriz Vacia:
        for i in range(1, int(FilasMO1) + 1):
            CodigoGraphViz = CodigoGraphViz + "<TR>\n"

            # Numero Fila
            CodigoGraphViz = CodigoGraphViz + "<TD border=\"1\">" + str(i) + "</TD>\n"

            for j in range(1, int(ColumnasMO1) + 1):
                if MatrizOrtogonalR.MostrarNodoMatriz(i, j) == None:
                    CodigoGraphViz = CodigoGraphViz + "<TD border=\"1\"> </TD>\n"
                elif MatrizOrtogonalR.MostrarNodoMatriz(i, j).valor == "*":
                    CodigoGraphViz = CodigoGraphViz + "<TD border=\"1\">*</TD>\n"

            # Cerrar Fila
            CodigoGraphViz = CodigoGraphViz + "</TR>\n"

        # Cerrar Tabla
        CodigoGraphViz = CodigoGraphViz + "</TABLE>>];\n}"

        # Generar el archivo GraphViz
        with open("MatrizOrtogonalR.dot", 'w') as ArchivoGraphViz:
            # TerminarElStringDelCodigo
            CodigoGraphViz = CodigoGraphViz + "\n}"
            ArchivoGraphViz.write(CodigoGraphViz)

        ArchivoGraphViz.close()
        print('')
        print('Ahorita pongo la imagen en Matriz Ortogonal R ;v')
        os.system("dot -Tjpg MatrizOrtogonalR.dot -o MORActual.jpg")

        # Poner Imagen en el Label
        FotoLabelR = Image.open("MORActual.jpg").resize((350, 350), Image.ANTIALIAS)
        Ventana.FotoLabelRReal = ImageTk.PhotoImage(FotoLabelR)

        MatrizR = Label(Panel, image=Ventana.FotoLabelRReal)
        MatrizR.place(x=800, y=5, height=350, width=350)

        print('')
    elif MatrizElegidaT.get() == "2":
        print("Eligio la 2")
        MatrizElegidaTReal = '2'
        tk.messagebox.showinfo(title="Info", message="Eligió la Matriz 2")

        #Pasar Matriz Original a la Resultado
        for i in range(1, int(FilasMO2) + 1):
            for j in range(1, int(ColumnasMO2) + 1):
                if MatrizOrtogonal2.MostrarNodoMatriz(i, j) == None:
                    print("Celda " + str(i) + ", "+ str(j) +" Vacia")
                elif MatrizOrtogonal2.MostrarNodoMatriz(i, j).valor == "*":
                    MatrizOrtogonalR.Insertar(j, i, "*")

        # Graficar La Matriz en GraphViz:
        print('')
        print('Generando la Imagen en Graphviz...')
        CodigoGraphViz = "graph G {\n subgraph tabla {\n node [shape = square]\n" + "M_Res" + "[label=<\n<TABLE border = \"1\">\n"

        # Fila Titulos Columnas:

        # Nombre Matriz
        CodigoGraphViz = CodigoGraphViz + '<TR>\n<TD border = \"1\">' + "M_Res" + "</TD>\n"

        # Numeros Columnas
        for i in range(1, int(FilasMO2) + 1):
            CodigoGraphViz = CodigoGraphViz + '<TD border = \"1\">' + str(i) + "</TD>\n"

        # Cerrar Filas
        CodigoGraphViz = CodigoGraphViz + "</TR>\n"

        # Crear Matriz Vacia:
        for i in range(1, int(FilasMO2) + 1):
            CodigoGraphViz = CodigoGraphViz + "<TR>\n"

            # Numero Fila
            CodigoGraphViz = CodigoGraphViz + "<TD border=\"1\">" + str(i) + "</TD>\n"

            for j in range(1, int(ColumnasMO2) + 1):
                if MatrizOrtogonalR.MostrarNodoMatriz(i, j) == None:
                    CodigoGraphViz = CodigoGraphViz + "<TD border=\"1\"> </TD>\n"
                elif MatrizOrtogonalR.MostrarNodoMatriz(i, j).valor == "*":
                    CodigoGraphViz = CodigoGraphViz + "<TD border=\"1\">*</TD>\n"

            # Cerrar Fila
            CodigoGraphViz = CodigoGraphViz + "</TR>\n"

        # Cerrar Tabla
        CodigoGraphViz = CodigoGraphViz + "</TABLE>>];\n}"

        # Generar el archivo GraphViz
        with open("MatrizOrtogonalR.dot", 'w') as ArchivoGraphViz:
            # TerminarElStringDelCodigo
            CodigoGraphViz = CodigoGraphViz + "\n}"
            ArchivoGraphViz.write(CodigoGraphViz)

        ArchivoGraphViz.close()
        print('')
        print('Ahorita pongo la imagen en Matriz Ortogonal R ;v')
        os.system("dot -Tjpg MatrizOrtogonalR.dot -o MORActual.jpg")

        # Poner Imagen en el Label
        FotoLabelR = Image.open("MORActual.jpg").resize((350, 350), Image.ANTIALIAS)
        Ventana.FotoLabelRReal = ImageTk.PhotoImage(FotoLabelR)

        MatrizR = Label(Panel, image=Ventana.FotoLabelRReal)
        MatrizR.place(x=800, y=5, height=350, width=350)

        print('')

def VentanaTranspuesta():
    VentanaT = tk.Tk()
    VentanaT.geometry("275x150")
    VentanaT.title("Transpuesta")

    LabelSaludo = Label(VentanaT, text="Bienvenido, indique cuál Transponerá")
    LabelSaludo.place(x=3, y=5, height=30, width=275)

    # ComboBox
    n = tk.StringVar()
    global MatrizElegidaT
    MatrizElegidaT = ttk.Combobox(VentanaT, width=27, textvariable=n)
    MatrizElegidaT['values'] = ("1", "2")
    MatrizElegidaT.place(x=35, y=40, height=20, width=200)

    # Boton Confirmacion
    btnOk = Button(VentanaT, text="Ok", command=Transpuesta)
    btnOk.place(x=130, y=65, height=20, width=30)

    VentanaT.mainloop()


#Linea Horizontal
def LineaHorizontal():
    #Variables Globales
    global FilaILH
    global ColumnaILH
    global NEspaciosLH
    global MatrizElegidaLH
    global MatrizElegidaLHReal
    global MatrizOrtogonal1
    global MatrizOrtogonal2
    global MatrizOrtogonalR
    MatrizOrtogonalR = Matriz()

    #Obtener Valores Ingresados
    FilaILH = txtbxFilaILH.get()
    ColumnaILH = txtbxColumnaILH.get()
    NEspaciosLH = txtbxNEspaciosLH.get()

    #Cual Matriz?
    if MatrizElegidaLH.get() == "1":
        MatrizElegidaLHReal = '1'
        tk.messagebox.showinfo(title="Info", message="Eligió la Matriz 1")

        # Pasar Matriz Original a la Resultado
        for i in range(1, int(FilasMO1) + 1):
            for j in range(1, int(ColumnasMO1) + 1):
                if MatrizOrtogonal1.MostrarNodoMatriz(i, j) == None:
                    print("Celda " + str(i) + ", " + str(j) + " Vacia")
                elif MatrizOrtogonal1.MostrarNodoMatriz(i, j).valor == "*":
                    MatrizOrtogonalR.Insertar(i, j, "*")

        # Agregar Linea a la Matriz Resultado:
        for j in range(int(ColumnaILH), (int(ColumnaILH) + int(NEspaciosLH))):

            if MatrizOrtogonalR.MostrarNodoMatriz(int(FilaILH), j) == None:
                MatrizOrtogonalR.Insertar(int(FilaILH), j, "*")

            elif MatrizOrtogonalR.MostrarNodoMatriz(int(FilaILH), j).valor == "*":
                print("Celda " + str(i) + ", " + str(j) + " Llena")

        # Graficar La Matriz en GraphViz:
        print('')
        print('Generando la Imagen en Graphviz...')
        CodigoGraphViz = "graph G {\n subgraph tabla {\n node [shape = square]\n" + "M_Res" + "[label=<\n<TABLE border = \"1\">\n"

        # Fila Titulos Columnas:

        # Nombre Matriz
        CodigoGraphViz = CodigoGraphViz + '<TR>\n<TD border = \"1\">' + "M_Res" + "</TD>\n"

        # Numeros Columnas
        for i in range(1, int(FilasMO1) + 1):
            CodigoGraphViz = CodigoGraphViz + '<TD border = \"1\">' + str(i) + "</TD>\n"

        # Cerrar Filas
        CodigoGraphViz = CodigoGraphViz + "</TR>\n"

        # Crear Matriz Vacia:
        for i in range(1, int(FilasMO1) + 1):
            CodigoGraphViz = CodigoGraphViz + "<TR>\n"

            # Numero Fila
            CodigoGraphViz = CodigoGraphViz + "<TD border=\"1\">" + str(i) + "</TD>\n"

            for j in range(1, int(ColumnasMO1) + 1):
                if MatrizOrtogonalR.MostrarNodoMatriz(i, j) == None:
                    CodigoGraphViz = CodigoGraphViz + "<TD border=\"1\"> </TD>\n"
                elif MatrizOrtogonalR.MostrarNodoMatriz(i, j).valor == "*":
                    CodigoGraphViz = CodigoGraphViz + "<TD border=\"1\">*</TD>\n"

            # Cerrar Fila
            CodigoGraphViz = CodigoGraphViz + "</TR>\n"

        # Cerrar Tabla
        CodigoGraphViz = CodigoGraphViz + "</TABLE>>];\n}"

        # Generar el archivo GraphViz
        with open("MatrizOrtogonalR.dot", 'w') as ArchivoGraphViz:
            # TerminarElStringDelCodigo
            CodigoGraphViz = CodigoGraphViz + "\n}"
            ArchivoGraphViz.write(CodigoGraphViz)

        ArchivoGraphViz.close()
        print('')
        print('Ahorita pongo la imagen en Matriz Ortogonal R ;v')
        os.system("dot -Tjpg MatrizOrtogonalR.dot -o MORActual.jpg")

        # Poner Imagen en el Label
        FotoLabelR = Image.open("MORActual.jpg").resize((350, 350), Image.ANTIALIAS)
        Ventana.FotoLabelRReal = ImageTk.PhotoImage(FotoLabelR)

        MatrizR = Label(Panel, image=Ventana.FotoLabelRReal)
        MatrizR.place(x=800, y=5, height=350, width=350)

        print('')
    elif MatrizElegidaLH.get() == "2":
        print("Eligio la 2")
        MatrizElegidaLHReal = '2'
        tk.messagebox.showinfo(title="Info", message="Eligió la Matriz 2")

        #Pasar Matriz Original a la Resultado
        for i in range(1, int(FilasMO2) + 1):
            for j in range(1, int(ColumnasMO2) + 1):
                if MatrizOrtogonal2.MostrarNodoMatriz(i, j) == None:
                    print("Celda " + str(i) + ", "+ str(j) +" Vacia")
                elif MatrizOrtogonal2.MostrarNodoMatriz(i, j).valor == "*":
                    MatrizOrtogonalR.Insertar(i, j, "*")


        #Agregar Linea a la Matriz Resultado:
        for j in range(int(ColumnaILH), (int(ColumnaILH) + int(NEspaciosLH))):

            if MatrizOrtogonalR.MostrarNodoMatriz(int(FilaILH), j) == None:
                MatrizOrtogonalR.Insertar(int(FilaILH), j, "*")

            elif MatrizOrtogonalR.MostrarNodoMatriz(int(FilaILH), j).valor == "*":
                print("Celda " + str(i) + ", "+ str(j) +" Llena")

        # Graficar La Matriz en GraphViz:
        print('')
        print('Generando la Imagen en Graphviz...')
        CodigoGraphViz = "graph G {\n subgraph tabla {\n node [shape = square]\n" + "M_Res" + "[label=<\n<TABLE border = \"1\">\n"

        # Fila Titulos Columnas:

        # Nombre Matriz
        CodigoGraphViz = CodigoGraphViz + '<TR>\n<TD border = \"1\">' + "M_Res" + "</TD>\n"

        # Numeros Columnas
        for i in range(1, int(FilasMO2) + 1):
            CodigoGraphViz = CodigoGraphViz + '<TD border = \"1\">' + str(i) + "</TD>\n"

        # Cerrar Filas
        CodigoGraphViz = CodigoGraphViz + "</TR>\n"

        # Crear Matriz Vacia:
        for i in range(1, int(FilasMO2) + 1):
            CodigoGraphViz = CodigoGraphViz + "<TR>\n"

            # Numero Fila
            CodigoGraphViz = CodigoGraphViz + "<TD border=\"1\">" + str(i) + "</TD>\n"

            for j in range(1, int(ColumnasMO2) + 1):
                if MatrizOrtogonalR.MostrarNodoMatriz(i, j) == None:
                    CodigoGraphViz = CodigoGraphViz + "<TD border=\"1\"> </TD>\n"
                elif MatrizOrtogonalR.MostrarNodoMatriz(i, j).valor == "*":
                    CodigoGraphViz = CodigoGraphViz + "<TD border=\"1\">*</TD>\n"

            # Cerrar Fila
            CodigoGraphViz = CodigoGraphViz + "</TR>\n"

        # Cerrar Tabla
        CodigoGraphViz = CodigoGraphViz + "</TABLE>>];\n}"

        # Generar el archivo GraphViz
        with open("MatrizOrtogonalR.dot", 'w') as ArchivoGraphViz:
            # TerminarElStringDelCodigo
            CodigoGraphViz = CodigoGraphViz + "\n}"
            ArchivoGraphViz.write(CodigoGraphViz)

        ArchivoGraphViz.close()
        print('')
        print('Ahorita pongo la imagen en Matriz Ortogonal R ;v')
        os.system("dot -Tjpg MatrizOrtogonalR.dot -o MORActual.jpg")

        # Poner Imagen en el Label
        FotoLabelR = Image.open("MORActual.jpg").resize((350, 350), Image.ANTIALIAS)
        Ventana.FotoLabelRReal = ImageTk.PhotoImage(FotoLabelR)

        MatrizR = Label(Panel, image=Ventana.FotoLabelRReal)
        MatrizR.place(x=800, y=5, height=350, width=350)

        print('')


def VentanaInsertarLineaHorizontal():
    VentanaLH = tk.Tk()
    VentanaLH.geometry("275x200")
    VentanaLH.title("Linea Horizontal")

    LabelSaludo = Label(VentanaLH, text="Bienvenido, ingrese los datos que se le piden")
    LabelSaludo.place(x=3, y=5, height=20, width=275)

    #Fila Inicial
    lblFilaI = Label(VentanaLH, text="Fila Inicial:")
    lblFilaI.place(x=30, y=35, height=20, width=75)

    global txtbxFilaILH
    txtbxFilaILH = Entry(VentanaLH)
    txtbxFilaILH.place(x=130, y=35, height=20, width=20)

    #ColumnaInicial
    lblColumnaI = Label(VentanaLH, text="Columna Inicial:")
    lblColumnaI.place(x=30, y=65, height=20, width=85)

    global txtbxColumnaILH
    txtbxColumnaILH = Entry(VentanaLH)
    txtbxColumnaILH.place(x=130, y=65, height=20, width=20)

    #NumeroEspacios
    lblNEspacios = Label(VentanaLH, text="Numero Espacios:")
    lblNEspacios.place(x=30, y=95, height=20, width=100)

    global txtbxNEspaciosLH
    txtbxNEspaciosLH = Entry(VentanaLH)
    txtbxNEspaciosLH.place(x=130, y=95, height=20, width=20)

    #Combobox
    lblMElegida = Label(VentanaLH, text="Cual Matriz?")
    lblMElegida.place(x=5, y=125, height=20, width=100)
    n = tk.StringVar()
    global MatrizElegidaLH
    MatrizElegidaLH = ttk.Combobox(VentanaLH, width=27, textvariable=n)
    MatrizElegidaLH['values'] = ("1", "2")
    MatrizElegidaLH.place(x=90, y=125, height=20, width=100)

    # Boton Confirmacion
    btnOk = Button(VentanaLH, text="Ok", command=LineaHorizontal)
    btnOk.place(x=120, y=155, height=20, width=30)

    VentanaLH.mainloop()

#Linea Vertical
def LineaVertical():
    #Variables Globales
    global FilaILV
    global ColumnaILV
    global NEspaciosLV
    global MatrizElegidaLV
    global MatrizElegidaLVReal
    global MatrizOrtogonal1
    global MatrizOrtogonal2
    MatrizOrtogonalR = Matriz()

    #Obtener Valores Ingresados
    FilaILV = txtbxFilaILV.get()
    ColumnaILV = txtbxColumnaILV.get()
    NEspaciosLV = txtbxNEspaciosLV.get()

    #Cual Matriz?
    if MatrizElegidaLV.get() == "1":
        MatrizElegidaLVReal = '1'
        tk.messagebox.showinfo(title="Info", message="Eligió la Matriz 1")

        # Pasar Matriz Original a la Resultado
        for i in range(1, int(FilasMO1) + 1):
            for j in range(1, int(ColumnasMO1) + 1):
                if MatrizOrtogonal1.MostrarNodoMatriz(i, j) == None:
                    print("Celda " + str(i) + ", " + str(j) + " Vacia")
                elif MatrizOrtogonal1.MostrarNodoMatriz(i, j).valor == "*":
                    MatrizOrtogonalR.Insertar(i, j, "*")

        # Agregar Linea a la Matriz Resultado:
        for i in range(int(FilaILV), (int(FilaILV) + int(NEspaciosLV))):

            if MatrizOrtogonalR.MostrarNodoMatriz(int(FilaILV), j) == None:
                MatrizOrtogonalR.Insertar(i, int(ColumnaILV), "*")

            elif MatrizOrtogonalR.MostrarNodoMatriz(int(FilaILV), j).valor == "*":
                print("Celda " + str(i) + ", " + str(j) + " Llena")

        # Graficar La Matriz en GraphViz:
        print('')
        print('Generando la Imagen en Graphviz...')
        CodigoGraphViz = "graph G {\n subgraph tabla {\n node [shape = square]\n" + "M_Res" + "[label=<\n<TABLE border = \"1\">\n"

        # Fila Titulos Columnas:

        # Nombre Matriz
        CodigoGraphViz = CodigoGraphViz + '<TR>\n<TD border = \"1\">' + "M_Res" + "</TD>\n"

        # Numeros Columnas
        for i in range(1, int(FilasMO1) + 1):
            CodigoGraphViz = CodigoGraphViz + '<TD border = \"1\">' + str(i) + "</TD>\n"

        # Cerrar Filas
        CodigoGraphViz = CodigoGraphViz + "</TR>\n"

        # Crear Matriz Vacia:
        for i in range(1, int(FilasMO1) + 1):
            CodigoGraphViz = CodigoGraphViz + "<TR>\n"

            # Numero Fila
            CodigoGraphViz = CodigoGraphViz + "<TD border=\"1\">" + str(i) + "</TD>\n"

            for j in range(1, int(ColumnasMO1) + 1):
                if MatrizOrtogonalR.MostrarNodoMatriz(i, j) == None:
                    CodigoGraphViz = CodigoGraphViz + "<TD border=\"1\"> </TD>\n"
                elif MatrizOrtogonalR.MostrarNodoMatriz(i, j).valor == "*":
                    CodigoGraphViz = CodigoGraphViz + "<TD border=\"1\">*</TD>\n"

            # Cerrar Fila
            CodigoGraphViz = CodigoGraphViz + "</TR>\n"

        # Cerrar Tabla
        CodigoGraphViz = CodigoGraphViz + "</TABLE>>];\n}"

        # Generar el archivo GraphViz
        with open("MatrizOrtogonalR.dot", 'w') as ArchivoGraphViz:
            # TerminarElStringDelCodigo
            CodigoGraphViz = CodigoGraphViz + "\n}"
            ArchivoGraphViz.write(CodigoGraphViz)

        ArchivoGraphViz.close()
        print('')
        print('Ahorita pongo la imagen en Matriz Ortogonal R ;v')
        os.system("dot -Tjpg MatrizOrtogonalR.dot -o MORActual.jpg")

        # Poner Imagen en el Label
        FotoLabelR = Image.open("MORActual.jpg").resize((350, 350), Image.ANTIALIAS)
        Ventana.FotoLabelRReal = ImageTk.PhotoImage(FotoLabelR)

        MatrizR = Label(Panel, image=Ventana.FotoLabelRReal)
        MatrizR.place(x=800, y=5, height=350, width=350)

        print('')
    elif MatrizElegidaLV.get() == "2":
        print("Eligio la 2")
        MatrizElegidaLVReal = '2'
        tk.messagebox.showinfo(title="Info", message="Eligió la Matriz 2")

        #Pasar Matriz Original a la Resultado
        for i in range(1, int(FilasMO2) + 1):
            for j in range(1, int(ColumnasMO2) + 1):
                if MatrizOrtogonal2.MostrarNodoMatriz(i, j) == None:
                    print("Celda " + str(i) + ", "+ str(j) +" Vacia")
                elif MatrizOrtogonal2.MostrarNodoMatriz(i, j).valor == "*":
                    MatrizOrtogonalR.Insertar(i, j, "*")

        # Agregar Linea a la Matriz Resultado:
        for i in range(int(FilaILV), (int(FilaILV) + int(NEspaciosLV))):

            if MatrizOrtogonalR.MostrarNodoMatriz(int(FilaILV), j) == None:
                MatrizOrtogonalR.Insertar(i, int(ColumnaILV), "*")

            elif MatrizOrtogonalR.MostrarNodoMatriz(int(FilaILV), j).valor == "*":
                print("Celda " + str(i) + ", " + str(j) + " Llena")

        # Graficar La Matriz en GraphViz:
        print('')
        print('Generando la Imagen en Graphviz...')
        CodigoGraphViz = "graph G {\n subgraph tabla {\n node [shape = square]\n" + "M_Res" + "[label=<\n<TABLE border = \"1\">\n"

        # Fila Titulos Columnas:

        # Nombre Matriz
        CodigoGraphViz = CodigoGraphViz + '<TR>\n<TD border = \"1\">' + "M_Res" + "</TD>\n"

        # Numeros Columnas
        for i in range(1, int(FilasMO2) + 1):
            CodigoGraphViz = CodigoGraphViz + '<TD border = \"1\">' + str(i) + "</TD>\n"

        # Cerrar Filas
        CodigoGraphViz = CodigoGraphViz + "</TR>\n"

        # Crear Matriz Vacia:
        for i in range(1, int(FilasMO2) + 1):
            CodigoGraphViz = CodigoGraphViz + "<TR>\n"

            # Numero Fila
            CodigoGraphViz = CodigoGraphViz + "<TD border=\"1\">" + str(i) + "</TD>\n"

            for j in range(1, int(ColumnasMO2) + 1):
                if MatrizOrtogonalR.MostrarNodoMatriz(i, j) == None:
                    CodigoGraphViz = CodigoGraphViz + "<TD border=\"1\"> </TD>\n"
                elif MatrizOrtogonalR.MostrarNodoMatriz(i, j).valor == "*":
                    CodigoGraphViz = CodigoGraphViz + "<TD border=\"1\">*</TD>\n"

            # Cerrar Fila
            CodigoGraphViz = CodigoGraphViz + "</TR>\n"

        # Cerrar Tabla
        CodigoGraphViz = CodigoGraphViz + "</TABLE>>];\n}"

        # Generar el archivo GraphViz
        with open("MatrizOrtogonalR.dot", 'w') as ArchivoGraphViz:
            # TerminarElStringDelCodigo
            CodigoGraphViz = CodigoGraphViz + "\n}"
            ArchivoGraphViz.write(CodigoGraphViz)

        ArchivoGraphViz.close()
        print('')
        print('Ahorita pongo la imagen en Matriz Ortogonal R ;v')
        os.system("dot -Tjpg MatrizOrtogonalR.dot -o MORActual.jpg")

        # Poner Imagen en el Label
        FotoLabelR = Image.open("MORActual.jpg").resize((350, 350), Image.ANTIALIAS)
        Ventana.FotoLabelRReal = ImageTk.PhotoImage(FotoLabelR)

        MatrizR = Label(Panel, image=Ventana.FotoLabelRReal)
        MatrizR.place(x=800, y=5, height=350, width=350)

        print('')


def VentanaInsertarLineaVertical():
    VentanaLV = tk.Tk()
    VentanaLV.geometry("275x200")
    VentanaLV.title("Linea Vertical")

    LabelSaludo = Label(VentanaLV, text="Bienvenido, ingrese los datos que se le piden")
    LabelSaludo.place(x=3, y=5, height=20, width=275)

    #Fila Inicial
    lblFilaI = Label(VentanaLV, text="Fila Inicial:")
    lblFilaI.place(x=30, y=35, height=20, width=75)

    global txtbxFilaILV
    txtbxFilaILV = Entry(VentanaLV)
    txtbxFilaILV.place(x=130, y=35, height=20, width=20)

    #ColumnaInicial
    lblColumnaI = Label(VentanaLV, text="Columna Inicial:")
    lblColumnaI.place(x=30, y=65, height=20, width=85)

    global txtbxColumnaILV
    txtbxColumnaILV = Entry(VentanaLV)
    txtbxColumnaILV.place(x=130, y=65, height=20, width=20)

    #NumeroEspacios
    lblNEspacios = Label(VentanaLV, text="Numero Espacios:")
    lblNEspacios.place(x=30, y=95, height=20, width=100)

    global txtbxNEspaciosLV
    txtbxNEspaciosLV = Entry(VentanaLV)
    txtbxNEspaciosLV.place(x=130, y=95, height=20, width=20)

    #Combobox
    lblMElegida = Label(VentanaLV, text="Cual Matriz?")
    lblMElegida.place(x=5, y=125, height=20, width=100)
    n = tk.StringVar()
    global MatrizElegidaLV
    MatrizElegidaLV = ttk.Combobox(VentanaLV, width=27, textvariable=n)
    MatrizElegidaLV['values'] = ("1", "2")
    MatrizElegidaLV.place(x=90, y=125, height=20, width=100)

    # Boton Confirmacion
    btnOk = Button(VentanaLV, text="Ok", command=LineaVertical)
    btnOk.place(x=120, y=155, height=20, width=30)

    VentanaLV.mainloop()

#Rectangulo
def Rectangulo():
    #Variables Globales
    global FilaIR
    global ColumnaIR
    global NEspaciosXR
    global NEspaciosYR
    global MatrizElegidaR
    global MatrizElegidaRReal
    global MatrizOrtogonal1
    global MatrizOrtogonal2
    MatrizOrtogonalR = Matriz()

    #Obtener Valores Ingresados
    FilaIR = txtbxFilaIR.get()
    ColumnaIR = txtbxColumnaIR.get()
    NEspaciosXR = txtbxNEspaciosXR.get()
    NEspaciosYR = txtbxNEspaciosYR.get()

    #Cual Matriz?
    if MatrizElegidaR.get() == "1":
        MatrizElegidaRReal = '1'
        tk.messagebox.showinfo(title="Info", message="Eligió la Matriz 1")

        # Pasar Matriz Original a la Resultado
        for i in range(1, int(FilasMO1) + 1):
            for j in range(1, int(ColumnasMO1) + 1):
                if MatrizOrtogonal1.MostrarNodoMatriz(i, j) == None:
                    print("Celda " + str(i) + ", " + str(j) + " Vacia")
                elif MatrizOrtogonal1.MostrarNodoMatriz(i, j).valor == "*":
                    MatrizOrtogonalR.Insertar(i, j, "*")

        # InsertarRectangulo
        #DibujarFilaArriba
        for j1 in range(int(ColumnaIR), (int(ColumnaIR) + int(NEspaciosXR))):
            if MatrizOrtogonalR.MostrarNodoMatriz(int(FilaIR), j1) == None:
                MatrizOrtogonalR.Insertar(int(FilaIR), j1, "*")

            elif MatrizOrtogonalR.MostrarNodoMatriz(int(FilaIR), j1).valor == "*":
                print("Celda " + str(FilaIR) + ", " + str(j1) + " Llena")

        #DibujarColumnaIzquierda
        for i1 in range(int(FilaIR), (int(FilaIR) + int(NEspaciosYR))):
            if MatrizOrtogonalR.MostrarNodoMatriz(i1, int(ColumnaIR)) == None:
                MatrizOrtogonalR.Insertar(i1, int(ColumnaIR), "*")
            elif MatrizOrtogonalR.MostrarNodoMatriz(i1, int(ColumnaIR)).valor == "*":
                print("Celda " + str(i1) + ", " + str(ColumnaIR) + " Llena")

        #DeterminarColumnaDerecha
        ColumnaDerecha = int(ColumnaIR) + int(NEspaciosXR) - 1

        # DeterminarColumnaDerecha
        FilaAbajo = int(FilaIR) + int(NEspaciosYR) - 1

        # DibujarFilaAbajo
        for j2 in range(int(ColumnaIR), ColumnaDerecha + 1):
            if MatrizOrtogonalR.MostrarNodoMatriz(int(FilaAbajo), j2) == None:
                MatrizOrtogonalR.Insertar(int(FilaAbajo), j2, "*")

            elif MatrizOrtogonalR.MostrarNodoMatriz(int(FilaAbajo), j2).valor == "*":
                print("Celda " + str(FilaAbajo) + ", " + str(j2) + " Llena")

        # DibujarColumnaDerecha
        for i2 in range(int(FilaIR), FilaAbajo + 1):
            if MatrizOrtogonalR.MostrarNodoMatriz(i2, ColumnaDerecha) == None:
                MatrizOrtogonalR.Insertar(i2, int(ColumnaDerecha), "*")

            elif MatrizOrtogonalR.MostrarNodoMatriz(i2, ColumnaDerecha).valor == "*":
                print("Celda " + str(i2) + ", " + str(ColumnaDerecha) + " Llena")

        # Graficar La Matriz en GraphViz:
        print('')
        print('Generando la Imagen en Graphviz...')
        CodigoGraphViz = "graph G {\n subgraph tabla {\n node [shape = square]\n" + "M_Res" + "[label=<\n<TABLE border = \"1\">\n"

        # Fila Titulos Columnas:

        # Nombre Matriz
        CodigoGraphViz = CodigoGraphViz + '<TR>\n<TD border = \"1\">' + "M_Res" + "</TD>\n"

        # Numeros Columnas
        for i in range(1, int(FilasMO1) + 1):
            CodigoGraphViz = CodigoGraphViz + '<TD border = \"1\">' + str(i) + "</TD>\n"

        # Cerrar Filas
        CodigoGraphViz = CodigoGraphViz + "</TR>\n"

        # Crear Matriz Vacia:
        for i in range(1, int(FilasMO1) + 1):
            CodigoGraphViz = CodigoGraphViz + "<TR>\n"

            # Numero Fila
            CodigoGraphViz = CodigoGraphViz + "<TD border=\"1\">" + str(i) + "</TD>\n"

            for j in range(1, int(ColumnasMO1) + 1):
                if MatrizOrtogonalR.MostrarNodoMatriz(i, j) == None:
                    CodigoGraphViz = CodigoGraphViz + "<TD border=\"1\"> </TD>\n"
                elif MatrizOrtogonalR.MostrarNodoMatriz(i, j).valor == "*":
                    CodigoGraphViz = CodigoGraphViz + "<TD border=\"1\">*</TD>\n"

            # Cerrar Fila
            CodigoGraphViz = CodigoGraphViz + "</TR>\n"

        # Cerrar Tabla
        CodigoGraphViz = CodigoGraphViz + "</TABLE>>];\n}"

        # Generar el archivo GraphViz
        with open("MatrizOrtogonalR.dot", 'w') as ArchivoGraphViz:
            # TerminarElStringDelCodigo
            CodigoGraphViz = CodigoGraphViz + "\n}"
            ArchivoGraphViz.write(CodigoGraphViz)

        ArchivoGraphViz.close()
        print('')
        print('Ahorita pongo la imagen en Matriz Ortogonal R ;v')
        os.system("dot -Tjpg MatrizOrtogonalR.dot -o MORActual.jpg")

        # Poner Imagen en el Label
        FotoLabelR = Image.open("MORActual.jpg").resize((350, 350), Image.ANTIALIAS)
        Ventana.FotoLabelRReal = ImageTk.PhotoImage(FotoLabelR)

        MatrizR = Label(Panel, image=Ventana.FotoLabelRReal)
        MatrizR.place(x=800, y=5, height=350, width=350)

        print('')
    elif MatrizElegidaR.get() == "2":
        print("Eligio la 2")
        MatrizElegidaRReal = '2'
        tk.messagebox.showinfo(title="Info", message="Eligió la Matriz 2")

        #Pasar Matriz Original a la Resultado
        for i in range(1, int(FilasMO2) + 1):
            for j in range(1, int(ColumnasMO2) + 1):
                if MatrizOrtogonal2.MostrarNodoMatriz(i, j) == None:
                    print("Celda " + str(i) + ", "+ str(j) +" Vacia")
                elif MatrizOrtogonal2.MostrarNodoMatriz(i, j).valor == "*":
                    MatrizOrtogonalR.Insertar(i, j, "*")

        # InsertarRectangulo
        # DibujarFilaArriba
        for j1 in range(int(ColumnaIR), (int(ColumnaIR) + int(NEspaciosXR))):
            if MatrizOrtogonalR.MostrarNodoMatriz(int(FilaIR), j1) == None:
                MatrizOrtogonalR.Insertar(int(FilaIR), j1, "*")

            elif MatrizOrtogonalR.MostrarNodoMatriz(int(FilaIR), j1).valor == "*":
                print("Celda " + str(FilaIR) + ", " + str(j1) + " Llena")

            # DibujarColumnaIzquierda
        for i1 in range(int(FilaIR), (int(FilaIR) + int(NEspaciosYR))):
            if MatrizOrtogonalR.MostrarNodoMatriz(i1, int(ColumnaIR)) == None:
                MatrizOrtogonalR.Insertar(i1, int(ColumnaIR), "*")
            elif MatrizOrtogonalR.MostrarNodoMatriz(i1, int(ColumnaIR)).valor == "*":
                print("Celda " + str(i1) + ", " + str(ColumnaIR) + " Llena")

            # DeterminarColumnaDerecha
        ColumnaDerecha = int(ColumnaIR) + int(NEspaciosXR) - 1

        # DeterminarColumnaDerecha
        FilaAbajo = int(FilaIR) + int(NEspaciosYR) - 1

        # DibujarFilaAbajo
        for j2 in range(int(ColumnaIR), ColumnaDerecha + 1):
            if MatrizOrtogonalR.MostrarNodoMatriz(int(FilaAbajo), j2) == None:
                MatrizOrtogonalR.Insertar(int(FilaAbajo), j2, "*")

            elif MatrizOrtogonalR.MostrarNodoMatriz(int(FilaAbajo), j2).valor == "*":
                print("Celda " + str(FilaAbajo) + ", " + str(j2) + " Llena")

        # DibujarColumnaDerecha
        for i2 in range(int(FilaIR), FilaAbajo + 1):
            if MatrizOrtogonalR.MostrarNodoMatriz(i2, ColumnaDerecha) == None:
                MatrizOrtogonalR.Insertar(i2, int(ColumnaDerecha), "*")

            elif MatrizOrtogonalR.MostrarNodoMatriz(i2, ColumnaDerecha).valor == "*":
                print("Celda " + str(i2) + ", " + str(ColumnaDerecha) + " Llena")

        # Graficar La Matriz en GraphViz:
        print('')
        print('Generando la Imagen en Graphviz...')
        CodigoGraphViz = "graph G {\n subgraph tabla {\n node [shape = square]\n" + "M_Res" + "[label=<\n<TABLE border = \"1\">\n"

        # Fila Titulos Columnas:

        # Nombre Matriz
        CodigoGraphViz = CodigoGraphViz + '<TR>\n<TD border = \"1\">' + "M_Res" + "</TD>\n"

        # Numeros Columnas
        for i in range(1, int(FilasMO2) + 1):
            CodigoGraphViz = CodigoGraphViz + '<TD border = \"1\">' + str(i) + "</TD>\n"

        # Cerrar Filas
        CodigoGraphViz = CodigoGraphViz + "</TR>\n"

        # Crear Matriz Vacia:
        for i in range(1, int(FilasMO2) + 1):
            CodigoGraphViz = CodigoGraphViz + "<TR>\n"

            # Numero Fila
            CodigoGraphViz = CodigoGraphViz + "<TD border=\"1\">" + str(i) + "</TD>\n"

            for j in range(1, int(ColumnasMO2) + 1):
                if MatrizOrtogonalR.MostrarNodoMatriz(i, j) == None:
                    CodigoGraphViz = CodigoGraphViz + "<TD border=\"1\"> </TD>\n"
                elif MatrizOrtogonalR.MostrarNodoMatriz(i, j).valor == "*":
                    CodigoGraphViz = CodigoGraphViz + "<TD border=\"1\">*</TD>\n"

            # Cerrar Fila
            CodigoGraphViz = CodigoGraphViz + "</TR>\n"

        # Cerrar Tabla
        CodigoGraphViz = CodigoGraphViz + "</TABLE>>];\n}"

        # Generar el archivo GraphViz
        with open("MatrizOrtogonalR.dot", 'w') as ArchivoGraphViz:
            # TerminarElStringDelCodigo
            CodigoGraphViz = CodigoGraphViz + "\n}"
            ArchivoGraphViz.write(CodigoGraphViz)

        ArchivoGraphViz.close()
        print('')
        print('Ahorita pongo la imagen en Matriz Ortogonal R ;v')
        os.system("dot -Tjpg MatrizOrtogonalR.dot -o MORActual.jpg")

        # Poner Imagen en el Label
        FotoLabelR = Image.open("MORActual.jpg").resize((350, 350), Image.ANTIALIAS)
        Ventana.FotoLabelRReal = ImageTk.PhotoImage(FotoLabelR)

        MatrizR = Label(Panel, image=Ventana.FotoLabelRReal)
        MatrizR.place(x=800, y=5, height=350, width=350)

        print('')


def VentanaInsertarReactangulo():
    VentanaR = tk.Tk()
    VentanaR.geometry("275x225")
    VentanaR.title("Rectangulo")

    LabelSaludo = Label(VentanaR, text="Bienvenido, ingrese los datos que se le piden")
    LabelSaludo.place(x=3, y=5, height=20, width=275)

    #Fila Inicial
    lblFilaI = Label(VentanaR, text="Fila Inicial:")
    lblFilaI.place(x=30, y=35, height=20, width=75)

    global txtbxFilaIR
    txtbxFilaIR = Entry(VentanaR)
    txtbxFilaIR.place(x=130, y=35, height=20, width=20)

    #ColumnaInicial
    lblColumnaI = Label(VentanaR, text="Columna Inicial:")
    lblColumnaI.place(x=30, y=65, height=20, width=85)

    global txtbxColumnaIR
    txtbxColumnaIR = Entry(VentanaR)
    txtbxColumnaIR.place(x=130, y=65, height=20, width=20)

    #NumeroEspacios
    lblNEspaciosXR = Label(VentanaR, text="Numero X:")
    lblNEspaciosXR.place(x=30, y=95, height=20, width=100)

    global txtbxNEspaciosXR
    txtbxNEspaciosXR = Entry(VentanaR)
    txtbxNEspaciosXR.place(x=130, y=125, height=20, width=20)

    # NumeroEspacios
    lblNEspaciosYR = Label(VentanaR, text="Numero Y:")
    lblNEspaciosYR.place(x=30, y=125, height=20, width=100)

    global txtbxNEspaciosYR
    txtbxNEspaciosYR = Entry(VentanaR)
    txtbxNEspaciosYR.place(x=130, y=95, height=20, width=20)

    #Combobox
    lblMElegida = Label(VentanaR, text="Cual Matriz?")
    lblMElegida.place(x=5, y=155, height=20, width=100)
    n = tk.StringVar()
    global MatrizElegidaR
    MatrizElegidaR = ttk.Combobox(VentanaR, width=27, textvariable=n)
    MatrizElegidaR['values'] = ("1", "2")
    MatrizElegidaR.place(x=90, y=155, height=20, width=100)

    # Boton Confirmacion
    btnOk = Button(VentanaR, text="Ok", command=Rectangulo)
    btnOk.place(x=120, y=185, height=20, width=30)

    VentanaR.mainloop()

#Triangulo
def Triangulo():
    # Variables Globales
    global FilaIT
    global ColumnaIT
    global NEspaciosIT
    global MatrizElegidaIT
    global MatrizElegidaITReal
    global MatrizOrtogonal1
    global MatrizOrtogonal2
    MatrizOrtogonalR = Matriz()

    # Obtener Valores Ingresados
    FilaIT = txtbxFilaIT.get()
    ColumnaIT = txtbxColumnaIT.get()
    NEspaciosIT = txtbxNEspaciosIT.get()

    # Cual Matriz?
    if MatrizElegidaIT.get() == "1":
        MatrizElegidaITReal = '1'
        tk.messagebox.showinfo(title="Info", message="Eligió la Matriz 1")

        # Pasar Matriz Original a la Resultado
        for i in range(1, int(FilasMO1) + 1):
            for j in range(1, int(ColumnasMO1) + 1):
                if MatrizOrtogonal1.MostrarNodoMatriz(i, j) == None:
                    print("Celda " + str(i) + ", " + str(j) + " Vacia")
                elif MatrizOrtogonal1.MostrarNodoMatriz(i, j).valor == "*":
                    MatrizOrtogonalR.Insertar(i, j, "*")

        # InsertarTriangulo
        # DibujarColumnaIzquierda
        for i1 in range(int(FilaIT), (int(FilaIT) + int(NEspaciosIT))):
            if MatrizOrtogonalR.MostrarNodoMatriz(i1, int(ColumnaIT)) == None:
                MatrizOrtogonalR.Insertar(i1, int(ColumnaIT), "*")
            elif MatrizOrtogonalR.MostrarNodoMatriz(i1, int(ColumnaIT)).valor == "*":
                print("Celda " + str(i1) + ", " + str(ColumnaIT) + " Llena")

        # DeterminarFilaAbajo
        FilaAbajo = int(FilaIT) + int(NEspaciosIT) - 1

        # DibujarFilaAbajo
        for j2 in range(int(ColumnaIT), (int(ColumnaIT) + int(NEspaciosIT))):
            if MatrizOrtogonalR.MostrarNodoMatriz(int(FilaAbajo), j2) == None:
                MatrizOrtogonalR.Insertar(int(FilaAbajo), j2, "*")

            elif MatrizOrtogonalR.MostrarNodoMatriz(int(FilaAbajo), j2).valor == "*":
                print("Celda " + str(FilaAbajo) + ", " + str(j2) + " Llena")

        # DibujarDiagonal
        FilaActualD = FilaIT
        ColumnaActualD = ColumnaIT
        while FilaActualD != FilaAbajo:
            if MatrizOrtogonalR.MostrarNodoMatriz(int(FilaActualD), int(ColumnaActualD)) == None:
                MatrizOrtogonalR.Insertar(int(FilaActualD), int(ColumnaActualD), "*")
            elif MatrizOrtogonalR.MostrarNodoMatriz(int(FilaActualD), int(ColumnaActualD)).valor == "*":
                print("Celda " + str(i1) + ", " + str(ColumnaIT) + " Llena")
            FilaActualD = int(FilaActualD) + 1
            ColumnaActualD = int(ColumnaActualD) + 1

        # Graficar La Matriz en GraphViz:
        print('')
        print('Generando la Imagen en Graphviz...')
        CodigoGraphViz = "graph G {\n subgraph tabla {\n node [shape = square]\n" + "M_Res" + "[label=<\n<TABLE border = \"1\">\n"

        # Fila Titulos Columnas:

        # Nombre Matriz
        CodigoGraphViz = CodigoGraphViz + '<TR>\n<TD border = \"1\">' + "M_Res" + "</TD>\n"

        # Numeros Columnas
        for i in range(1, int(FilasMO1) + 1):
            CodigoGraphViz = CodigoGraphViz + '<TD border = \"1\">' + str(i) + "</TD>\n"

        # Cerrar Filas
        CodigoGraphViz = CodigoGraphViz + "</TR>\n"

        # Crear Matriz Vacia:
        for i in range(1, int(FilasMO1) + 1):
            CodigoGraphViz = CodigoGraphViz + "<TR>\n"

            # Numero Fila
            CodigoGraphViz = CodigoGraphViz + "<TD border=\"1\">" + str(i) + "</TD>\n"

            for j in range(1, int(ColumnasMO1) + 1):
                if MatrizOrtogonalR.MostrarNodoMatriz(i, j) == None:
                    CodigoGraphViz = CodigoGraphViz + "<TD border=\"1\"> </TD>\n"
                elif MatrizOrtogonalR.MostrarNodoMatriz(i, j).valor == "*":
                    CodigoGraphViz = CodigoGraphViz + "<TD border=\"1\">*</TD>\n"

            # Cerrar Fila
            CodigoGraphViz = CodigoGraphViz + "</TR>\n"

        # Cerrar Tabla
        CodigoGraphViz = CodigoGraphViz + "</TABLE>>];\n}"

        # Generar el archivo GraphViz
        with open("MatrizOrtogonalR.dot", 'w') as ArchivoGraphViz:
            # TerminarElStringDelCodigo
            CodigoGraphViz = CodigoGraphViz + "\n}"
            ArchivoGraphViz.write(CodigoGraphViz)

        ArchivoGraphViz.close()
        print('')
        print('Ahorita pongo la imagen en Matriz Ortogonal R ;v')
        os.system("dot -Tjpg MatrizOrtogonalR.dot -o MORActual.jpg")

        # Poner Imagen en el Label
        FotoLabelR = Image.open("MORActual.jpg").resize((350, 350), Image.ANTIALIAS)
        Ventana.FotoLabelRReal = ImageTk.PhotoImage(FotoLabelR)

        MatrizR = Label(Panel, image=Ventana.FotoLabelRReal)
        MatrizR.place(x=800, y=5, height=350, width=350)

        print('')
    elif MatrizElegidaIT.get() == "2":
        print("Eligio la 2")
        MatrizElegidaITReal = '2'
        tk.messagebox.showinfo(title="Info", message="Eligió la Matriz 2")

        # Pasar Matriz Original a la Resultado
        for i in range(1, int(FilasMO2) + 1):
            for j in range(1, int(ColumnasMO2) + 1):
                if MatrizOrtogonal2.MostrarNodoMatriz(i, j) == None:
                    print("Celda " + str(i) + ", " + str(j) + " Vacia")
                elif MatrizOrtogonal2.MostrarNodoMatriz(i, j).valor == "*":
                    MatrizOrtogonalR.Insertar(i, j, "*")

                # InsertarTriangulo
                # DibujarColumnaIzquierda
                for i1 in range(int(FilaIT), (int(FilaIT) + int(NEspaciosIT))):
                    if MatrizOrtogonalR.MostrarNodoMatriz(i1, int(ColumnaIT)) == None:
                        MatrizOrtogonalR.Insertar(i1, int(ColumnaIT), "*")
                    elif MatrizOrtogonalR.MostrarNodoMatriz(i1, int(ColumnaIT)).valor == "*":
                        print("Celda " + str(i1) + ", " + str(ColumnaIT) + " Llena")

                # DeterminarFilaAbajo
                FilaAbajo = int(FilaIT) + int(NEspaciosIT) - 1

                # DibujarFilaAbajo
                for j2 in range(int(ColumnaIT), (int(ColumnaIT) + int(NEspaciosIT))):
                    if MatrizOrtogonalR.MostrarNodoMatriz(int(FilaAbajo), j2) == None:
                        MatrizOrtogonalR.Insertar(int(FilaAbajo), j2, "*")

                    elif MatrizOrtogonalR.MostrarNodoMatriz(int(FilaAbajo), j2).valor == "*":
                        print("Celda " + str(FilaAbajo) + ", " + str(j2) + " Llena")

                # DibujarDiagonal
                FilaActualD = FilaIT
                ColumnaActualD = ColumnaIT
                while FilaActualD != FilaAbajo:
                    if MatrizOrtogonalR.MostrarNodoMatriz(int(FilaActualD), int(ColumnaActualD)) == None:
                        MatrizOrtogonalR.Insertar(int(FilaActualD), int(ColumnaActualD), "*")
                    elif MatrizOrtogonalR.MostrarNodoMatriz(int(FilaActualD), int(ColumnaActualD)).valor == "*":
                        print("Celda " + str(i1) + ", " + str(ColumnaIT) + " Llena")
                    FilaActualD = int(FilaActualD) + 1
                    ColumnaActualD = int(ColumnaActualD) + 1

        # Graficar La Matriz en GraphViz:
        print('')
        print('Generando la Imagen en Graphviz...')
        CodigoGraphViz = "graph G {\n subgraph tabla {\n node [shape = square]\n" + "M_Res" + "[label=<\n<TABLE border = \"1\">\n"

        # Fila Titulos Columnas:

        # Nombre Matriz
        CodigoGraphViz = CodigoGraphViz + '<TR>\n<TD border = \"1\">' + "M_Res" + "</TD>\n"

        # Numeros Columnas
        for i in range(1, int(FilasMO2) + 1):
            CodigoGraphViz = CodigoGraphViz + '<TD border = \"1\">' + str(i) + "</TD>\n"

        # Cerrar Filas
        CodigoGraphViz = CodigoGraphViz + "</TR>\n"

        # Crear Matriz Vacia:
        for i in range(1, int(FilasMO2) + 1):
            CodigoGraphViz = CodigoGraphViz + "<TR>\n"

            # Numero Fila
            CodigoGraphViz = CodigoGraphViz + "<TD border=\"1\">" + str(i) + "</TD>\n"

            for j in range(1, int(ColumnasMO2) + 1):
                if MatrizOrtogonalR.MostrarNodoMatriz(i, j) == None:
                    CodigoGraphViz = CodigoGraphViz + "<TD border=\"1\"> </TD>\n"
                elif MatrizOrtogonalR.MostrarNodoMatriz(i, j).valor == "*":
                    CodigoGraphViz = CodigoGraphViz + "<TD border=\"1\">*</TD>\n"

            # Cerrar Fila
            CodigoGraphViz = CodigoGraphViz + "</TR>\n"

        # Cerrar Tabla
        CodigoGraphViz = CodigoGraphViz + "</TABLE>>];\n}"

        # Generar el archivo GraphViz
        with open("MatrizOrtogonalR.dot", 'w') as ArchivoGraphViz:
            # TerminarElStringDelCodigo
            CodigoGraphViz = CodigoGraphViz + "\n}"
            ArchivoGraphViz.write(CodigoGraphViz)

        ArchivoGraphViz.close()
        print('')
        print('Ahorita pongo la imagen en Matriz Ortogonal R ;v')
        os.system("dot -Tjpg MatrizOrtogonalR.dot -o MORActual.jpg")

        # Poner Imagen en el Label
        FotoLabelR = Image.open("MORActual.jpg").resize((350, 350), Image.ANTIALIAS)
        Ventana.FotoLabelRReal = ImageTk.PhotoImage(FotoLabelR)

        MatrizR = Label(Panel, image=Ventana.FotoLabelRReal)
        MatrizR.place(x=800, y=5, height=350, width=350)

        print('')

def VentanaInsertarTriangulo():
    VentanaIT = tk.Tk()
    VentanaIT.geometry("275x200")
    VentanaIT.title("Insertar Triangulo")

    LabelSaludo = Label(VentanaIT, text="Bienvenido, ingrese los datos que se le piden")
    LabelSaludo.place(x=3, y=5, height=20, width=275)

    #Fila Inicial
    lblFilaI = Label(VentanaIT, text="Fila Inicial:")
    lblFilaI.place(x=30, y=35, height=20, width=75)

    global txtbxFilaIT
    txtbxFilaIT = Entry(VentanaIT)
    txtbxFilaIT.place(x=130, y=35, height=20, width=20)

    #ColumnaInicial
    lblColumnaI = Label(VentanaIT, text="Columna Inicial:")
    lblColumnaI.place(x=30, y=65, height=20, width=85)

    global txtbxColumnaIT
    txtbxColumnaIT = Entry(VentanaIT)
    txtbxColumnaIT.place(x=130, y=65, height=20, width=20)

    #NumeroEspacios
    lblNEspacios = Label(VentanaIT, text="Numero Espacios:")
    lblNEspacios.place(x=30, y=95, height=20, width=100)

    global txtbxNEspaciosIT
    txtbxNEspaciosIT = Entry(VentanaIT)
    txtbxNEspaciosIT.place(x=130, y=95, height=20, width=20)

    #Combobox
    lblMElegida = Label(VentanaIT, text="Cual Matriz?")
    lblMElegida.place(x=5, y=125, height=20, width=100)
    n = tk.StringVar()
    global MatrizElegidaIT
    MatrizElegidaIT = ttk.Combobox(VentanaIT, width=27, textvariable=n)
    MatrizElegidaIT['values'] = ("1", "2")
    MatrizElegidaIT.place(x=90, y=125, height=20, width=100)

    # Boton Confirmacion
    btnOk = Button(VentanaIT, text="Ok", command=Triangulo)
    btnOk.place(x=120, y=155, height=20, width=30)

    VentanaIT.mainloop()

#####################################################
#METODOS OPERACIONES DOS MATRICES                   #
#####################################################

#Union
def UnionMatrices():
    global MatrizOrtogonal1
    global FilasMO1
    global ColumnasMO1
    global MatrizOrtogonal2
    global FilasMO2
    global ColumnasMO2
    MatrizOrtogonalR = Matriz()

    # Pasar Matriz 1 a la Resultado
    for i in range(1, int(FilasMO1) + 1):
        for j in range(1, int(ColumnasMO1) + 1):
            if MatrizOrtogonal1.MostrarNodoMatriz(i, j) == None:
                print("Celda " + str(i) + ", " + str(j) + " Vacia")
            elif MatrizOrtogonal1.MostrarNodoMatriz(i, j).valor == "*":
                MatrizOrtogonalR.Insertar(i, j, "*")

    # Pasar Matriz 2 a la Resultado
    for i in range(1, int(FilasMO2) + 1):
        for j in range(1, int(ColumnasMO2) + 1):
            if MatrizOrtogonal2.MostrarNodoMatriz(i, j) == None:
                print("Celda " + str(i) + ", " + str(j) + " Vacia")
            elif MatrizOrtogonal2.MostrarNodoMatriz(i, j).valor == "*":
                MatrizOrtogonalR.Insertar(i, j, "*")

    #Ver Cuales Dimensiones son más grandes:
    FilasMayor = ''
    ColumnasMayor = ''
    #Filas
    if int(FilasMO1) > int(FilasMO2):
        FilasMayor = int(FilasMO1)
    else:
        FilasMayor = int(FilasMO2)
    #Columnas
    if int(ColumnasMO1) > int(ColumnasMO2):
        ColumnasMayor = int(ColumnasMO1)
    else:
        ColumnasMayor = int(ColumnasMO2)


    # Graficar La Matriz en GraphViz:
    print('')
    print('Generando la Imagen en Graphviz...')
    CodigoGraphViz = "graph G {\n subgraph tabla {\n node [shape = square]\n" + "M_Res" + "[label=<\n<TABLE border = \"1\">\n"

    # Fila Titulos Columnas:

    # Nombre Matriz
    CodigoGraphViz = CodigoGraphViz + '<TR>\n<TD border = \"1\">' + "M_Res" + "</TD>\n"

    # Numeros Columnas
    for i in range(1, int(FilasMayor) + 1):
        CodigoGraphViz = CodigoGraphViz + '<TD border = \"1\">' + str(i) + "</TD>\n"

    # Cerrar Filas
    CodigoGraphViz = CodigoGraphViz + "</TR>\n"

    # Crear Matriz Vacia:
    for i in range(1, int(FilasMayor) + 1):
        CodigoGraphViz = CodigoGraphViz + "<TR>\n"

        # Numero Fila
        CodigoGraphViz = CodigoGraphViz + "<TD border=\"1\">" + str(i) + "</TD>\n"

        for j in range(1, int(ColumnasMayor) + 1):
            if MatrizOrtogonalR.MostrarNodoMatriz(i, j) == None:
                CodigoGraphViz = CodigoGraphViz + "<TD border=\"1\"> </TD>\n"
            elif MatrizOrtogonalR.MostrarNodoMatriz(i, j).valor == "*":
                CodigoGraphViz = CodigoGraphViz + "<TD border=\"1\">*</TD>\n"

        # Cerrar Fila
        CodigoGraphViz = CodigoGraphViz + "</TR>\n"

    # Cerrar Tabla
    CodigoGraphViz = CodigoGraphViz + "</TABLE>>];\n}"

    # Generar el archivo GraphViz
    with open("MatrizOrtogonalR.dot", 'w') as ArchivoGraphViz:
        # TerminarElStringDelCodigo
        CodigoGraphViz = CodigoGraphViz + "\n}"
        ArchivoGraphViz.write(CodigoGraphViz)

    ArchivoGraphViz.close()
    print('')
    print('Ahorita pongo la imagen en Matriz Ortogonal R ;v')
    os.system("dot -Tjpg MatrizOrtogonalR.dot -o MORActual.jpg")

    # Poner Imagen en el Label
    FotoLabelR = Image.open("MORActual.jpg").resize((350, 350), Image.ANTIALIAS)
    Ventana.FotoLabelRReal = ImageTk.PhotoImage(FotoLabelR)

    MatrizR = Label(Panel, image=Ventana.FotoLabelRReal)
    MatrizR.place(x=800, y=5, height=350, width=350)

    print('')

#Interseccion
def InterseccionMatrices():
    global MatrizOrtogonal1
    global FilasMO1
    global ColumnasMO1
    global MatrizOrtogonal2
    global FilasMO2
    global ColumnasMO2
    MatrizOrtogonalR = Matriz()

    #Ver Cuales Dimensiones son más grandes:
    FilasMayor = ''
    ColumnasMayor = ''
    #Filas
    if int(FilasMO1) > int(FilasMO2):
        FilasMayor = int(FilasMO1)
    else:
        FilasMayor = int(FilasMO2)
    #Columnas
    if int(ColumnasMO1) > int(ColumnasMO2):
        ColumnasMayor = int(ColumnasMO1)
    else:
        ColumnasMayor = int(ColumnasMO2)


    # Pasar Matriz 1 a la Resultado
    for i in range(1, int(FilasMayor) + 1):
        for j in range(1, int(ColumnasMayor) + 1):
            if MatrizOrtogonal1.MostrarNodoMatriz(i, j) == None or MatrizOrtogonal2.MostrarNodoMatriz(i, j) == None:
                print("Celda " + str(i) + ", " + str(j) + " Vacia")
            elif MatrizOrtogonal1.MostrarNodoMatriz(i, j).valor == "*" and MatrizOrtogonal2.MostrarNodoMatriz(i, j).valor == "*":
                MatrizOrtogonalR.Insertar(i, j, "*")



    # Graficar La Matriz en GraphViz:
    print('')
    print('Generando la Imagen en Graphviz...')
    CodigoGraphViz = "graph G {\n subgraph tabla {\n node [shape = square]\n" + "M_Res" + "[label=<\n<TABLE border = \"1\">\n"

    # Fila Titulos Columnas:

    # Nombre Matriz
    CodigoGraphViz = CodigoGraphViz + '<TR>\n<TD border = \"1\">' + "M_Res" + "</TD>\n"

    # Numeros Columnas
    for i in range(1, int(FilasMayor) + 1):
        CodigoGraphViz = CodigoGraphViz + '<TD border = \"1\">' + str(i) + "</TD>\n"

    # Cerrar Filas
    CodigoGraphViz = CodigoGraphViz + "</TR>\n"

    # Crear Matriz Vacia:
    for i in range(1, int(FilasMayor) + 1):
        CodigoGraphViz = CodigoGraphViz + "<TR>\n"

        # Numero Fila
        CodigoGraphViz = CodigoGraphViz + "<TD border=\"1\">" + str(i) + "</TD>\n"

        for j in range(1, int(ColumnasMayor) + 1):
            if MatrizOrtogonalR.MostrarNodoMatriz(i, j) == None:
                CodigoGraphViz = CodigoGraphViz + "<TD border=\"1\"> </TD>\n"
            elif MatrizOrtogonalR.MostrarNodoMatriz(i, j).valor == "*":
                CodigoGraphViz = CodigoGraphViz + "<TD border=\"1\">*</TD>\n"

        # Cerrar Fila
        CodigoGraphViz = CodigoGraphViz + "</TR>\n"

    # Cerrar Tabla
    CodigoGraphViz = CodigoGraphViz + "</TABLE>>];\n}"

    # Generar el archivo GraphViz
    with open("MatrizOrtogonalR.dot", 'w') as ArchivoGraphViz:
        # TerminarElStringDelCodigo
        CodigoGraphViz = CodigoGraphViz + "\n}"
        ArchivoGraphViz.write(CodigoGraphViz)

    ArchivoGraphViz.close()
    print('')
    print('Ahorita pongo la imagen en Matriz Ortogonal R ;v')
    os.system("dot -Tjpg MatrizOrtogonalR.dot -o MORActual.jpg")

    # Poner Imagen en el Label
    FotoLabelR = Image.open("MORActual.jpg").resize((350, 350), Image.ANTIALIAS)
    Ventana.FotoLabelRReal = ImageTk.PhotoImage(FotoLabelR)

    MatrizR = Label(Panel, image=Ventana.FotoLabelRReal)
    MatrizR.place(x=800, y=5, height=350, width=350)

    print('')

#####################################################
#METODOS AYUDA                                      #
#####################################################
# MostrarDatosEstudiante
def MostrarDatosEstudiante():
    tk.messagebox.showinfo(title="Datos Estudiante", message=" Nombre: Bryan Steve Montepeque Santos \n Carnet: 201700375 \n DPI: 2997077170101 \n Curso: IPC2 Sección E")

#Mostrar Documentacion
def MostrarDocumentacion():
    webbrowser.open("C:\\Users\\Bryan\\PycharmProjects\\IPC2_Proyecto2_201700375\\Assets\\Documentacion\\PROYECTO2ENSAYO.pdf")

# MenuBar
BarraMenu = Menu(Ventana)

# Menu Archivo
MenuArchivo = Menu(BarraMenu, tearoff=0)
MenuArchivo.add_command(label="Cargar Archivo", command=CargarArchivo)
MenuArchivo.add_command(label="Salir", command=Ventana.quit)
BarraMenu.add_cascade(label="Archivo", menu=MenuArchivo)

# Menu Operaciones Una Matriz
MenuOperaciones = Menu(BarraMenu, tearoff=0)
MenuOperaciones.add_command(label="Rotación Horizontal", command=VentanaRotacionHorizontal)
MenuOperaciones.add_command(label="Rotación Vertical", command=VentanaRotacionVertical)
MenuOperaciones.add_command(label="Transpuesta", command=VentanaTranspuesta)
MenuOperaciones.add_separator()
MenuOperaciones.add_command(label="Limpiar Area", command=nada)
MenuOperaciones.add_command(label="Línea Horizontal", command=VentanaInsertarLineaHorizontal)
MenuOperaciones.add_command(label="Línea Vertical", command=VentanaInsertarLineaVertical)
MenuOperaciones.add_separator()
MenuOperaciones.add_command(label="Rectángulo", command=VentanaInsertarReactangulo)
MenuOperaciones.add_command(label="Triángulo Rectángulo", command=VentanaInsertarTriangulo)
BarraMenu.add_cascade(label="Operaciones Una Matriz", menu=MenuOperaciones)

# Menu Operaciones Dos Matrices
MenuOperaciones = Menu(BarraMenu, tearoff=0)
MenuOperaciones.add_command(label="Unión", command=UnionMatrices)
MenuOperaciones.add_command(label="Intersección", command=InterseccionMatrices)
MenuOperaciones.add_command(label="Diferencia", command=nada)
MenuOperaciones.add_command(label="Diferencia Simétrica", command=nada)
BarraMenu.add_cascade(label="Operaciones Dos Matrices", menu=MenuOperaciones)

# Menu Reportes
MenuReportes = Menu(BarraMenu, tearoff=0)
MenuReportes.add_command(label="Generar Reporte", command=nada)
BarraMenu.add_cascade(label="Reportes", menu=MenuReportes)

# Menu Ayuda
MenuAyuda = Menu(BarraMenu, tearoff=0)
MenuAyuda.add_command(label="Mostrar Datos Estudiante", command=MostrarDatosEstudiante)
MenuAyuda.add_command(label="Mostrar Documentación", command=MostrarDocumentacion)
BarraMenu.add_cascade(label="Ayuda", menu=MenuAyuda)

Ventana.config(menu=BarraMenu)

# Panel Matrices
Panel = LabelFrame(Ventana, text="Matrices", bg='grey')
Panel.place(x=20, y=20, height=400, width=1170)

#Etiquetas Matrices

#Matriz 1
FotoLabel1=Image.open("Assets/Imagen Original 1.jpg").resize((350, 350),Image.ANTIALIAS)
FotoLabel1Real=ImageTk.PhotoImage(FotoLabel1)

Matriz1 = Label(Panel, image=FotoLabel1Real)
Matriz1.place(x=10, y=5, height=350, width=350)

#Matriz 2
FotoLabel2=Image.open("Assets/Imagen Original 2.jpg").resize((350, 350),Image.ANTIALIAS)
FotoLabel2Real=ImageTk.PhotoImage(FotoLabel2)

Matriz2 = Label(Panel, image=FotoLabel2Real)
Matriz2.place(x=400, y=5, height=350, width=350)

#Matriz Resultado
FotoLabelR=Image.open("Assets/Imagen Resultado.jpg").resize((350, 350),Image.ANTIALIAS)
FotoLabelRReal=ImageTk.PhotoImage(FotoLabelR)

MatrizR = Label(Panel, image=FotoLabelRReal)
MatrizR.place(x=800, y=5, height=350, width=350)

#Etiquetas Nombre Matrices
MatrizN1 = Label(Panel, text="Matriz Original 1", bg='grey')
MatrizN1.place(x=130, y=360, height=15, width=100)

MatrizN2 = Label(Panel, text="Matriz  Original 2", bg='grey')
MatrizN2.place(x=525, y=360, height=15, width=100)


MatrizNR = Label(Panel, text="Matriz Resultado", bg='grey')
MatrizNR.place(x=930, y=360, height=15, width=100)

#Etiqeutas Nombre Matrices
Ventana.mainloop()
