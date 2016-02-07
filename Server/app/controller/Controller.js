// http://localhost:9009/
var html_dir = './app/views/';
var db = require('../model/model.js');


exports.validarLogIn = function(request, response){
	console.log("request")
	db.validarLogin(request,response);
};

exports.getPlatos = function(request, response){
	idRestautante = request.body.idRestautante;
	console.log(idRestautante);
	response.send({at1:"hola"});

}

