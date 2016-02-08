
/*var mysql = require('mysql');

var db = mysql.createConnection({
  host:     '127.0.0.1',
  user:     'root',
  password: '',
  database: 'FoodEspol'
});
*/
var contPlatos = 4;

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
		categorias: [0,1],
		foto: "imagens/arrozpollo.png"
	},
	"2":{
		nombre: "arroz con carne",
		precio: 1.50,
		categorias: [1,2,3],
		foto: "imagens/arrozpollo.png"
	},
	"3":{
		nombre: "arroz con pescado",
		precio: 1.50,
		categorias: [0,3],
		foto: "imagens/arrozpollo.png"
	},
	"4":{
		nombre: "arroz con arroz",
		precio: 1.50,
		categorias: [0,2],
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
	var idRest = -1;

	for (var it in Restaurantes){
		var rest = Restaurantes[it];
		var administrador = rest.administrador;

		if(administrador.usuario == usuario && administrador.clave == clave){
			idRest = parseInt(it);
		}
	}

	response.json({idRestaurante: idRest} );
 	
}

exports.guardarPlato = function(request, response){
	var plato = request.body;
	console.log("****El plato", plato);
	contPlatos+=1;

	var categorias = [];

	var strBase = "categoria";
	var cont = 1;

	var strIt = strBase + cont;
	for (var i = cont; i < 4; i++){
		cat = plato[strIt]
		if(cat){
			console.log(cat, strIt)
			categorias.push(parseInt(cat)) ;
		}
		cont +=1;
		strIt = strBase + cont;
	}

	var newPlato = {
		nombre: plato.nombre,
		precio: plato.precio,
		categorias: categorias,
		foto: "imagens/arrozpollo.png" 
	}

	console.log("***newPlato",newPlato);

	var idNewPlato = contPlatos.toString();
	platos[idNewPlato] = newPlato;
	response.json({idPlato: contPlatos});


}

exports.getMenus = function(request, response){
	idRestautante = request.query.idRestautante;
	console.log("Get Menus ",idRestautante);
	console.log(Restaurantes[idRestautante]);
	listMenu=Restaurantes[idRestautante].menu;
	var result=[];
	for (var i = 0; i <listMenu.length; i++) {
		menu=menus[listMenu[i]];
		result.push({id:listMenu[i],fecha: menu.fecha});
	};
	response.json({menus: result} );

}
