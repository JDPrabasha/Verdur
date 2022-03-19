import dish from "/Client/Manager/JS/reports/dish/dish.js";
import dish_serializer from "/Client/Manager/JS/reports/dish/dish_serializer.js";
import { dish_list_reports } from "./dish/dish_list_reports.js";
import { supplier_list_reports } from "/Client/Manager/JS/reports/supplier/supplier_list_reports.js";
import supplier_Serializer from "./supplier/supplier_serializer.js";
import supplier from "./supplier/supplier.js";


let dishChartData = [['Dish', 'Units Sold'],];
let supplierChartData = [['Supplier', 'Orders Done'],];
let salesChartData = [['Month', 'Sales', 'Expenses', 'Profit'],];
let chart, options, chartData1, topDishSum = 0;
let chart2, options2, chartData2, topSupplierSum = 0;
let chart3, options3,chartData3;
$.getScript("/Client/Manager/JS/side_menu.js",
    $(document).ready(function reload() {

        //adding Header and Footer
        $("#notificationbox").load("/Client/Manager/Manager-Header.html #notification",function(){
            $.getScript("/Client/Manager/JS/functionalities/profile.js");
        })
        $("#fotter").load("/Client/fotterKM.html #KMfotter")

        let jwt = "Bearer " + window.localStorage.getItem("jwt");
        $.ajax({
            url: "http://localhost:8080/Server_war_exploded/Manager/Report/reports",
            headers: {
                "authorization": jwt
            },
            eroor:function(){
                reload();
            }
        }).then(function (data) {
            $("#topDishesReports").html('');
            let serializedDishList = data['topDish'].map(i => dish_serializer.doSerialize(i))
            serializedDishList.map(i => new dish(i).printRowRecords());
            serializedDishList.map(i => new dish(i).getNameAndSoldList(dishChartData))
            calculateDishSum(data['topDish'])
            $("#dishTotal").html(formatNo(data['totalDishSales']))
            dishChartData.push(["Other", data['totalDishSales'] - topDishSum])

            chartData1 = google.visualization.arrayToDataTable(dishChartData);
            chart.draw(chartData1, options);

            $("#topSuppliersReports").html('');
            let serializedSupplierList = data['topSupplier'].map(i => supplier_Serializer.doSerializer(i));
            serializedSupplierList.map(i => new supplier(i).printRowReports());
            serializedSupplierList.map(i => new supplier(i).getNameAndSoldList(supplierChartData));

            

            calculateSupplierSum(data['topSupplier'])
            $("#supplierTotal").html(formatNo(data['totalSupplierOrders']))
            supplierChartData.push(["Other",data['totalSupplierOrders']-topSupplierSum])

            chartData2 = google.visualization.arrayToDataTable(supplierChartData);
            chart2.draw(chartData2, options2);

            console.log(data['lastRevenue'])

            function convert(arr) {
                var first = parseInt(arr[1]);
                var second = parseInt(arr[2]);
                var third = parseInt(arr[1])-parseInt(arr[2])
                return [arr[0], first, second,third];
              }

            console.log(data['lastRevenue'].map(i => convert(i)))
            data['lastRevenue'].map(i => convert(i)).forEach(element => {
                salesChartData.push(element)
            });
            console.log(salesChartData)
            chartData3 = google.visualization.arrayToDataTable(salesChartData);
            chart3.draw(chartData3, options3);

            $("#loading").trigger("loaded")


        })

        // Report Dish List
        // $("#topDishesReports").html('');
        // let serializedDishList = dish_list_reports.map(i => dish_serializer.doSerialize(i));
        // serializedDishList.map(i => new dish(i).printRowRecords());

        // //Report Supplier List
        // $("#topSuppliersReports").html('');
        // let serializedSupplierList = supplier_list_reports.map(i => supplier_Serializer.doSerializer(i));
        // serializedSupplierList.map(i => new supplier(i).printRowReports());

    })
)


google.charts.load('current', { 'packages': ['corechart'] });
google.charts.setOnLoadCallback(drawChart);

function drawChart() {

    

    options = {
        title: 'Dish Sales',
        curveType: 'function',
        legend: { position: 'right' },
        // 'width': 1000,
        fontSize: 15,
        titleTextStyle: {
            fontSize: 25
        },
        // is3D:true,
        height: 300,
        chartArea: { width: 400, left: 40, top: 40, width: '100%', height: '99%' },
        pieHole: 0.7,
        pieSliceTextStyle: {
            color: 'none',
        },
        slices: [{ color: 'grey' }, { color: 'red' }, { color: 'yellow' }, { color: 'orange' }, { color: 'green' }, { color: 'pink' },]
        // vAxis:{gridlines:{color: '#FFF', minSpacing: 100}}
    };

    options2 = {
        title: 'Supplier Contribution',
        curveType: 'function',
        legend: { position: 'right' },
        // 'width': 1000,
        fontSize: 15,
        titleTextStyle: {
            fontSize: 25
        },
        // is3D:true,
        height: 300,
        chartArea: { width: 400, left: 40, top: 40, width: '100%', height: '99%' },
        pieHole: 0.7,
        pieSliceTextStyle: {
            color: 'none',
        }
    }

    options3 = {
        title: "Profit Evaluation",
        height: 300,
        fontSize: 15,
        titleTextStyle: {
            fontSize: 25
        },
        titlePosition: 'out',
        chartArea: { left: 60, top: 40, width: '55%', height: '80%' },
    }


    chart = new google.visualization.PieChart(document.getElementById('curve_chart'));
    chart2 = new google.visualization.PieChart(document.getElementById('curve_chart2'));
    chart3 = new google.visualization.ColumnChart(document.getElementById('curve_chart3'));

}

function calculateDishSum(array) {
    array.forEach(y => {
        topDishSum += y['unitsSold']
    });
}

function calculateSupplierSum(array) {
    array.forEach(y => {
        topSupplierSum += y['ordersDone']
    });
}

function formatNo(x){
    if(x<1000){
        var formattedNumber = ("000" + x).slice(-3);
        return formattedNumber;
    }else{
        return x.toLocaleString()
    }
}