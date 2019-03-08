//si requiere al archivo model, los nombres del esquema deben coincidir
const clientesModel = require('./clientes.model');

module.exports.registrar  = function (req, res){

    let newCliente = new  clientesModel({
        nombre : req.body.nombre,
        snombre : req.body.snombre,
        apellido : req.body.apellido,
        sapellido : req.body.sapellido,
        id : req.body.id,
        correo : req.body.correo,
        fecha : req.body.fecha,
        tel: req.body.tel,
        pw : req.body.pw,
        confirmpw : req.body.confirmpw,
        rol : req.body.rol,
        activo: req.body.activo,
    });

    newCliente.save(function(error){
        if(error){
            res.json({success : false, msg : 'No se pudo registrar la cliente, ocurrió el siguiente error' + error});
        }else{
            res.json({success : true, msg : 'El cliente se registró con éxito'});
        }
    });
};

module.exports.listar_clientes = function(req, res){
    clientesModel.find().then(function(clientes){
        res.send(clientes);
    });
};

/*tengo que meterle el _id al arreglo - se declara la variable global
  en el controlador de modificar*/
module.exports.buscar_cliente_por_id = function(req, res){
    clientesModel.findOne({correo : req.body.correo}).then(
        function(cliente){
            res.send(cliente);
        });
};

module.exports.actualizar_cliente = function(req, res){
    clientesModel.findOneAndUpdate({'correo' : req.body.correo},{ $set: req.body },
        function(error){
            if(error){
                res.json({ success: false, msg: 'No se ha actualizado el cliente debido al siguiente error: ' + handleError(error) });
            }else{
                res.json({ success: true, msg: 'El cliente ha sido modificado con éxito'});
            }
        });
};