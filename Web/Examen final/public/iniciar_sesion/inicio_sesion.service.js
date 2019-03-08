

// let listaUsuarios = admin quemado

// let listaUsuarios = obtenerListaCliente();
// console.log(listaUsuarios);

function validarCredenciales(pcorreo, pcontrasena) {
    let listaUsuarios = obtenerListaCliente();
    let acceso = false;

    for(let i = 0; i < listaUsuarios.length; i++) {
        if(pcorreo == listaUsuarios[i]['correo'] && pcontrasena == listaUsuarios[i]['pw']){
            acceso = true;

            sessionStorage.setItem('usuarioActivo', listaUsuarios[i]['nombre'] + " " + listaUsuarios[i]['apellido']);
            sessionStorage.setItem('rol', listaUsuarios[i]['rol']);
        }
    }
    return acceso;
}