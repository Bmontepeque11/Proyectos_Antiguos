package estructuras

import (
	"fmt"
	"io/ioutil"
	"os"
	"os/exec"
	"strconv"
	"strings"
)

type Pila struct {
	Primero  *NodoPila
	Longitud int
}

func (p *Pila) Push(IDpedido int, idEmpleado string, IDCliente string, Imagen string) {
	if p.Longitud == 0 {
		nuevoNodo := &NodoPila{IDPedido: IDpedido, IDEmpleadoPedido: idEmpleado, IDClientePedido: IDCliente, NombreImagenPedida: Imagen, Siguiente: nil}
		p.Primero = nuevoNodo
		p.Longitud++
	} else {
		nuevoNodo := &NodoPila{IDPedido: IDpedido, IDEmpleadoPedido: idEmpleado, IDClientePedido: IDCliente, NombreImagenPedida: Imagen, Siguiente: p.Primero}
		p.Primero = nuevoNodo
		p.Longitud++
	}
}

func (p *Pila) Pop() {
	if p.Longitud == 0 {
		fmt.Println("No hay elementos en la pila")
	} else {
		p.Primero = p.Primero.Siguiente
		p.Longitud--
	}
}

func (p *Pila) GenerarReporte() {
	StringGraphViz := "digraph PilaPedidos{\n"
	StringGraphViz += "rankdir=LR;\n"
	StringGraphViz += "node[shape = record]"
	aux := p.Primero
	StringGraphViz += "nodo0 [label=\""
	for i := 0; i < p.Longitud; i++ {
		StringGraphViz = StringGraphViz + "|(IDPedido: " + strconv.Itoa(aux.IDPedido) + ", ID Empleado: " + aux.IDEmpleadoPedido + ", ID Cliente: " + aux.IDClientePedido + ", ImagenPedida: " + aux.NombreImagenPedida + ")"
		aux = aux.Siguiente
	}
	StringGraphViz += "\"]; \n}"

	NombreArchivo := "./PilaPedidos.dot"
	NombreImagen := "./PilaPedidos.jpg"

	fmt.Println(StringGraphViz)

	p.CrearArchivo(NombreArchivo)
	p.EscribirArchivo(NombreArchivo, StringGraphViz)
	p.EjecutarComandos(NombreArchivo, NombreImagen)
}

func (p *Pila) GenerarJSON() {
	//GenerarCodigoDeJSON
	StringJSON := "{\n"
	StringJSON += "\t\"Pedidos\":[\n"
	aux := p.Primero
	for i := 0; i < p.Longitud; i++ {
		StringJSON = StringJSON + "\t{\n\t\"id_pedido\": " + strconv.Itoa(aux.IDPedido) + ",\n \t\"id_empleado\": " + aux.IDEmpleadoPedido + ",\n \t\" id_cliente\": " + aux.IDClientePedido + ",\n \t\"imagen\": \"" + aux.NombreImagenPedida + "\"\n\t},\n"
		aux = aux.Siguiente
	}

	//Quitar la ultima coma
	StringJSON = strings.TrimSuffix(StringJSON, "\n")
	StringJSON = strings.TrimSuffix(StringJSON, ",")

	StringJSON += "\n] \n}"

	NombreArchivoJSON := "./Pedidos.json"
	p.CrearArchivo(NombreArchivoJSON)
	p.EscribirArchivo(NombreArchivoJSON, StringJSON)

}

func (p *Pila) CrearArchivo(NombreArchivo string) {
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

func (p *Pila) EscribirArchivo(NombreArchivo string, StringGraphViz string) {
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

	fmt.Println("Esto se escribio al archivo")
	fmt.Println(StringGraphViz)
	fmt.Println("Archivo escrito")
}

func (p *Pila) EjecutarComandos(NombreArchivo string, NombreImagen string) {
	path, _ := exec.LookPath("dot")
	cmd, _ := exec.Command(path, "-Tjpg", NombreArchivo).Output()
	mode := 0777
	_ = ioutil.WriteFile(NombreImagen, cmd, os.FileMode(mode))
}
