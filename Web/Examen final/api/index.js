'use strict'

const express = require('express'),
      app = express(),
      path = require('path'),
      bodyParser = require('body-parser'),
      morgan =  require('morgan'),
      mongoose = require('mongoose');

let db = mongoose.connection,
//Preguntar sobre el puerto, ya que este no está desbloqueado en la U
    dburl = 'mongodb://admin:admin@ds117821.mlab.com:17821/hotelesapp',
    port = 4000;

let server = app.listen(port, _server());

mongoose.connect(dburl);
db.on('error', console.error.bind(console, 'Error de conexión: '));
db.once('open', function () {
  console.log('Base de datos conectada correctamente');
});

app.use(express.static(path.join(__dirname, 'public')));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended: false}));
app.use(morgan('dev'));

app.use( function(req, res, next) {
  res.setHeader('Access-Control-Allow-Origin', '*');
  res.setHeader('Access-Control-Allow-Methods', 'GET, POST, OPTIONS, PUT, PATCH, DELETE');
  res.setHeader('Access-Control-Allow-Headers', 'Origin, Accept, Accept-Version, Content-Length, Content-MD5, Content-Type, Date, X-Api-Version, X-Response-Time, X-PINGOTHER, X-CSRF-Token,Authorization');
  res.setHeader('Access-Control-Allow-Credentials', true);
  next();
});

const clientes = require('./components/clientes/clientes.route');
app.use('/api', clientes);

const hoteles = require('./components/hoteles/hoteles.route');
app.use('/api', hoteles);

module.exports = app;

function _server(){
  console.log('Conexión establecida en el puerto ' + port);
};