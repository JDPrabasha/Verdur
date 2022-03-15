class Ingredient_list {
    constructor(params){
        this.ingID = params.ingID;
        this.ingName = params.ingName;
    }

    addListing(){
        var List = $("#items1");
            var item = $(document.createElement("option")).attr("value",this.ingID).html(`${this.ingName}`);
        List.append(item);
    }
}