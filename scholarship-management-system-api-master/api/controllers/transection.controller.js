const Transection = require('../models/transection.model')



const getAlltransections = async (req,res)=>{

    const alltransections = await Transection.find();

    if (!alltransections)
    {
     res.status(400).json({
         hasError : true
     })    
    }

    res.status(200).json({
        hasError : false,
        transections : alltransections
    })

}

const getSpecifictransection = async (req,res)=>{

    const id = req.params.id

    const transection = await Transection.findOne({_id : id});

    if(!transection)
    {
        res.status(405).json({
            hasError : true,
            message : "transection not found" 
        })
    }

    res.status(200).json({
        hasError :false,
        transection
    })

}

const addtransection = async(req,res)=>{
    console.log("transection")
    const transection = req.body
    

    const result = await Transection.create(transection)

    if(!result){
        res.status(400)
    }
    

    res.status(200).json({
        hasError : false,
        message : "transection has been Added"
    })
}

const updatetransection = async (req,res)=>{
    const transection = req.body
    const id = req.params.id

    const result = await Transection.updateOne({_id : id} , transection , {new : true})

    if(!result)
    {
     return   res.status(400).json({
            hasError : true,
            message : "failed to update"
        })
    }


    res.status(200).json({
        hasError : false,
        message : "transection has been updated successfully"
    })
}

const deletetransection = async(req,res)=>{
    const id = req.params.id

    const result = await Transection.deleteOne({_id : id})

    if(!result){
      res.status(400).json({
        hasError : true,
        message : "failed tp delete the transection"
      })
    }

    res.status(200).json({
        hasError : false ,
        mesage :"transection deleted successsfully"
    })
}

module.exports = {
    getSpecifictransection,
    getAlltransections,
    deletetransection,
    updatetransection,
    addtransection
}