const Application = require('../models/application.model')

const getAllapplications = async (req,res)=>{

    const allapplications = await Application.find();

    if (!allapplications)
    {
     res.status(400).json({
         hasError : true
     })    
    }

    res.status(200).json({
        hasError : false,
        applications : allapplications
    })

}

const getSpecificapplication = async (req,res)=>{

    const id = req.params.id

    const application = await Application.findOne({_id : id});

    if(!application)
    {
        res.status(405).json({
            hasError : true,
            message : "application not found" 
        })
    }

    res.status(200).json({
        hasError :false,
        application
    })

}

const addAapplication = async(req,res)=>{

    const application = req.body


    const result = await Application.create(application)

    if(!result){
        res.status(400)
    }
    

    res.status(200).json({
        hasError : false,
        message : "application has been updated"
    })
}

const updateAapplication = async (req,res)=>{
    const application = req.body
    const id = req.params.id

    const result = await Application.updateOne({_id : id} , application , {new : true})

    if(!result)
    {
     return   res.status(400).json({
            hasError : true,
            message : "failed to update"
        })
    }


    res.status(200).json({
        hasError : false,
        message : "application has been updated successfully"
    })
}

const deleteAapplication = async(req,res)=>{
    const id = req.params.id

    const result = await Application.deleteOne({_id , id})

    if(!result){
      res.status(400).json({
        hasError : true,
        message : "failed tp delete the application"
      })
    }

    res.status(200).json({
        hasError : false ,
        mesage :"application deleted successsfully"
    })
}

module.exports = {
    getSpecificapplication,
    getAllapplications,
    deleteAapplication,
    updateAapplication,
    addAapplication
}