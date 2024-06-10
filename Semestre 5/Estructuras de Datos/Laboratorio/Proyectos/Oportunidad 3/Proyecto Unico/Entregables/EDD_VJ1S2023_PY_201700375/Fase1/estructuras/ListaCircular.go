package estructuras

import (
	"fmt"
	"io/ioutil"
	"os"
	"os/exec"
	"strconv"
)

type ListaCircular struct {
	Inicio   *NodoCircular
	Longitud int
}

func (l *ListaCircular) Insertar(id int, nombre string) {
	if l.Longitud == 0 {
		l.Inicio = &NodoCircular{Id: id, Nombre: nombre, siguiente: nil}
		l.Inicio.siguiente = l.Inicio
		l.Longitud++
	} else {
		if l.Longitud == 1 {
			l.Inicio.siguiente = &NodoCircular{Id: id, Nombre: nombre, siguiente: l.Inicio}
			l.Longitud++
		} else {
			aux := l.Inicio
			for i := 0; i < l.Longitud-1; i++ {
				aux = aux.siguiente
			}
			aux.siguiente = &NodoCircular{Id: id, Nombre: nombre, siguiente: l.Inicio}
			l.Longitud++
		}
	}
}

func (l *ListaCircular) Mostrar() {
	aux := l.Inicio
	for i := 0; i < l.Longitud; i++ {
		fmt.Println("idCliente: "+strconv.Itoa(aux.Id)+", Nombre: ", aux.Nombre)
		aux = aux.siguiente
	}
}

func (l *ListaCircular) BuscarPorID(id int) bool {
	var ExisteID bool
	//Ver si la lista está vacia
	if l.Longitud == 0 {
		fmt.Println("La Lista de Clientes está vacía")
	} else {
		//Crear y utilizar Nodo Aux para recorrer la lista

		aux := l.Inicio
		for aux.Id != id {
			aux = aux.siguiente
		}
		if aux == nil { //Si llega a nil es porque no existe
			ExisteID = false
		} else {
			ExisteID = true
		}

	}
	return ExisteID
}

func (l *ListaCircular) GenerarReporte() {
	//Iniciar String GraphViz
	StringGraphViz := "digraph ListaCircularClientes{\n"
	StringGraphViz += "rankdir=LR;\n"
	StringGraphViz += "node[shape = oval];\n"
	StringGraphViz += "Inicio[label=\"Inicio\"];\n"

	//Cuerpo del String GraphViz
	aux := l.Inicio
	contador := 0
	StringGraphViz += "Inicio->nodo0;\n"
	for i := 0; i < l.Longitud; i++ {
		StringGraphViz += "nodo" + strconv.Itoa(i) + "[label=\"ID: " + strconv.Itoa(aux.Id) + ", Nombre: " + aux.Nombre + "\"];\n"
		aux = aux.siguiente
	}
	for i := 0; i < l.Longitud-1; i++ {
		c := i + 1
		StringGraphViz += "nodo" + strconv.Itoa(i) + "->nodo" + strconv.Itoa(c) + ";\n"
		contador = c
	}
	StringGraphViz += "nodo" + strconv.Itoa(contador) + "->Inicio;\n"
	StringGraphViz += "}"

	fmt.Println(StringGraphViz)

	NombreArchivo := "./ListaCircularClientes.dot"
	NombreImagen := "./ListaCircularClientes.jpg"

	l.CrearArchivo(NombreArchivo)
	l.EscribirArchivo(NombreArchivo, StringGraphViz)
	l.EjecutarComandos(NombreArchivo, NombreImagen)

}

func (l *ListaCircular) CrearArchivo(NombreArchivo string) {
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

func (l *ListaCircular) EscribirArchivo(NombreArchivo string, StringGraphViz string) {
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

func (l *ListaCircular) EjecutarComandos(NombreArchivo string, NombreImagen string) {
	path, _ := exec.LookPath("dot")
	cmd, _ := exec.Command(path, "-Tjpg", NombreArchivo).Output()
	mode := 0777
	_ = ioutil.WriteFile(NombreImagen, cmd, os.FileMode(mode))
}
