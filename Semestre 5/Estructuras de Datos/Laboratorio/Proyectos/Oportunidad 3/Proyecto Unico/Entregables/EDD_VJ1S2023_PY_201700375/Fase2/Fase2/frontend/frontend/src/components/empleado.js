import React, {useState, useEffect} from 'react';
import '../css/administrador.css'
import 'bootstrap/dist/css/bootstrap.min.css'

export const Empleado = () => {
    const salir = (e) => {
        e.preventDefault();
        console.log("Listo")
        window.open("/","_self")
    }

    const validar = (data) =>{
        console.log(data)
    }

    const aplicarFiltros = async(e) => {
        e.preventDefault();
        console.log("Listo")
        window.open("/filtros","_self")
    }

    const generarFacturas = async(e) => {
        e.preventDefault();
        window.open("/factura","_self")
    }
    
    const verFacturas = async(e) => {
        e.preventDefault();
        window.open("/verfactura","_self")
    }

    return(
        <div className="form-signin1">
            <div className="text-center">
                  <form>
                    <h1 className="h3 mb-3 fw-normal">Dashboard Empleado {localStorage.getItem("empleado")}</h1>
                    <br/>
                    <center><button className="w-100 btn btn-primary" onClick={aplicarFiltros}>Aplicacion de Filtros</button></center>
                    <br/>
                    <center><button className="w-100 btn btn-primary" onClick={generarFacturas}>Generar Facturas</button></center>
                    <br/>
                    <center><button className="w-100 btn btn-primary" onClick={verFacturas}>Ver Facturas</button></center>
                    <br/>
                    <center><button className="w-50 btn btn-danger" onClick={salir}>Salir</button></center>
                    <br/>
                    <br/>
                  </form>
            </div>
          </div>
    );
}