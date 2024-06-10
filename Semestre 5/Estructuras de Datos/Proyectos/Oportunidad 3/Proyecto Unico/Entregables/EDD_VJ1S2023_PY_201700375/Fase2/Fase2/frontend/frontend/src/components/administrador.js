import React, {useState, useEffect} from 'react';
import '../css/administrador.css'
import 'bootstrap/dist/css/bootstrap.min.css'

export const Administrador = () => {
    const [mensajes, setMensajes] = useState([]);
    const reportes = (e) => {
        e.preventDefault();
        console.log("Listo")
        window.open("/reportes","_self")
    }

    const onChange = (e) => {
        e.preventDefault();
        var reader = new FileReader();
        reader.onload = (e) => {
            var obj = JSON.parse(e.target.result);
            console.log(obj.pedidos)
            fetch('http://localhost:3001/cargarpedidos',{
                method: 'POST',
                body: JSON.stringify({
                    Pedidos: obj.pedidos
                }),
                headers:{
                    'Content-Type': 'application/json'
                }
            })
            .then(response => response.json())
            .then(data => validar(data))
        };
        reader.readAsText(e.target.files[0]);
    }

    const onChange1 = (e, file1) => {
        var file = file1 || e.target.files[0];
        console.log(file.name);
        fetch('http://localhost:3001/cargarempleados',{
            method: 'POST',
            body: JSON.stringify({
                Nombre: file.name
            }),
            headers:{
                'Content-Type': 'application/json'
            }
        })
        .then(response => response.json())
        .then(data => validar(data))
    }

    const validar = (data) => {
        console.log(data)
    }

    const salir = (e) => {
        e.preventDefault();
        console.log("Listo")
        window.open("/","_self")
    }

    return(
        <div className="form-signin1">
            <div className="text-left">
                  <form>
                    <h1 className="text-center h3 mb-3 fw-normal">ADMIN_201700375</h1>
                    <br/>
                    <h6 className="h3 mb-3 fw-normal">Cargar Archivos</h6>
                    <br/>
                    <div className="input-group mb-3">
                        <label className="input-group-text">Cargar Pedidos</label>
                        <input className="form-control" id="inputGroupFile01"
                        type="file"
                        accept="application/json"
                        onChange={onChange}/>
                    </div>
                    <br/>
                    <div className="input-group mb-3">
                        <label className="input-group-text">Cargar Empleados</label>
                        <input className="form-control" id="inputGroupFile02"
                        type="file"
                        accept=".csv, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel"
                        onChange={onChange1}/>
                    </div>
                    <br/>
                    <center><button className="w-100 btn btn-info" onClick={reportes}>Reportes</button></center>
                    <br/>
                    <center><button className="w-50 btn btn-danger" onClick={salir}>Salir</button></center>
                    <br/>
                  </form>
            </div>
          </div>
    );
}