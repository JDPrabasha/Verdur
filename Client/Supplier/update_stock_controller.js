$(document).ready(function restockrequestlist() {
    var authHeader = "Bearer " + window.localStorage.getItem("jwt"),
        supplierID = window.localStorage.getItem('id')

    queryString = window.location.search ,
    urlParams = new URLSearchParams(queryString),
    ingID = urlParams.get('id')

    console.log(ingID);
    // console.log(authHeader)
    $.ajax({
        url: "http://localhost:8080/Server_war_exploded/Supplier/UpdateStockServlet?ingID=" + ingID +"&supplierID=" + supplierID,
        beforeSend: function (xhr) {
            xhr.setRequestHeader("authorization", authHeader);
        }

    }).then(function (data) {
        console.log(data)
        console.log(data[0])
        $("#itemName").val(data[0]['itemName'])
        $("#itemType").val(data[0]['itemType'])
        $("#quantity").val(data[0]['quantity'] + " " + data[0]['unit'])
        $("#price").val(data[0]['price'])

        $("#confirm_button").click(function () {
            var supplierID = window.localStorage.getItem("id"),
                ingID = data[0]['itemID'],
                quantity = $("#quantity").val(),
                price = $("#price").val(),
                unit = data[0]['unit']

            json = {
                "supplierID": parseInt(supplierID),
                "itemID": parseInt(ingID),
                "quantity": parseInt(quantity.slice(0, quantity.length - unit.length)),
                "price": parseInt(price)
            }

            console.log(json)

            $.ajax({
                url : "http://localhost:8080/Server_war_exploded/Supplier/UpdateStockServlet",
                type : "PUT",
                beforeSend : function(xhr){
                    xhr.setRequestHeader("authorization",authHeader)
                },
                data:JSON.stringify(json)
            })
            window.location.href = "ViewStock.html"
        })


       

    })



});