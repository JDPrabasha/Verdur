export default class inventory{
    constructor(params){
        this.id         = params.id;
        this.type       = params.type;
        this.image      = params.image;
        this.name       = params.name;
        this.restockDate= params.restockedDate;
        this.quantity   = params.quantity;
        this.unit       = params.unit;
        this.max        = params.max;
        this.safe       = params.safe;
        this.critical   = params.critical;

        this.itemcode   = this.type[0]+"/"+this.id;
        this.percentage = parseInt((this.quantity)/(this.max)*100)
    }

    printInventoryRecord(){
        let table = $("#inventory-tbody"),
            row1 = $(document.createElement('tr')).attr("id",`rec-${parseInt(this.id)}`).html(`
                <td><img class="icon-1" src="${this.image}"></td>
                <td>${this.itemcode}</td>
                <td>${this.name}</td>
                <td>${this.type}s</td>
                <td>${this.restockDate}</td>
                <td>${this.quantity+" "+this.unit}</td>
                <td> ${this.percentage}% <input type="range" value="${this.percentage}" disabled id="${this.id}"></td>
                <td>
                    <a class=" text-center ml-6" style="width: 35%;" href="/Client/kitchenmanager/disabledish.html"><i class="material-icons">info</i></a>      
                </td>
                <td>  <a class="dropdown" id="expand-${parseInt(this.id)}"><i class="material-icons">expand_more</i></a></td>
            `),
            row2 = $(document.createElement('tr')).attr("id",`rec-e-${parseInt(this.id)}`).attr("class","hidden").html(`
                <td colspan="8">
                    <form class="form_reset">
                        <div class="row alignmiddle">
                            <div class="col-2">
                                <img class="icon-2" src="${this.image}">   
                            </div>
                            <div class="col-6 text-grey text-bold text-1 mt-6">
                                <div class="row pb-8">
                                    <div class="col-4 "> <label for="form_max">Maximum Capacity :</label></div>
                                    <div class="col-4"> <input type="text" id="form_max" style="width: 30%; font-weight: bold; font-size: 1em;" value="${this.max+this.unit} " disabled></div>
                                </div>
                                <div class="row pb-8">
                                    <div class="col-4 "> <label for="form_dif">Safe Level :</label></div>
                                    <div class="col-4"><input type="text" id="form_dif" value="${this.safe+this.unit}" style="width: 30%; font-weight: bold; font-size: 1em;" disabled></div>
                                </div>
                                <div class="row pb-8">
                                    <div class="col-4"><label  for="form_min">Critical Level :</label></div>
                                    <div class="col-4"> <input type="text" id="form_min" value="${this.critical+this.unit}" style="width: 30%; font-weight: bold; font-size: 1em;" disabled></div>
                                </div>
                            </div>
                            <div class="col-4 text-2  text-bold ">
                                  <div class="row pt-12">
                                    <div class="col-6 text-grey"><label  for="form_min"> Quantity:</label></div>
                                    <div class="col-4"> <input type="text" id="form_min" value="${this.quantity+this.unit}" style="width: 40%;  font-size: 0.8em;" disabled ></div>
                                    <div class="col-1"><i class="material-icons" >drive_file_rename_outline</i></div>
                                </div>
    
                            </div>
                        </div> 
                    </form>
                </td>
                <td>
                    <a class="dropdown" id="hide-${parseInt(this.id)}" ><i class="material-icons"> expand_less</i></a>
                </td>
            `)
        table.append(row1,row2);
    }

}