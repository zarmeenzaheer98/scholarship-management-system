
const {Router} = require('express');
const { getAllscholarships, getSpecificscholarship, addAnscholarship, updateAnscholarship, deleteAnscholarship } = require('../controllers/scholarship.cotroller');
const scholarshipRouter = Router()






scholarshipRouter.get('/' , getAllscholarships);
scholarshipRouter.get('/:id' , getSpecificscholarship);
scholarshipRouter.post('/add' ,addAnscholarship )
scholarshipRouter.patch('/update/:id' , updateAnscholarship);
scholarshipRouter.delete('/delete/:id' , deleteAnscholarship)

module.exports = {
    scholarshipRouter
}
