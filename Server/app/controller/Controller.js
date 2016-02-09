// http://localhost:9009/
var html_dir = './app/views/';
var db = require('../model/model.js');


exports.validarLogIn = function(request, response){
	console.log("request")
	db.validarLogIn(request,response);
};

exports.getPlatos = function(request, response){
	idRestautante = request.body.idRestautante;
	console.log(idRestautante);
	response.send({at1:"hola"});

}

exports.guardarPlato= function(request, response){
	console.log("guardarPlato");
	db.guardarPlato(request, response);

}

exports.getMenus = function(request, response){
	idRestautante = request.query.idRestautante;
	console.log("Get Menus ",idRestautante);
	db.getMenus(request,response);

}
exports.getPlatos = function(request, response){
	idRestautante = request.query.idRestautante;
	console.log("Get Platos ",idRestautante);
	db.getPlatos(request,response);

}


