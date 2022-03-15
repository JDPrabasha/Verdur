// $(document).ready(function() {
//     $("#sub").click(function(){

//         $.post('http://localhost:8080/verdur_war_exploded/AddItem',  // url
//            { 
        
//             itemName:itemName,
//               itemType:itemType,
//              quantity:quantity,
//              price:price
        
//         },
//             // data to be submit
//            function(data, status, xhr) {   // success callback function
//                     alert('status: ' + status + ', data: ' + data.responseData);
//                 },
//            'json');
  
//     });
$('#sub').on('click',()=>{

    console.log("yyy");
    doSubmit();
})

    function doSubmit(){
        let supplierID = 1,
        ingID= document.getElementById("items1").value,
        quantity= document.getElementById("quantity").value,
        price= document.getElementById("price").value;




        var authHeader = "Bearer " + window.localStorage.getItem("jwt");
       
        // used to stop popping the alert multiple times
        // var alertx;
         xhr = new XMLHttpRequest();
        
        var url = "http://localhost:8080/Server_war_exploded/Supplier/StockServlet?id=1";
        xhr.open("POST", url, true);

        xhr.setRequestHeader("authorization", authHeader);

        var data = JSON.stringify({
            "supplierID":supplierID,
            "itemID":ingID,
            "quantity":quantity,
            "price":price



        });
        // console.log(data)
        xhr.send(data);
        window.location.href="ViewStock.html";
       
        
    }
   
    
    
    
  