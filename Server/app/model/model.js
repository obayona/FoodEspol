
/*var mysql = require('mysql');

var db = mysql.createConnection({
  host:     '127.0.0.1',
  user:     'root',
  password: '',
  database: 'FoodEspol'
});
*/

var Restaurantes = {
 '1':{
 	nombre:"Panchos",
 	administrador:{
 		nombre: "Oswaldo Bayona",
 		usuario: "obayona",
 		clave: "hola"
 	},
 	capacidad: 20,
 	latitud: 123.65,
 	longitud: 12566.455,
 	numClientes: 12,
 	logo: "imagenes/panchos.png",
 	platos: [1,2,3,4],//id de los platos
 	menu: [1,2] //id de los menus
 },

 '2' : {
 	nombre:"Fajada",
 	administrador:{
 		nombre: "Kevin Ortiz",
 		usuario: "kevanort",
 		clave: "hola"
 	},
 	capacidad: 20,
 	latitud: 123.65,
 	longitud: 12566.455,
 	numClientes: 12,
 	logo: "imagenes/panchos.png",
 	platos: [3,4],//id de los platos
 	menu: [3,4] //id de los menus
 }

};

var platos = {
	"1":{
		nombre: "arroz con pollo",
		precio: 1.50,
		categorias: ["almuerzo", "piqueo"],
		foto: "imagens/arrozpollo.png"
	},
	"2":{
		nombre: "arroz con carne",
		precio: 1.50,
		categorias: ["almuerzo", "piqueo"],
		foto: "imagens/arrozpollo.png"
	},
	"3":{
		nombre: "arroz con pescado",
		precio: 1.50,
		categorias: ["almuerzo", "piqueo"],
		foto: "imagens/arrozpollo.png"
	},
	"4":{
		nombre: "arroz con arroz",
		precio: 1.50,
		categorias: ["almuerzo", "piqueo"],
		foto: "imagens/arrozpollo.png"
	}

}

var menus ={
	"1":{
		fecha: "07/02/2016",
		platos: [1,2,3,4]
	},
	"2":{
		fecha: "07/02/2016",
		platos: [1,2,3,4]
	},
	"3":{
		fecha: "07/02/2016",
		platos: [1,2,3,4]
	},
	"4":{
		fecha: "07/02/2016",
		platos: [1,2,3,4]
	}

}
exports.validarLogIn = function(request, response){ 

	//var user= request.body.user;
	//var pass= request.body.pass;
	usuario = request.query.user;
	clave = request.query.clave;
	console.log("recibi", usuario, clave);	
	console.log('requerimiento llego al servidor ')

	/*
	console.log(ruc);
	var queryString = "SELECT * FROM Administrador where cedula="+user+" clave="+pass;
	console.log(queryString);
	
	db.query(queryString, function(err, rows, fields) {
	 	
	 	console.log(rows[0]);
	});
	*/

	var idR = 3;

	response.json({idRestaurante: idR} );

 	
}
