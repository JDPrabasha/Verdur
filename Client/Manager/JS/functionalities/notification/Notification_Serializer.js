export class Notification_Serializer{
    static doSerialize(data){
        return{
            id : data.id,
            type : data.type,
            description : data.description,
            targetID : data.targetID
        }
    }
}