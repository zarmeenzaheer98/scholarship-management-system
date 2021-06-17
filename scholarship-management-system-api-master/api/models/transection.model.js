const mongoose = require('mongoose');

const transectionSchema = mongoose.Schema({
    transectionId :{
        type : String,
        required : true
    },
    amount : {
        type : String,
        required : true
    },
    reciever : {
        type : String,
        required:true
    },
    scholarship : {
        type : String,
        required : true
    },
    status : {
        type : String,
        required : true
    },
    
})

module.exports = mongoose.model('transection' , transectionSchema)
