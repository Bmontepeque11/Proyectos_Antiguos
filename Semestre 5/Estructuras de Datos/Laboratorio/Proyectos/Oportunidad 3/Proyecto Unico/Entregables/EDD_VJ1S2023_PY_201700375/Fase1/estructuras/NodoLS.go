package estructuras

type NodoSimple struct {
	Id         int
	Nombre     string
	Cargo      string
	Contraseña string
	siguiente  *NodoSimple
}
