const Scholarship = require('../models/scholarship.model')



const getAllscholarships = async (req,res)=>{

    const allscholarships = await Scholarship.find();

    if (!allscholarships)
    {
     res.status(400).json({
         hasError : true
     })    
    }

    res.status(200).json({
        hasError : false,
        scholarships : allscholarships
    })

}

const getSpecificscholarship = async (req,res)=>{

    const id = req.params.id

    const scholarship = await Scholarship.findOne({_id : id});

    if(!scholarship)
    {
        res.status(405).json({
            hasError : true,
            message : "scholarship not found" 
        })
    }

    res.status(200).json({
        hasError :false,
        scholarship
    })

}

const addAnscholarship = async(req,res)=>{

    const scholarship = req.body


    const result = await Scholarship.create(scholarship)

    if(!result){
        res.status(400)
    }
    

    res.status(200).json({
        hasError : false,
        message : "scholarship has been updated"
    })
}

const updateAnscholarship = async (req,res)=>{
    const scholarship = req.body
    const id = req.params.id

    const result = await Scholarship.updateOne({_id : id} , scholarship , {new : true})

    if(!result)
    {
     return   res.status(400).json({
            hasError : true,
            message : "failed to update"
        })
    }


    res.status(200).json({
        hasError : false,
        message : "scholarship has been updated successfully"
    })
}

const deleteAnscholarship = async(req,res)=>{
    const id = req.params.id

    const result = await Scholarship.deleteOne({_id : id})

    if(!result){
      res.status(400).json({
        hasError : true,
        message : "failed tp delete the scholarship"
      })
    }

    res.status(200).json({
        hasError : false ,
        mesage :"scholarship deleted successsfully"
    })
}

module.exports = {
    getSpecificscholarship,
    getAllscholarships,
    deleteAnscholarship,
    updateAnscholarship,
    addAnscholarship
}