var express = require('express');
var path = require('path');

var app = express();
var http=require('http').Server(app);

var router=require('./app/router.js');

app.set('port', 9009);

/*Statics*/
app.use(express.static('./app/public'));
/*Template engine*/
app.use('/',router);

http.listen(app.get('port'),function(){
    console.log("DRisk Aplication running in a port " + app.get('port'));
});