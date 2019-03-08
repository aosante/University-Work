document.querySelector('#botonRegistrar').addEventListener('click', registrarHotel);

// var imagePreview = document.querySelector('#previewFoto');
//     var urlFotoPerfil = false;
  
//     function initFotoPerfil() {
//       $("input.cloudinary-fileupload[type=file]").unsigned_cloudinary_upload(unsignedUser, { cloud_name: imageCloudName, tags: 'browser_uploads' })
//         .bind('cloudinarydone', function(e, data) {
//           imagePreview.setAttribute("src", data.result.url);
//           imagePreview.style.display = 'block';
//           urlFotoPerfil = data.result.url;
//           document.querySelector('#inputFotoPerfil').classList.remove('inputError');
//         });
//     };
//     initFotoPerfil();
 
let urlFotoPerfil = "x";

function registrarHotel() {
let inputs = document.querySelectorAll('input:required');
let error = validarInputsRequeridos(inputs);

if(error == true) {
    mostrarMensajeModal('error formulario');
} else {
    let infoHotel = [];

    let nombre = document.querySelector('#txtNombre').value;
    let tel = document.querySelector('#txtTelSC').value;
    let correo = document.querySelector('#txtCorreoSC').value;
    let telr = document.querySelector('#txtTelR').value;
    let correor = document.querySelector('#txtCorreoR').value;
    let foto = urlFotoPerfil;
    let provincia = document.querySelector('#provincias').value;
    let canton = document.querySelector('#cantones').value;
    let distrito = document.querySelector('#distritos').value;
    let coordenadas = document.querySelector('#coordenadas').value;
    let activo = '1';

    infoHotel.push(nombre, tel, correo, telr, correor,
    foto, provincia, canton, distrito, coordenadas, activo);
    guardarHotel(infoHotel);
    limpiar();
    mostrarMensajeModal('registro exitoso');
}
}

function limpiar() {
    document.querySelector('#txtNombre').value = '';
    document.querySelector('#txtTelSC').value = '';
    document.querySelector('#txtCorreoSC').value = '';
    document.querySelector('#txtTelR').value = '';
    document.querySelector('#txtCorreoR').value = '';
}