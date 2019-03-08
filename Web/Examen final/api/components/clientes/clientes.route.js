
const express = require('express');
const router = express.Router();
const clientes = require('./clientes.api');

router.param('id', function(req, res, next, id) {
    console.log(req.body)
    req.body.id = id;
    next();
});

// router.route('/user_by_email')
//   .post(function(req, res){
//     users.findByEmail(req, res);
// });

//las rutas debe coincidir con el url especificado en las peticiones
router.route('/registrar_cliente')
    .post(function(req, res){ 
    clientes.registrar(req, res);
});

router.route('/listar_todos_clientes')
    .get(function(req, res){
    clientes.listar_clientes(req, res);
});

router.route('/buscar_cliente_id')
    .post(function(req, res){
        clientes.buscar_cliente_por_id(req, res);
    });

router.route('/actualizar_cliente')
    .put(function(req, res){
        clientes.actualizar_cliente(req, res);
    });



module.exports = router;