//si requiere al archivo model, los nombres del esquema deben coincidir
const hotelesModel = require('./hoteles.model');

module.exports.registrar  = function (req, res){

    let newHotel = new  hotelesModel({
        nombre : req.body.nombre,
        tel : req.body.tel,
        correo : req.body.correo,
        telr : req.body.telr,
        correor : req.body.correor,
        foto : req.body.foto,
        provincia : req.body.provincia,
        canton : req.body.canton,
        distrito : req.body.distrito,
        coordenadas : req.body.coordenadas,
        activo : req.body.activo
        
        
    });

    newHotel.save(function(error){
        if(error){
            res.json({success : false, msg : 'No se pudo registrar el hotel, ocurrió el siguiente error' + error});
        }else{
            res.json({success : true, msg : 'El hotel se registró con éxito'});
        }
    });
};

module.exports.listar_hoteles = function(req, res){
    hotelesModel.find().then(function(hoteles){
        res.send(hoteles);
    });
};

/*tengo que meterle el _id al arreglo - se declara la variable global
  en el controlador de modificar*/
module.exports.buscar_hotel_por_id = function(req, res){
    hotelesModel.findOne({correo : req.body.correo}).then(
        function(hotel){
            res.send(hotel);
        });
};

module.exports.actualizar_hotel = function(req, res){
    hotelesModel.findOneAndUpdate({'correo' : req.body.correo},{ $set: req.body },
        function(error){
            if(error){
                res.json({ success: false, msg: 'No se ha actualizado el hotel debido al siguiente error: ' + handleError(error) });
            }else{
                res.json({ success: true, msg: 'El hotel ha sido modificado con éxito'});
            }
        });
};