package estructuras

import (
	"fmt"
	"io/ioutil"
	"os"
	"os/exec"
	"strconv"
)

type Cola struct {
	Primero  *NodoCola
	Longitud int
}

func (c *Cola) Encolar(id string, nombre string) {
	if c.Longitud == 0 {
		nuevoNodo := &NodoCola{Id: id, Nombre: nombre}
		c.Primero = nuevoNodo
		c.Longitud++
	} else {
		nuevoNodo := &NodoCola{Id: id, Nombre: nombre}
		aux := c.Primero
		for aux.Siguiente != nil {
			aux = aux.Siguiente
		}
		aux.Siguiente = nuevoNodo
		c.Longitud++
	}
}

func (c *Cola) Descolar() NodoCola {
	var ObjetoDevuelto NodoCola
	if c.Longitud == 0 {
		fmt.Println("Cola Vacia")
	} else {

		//Preparar Objeto para devolver:
		ObjetoDevuelto.Id = c.Primero.Id
		ObjetoDevuelto.Nombre = c.Primero.Nombre
		ObjetoDevuelto.Siguiente = nil

		c.Primero = c.Primero.Siguiente
		c.Longitud--
	}
	return ObjetoDevuelto
}

func (c *Cola) Mostrar() {
	aux := c.Primero

	for aux != nil {
		fmt.Println("id: " + aux.Id + ", Nombre: " + aux.Nombre)
		aux = aux.Siguiente
	}
}

func (c *Cola) Graficar() {
	texto := "digraph cola{\n"
	texto += "rankdir=LR;\n"
	texto += "node[shape = record];\n"
	texto += "nodonull2[label=\"null\"];\n"
	aux := c.Primero
	contador := 0
	for i := 0; i < c.Longitud; i++ {
		texto = texto + "nodo" + strconv.Itoa(i) + "[label=\"{Nombre: " + aux.Id + ", Carnet: " + aux.Nombre + "|}\"];\n"
		aux = aux.Siguiente
	}
	for i := 0; i < c.Longitud-1; i++ {
		c := i + 1
		texto += "nodo" + strconv.Itoa(i) + "->nodo" + strconv.Itoa(c) + ";\n"
		contador = c
	}
	texto += "nodo" + strconv.Itoa(contador) + "->nodonull2;\n"
	texto += "}"

}

func (c *Cola) GenerarReporte() {
	//Iniciar String GraphViz
	StringGraphViz := "digraph ColaClientesActuales{\n"
	StringGraphViz += "rankdir=LR;\n"
	StringGraphViz += "node[shape = record];\n"

	//Cuerpo del String GraphViz
	aux := c.Primero
	//contador := 0
	StringGraphViz += "Primero->nodo0;\n"
	for i := 0; i < c.Longitud; i++ {
		StringGraphViz += "nodo" + strconv.Itoa(i) + "[label=\"ID: " + aux.Id + ", Nombre: " + aux.Nombre + "\"];\n"
		aux = aux.Siguiente
	}
	for i := 0; i < c.Longitud-1; i++ {
		c := i + 1
		StringGraphViz += "nodo" + strconv.Itoa(i) + "->nodo" + strconv.Itoa(c) + ";\n"
		//contador = c
	}
	StringGraphViz += "}"

	fmt.Println(StringGraphViz)

	NombreArchivo := "./ColaClientesActuales.dot"
	NombreImagen := "./ColaClientesActuales.jpg"

	c.CrearArchivo(NombreArchivo)
	c.EscribirArchivo(NombreArchivo, StringGraphViz)
	c.EjecutarComandos(NombreArchivo, NombreImagen)
}

func (c *Cola) CrearArchivo(NombreArchivo string) {
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

func (c *Cola) EscribirArchivo(NombreArchivo string, StringGraphViz string) {
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

func (c *Cola) EjecutarComandos(NombreArchivo string, NombreImagen string) {
	path, _ := exec.LookPath("dot")
	cmd, _ := exec.Command(path, "-Tjpg", NombreArchivo).Output()
	mode := 0777
	_ = ioutil.WriteFile(NombreImagen, cmd, os.FileMode(mode))
}
