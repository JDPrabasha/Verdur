class Dish_Requests {
    constructor(params){
        this.dishID = params.dishID;
        this.dishName = params.dishName;
        this.defaultPrice = params.defaultPrice;
        this.image = params.image;
        this.request = params.request;
    }

    printRequest(){
        var requests = $("#requests");
            var singleRecord = $(document.createElement('div')).attr("class","flex-space-evenly").html(`
                            <img class="round-2" src="${this.image}" alt="" style="width:90px;height:90px;">
                            <div style="flex-direction: column;">
                                    <h1 class=" m-1 text-upper ml-6">${this.request} Dish</h1>
                                <div class="ml-12 text-upper">
                                    ${this.dishName}
                                </div>
                                <div class="flex-space-evenly mt-3">
                                    <button class= "btn-confirm  pl-12 ml-6" onclick="displaypop()">Review</button>
                                    <button class= "btn-reject pl-12 pt-7 pb-7 ml-6" style = "padding-left:10px;padding-right:10px;padding-top:5px;padding-bottom:5px;" href="">Deny</button>
                                </div>
                                    <!-- <p>Rs.600</p> -->
                                
                            </div>`);
            var ruler = $(document.createElement('hr')).attr("class", "mb-2 mt-2")
           
        requests.append(singleRecord);
        requests.append(ruler);
    }
}