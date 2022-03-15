class inventoryserializer{
    static doserializer(data){
        return{
            ingid: data.ingid,
            itemcode: data.itemcode,
            type: data.itemtype,
            ingname: data.ingname,
            quantity: data.quantity,
            maxlevel: data.maxquantity,
            safelevel: data.safelevel,
            restocklevel: data.restocklevel,
            image: data.image,
            restockdate: data.restockdate,
            unit: data.unit

        }
    }
}