var html_dir = './app/views/';
var db = require('../model/model.js');


exports.obtenerPaises = function(request, response){
	
	db.obtenerPaises().then(function (result){

		lista = [];
		for (var i = 0; i< result.length;i ++){
			var pais = result[i].dataValues;
			lista.push(pais);

		}
		response.render('paises', {listPaises: lista} );

	});
};


exports.crearPais = function(request, response){
	response.render("crearPais");
}

exports.guardarPais = function(request, response){
	var codigo = request.query.cod;
	var descripcion = request.query.desc;

	db.crearPaises(codigo, descripcion).then( function(result){
		console.log("redirijiendo");
		response.json({respuesta: "Bien"});
	});
}

exports.eliminarPais = function(request, response){
	var codigo = request.query.cod;
	console.log("eliminado", codigo);
	db.eliminarPaises(codigo).then(function(resultados){
		
		response.json({respuesta: "Bien"});
	});
}

exports.editarPais = function(request, response){
	var codigo = request.query.cod;
	var desc = request.query.desc;

	console.log("editado", codigo,desc);
	db.editarPais(codigo, desc).then(function(resultados){

		response.json({respuesta: "Bien"});
	})
	
}
