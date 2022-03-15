import { all_Item_List } from "./data/allitems.js";
import { vegetableItems } from "./data/vegetableItems.js";
import { fruits_list } from "./data/fruits.js";
import { meatList } from "./data/meat.js";
import { other_items } from "./data/others.js";
import inventory from "./inventory.js";



$.getScript("/Client/Manager/JS/functionalities/expand.js");

$.getScript("/Client/Manager/JS/side_menu.js",
    $(document).ready(function () {
        $("#notificationbox").load("/Client/Manager/Manager-Header.html #notification")
        $("#fotter").load("/Client/fotterKM.html #KMfotter")
        $("#inventory-tbody").html('');
        let allItems = all_Item_List;
        allItems.map(i => new inventory(i).printInventoryRecord())


        var importslider = $(document.createElement('script')),
            inventorypop = $(document.createElement('script'));
        importslider.attr("src", "/Client/Manager/JS/inventory/slider.js")
        inventorypop.attr("src", "/Client/Manager/JS/inventory/inventory-pop.js")
        $(document.head).append(importslider, inventorypop);
        updateView();



        //buttons
        let all        = $("#all-btn"),
            vegetables = $("#vegetables-btn"),
            fruits     = $("#fruits-btn"),
            meat       = $("#meat-btn"),
            others     = $("#others-btn");


        vegetables.on("click", function () {
            let veg = vegetableItems;
            $("#inventory-tbody").html('');
            veg.map(i => new inventory(i).printInventoryRecord());
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
            $("#inventory-tbody").html('');
            allItems.map(i => new inventory(i).printInventoryRecord());
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
            $("#inventory-tbody").html('');
            fruitsl.map(i => new inventory(i).printInventoryRecord());
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

        meat.on("click",function(){
            let meatl = meatList;
            $("#inventory-tbody").html('');
            meatl.map(i => new inventory(i).printInventoryRecord());
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

        others.on("click",function(){
            let otherl = other_items;
            $("#inventory-tbody").html('');
            otherl.map(i => new inventory(i).printInventoryRecord());
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

    })
)

function updateView() {
    sliderUpdate();
    // updateExpandButtons();
    $(document).ready(function(){
        updateExpandButtons();
    })
}



// function updateExpandButtons() {
//     $(`[id^=expand-]`).each(function () {
//         var i = this.id.split("-")[1]
//         $(this).on("click", function () {
//             expand(i)
//         })
//     })

//     $(`[id^=hide-]`).each(function () {
//         var i = this.id.split("-")[1]
//         $(this).on("click", function () {
//             hide(i)
//         })
//     })
// }


// function expand(i) {
//     document.getElementById(`inv-${i}`).classList.add('hidden');
//     document.getElementById(`inv-e-${i}`).classList.remove('hidden');
// }
// function hide(i) {
//     document.getElementById(`inv-${i}`).classList.remove('hidden');
//     document.getElementById(`inv-e-${i}`).classList.add('hidden');
// }
