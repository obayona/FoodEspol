var express = require('express');
var bodyParser = require("body-parser");
var path = require('path');

var app = express();
var http=require('http').Server(app);

var router=require('./app/router.js');


var ip = require('ip');
app.set('port', 9009);
app.set('ipAddress',ip.address()); // my ip address

/*parser*/
app.use(bodyParser.urlencoded({extended : true}));
app.use(bodyParser.json());

//public
app.use(express.static('./app/public'));
/*router*/
app.use('/',router);

http.listen(app.get('port'),function(){
    console.log("DRisk Aplication running in a port " + app.get('port'));
});