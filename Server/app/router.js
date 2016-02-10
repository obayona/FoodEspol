var Controller = require('./controller/Controller');

var express=require('express');
var router=express.Router();

router.get('/validarLogIn',Controller.validarLogIn);
router.get('/getPlatos',Controller.getPlatos);
router.get('/getAllPlatos',Controller.getAllPlatos);
router.post('/guardarPlato', Controller.guardarPlato);
router.get('/getMenus',Controller.getMenus);
router.get('/getPlatos',Controller.getPlatos);
router.get('/eliminarMenu',Controller.eliminarMenu);
router.get('/eliminarPlato',Controller.eliminarPlato);
router.get('/getPlatosMenu',Controller.getPlatosMenu);
router.get('/eliminarPlatoMenu',Controller.eliminarPlatoMenu);
router.get('/getRestaurante', Controller.getRestaurante);
router.post('/postMenu', Controller.postMenu);
router.post('/editarRestaurante', Controller.editarRestaurante);
router.get('/getRestaurantes', Controller.getRestaurantes);
router.get('/getPlato', Controller.getPlato);


module.exports = router;

