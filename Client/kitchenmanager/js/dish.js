import { ingredients } from "./ingredients.js";
export class dish{
    constructor(params)
    {
        this.dishid = params.dishid;
        this.name= params.dishname;
        this.defaultprice= params.defaultprice;
        this.estimatedtime= params.estimatedtime;
        this.dishcode= params.dishcode;
        this.description= params.description;
        this.image= params.image;
        this.enable= params.enable,
        this.estatus= params.estatus;
        this. defaultquantity =params.defaultquantity;
        this.ingredienttype = params.ingredienttype;
        this.ingredients = params.ingredients;
        this.unit = params.unit;
        this.ingid = params.ingid;
       
    } 
    printdish(){
        //check level resturns 0 if restock level is not reached
        var level = (this.ingredients.map(i=> new ingredients(i).checklevels())).filter(i => i==1).length
        // console.log((this.ingredients.map(i=> new ingredients(i).checklevels())))
        var buttonname;
        var buttoncolor;
        var functionname;
        var bgcolor
        var id
        if(this.enable == 1){
            buttonname = "Disable";
            buttoncolor = "tag-red";
            functionname = `disable(${this.dishid})`;
            id=`${this.dishid}`;
        }
        else{

            
            buttonname = "Enable";
            buttoncolor = "tag-green";
            // checks if stocks are not at restock level
            if(level==0){
                functionname = `enable(${this.dishid})`;
                id=`${this.dishid}`;
            }
            else{
                functionname=`error-${this.dishid}`;
                id=`error-${this.dishid}`;
            }
    
            bgcolor = "disabled-row";

        }
        // console.log(this.name)
        // console.log(level)
        var dish = $("#results");
            var card = $(document.createElement('div')).attr( "class","card").attr("id","dishcard-"+this.dishid).html(`  <img class="round-2" src="${this.image}">
            <div class="row">
            <div class="col-10"> <h1 class=" m-1 text-upper">${this.name}</h1></div>
            <div class="col-2"> <h1 class=" m-1 text-upper">${this.dishcode}</h1></div>
            </div>
            <hr class="mb-2 mt-2">
            <div class="flex-space-evenly">
            <button onclick="${functionname}" class=" btn ${buttoncolor} " id=${id} style="width: 100px;">${buttonname}</button>
                <a class= "btn tag-green" href="/Client/kitchenmanager/updatedish.html?id=${this.dishid}"><i class="material-icons icon">edit</i></a>
              <button class="btn tag-red" onclick="deletedish(${this.dishid})"><i class="material-icons icon">close</i></button>
            </div>`);
        dish.append(card);
    }

    printdishbyingredient(){
        var buttonname;
        var buttoncolor;
        var functionname;
        var bgcolor
        if(this.enable == 1){
            buttonname = "Disable";
            buttoncolor = "tag-red";
            functionname = `disable(${this.dishid})`;
        }
        else{
            buttonname = "Enable";
            buttoncolor = "tag-green";
            functionname = `enable(${this.dishid})`;
            bgcolor = "disabled-row";

        }

        var disableallbuttonname;
        var disableallbuttoncolor;
        var disableallfunctionname;
      
        if(this.enable == 1){
            disableallbuttonname = "Disable";
            disableallbuttoncolor = "tag-red";
            disableallfunctionname = `disableall(${this.ingid})`;
        }
        else{
            disableallbuttonname = "Enable";
            disableallbuttoncolor = "tag-green";
            disableallfunctionname = `enableall(${this.ingid})`;

        }
        var dishbyingredients = $("#dishbying");
      
        var dishtable = $(document.createElement('tr')).attr("id","dishbying").attr("class",`${bgcolor}`).html(` <td><img  class="icon-6" src="${this.image}" ></td>
        <td>${this.name}</td>
        <td>${this.ingredienttype}</td>
        <td>${this.defaultquantity} ${this.unit}</td>
        <td><div ><button onclick="${functionname}" class=" ${buttoncolor} " style="width: 100px;">${buttonname}</button></div></td>
        `)
        dishbyingredients.append(dishtable);

     



        
    }

    printupdatedish(){



 
    $("#dishcode").val(this.dishcode);
    $("#dishname").val(this.name);
    $("#estimatedTime").val(this.estimatedtime);
    $("#description").html(this.description);
    $("#image-label").attr("src",this.image);
    console.log(this.ingredients);

    $("#ingredients-list").html(this.ingredients.map(i=> new ingredients(i).printingredients()))

    

    


    }

  
}

 