let mongoose = require('mongoose');

let hotelSchema = new mongoose.Schema({
    nombre :{type: String, required : true},
        tel : {type: String, required : true},
        correo : {type: String, required : true},
        telr : {type: String, required : true},
        correor : {type: String, required : true},
        foto : {type: String},
        provincia : {type: String},
        canton : {type: String},
        distrito : {type: String},
        coordenadas : {type: String},
        activo : {type: String}
});

module.exports = mongoose.model('Hoteles', hotelSchema);