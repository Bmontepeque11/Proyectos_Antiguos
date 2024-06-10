package estructuras

import (
	"fmt"
	"io/ioutil"
	"os"
	"os/exec"
	"strconv"
)

type ListaSimple struct {
	Inicio   *NodoSimple
	Longitud int
}

func (l *ListaSimple) estaVacia() bool {
	return l.Longitud == 0
}

func (l *ListaSimple) Insertar(id int, nombre string, cargo string, contraseña string) {
	if l.estaVacia() {
		l.Inicio = &NodoSimple{Id: id, Nombre: nombre, Cargo: cargo, Contraseña: contraseña, siguiente: nil}
		l.Longitud++
	} else {
		aux := l.Inicio
		for aux.siguiente != nil {
			aux = aux.siguiente
		}
		aux.siguiente = &NodoSimple{Id: id, Nombre: nombre, Cargo: cargo, Contraseña: contraseña, siguiente: nil}
		l.Longitud++
	}
}

func (l *ListaSimple) Mostrar() {
	aux := l.Inicio

	for aux != nil {
		fmt.Println("idEmpleado: " + strconv.Itoa(aux.Id) + ", Nombre: " + aux.Nombre + ", Cargo: " + aux.Cargo + ", Contraseña: " + aux.Contraseña)
		aux = aux.siguiente
	}
}

func (l *ListaSimple) BuscarPorID(id int) NodoSimple {
	//Declarar Objeto Devuelto
	var EmpleadoDevuelto NodoSimple

	//Ver si la lista está vacia
	if l.Longitud == 0 {
		fmt.Println("La Lista de Empleados está vacía")
		//Asignar valores al nodo empleado devuelto
		EmpleadoDevuelto.Id = 0
		EmpleadoDevuelto.Nombre = ""
		EmpleadoDevuelto.Cargo = ""
		EmpleadoDevuelto.Contraseña = ""
	} else {
		//Crear y utilizar Nodo Aux para recorrer la lista

		aux := l.Inicio
		for aux.Id != id {
			aux = aux.siguiente
		}

		//Asignar valores al nodo empleado devuelto
		EmpleadoDevuelto.Id = aux.Id
		EmpleadoDevuelto.Nombre = aux.Nombre
		EmpleadoDevuelto.Cargo = aux.Cargo
		EmpleadoDevuelto.Contraseña = aux.Contraseña
	}

	return EmpleadoDevuelto
}

func (l *ListaSimple) GenerarReporte() {
	//Iniciar String GraphViz
	StringGraphViz := "digraph ListaSimpleEmpleados{\n"
	StringGraphViz += "rankdir=LR;\n"
	StringGraphViz += "node[shape = oval];\n"
	StringGraphViz += "Inicio[label=\"Inicio\"];\n"
	StringGraphViz += "Fin[label=\"Fin\"];\n"

	//Cuerpo del String GraphViz
	aux := l.Inicio
	contador := 0
	StringGraphViz += "Inicio->nodo0;\n"
	for i := 0; i < l.Longitud; i++ {
		StringGraphViz += "nodo" + strconv.Itoa(i) + "[label=\"ID: " + strconv.Itoa(aux.Id) + ", Nombre: " + aux.Nombre + ", Cargo: " + aux.Cargo + ", Contraseña: " + aux.Contraseña + "\"];\n"
		aux = aux.siguiente
	}
	for i := 0; i < l.Longitud-1; i++ {
		c := i + 1
		StringGraphViz += "nodo" + strconv.Itoa(i) + "->nodo" + strconv.Itoa(c) + ";\n"
		contador = c
	}
	StringGraphViz += "nodo" + strconv.Itoa(contador) + "->Fin;\n"
	StringGraphViz += "}"

	fmt.Println(StringGraphViz)

	NombreArchivo := "./ListaSimpleEmpleados.dot"
	NombreImagen := "./ListaSimpleEmpleados.jpg"

	l.CrearArchivo(NombreArchivo)
	l.EscribirArchivo(NombreArchivo, StringGraphViz)
	l.EjecutarComandos(NombreArchivo, NombreImagen)

}

func (l *ListaSimple) CrearArchivo(NombreArchivo string) {
	var _, err = os.Stat(NombreArchivo)

	if os.IsNotExist(err) {
		var file, err = os.Create(NombreArchivo)
		if err != nil {
			return
		}
		defer file.Close()
	}
	fmt.Println("Archivo creado")
}

func (l *ListaSimple) EscribirArchivo(NombreArchivo string, StringGraphViz string) {
	var file, err = os.OpenFile(NombreArchivo, os.O_RDWR, 0644)
	if err != nil {
		fmt.Print("Hubo un error escribiendo el archivo")
		return
	}
	defer file.Close()
	_, err = file.WriteString(StringGraphViz)
	if err != nil {
		return
	}
	err = file.Sync()
	if err != nil {
		return
	}
	fmt.Println("Archivo escrito")
}

func (l *ListaSimple) EjecutarComandos(NombreArchivo string, NombreImagen string) {
	path, _ := exec.LookPath("dot")
	cmd, _ := exec.Command(path, "-Tjpg", NombreArchivo).Output()
	mode := 0777
	_ = ioutil.WriteFile(NombreImagen, cmd, os.FileMode(mode))
}
