const express = require("express");
const mongoose = require("mongoose")
const app = express()

const {scholarshipRouter} = require('./api/routers/scholarship.router')
const {applicationRouter} = require('./api/routers/application.router');
const {transectionRouter} = require('./api/routers/transection.router');


app.use(express.json());
app.set('content/type' , 'application/json')


// connection to datbase

mongoose.connect("mongodb://localhost:27017/SMS",{ useUnifiedTopology: true ,useNewUrlParser: true})
.then(()=>{
    console.log("DataBase Connected");

  // making server live
    app.listen(300 , ()=>{
        console.log("Server is live on localhost:300");
    })

  }).catch(err=>{
    console.log(err);
});


app.use('/scholarship' , scholarshipRouter)
app.use('/application' , applicationRouter)
app.use('/transection' , transectionRouter)
