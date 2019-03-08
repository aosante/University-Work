obtenerHotel();

function obtenerHotel() {
    let correo = getTemp();
    let infoHotel = buscarHotelPorId(correo);

    document.querySelector('#txtNombre').value = infoHotel['nombre'];
    document.querySelector('#txtTelSC').value = infoHotel['correo'];
    document.querySelector('#txtCorreoSC').value = infoHotel['tel'];
    document.querySelector('#txtCorreoSC').disabled = true;
    document.querySelector('#txtTelR').value = infoHotel['telr'];
    document.querySelector('#txtCorreoR').value = infoHotel['correor'];
    document.querySelector('#provincias').value = infoHotel['provincia'];
    document.querySelector('#provincias').disabled = true;
    document.querySelector('#cantones').value = infoHotel['canton'];
    document.querySelector('#cantones').disabled = true;
    document.querySelector('#distritos').value = infoHotel['distritos'];
    document.querySelector('#distritos').disabled = true;
    document.querySelector('#coordenadas').value = infoHotel['coordenadas'];

}

let botonActualizar = document.querySelector('#botonActualizar');
botonActualizar.addEventListener('click', obtenerActualizar);

function obtenerActualizar() {
    let aHoteles = [];

    let nombre = document.querySelector('#txtNombre').value;
    let tel = document.querySelector('#txtTelSC').value;
    let correo = document.querySelector('#txtCorreoSC').value;
    let telr = document.querySelector('#txtTelR').value;
    let correor = document.querySelector('#txtCorreoR').value;
    let foto = 'x';
    let provincia = document.querySelector('#provincias').value;
    let canton = document.querySelector('#cantones').value;
    let distrito = document.querySelector('#distritos').value;
    let coordenadas = document.querySelector('#coordenadas').value;
    let activo = '1';

    aHoteles.push(nombre, tel, correo, telr, correor,
        foto, provincia, canton, distrito, coordenadas, activo);
        actualizarHotel(aHoteles);
        removeTemp();
        window.location.href = 'listar_hoteles.html';
}