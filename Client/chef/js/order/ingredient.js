export class Ingredient{
    constructor(params){
        this.ingID = params.ingID;
        this.name = params.name;
        this.image = params.image;
        this.custquantity = params.custquantity;
        this.unit = params.unit;

    }

    printdishingredient(){
        
        // var customizedish = $("#customizeddish");
        var ingredient_Row = $(document.createElement('tr')).html(`
       
        <td>${this.ingID}</td>
        <td><img class="icon-3" src="${this.image}"></td>
        <td>${this.name}</td>
        <td>${this.custquantity} ${this.unit}</td>`);
        
        // customizedish.append(ingredienttable);
        // console.log(customizedish)

        return ingredient_Row;
    }
}