import { dishlist } from "./dishlist.js";
import { customerList } from "./customerlist.js";
import { Dish_List_Serializer } from "./dishlist_serializer.js";
import { Cutomer_List_Serializer } from "./customerlist_serializer.js";

var namelist = new Array(),
monthlist = new Array(),
salesData = new Array(),
chart2,
options2;
$(document).ready(function reload() {
    $("#notificationbox").load("/Client/Manager/Manager-Header.html #notification",function(){
        $.getScript("/Client/Manager/JS/functionalities/profile.js");
    })
    $("#fotter").load("/Client/Manager/fotterKM.html #KMfotter")
    let jwt = "Bearer " + window.localStorage.getItem("jwt");
    $.ajax({
        url:"http://localhost:8080/Server_war_exploded/Manager/Report/home",
        headers: {
            "authorization": jwt
        },
        // error:function(){
        //     // reload();
        // }
         }).fail(function (jqXHR, textStatus, errorThrown) {
        console.log(jqXHR)
        console.log(textStatus)
        console.log(errorThrown)
        window.location.href = "/Client/Manager/Invalid Token.html"
        // Request failed. Show error message to user. 
        // errorThrown has error message.
    // })
    }).then(function(data){
        monthlist=['Month'];
        
        let abc;
        for (abc in data['topDish'][0]['lastMonthSales']){
            
            monthlist.push(abc)
        }
        namelist.push(monthlist)
        data['topDish'].map(i => namelist.push(returnlist(i)))
       
        

        $("#dishlist").html('');
        data['topDish'].map(i => Dish_List_Serializer.doSerialize(i)).map(i => printDishList(i));

        $('#cusList').html('');
        let customerList = data['topCustomers'].map(i => Cutomer_List_Serializer.doSerialize(i));
        customerList.map(i=>printCusList(i))

        $("#totalSalesOut").html("Rs." + data['totalSales'].toLocaleString());
        $("#totalExpensesOut").html("Rs." + data['totalExpenses'].toLocaleString());
        $("#totalProfitOut").html("Rs." +(data['totalSales']-data['totalExpenses']).toLocaleString());
        $("#todaysRevenueOut").html("Rs." + data['todaysRevenue'].toLocaleString());

        

        function convert(arr) {
            var first = parseInt(arr[1]);
            var second = parseInt(arr[2]);
            return [arr[0], first, second];
          }
          
        salesData = data['lastRevenue'].map(convert);
        // console.log(salesData)
        
        
        google.charts.load('current', { 'packages': ['corechart'] });
        google.charts.setOnLoadCallback(drawChart);
        $("#loading").trigger("loaded")

    });
})


function printDishList(x) {
    let table = $("#dishlist"),
        row = $(document.createElement('tr')).attr("id", x.dishID),
            image = $(document.createElement('td')).html(`<img class="icon-1" src="${x.dishImage}" alt="">`),
            name = $(document.createElement('td')).html(x.dishName),
            units = $(document.createElement('td')).html(x.units + " units"),
            price = $(document.createElement('td')).html("Rs. " + x.price),
            // stars = $(document.createElement('td')).html(x.stars)
            stars = $(document.createElement('td')).append(printStars(x.stars))
        // namelist.push(x.dishName);
        row.append(image, name, units, price, stars);
    table.append(row);
}

function printCusList(x){
    let table = $('#cusList'),
        row1   = $(document.createElement('tr')).attr("id",x.cusID),
            image   = $(document.createElement('td')).attr("rowspan",2).html(`<img src="${x.image}" alt="">`),
            name    = $(document.createElement('td')).html(x.name),
        row2 = $(document.createElement('tr')).attr("id",x.cusID),
            email    = $(document.createElement('td')).html(x.email);
        
        row1.append(image,name);
        // row2.append(email);
    table.append(row1,row2)      
}

function printStars(x){
    let y = $(document.createElement('div'));
    let index = 0;
    // console.log(x | 0)
    for (index = 0; index < (x | 0) ; index++) {
        let star = $(document.createElement('div')).attr("class","material-icons").html('star').attr("style","font-size:18px")
        y.append(star);
    }
    // console.log(4==(4.36.toFixed()-1))
    if(index==(x.toFixed()-1)){
        let halfstar = $(document.createElement('div')).attr("class","material-icons").html('star_half').attr("style","font-size:18px")
        y.append(halfstar)
    }
    return y;
}





function drawChart() {
    // var data = google.visualization.arrayToDataTable([
    //     ['Month', 'Sales', 'Expenses'],
    //     ['June', 1000, 400],
    //     ['July', 1170, 460],
    //     ['August', 660, 1120],
    //     ['September', 1030, 540]
    // ]);
    let unformated = [['Month','Sales','Expenses']]
    salesData.forEach(element => {
        unformated.push(element)
    });
    // unformated.push(salesData)
    // console.log(unformated)
    var data = google.visualization.arrayToDataTable(unformated)
    // console.log(data)

  
    var data2 = google.visualization.arrayToDataTable(namelist)

    var options = {
        title: 'Earnings',
        curveType: 'function',
        titleTextStyle: {
            fontSize: 40
        },
        fontSize: 18,
        legend: { position: 'right' },
        'width': 1100,
        'height': 430,
        // vAxis:{gridlines:{color: '#FFF', minSpacing: 100}}
    };

    options2 = {
        title: 'Dish Sales',
        curveType: 'function',
        titleTextStyle: {
            fontSize: 40
        },
        // vAxis: {
        //     viewWindow: {min: 0, max:100}
        //   },
        fontSize: 16,
        legend: { position: 'right' },
        'width': 1300,
        'height': 510,
    }

    // var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));
    var chart = new google.visualization.ColumnChart(document.getElementById('curve_chart'));
    chart2 = new google.visualization.ColumnChart(document.getElementById('curve_chart2'));

    chart.draw(data, options);
    chart2.draw(data2, options2);
}

function returnlist(i){
    let x = new Array;
    x.push(i.name)
    for (let y in i.lastMonthSales){
        x.push(i.lastMonthSales[y])
    }
    // console.log(x)
    return x;
}