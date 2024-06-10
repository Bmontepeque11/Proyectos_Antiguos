package main

import (
	"encoding/csv"
	"fmt"
	"io"
	"os"
	"time"
)

//###################################################
//#				Estructuras de Datos                #
//###################################################

// Pila de Movimientos Administrador
type MetodosPila interface {
	Agregar(Mensaje string)
	Eliminar()
	ObtenerPrimero() *NodoPilaAdmin
	ObtenerTamaño() int
}

type NodoPilaAdmin struct {
	Mensaje       string
	NodoSiguiente *NodoPilaAdmin
}

type Pila struct {
	PrimerNodo *NodoPilaAdmin
	Tamaño     int
}

func (p *Pila) Agregar(Mensaje string) {
	p.PrimerNodo = &NodoPilaAdmin{
		Mensaje:       Mensaje,
		NodoSiguiente: p.PrimerNodo,
	}
	p.Tamaño++
}

func (p *Pila) Eliminar() {
	p.PrimerNodo = p.PrimerNodo.NodoSiguiente
	p.Tamaño--
}

func (p *Pila) ObtenerPrimero() *NodoPilaAdmin {
	return p.PrimerNodo
}

func (p *Pila) ObtenerTamaño() int {
	return p.Tamaño
}

// Crear Pila Movimientos del Admin
var PilaMovimientosAdmin MetodosPila = &Pila{}

//Lista de Pilas

// Lista Doble Estudiantes Aprobados
type NodoListaDoble struct {
	Carnet        string
	Nombre        string
	Contraseña    string
	NodoSiguiente *NodoListaDoble
	NodoAnterior  *NodoListaDoble
}

type ListaDoble struct {
	Tamaño  int
	Primero *NodoListaDoble
	Ultimo  *NodoListaDoble
}

func (LD *ListaDoble) AgregarAlFinal(carnet string, nombre string, contraseña string) {
	var Temporal = &NodoListaDoble{}
	Temporal.Carnet = carnet
	Temporal.Nombre = nombre
	Temporal.Contraseña = contraseña
	Temporal.NodoSiguiente = nil
	Temporal.NodoAnterior = nil

	if LD.Primero == nil && LD.Ultimo == nil {
		LD.Primero = Temporal
		LD.Ultimo = Temporal
	} else {
		Temporal.NodoAnterior = LD.Ultimo
		LD.Ultimo.NodoSiguiente = Temporal
		LD.Ultimo = Temporal
	}
	LD.Tamaño++
}

func (LD *ListaDoble) MostarListaDoble() {
	var p *NodoListaDoble = LD.Primero
	if LD.Tamaño == 0 {
		fmt.Println("La Lista Doble está vacia")
	} else {
		for p != nil {
			fmt.Print("Nombre: -> ", p.Nombre, ", Carnet: ", p.Carnet, "\n")
			fmt.Println("***********************************************")
			p = p.NodoSiguiente
		}
	}

}

// Crear la Lista Doble
var ListaDobleEstudiantesAprobados = &ListaDoble{}

// Cola de Espera de Estudiantes
type MetodosCola interface {
	Agregar(carnet string, nombre string, contraseña string)
	ObtenerPrimero() *Node
	Eliminar()
	VerTamaño() int
}

type Node struct {
	Carnet        string
	Nombre        string
	Contraseña    string
	NodoSiguiente *Node
}

type Cola struct {
	primerNodo *Node
	ultimoNodo *Node
	Tamaño     int
}

func (c *Cola) Agregar(carnet string, nombre string, contraseña string) {
	nuevoNodo := &Node{
		Carnet:        carnet,
		Nombre:        nombre,
		Contraseña:    contraseña,
		NodoSiguiente: nil,
	}
	if c.Tamaño == 0 {
		c.primerNodo = nuevoNodo
	} else {
		c.ultimoNodo.NodoSiguiente = nuevoNodo
	}
	c.ultimoNodo = nuevoNodo

	c.Tamaño++
}

func (c *Cola) ObtenerPrimero() *Node {
	if c.Tamaño == 0 {
		return nil
	} else {
		return c.primerNodo
	}

}

func (c *Cola) Eliminar() {
	if c.Tamaño == 1 {
		c.primerNodo = nil
		c.ultimoNodo = nil
	} else {
		c.primerNodo = c.primerNodo.NodoSiguiente
	}
	c.Tamaño--
}

func (c *Cola) VerTamaño() int {
	return c.Tamaño
}

// Crear la Cola
var ColaEstudiantesPendientes MetodosCola = &Cola{}

//###################################################
//#				FUNCIONES VARIAS                    #
//###################################################

func EstudiantesPendientes() {

	//Lineas de Espacio
	fmt.Println("")
	fmt.Println("")

	if ColaEstudiantesPendientes.VerTamaño() == 0 {
		//Lista de Estudiantes
		fmt.Print("************* Pendientes: 0 *************\n")
		fmt.Println("*Estudiante Actual: Vacio               *")
		fmt.Println("*       1.Aceptar al Estudiante         *")
		fmt.Println("*       2.Rechazar al Estudiante        *")
		fmt.Println("*       3.Volver al Menú                *")
		fmt.Println("*****************************************")

		//Mensaje de confirmacion
		var VolverAlMenu string
		fmt.Println("Lista Vacia, Ingrese cualquier valor para volver al Menú: ")
		fmt.Scanln(&VolverAlMenu)

		MenuAdministrador()

	} else {
		//Lista de Estudiantes
		fmt.Print("************* Pendientes: ", ColaEstudiantesPendientes.VerTamaño(), " *************\n")
		fmt.Println("*Estudiante Actual: ", ColaEstudiantesPendientes.ObtenerPrimero().Nombre)
		fmt.Println("*       1.Aceptar al Estudiante         *")
		fmt.Println("*       2.Rechazar al Estudiante        *")
		fmt.Println("*       3.Volver al Menú                *")
		fmt.Println("*****************************************")

		//Recibir Input
		var OpcionEstudiantesPendientes int
		fmt.Print("La Opción Ingresada es: ")
		fmt.Scanln(&OpcionEstudiantesPendientes)
		switch OpcionEstudiantesPendientes {
		case 1:
			//Aceptar al Estudiante

			//Crear variables
			var CarnetEAprobado string
			CarnetEAprobado = ColaEstudiantesPendientes.ObtenerPrimero().Carnet
			var NombreEAporbado string
			NombreEAporbado = ColaEstudiantesPendientes.ObtenerPrimero().Nombre
			var ContraseñaEAporbado string
			ContraseñaEAporbado = ColaEstudiantesPendientes.ObtenerPrimero().Contraseña

			//Agregar a Lista Doble
			ListaDobleEstudiantesAprobados.AgregarAlFinal(CarnetEAprobado, NombreEAporbado, ContraseñaEAporbado)

			//Agregar Movimiento a Pila de Movimientos del Admin

			HorayFechaActual := time.Now()

			var StringPilaAdmin string
			StringPilaAdmin = ("Se Aceptó a Estudiante \n" + HorayFechaActual.String())
			PilaMovimientosAdmin.Agregar(StringPilaAdmin)

			//Mensaje de confirmacion
			fmt.Println("Estudiante Agregado a Cola de Pendientes")

			ColaEstudiantesPendientes.Eliminar()
			EstudiantesPendientes()

		case 2:
			//Rechazar al Estudiante

			fmt.Println("Estudiante Rechazado")
			ColaEstudiantesPendientes.Eliminar()

			//Agregar Movimiento a Pila de Movimientos del Admin

			HorayFechaActual := time.Now()

			var StringPilaAdmin string
			StringPilaAdmin = ("Se Rechazó a Estudiante \n" + HorayFechaActual.String())
			PilaMovimientosAdmin.Agregar(StringPilaAdmin)

			EstudiantesPendientes()

		case 3:
			//Volver al Menú Administrador
			MenuAdministrador()
		}

	}

}

func VerEstudiantes() {

	//Lineas de Espacio
	fmt.Println("")
	fmt.Println("")

	//Mostrar Lista Doble
	ListaDobleEstudiantesAprobados.MostarListaDoble()

	//Mensaje de confirmacion
	var VolverAlMenu string
	fmt.Println("Lista Mostrada, Ingrese cualquier valor para volver al Menú: ")
	fmt.Scanln(&VolverAlMenu)

	MenuAdministrador()

}

func RegistrarEstudiante() {

	//Lineas de Espacio
	fmt.Println("")
	fmt.Println("")

	//Crear Varibables
	var NombreE string
	var ApellidoE string
	var Carnet string
	var Contraseña string
	var VolverAlMenu string

	//Obtenrer Nombre
	fmt.Print("Ingrese Nombre: ")
	fmt.Scanln(&NombreE)

	//Obtener Apellido
	fmt.Print("Ingrese Apellido: ")
	fmt.Scanln(&ApellidoE)

	//Obtener Carnet
	fmt.Print("Ingrese Carnet: ")
	fmt.Scanln(&Carnet)

	//Obtener Contraseña
	fmt.Print("Ingrese Contraseña: ")
	fmt.Scanln(&Contraseña)

	//Fusionar nombre y apellido
	var NombreCompleto string
	NombreCompleto = NombreE + " " + ApellidoE

	//Agregar a Cola
	ColaEstudiantesPendientes.Agregar(Carnet, NombreCompleto, Contraseña)

	//Confirmación de Agregado
	fmt.Println("Estudiante Agregado a Cola de Pendientes, ingrese cualquier valor para volver al Menú de Administrador: ")
	fmt.Scanln(&VolverAlMenu)

	MenuAdministrador()
}

func CargaMasiva(ruta string) {
	file, err := os.Open(ruta)
	//Si no encuentra el archivo
	if err != nil {
		fmt.Println("Error al abrir el archivo")
		return
	}
	defer file.Close()

	leer := csv.NewReader(file)
	leer.Comma = ','
	encabezado := true
	for {
		linea, err := leer.Read()
		if err == io.EOF {
			break
		}
		if err != nil {
			fmt.Println("No se pudo leer la linea")
			continue
		}
		if encabezado {
			encabezado = false
			continue
		}

		//Agregar Estudiantes Pendientes (Carnet = linea[0], Nombre = linea[1], Contraseña = linea[2])
		ColaEstudiantesPendientes.Agregar(linea[0], linea[1], linea[2])

		fmt.Println("Nombre: ", linea[1], " Carnet: ", linea[0])

	}

	//Confirmacion Agregados
	var VolverAlMenu string
	fmt.Println("Estudiantes Agregados a la Cola de Estudiantes Pendientes, ingrese cualquier valor para volver al menú: ")
	fmt.Scanln(&VolverAlMenu)

	MenuAdministrador()
}
func MenuAdministrador() {

	//Lineas de Espacio
	fmt.Println("")
	fmt.Println("")

	//Crear el menú principal
	fmt.Println("*** Dashboard Administrador - EDD GoDrive ***")
	fmt.Println("*       1.Ver Estudiantes Pendientes        *")
	fmt.Println("*       2.Ver Estudiantes del Sistema       *")
	fmt.Println("*       3.Registrar Nuevo Estudiante        *")
	fmt.Println("*       4.Carga Masiva de Estudiantes       *")
	fmt.Println("*       5.Cerrar Sesión                     *")
	fmt.Println("*********************************************")

	//Recibir Input
	var OpcionMenuAdmin int
	fmt.Print("La Opción Ingresada es: ")
	fmt.Scanln(&OpcionMenuAdmin)

	switch OpcionMenuAdmin {
	case 1:
		//Estudiantes Pendientes

		//Crear el banner
		fmt.Println("*** Estudiantes Pendientes ***")
		EstudiantesPendientes()

	case 2:
		//Estudiantes Registrados

		//Crear el banner
		fmt.Println("*** Estudiantes Registrados ***")
		VerEstudiantes()

	case 3:
		//Registrar Estudiante Nuevo

		//Crear el banner
		fmt.Println("*** Registrar Estudiante ***")
		RegistrarEstudiante()

	case 4:
		//Carga Masiva Estudiantes

		//Crear el banner
		fmt.Println("*** Carga Masiva Estudiantes ***")

		var RutaArchivo string
		fmt.Print("Ingrese la ruta del archivo: ")
		fmt.Scanln(&RutaArchivo)

		fmt.Println("Esta es la ruta del archivo abierto: ", RutaArchivo)

		CargaMasiva(RutaArchivo)

	case 5:

		//Lineas de Espacio
		fmt.Println("")
		fmt.Println("")
		MenuPrincipal()
	}
}
func MenuUsuario() {

}

func CrearArchivo() {
	//Verifica que el archivo existe
	var _, err = os.Stat("Archivo.json")
	//Crea el archivo si no existe
	if os.IsNotExist(err) {
		var file, err = os.Create("Archivo.json")
		if err != nil {
			return
		}
		defer file.Close()
	}
	fmt.Println("Archivo creado exitosamente", "Archivo.json")
}

func EscribirArchivo(contenido string) {
	var file, err = os.OpenFile("Archivo.json", os.O_RDWR, 0644)
	if err != nil {
		return
	}
	defer file.Close()
	// Escribe algo de texto linea por linea
	_, err = file.WriteString(contenido)
	if err != nil {
		return
	}
	// Salva los cambios
	err = file.Sync()
	if err != nil {
		return
	}
	fmt.Println("Archivo actualizado existosamente.")
}

func VerReportes() {

	//Crear el menú
	fmt.Println("********** Ver Reportes **********")
	fmt.Println("*   1.Pila Movimientos Admin     *")
	fmt.Println("*  2.Cola Estudiantes Pendientes *")
	fmt.Println("*  3.Lista Doble Estudiantes     *")
	fmt.Println("*       4.Reporte JSON          *")
	fmt.Println("*       5.Salir del Sistema     *")
	fmt.Println("*********************************")

	var OpcionMenu int
	fmt.Print("La Opción Ingresada es: ")
	fmt.Scanln(&OpcionMenu)

	switch OpcionMenu {

	case 1:

		fmt.Println("")
		fmt.Println("")
		fmt.Println("Pila de Movimientos del Admin:")

		var NodoActual *NodoPilaAdmin = PilaMovimientosAdmin.ObtenerPrimero()
		if PilaMovimientosAdmin.ObtenerTamaño() == 0 {
			fmt.Println("La Pila está vacia")
		} else {
			for NodoActual != nil {
				fmt.Print("Mensaje: -> ", NodoActual.Mensaje, "\n")
				fmt.Println("***********************************************")
				NodoActual = NodoActual.NodoSiguiente
			}
		}

		//Confirmacion
		var VolverAlMenu string
		fmt.Println("")
		fmt.Println("Estructura Mostrada, ingrese cualquier valor para volver al menú: ")
		fmt.Scanln(&VolverAlMenu)

		VerReportes()

	case 2:
		fmt.Println("")
		fmt.Println("")
		fmt.Println("Cola Estudiantes Pendientes:")

		var NodoActual *Node = ColaEstudiantesPendientes.ObtenerPrimero()
		if ColaEstudiantesPendientes.VerTamaño() == 0 {
			fmt.Println("La Cola está vacia")
		} else {
			for NodoActual != nil {
				fmt.Print("Carnet: -> ", NodoActual.Carnet, ", Nombre: ", NodoActual.Nombre, "\n")
				fmt.Println("***********************************************")
				NodoActual = NodoActual.NodoSiguiente
			}
		}

		//Confirmacion
		var VolverAlMenu string
		fmt.Println("")
		fmt.Println("Estructura Mostrada, ingrese cualquier valor para volver al menú: ")
		fmt.Scanln(&VolverAlMenu)

		VerReportes()

	case 3:
		ListaDobleEstudiantesAprobados.MostarListaDoble()

		//Confirmacion
		var VolverAlMenu string
		fmt.Println("")
		fmt.Println("Estructura Mostrada, ingrese cualquier valor para volver al menú: ")
		fmt.Scanln(&VolverAlMenu)

		VerReportes()
	case 4:
		//Inicializar String
		StringJSON := "{\n"
		StringJSON += "\t\"Alumnos\": [\n"

		//Crear Nodo Auxiliar
		aux := ListaDobleEstudiantesAprobados.Primero

		//Recorrer Lista Doblemente Enlazada
		for aux.NodoSiguiente != nil {

			StringJSON += "\t\t{\n"

			//Datos de Alumno:
			StringJSON += "\t\t\t\"Nombre\": \"" + aux.Nombre + "\", \n"
			StringJSON += "\t\t\t\"Carnet\": " + aux.Carnet + ", \n"
			StringJSON += "\t\t\t\"Password\": \"" + aux.Contraseña + "\", \n"
			StringJSON += "\t\t\t\"Carpeta_Raiz\": \"/\" \n"

			StringJSON += "\t\t},\n"
			aux = aux.NodoSiguiente
		}

		//Esto es para el ultimo elemento
		StringJSON += "\t\t{\n"

		//Datos de Alumno:
		StringJSON += "\t\t\t\"Nombre\": \"" + aux.Nombre + "\", \n"
		StringJSON += "\t\t\t\"Carnet\": " + aux.Carnet + ", \n"
		StringJSON += "\t\t\t\"Password\": \"" + aux.Contraseña + "\", \n"
		StringJSON += "\t\t\t\"Carpeta_Raiz\": \"/\" \n"

		StringJSON += "\t\t}\n"
		StringJSON += "\t]\n"
		StringJSON += "}"

		//Mostrar el StringJSON

		fmt.Print(StringJSON)
		fmt.Println("\n")

		//Crear Archivo JSON
		CrearArchivo()
		EscribirArchivo(StringJSON)

		//Confirmacion
		var VolverAlMenu string
		fmt.Println("")
		fmt.Println("Estructura Mostrada, ingrese cualquier valor para volver al menú: ")
		fmt.Scanln(&VolverAlMenu)

		VerReportes()

	case 5:
		//Confirmacion
		var VolverAlMenu string
		fmt.Println("")
		fmt.Println("Ingrese cualquier valor para volver al menú: ")
		fmt.Scanln(&VolverAlMenu)

		MenuPrincipal()
	}

}
func MenuPrincipal() {
	//Crear el menú principal
	fmt.Println("********** EDD GoDrive **********")
	fmt.Println("*        1.Iniciar Sesion       *")
	fmt.Println("*       2.Ver Reportes          *")
	fmt.Println("*       3.Salir del Sistema     *")
	fmt.Println("*********************************")

	//Recibir Input
	var OpcionMenu int
	fmt.Print("La Opción Ingresada es: ")
	fmt.Scanln(&OpcionMenu)

	switch OpcionMenu {
	case 1:
		//Pedir datos
		var Usuario string
		var Contraseña string

		fmt.Println("Ingrese Usuario: ")
		fmt.Scanln(&Usuario)
		fmt.Println("Ingrese Contraseña: ")
		fmt.Scanln(&Contraseña)

		if Usuario == "Admin" && Contraseña == "Admin" {
			MenuAdministrador()
		}

	case 2:
		//Ver Reportes
		fmt.Println("")
		fmt.Println("")

		VerReportes()

	case 3:
		//Salir del Programa
		os.Exit(0)
	}
}

func main() {
	MenuPrincipal()
}
