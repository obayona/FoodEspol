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
	db.getMenus(request,response);

}
exports.getPlatos = function(request, response){
	idRestautante = request.query.idRestautante;
	db.getPlatos(request,response);

}

exports.getRestaurante = function(request, response){
	db.getRestaurante(request, response);
}

exports.eliminarMenu = function(request, response){
	idRestautante = request.query.idRestautante;
	idMenu = request.query.idMenu;
	console.log("Eliminar Menu ",idRestautante, " ",idMenu);
	db.eliminarMenu(request,response);

}
exports.eliminarPlato = function(request, response){
	idRestautante = request.query.idRestautante;
	idPlato = request.query.idPlato;
	console.log("Eliminar Plato ",idRestautante, " ",idPlato);
	db.eliminarPlato(request,response);

}
exports.getPlatosMenu = function(request, response){
	idRestautante = request.query.idRestautante;
	idMenu= request.query.idMenu;
	console.log("Get Platos Menu ",idRestautante, " ",idMenu);
	db.getPlatosMenu(request,response);

}

exports.eliminarPlatoMenu = function(request, response){
	idPlato = request.query.idPlato;
	idMenu= request.query.idMenu;
	console.log("eliminar plato de Menu",idMenu, " ",idPlato);
	db.getPlatosMenu(request,response);

}

exports.postMenu = function(request, response){
	idRestautante = request.body.idRestautante;
	idMenu= request.body.idMenu;
	console.log("Guardar Menu ",request.body);
	db.postMenu(request,response);

}

exports.getAllPlatos = function(request, response){
	db.getAllPlatos(request, response);
}


exports.editarRestaurante = function(request, response){

	db.editarRestaurante(request, response);
}

exports.getRestaurantes = function(request, response){

	db.getRestaurantes(request, response);
}





