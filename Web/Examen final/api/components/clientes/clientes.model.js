let mongoose = require('mongoose');

let clienteSchema = new mongoose.Schema({
    nombre : {type: String, required : true},
    snombre : {type: String, required : false},
    apellido : {type: String, required : true},
    sapellido : {type: String, required : false},
    id : {type: String, required : true},
    correo : {type: String, required : true},
    fecha : {type: String, required : true},
    tel : {type: String, required : true},
    pw : {type: String, required : true},
    confirmpw : {type: String, required : true},
    rol : {type: String, required : true},
    activo : {type : String, required : true}
});

module.exports = mongoose.model('Clientes', clienteSchema);