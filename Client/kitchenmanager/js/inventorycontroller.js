let all_Item_List,
    vegetableItems,
    fruits_list,
    meatList,
    other_items;

    let chunk = 4;

$(document).ready(function inventorylist(){
    var authHeader = "Bearer " + window.localStorage.getItem("jwt");
    $.ajax({
        url:"http://localhost:8080/Server_war_exploded/KitchenManager/inventory",
        beforeSend: function(xhr) {
            xhr.setRequestHeader("authorization", authHeader);
          },
     },).then(function(data){
         
        $("#inventorytable").on("update",function(){
            $("[id^=editstock-").each(function(){
                
                $(this).click(function(){
                    edit_button(this)
                })
            })
        })

      
        
        //  var array = $.parseJSON(JSON.stringify(data));
        //  console.log(array);
        //  const deserializeddata = array.map(i=>inventoryserializer.doserializer(i));
        //  deserializeddata.map(params=>new inventory(params).printinventory());

        data = data.map(i => inventoryserializer.doserializer(i));

        all_Item_List = data,
            vegetableItems = data.filter(i => i.type == "Vegetable"),
            fruits_list = data.filter(i => i.type == "Fruit"),
            meatList = data.filter(i => i.type == "Meat"),
            other_items = data.filter(i => i.type == "Other");

            console.log(data)
            console.log(vegetableItems)
            console.log(fruits_list)
            console.log(meatList)
            console.log(other_items)

        
            let allItems = all_Item_List;

            allItems.slice(0, chunk).map(i => new inventory(i).printinventory())
            $("#inventorytable").trigger("update")
            // nextbuttons(allItems.length, chunk, allItems);
            // initiateNextButtons(allItems);



            let all = $("#all-btn"),
            vegetables = $("#vegetables-btn"),
            fruits = $("#fruits-btn"),
            meat = $("#meat-btn"),
            others = $("#others-btn");

            vegetables.on("click", function () {
                let veg = vegetableItems;
                $("#inventorytable").html('');
                // veg.map(i => new inventory(i).printInventoryRecord());
    
                // ---- Next Button initialization for vegetables -- //
    
                veg.slice(0, chunk).map(i => new inventory(i).printinventory())
                // nextbuttons(veg.length, chunk, veg);
                // initiateNextButtons(veg);
    
                // ----------*****************************---------//
    
                all[0].classList.remove("tag-select");
                all[0].classList.add("tag-all");
                fruits[0].classList.remove("tag-select");
                fruits[0].classList.add("tag-yellow");
                meat[0].classList.remove("tag-select");
                meat[0].classList.add("tag-red");
                others[0].classList.remove("tag-select");
                others[0].classList.add("tag-other");
                vegetables[0].classList.add("tag-select");
                vegetables[0].classList.remove("tag-green");
                updateView();
                $("#typeView").html("Vegetables");
            })

            all.on("click", function () {
                let allItems = all_Item_List;
                $("#inventorytable").html('');
                // allItems.map(i => new inventory(i).printInventoryRecord());
    
                // ---- Next Button initialization for all-items -- //
    
                allItems.slice(0, chunk).map(i => new inventory(i).printinventory())
                // nextbuttons(allItems.length, chunk, allItems);
                // initiateNextButtons(allItems);
    
                // ----------*****************************---------//
    
    
                all[0].classList.add("tag-select");
                all[0].classList.remove("tag-all");
                vegetables[0].classList.remove("tag-select");
                vegetables[0].classList.add("tag-green");
                fruits[0].classList.remove("tag-select");
                fruits[0].classList.add("tag-yellow");
                meat[0].classList.remove("tag-select");
                meat[0].classList.add("tag-red");
                others[0].classList.remove("tag-select");
                others[0].classList.add("tag-other");
                updateView();
                $("#typeView").html("All Items");
            })

            fruits.on("click", function () {
                let fruitsl = fruits_list;
                $("#inventorytable").html('');
                // fruitsl.map(i => new inventory(i).printInventoryRecord());
    
                // ---- Next Button initialization for Fruits -- //
    
                fruitsl.slice(0, chunk).map(i => new inventory(i).printinventory())
                // nextbuttons(fruitsl.length, chunk, fruitsl);
                // initiateNextButtons(fruitsl);
    
                // ----------*****************************---------//
                all[0].classList.remove("tag-select");
                all[0].classList.add("tag-all");
                vegetables[0].classList.remove("tag-select");
                vegetables[0].classList.add("tag-green");
                fruits[0].classList.add("tag-select");
                fruits[0].classList.remove("tag-yellow");
                meat[0].classList.remove("tag-select");
                meat[0].classList.add("tag-red");
                others[0].classList.remove("tag-select");
                others[0].classList.add("tag-other");
                updateView();
                $("#typeView").html("Fruits");
            })

            meat.on("click", function () {
                let meatl = meatList;
                $("#inventorytable").html('');
                // meatl.map(i => new inventory(i).printInventoryRecord());
    
                // ---- Next Button initialization for meat -- //
    
                meatl.slice(0, chunk).map(i => new inventory(i).printinventory())
                // nextbuttons(meatl.length, chunk, meatl);
                // initiateNextButtons(meatl);
    
                // ----------*****************************---------//
                all[0].classList.remove("tag-select");
                all[0].classList.add("tag-all");
                vegetables[0].classList.remove("tag-select");
                vegetables[0].classList.add("tag-green");
                fruits[0].classList.remove("tag-select");
                fruits[0].classList.add("tag-yellow");
                meat[0].classList.add("tag-select");
                meat[0].classList.remove("tag-red");
                others[0].classList.remove("tag-select");
                others[0].classList.add("tag-other");
                updateView();
                $("#typeView").html("Meat");
            })

            others.on("click", function () {
                let otherl = other_items;
                $("#inventorytable").html('');
                // otherl.map(i => new inventory(i).printInventoryRecord());
    
                // ---- Next Button initialization for other -- //
    
                otherl.slice(0, chunk).map(i => new inventory(i).printinventory())
                // nextbuttons(otherl.length, chunk, otherl);
                // initiateNextButtons(otherl);
    
                // ----------*****************************---------//
                all[0].classList.remove("tag-select");
                all[0].classList.add("tag-all");
                vegetables[0].classList.remove("tag-select");
                vegetables[0].classList.add("tag-green");
                fruits[0].classList.remove("tag-select");
                fruits[0].classList.add("tag-yellow");
                meat[0].classList.remove("tag-select");
                meat[0].classList.add("tag-red");
                others[0].classList.add("tag-select");
                others[0].classList.remove("tag-other");
                updateView();
                $("#typeView").html("Others");
            })

            $("#loading").trigger("loaded")

     })

     function updateView() {
        // sliderUpdate();
        // $(document).ready(function () {
        //     updateExpandButtons();
        //     editInvButton();
        // })
    }

   
    
});



function edit_button(x){
    console.log("bb")
    let maindiv = x.parentElement.parentElement
    $(maindiv).find("#stock").prop("disabled",false)

    $(x).attr("style", "display:none")

    $(`#done-edit-${x.id.split('-')[1]}`).attr("style", "display:block");
    $(`#cancel-edit-${x.id.split('-')[1]}`).attr("style", "display:block");

    let stock = $(maindiv).find("#stock").val()

    $(`#cancel-edit-${x.id.split('-')[1]}`).click(function (){
        $(maindiv).find('#stock').val(stock) ;
        

        $(maindiv).find("#stock").prop("disabled",true)
      

        $(this).attr("style", "display:none")
        $(`#editstock-${x.id.split('-')[1]}`).attr("style", "display:inline-block");
        $(`#done-edit-${x.id.split('-')[1]}`).attr("style", "display:none");
     })

     
     $(`#done-edit-${x.id.split('-')[1]}`).click(function (){  
        $(this).attr("style", "display:none")
        $(`#editstock-${x.id.split('-')[1]}`).attr("style", "display:inline-block");
        $(`#cancel-edit-${x.id.split('-')[1]}`).attr("style", "display:none");
        let stock = $(maindiv).find("#stock").val()

        let   jsonlist = {
            "ingid": this.id.split('-')[2],
            "quantity": stock 
        }

        var jwt = "Bearer " + window.localStorage.getItem('jwt')
       console.log(jsonlist)
        
        $.ajax({
            type: 'PUT',
            url: 'http://localhost:8080/Server_war_exploded/KitchenManager/inventory',
            headers: {
                "authorization": jwt
            },
            data: JSON.stringify(jsonlist)
        }).done(function () {
            console.log("done");
        })
     })



        
}