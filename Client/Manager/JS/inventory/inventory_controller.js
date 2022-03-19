// import { all_Item_List } from "./data/allitems.js";
// import { vegetableItems } from "./data/vegetableItems.js";
// import { fruits_list } from "./data/fruits.js";
// import { meatList } from "./data/meat.js";
// import { other_items } from "./data/others.js";
import { nextbuttons } from "../functionalities/next_Buttons.js";
import inventory from "./inventory.js";
import { Inventory_Serializer } from "./inventory_serializer.js";

let all_Item_List,
    vegetableItems,
    fruits_list,
    meatList,
    other_items;

let chunk = 4;





$.getScript("/Client/Manager/JS/functionalities/expand.js");

$.getScript("/Client/Manager/JS/side_menu.js",
    $(document).ready(function reload() {
        $("#notificationbox").load("/Client/Manager/Manager-Header.html #notification",function(){
            $.getScript("/Client/Manager/JS/functionalities/profile.js");
        })
        $("#fotter").load("/Client/fotterKM.html #KMfotter")

        var authHeader = "Bearer " + window.localStorage.getItem("jwt");
        $.ajax({
            url: "http://localhost:8080/Server_war_exploded/Manager/ingredients",
            beforeSend: function (xhr) {
                xhr.setRequestHeader("authorization", authHeader);
            },
            error: function () {
                reload();
            }
        }).then(function (data) {
            // console.log(data.filter(i => i.type=="Fruits"));
            // console.log(data)

            data = data.map(i => Inventory_Serializer.doSerialize(i));

            all_Item_List = data,
                vegetableItems = data.filter(i => i.type == "Vegetable"),
                fruits_list = data.filter(i => i.type == "Fruit"),
                meatList = data.filter(i => i.type == "Meat"),
                other_items = data.filter(i => i.type == "Other");
            // console.log(fruits_list)

            // console.log(data.filter(i => ((i.name).toLowerCase().includes("e")) || (i.type).toLowerCase().includes("e")))





            $("#inventory-tbody").html('');
            let allItems = all_Item_List;

            // ---- Next Button initialization for all-items -- //

            allItems.slice(0, chunk).map(i => new inventory(i).printInventoryRecord())
            nextbuttons(allItems.length, chunk, allItems);
            initiateNextButtons(allItems);

            // ----------*****************************---------//


            var importslider = $(document.createElement('script')),
                inventorypop = $(document.createElement('script'));
            importslider.attr("src", "/Client/Manager/JS/inventory/slider.js")
            inventorypop.attr("src", "/Client/Manager/JS/inventory/inventory-pop.js")
            //imageUploadScript


            $(document.head).append(importslider, inventorypop);
            $.getScript("/Client/Manager/JS/functionalities/image_upload.js", function () {
                $("#pop-2").load("/Client/Manager/Manager-Ingredient-Pop-2.html #pop-2-content", function () {
                    $("#pop-2").trigger("loaded")
                })
                pop_2_initiate();
            });
            updateView();


            $("#loading").trigger("loaded")
        })

        // $("#notificationbox").load("/Client/Manager/Manager-Header.html #notification")
        //     $("#fotter").load("/Client/fotterKM.html #KMfotter")
        //     $("#inventory-tbody").html('');
        //     let allItems = all_Item_List;
        //     allItems.map(i => new inventory(i).printInventoryRecord())


        //     var importslider = $(document.createElement('script')),
        //         inventorypop = $(document.createElement('script'));
        //     importslider.attr("src", "/Client/Manager/JS/inventory/slider.js")
        //     inventorypop.attr("src", "/Client/Manager/JS/inventory/inventory-pop.js")

        //     $(document.head).append(importslider, inventorypop);
        //     updateView();


        //buttons
        let all = $("#all-btn"),
            vegetables = $("#vegetables-btn"),
            fruits = $("#fruits-btn"),
            meat = $("#meat-btn"),
            others = $("#others-btn");


        vegetables.on("click", function () {
            let veg = vegetableItems;
            $("#inventory-tbody").html('');
            // veg.map(i => new inventory(i).printInventoryRecord());

            // ---- Next Button initialization for vegetables -- //

            veg.slice(0, chunk).map(i => new inventory(i).printInventoryRecord())
            nextbuttons(veg.length, chunk, veg);
            initiateNextButtons(veg);

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
            $("#inventory-tbody").html('');
            // allItems.map(i => new inventory(i).printInventoryRecord());

            // ---- Next Button initialization for all-items -- //

            allItems.slice(0, chunk).map(i => new inventory(i).printInventoryRecord())
            nextbuttons(allItems.length, chunk, allItems);
            initiateNextButtons(allItems);

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
            $("#inventory-tbody").html('');
            // fruitsl.map(i => new inventory(i).printInventoryRecord());

            // ---- Next Button initialization for Fruits -- //

            fruitsl.slice(0, chunk).map(i => new inventory(i).printInventoryRecord())
            nextbuttons(fruitsl.length, chunk, fruitsl);
            initiateNextButtons(fruitsl);

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
            $("#inventory-tbody").html('');
            // meatl.map(i => new inventory(i).printInventoryRecord());

            // ---- Next Button initialization for meat -- //

            meatl.slice(0, chunk).map(i => new inventory(i).printInventoryRecord())
            nextbuttons(meatl.length, chunk, meatl);
            initiateNextButtons(meatl);

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
            $("#inventory-tbody").html('');
            // otherl.map(i => new inventory(i).printInventoryRecord());

            // ---- Next Button initialization for other -- //

            otherl.slice(0, chunk).map(i => new inventory(i).printInventoryRecord())
            nextbuttons(otherl.length, chunk, otherl);
            initiateNextButtons(otherl);

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

        $("#addarea").on("done", function () {
            $("#add_Ing_btn").on("click", function () {
                createIngredient();
            })
        })
    })
)

function updateView() {
    sliderUpdate();
    $(document).ready(function () {
        updateExpandButtons();
        editInvButton();
        deleteButton();
    })
}



function createIngredient() {
    let itemcode = $('#itemcode').val(),
        itemname = $('#itemname').val(),
        itemunit = $('#itemunit').val(),
        description = $('#description').val(),
        carbpg = $('#carb-pg').val(),
        calpg = $('#cal-pg').val(),
        proteinpg = $('#prot-pg').val(),
        type = $("#Item-Type").val(),
        image = $("#label-image").attr("src"),
        fatpg = $("#fats-pg").val(),
        minq = $("#min-lvl").val(),
        resq = $("#res-lvl").val(),
        maxq = $("#max-lvl").val(),
        expandable = $("input[name='expandable']:checked").val();

    var unitsList = new Array();
    $('#unit-tbody tr').each(function (row, tr) {
        unitsList[row] = {
            "unit": $(tr).find('#unit').val(),
            "ratio": parseFloat($(tr).find('#ratio').val()),
        } //tableData[row]
    });


    var result = {
        "ingcode": itemcode,
        "ingname": itemname,
        "unit": itemunit,
        "description": description,
        "carbpg": parseInt(carbpg),
        "calpg": parseInt(calpg),
        "fatpg": parseInt(fatpg),
        "proteinpg": parseInt(proteinpg),
        "type": type,
        "image": image,
        "expandable": expandable,
        "min": parseInt(minq),
        "normal": parseInt(resq),
        "max": parseInt(maxq),
        "unitsList" : unitsList
    }

    // console.log((result));
    var jwt = "Bearer " + window.localStorage.getItem('jwt');
    // var jwt = window.localStorage.getItem('jwt');

    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/Server_war_exploded/Manager/ingredients',
        headers: {
            "authorization": jwt
        },

        data: JSON.stringify(result),
        success: function (data) {
            console.log('success')
        },
        error: function (e) {
            console.log(e)
            window.location = "/Client/Manager/Manager-Inventory.html";
        }
    }).done(function () {
        console.log("done");
        window.location = "/Client/Manager/Manager-Inventory.html";
    })
}

function initiateNextButtons(list) {
    //initiate the buttons
    $(`[id^=next-button]*`).each(function () {
        $(this).on("click", function () {
            inventory.changepage(parseInt($(this).html()), list, chunk)
            // sliderUpdate();
            updateView();

        })
    })
}

function editInvButton() {
    $('[id^=inv-edit-').click(function () {
        let id = this.id.split('-')[2],
            row = this.parentElement.parentElement.parentElement,
            mincontainer = $(row).find("#form_min"),
            maxcontainer = $(row).find("#form_max"),
            difcontainer = $(row).find("#form_dif")
        mincontainer.prop("disabled", false)
        maxcontainer.prop("disabled", false)
        difcontainer.prop("disabled", false)

        hideEditButton(id)

        // $(this).attr("style", "display:none")
        // $(`#delete-inv-${id}`).attr("style", "display:none")
        // $(`#done-edit-${id}`).attr("style", "display:block");
        // $(`#cancel-edit-${id}`).attr("style", "display:block");

        doneEditButton(id, mincontainer, maxcontainer, difcontainer);

        let minVal = mincontainer.val(),
            maxVal = maxcontainer.val(),
            difVal = difcontainer.val();
        cancelEditButton(id, mincontainer, maxcontainer, difcontainer, minVal, maxVal, difVal)

        // mincontainer[0].classList.add("enabled-input")
        // maxcontainer[0].classList.add("enabled-input")
        // difcontainer[0].classList.add("enabled-input")

        console.log(row)
    })
}

function doneEditButton(id, min, max, dif) {
    $("#done-edit-" + id).click(function () {
        let minVal = min.val(),
            maxVal = max.val(),
            difVal = dif.val(),
            data = JSON.stringify({
                "ingid": id,
                "min": minVal,
                "max": maxVal,
                "normal": difVal
            })
        console.log("ID: " + id + "Min : " + minVal + " Max : " + maxVal + " Default : " + difVal)
        let jwt = "Bearer " + window.localStorage.getItem("jwt");
        $.ajax({
            url: "http://localhost:8080/Server_war_exploded/Manager/ingredients",
            type: 'PUT',
            headers: {
                "authorization": jwt
            },
            data: data,
            success: showEditButton(),
            error: function (e) {
                console.log(e)
            }
        })



        showEditButton(id)
    })
}

function cancelEditButton(id, min, max, dif, minVal, maxVal, difVal) {
    $("#cancel-edit-" + id).click(function () {
        min.val(minVal);
        max.val(maxVal);
        dif.val(difVal);
        showEditButton(id);
    })
}

function showEditButton(id) {
    $(`#inv-edit-${id}`).attr("style", "display:inline-block")
    $(`#delete-inv-${id}`).attr("style", "display:inline-block")
    $(`#done-edit-${id}`).attr("style", "display:none");
    $(`#cancel-edit-${id}`).attr("style", "display:none");
}

function hideEditButton(id, x) {
    $(`#inv-edit-${id}`).attr("style", "display:none")
    $(`#delete-inv-${id}`).attr("style", "display:none")
    $(`#done-edit-${id}`).attr("style", "display:block");
    $(`#cancel-edit-${id}`).attr("style", "display:block");
}

function deleteButton() {
    $("[id^=delete-inv-").click(function () {
        let id = this.id.split('-')[2],
            jwt = "Bearer " + window.localStorage.getItem('jwt')
        $.ajax({
            url: "http://localhost:8080/Server_war_exploded/Manager/ingredients/" + id,
            type: 'DELETE',
            headers: {
                "authorization": jwt
            },
            success: function () {
                window.location = "/Client/Manager/Manager-Inventory.html"
            },
            error: function (e) {
                console.log(e)
            }
        })
    })
}


//  pop-up-2 Function
function pop_2_initiate() {
    $("#pop-2").on("loaded", function () {
        $("#add-btn").click(function () {
            let row = $('<tr>'),
                td1 = $('<td>'),
                td2 = $('<td>'),
                td3 = $("<td>")
            td1.load("/Client/Manager/Manager-Pop-Row.html #unit")
            td2.load("/Client/Manager/Manager-Pop-Row.html #ratio")
            td3.load("/Client/Manager/Manager-Pop-Row.html #buttons")
            row.append(td1, td2, td3)
            $("#unit-tbody").append(row)
        })
        $("#done-btn").click(function () {
            doneAdding();
        })
    })



    $(document).ajaxStop(function initiate_Buttons() {
        let unit, ratio;
        $('[id=edit]').click(function () {
            let maindiv = this.parentElement;
            $(this).attr("style", "display:none;")
            $($(maindiv).find('#delete')[0]).attr("style", "display:none;");
            $($(maindiv).find('#done')[0]).attr("style", "display:inline-block;");
            $($(maindiv).find('#close')[0]).attr("style", "display:inline-block;");

            let row = maindiv.parentElement.parentElement;
            $(row).find('#unit')[0].classList.add("enabled-input")
            $(row).find('#unit').prop("disabled", false)
            $(row).find('#ratio')[0].classList.add("enabled-input")
            $(row).find('#ratio').prop("disabled", false)


            unit = $(row).find('#unit').val()
            ratio = $(row).find('#ratio').val()


        })

        $('[id=close]').click(function () {
            let maindiv = this.parentElement;
            $(this).attr("style", "display:none;")
            $($(maindiv).find('#done')[0]).attr("style", "display:none;");
            $($(maindiv).find('#edit')[0]).attr("style", "display:inline-block;");
            $($(maindiv).find('#delete')[0]).attr("style", "display:inline-block;");

            let row = maindiv.parentElement.parentElement;
            $(row).find('#unit')[0].classList.remove("enabled-input")
            $(row).find('#unit').prop("disabled", true)
            $(row).find('#ratio')[0].classList.remove("enabled-input")
            $(row).find('#ratio').prop("disabled", true)

            $(row).find('#unit').val(unit)
            $(row).find('#ratio').val(ratio)
        })

        $('[id=delete]').click(function () {
            let row = this.parentElement.parentElement.parentElement
            $(row).remove()
        })

        $('[id=done]').click(function () {
            let maindiv = this.parentElement;
            $(this).attr("style", "display:none;")
            $($(maindiv).find('#close')[0]).attr("style", "display:none;");
            $($(maindiv).find('#edit')[0]).attr("style", "display:inline-block;");
            $($(maindiv).find('#delete')[0]).attr("style", "display:inline-block;");

            let row = maindiv.parentElement.parentElement;
            $(row).find('#unit')[0].classList.remove("enabled-input")
            $(row).find('#unit').prop("disabled", true)
            $(row).find('#ratio')[0].classList.remove("enabled-input")
            $(row).find('#ratio').prop("disabled", true)
        })
    })

    function doneAdding() {
        var unitsList = new Array();
        $('#unit-tbody tr').each(function (row, tr) {
            unitsList[row] = {
                "unit": $(tr).find('#unit').val(),
                "ratio": $(tr).find('#ratio').val(),
            } //tableData[row]
        });
        console.log(unitsList)
    }

}

