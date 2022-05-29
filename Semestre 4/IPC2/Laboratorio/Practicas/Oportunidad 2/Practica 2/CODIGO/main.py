import sys
import os
import webbrowser
#################################################
# LISTA DOBLE DATOS
class linked_list_de:
    def __init__ (self, head=None):
        self.head = head
        self.last = head
        self.size = 0

    def insertar (self, Numero, Nombre, Apellido):
        if self.size == 0:
            self.head = node_de(Numero = Numero, Nombre = Nombre, Apellido = Apellido)
            self.last = self.head
        else:
            new_node = node_de(Numero = Numero, Nombre = Nombre, Apellido = Apellido, next=self.head)
            self.head.previous = new_node
            self.head = new_node
        self.size += 1

    def buscar(self, Numero):
        actual = self.head
        while actual != None:
            if Numero == actual.Numero:
                return actual
            actual = actual.next
        return None

    def imprimir (self):
        if self.head is None:
            return
        node = self.head
        print(node.Nombre, end = " => ")
        while node.next:
            node = node.next
            print(node.Nombre, end = " => ")

    def eliminar (self, Numero):
        node = self.head
        while node is not None:
            if node.Numero == Numero:
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


# Nodo Lista Simple
class node_de:
    def __init__(self, Numero=None, Nombre=None, Apellido = None, previous = None, next=None):
        self.Numero = Numero
        self.Nombre = Nombre
        self.Apellido = Apellido
        self.previous = previous
        self.next = next

Agenda = linked_list_de()

def IngresarContacto():
    print("Bienvenido, ingrese los datos que se requieren: ")
    Valido = False
    while not Valido:
        try:
            NumeroContacto = input("Ingrese el Numero del Contacto: ")
            NombreContacto = input("Ingrese el Nombre del Contacto: ")
            ApellidoContacto = input("Ingrese el Apellido del Contacto: ")
            if Agenda.buscar(NumeroContacto) != None:
                raise ValueError
            else:
                Agenda.insertar(NumeroContacto, NombreContacto, ApellidoContacto)
                print("Contacto guardado con exito :)")
                input("Ingrese cualquier valor para volver al menú principal: ")
                Menu()
                Valido = True
        except ValueError:
            print("El Concacto ya existe, ingrese otro")

def BuscarContacto():
    NumeroContacto = input("Bienvenido, ingrese el numero que desea buscar: ")
    if Agenda.buscar(NumeroContacto) == None:
        OpcionAgregar = input("El Número no existe, desea agregarlo? Y/N: ")
        if OpcionAgregar == "Y":
            NombreContacto = input("Ingrese el Nombre del Contacto: ")
            ApellidoContacto = input("Ingrese el Apellido del Contacto: ")
            Agenda.insertar(NumeroContacto, NombreContacto, ApellidoContacto)
            print("Contacto guardado con exito!")
            input("Ingrese cualquier valor para volver al menú principal: ")
            Menu()
        elif OpcionAgregar == "N":
            print('')
            input("Ingrese cualquier valor para volver al menú principal: ")
            Menu()
        else:
            print('')
            input("Opción no valida, ingrese cualquier valor para volver al menú principal: ")
            Menu()
    elif Agenda.buscar(NumeroContacto) != None:
        print("Contacto Encontrado ;v")
        print("Nombre: " + Agenda.buscar(NumeroContacto).Nombre)
        print("Apellido: " + Agenda.buscar(NumeroContacto).Apellido)
        print("Número: " + Agenda.buscar(NumeroContacto).Numero)
        print('')
        input("Ingrese cualquier valor para volver al menú principal: ")
        Menu()


def VisualizarAgenda():
    CodigoGraphViz = 'digraph D {\n rankdir = TB;\n 1[label=\"' + "Agenda" + '\"];\n'

    # Filas
    Actual = Agenda.head
    NumeroNodo = 2
    while Actual != None:
        #Crear Nuevos Nodos
        CodigoGraphViz = CodigoGraphViz + '\n' + str(NumeroNodo) + '[label=\"Nombre: ' + str(Actual.Nombre) + '\n Apellido:' + str(Actual.Apellido) + '\n Teléfono: '+ str(Actual.Numero) +'\"];\n'
        NumeroNodoAnterior = NumeroNodo - 1
        #CrearConexiones
        CodigoGraphViz = CodigoGraphViz + str(NumeroNodoAnterior) + ' -> ' + str(NumeroNodo) + '\n' + str(NumeroNodo) + ' -> ' + str(NumeroNodoAnterior) + '\n'
        NumeroNodo = NumeroNodo + 1
        Actual = Actual.next

    # Generar El Archivo de GraphViz:
    with open("AgendaGraficada.dot", 'w') as ArchivoGraphViz:
        #TerminarElStringDelCodigo
        CodigoGraphViz = CodigoGraphViz + "\n}"
        ArchivoGraphViz.write(CodigoGraphViz)
        ArchivoGraphViz.close()
    print('')
    os.system("dot -Tjpg AgendaGraficada.dot -o AgendaGraficadaReal.jpg")
    print('')
    print("Ahorita te lo abro ;v")
    webbrowser.open('AgendaGraficadaReal.jpg')

    print('')
    input("Ingrese cualquier valor para volver al menú principal: ")
    Menu()

def Menu():
    # Caja Opciones Menú
    # Linea Superior
    for x in range(0, 42):
        print("-", end="")

    # Un Salto de Linea
    print()

    # Partes Intermedias

    print("| 1. Insertar Nuevo Contacto             |")
    print("| 2. Buscar Contacto                     |")
    print("| 3. Visualizar Agenda                   |")
    print("| 4. Salida                              |")

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
                IngresarContacto()
                Valido = True
            elif OpcionMenu == 2:
                BuscarContacto()
                Valido = True
            elif OpcionMenu == 3:
                VisualizarAgenda()
                Valido = True
            elif OpcionMenu == 4:
                sys.exit()
            else:
                raise ValueError
        except ValueError:
            print("Solo Puede Ingresar el Número de la función, por ejemplo, ingrese '1' si desea cargar un archivo, Intentelo de nuevo ")
        print()


# Funcion que inicia el programa
Menu()
