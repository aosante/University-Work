obtenerCliente();


function obtenerCliente() {
    let correo = getTemp();
    let infoCliente = buscarClientePorId(correo);

    document.querySelector('#txtNombre').value = infoCliente['nombre'];
    document.querySelector('#txt2doNombre').value = infoCliente['snombre'];
    document.querySelector('#txtApellido').value = infoCliente['apellido'];
    document.querySelector('#txt2doApellido').value = infoCliente['sapellido'];
    document.querySelector('#txtId').value = infoCliente['id'];
    document.querySelector('#txtCorreo').value = infoCliente['correo'];
    document.querySelector('#txtCorreo').disabled = true;
    document.querySelector('#txtEdad').value = infoCliente['fecha'];
    document.querySelector('#txtTel').value = infoCliente['tel'];
    document.querySelector('#txtPassword').value = infoCliente['pw'];
    document.querySelector('#txtConfirmPassword').value = infoCliente['confirmpw'];
}

let botonActualizar = document.querySelector('#botonActualizar');
botonActualizar.addEventListener('click', obtenerActualizar);

function obtenerActualizar() {

    let aClientes = [];

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



    aClientes.push(primerNombre, segundoNombre, primerApellido, segundoApellido, 
        id, correo, fecha, telefono, pw, confirmPw, rol, activo);
    actualizarCliente(aClientes);
    removeTemp();
    window.location.href = 'listar_clientes.html';
}