import { dishlist } from "./dishlist.js";
import { customerList } from "./customerlist.js";

var namelist;
$(document).ready(function () {

    // Dish Table and Graph
    namelist=['Month'];
    $("#dishlist").html('');
    dishlist.map(i => printDishList(i));
    // printing googlecharts
    google.charts.load('current', { 'packages': ['corechart'] });
    google.charts.setOnLoadCallback(drawChart);

    // Customer List
    $('#cusList').html('');
    customerList.map(i=>printCusList(i))
})


function printDishList(x) {
    let table = $("#dishlist"),
        row = $(document.createElement('tr')).attr("id", x.dishID),
            image = $(document.createElement('td')).html(`<img class="icon-1" src="${x.dishImage}" alt="">`),
            name = $(document.createElement('td')).html(x.dishName),
            units = $(document.createElement('td')).html(x.units + " units"),
            price = $(document.createElement('td')).html("Rs. " + x.price),
            stars = $(document.createElement('td')).html(x.stars)
        namelist.push(x.dishName);
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





function drawChart() {
    var data = google.visualization.arrayToDataTable([
        ['Month', 'Sales', 'Expenses'],
        ['June', 1000, 400],
        ['July', 1170, 460],
        ['August', 660, 1120],
        ['September', 1030, 540]
    ]);

    var data2 = google.visualization.arrayToDataTable([
        namelist,
        ['May', 50, 70, 30, 50, 70],
        ['June', 80, 45, 100, 70, 35],
        ['July', 100, 20, 90, 20, 70],
        ['August', 30, 90, 25, 80, 70],
        ['September', 150, 50, 75, 34, 55],

    ]);

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

    var options2 = {
        title: 'Dish Sales',
        curveType: 'function',
        titleTextStyle: {
            fontSize: 40
        },
        fontSize: 16,
        legend: { position: 'right' },
        'width': 1300,
        'height': 510,
    }

    var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));
    var chart2 = new google.visualization.ColumnChart(document.getElementById('curve_chart2'));

    chart.draw(data, options);
    chart2.draw(data2, options2);
}
