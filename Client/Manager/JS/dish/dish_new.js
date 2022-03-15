import { Dish_Ingredients } from "./dish_ingredients.js";
import { dish_new_serialier } from "./dish_new_serializer.js";

export class dish_new {

    constructor(params) {
        this.id = params.id;
        this.name = params.name;
        this.description = params.description;
        this.defaultPrice = params.defaultPrice;
        this.image = params.image;
        this.code = params.code;
        this.ingredients = params.ingredients;
        if (params.updatedDish != undefined) {
            this.updatedDish = dish_new_serialier.doSerialize(params.updatedDish);
        }
        this.estTime = params.estTime;
    }

    updateDishPop() {
        $("#name").html(this.name);
        $('#description').html(this.description);
        $('#image').attr('src', this.image);
        var ingredients = this.ingredients.map(i => new Dish_Ingredients(i).printingredients());
        $('#ingredients').html(ingredients);
        $('#cost-in').html("Rs. "+this.defaultPrice.toFixed(0))
        $(`#price-out`).html("Rs. "+ (this.defaultPrice*1.1).toFixed(0))
        $('#time').html(this.estTime + " mins")
        $('#approve').attr('val',this.id)
        $('#reject').attr('val',this.id)
    }

    updateDishPopUpdated() {
        if (this.name == this.updatedDish.name) {
            $("#name").html(this.name);
        } else {
            $("#name").html(this.updatedDish.name + "<br><div style=\"font-size:2pc\"><del>" + this.name + "</del></div>")
        }
        if (this.description == this.updatedDish.description) {
            $('#description').html(this.description);
        } else {
            $('#description').html(this.updatedDish.description + "<br><del>" + this.description + "</del></div>");
        }
        $('#cost-in').html("Rs. "+this.defaultPrice.toFixed(0))
        $(`#price-out`).html("Rs. "+ (this.defaultPrice*1.1).toFixed(0))
        $('#time').html(this.estTime + " mins")
        

        $('#image').attr('src', this.image);
        var ing = [];

        console.log(JSON.stringify(this.ingredients) == JSON.stringify(this.updatedDish.ingredients))
        console.log("tfffffffffffffffffffff")
        var ingredients = this.ingredients.map(i => new Dish_Ingredients(i).printingredients2(this.updatedDish.ingredients))
        console.log("test")
        if (JSON.stringify(this.ingredients) != JSON.stringify(this.updatedDish.ingredients)) {
            var ingredientsnew = this.updatedDish.ingredients.map(i => new Dish_Ingredients(i).printingredientsUpdated(this.ingredients))
        }
        // var div = $(document.createElement('div')).attr("style", "filter: sepia(1)");
        var div = $(document.createElement('div'));
        div.append(ingredientsnew)

        $('#ingredients').html(ingredients)
        $('#ingredients').append(div)
        $('#approve').attr('val',this.id)
        $('#reject').attr('val',this.id)

    }
}