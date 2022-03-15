export class dish_new_serialier {
    static doSerialize(data) {
        return {
            id            : data.dishID,
            name          : data.dishName,
            description   : data.description,
            defaultPrice  : data.defaultPrice,
            image         : data.image,
            code          : data.code,
            ingredients   : data.ingredients,
            updatedDish   : data.updatedDish,
            estTime       : data.estimatedTime
        }
    }
}