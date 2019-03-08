function setTemp(correo) {
    localStorage.setItem('tempHotel', correo);
}

function getTemp() {
    return localStorage.getItem('tempHotel');
}

function removeTemp() {
    localStorage.removeItem('tempHotel');
}



function guardarHotel(pDatosHotel){
    let peticion = $.ajax({
        url: 'http://localhost:4000/api/registrar_hotel',
        type: 'post',
        contentType: 'application/x-www-form-urlencoded; charset=utf-8',
        dataType : 'json',
        async:false,
        data:{
            'nombre' : pDatosHotel[0],
            'tel' : pDatosHotel[1],
            'correo' : pDatosHotel[2],
            'telr' : pDatosHotel[3],
            'correor' : pDatosHotel[4],
            'foto' : pDatosHotel[5],
            'provincia' : pDatosHotel[6],
            'canton' : pDatosHotel[7],
            'distrito' : pDatosHotel[8],
            'coordenadas' : pDatosHotel[9],
            'activo' : pDatosHotel[10]
        }
      });
    
      peticion.done(function(response){
        
      });
    
      peticion.fail(function(){
       
      });
}

function obtenerListaHotel(numero) {
    let listaHoteles = [];
    let peticion = $.ajax({
        //los urls tiene que llamarse igual que la ruta en el archivo route
        url: 'http://localhost:4000/api/listar_todos_hoteles',
        type: 'get',
        contentType: 'application/x-www-form-urlencoded; charset=utf-8',
        dataType: 'json',
        async:false,
        data:{}
    });

    peticion.done(function(response) {
        listaHoteles = response;
    });

    peticion.fail(function() {
        
    });

    return listaHoteles;
}

function buscarHotelPorId(pcorreo) {
    let hotel = [];
    let peticion = $.ajax({
        url: 'http://localhost:4000/api/buscar_hotel_id',
        type: 'post',
        contentType: 'application/x-www-form-urlencoded; charset=utf-8',
        dataType: 'json',
        async:false,
        data: {
            'correo' : pcorreo
        }
    });

    peticion.done(function(response) {
        hotel = response;
    });

    peticion.fail(function() {

    });

    return hotel;
}

function actualizarHotel(pDatosHotel) {
    // console.log(pDatosHotel);
    let peticion = $.ajax({
        url: 'http://localhost:4000/api/actualizar_hotel',
        type: 'put',
        contentType: 'application/x-www-form-urlencoded; charset=utf-8',
        dataType: 'json',
        async:false,
        data:{
            'nombre' : pDatosHotel[0],
            'tel' : pDatosHotel[1],
            'correo' : pDatosHotel[2],
            'telr' : pDatosHotel[3],
            'correor' : pDatosHotel[4],
            'foto' : pDatosHotel[5],
            'provincia' : pDatosHotel[6],
            'canton' : pDatosHotel[7],
            'distrito' : pDatosHotel[8],
            'coordenadas' : pDatosHotel[9],
            'activo' : pDatosHotel[10]
        }
    });

    peticion.done(function(response) {

    });

    peticion.fail(function() {

    });
}