
function doSubmit()
{
    var url = new URL(window.location.href);
    var ingid = url.searchParams.get("id");

    quantity = document.getElementById("quantity").value,
    supplier = document.getElementById("supplierdetails").value,
    dueBy = document.getElementById("dueby").value,
    expired = document.getElementById("expired").value;



    console.log(supplier);
    // console.log(quantity);
    // console.log(dueby.value);
    console.log(expired);
    // console.log(ingid);
    

    var authHeader = "Bearer " + window.localStorage.getItem("jwt");


    xhr = new XMLHttpRequest();
  

    var url = "http://localhost:8080/Server_war_exploded/KitchenManager/restocks";
    xhr.open("POST", url, true);
    xhr.setRequestHeader("authorization", authHeader);
    xhr.setRequestHeader("Content-Type", "application/json");

    var data = JSON.stringify({
        "ingID" : parseInt(ingid),
        "dueBy" : dueBy,
        "quantity" : parseInt(quantity),
        "deadline" : expired,
        "supplierid" : parseInt(supplier) 
    })
    console.log(data);
    xhr.send(data);
    
    window.location.href="/Client//kitchenmanager/inventory.html";





}