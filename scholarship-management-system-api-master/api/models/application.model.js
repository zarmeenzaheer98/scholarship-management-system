const mongoose = require('mongoose');

const applicationSchema = mongoose.Schema({
    name :{
        type : String,
        required : true
    },
    cnic : {
        type : String,
        required : true
    },
    status : {
        type : String,
        required:true
    },
    cgpa : {
        type : String,
        required : true
    },
    degree : {
        type : String,
        required : true
    },
    
})

module.exports = mongoose.model('application' , applicationSchema)
