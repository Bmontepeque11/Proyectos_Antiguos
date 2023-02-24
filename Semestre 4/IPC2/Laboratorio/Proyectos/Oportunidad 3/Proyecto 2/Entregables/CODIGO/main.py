import webbrowser
import os
import re
from xml.etree import ElementTree
import tkinter as tk
from tkinter import *
from tkinter import messagebox
from tkinter import filedialog
from tkinter import ttk

#################################################
# LISTA SIMPLE LINEAS DE PRODUCCION
#################################################
class linked_list_lineas_produccion:
    def __init__(self):
        self.head = None

    def insertar(self, id, CantidadComponentes, TiempoEnsamblaje, Componentes):
        if not self.head:
            self.head = nodeLSLP(id=id, CantidadComponentes=CantidadComponentes, TiempoEnsamblaje=TiempoEnsamblaje, Componentes=Componentes)
            return
        current = self.head
        while current.next:
            current = current.next
        current.next = nodeLSLP(id=id, CantidadComponentes=CantidadComponentes, TiempoEnsamblaje=TiempoEnsamblaje, Componentes=Componentes)

    def mostrar(self):
        actual = self.head
        while actual != None:
            print(actual.id, end="=>")
            actual = actual.next

    def buscar(self, id):
        actual = self.head

        while actual != None:
            if actual.id == id:
                return actual
            actual = actual.next
        return False

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
        Actual = self.head
        print(Actual.id, end=") ")
        print(Actual.CantidadComponentes)
        while Actual.next != None:
            Actual = Actual.next
            print(Actual.id, end=") ")
            print(Actual.CantidadComponentes)


# Nodo Lista Simple Lineas Produccion
class nodeLSLP:
    def __init__(self, id=None, CantidadComponentes=None, TiempoEnsamblaje = None, Componentes=None ,next=None):
        self.id = id
        self.CantidadComponentes = CantidadComponentes
        self.TiempoEnsamblaje = TiempoEnsamblaje
        self.Componentes = Componentes
        self.next = next


#################################################
# LISTA SIMPLE COMPONENTES DE LINEAS DE PRODUCCION
#################################################
class linked_list_de_componentes_lineas_produccion:
  def __init__ (self, head=None):
    self.head = head
    self.last = head
    self.size = 0

  def insertarCabeza (self, id):
    if self.size == 0:
      self.head = nodeLSCLP(id=id)
      self.last = self.head
    else:
      new_node = nodeLSCLP(id=id, next=self.head)
      self.head.previous = new_node
      self.head = new_node
    self.size += 1

  def insertarFinal(self, id):
      if self.size == 0:
          self.head = nodeLSCLP(id=id)
      else:
          new_node = nodeLSCLP(id=id)
          temp = self.head
          while (temp.next != None):
              temp = temp.next

          temp.next = new_node
          new_node.prev = temp
      self.size += 1

  def imprimir (self):
    if self.head is None:
      return
    node = self.head
    print(node.id, end = " => ")
    while node.next:
      node = node.next
      print(node.id, end = " => ")

  def eliminar (self, id):
    node = self.head

    while node is not None:
      if node.id == id:
        if node.previous is not None:
          if node.next:
            node.previous.next = node.next
            node.next.previous = node.previous
          else:
            node.previous.next = None
            self.last = node.previous
        else:
          self.head = node.next
          node.next.previous = self.head
        self.size -= 1
        return True
      else:
        node = node.next
    return False


# Nodo Lista Simple Componentes Lineas Produccion
class nodeLSCLP:
    def __init__(self, id=None, next=None, previous=None):
        self.id = id
        self.next = next
        self.previous = previous


#################################################
# LISTA SIMPLE DE PRODUCTOS MAQUINA
#################################################
class linked_list_productos:
    def __init__(self):
        self.head = None

    def insertar(self, id, Nombre, Elaboracion):
        if not self.head:
            self.head = nodeLSP(id=id, Nombre=Nombre, Elaboracion=Elaboracion)
            return
        current = self.head
        while current.next:
            current = current.next
        current.next = nodeLSP(id=id, Nombre=Nombre, Elaboracion=Elaboracion)

    def mostrar(self):
        actual = self.head
        while actual != None:
            print(actual.id, end="=>")
            actual = actual.next

    def buscar(self, Nombre):
        actual = self.head

        while actual != None:
            if actual.Nombre == Nombre:
                return actual
            actual = actual.next
        return False

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
        Actual = self.head
        print(Actual.id, end=") ")
        print(Actual.Nombre)
        while Actual.next != None:
            Actual = Actual.next
            print(Actual.id, end=") ")
            print(Actual.Nombre)


# Nodo Lista Simple Productos
class nodeLSP:
    def __init__(self, id=None, Nombre=None, Elaboracion=None, next=None):
        self.id = id
        self.Nombre = Nombre
        self.Elaboracion = Elaboracion
        self.next = next

#################################################
# LISTA SIMPLE COLA DE SECUENCIA PASOS
#################################################
class linked_list_pasos_graphviz:
    def __init__(self):
        self.head = None

    def insertar(self, id, PasoReal, LineaProduccion, Componente):
        if not self.head:
            self.head = nodeLSPG(id=id, PasoReal=PasoReal, LineaProduccion=LineaProduccion, Componente=Componente)
            return
        current = self.head
        while current.next:
            current = current.next
        current.next = nodeLSPG(id=id, PasoReal=PasoReal, LineaProduccion=LineaProduccion, Componente=Componente)

    def mostrar(self):
        actual = self.head
        while actual != None:
            print(actual.Nombre, end="=>")
            actual = actual.next

    def buscar(self, id):
        actual = self.head

        while actual != None:
            if actual.id == id:
                return actual
            actual = actual.next
        return False

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
        Actual = self.head
        print(Actual.id, end=") ")
        print(Actual.PasoReal + ' Linea: ' + str(Actual.LineaProduccion) + ' Componente: ' + str(Actual.Componente))
        while Actual.next != None:
            Actual = Actual.next
            print(Actual.id, end=") ")
            print(Actual.PasoReal + ' Linea: ' + str(Actual.LineaProduccion) + ' Componente: ' + str(Actual.Componente))


# Nodo Lista Simple Pasos GraphViz
class nodeLSPG:
    def __init__(self, id=None, PasoReal=None, LineaProduccion=None, Componente=None, next=None):
        self.id = id
        self.PasoReal = PasoReal
        self.LineaProduccion = LineaProduccion
        self.Componente = Componente
        self.next = next


#################################################
# LISTA SIMPLE DE SIMULACIONES
#################################################
class linked_list_simulaciones:
    def __init__(self):
        self.head = None

    def insertar(self, id, Nombre, ListaProductos):
        if not self.head:
            self.head = nodeLSS(id=id, Nombre=Nombre, ListaProductos=ListaProductos)
            return
        current = self.head
        while current.next:
            current = current.next
        current.next = nodeLSS(id=id, Nombre=Nombre, ListaProductos=ListaProductos)

    def mostrar(self):
        actual = self.head
        while actual != None:
            print(actual.id, end="=>")
            actual = actual.next

    def buscar(self, Nombre):
        actual = self.head

        while actual != None:
            if actual.Nombre == Nombre:
                return actual
            actual = actual.next
        return False

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
        Actual = self.head
        print(Actual.id, end=") ")
        print(Actual.Nombre)
        while Actual.next != None:
            Actual = Actual.next
            print(Actual.id, end=") ")
            print(Actual.Nombre)


# Nodo Lista Simple Simulaciones
class nodeLSS:
    def __init__(self, id=None, Nombre=None, ListaProductos=None, next=None):
        self.id = id
        self.Nombre = Nombre
        self.ListaProductos = ListaProductos
        self.next = next


#################################################
# LISTA SIMPLE DE PRODUCTOS SIMULACIONES
#################################################
class linked_list_productos_simulaciones:
    def __init__(self):
        self.head = None

    def insertar(self, id, Nombre):
        if not self.head:
            self.head = nodeLSPS(id=id, Nombre=Nombre)
            return
        current = self.head
        while current.next:
            current = current.next
        current.next = nodeLSPS(id=id, Nombre=Nombre)

    def mostrar(self):
        actual = self.head
        while actual != None:
            print(actual.id, end="=>")
            actual = actual.next

    def buscar(self, Nombre):
        actual = self.head

        while actual != None:
            if actual.Nombre == Nombre:
                return actual
            actual = actual.next
        return False

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
        Actual = self.head
        print(Actual.id, end=") ")
        print(Actual.Nombre)
        while Actual.next != None:
            Actual = Actual.next
            print(Actual.id, end=") ")
            print(Actual.Nombre)


# Nodo Lista Simple Productos Simulaciones
class nodeLSPS:
    def __init__(self, id=None, Nombre=None, next=None):
        self.id = id
        self.Nombre = Nombre
        self.next = next

##########################################
#Variables Globales                      #
##########################################

#Datos Importantes de el Archivo de Maquina
#Cantidad de Lineas de Produccion
CantidadLineasProduccionMaquina = ''

#Lista de Lineas de Produccion
ListaLineasProduccion = linked_list_lineas_produccion()

#Lista de Productos
ListaProductosMaquina = linked_list_productos()

#Datos Importantes del Archivo de Simulacion
#Lista de Simulaciones
ListaSimulaciones = linked_list_simulaciones()
idSimulacion = 0

#Objetos GUI para usar en los Métodos
ProductoAElegir = ttk.Combobox

#####################################################
#METODOS AYUDA                                      #
#####################################################
# MostrarDatosEstudiante
def MostrarDatosEstudiante():
    tk.messagebox.showinfo(title="Datos Estudiante", message=" Nombre: Bryan Steve Montepeque Santos \n Carnet: 201700375 \n DPI: 2997077170101 \n Curso: IPC2 Sección E")

#####################################################
#CREAR FRAME INICIAL                                #
#####################################################
Ventana = tk.Tk()
Ventana.geometry("900x420")
Ventana.config(bg='#4682B4')
Ventana.title("IPC2_Proyecto2_201700375")

def nada():
    x = 0

#####################################################
#METODOS CARGAR ARCHIVO                             #
#####################################################

#Cargar Archivo Maquina
def CargarArchivoMaquina():
    filters = (("Archivos de XML", "*.xml"), ("Todos los Archivos", "*.*"))
    ArchivoAbierto = tk.filedialog.askopenfilename(filetypes=filters)
    Archivo = ElementTree.parse(ArchivoAbierto)

    #Variables Globales
    global ListaLineasProduccion
    global CantidadLineasProduccionMaquina

    #Analizar el XML

    #Titulo de Inicio
    print('')
    print('---------------------------------------------------')
    print('Inicio de Analisis del Archivo de Máquina')
    print('---------------------------------------------------')
    print('')

    #CantidadLineasProduccion
    CantidadLineasProduccion = Archivo.find('CantidadLineasProduccion').text
    print("Cantidad de Lineas de Produccion: " + str(CantidadLineasProduccion))
    print('')

    CantidadLineasProduccionMaquina = int(CantidadLineasProduccion)
    print('Cantidad de Lineas de Produccion Almacenada')
    print('')

    #ListadoLineasProduccion
    ListadoLineasProduccion = Archivo.find('ListadoLineasProduccion')

    #LineaProduccion
    LineasProduccion = ListadoLineasProduccion.findall('LineaProduccion')

    #Titulo Lista de Lineas de Produccion
    print("Lista de Lineas de Producción: ")
    print('')

    for LineaProduccion in LineasProduccion:

        #Numero de Linea de Produccion
        NumeroLineaProduccion = int(LineaProduccion.find('Numero').text)
        print("Linea de Producción Número: " + str(NumeroLineaProduccion))

        #Cantidad de Componentes
        CantidadComponentes = int(LineaProduccion.find('CantidadComponentes').text)
        print("Cantidad de Componentes: " + str(CantidadComponentes))

        #TiempoEnsamblaje
        TiempoEnsamblaje = int(LineaProduccion.find('TiempoEnsamblaje').text)
        print("Tiempo de Ensamblaje: " + str(TiempoEnsamblaje))

        #Generar Lista de Componentes
        ListaComponentesLineaProduccion = linked_list_de_componentes_lineas_produccion()

        #Ingresar Componentes a la Lista
        for i in range(1, (CantidadComponentes+1)):
            ListaComponentesLineaProduccion.insertarFinal(i)

        #Revisar que si meta bien los Componentes
        print('Estos son los componentes de la Linea de Produccion ' + str(NumeroLineaProduccion) + ': ')
        ListaComponentesLineaProduccion.imprimir()

        #Almacenar La Lista de Linea de Produccion
        ListaLineasProduccion.insertar(NumeroLineaProduccion, CantidadComponentes, TiempoEnsamblaje, ListaComponentesLineaProduccion)
        print("Linea de Produccion Almacenada en Lista ;v")

        # Un Salto de Linea por orden
        print('')


    print('')

    #Variables Globales
    global ListaProductosMaquina

    #ListadoProductos
    ListadoProductos = Archivo.find('ListadoProductos')

    #Productos
    Productos = ListadoProductos.findall('Producto')

    #Titulo de Lista de Productos de Máquina
    print("Lista de Productos en la Máquina: ")
    print('')

    # Crear un id de Producto para identificar los productos con las lineas y almacenarlos en la lista
    idProducto = 0

    for Producto in Productos:

        #Aumentar el id de LineaProduccion
        idProducto = idProducto + 1
        print("El id del Producto es: " + str(idProducto))

        #NombreProducto
        NombreProducto = Producto.find('nombre').text
        print("Nombre de Producto: " + str(NombreProducto))

        #Elaboracion
        ElaboracionProducto = Producto.find('elaboracion').text
        print("Elaboracion del Producto: " + str(ElaboracionProducto))

        #Analizar la Elaboracion del Producto con regex

        #Quitar las "p"
        QuitarDeString = r'p'

        ElaboracionSinP = re.sub(QuitarDeString, '', ElaboracionProducto)

        print('')
        print("Elaboracion sin P: ")
        print(ElaboracionSinP)

        # Crear Lista de Pasos de cada Producto
        ListaPasosProducto = linked_list_pasos_graphviz()

        #MiniAnlizadorLexico
        Estado = 'L'
        Lexema = ''
        idPaso = 0

        # Varaibles para Guardar la Linea y Componente de cada paso
        LineaProduccionPaso = ''
        ComponentePaso = ''

        for Caracter in range(len(ElaboracionSinP)):
            CaracterActual = ElaboracionSinP[Caracter]

            try:
                CaracterSiguiente = ElaboracionSinP[Caracter + 1]  # Este es un pequeño "Scout", puede ver valores que den error
            except:
                CaracterSiguiente = ""
            #Encontrar la Primera L
            if Estado == "L":
                if CaracterActual == "L".upper() and CaracterSiguiente.isdigit():
                    Estado = "DigitosDespuesDeL"
                    Lexema = Lexema + CaracterActual
                    print('Este es el Lexema Actual: ' + str(Lexema))

            #Cualquier Numero de Digitos Entre L y C
            elif Estado == "DigitosDespuesDeL":
                if CaracterActual.isdigit() and CaracterSiguiente.isdigit():
                    Lexema = Lexema + CaracterActual
                    LineaProduccionPaso = LineaProduccionPaso + CaracterActual
                    print('Este es el Lexema Actual: ' + str(Lexema))
                elif CaracterActual.isdigit() and CaracterSiguiente == "C":
                    Estado = "C"
                    Lexema = Lexema + CaracterActual
                    LineaProduccionPaso = LineaProduccionPaso + CaracterActual
                    print('Este es el Lexema Actual: ' + str(Lexema))

            #Encontrar la C
            elif Estado == "C":
                if CaracterActual == "C" and CaracterSiguiente.isdigit():
                    Estado = "DigitosDespuesDeC"
                    Lexema = Lexema + CaracterActual
                    print('Este es el Lexema Actual: ' + str(Lexema))

            #Cualquier Numero de Digitos Despues de la C
            elif Estado == "DigitosDespuesDeC":
                if CaracterActual.isdigit() and CaracterSiguiente.isdigit():
                    Lexema = Lexema + CaracterActual
                    ComponentePaso = ComponentePaso + CaracterActual
                    print('Este es el Lexema Actual: ' + str(Lexema))

                elif CaracterActual.isdigit() and CaracterSiguiente.isspace():
                    Estado = "Espacio"
                    Lexema = Lexema + CaracterActual
                    ComponentePaso = ComponentePaso + CaracterActual
                    idPaso = idPaso + 1

                    # Ingresar Pasos a La Lista de Pasos
                    ListaPasosProducto.insertar(idPaso, Lexema, int(LineaProduccionPaso), int(ComponentePaso))
                    print('El Lexema almacenado es: ' + str(Lexema))
                    print('')

                    #Limpiar las variables del paso
                    LineaProduccionPaso = ''
                    ComponentePaso = ''

                elif CaracterActual.isdigit() and CaracterSiguiente == '':
                    Estado = "Final"
                    Lexema = Lexema + CaracterActual
                    ComponentePaso = ComponentePaso + CaracterActual
                    idPaso = idPaso + 1

                    #Ingresar Pasos a La Lista de Pasos
                    ListaPasosProducto.insertar(idPaso, Lexema, int(LineaProduccionPaso), int(ComponentePaso))
                    print('El Lexema almacenado es: ' + str(Lexema))
                    print('')

                    # Limpiar las variables del paso
                    LineaProduccionPaso = ''
                    ComponentePaso = ''

            elif Estado == "Espacio":
                Estado = "L"
                Lexema = ''
                print('Este es el Lexema Actual: ' + str(Lexema))

        print("Analisis Terminado ;v")


        print('')

        #Prueba que si se hayan ingresado
        print("Estos son los pasos que se guardaron en la Lista de Pasos Reales: ")
        ListaPasosProducto.imprimir()

        # Almacenar La Lista de Productos
        ListaProductosMaquina.insertar(idProducto, NombreProducto, ListaPasosProducto)
        print("Linea de Produccion Almacenada en Lista ;v")

        #Un Salto de Linea por orden
        print('')

    print('')

#Cargar Archivo Simulacion
def CargarArchivoSimulacion():
    filters = (("Archivos de XML", "*.xml"), ("Todos los Archivos", "*.*"))
    ArchivoAbierto = tk.filedialog.askopenfilename(filetypes=filters)
    Archivo = ElementTree.parse(ArchivoAbierto)

    #Variables Globales
    global ListaSimulaciones
    global idSimulacion
    #Analizar el XML

    #Aumentar el id
    idSimulacion = idSimulacion + 1

    # Titulo de Inicio
    print('')
    print('---------------------------------------------------')
    print('Inicio de Analisis del Archivo de Simulación')
    print('---------------------------------------------------')
    print('')

    #Nombre Simulacion
    NombreSimulacion = Archivo.find('Nombre').text
    print('El Nombre de la Simulación Cargada es: ' + str(NombreSimulacion))
    print('')

    #Listado de Productos
    ListadoProductos = Archivo.find('ListadoProductos')

    #Productos
    Productos = ListadoProductos.findall('Producto')

    #Crear Lista de Productos por Simulacion
    ListaProductosSimulacion = linked_list_productos_simulaciones()

    #Crear Lista para agregar los nombres de los productos al ComboBox
    ProductosComboBox = []

    idProductoSimulacion = 0
    for Producto in Productos:

        #Aumentar el id
        idProductoSimulacion = idProductoSimulacion + 1

        #Obtener el Nombre
        NombreProducto = Producto.text
        print('El Nombre del Producto es: ' + str(NombreProducto))
        print('')

        #Almacenar el Producto en una Lista para el ComboBox
        ProductosComboBox.append(NombreProducto)
        print('Estos son los productos en el ComboBox: ')
        print(ProductosComboBox)

        #Guardar el Producto enla Lista de Ssimulacion
        ListaProductosSimulacion.insertar(idProductoSimulacion, NombreProducto)
        print('Producto Almacenado en la Lista de Productos de Simulación')
        print('')

    #Guardar La Simulacion en la lista de Simulaciones
    ListaSimulaciones.insertar(idSimulacion, NombreSimulacion, ListaProductosSimulacion)
    print('Simulación Almacenada en la Lista de Simulaciones')
    print('')

    #Agregar los Nombres de los productos al ComboBox:
    ProductoAElegir['values'] = ProductosComboBox

    #Mensaje Analisis Terminado
    print('Analisis Terminado ;v')
    print('')

#####################################################
#METODOS PROCESAR ARCHIVO                           #
#####################################################
def RealizarSimulacion():

    #Revisar que Ambos Archivos esten cargados:
    if ListaLineasProduccion.head == None:
        print('No se ha cargado el Archivo de Maquina >:v')
        tk.messagebox.showinfo(title="Info", message="No se ha cargado el Archivo de Maquina >:v")
    elif ListaSimulaciones.head == None:
        print('No se ha cargado el Archivo de Simulaciones >:v')
        tk.messagebox.showinfo(title="Info", message="No se ha cargado el Archivo de Simulaciones >:v")
    else:

        #Obtener el nombre seleccionado del ComboBox y buscar el nombre en la lista de productos
        if ProductoAElegir.get() == '':
            print("No ha seleccionado un producto >:v")
            tk.messagebox.showinfo(title="Info", message="No ha seleccionado un producto >:v")
        else:
            ProductoComboBox = ProductoAElegir.get()
            ProductoSeleccionado = ListaProductosMaquina.buscar(ProductoComboBox)

            #Obtener la lista de Pasos para el Producto
            PasosProducto = ProductoSeleccionado.Elaboracion

            #Ver que sí tenga la lista correcta
            print('Estos son los pasos de ' + ProductoComboBox +': ')
            PasosProducto.imprimir()
            print('')

            #Cambiar El Label de Pasos Necesarios:
            ActualPasosNecesarios = PasosProducto.head
            StringPasosNecesarios = ''
            while ActualPasosNecesarios != None:
                StringPasosNecesarios = StringPasosNecesarios + str(ActualPasosNecesarios.PasoReal) + '\n'
                ActualPasosNecesarios = ActualPasosNecesarios.next

            #Poner el String en el Label
            textPasosNReal.delete('1.0', 'end')
            textPasosNReal.insert(INSERT, StringPasosNecesarios)

            # Generar Archivo HTML
            ####################################################################

            #Empezar el String del HTML
            StringDocumentoHTML ='<!DOCTYPE html>\n<html>\n  <head>\n<style>\n table, th, td {\n border: 1px solid black;\n}\n</style>\n<title>Simulacion HTML</title>\n   </head>\n   <body>\n '

            #Titulo Archivo
            StringDocumentoHTML = StringDocumentoHTML + '<h1>IPC2 Proyecto 2</h1>\n<h2>Tabla de Pasos: </h2>\n'

            # Crear Tabla de Pasos
            StringDocumentoHTML = StringDocumentoHTML + '<table>\n  <tr>\n  <th bgcolor = #A93226>Tiempo (t)</th>\n\n'

            #Recorrer la Lista de Lineas de Produccion

            #Generar Encabezados
            ActualListaLineasProduccion = ListaLineasProduccion.head

            while ActualListaLineasProduccion != None:
                StringDocumentoHTML = StringDocumentoHTML + '<th bgcolor = #F1C40F> Linea de Ensamblaje '+ str(ActualListaLineasProduccion.id) + '</th>\n\n'
                ActualListaLineasProduccion = ActualListaLineasProduccion.next

            #Cerrar los encabezados de la tabla
            StringDocumentoHTML = StringDocumentoHTML + '</tr>\n'

            #Llenar los pasos en la Tabla
            #Recorrer Cada Paso
            ActualPasosProducto = PasosProducto.head

            #SegundoActual
            SegundoActual = 0
            while ActualPasosProducto != None:

                #Recorrer cada Linea de Produccion
                for i in range(1,CantidadLineasProduccionMaquina+1):
                    ActualRecorrerListaLineasProduccion = ListaLineasProduccion.buscar(i)


                    if int(ActualRecorrerListaLineasProduccion.id) == ActualPasosProducto.LineaProduccion:

                        #Obtener el Valor Inicial del Componente Actual
                        ActualComponentesLineaProduccionSeleccionada = ActualRecorrerListaLineasProduccion.Componentes.head

                        if int(ActualComponentesLineaProduccionSeleccionada.id) < ActualPasosProducto.Componente:
                            while ActualComponentesLineaProduccionSeleccionada.id != ActualPasosProducto.Componente:
                                # Empezar la Fila
                                StringDocumentoHTML = StringDocumentoHTML + '<tr>\n'
                                # Agregar Segundo
                                SegundoActual = SegundoActual + 1
                                StringDocumentoHTML = StringDocumentoHTML + '<td bgcolor = #A93226 >' + str(SegundoActual) + '</td>\n'
                                for j in range(1, CantidadLineasProduccionMaquina + 1):
                                    ActualRecorrerListaLineasProduccionWhile = ListaLineasProduccion.buscar(j).id
                                    if ActualRecorrerListaLineasProduccionWhile == ActualPasosProducto.LineaProduccion:
                                        StringDocumentoHTML = StringDocumentoHTML + '<td bgcolor = #2980B9> Mover Brazo, Componente ' + str(ActualComponentesLineaProduccionSeleccionada.id) + '</td>\n'
                                    elif ActualRecorrerListaLineasProduccionWhile != ActualPasosProducto.LineaProduccion:
                                        StringDocumentoHTML = StringDocumentoHTML + '<td bgcolor = #2980B9> No hacer nada </td>\n'
                                # Cerrar la Fila
                                StringDocumentoHTML = StringDocumentoHTML + '</tr>\n\n'
                                ActualComponentesLineaProduccionSeleccionada = ActualComponentesLineaProduccionSeleccionada.next
                            #Ensamblar Componente
                            if ActualComponentesLineaProduccionSeleccionada.id == ActualPasosProducto.Componente:
                                # Empezar la Fila
                                StringDocumentoHTML = StringDocumentoHTML + '<tr>\n'
                                # Agregar Segundo
                                SegundoActual = SegundoActual + 1
                                StringDocumentoHTML = StringDocumentoHTML + '<td bgcolor = #A93226 >' + str(SegundoActual) + '</td>\n'

                                for j in range(1, CantidadLineasProduccionMaquina + 1):
                                    ActualRecorrerListaLineasProduccionWhile = ListaLineasProduccion.buscar(j).id
                                    if ActualRecorrerListaLineasProduccionWhile == ActualPasosProducto.LineaProduccion:
                                        StringDocumentoHTML = StringDocumentoHTML + '<td bgcolor = #2980B9> Ensamblar Componente ' + str(ActualComponentesLineaProduccionSeleccionada.id) + '</td>\n'
                                    elif ActualRecorrerListaLineasProduccionWhile != ActualPasosProducto.LineaProduccion:
                                        StringDocumentoHTML = StringDocumentoHTML + '<td bgcolor = #2980B9> No hacer nada </td>\n'
                                # Cerrar la Fila
                                StringDocumentoHTML = StringDocumentoHTML + '</tr>\n\n'

                        elif int(ActualComponentesLineaProduccionSeleccionada.id) > ActualPasosProducto.Componente:
                            while ActualComponentesLineaProduccionSeleccionada.id != ActualPasosProducto.Componente:
                                # Empezar la Fila
                                StringDocumentoHTML = StringDocumentoHTML + '<tr>\n'
                                # Agregar Segundo
                                SegundoActual = SegundoActual + 1
                                StringDocumentoHTML = StringDocumentoHTML + '<td bgcolor = #A93226 >' + str(SegundoActual) + '</td>\n'
                                for i in range(1, CantidadLineasProduccionMaquina + 1):
                                    ActualRecorrerListaLineasProduccionWhile = ListaLineasProduccion.buscar(j).id
                                    if ActualRecorrerListaLineasProduccionWhile == ActualPasosProducto.LineaProduccion:
                                        StringDocumentoHTML = StringDocumentoHTML + '<td bgcolor = #2980B9> Mover Brazo, Componente ' + str(ActualComponentesLineaProduccionSeleccionada.id) + '</td>\n'
                                    elif ActualRecorrerListaLineasProduccionWhile != ActualPasosProducto.LineaProduccion:
                                        StringDocumentoHTML = StringDocumentoHTML + '<td bgcolor = #2980B9> No hacer nada </td>\n'
                                ActualComponentesLineaProduccionSeleccionada = ActualComponentesLineaProduccionSeleccionada.previous
                                # Cerrar la Fila
                                StringDocumentoHTML = StringDocumentoHTML + '</tr>\n\n'

                            #Ensamblar Componente
                            if ActualComponentesLineaProduccionSeleccionada.id == ActualPasosProducto.Componente:
                                # Empezar la Fila
                                StringDocumentoHTML = StringDocumentoHTML + '<tr>\n'
                                # Agregar Segundo
                                SegundoActual = SegundoActual + 1
                                StringDocumentoHTML = StringDocumentoHTML + '<td bgcolor = #A93226 >' + str(
                                    SegundoActual) + '</td>\n'

                                for j in range(1, CantidadLineasProduccionMaquina + 1):
                                    ActualRecorrerListaLineasProduccionWhile = ListaLineasProduccion.buscar(j).id
                                    if ActualRecorrerListaLineasProduccionWhile == ActualPasosProducto.LineaProduccion:
                                        StringDocumentoHTML = StringDocumentoHTML + '<td bgcolor = #2980B9> Ensamblar Componente ' + str(
                                            ActualComponentesLineaProduccionSeleccionada.id) + '</td>\n'
                                    elif ActualRecorrerListaLineasProduccionWhile != ActualPasosProducto.LineaProduccion:
                                        StringDocumentoHTML = StringDocumentoHTML + '<td bgcolor = #2980B9> No hacer nada </td>\n'
                                # Cerrar la Fila
                                StringDocumentoHTML = StringDocumentoHTML + '</tr>\n\n'

                #Avanzar el Actual de Pasos
                ActualPasosProducto = ActualPasosProducto.next




            # Generar el HTML
            with open("TablaPasos.html", "w") as ArchivoHTML:
                # Terminar el String del HTML
                StringDocumentoHTML = StringDocumentoHTML + '</tr>\n</table>\n</body>\n</html>'
                ArchivoHTML.write(StringDocumentoHTML)
                ArchivoHTML.close()
                print('')
                print("Ahorita te abro el HTML ;v")
                os.system("start TablaPasos.html")

            #Generar Archivo de GraphViz
            ####################################################################
            ActualGraphviz = PasosProducto.head

            #Empezar el String de Graphviz
            CodigoGraphViz = 'digraph D {\n rankdir = LR;\n'
            idNodoGraphViz = 1
            while ActualGraphviz != None:
                #Agregar Nodo por Nodo
                CodigoGraphViz = CodigoGraphViz + str(idNodoGraphViz) + '[label=\"' + ActualGraphviz.PasoReal + '\", shape=box];\n'

                # Aumentar el numero del apuntador
                idNodoGraphViz = idNodoGraphViz + 1

                #Agregar Los Apuntadores
                if ActualGraphviz.next != None:
                    CodigoGraphViz = CodigoGraphViz + str(idNodoGraphViz - 1) + '->' + str(idNodoGraphViz) + ';\n'
                elif ActualGraphviz.next == None:
                    CodigoGraphViz = CodigoGraphViz

                ActualGraphviz = ActualGraphviz.next

            #Generar El Archivo de GraphViz:
            with open("SecuenciaPasos.dot", 'w') as ArchivoGraphViz:

                # Agregar el Nombre del Producto
                CodigoGraphViz = CodigoGraphViz + "\nlabelloc = \"t\";\nlabel=\"" + ProductoComboBox + "\";\n"

                # TerminarElStringDelCodigo
                CodigoGraphViz = CodigoGraphViz + "\n}"

                ArchivoGraphViz.write(CodigoGraphViz)
            ArchivoGraphViz.close()
            print('')
            print('Ahorita te abro el Grafo ;v')
            os.system("dot -Tjpg SecuenciaPasos.dot -o SecuenciaPasosReal.jpg")
            os.system("start SecuenciaPasosReal.jpg")

def RealizarSimulacionTiempo():

    #Revisar que Ambos Archivos esten cargados:
    if ListaLineasProduccion.head == None:
        print('No se ha cargado el Archivo de Maquina >:v')
        tk.messagebox.showinfo(title="Info", message="No se ha cargado el Archivo de Maquina >:v")
    elif ListaSimulaciones.head == None:
        print('No se ha cargado el Archivo de Simulaciones >:v')
        tk.messagebox.showinfo(title="Info", message="No se ha cargado el Archivo de Simulaciones >:v")
    else:

        #Obtener el nombre seleccionado del ComboBox y buscar el nombre en la lista de productos
        if ProductoAElegir.get() == '':
            print("No ha seleccionado un producto >:v")
            tk.messagebox.showinfo(title="Info", message="No ha seleccionado un producto >:v")
        else:
            ProductoComboBox = ProductoAElegir.get()
            ProductoSeleccionado = ListaProductosMaquina.buscar(ProductoComboBox)

            #Obtener la lista de Pasos para el Producto
            PasosProducto = ProductoSeleccionado.Elaboracion

            #Ver que sí tenga la lista correcta
            print('Estos son los pasos de ' + ProductoComboBox +': ')
            PasosProducto.imprimir()
            print('')

            #Cambiar El Label de Pasos Necesarios:
            ActualPasosNecesarios = PasosProducto.head
            StringPasosNecesarios = ''
            while ActualPasosNecesarios != None:
                StringPasosNecesarios = StringPasosNecesarios + str(ActualPasosNecesarios.PasoReal) + '\n'
                ActualPasosNecesarios = ActualPasosNecesarios.next

            #Poner el String en el Label
            textPasosNReal.delete('1.0', 'end')
            textPasosNReal.insert(INSERT, StringPasosNecesarios)

            #Obtener el Valor de t
            Segundo = int(txtbbxMomentoProcesoActual.get())

            #Generar Archivo de GraphViz
            ActualGraphviz = PasosProducto.head

            #Empezar el String de Graphviz
            CodigoGraphViz = 'digraph D {\n rankdir = LR;\n'
            idNodoGraphViz = 1

            for i in range(1, (Segundo+1)):
                if PasosProducto.buscar(i) != False:
                    #Agregar Nodo por Nodo
                    CodigoGraphViz = CodigoGraphViz + str(idNodoGraphViz) + '[label=\"' + PasosProducto.buscar(i).PasoReal + '\", shape=box];\n'

                    # Aumentar el numero del apuntador
                    idNodoGraphViz = idNodoGraphViz + 1

                    # Agregar Los Apuntadores
                    if i < Segundo:
                        CodigoGraphViz = CodigoGraphViz + str(idNodoGraphViz - 1) + '->' + str(idNodoGraphViz) + ';\n'
                    elif i == Segundo:
                        CodigoGraphViz = CodigoGraphViz
                else:
                    CodigoGraphViz = CodigoGraphViz



            #Generar El Archivo de GraphViz:
            with open("SecuenciaPasos.dot", 'w') as ArchivoGraphViz:

                # Agregar el Nombre del Producto
                CodigoGraphViz = CodigoGraphViz + "\nlabelloc = \"t\";\nlabel=\"" + ProductoComboBox + "\";\n"

                # TerminarElStringDelCodigo
                CodigoGraphViz = CodigoGraphViz + "\n}"

                ArchivoGraphViz.write(CodigoGraphViz)
            ArchivoGraphViz.close()
            print('')
            print('Ahorita te lo abro ;v')
            os.system("dot -Tjpg SecuenciaPasos.dot -o SecuenciaPasosReal.jpg")
            os.system("start SecuenciaPasosReal.jpg")



# MenuBar
BarraMenu = Menu(Ventana)

# Menu Archivo
MenuArchivo = Menu(BarraMenu, tearoff=0)
MenuArchivo.add_command(label="Cargar Archivo Configuracion Maquina", command=CargarArchivoMaquina)
MenuArchivo.add_command(label="Cargar Archivo Configuracion Simulacion", command=CargarArchivoSimulacion)
MenuArchivo.add_command(label="Salir", command=Ventana.quit)
BarraMenu.add_cascade(label="Archivo", menu=MenuArchivo)

# Menu Ayuda
MenuAyuda = Menu(BarraMenu, tearoff=0)
MenuAyuda.add_command(label="Mostrar Datos Estudiante", command=MostrarDatosEstudiante)
MenuAyuda.add_command(label="Mostrar Documentación", command=nada)
BarraMenu.add_cascade(label="Ayuda", menu=MenuAyuda)

Ventana.config(menu=BarraMenu)

#ComboBox De Productos
lblProductoElegir = Label(Ventana, text="Producto Elegido: ", bg="#00FF7F")
lblProductoElegir.place(x=30, y=10, height=20, width=100)

ProductoAElegir = ttk.Combobox(Ventana, textvariable='ProductoElegido')
ProductoAElegir.place(x=30, y=40, height=20, width=200)

#PasosNecesarios
lblPasosN = Label(Ventana, text="Pasos Necesarios: ", bg="#00FF7F")
lblPasosN.place(x=50, y=70, height=20, width=150)

#Crear Texto
textPasosNReal = Text(Ventana, bg='#8FBC8F')
textPasosNReal.insert(INSERT, "Pasos a Realizar")

#Crear Scrollbar
ScrollbarPasosNReal = ttk.Scrollbar(textPasosNReal, orient='vertical', command=textPasosNReal.yview)
ScrollbarPasosNReal.place(x=250, y=0, height=270)
textPasosNReal.config(yscrollcommand=ScrollbarPasosNReal.set)
textPasosNReal.place(x=50, y=100, height=270, width=270)

#Boton Realizar Simulacion 100%
btnRealizarSimulacion = Button(Ventana, text="Realizar Simulación", command=RealizarSimulacion)
btnRealizarSimulacion.place(x=200, y=380, height=20, width=120)

#ProcesoActual
lblProcesoActual = Label(Ventana, text="Proceso en el momento: ", bg="#00FF7F")
lblProcesoActual.place(x=350, y=10, height=20, width=170)

txtbbxMomentoProcesoActual = Entry(Ventana)
txtbbxMomentoProcesoActual.place(x=530, y=10, height=20, width=50)

btnMostrarProceso = Button(Ventana, text="Mostrar Proceso", command=RealizarSimulacionTiempo)
btnMostrarProceso.place(x=590, y=10, height=20, width=120)

#Etiqeutas Nombre Matrices
Ventana.mainloop()
