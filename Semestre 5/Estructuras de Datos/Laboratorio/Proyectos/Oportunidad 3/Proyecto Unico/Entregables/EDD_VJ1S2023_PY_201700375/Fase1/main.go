package main

import (
	"EDD_VJ1S2023_PY_201700375/Fase1/estructuras"
	"encoding/csv"
	"fmt"
	"io"
	"math/rand"
	"os"
	"strconv"
)

//######################################
//#Variables Globales				   #
//######################################

// Declarar la Lista Simple
var LSEmpleados = &estructuras.ListaSimple{Inicio: nil, Longitud: 0}

// Declarar la Lista Circular
var LCClientes = &estructuras.ListaCircular{Inicio: nil, Longitud: 0}

// Declarar la Lista Doble
var LDImagenes = &estructuras.ListaDoble{Cabeza: nil, Longitud: 0}

// Declarar la Cola de Clientes Actuales
var CClientesActuales = &estructuras.Cola{Primero: nil, Longitud: 0}

// Declarar la Matriz Dispersa de Imagen
var MDImagen = &estructuras.Matriz{Raiz: &estructuras.NodoMatriz{PosX: -1, PosY: -1, Color: "RAIZ"}}

// Declarar la Matriz Dispersa de Imagen para el CSV
var MDImagenCSV = &estructuras.Matriz{Raiz: &estructuras.NodoMatriz{PosX: -1, PosY: -1, Color: "RAIZ"}}

// Declarar Pila de Pedidos
var PPedidos = &estructuras.Pila{Primero: nil, Longitud: 0}

// Declarar Empleado que Inicio Sesion
var IDEmpleadoLoggueado string
var NombreEmpleadoLoggueado string
var CargoEmpleadoLoggueado string
var ContraseñaEmpleadoLoggueado string

// Declarar Cliente que va a ser desencolado
var IDClienteDesencolado string
var NombreClienteDesencolado string

// Declarar ID de Pedido del Dia
var IDPedido int

// Declarar Imagen que pidio el cliente:
var ImagenSeleccionadaCliente string

func CargarEmpleados() {
	//Pedir la Ruta del Archivo CSV
	var RutaEmpleados string
	fmt.Print("La Ruta Ingresada es: ")
	fmt.Scanln(&RutaEmpleados)

	//Leer Archivo de Empleados.csv
	file, err := os.Open(RutaEmpleados)
	if err != nil {
		fmt.Println("No pude abrir el archivo")
		return
	}
	defer file.Close() //Para que se cierre al dejarlo de utilizar

	lector := csv.NewReader(file)
	lector.Comma = ','
	encabezado := true //Es porque aveces los CSV traen encabezado y con esto podemos quitarlo
	for {
		linea, err := lector.Read() //Read lee como arrreglo, ReadAll lee como matriz
		if err == io.EOF {
			break
		}
		if err != nil {
			fmt.Println("No pude leer esta linea del csv :´v")
			continue
		}
		if encabezado {
			encabezado = false
			continue
		}

		//Mostrar lo que va leyendo el programa
		//fmt.Println("Esta es la lìnea del csv leida: ")
		//1fmt.Println("ID: " + linea[0] + "Nombre: " + linea[1] + "Cargo: " + linea[2] + "Contraseña: " + linea[3])

		//Convertir id de String a Int
		idEmpleado := linea[0]
		idEmpleadoint, err := strconv.Atoi(idEmpleado)

		//Insertar en la lista Simple
		LSEmpleados.Insertar(idEmpleadoint, linea[1], linea[2], linea[3])

	}
	fmt.Println("")
	fmt.Println("")

	//Mostrar Lista:
	fmt.Println("Esto hay en la Lista Simple: ")
	LSEmpleados.Mostrar()

	fmt.Println("")
	//Confirmacion
	var VolverAlMenuAdmin string
	fmt.Println("")
	fmt.Println("Archivo Empleados.csv guarado en la Lista Simple, ingrese cualquier valor para volver al menú de Admin: ")
	fmt.Scanln(&VolverAlMenuAdmin)

	MenuAdmin()
}

func CargarImagenes() {
	//Pedir la Ruta del Archivo CSV
	var RutaEmpleados string
	fmt.Print("La Ruta Ingresada es: ")
	fmt.Scanln(&RutaEmpleados)

	//Leer Archivo de Empleados.csv
	file, err := os.Open(RutaEmpleados)
	if err != nil {
		fmt.Println("No pude abrir el archivo")
		return
	}
	defer file.Close() //Para que se cierre al dejarlo de utilizar

	lector := csv.NewReader(file)
	lector.Comma = ','
	encabezado := true //Es porque aveces los CSV traen encabezado y con esto podemos quitarlo
	for {
		linea, err := lector.Read() //Read lee como arrreglo, ReadAll lee como matriz
		if err == io.EOF {
			break
		}
		if err != nil {
			fmt.Println("No pude leer esta linea del csv :´v")
			continue
		}
		if encabezado {
			encabezado = false
			continue
		}

		//Convertir id de String a Int
		Capas := linea[1]
		CapasInt, err := strconv.Atoi(Capas)

		//Insertar en la lista Simple
		LDImagenes.InsertarAlFinal(linea[0], CapasInt)

	}
	fmt.Println("")
	fmt.Println("")

	//Mostrar Lista:
	fmt.Println("Esto hay en la Lista Doble: ")
	LDImagenes.Mostrar()

	fmt.Println("")
	//Confirmacion
	var VolverAlMenuAdmin string
	fmt.Println("")
	fmt.Println("Archivo Imagenes.csv guarado en la Lista Doble, ingrese cualquier valor para volver al menú de Admin: ")
	fmt.Scanln(&VolverAlMenuAdmin)

	MenuAdmin()

}

func CargarClientes() {
	//Pedir la Ruta del Archivo CSV
	var RutaEmpleados string
	fmt.Print("La Ruta Ingresada es: ")
	fmt.Scanln(&RutaEmpleados)

	//Leer Archivo de Empleados.csv
	file, err := os.Open(RutaEmpleados)
	if err != nil {
		fmt.Println("No pude abrir el archivo")
		return
	}
	defer file.Close() //Para que se cierre al dejarlo de utilizar

	lector := csv.NewReader(file)
	lector.Comma = ','
	encabezado := true //Es porque aveces los CSV traen encabezado y con esto podemos quitarlo
	for {
		linea, err := lector.Read() //Read lee como arrreglo, ReadAll lee como matriz
		if err == io.EOF {
			break
		}
		if err != nil {
			fmt.Println("No pude leer esta linea del csv :´v")
			continue
		}
		if encabezado {
			encabezado = false
			continue
		}

		//Convertir id de String a Int
		idCliente := linea[0]
		idClienteInt, err := strconv.Atoi(idCliente)

		//Insertar en la lista Simple
		LCClientes.Insertar(idClienteInt, linea[1])

	}
	fmt.Println("")
	fmt.Println("")

	//Mostrar Lista:
	fmt.Println("Esto hay en la Lista Circular: ")
	LCClientes.Mostrar()

	fmt.Println("")
	//Confirmacion
	var VolverAlMenuAdmin string
	fmt.Println("")
	fmt.Println("Archivo Clientes.csv guarado en la Lista Circular, ingrese cualquier valor para volver al menú de Admin: ")
	fmt.Scanln(&VolverAlMenuAdmin)

	MenuAdmin()
}

func CargarColaActual() {
	//Pedir la Ruta del Archivo CSV
	var RutaEmpleados string
	fmt.Print("La Ruta Ingresada es: ")
	fmt.Scanln(&RutaEmpleados)

	//Leer Archivo de Empleados.csv
	file, err := os.Open(RutaEmpleados)
	if err != nil {
		fmt.Println("No pude abrir el archivo")
		return
	}
	defer file.Close() //Para que se cierre al dejarlo de utilizar

	lector := csv.NewReader(file)
	lector.Comma = ','
	encabezado := true //Es porque aveces los CSV traen encabezado y con esto podemos quitarlo
	for {
		linea, err := lector.Read() //Read lee como arrreglo, ReadAll lee como matriz
		if err == io.EOF {
			break
		}
		if err != nil {
			fmt.Println("No pude leer esta linea del csv :´v")
			continue
		}
		if encabezado {
			encabezado = false
			continue
		}

		//Insertar en la lista Simple
		CClientesActuales.Encolar(linea[0], linea[1])

	}
	fmt.Println("")
	fmt.Println("")

	//Mostrar Lista:
	fmt.Println("Esto hay en la Cola: ")
	CClientesActuales.Mostrar()

	fmt.Println("")
	//Confirmacion
	var VolverAlMenuAdmin string
	fmt.Println("")
	fmt.Println("Archivo Clientes_Cola.csv guarado en la Cola, ingrese cualquier valor para volver al menú de Admin: ")
	fmt.Scanln(&VolverAlMenuAdmin)

	MenuAdmin()
}

func GenerarReportes() {
	//Crear lineas en blanco para mayor orden:
	fmt.Println("")
	fmt.Println("")

	//Crear el Menú del Administrador
	fmt.Println("Seleccione la estructura de la que quiere ver el reporte: ")
	fmt.Println("1) LS Empleados")
	fmt.Println("2) LD Imagenes")
	fmt.Println("3) LC Clientes")
	fmt.Println("4) Cola Clientes Actuales")
	fmt.Println("5) Pila Pedidos")
	fmt.Println("6) Volver a Menù Admin")

	//Pedir la opción del menú
	var OpcionMenu int
	fmt.Print("La Opción Ingresada es: ")
	fmt.Scanln(&OpcionMenu)

	switch OpcionMenu {

	case 1: //Cargar CSV Empleados:
		LSEmpleados.GenerarReporte()

		fmt.Println("")
		//Confirmacion
		var VolverAlMenuAdmin string
		fmt.Println("")
		fmt.Println("Reporte Generado, Ingrese cualquier valor para volver al menú de Admin: ")
		fmt.Scanln(&VolverAlMenuAdmin)
		MenuAdmin()
	case 2:
		LDImagenes.GenerarReporte()

		fmt.Println("")
		//Confirmacion
		var VolverAlMenuAdmin string
		fmt.Println("")
		fmt.Println("Reporte Generado, Ingrese cualquier valor para volver al menú de Admin: ")
		fmt.Scanln(&VolverAlMenuAdmin)
		MenuAdmin()
	case 3:
		LCClientes.GenerarReporte()

		fmt.Println("")
		//Confirmacion
		var VolverAlMenuAdmin string
		fmt.Println("")
		fmt.Println("Reporte Generado, Ingrese cualquier valor para volver al menú de Admin: ")
		fmt.Scanln(&VolverAlMenuAdmin)
		MenuAdmin()
	case 4:
		CClientesActuales.GenerarReporte()

		fmt.Println("")
		//Confirmacion
		var VolverAlMenuAdmin string
		fmt.Println("")
		fmt.Println("Reporte Generado, Ingrese cualquier valor para volver al menú de Admin: ")
		fmt.Scanln(&VolverAlMenuAdmin)
		MenuAdmin()
	case 5:
		PPedidos.GenerarReporte()
		fmt.Println("")
		//Confirmacion
		var VolverAlMenuAdmin string
		fmt.Println("")
		fmt.Println("Reporte Generado, Ingrese cualquier valor para volver al menú de Admin: ")
		fmt.Scanln(&VolverAlMenuAdmin)
		MenuAdmin()
	case 6:
		fmt.Println("")
		//Confirmacion
		var VolverAlMenuAdmin string
		fmt.Println("")
		fmt.Println("Ingrese cualquier valor para volver al menú de Admin: ")
		fmt.Scanln(&VolverAlMenuAdmin)

		MenuAdmin()
	}

}

func MenuAdmin() {

	//Crear lineas en blanco para mayor orden:
	fmt.Println("")
	fmt.Println("")

	fmt.Println("Soy Admin B), ha iniciado sesión")

	//Crear el Encabezado
	for i := 0; i < 10; i++ {
		fmt.Print("-")
	}

	fmt.Print("Dashboard Administrador 201700375")

	for i := 0; i < 10; i++ {
		fmt.Print("-")
	}

	//Crear el Menú del Administrador
	fmt.Println("")
	fmt.Println("1) Cargar Empleados")
	fmt.Println("2) Cargar Imagenes")
	fmt.Println("3) Cargar Clientes")
	fmt.Println("4) Cargar Cola Actual")
	fmt.Println("5) Reportes Estructuras")
	fmt.Println("6) Cerrar Sesión")

	//Pedir la opción del menú
	var OpcionMenu int
	fmt.Print("La Opción Ingresada es: ")
	fmt.Scanln(&OpcionMenu)

	switch OpcionMenu {

	case 1: //Cargar CSV Empleados:
		CargarEmpleados()
	case 2:
		CargarImagenes()
	case 3:
		CargarClientes()
	case 4:
		CargarColaActual()
	case 5:
		GenerarReportes()
	case 6:
		//Salir del Programa
		Login()
	}

}

func MenuEmpleado() {
	//Crear lineas en blanco para mayor orden:
	fmt.Println("")
	fmt.Println("")

	fmt.Println(NombreEmpleadoLoggueado + ", ha iniciado sesión")

	//Crear el Encabezado
	for i := 0; i < 10; i++ {
		fmt.Print("-")
	}

	fmt.Print("EDD Creative " + IDEmpleadoLoggueado)

	for i := 0; i < 10; i++ {
		fmt.Print("-")
	}

	//Crear el Menú del Administrador
	fmt.Println("")
	fmt.Println("1) Ver Imagenes")
	fmt.Println("2) Realizar Pedido")
	fmt.Println("3) Cerrar Sesión")

	//Pedir la opción del menú
	var OpcionMenu int
	fmt.Print("La Opción Ingresada es: ")
	fmt.Scanln(&OpcionMenu)

	switch OpcionMenu {

	case 1:
		fmt.Println("")
		LDImagenes.Mostrar()

		//Pedir la opción del menú

		fmt.Print("Ingrese nombre de la imagen que desea ver: ")
		fmt.Scanln(&ImagenSeleccionadaCliente)

		//Generar el HTML y CSS de la matriz
		fmt.Println("Leyendo csv e insertando todo en la Matriz: ")
		MDImagenCSV.LeerInicial("csv/"+ImagenSeleccionadaCliente+"/inicial.csv", ImagenSeleccionadaCliente)

		fmt.Println("\nGenerar el Reporte de la Matriz: ")
		MDImagenCSV.Reporte()

		fmt.Println("\nGenerar HTML de Imagen: ")
		MDImagenCSV.GenerarImagen(ImagenSeleccionadaCliente)

		fmt.Println("\nImagen Seleccionada y html generado, ingrese cualquier valor para volver al menú de empleado")
		MenuEmpleado()
	case 2:
		fmt.Println("")
		fmt.Println("")

		fmt.Println("Desencolando Cliente de la Cola de Clientes Actuales")

		var ClienteDesencolado = estructuras.NodoCola{Id: "", Nombre: ""}

		ClienteDesencolado = CClientesActuales.Descolar()

		//Asignar los valores del nodo devuelto a las variables globales
		IDClienteDesencolado = ClienteDesencolado.Id
		NombreClienteDesencolado = ClienteDesencolado.Nombre

		//Generar Nuevo ID de Cliente e Insertar en Cola de Clientes si es necesario:
		if IDClienteDesencolado == "X" {
			fmt.Println("Usuario sin ID Detectado, generando ID")
			NuevoID := rand.Intn(1000) + 86744 //El nùmero viene de Toyota 86 y Lmaborghini Revuelto (LB744)
			IDClienteDesencolado = strconv.Itoa(NuevoID)

			fmt.Println("Aqui empezamos a buscar si el id existe")
			//if LCClientes.BuscarPorID(NuevoID) == false {
			//	LCClientes.Insertar(NuevoID, NombreClienteDesencolado)
			//	fmt.Println("Cliente Registrado en la Lista Circular")
			//} else {
			//	fmt.Println("ID ya existía pero ya lo arreglé xd")
			//	NuevoID++
			//	LCClientes.Insertar(NuevoID, NombreClienteDesencolado)
			//}

			LCClientes.Insertar(NuevoID, NombreClienteDesencolado)

		} else {
			//Convertir id de String a Int

			IDClienteDesencoladoInt, err := strconv.Atoi(IDClienteDesencolado)

			if err != nil {
				fmt.Println("Error al convertir de string a int :(")
			}

			//Insertar en la lista Circular
			LCClientes.Insertar(IDClienteDesencoladoInt, NombreClienteDesencolado)
			fmt.Println("Cliente Registrado en la Lista Circular")
		}

		//GenerarPedido
		IDPedido++
		PPedidos.Push(IDPedido, IDEmpleadoLoggueado, IDClienteDesencolado, ImagenSeleccionadaCliente)

		//GenerarArchivoJSON
		PPedidos.GenerarJSON()

		//Confirmacion
		var VolverAlMenuAdmin string
		fmt.Println("")
		fmt.Println("Pedido generado, Ingrese cualquier valor para volver al menú de Empleado: ")
		fmt.Scanln(&VolverAlMenuAdmin)

		MenuEmpleado()

	case 3:
		//Salir del Programa
		Login()
	}
}

func Login() {
	//Crear lineas en blanco para mayor orden:
	fmt.Println("")
	fmt.Println("")

	//Actualizar Variables Globales
	IDEmpleadoLoggueado = ""
	NombreEmpleadoLoggueado = ""
	CargoEmpleadoLoggueado = ""
	ContraseñaEmpleadoLoggueado = ""

	//Crear el Encabezado del Login
	for i := 0; i < 10; i++ {
		fmt.Print("-")
	}

	fmt.Print("Login")

	for i := 0; i < 10; i++ {
		fmt.Print("-")
	}

	//Crear el Menú del Login
	fmt.Println("")
	fmt.Println("1) Iniciar Sesion")
	fmt.Println("2) Salir del Sistema")

	//Pedir la opción del menú
	var OpcionMenu int
	fmt.Print("La Opción Ingresada es: ")
	fmt.Scanln(&OpcionMenu)

	switch OpcionMenu {

	case 1:
		//Crear lineas en blanco para mayor orden:
		fmt.Println("")
		fmt.Println("")

		//Pedir Datos para ingresar:

		//NombreUsuario
		var Usuario string
		fmt.Println("")
		fmt.Print("Ingrese el id del Usuario: ")
		fmt.Scanln(&Usuario)

		//Contraseña
		var Contraseña string
		fmt.Println("")
		fmt.Print("Ingrese la contraseña del Usuario: ")
		fmt.Scanln(&Contraseña)

		if Usuario == "ADMIN_201700375" && Contraseña == "Admin" {
			MenuAdmin()
		} else {

			//Convertir id de String a Int
			UsuarioInt, err := strconv.Atoi(Usuario)

			//Para manejar el error:
			if err != nil {
				fmt.Println("F Bro, hubo un error :(")
			}

			var EmpleadoIntentoLog = LSEmpleados.BuscarPorID(UsuarioInt)

			if UsuarioInt == EmpleadoIntentoLog.Id && Contraseña == EmpleadoIntentoLog.Contraseña {
				//Actualizar Variables Globales
				IDEmpleadoLoggueado = Usuario
				NombreEmpleadoLoggueado = EmpleadoIntentoLog.Nombre
				CargoEmpleadoLoggueado = EmpleadoIntentoLog.Cargo
				ContraseñaEmpleadoLoggueado = EmpleadoIntentoLog.Contraseña

				fmt.Println("Empleado: " + strconv.Itoa(EmpleadoIntentoLog.Id) + " ha Iniciado Sesion.")
				MenuEmpleado()
			} else {
				//Confirmacion
				var VolverAlLogin string
				fmt.Println("")
				fmt.Println("Datos Incorrectos, ingrese cualquier valor para intentar de nuevo: ")
				fmt.Scanln(&VolverAlLogin)
				Login()
			}
		}

		Login()

	case 2:
		//Salir del Programa
		os.Exit(0)
	}

}

func main() {
	Login()

	//Inicializar ID Pedido
	IDPedido = 0

}
