class Ingredient_List {

    constructor(params){
        this.ingID = params.ingID;
        this.ingCode = params.ingCode;
        this.ingName = params.ingName;
        this.image = params.image;
        this.unit = params.unit
    }

    addSelection(){
        var selection = $("#Ingredient-Name");
            var singleoption = $(document.createElement('option')).val(this.ingID).html(this.ingName).attr("code",this.ingCode).attr("src",this.image);

        selection.append(singleoption);
    }
}