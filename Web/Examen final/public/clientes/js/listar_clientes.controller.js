document.querySelector('#txtFiltro').addEventListener('keyup', mostrarClientes);

let listaClientes = obtenerListaCliente();
// let _id = 0;

mostrarClientes();

function mostrarClientes() {
    let tbody = document.querySelector('#tblClientes tbody');
    let sFiltro = document.querySelector('#txtFiltro').value;

    tbody.innerHTML = '';

    for(let i = 0; i < listaClientes.length; i++) {
        if(listaClientes[i]['activo'] == '1') {
            //buscar por nombre apellido, sapellido y correo
            if(listaClientes[i]['nombre'].toLowerCase().includes(sFiltro) || listaClientes[i]['apellido'].toLowerCase().includes(sFiltro) || listaClientes[i]['sapellido'].toLowerCase().includes(sFiltro) || listaClientes[i]['correo'].toLowerCase().includes(sFiltro)) {
                let fila = tbody.insertRow();

                let nombre = fila.insertCell();
                let apellido = fila.insertCell();
                let sapellido = fila.insertCell();
                let correo = fila.insertCell();
                let telefono = fila.insertCell();
                let modificar = fila.insertCell();
                let eliminar = fila.insertCell();

                nombre.appendChild(document.createTextNode(listaClientes[i]['nombre']));
                apellido.appendChild(document.createTextNode(listaClientes[i]['apellido']));
                sapellido.appendChild(document.createTextNode(listaClientes[i]['sapellido']));
                correo.appendChild(document.createTextNode(listaClientes[i]['correo']));
                telefono.appendChild(document.createTextNode(listaClientes[i]['tel']));

                let botonModificar = document.createElement('i');
                botonModificar.classList.add("far", "fa-edit");
                
                let botonDesactivar = document.createElement('i');
                botonDesactivar.classList.add("fas", "fa-times");
                botonDesactivar.dataset.indice = i;
        
                botonDesactivar.addEventListener('click', deshabilitar);

                let elementa = document.createElement('a');
                elementa.setAttribute('href', "modificar_clientes.html");
                elementa.appendChild(botonModificar);
                elementa.addEventListener('click', redirect);
                //revisar esto en api
                elementa.dataset.correo = listaClientes[i]['correo'];

                modificar.appendChild(elementa);
                // 
                
                eliminar.appendChild(botonDesactivar);
                // eliminar.classList.add();
            }
        }
    }
}

function redirect() {
    let correo = this.dataset.correo;
    setTemp(correo);
    console.log(correo);
}


function deshabilitar() {
    let idModificar = this.dataset.indice;
    let actualizar = [];
    swal({
        title: "¿Está seguro de eliminar al cliente?",
        text: "Si lo hace, el registro del mismo desaparecerá por completo",
        icon: "warning",
        buttons: {
          catch: {
              text: 'Eliminar',
              value: 'Eliminar',
              className: 'button',
          },
          cancel: 'Cancelar'
        },
      })
      .then((botonUsuario) => {
          if(botonUsuario === "Eliminar") {
              //let listaClientes = obtenerListaSucursal();
              if(listaClientes[idModificar]['activo'] == '1') {
                  listaClientes[idModificar]['activo'] = '0';
                //   console.log(listaClientes[idModificar]);
                  actualizar.push(listaClientes[idModificar]['nombre'], listaClientes[idModificar]['apellido'], listaClientes[idModificar]['snombre'], listaClientes[idModificar]['sapellido'], listaClientes[idModificar]['id'], listaClientes[idModificar]['correo'], listaClientes[idModificar]['fecha'], listaClientes[idModificar]['tel'], listaClientes[idModificar]['pw'], listaClientes[idModificar]['confirmpw'], listaClientes[idModificar]['rol'], listaClientes[idModificar]['activo']);
                  actualizarCliente(actualizar);
                  mostrarClientes();
              }
              
             
          }
      })
}