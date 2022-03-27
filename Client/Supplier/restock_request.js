class restockrequest {
    constructor(params) {
        this.restockID = params.restockID;
        this.itemName = params.itemName;
        this.quantity = params.quantity;
        this.unit = params.unit;
        this.price = params.price;
        this.responseDeadline = params.responseDeadline;
        this.deliveryRequestDate = params.deliveryRequestDate;
        this.timeTillExpiry = this.getTimeRemaining(params.responseDeadline);
    }
    printrestockrequest() {
        var view = $("#output");
        var row = $(document.createElement('tr')).attr("id", this.restockID);
        // var image = $(document.createElement('td')).html(`<img src="burger.png"  width="60" height="60">`);
        var restockID = $(document.createElement('td')).html(this.restockID);
        var itemName = $(document.createElement('td')).html(this.itemName);
        var quantity = $(document.createElement('td')).html(this.quantity);
        var unit = $(document.createElement('td')).html(this.unit);
        var price = $(document.createElement('td')).html(this.price);
        var responseDeadline = $(document.createElement('td')).html(this.responseDeadline);
        var deliveryRequestDate = $(document.createElement('td')).html(this.deliveryRequestDate);
        var timeTillExpiry = $(document.createElement('td')).html(this.timeTillExpiry).attr("time",this.responseDeadline).attr("id","timeTillExp-"+this.restockID);

        // var button1 = $(document.createElement('td')).html(`<a href="UpdateStock.html?id=${this.itemID}" class="btn text-1 bg-clr-paid fw-b text-center">Update</button>`);
        var button2 = $(document.createElement('td')).html(`<button class="btn text-1 bg-clr-paid fw-b text-center " name="OK" class="ok" value="OK" onclick="hiderow(${this.restockID})">Accept</button>`);
        //console.log(this.restockID)
        $("#output tr").click(function () {
            $(this).addClass('selected').siblings().removeClass('selected');
            var value = $(this).find('td:first').html();
            console.log(value);

            // var jwt = window.localStorage.getItem('jwt');
            // var accept =  {
            //     "restockID":parseInt(value)
            // };
            // $.ajax({
            //     type: 'PUT',
            //     url: 'http://localhost:8080/Server_war_exploded/Supplier/RestockRequestServlet',
            //     headers: {
            //         "authorization": jwt
            //     },
            //     data: JSON.stringify(accept)
            // }).done(function () {
            //     console.log("done");
            // })




        });
        // $('.ok').on('click', function (e) {
        //     alert($("#output tr.selected td:first").html());
        // })

        // var viewbutton = $(document.createElement('td')).html(`<a href="dishview.html?id=${this.dishid}"><button>View</button></a>`);

        // var updatebutton = $(document.createElement('td')).html(`<a href="updatedish.html"><button>Update</button></a>`);
        // var deletebutton = $(document.createElement('td')).html(`<button>Remove</button>`);


        row.append(restockID);
        row.append(itemName);
        row.append(quantity);
        row.append(unit);
        row.append(price);
        row.append(responseDeadline);
        row.append(deliveryRequestDate);
        row.append(timeTillExpiry);

        //row.append(button1);
        row.append(button2);

        view.append(row);
    }

    getTimeRemaining(x) {
        // console.log(new Date(x))
        // let difference = new Date(new Date(x) - new Date() - new Date("1970-01-01 11:00:00"))
        console.log(x)
        let difference = new Date(new Date(x) - new Date() + new Date().getTimezoneOffset() * 60 * 1000)
    
    
    
        let months = difference.getMonth(), 
        days = difference.getDate(),
        hours = difference.getHours(),
        mins = difference.getMinutes(),
        secs = difference.getSeconds(),
        timeremain;
        if(months!=0){
            timeremain = months+"  "+" Months "+days+" Days " + hours +" hours "+ mins + " mins " + secs +" secs"  
        }else if(days!=0){
            timeremain = days+" Days " + hours +" hours "+ mins + " mins " + secs +" secs"  
        }else if(hours!=0){
            timeremain =  hours +" hours "+ mins + " mins " + secs +" secs"  
        }else if(mins!=0){
            timeremain = mins + " mins " + secs +" secs"  
        }else{
            timeremain =   secs +" secs"  
        }
        return timeremain;
    
    }

}

function hiderow(rc) {
    let ric = "#" + rc;
    let supplierID = window.localStorage.getItem("id")

    $(ric).css("display", "none")
    console.log(rc)
    var jwt = window.localStorage.getItem('jwt');
    var accept = {
        "restockID": parseInt(rc)
    };
    $.ajax({
        type: 'PUT',
        url: 'http://localhost:8080/Server_war_exploded/Supplier/RestockRequestServlet?supplierID='+supplierID,
        headers: {
            "authorization": jwt
        },
        data: JSON.stringify(accept)
    }).done(function () {
        console.log("done");
    })
}