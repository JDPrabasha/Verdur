export class Inventory_Serializer {

    static doSerialize(data) {
        return {
            id: data.ingid,
            type: data.type,
            image: data.image,
            name: data.ingname,
            restockedDate: "2021-04-05",
            quantity: data.quantity,
            unit: data.unit,
            max: data.max,
            safe: data.normal,
            critical: data.min
        }
    }
}