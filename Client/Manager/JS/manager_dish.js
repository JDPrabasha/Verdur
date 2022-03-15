class Dish {
    constructor(params){
        this.dishID = params.dishID;
        this.dishName = params.dishName;
        this.defaultPrice = params.defaultPrice;
        this.image = params.image;
    }

    printDish(){
        var dish = $("#cards");
            var card = $(document.createElement('div')).attr("class","card").attr("id",this.dishID).html(`
                    <img class="round-2" src="${this.image}" alt="">
                    <h1 class=" m-1">${this.dishName}</h1>
                    <hr class="mb-2 mt-2">
                    <div class="flex-space-evenly">
                        <h1 class="text-2">Rs. ${this.defaultPrice}</h1>
                        <a class= "btn" style="background: #d9534f;" href="">Remove</a>
                    </div>`);

        dish.append(card);
    }
}