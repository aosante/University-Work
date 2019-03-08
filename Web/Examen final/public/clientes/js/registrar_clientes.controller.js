document.querySelector('#botonRegistrar').addEventListener('click', registrarDatos);

function registrarDatos() {
    let inputs = document.querySelectorAll('input:required');
    let error = validarInputsRequeridos(inputs);

    if (error == true) {
        mostrarMensajeModal('error formulario');
    } else {
        let infoCliente = [];

        let primerNombre = document.querySelector('#txtNombre').value;
        let segundoNombre = document.querySelector('#txt2doNombre').value;
        let primerApellido = document.querySelector('#txtApellido').value;
        let segundoApellido = document.querySelector('#txt2doApellido').value;
        let id = document.querySelector('#txtId').value;
        let correo = document.querySelector('#txtCorreo').value;
        let fecha = document.querySelector('#txtEdad').value;
        let telefono = document.querySelector('#txtTel').value;
        let pw = document.querySelector('#txtPassword').value;
        let confirmPw = document.querySelector('#txtConfirmPassword').value;
        let rol = '2';
        let activo = '1';

        let edad = calcularEdad(fecha);

        if (edad < 18) {
            mostrarMensajeModal('error edad');
            return false;
        }

        if(pw != confirmPw) {
            mostrarMensajeModal('error contrasena');
            return false;
        }

        // let registroValido = validarRegistroDoble(correo);
        // if(registroValido == false) {
        //     return false;
        // }

        infoCliente.push(primerNombre, segundoNombre, primerApellido, segundoApellido, 
        id, correo, fecha, telefono, pw, confirmPw, rol, activo);
        guardarCliente(infoCliente);
        limpiar();
        mostrarMensajeModal('registro exitoso'); 
    }
}

function limpiar() {
    document.querySelector('#txtNombre').value = '';
    document.querySelector('#txt2doNombre').value = '';
    document.querySelector('#txtApellido').value = '';
    document.querySelector('#txt2doApellido').value = '';
    document.querySelector('#txtId').value = '';
    document.querySelector('#txtCorreo').value = '';
    document.querySelector('#txtEdad').value = '';
    document.querySelector('#txtTel').value = '';
    document.querySelector('#txtPassword').value = '';
    document.querySelector('#txtConfirmPassword').value = '';

}