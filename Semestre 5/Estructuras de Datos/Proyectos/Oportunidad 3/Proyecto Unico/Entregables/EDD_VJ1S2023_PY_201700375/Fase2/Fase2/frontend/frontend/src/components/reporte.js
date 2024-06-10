import React, {useState, useEffect} from 'react';
import '../css/administrador.css'
import 'bootstrap/dist/css/bootstrap.min.css'

export const Reportes = () => {
    const [imagen, setImagen] = useState('https://www.ledr.com/colours/white.html')
    const salir = (e) => {
        e.preventDefault();
        console.log("Listo")
        window.open("/admin","_self")
    }

    const validar = (data) =>{
        console.log(data)
        setImagen(data.imagen.Imagenbase64)
    }

    const reporteGrafo = async(e) => {
        e.preventDefault();
        fetch('http://localhost:3001/reporte-grafo',{
        })
        .then(response => response.json())
        .then(data => validar(data));
    }

    const reporteArbol = async(e) => {
        e.preventDefault();
        fetch('http://localhost:3001/reporte-arbol',{
        })
        .then(response => response.json())
        .then(data => validar(data));
    }

    const reporteBlockchain = async(e) => {
        e.preventDefault();
        fetch('http://localhost:3001/reporte-bloque',{
        })
        .then(response => response.json())
        .then(data => validar(data));
    }

    return(
        <div className="form-signin1">
            <div className="text-center">
                  <form>
                    <h1 className="h3 mb-3 fw-normal">Reportes Administrador</h1>
                    <br/>
                    <center><button className="w-100 btn btn-primary" onClick={reporteArbol}>Arbol AVL</button></center>
                    <br/>
                    <center><button className="w-50 btn btn-danger" onClick={salir}>Salir</button></center>
                    <br/>
                    <img src={imagen} width="350" height="350" align="right" alt='reporte en blanco :)'  />
                    <br/>
                    <br/>
                  </form>
            </div>
          </div>
    );
}