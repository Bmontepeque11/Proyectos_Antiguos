package estructuras

type NodoPila struct {
	IDPedido           int
	IDEmpleadoPedido   string
	IDClientePedido    string
	NombreImagenPedida string
	Siguiente          *NodoPila
}
