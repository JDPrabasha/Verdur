class inventory{
    constructor(params)
    {
        this.ingid = params.ingid;
        this.itemcode = params.itemcode;
        this.itemtype = params.type;
        this.ingname = params.ingname;
        this.quantity = params.quantity;
        this.maxlevel = params.maxlevel;
        this.safelevel = params.safelevel;
        this.restocklevel = params.restocklevel;
        this.image = params.image;
        this.restockdate = params.restockdate;
        this.unit = params.unit;

        this.presentage = parseInt(this.quantity/this.maxlevel*100)
    }

    printinventory(){
         
        

        let inventory = $("#inventorytable"),
        
        rowclose = $(document.createElement('tr')).attr("id",`emp-${this.ingid}`).html(`
        <td><img  class="icon-5" src=${this.image}></td>
        <td>${this.itemcode}</td>
        <td>${this.ingname}</td>
        <td > <div class="tag-yellow px-3 " style="width: fit-content; padding-top: 2px; padding-bottom: 2px; border-radius: 6px;"> ${this.itemtype}</div></td>
        <td>${this.restockdate}</td>
        <td>${this.quantity} ${this.unit}</td>
        <td> ${this.presentage}% <input type="range" min="1" max="100" value="${this.presentage}" disabled id="1"></td>
        <td><a class=" text-center ml-6" style="width: 35%;" href="/Client/kitchenmanager/disabledish.html?ingID=${this.ingid}"><i class="material-icons text-darkgrey">info</i></a>  
            <a class=" text-center  ml-6" style="width: 35%;" href="/Client/kitchenmanager/createrestockmenu.html?id=${this.ingid}"><i class="material-icons text-darkgrey ">fact_check </i></a>
        <td>  <a class="dropdown" onclick="expand(${this.ingid})"><i class="material-icons text-darkgrey">expand_more</i></a></td>    
        `),

  
    rowopen = $(document.createElement('tr')).attr("id",`emp-e-${this.ingid}`).attr("class","hidden").html(`
    <td colspan="8">
                    <form class="form_reset">
                        <div class="row alignmiddle">
                            <div class="col-2">
                                <img class="icon-8" src=${this.image}>
                            </div>
                            <div class="col-6 text-grey text-bold text-1 mt-6" >
                                <div class="row pb-8">
                                    <div class="col-4 "> <label for="form_max">Maximum Quantity:</label></div>
                                    <div class="col-4"> <input type="text" id="form_max" style="width: 30%; font-weight: bold; font-size: 1em;" value="${this.maxlevel} ${this.unit}" disabled></div>
                                </div>
                                <div class="row pb-8">
                                    <div class="col-4 "> <label for="form_dif">Default Quantity:</label></div>
                                    <div class="col-4"><input type="text" id="form_dif" value="${this.safelevel} ${this.unit}" style="width: 30%; font-weight: bold; font-size: 1em;" disabled></div>
                                </div>
                                <div class="row pb-8">
                                    <div class="col-4"><label  for="form_min">Minimum Quantity:</label></div>
                                    <div class="col-4"> <input type="text" id="form_min" value="${this.maxlevel} ${this.unit}" style="width: 30%; font-weight: bold; font-size: 1em;" disabled></div>
                                </div>
                            </div>
                            <div class="col-4  text-bold ">
    
                                  <div class="row pt-12">
                                    <div class="col-3 text-grey" style="font-size: 1.2em;"><label  for="form_min"> Quantity:(${this.unit})</label></div>
                                    <div class="col-4"  style="font-size: 1.3em;"> <input class = " text-bold" type="text" id="stock" value="${this.quantity} " style="width: 40%;" disabled ></div>
                                    <div class="col-1">  <div id="editstock-${parseInt(this.ingid)}" class="btn tag-green">Edit</div></div>
                                    <div id="done-edit-${parseInt(this.ingid)}" style="display:none"><i class="material-icons" style="font-size:18px;cursor:pointer">done</i></div>
                                    <div id="cancel-edit-${parseInt(this.ingid)}" style="display:none"><i class="material-icons" style="font-size:18px;cursor:pointer">cancel</i></div>
                                   
                                    </div>
    
                            </div>
                        </div> 
                    </form>
                </td>
                <td>
                    <a class="dropdown" onclick="hide(${this.ingid})"><i class="material-icons"> expand_less</i></a>
                </td>`)

    inventory.append(rowclose);
    inventory.append(rowopen);

    }
}