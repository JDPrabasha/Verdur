export class supplier{
    constructor(params)
        {
            this.supplierid = params.supplierid;
            this.suppliername = params.suppliername;   
            this.price = params.price; 
        }
    
    printsupplier()
    {
      
            // var supplierdetail = $(document.createElement('div')).html(`<option value="${this.suppliername}-${this.price}"`);
            console.log(this.supplierid)
            var supplierdetail = $(document.createElement('option')).attr("value", this.supplierid).html(`${this.suppliername}-${this.price}`);
            return supplierdetail;




            // var selectsupplier = $("#selectsupplier");

            // var supplierdetailall = $(document.createElement('option')).attr("id","0").attr("value", "All");
            // var supplierdetail = $(document.createElement('option')).attr("id",`${this.supplierid}`).attr("value", `${this.suppliername}-${this.price}`).html(`${this.suppliername}-${this.price}`);
            // selectsupplier.append(supplierdetailall,supplierdetail);
            // return selectsupplier;

           
            // var supplierdetailall = $(document.createElement('option')).attr("id","0").attr("value", "All");
            // var supplierdetail = $(document.createElement('option')).attr("id",`${this.supplierid}`).attr("value", `${this.suppliername}-${this.price}`).html(`${this.suppliername}-${this.price}`);
            // // selectsupplier.append(supplierdetailall,supplierdetail);
            // // supplierdetail.append(supplierdetailall);
            // var selectsupplier;
            // selectsupplier.append(supplierdetailall,supplierdetail);
            // return selectsupplier;

    }
}
