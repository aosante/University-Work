document.querySelector('#txtFiltro').addEventListener('keyup', mostrarHoteles);

let listaHoteles = obtenerListaHotel();

for(let i = 0; i < listaHoteles.length; i++) {
    console.log(listaHoteles[i]['coordenadas']);
}



mostrarHoteles();

function mostrarHoteles() {
    let tbody = document.querySelector('#tblHoteles');
    let sFiltro = document.querySelector('#txtFiltro').value;

    tbody.innerHTML = '';

    for(let i = 0; i < listaHoteles.length; i++) {
        if(listaHoteles[i]['activo'] == '1') {
            if(listaHoteles[i]['nombre'].toLowerCase().includes(sFiltro)) {
                let fila = tbody.insertRow();

                let nombre = fila.insertCell();
                let correo1 = fila.insertCell();
                let tel1 = fila.insertCell();
                let correo2 = fila.insertCell();
                let tel2 = fila.insertCell();
                let modificar = fila.insertCell();
                let deshabilitar = fila.insertCell();
                let ver = fila.insertCell();
                //AQUI SE IMPLEMENTA LA CALIFICACION

                nombre.appendChild(document.createTextNode(listaHoteles[i]['nombre']));
                correo1.appendChild(document.createTextNode(listaHoteles[i]['correo']));
                tel1.appendChild(document.createTextNode(listaHoteles[i]['tel']));
                correo2.appendChild(document.createTextNode(listaHoteles[i]['correor']));
                tel2.appendChild(document.createTextNode(listaHoteles[i]['telr']));

                let botonModificar = document.createElement('i');
                botonModificar.classList.add("far", "fa-edit");

                let botonDesactivar = document.createElement('i');
                botonDesactivar.classList.add("fas", "fa-times");
                botonDesactivar.dataset.indice = i;

                botonDesactivar.addEventListener('click', eliminar);

                let elementa = document.createElement('a');
                elementa.setAttribute('href', "modificar_hoteles.html");
                elementa.appendChild(botonModificar);
                //!!!
                elementa.addEventListener('click', redirect);

                elementa.dataset.correo = listaHoteles[i]['correo'];
                modificar.appendChild(elementa);

                deshabilitar.appendChild(botonDesactivar);

                let botonVer = document.createElement('i');
                botonVer.classList.add("fas", "fa-eye");

                let verhotel = document.createElement('a');
                verhotel.setAttribute('href', "evaluar_hoteles.html");
                verhotel.appendChild(botonVer);
                verhotel.addEventListener('click', redirect);
                verhotel.dataset.correo = listaHoteles[i]['correo'];
                ver.appendChild(verhotel);
            }
        }
    }
}

function redirect() {
    let correo = this.dataset.correo;
    setTemp(correo);
}

function eliminar() {
    let idModificar = this.dataset.indice;
    let actualizar = [];
    swal({
        title: "¿Está seguro de eliminar el hotel?",
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
              //let listaHoteles = obtenerListaSucursal();
              if(listaHoteles[idModificar]['activo'] == '1') {
                  listaHoteles[idModificar]['activo'] = '0';
                //   console.log(listaHoteles[idModificar]);
                  actualizar.push(listaHoteles[idModificar]['nombre'], listaHoteles[idModificar]['tel'], listaHoteles[idModificar]['correo'], listaHoteles[idModificar]['telr'], listaHoteles[idModificar]['correor'], listaHoteles[idModificar]['foto'], listaHoteles[idModificar]['provincia'], listaHoteles[idModificar]['canton'], listaHoteles[idModificar]['distrito'], listaHoteles[idModificar]['coordenadas'], listaHoteles[idModificar]['activo']);
                  actualizarHotel(actualizar);
                  mostrarHoteles();
              }
              
             
          }
      })
}