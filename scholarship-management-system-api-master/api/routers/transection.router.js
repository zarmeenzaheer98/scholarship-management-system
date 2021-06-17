const {Router} = require('express');
const { getAlltransections, getSpecifictransection, addtransection, updatetransection, deletetransection } = require('../controllers/transection.controller');
const transectionRouter = Router()




transectionRouter.get('/' , getAlltransections);
transectionRouter.get('/:id' , getSpecifictransection);
transectionRouter.post('/add' , addtransection);
transectionRouter.patch('/update/:id' , updatetransection);
transectionRouter.delete('/delete/:id' , deletetransection)


module.exports = {
    transectionRouter
}