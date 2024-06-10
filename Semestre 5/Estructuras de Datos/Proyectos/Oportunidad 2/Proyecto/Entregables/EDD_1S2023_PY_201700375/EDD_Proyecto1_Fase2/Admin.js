function MostrarAlumnos(){

}

function CargaMasiva(){
    let ArchivoSubidoHTML = document.getElementById("JSON Estudiantes").value
    window.alert([ArchivoSubidoHTML])

    var DatosLeidosJSON = JSON.parse(ArchivoSubidoHTML)
    window.alert([DatosLeidosJSON])
}

function CerrarSesion(){
    location.href = "../EDD_Proyecto1_Fase2/index.html"

    window.alert(["El Administrador ha cerrado sesión"])
}