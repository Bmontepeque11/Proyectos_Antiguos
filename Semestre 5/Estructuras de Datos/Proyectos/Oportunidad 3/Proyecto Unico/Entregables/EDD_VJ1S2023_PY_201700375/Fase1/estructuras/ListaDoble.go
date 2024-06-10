package estructuras

import (
	"fmt"
	"io/ioutil"
	"os"
	"os/exec"
	"strconv"
)

type ListaDoble struct {
	Cabeza   *NodoDoble
	Cola     *NodoDoble
	Longitud int
}

func (l *ListaDoble) estaVacia() bool {
	return l.Longitud == 0
}

func (l *ListaDoble) InsertarAlFinal(nombre string, capas int) {
	//Crear Nodo Auxiliar:
	var aux = &NodoDoble{}
	aux.Nombre = nombre
	aux.Capas = capas
	aux.siguiente = nil
	aux.anterior = nil

	if l.Cabeza == nil && l.Cola == nil {
		l.Cabeza = aux
		l.Cola = aux
	} else {
		aux.anterior = l.Cola
		l.Cola.siguiente = aux
		l.Cola = aux
	}
	l.Longitud++

}

func (l *ListaDoble) Mostrar() {
	aux := l.Cabeza
	//Para mostrar la lista en el menu de empleados:
	i := 0
	for aux != nil {
		fmt.Println(strconv.Itoa(i+1) + ") Nombre: " + aux.Nombre + ", Capas: " + strconv.Itoa(aux.Capas))
		aux = aux.siguiente
		i += 1
	}
}

func (l *ListaDoble) GenerarReporte() {
	//Iniciar String GraphViz
	StringGraphViz := "digraph ListaDobleImagenes{\n"
	StringGraphViz += "rankdir=LR;\n"
	StringGraphViz += "node[shape = oval];\n"
	StringGraphViz += "Inicio[label=\"Inicio\"];\n"
	StringGraphViz += "Fin[label=\"Fin\"];\n"

	//Cuerpo del String GraphViz
	aux := l.Cabeza
	contador := 0
	StringGraphViz += "Inicio->nodo0 [dir=back];\n"
	for i := 0; i < l.Longitud; i++ {
		StringGraphViz += "nodo" + strconv.Itoa(i) + "[label=\"" + aux.Nombre + ", Capas: " + strconv.Itoa(aux.Capas) + "\"];\n"
		aux = aux.siguiente
	}
	for i := 0; i < l.Longitud-1; i++ {
		c := i + 1
		StringGraphViz += "nodo" + strconv.Itoa(i) + "->nodo" + strconv.Itoa(c) + ";\n"
		StringGraphViz += "nodo" + strconv.Itoa(c) + "->nodo" + strconv.Itoa(i) + ";\n"
		contador = c
	}
	StringGraphViz += "nodo" + strconv.Itoa(contador) + "->Fin;\n"
	StringGraphViz += "}"

	fmt.Println(StringGraphViz)

	NombreArchivo := "./ListaDobleImagenes.dot"
	NombreImagen := "./ListaDobleImagenes.jpg"

	l.CrearArchivo(NombreArchivo)
	l.EscribirArchivo(NombreArchivo, StringGraphViz)
	l.EjecutarComandos(NombreArchivo, NombreImagen)

}

func (l *ListaDoble) CrearArchivo(NombreArchivo string) {
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

func (l *ListaDoble) EscribirArchivo(NombreArchivo string, StringGraphViz string) {
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

func (l *ListaDoble) EjecutarComandos(NombreArchivo string, NombreImagen string) {
	path, _ := exec.LookPath("dot")
	cmd, _ := exec.Command(path, "-Tjpg", NombreArchivo).Output()
	mode := 0777
	_ = ioutil.WriteFile(NombreImagen, cmd, os.FileMode(mode))
}
