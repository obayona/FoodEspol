var Controller = require('./controller/Controller');

var express=require('express');
var router=express.Router();

router.get('/validarLogIn',Controller.validarLogIn);
router.get('/getPlatos',Controller.getPlatos);
module.exports = router;
