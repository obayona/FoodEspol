var Controller = require('./controller/Controller');

var express=require('express');
var router=express.Router();

router.get('/validarLogIn',Controller.validarLogIn);
router.get('/getPlatos',Controller.getPlatos);
router.post('/guardarPlato', Controller.guardarPlato);
router.get('/getMenus',Controller.getMenus);
router.get('/getPlatos',Controller.getPlatos);
router.get('/eliminarMenu',Controller.eliminarMenu);
router.get('/eliminarPlato',Controller.eliminarPlato);
module.exports = router;

