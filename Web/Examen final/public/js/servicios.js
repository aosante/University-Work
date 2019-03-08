esconder();

const imageCloudName = 'app-correos-costarica';
const unsignedUser = 'pgl2jn3n';

if (typeof($) !== 'undefined' && $.cloudinary && $.cloudinary.unsigned_upload_tag) {
  $.cloudinary.unsigned_upload_tag(unsignedUser, { cloud_name: imageCloudName });
}

function validarInputsRequeridos(inputs) {
  let error = false;

  for (let i = 0; i < inputs.length; i++) {
    if (inputs[i].value == '') {
      error = true;
      inputs[i].classList.add('inputError');
    } else {
      inputs[i].classList.remove('inputError');
    }
  }

  return error;
}

function calcularEdad(fecha) {
  let hoy = new Date();
  let nacimiento = new Date(fecha);
  let edad = hoy.getFullYear() - nacimiento.getFullYear();

  return edad;
}

function esconder() {
  let rol = sessionStorage.getItem('rol');
  if(rol == '2') {
    document.querySelector('#registros').classList.add('hide');
    document.querySelector('#opcion').classList.add('hide');
  }
}


function mostrarMensajeModal(tipoMensaje) {
  switch (tipoMensaje) {
    case 'error formulario':
      swal({
        title: "Ocurrió un error",
        text: "Por favor verifique los campos resaltados",
        icon: "error",
        button: {
          text: "OK",
          className: "button",
        },
      });
      break;
    case 'registro exitoso':
      swal({
        title: "Información registrada correctamente",
        text: "Puede proceder",
        icon: "success",
        button: {
          text: "OK",
          className: "button",
        },
      });
      break;
    case 'error edad':
      swal({
        title: "Necesita ser mayor de edad para ingresar",
        text: "Verifique la fecha de nacimiento",
        icon: "error",
        button: {
          text: "OK",
          className: "button",
        },
      });
      break;
    case 'error contrasena':
      swal({
        title: "La contraseña confirmada no es igual a la digitada",
        text: "Verifique los campos de contraseña",
        icon: "error",
        button: {
          text: "OK",
          className: "button",
        },
      });
      break;
    case 'registro exitoso de usuario':
      swal({
        title: "Información registrada correctamente",
        text: "Se ha enviado una contraseña temporal al correo electrónico registrado.",
        icon: "success",
        button: {
          text: "OK",
          className: "button",
        },
      }).then(() => {
        window.location.href = '../iniciarSesion/index.html';
      });
      break;
  }
}


// function obtenerClienteDB(correo){
//   let cliente = [];
//   let peticion = $.ajax({
//     url: 'http://localhost:4000/api/user_by_email',
//     type: 'post',
//     contentType: 'application/x-www-form-urlencoded; charset=utf-8',
//     dataType : 'json',
//     async:false,
//     data: {
//       correo : correo
//     }
//   });

//   peticion.done(function(clienteInfo){
//     for (var key in clienteInfo.data) {
//       if (key != '__v' && key != '_id') {
//         cliente.push(clienteInfo.data[key]);
//       };
//     };
//     console.log('Petición realizada con éxito');
//   });

//   peticion.fail(function(){
//     cliente = false;
//     console.log('Ocurrió un error');
//   });

//   return cliente;
// };