let correo = getTemp();
let infoHotel = buscarHotelPorId(correo);

let coordenadas = infoHotel['coordenadas'];
let coordenadasObj = coordenadas.split(",");
let latit = coordenadasObj[0];
let long = coordenadasObj[1];

latitud = Number(latit);
longitud = Number(long);

mostrarNombre();

function mostrarNombre() {
    document.querySelector('#txtNombre').value = infoHotel['nombre'];
}



function initMap() {
    var myLatLng = {lat: 9.462482, lng: 10.48932};
    myLatLng["lat"] = latitud;
    myLatLng["lng"] = longitud;
  
    var map = new google.maps.Map(document.getElementById('map'), {
      zoom: 7,
      center: myLatLng
    });
  
    var marker = new google.maps.Marker({
      position: myLatLng,
      map: map,
      title: 'Hello World!'
    });
  }



   //Ratings iniciales 
        
   const ratings = {
    comida: 4.2,
    calidad_servicio: 3.8,
    habitaciones: 5,
    infraestructura: 2.6,
    limpieza: 4 
}

console.log(ratings);
console.log(ratings['comida']);

//total de estrellas / maximo
const starsTotal = 5;

//se corre la funcion geRatings() cuando el DOM cargue
document.addEventListener('DOMContentLoaded', getRatings);

const rubroSelect = document.getElementById('rubro-select');
const ratingControl = document.getElementById('rating-control');

let rubro;

//rubroSelect change
rubroSelect.addEventListener('change', (e) => {
rubro = e.target.value;
//habilitar rating-control
ratingControl.disabled = false;
ratingControl.value = ratings[rubro];

});

//ratingControl change
ratingControl.addEventListener('blur', (e) => {
const rating = e.target.value;
console.log(rating);
//asegurarse de que el valor se 5 o menos
if(rating > 5) {
    alert("por favor califique del 1 al 5");
    return;
}

//cambiar rating
ratings[rubro] = rating;
getRatings();
});

function getRatings() {
    for(let rating in ratings) {
        // console.log(rating);
        /*esto loggea el rubro, pero no el numero, el numero se obtiene de la siguiete manera*/
        // console.log(ratings[rating]);

        //obtener porcentaje
        let porcentaje = (ratings[rating] / starsTotal) * 100;
        //redondear
        //esta constante se utiliza para el ancho de las estrellas (stars-inner)
        const porcentajeRnd = `${Math.round(porcentaje / 10) * 10}%`;

        //definir el ancho de las estrellas
        document.querySelector(`.${rating} .stars-inner`).style.width = porcentajeRnd;
        

    }
}

// function registrarRatings() {
//   let r1 = 
//   let r2 = 
// }