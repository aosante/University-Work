function setTemp(correo) {
    localStorage.setItem('tempCliente', correo);
}

function getTemp() {
    return localStorage.getItem('tempCliente');
}

function removeTemp() {
    localStorage.removeItem('tempCliente');
}



function guardarCliente(pDatosCliente){
    let peticion = $.ajax({
        url: 'http://localhost:4000/api/registrar_cliente',
        type: 'post',
        contentType: 'application/x-www-form-urlencoded; charset=utf-8',
        dataType : 'json',
        async:false,
        data:{
            'nombre' : pDatosCliente[0],
            'snombre' : pDatosCliente[1],
            'apellido' : pDatosCliente[2],
            'sapellido' : pDatosCliente[3],
            'id' : pDatosCliente[4],
            'correo' : pDatosCliente[5],
            'fecha' : pDatosCliente[6],
            'tel' : pDatosCliente[7],
            'pw' : pDatosCliente[8],
            'confirmpw' : pDatosCliente[9],
            'rol' : pDatosCliente[10],
            'activo' : pDatosCliente[11]
        }
      });
    
      peticion.done(function(response){
        
      });
    
      peticion.fail(function(){
       
      });
}

function obtenerListaCliente(numero) {
    let listaClientes = [];
    let peticion = $.ajax({
        //los urls tiene que llamarse igual que la ruta en el archivo route
        url: 'http://localhost:4000/api/listar_todos_clientes',
        type: 'get',
        contentType: 'application/x-www-form-urlencoded; charset=utf-8',
        dataType: 'json',
        async:false,
        data:{}
    });

    peticion.done(function(response) {
        listaClientes = response;
    });

    peticion.fail(function() {
        
    });

    return listaClientes;
}

function buscarClientePorId(pcorreo) {
    let cliente = [];
    let peticion = $.ajax({
        url: 'http://localhost:4000/api/buscar_cliente_id',
        type: 'post',
        contentType: 'application/x-www-form-urlencoded; charset=utf-8',
        dataType: 'json',
        async:false,
        data: {
            'correo' : pcorreo
        }
    });

    peticion.done(function(response) {
        cliente = response;
    });

    peticion.fail(function() {

    });

    return cliente;
}

function actualizarCliente(pDatosCliente) {
    console.log(pDatosCliente);
    let peticion = $.ajax({
        url: 'http://localhost:4000/api/actualizar_cliente',
        type: 'put',
        contentType: 'application/x-www-form-urlencoded; charset=utf-8',
        dataType: 'json',
        async:false,
        data:{
            'nombre' : pDatosCliente[0],
            'snombre' : pDatosCliente[1],
            'apellido' : pDatosCliente[2],
            'sapellido' : pDatosCliente[3],
            'id' : pDatosCliente[4],
            'correo' : pDatosCliente[5],
            'fecha' : pDatosCliente[6],
            'tel' : pDatosCliente[7],
            'pw' : pDatosCliente[8],
            'confirmpw' : pDatosCliente[9],
            'rol' : pDatosCliente[10],
            'activo' : pDatosCliente[11]
        }
    });

    peticion.done(function(response) {

    });

    peticion.fail(function() {

    });
}