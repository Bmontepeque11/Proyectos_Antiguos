import sys
import os
import tkinter as tk
from tkinter import *
from tkinter import messagebox
from tkinter import filedialog
from tkinter import ttk
from xml.etree import ElementTree


#################################################
# LISTA SIMPLE TERRENOS
#################################################
class linked_list_terrenos:
    def __init__(self):
        self.head = None

    def insertar(self, idTerreno, NombreTerreno, DimensionX, DimensionY, PosIX, PosIY, PosFX, PosFY, TerrenoReal):
        if not self.head:
            self.head = nodeLST(idTerreno=idTerreno, NombreTerreno=NombreTerreno, DimensionX=DimensionX, DimensionY=DimensionY, PosIX=PosIX, PosIY=PosIY, PosFX=PosFX, PosFY=PosFY, TerrenoReal=TerrenoReal)
            return
        current = self.head
        while current.next:
            current = current.next
        current.next = nodeLST(idTerreno=idTerreno, NombreTerreno=NombreTerreno, DimensionX=DimensionX, DimensionY=DimensionY, PosIX=PosIX, PosIY=PosIY, PosFX=PosFX, PosFY=PosFY, TerrenoReal=TerrenoReal)

    def mostrar(self):
        nodeLSF = self.head
        while nodeLSF != None:
            print(nodeLST.Nombre, end="=>")
            nodeLSF = nodeLST.next

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

    def imprimir(self):
        if self.head is None:
            return
        nodeLST = self.head
        print(nodeLST.idTerreno, end=") ")
        print(nodeLST.NombreTerreno)
        while nodeLST.next != None:
            nodeLST = nodeLST.next
            print(nodeLST.idTerreno, end=") ")
            print(nodeLST.NombreTerreno)


# Nodo Lista Simple Terrenos
class nodeLST:
    def __init__(self, idTerreno=None, NombreTerreno=None, DimensionX=None, DimensionY=None, PosIX=None, PosIY=None, PosFX=None, PosFY=None, TerrenoReal=None,  next=None):
        self.idTerreno = idTerreno
        self.NombreTerreno = NombreTerreno
        self.DimensionX = DimensionX
        self.DimensionY = DimensionY
        self.PosIX = PosIX
        self.PosIY = PosIY
        self.PosFX = PosFX
        self.PosFY = PosFY
        self.TerrenoReal = TerrenoReal
        self.next = next


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
#ListaSimpleTerrenos
ListaTerrenos = linked_list_terrenos()

#CodigoArchivoSalida
CodigoArchivoSalida = ''

def CargarArchivo():
    print("Cargar Archivo: ")
    print('')

    global ListaTerrenos

    # CargarArchivo en sí
    RutaArchivo = input(
        r"Ingrese la ruta del archivo .xml que deseea cargar al programa y este le mostará los resultados: ")

    if RutaArchivo.lower().endswith(".xml"):
        Archivo = ElementTree.parse(RutaArchivo)
        print("Archivo Cargado: " + RutaArchivo)
        print("")

        Terrenos = Archivo.findall('TERRENO')
        print("Terrenos Identificados")
        print("")

        idTerreno = 0

        # Analisis del XML
        for Terreno in Terrenos:
            # Identificar los valores de cada terreno

            #Generar el Id de Terreno
            idTerreno = idTerreno + 1

            #Nombre Terreno
            NombreTerreno = Terreno.get('nombre')

            #Dimensiones del Terreno
            DimensionesTerreno = Terreno.find('DIMENSION')
            #M = X
            CantidadM = int(DimensionesTerreno.find('m').text)
            #N = Y
            CantidadN = int(DimensionesTerreno.find('n').text)

            #Posicion Inicio del Terreno
            PosicionInicio = Terreno.find('posicioninicio')
            PosicionInicioX = int(PosicionInicio.find('x').text)
            PosicionInicioY = int(PosicionInicio.find('y').text)

            #Posicion Fin del Terreno
            PosicionFin = Terreno.find('posicionfin')
            PosicionFinX = int(PosicionFin.find('x').text)
            PosicionFinY = int(PosicionFin.find('y').text)

            #Nombre Terreno
            print("El Nombre del Terreno es= " + NombreTerreno)

            #Dimensiones
            print("Dimensiones del Terreno:")
            print('')
            print("El Valor de M es= " + str(CantidadM))
            print("El Valor de N es= " + str(CantidadN))
            print('')

            #PosicionInicio
            print("Posición Inicio:")
            print('')
            print("El Valor de X es= " + str(PosicionInicioX))
            print("El Valor de Y es= " + str(PosicionInicioY))
            print('')

            #PosicionFinal
            print("Posición Final:")
            print('')
            print("El Valor de X es= " + str(PosicionFinX))
            print("El Valor de Y es= " + str(PosicionFinY))
            print('')

            #Terrreno en sí
            print("Estas son las posiciones del terreno: ")
            print('')
            Posiciones = Terreno.findall('posicion')
            TerrenoReal = Matriz()

            for Posicion in Posiciones:

                #ValorX
                FilaActual = Posicion.get('x')

                #ValorY
                ColumnaActual = Posicion.get('y')

                #Valor de Combustible
                Combustible = Posicion.text

                print("Fila: " + str(FilaActual) + ", Columna: " + str(ColumnaActual) + ", Gas: " + str(Combustible))

                #Ingresar datos al tablero
                TerrenoReal.Insertar(int(FilaActual), int(ColumnaActual), int(Combustible))

            print("Tablero Almacenado")
            print('')

            #Almacenar datos en la lista de terrenos
            ListaTerrenos.insertar(idTerreno, NombreTerreno, CantidadM, CantidadN, PosicionInicioX, PosicionInicioY, PosicionFinX, PosicionFinY, TerrenoReal)

            print("Terreno Almacenado en la lista ;v")
            print('')

    # Volver al Menú
    print('')
    input("Archivo Cargado, ingrese cualquier valor para volver al Menú Principal: ")
    Menu()

def ProcesarTerreno():
    print("Procesar Archivo: ")
    print('')

    global ListaTerrenos
    global CodigoArchivoSalida

    print('Los Terrenos Almacenados son: ')
    ListaTerrenos.imprimir()

    # Pedir una opción al usuario
    OpcionTerrenos = input("Ingrese el Nombre de un Terreno para procesarlo: ")

    # Buscar la Matriz Elegida e Iniciar el String del Archivo de Salida
    Actual = ListaTerrenos.head
    print("Buscando Terreno...")
    while not Actual.NombreTerreno == OpcionTerrenos:
        Actual = Actual.next
    if Actual.NombreTerreno == OpcionTerrenos:
        print("Terreno Encontrado!")
        print("Identificando Datos del Terreno...")
        NombreTerreno = str(Actual.NombreTerreno)
        DimensionX = int(Actual.DimensionX)
        DimensionY = int(Actual.DimensionY)

        #Agregar Etiquetas al Archivo de Salida
        #Posicion Inicio
        PosicionInicioX = int(Actual.PosIX)
        PosicionInicioY = int(Actual.PosIY)

        #Posicion Final
        PosicionFinalX = int(Actual.PosFX)
        PosicionFinalY = int(Actual.PosFY)

        #Agregar Nombre del Terreno
        CodigoArchivoSalida = CodigoArchivoSalida + "<terreno nombre=\"" + NombreTerreno + "\">\n"

        #Agregar Posicion Inicio
        CodigoArchivoSalida = CodigoArchivoSalida + "\t<posicioninicio>\n"
        CodigoArchivoSalida = CodigoArchivoSalida + "\t\t<x>" + str(PosicionInicioX) + "</x>\n"
        CodigoArchivoSalida = CodigoArchivoSalida + "\t\t<y>" + str(PosicionInicioY) + "</y>\n"
        CodigoArchivoSalida = CodigoArchivoSalida + "\t</posicioninicio>\n"

        # Agregar Posicion Final
        CodigoArchivoSalida = CodigoArchivoSalida + "\t<posicionfin>\n"
        CodigoArchivoSalida = CodigoArchivoSalida + "\t\t<x>" + str(PosicionFinalX) + "</x>\n"
        CodigoArchivoSalida = CodigoArchivoSalida + "\t\t<y>" + str(PosicionFinalY) + "</y>\n"
        CodigoArchivoSalida = CodigoArchivoSalida + "\t</posicionfin>\n"


        #Recorrer La Ruta En EL Terreno y llenar otra matriz solo de la ruta

        #CombustibleTotal
        CombustibleTotal = 0

        #Matriz Ortogonal Terreno Recorrido
        TerrenoRecorrido = Matriz()

        print("Generando Ruta...")

        #Que pasa si la Posicion Final queda Abajo?
        if PosicionInicioX < PosicionFinalX:
            for i in range(PosicionInicioX, PosicionFinalX+1):
                NodoActual = Actual.TerrenoReal.MostrarNodoMatriz(i, PosicionInicioY)

                FilaActual = NodoActual.fila
                ColumnaActual = NodoActual.columna
                Combustible = NodoActual.valor

                CombustibleTotal = CombustibleTotal + int(Combustible)

                CodigoArchivoSalida = CodigoArchivoSalida + "\t<posicion x=\"" + str(FilaActual) + "\" y=\"" + str(ColumnaActual) +"\">" + str(Combustible) + "</posicion>\n"

                TerrenoRecorrido.Insertar(FilaActual, ColumnaActual, Combustible)

            #Que pasa si Posicion Final queda a la Derecha?
            if PosicionInicioY < PosicionFinalY:
                for j in range(PosicionInicioY, PosicionFinalY+1):
                    NodoActual = Actual.TerrenoReal.MostrarNodoMatriz(FilaActual, j)

                    FilaActual = NodoActual.fila
                    ColumnaActual = NodoActual.columna
                    Combustible = NodoActual.valor

                    CombustibleTotal = CombustibleTotal + int(Combustible)

                    CodigoArchivoSalida = CodigoArchivoSalida + "\t<posicion x=\"" + str(FilaActual) + "\" y=\"" + str(ColumnaActual) + "\">" + str(Combustible) + "</posicion>\n"

                    TerrenoRecorrido.Insertar(FilaActual, ColumnaActual, Combustible)

            #Que pasa si Posicion Final queda a la Izquierda?
            elif PosicionInicioY > PosicionFinalY:
                for j in range(PosicionInicioY, PosicionFinalY-1, -1):
                    NodoActual = Actual.TerrenoReal.MostrarNodoMatriz(FilaActual, j)

                    FilaActual = NodoActual.fila
                    ColumnaActual = NodoActual.columna
                    Combustible = NodoActual.valor

                    CombustibleTotal = CombustibleTotal + int(Combustible)

                    CodigoArchivoSalida = CodigoArchivoSalida + "\t<posicion x=\"" + str(FilaActual) + "\" y=\"" + str(ColumnaActual) + "\">" + str(Combustible) + "</posicion>\n"

                    TerrenoRecorrido.Insertar(FilaActual, ColumnaActual, Combustible)

            # Que pasa si la Posicion Final queda en la misma Columna?
            elif PosicionInicioY == PosicionFinalY:
                if PosicionInicioX < PosicionFinalX:
                    for i in range(PosicionInicioX, PosicionFinalX + 1):
                        NodoActual = Actual.TerrenoReal.MostrarNodoMatriz(i, PosicionInicioY)

                        FilaActual = NodoActual.fila
                        ColumnaActual = NodoActual.columna
                        Combustible = NodoActual.valor

                        CombustibleTotal = CombustibleTotal + int(Combustible)

                        CodigoArchivoSalida = CodigoArchivoSalida + "\t<posicion x=\"" + str(FilaActual) + "\" y=\"" + str(ColumnaActual) + "\">" + str(Combustible) + "</posicion>\n"

                        TerrenoRecorrido.Insertar(FilaActual, ColumnaActual, Combustible)

                elif PosicionInicioX > PosicionFinalX:
                    for i in range(PosicionInicioX, PosicionFinalX-1, -1):
                        NodoActual = Actual.TerrenoReal.MostrarNodoMatriz(i, PosicionInicioY)

                        FilaActual = NodoActual.fila
                        ColumnaActual = NodoActual.columna
                        Combustible = NodoActual.valor

                        CombustibleTotal = CombustibleTotal + int(Combustible)

                        CodigoArchivoSalida = CodigoArchivoSalida + "\t<posicion x=\"" + str(FilaActual) + "\" y=\"" + str(ColumnaActual) + "\">" + str(Combustible) + "</posicion>\n"

                        TerrenoRecorrido.Insertar(FilaActual, ColumnaActual, Combustible)
                else:
                    NodoActual = Actual.TerrenoReal.MostrarNodoMatriz(i, PosicionInicioY)

                    FilaActual = NodoActual.fila
                    ColumnaActual = NodoActual.columna
                    Combustible = NodoActual.valor

                    CombustibleTotal = CombustibleTotal + int(Combustible)

                    CodigoArchivoSalida = CodigoArchivoSalida + "\t<posicion x=\"" + str(FilaActual) + "\" y=\"" + str(ColumnaActual) + "\">" + str(Combustible) + "</posicion>\n"

                    TerrenoRecorrido.Insertar(FilaActual, ColumnaActual, Combustible)



        # Que pasa si la Posicion Final queda Arriba?
        elif PosicionInicioX > PosicionFinalX:
            for i in range(PosicionInicioX, PosicionFinalX-1, -1):
                NodoActual = Actual.TerrenoReal.MostrarNodoMatriz(i, PosicionInicioY)

                FilaActual = NodoActual.fila
                ColumnaActual = NodoActual.columna
                Combustible = NodoActual.valor

                CombustibleTotal = CombustibleTotal + int(Combustible)

                CodigoArchivoSalida = CodigoArchivoSalida + "\t<posicion x=\"" + str(FilaActual) + "\" y=\"" + str(ColumnaActual) + "\">" + str(Combustible) + "</posicion>\n"

                TerrenoRecorrido.Insertar(FilaActual, ColumnaActual, Combustible)

            # Que pasa si Posicion Final queda a la Derecha?
            if PosicionInicioY < PosicionFinalY:
                for j in range(PosicionInicioY, PosicionFinalY+1):
                    NodoActual = Actual.TerrenoReal.MostrarNodoMatriz(FilaActual, j)

                    FilaActual = NodoActual.fila
                    ColumnaActual = NodoActual.columna
                    Combustible = NodoActual.valor

                    CombustibleTotal = CombustibleTotal + int(Combustible)

                    CodigoArchivoSalida = CodigoArchivoSalida + "\t<posicion x=\"" + str(FilaActual) + "\" y=\"" + str(ColumnaActual) + "\">" + str(Combustible) + "</posicion>\n"

                    TerrenoRecorrido.Insertar(FilaActual, ColumnaActual, Combustible)

            # Que pasa si Posicion Final queda a la Izquierda?
            elif PosicionInicioY > PosicionFinalY:
                for j in range(PosicionInicioY, PosicionFinalY-1, -1):
                    NodoActual = Actual.TerrenoReal.MostrarNodoMatriz(FilaActual, j)

                    FilaActual = NodoActual.fila
                    ColumnaActual = NodoActual.columna
                    Combustible = NodoActual.valor

                    CombustibleTotal = CombustibleTotal + int(Combustible)

                    CodigoArchivoSalida = CodigoArchivoSalida + "\t<posicion x=\"" + str(FilaActual) + "\" y=\"" + str(ColumnaActual) + "\">" + str(Combustible) + "</posicion>\n"

                    TerrenoRecorrido.Insertar(FilaActual, ColumnaActual, Combustible)

            # Que pasa si la Posicion Final queda en la misma Columna?
            elif PosicionInicioY == PosicionFinalY:
                if PosicionInicioX < PosicionFinalX:
                    for i in range(PosicionInicioX, PosicionFinalX + 1):
                        NodoActual = Actual.TerrenoReal.MostrarNodoMatriz(i, PosicionInicioY)

                        FilaActual = NodoActual.fila
                        ColumnaActual = NodoActual.columna
                        Combustible = NodoActual.valor

                        CombustibleTotal = CombustibleTotal + int(Combustible)

                        CodigoArchivoSalida = CodigoArchivoSalida + "\t<posicion x=\"" + str(FilaActual) + "\" y=\"" + str(ColumnaActual) + "\">" + str(Combustible) + "</posicion>\n"

                        TerrenoRecorrido.Insertar(FilaActual, ColumnaActual, Combustible)

                elif PosicionInicioX > PosicionFinalX:
                    for i in range(PosicionInicioX, PosicionFinalX - 1, -1):
                        NodoActual = Actual.TerrenoReal.MostrarNodoMatriz(i, PosicionInicioY)

                        FilaActual = NodoActual.fila
                        ColumnaActual = NodoActual.columna
                        Combustible = NodoActual.valor

                        CombustibleTotal = CombustibleTotal + int(Combustible)

                        CodigoArchivoSalida = CodigoArchivoSalida + "\t<posicion x=\"" + str(FilaActual) + "\" y=\"" + str(ColumnaActual) + "\">" + str(Combustible) + "</posicion>\n"

                        TerrenoRecorrido.Insertar(FilaActual, ColumnaActual, Combustible)
                else:
                    NodoActual = Actual.TerrenoReal.MostrarNodoMatriz(i, PosicionInicioY)

                    FilaActual = NodoActual.fila
                    ColumnaActual = NodoActual.columna
                    Combustible = NodoActual.valor

                    CombustibleTotal = CombustibleTotal + int(Combustible)

                    CodigoArchivoSalida = CodigoArchivoSalida + "\t<posicion x=\"" + str(FilaActual) + "\" y=\"" + str(ColumnaActual) + "\">" + str(Combustible) + "</posicion>\n"

                    TerrenoRecorrido.Insertar(FilaActual, ColumnaActual, Combustible)

        # Que pasa si la Posicion Final queda en la misma columna?
        elif PosicionInicioX == PosicionFinalX:

            NodoActual = Actual.TerrenoReal.MostrarNodoMatriz(PosicionInicioX, PosicionInicioY)

            FilaActual = NodoActual.fila
            ColumnaActual = NodoActual.columna
            Combustible = NodoActual.valor

            CombustibleTotal = CombustibleTotal + int(Combustible)

            CodigoArchivoSalida = CodigoArchivoSalida + "\t<posicion x=\"" + str(FilaActual) + "\" y=\"" + str(ColumnaActual) + "\">" + str(Combustible) + "</posicion>\n"

            TerrenoRecorrido.Insertar(FilaActual, ColumnaActual, Combustible)

            #Que pasa si Posicion Final queda a la Derecha?
            if PosicionInicioY < PosicionFinalY:
                for j in range(PosicionInicioY, PosicionFinalY+1):
                    NodoActual = Actual.TerrenoReal.MostrarNodoMatriz(FilaActual, j)

                    FilaActual = NodoActual.fila
                    ColumnaActual = NodoActual.columna
                    Combustible = NodoActual.valor

                    CombustibleTotal = CombustibleTotal + int(Combustible)

                    CodigoArchivoSalida = CodigoArchivoSalida + "\t<posicion x=\"" + str(FilaActual) + "\" y=\"" + str(ColumnaActual) + "\">" + str(Combustible) + "</posicion>\n"

                    TerrenoRecorrido.Insertar(FilaActual, ColumnaActual, Combustible)
            # Que pasa si Posicion Final queda a la Izquierda?
            elif PosicionInicioY > PosicionFinalY:
               for j in range(PosicionInicioY, PosicionFinalY-1, -1):
                   NodoActual = Actual.TerrenoReal.MostrarNodoMatriz(FilaActual, j)

                   FilaActual = NodoActual.fila
                   ColumnaActual = NodoActual.columna
                   Combustible = NodoActual.valor

                   CombustibleTotal = CombustibleTotal + int(Combustible)

                   CodigoArchivoSalida = CodigoArchivoSalida + "\t<posicion x=\"" + str(FilaActual) + "\" y=\"" + str(ColumnaActual) + "\">" + str(Combustible) + "</posicion>\n"

                   TerrenoRecorrido.Insertar(FilaActual, ColumnaActual, Combustible)
               # Que pasa si la Posicion Final queda en la misma Columna?

            else:
                NodoActual = Actual.TerrenoReal.MostrarNodoMatriz(FilaActual, PosicionInicioY)

                FilaActual = NodoActual.fila
                ColumnaActual = NodoActual.columna
                Combustible = NodoActual.valor

                CombustibleTotal = CombustibleTotal + int(Combustible)

                CodigoArchivoSalida = CodigoArchivoSalida + "\t<posicion x=\"" + str(FilaActual) + "\" y=\"" + str(ColumnaActual) + "\">" + str(Combustible) + "</posicion>\n"

                TerrenoRecorrido.Insertar(FilaActual, ColumnaActual, Combustible)


        #Graficar la Matriz Recorrida en Consola:
        for i in range(1, DimensionX + 1):
            for j in range(1, DimensionY + 1):

                if TerrenoRecorrido.MostrarNodoMatriz(i, j) == None:
                    print("|o|", end='')
                elif TerrenoRecorrido.MostrarNodoMatriz(i, j) != None:
                    print("|1|", end='')
            print('')

        #Etiquetas Finales
        print('')
        print("Proceso Completado!")
        print("Terreno Procesado: " + str(NombreTerreno))
        print("Combustible Empleado: " + str(CombustibleTotal))

        #Etiqueta Combustible en XML Salida
        CodigoArchivoSalida = CodigoArchivoSalida + "<combustible>" + str(CombustibleTotal) + "</combustible>\n"

        #Terminar el Codigo del XML
        CodigoArchivoSalida = CodigoArchivoSalida + "</terreno>"


    # Volver al Menú
    print('')
    input("Archivo Procesado, ingrese cualquier valor para volver al Menú Principal: ")
    Menu()


def EscribirArchivoSalida():
    print("Escribir Archivo Salida: ")
    print('')

    RutaSalida = input("Ingrese la Ruta en donde quiere que se genere el archivo: ")

    if not os.path.exists(RutaSalida):
        os.makedirs(RutaSalida)

    NombreArchivo = "Proyecto1AS.xml"

    # Generar El Archivo de GraphViz:
    with open(os.path.join(RutaSalida, NombreArchivo), 'w') as ArchivoSalida:
        ArchivoSalida.write(CodigoArchivoSalida)
    ArchivoSalida.close()
    print('')
    print('Archivo Generado ;v')

    # Volver al Menú
    print('')
    input("Archivo Salida Escrito, ingrese cualquier valor para volver al Menú Principal: ")
    Menu()


def MostrarDatosEstudiante():
    print("Mostrar Datos de Estudiante: ")
    print('')

    # Mostrar Los Datos
    print('Nombre: Bryan Steve Montepeque Santos')
    print('Carnet: 201700375')
    print('Curso: Introducción a la Programación y Computación 2 Sección \"E\"')
    print('Carrera: Ingeniería en Ciencias Y Sistemas')
    print('Semestre: 4to Semestre')

    # Volver al Menú
    print('')
    input("Datos de Estudiante Mostrados, ingrese cualquier valor para volver al Menú Principal: ")
    Menu()

def GenerarGrafica():
    print("Generar Gráfica")
    print('')

    global ListaTerrenos

    print('Los Terrenos Almacenados son: ')
    ListaTerrenos.imprimir()

    # Pedir una opción al usuario
    OpcionTerrenos = input("Ingrese el Nombre de un Terreno para ver su gráfica: ")

    # Buscar la Matriz Elegida e Iniciar el String del Archivo
    Actual = ListaTerrenos.head
    while not Actual.NombreTerreno == OpcionTerrenos:
        Actual = Actual.next
    if Actual.NombreTerreno == OpcionTerrenos:
        NombreTerreno = str(Actual.NombreTerreno)
        DimensionX = int(Actual.DimensionX)
        DimensionY = int(Actual.DimensionY)
        CodigoGraphViz = 'graph D {\n rankdir = TB;\n'

        #Agregar la Matriz al Archivo
        NumeroNodo = 1
        # Filas
        for Fila in range(1, DimensionX+1):
            for Columna in range(1, DimensionY+1):
                #Valores de Matriz
                NodoActual = Actual.TerrenoReal.MostrarNodoMatriz(Fila, Columna)

                CodigoGraphViz = CodigoGraphViz + '\n' + str(NumeroNodo) + '[label=\"' + str(NodoActual.valor) + '\"];\n'
                # Conectores
                if Fila<DimensionX:
                    CodigoGraphViz = CodigoGraphViz + str(NumeroNodo) + " -- " + str(NumeroNodo + DimensionY) + ";\n"
                NumeroNodo = NumeroNodo + 1


    # Generar El Archivo de GraphViz:
    with open("TerrenoGraficado.dot", 'w') as ArchivoGraphViz:
        #Agregar el Nombre del Terreno
        CodigoGraphViz = CodigoGraphViz + "\nlabelloc = \"t\";\nlabel=\"" + NombreTerreno + "\";\n"

        # TerminarElStringDelCodigo
        CodigoGraphViz = CodigoGraphViz + "\n}"

        ArchivoGraphViz.write(CodigoGraphViz)
    ArchivoGraphViz.close()
    print('')
    print('Ahorita te lo abro ;v')
    os.system("dot -Tjpg TerrenoGraficado.dot -o TerrenoGraficadoReal.jpg")
    os.system("start TerrenoGraficadoReal.jpg")

    # Volver al Menú
    print('')
    input("Gráfica Generada, ingrese cualquier valor para volver al Menú Principal: ")
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
                ProcesarTerreno()
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
