$(document).ready(function restockrequestlist() {
    var authHeader = "Bearer " + window.localStorage.getItem("jwt"),
        supplierID = window.localStorage.getItem('id')

$('#output').on('click','.remove_button',function() {
    console.log($(this).attr("data-id"))
    
            json = {
                "supplierID": parseInt(supplierID),
                "itemID": parseInt($(this).attr("data-id"))
               
            }
    
            $.ajax({
                url : "http://localhost:8080/Server_war_exploded/Supplier/RemoveStockServlet",
                type : "DELETE",
                beforeSend : function(xhr){
                    xhr.setRequestHeader("authorization",authHeader)
                },
                data:JSON.stringify(json)
            })
         
    
    
        })
    })
        