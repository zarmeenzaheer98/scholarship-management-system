const {Router} = require('express');
const { getAllapplications, getSpecificapplication, addAapplication, updateAapplication, deleteAapplication } = require('../controllers/application.controller');
const applicationRouter = Router()




applicationRouter.get('/' , getAllapplications);
applicationRouter.get('/:id' , getSpecificapplication);
applicationRouter.post('/add' , addAapplication);
applicationRouter.patch('/update/:id' , updateAapplication);
applicationRouter.delete('/delete/"id' , deleteAapplication)


module.exports = {
    applicationRouter
}