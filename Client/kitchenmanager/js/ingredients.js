export class ingredients{
    constructor(params)
    {
        this.ingID = params.ingID;
        this.type = params.type;
        this.minimum = params.minimum;
        this.defaultv = params.defaultv;
        this.maximum = params.maximum;
        this.itemcode = params.itemcode;
        this.name = params.name;
        this.image = params.image;
        this.carbpg = params.carbpg;
        this.calpg = params.calpg;
        this.proteinpg = params.proteinpg;
        this.custquantity = params.custquantity;
        this.unit = params.unit;

        this.restocklevel = params.restocklevel;
        this.stock = params.stock;
    } 

    printingredients(){
       
        var ingredients = $(document.createElement('tr')).html(` <td> <img class="icon-4" src="${this.image}" alt=""></td>
        <td>${this.ingID}</td>
        <td>${this.name}</td>
        <td style="display:none;">${this.ingID}</td>
        <td><input type="text" value="${this.type}" style="width:fit-content" id ="ingtype" disabled></td>
        <td> <input type="text" value="${this.minimum}"style="width:fit-content" id="min"  disabled>${this.unit}</td>
        <td><input type="text" value="${this.defaultv}"style="width:fit-content" id ="default"  disabled> ${this.unit}</td>
        <td><input type="text" value="${this.maximum}"style="width:fit-content" id="max"  disabled> ${this.unit}</td>
        <td>  <div id="done-edit-${parseInt(this.ingID)}" style="display:none"><i class="material-icons" style="font-size:18px;cursor:pointer">done</i></div>
        <div id="cancel-edit-${parseInt(this.ingID)}" style="display:none"><i class="material-icons" style="font-size:18px;cursor:pointer">cancel</i></div>
        <button id="edit-${parseInt(this.ingID)}" class="btn tag-green">Edit</button></td>
       `);

       


        return ingredients;
    }

    getingid(){
        var ingid = $(document.createElement('div')).html(`${this.ingID}`);

        return ingid;
    }
    getingtype(){
         var ingtype = $(document.createElement('div')).html(`${this.type}`);
         return ingtype;
    }

    printingredientdetails()
    {
        $("#ingname").val(this.name);
        $("#ingtype").val(this.type);
        $("#ingimage").val(this.image);
        $("#ingiunit").val(this.unit);

    }

    // must return 0 if restock level is not met or else 1
    checklevels(){
        // console.log(this.stock<=this.restocklevel)
        if(this.stock<=this.restocklevel){
            return 1
        }else{
            return 0
        }
    }
}