class Ingredient_list {
    constructor(params){
        this.ingID = params.ingID;
        this.ingName = params.ingName;
    }

    addListing(){
        var List = $("#items");
            var item = $(document.createElement("oprtion")).attr("value",this.ingID).html(this.ingName);
    }
}