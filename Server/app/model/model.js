
/*var mysql = require('mysql');

var db = mysql.createConnection({
  host:     '127.0.0.1',
  user:     'root',
  password: '',
  database: 'FoodEspol'
});
*/
var formidable = require('formidable');


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
 	latitud: -2.145130,
 	longitud: -79.967372,
 	numClientes: 12,
 	logo: "imagenes/logo.jpg",
 	platos: [1,2],//id de los platos
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
		idRestaurante:1,
		nombre: "arroz con pollo",
		precio: 1.50,
		foto: "imagens/arrozpollo.png",
		catPiqueo:0,
		catComidaRapida:1,
		catDesayuno:0,
		catAlmuerzo:1,

	},
	"2":{
		idRestaurante:1,
		nombre: "arroz con carne",
		precio: 1.50,
		foto: "imagens/arrozpollo.png",
		catPiqueo:1,
		catComidaRapida:1,
		catDesayuno:0,
		catAlmuerzo:1
	},
	"3":{
		idRestaurante:2,
		nombre: "arroz con pescado",
		precio: 1.50,
		foto: "imagens/arrozpollo.png",
		catPiqueo:1,
		catComidaRapida:0,
		catDesayuno:0,
		catAlmuerzo:1
	},
	"4":{
		idRestaurante:2,
		nombre: "arroz con arroz",
		precio: 1.50,
		foto: "imagens/arrozpollo.png",
		catPiqueo:0,
		catComidaRapida:1,
		catDesayuno:0,
		catAlmuerzo:1
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
	var incoming = new formidable.IncomingForm();
    //Carpeta donde se guardarán los archivos.
    var rutasimagen = 'app/public/imagenes/';
    var rutaPublica = '/imagenes/';

    incoming.uploadDir = rutasimagen;


    incoming.on('fileBegin', function(field, file){
        if(file.name){
            file.path = file.path + file.name;
            rutaPublica = rutaPublica + file.name;

         }
    })
    incoming.on('file', function(field, file){
    	console.log('Archivo recibido');
    });
    incoming.parse(request,function(err,field,file){
      	console.log(field);

      	plato = {
      		idRestaurante: parseInt(field.idRestaurante),
			nombre: field.nombre,
			precio: parseFloat(field.precio),
			foto: rutaPublica,
			catPiqueo: parseInt(field.catPiqueo),
			catComidaRapida:parseInt(field.catComidaRapida),
			catDesayuno: parseInt(field.catDesayuno),
			catAlmuerzo:parseInt(field.catAlmuerzo)
      	}

      	contPlatos+=1;
      	var idPlato = contPlatos.toString();
      	platos[idPlato] = plato

      	var idRestaurante = (field.idRestaurante).toString();
      	Restaurantes[idRestaurante].platos.push(contPlatos);

      	console.log("***platos", platos);
      	console.log("*** Restaurantes", Restaurantes);

      	response.json({"response": "Saved"});
    });
    incoming.on('error',function(err){
        console.log(err);
        response.json({'response':"Error"});
    })
    incoming.on('end', function(fields, files) { 
    	console.log('end');
    });
    
}

exports.editarRestaurante = function(request, response){

	var incoming = new formidable.IncomingForm();
    //Carpeta donde se guardarán los archivos.
    var rutasimagen = 'app/public/imagenes/';
    var rutaPublica = '/imagenes/';

    incoming.uploadDir = rutasimagen;


    incoming.on('fileBegin', function(field, file){
        if(file.name){
            file.path = file.path + file.name;
            rutaPublica = rutaPublica + file.name;

         }
    })
    incoming.on('file', function(field, file){
    	console.log('Archivo recibido');
    });
    incoming.parse(request,function(err,field,file){
      	
      	console.log("me llego esta guevada",field);
      	dRest = parseInt(field.idRestaurante);
      	restaurante = Restaurantes[idRest];
 		restaurante.nombre = field.nombre;
 		restaurante.administrador.nombre = field.nombreProp;
 		restaurante.capacidad = parseInt(field.capacidad);
 		restaurante.latitud = parseFloat(field.latitud);
 		restaurante.longitud = parseFloat(field.longitud);


 		bandPath = parseInt(field.bandPath);
 		if(bandPath == 1){
 			restaurante.logo = rutaPublica;	
 		}
 		
 		console.log("****El restaurante", Restaurantes[idRest]);
      	response.json({"response": "Saved"});
    });
    incoming.on('error',function(err){
        console.log(err);
        response.json({'response':"Error"});
    })
    incoming.on('end', function(fields, files) { 
    	console.log('end');
    });

}

exports.getRestaurante = function(request, response){
	var idRestaurante = request.query.idRestaurante;

	var idRest = idRestaurante.toString();
	var restaurante = Restaurantes[idRest];

	var Rest = {
		nombreProp: restaurante.administrador.nombre,
		nombre: restaurante.nombre,
		capacidad: restaurante.capacidad,
		latitud: restaurante.latitud,
		longitud: restaurante.longitud,
		logo: restaurante.logo
	}

	response.json(Rest);

}


exports.getMenus = function(request, response){
	idRestautante = request.query.idRestautante;
	console.log("Get Menus ",idRestautante);
	listMenu=Restaurantes[idRestautante].menu;
	var result=[];
	console.log("Menus ",listMenu);
	for (var i = 0; i <listMenu.length; i++) {
		menu=menus[listMenu[i]];

		console.log("Menu ",menus);
		result.push({id:listMenu[i],fecha: menu.fecha});
	};
	response.json({menus: result} );

}

exports.getPlatos = function(request, response){
	idRestautante = request.query.idRestautante;
	console.log("Get platos ",idRestautante);
	listPlatos=Restaurantes[idRestautante].platos;
	var result=[];
	for (var i = 0; i <listPlatos.length; i++) {
		plato=platos[listPlatos[i]];
		result.push({id:listPlatos[i],nombre:plato.nombre,precio:plato.precio,foto:plato.foto});
	};
	response.json({platos: result} );

}

exports.eliminarMenu = function(request, response){
	idRestautante = request.query.idRestautante;
	idMenu = request.query.idMenu;
	auxListmenus=Restaurantes[idRestautante].menu;
	for (var i = 0; i < auxListmenus.length; i++) {
		if(auxListmenus[i]==idMenu){
			auxListmenus.splice(i,1);
			menus[idMenu]=null;
			break;
		}
	}
	response.json({ban: true} );
}
exports.eliminarPlato = function(request, response){
	idRestautante = request.query.idRestautante;
	idPlato = request.query.idPlato;
	auxListPlatos=Restaurantes[idRestautante].platos;
	auxListmenus=Restaurantes[idRestautante].menu;
	//elimino el plato de todos los menus del restaurante
	console.log('%%%%%%%% ',auxListmenus)
	if(auxListmenus){
		for (var i = 0; i < auxListmenus.length; i++) {
			auxMenuPlatos=menus[auxListmenus[i]].platos;
			console.log("iteracion ",auxListmenus[i])
			for (var i = 0; i < auxMenuPlatos.length; i++) {

				if(auxMenuPlatos[i]==idPlato){
					auxMenuPlatos.splice(i,1);
					console.log("elimino ",auxMenuPlatos)
					//break;
				}
			
			}

		}
	}

	for (var i = 0; i < auxListPlatos.length; i++) {
		if(auxListPlatos[i]==idPlato){
			auxListPlatos.splice(i,1);
			platos[idPlato]=null;
			break;
		}
	}
	response.json({ban: true} );
	
}

exports.getPlatosMenu = function(request, response){
	idRestautante = request.query.idRestautante;
	idMenu = request.query.idMenu;
	listPlatos=menus[idMenu].platos;
	var result=[];
	for (var i = 0; i <listPlatos.length; i++) {
		plato=platos[listPlatos[i]];
		result.push({id:listPlatos[i],nombre:plato.nombre,precio:plato.precio,foto:plato.foto});
	};
	console.log("la respuesta de los Platos del Menu es ", result)
	response.json({platos: result} );
}




