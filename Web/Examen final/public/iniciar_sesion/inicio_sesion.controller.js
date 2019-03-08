// esconder() {
//     document.getElementById()
// }

/*Para poner el class de hide, se llama al usuarioActivo
del session storage y si es rol 2, se esconde el boton de registros
y el de listar clientes */



document.querySelector('#ingresar').addEventListener('click', iniciarSesion);

function iniciarSesion() {
    let correo = '';
    let pw = '';

    correo = document.querySelector('#txtCorreo').value;
    pw = document.querySelector('#txtContrasena').value;
    let acceso = validarCredenciales(correo, pw);
    console.log(pw);

    if (acceso == true) {
     ingresar();   
    } else {
        swal({
            title: "Contraseña o correo incorrectos",
            text: "Por favor verifique los campos e intente de nuevo",
            icon: "error",
            button: {
              text: "OK",
              className: "button",
            },
          });
    }
}


function ingresar() {
    let rol = sessionStorage.getItem('rol');
    if (rol == '2') {
        window.location.href = "../hoteles/listar_hoteles.html";
    } else if (rol == '1') {
        window.location.href = "../clientes/listar_clientes.html";
    }
}