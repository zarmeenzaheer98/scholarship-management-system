const mongoose = require('mongoose');

const scholarshipSchema = mongoose.Schema({
    title :{
        type : String,
        required : true
    },
    from : {
        type : String,
        required : true
    },
    province : {
        type : String,
        required:true
    },
    cgpa : {
        type : String,
        required : true
    },
    date : {
        type : String,
        required: true
    },
    degree : {
        type : String,
        required : true
    },
    description : {
        type : String , 
        required : true
    }
})

module.exports = mongoose.model('scholarship' , scholarshipSchema)
