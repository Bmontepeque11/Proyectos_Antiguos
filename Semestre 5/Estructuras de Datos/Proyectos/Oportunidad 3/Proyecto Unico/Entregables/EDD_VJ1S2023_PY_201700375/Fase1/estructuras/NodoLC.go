package estructuras

type NodoCircular struct {
	Id        int
	Nombre    string
	siguiente *NodoCircular
}
