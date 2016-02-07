// http://localhost:9009/
var html_dir = './app/views/';
var db = require('../model/model.js');


exports.validarLogIn = function(request, response){
	console.log("request")
	db.validarLogin(request,response);
};

exports.getPlatos = function(request, response){
	

}

