class dish {
    constructor(params) {
        this.itemID = params.itemID;
        this.itemName = params.itemName;
        this.itemType = params.itemType;
        this.quantity = params.quantity;
        this.price = params.price;
        this.dateAdded = params.dateAdded;
        this.unit = params.unit;
    }
    printdish() {
        var view = $("#output");
        var row = $(document.createElement('tr')).attr("id", this.itemID);
        // var image = $(document.createElement('td')).html(`<img src="burger.png"  width="60" height="60">`);
        var itemID = $(document.createElement('td')).html(this.itemID);
        var itemName = $(document.createElement('td')).html(this.itemName);
        var itemType = $(document.createElement('td')).html(this.itemType);
        var quantity = $(document.createElement('td')).html(this.quantity);
        var unit = $(document.createElement('td')).html(this.unit);
        var price = $(document.createElement('td')).html(this.price);
        var dateAdded = $(document.createElement('td')).html(this.dateAdded);

        var button1 = $(document.createElement('td')).html(`<a href="UpdateStock.html?id=${this.itemID}" class="btn text-1 bg-clr-paid fw-b text-center">Update</button>`);
        var button2 = $(document.createElement('td')).html(`<button class="btn bg-clr-unpaid text-1 fw-b"  onclick="hiderow(${this.itemID})">Remove</button>`);

        // $("#table tr").click(function () {
        //     $(this).addClass('selected').siblings().removeClass('selected');
        //     var value = $(this).find('td:first').html();
        //     alert(value);
        // });
        // $('.ok').on('click', function (e) {
        //     alert($("#table tr.selected td:first").html());
        // })


        // var viewbutton = $(document.createElement('td')).html(`<a href="dishview.html?id=${this.dishid}"><button>View</button></a>`);

        // var updatebutton = $(document.createElement('td')).html(`<a href="updatedish.html"><button>Update</button></a>`);
        // var deletebutton = $(document.createElement('td')).html(`<button>Remove</button>`);


        row.append(itemID);
        row.append(itemName);
        row.append(itemType);
        // row.append(name);
        // row.append(status);

        row.append(quantity);
        row.append(unit);
        row.append(price);
        row.append(dateAdded);

        row.append(button1);
        row.append(button2);

        view.append(row);
    }
}

function hiderow(rc) {
    let ric = "#" + rc;

    $(ric).css("display", "none")

}