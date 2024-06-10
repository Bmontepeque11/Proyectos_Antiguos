function IniciarSesion(){

    let Usuario = document.getElementById("Ingresar Usuario").value
    let Password = document.getElementById("Ingresar Password").value

    if (Usuario == "admin" && Password == "admin"){
        location.href = "../EDD_Proyecto1_Fase2/Admin.html"
    } else {
        window.alert(["Error, no ha ingresado los datos del admin"])
    }
}