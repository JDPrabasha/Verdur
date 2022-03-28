export class restockRequest {

    constructor(params) {
        this.restockID = params.restockID;
        this.item = params.item;
        this.supplier = params.supplier;
        this.issueddate = params.issueddate;
        this.approvalstatus = params.approvalstatus;
        this.oldtime = params.timeremain;
        this.quantity = params.quantity;
        this.price = params.price;
        this.status = params.status
        this.timeremain = this.getTimeRemaining(params.timeremain);

    }

    printApprovalPending() {
        //former return value was "" now its changed to pending
        if (this.approvalstatus == "" || this.approvalstatus == "pending") {
            this.approvalstatus = "Manager Pending"
        }
        var statuses;
        if (this.approvalstatus == "Manager Pending") {
            statuses = "ManagerPending"
        } else {
            statuses = "SupplierPending"
            this.approvalstatus = "Supplier Pending"
        }

        


        let table = $("#approvalList"),
            row = $(document.createElement('tr')).attr("id", "approval-" + this.restockID),
            restockID = $(document.createElement('td')).html(this.restockID),
            item = $(document.createElement('td')).html(this.item),
            supplier = $(document.createElement('td')).html(this.supplier),
            issuedate = $(document.createElement('td')).html(this.issueddate.split(" ")[0]),
            status = $(document.createElement('td')).html(`<div class="status_${statuses}"></div><div class="status_${statuses}_text">${this.approvalstatus}</div>`).attr("style", "width:14%"),
            timeremain = $(document.createElement('td')).html(this.timeremain).attr("time",this.oldtime).css("width", "18%").attr("id","time-cal-"+this.restockID),
            quantity = $(document.createElement('td')).html(this.quantity).css("text-align", "right"),
            price = $(document.createElement('td')).html("Rs. " + this.price.toLocaleString()).css("width", "10%").css("text-align", "right"),
            action = $(document.createElement('td')).attr("style", "width:14.6%");

        if (this.approvalstatus == "Manager Pending") {
            action.html('<button class="btn-confirm">Confirm</button> <button class="btn-reject ml-6">Reject</button>');
        }

        row.append(restockID, item, supplier, issuedate, status, timeremain, quantity, price, action);
        table.append(row);
    }


    printDeliveryPending() {
        // if (this.status == "delivering") {
        if (this.status == "pending") {
            this.status = "Pending"
        } else if (this.status == "delivered") {
            this.status = "Completed"
            this.oldtime = null;
            this.timeremain = null;
        }
        let table = $("#deliveryPendingList"),
            row = $(document.createElement('tr')).attr("id", "delivery-" + this.restockID),
            restockID = $(document.createElement('td')).html(this.restockID),
            item = $(document.createElement('td')).html(this.item),
            supplier = $(document.createElement('td')).html(this.supplier),
            issueddate = $(document.createElement('td')).html(this.issueddate.split(" ")[0]),
            status = $(document.createElement('td')).html(`<div class="status_${this.status}"></div><div class="status_${this.status}_text">${this.status}</div>`).attr("style", "width:10%"),
            timeremain = $(document.createElement('td')).html(this.timeremain).attr("time",this.oldtime).attr("id","time-cal-"+this.restockID),
            quantity = $(document.createElement('td')).html(this.quantity).css("text-align", "right"),
            price = $(document.createElement('td')).html("Rs. " + this.price).css("text-align", "right"),
            action = $(document.createElement('td')).attr("style", "width:8%");

        if (this.status == "Pending") {
            action.html('<button class="btn-confirm">Confirm</button>');
        }

        row.append(restockID, item, supplier, issueddate, status, timeremain, quantity, price, action);
        table.append(row);
    }

getTimeRemaining(x) {
    // console.log(new Date(x))
    // let difference = new Date(new Date(x) - new Date() - new Date("1970-01-01 11:00:00"))
    let difference = new Date(new Date(x) - new Date() + new Date().getTimezoneOffset() * 60 * 1000)



    let days = difference.getDate(),
    hours = difference.getHours(),
    mins = difference.getMinutes(),
    secs = difference.getSeconds(),
    timeremain;
    if(days!=null){
        timeremain = days+" Days " + hours +" hours "+ mins + " mins " + secs +" secs"  
    }else if(hours!=null){
        timeremain =  hours +" hours "+ mins + " mins " + secs +" secs"  
    }else if(mins!=null){
        timeremain = mins + " mins " + secs +" secs"  
    }else{
        timeremain =   secs +" secs"  
    }
    return timeremain;

}

}


