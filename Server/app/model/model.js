
/*var mysql = require('mysql');

var db = mysql.createConnection({
  host:     '127.0.0.1',
  user:     'root',
  password: '',
  database: 'FoodEspol'
});
*/


exports.validarLogIn = function(request, response){ 

	//var user= request.body.user;
	//var pass= request.body.pass;
	console.log('requerimiento llego al servidor ')
	/*
	console.log(ruc);
	var queryString = "SELECT * FROM Administrador where cedula="+user+" clave="+pass;
	console.log(queryString);
	
	db.query(queryString, function(err, rows, fields) {
	 	
	 	console.log(rows[0]);
	});
	*/
	response.json({estado: true} );

 	
}
