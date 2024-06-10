package estructuras

type NodoDoble struct {
	Nombre    string
	Capas     int
	siguiente *NodoDoble
	anterior  *NodoDoble
}
