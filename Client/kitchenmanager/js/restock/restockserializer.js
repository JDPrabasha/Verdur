export class restockserializer{
    static doserializer(data){
        return{
            restockid: data.restockID,
            ingname: data.ingredientname,
            issuedate: data.requestedat,
            status:data.status,
            timeremain: data.deadline,
            inventory:data.inventory,
            supplier:data.supplier,
            
           
            
           
            
        }
    }

}
