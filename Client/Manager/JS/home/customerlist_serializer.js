export class Cutomer_List_Serializer{
    static doSerialize(data){
        return{
            cusID   : data.custID,
            name    : data.firstName + " "+data.lastName,
            image   : data.image,
        }
    }
}