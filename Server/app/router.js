var Controller = require('./controller/Controller');

var express=require('express');
var router=express.Router();

router.post('/validarlogIn',Controller.validarLogIn);
router.get('/validarlogIn',Controller.validarLogIn);
module.exports = router;
