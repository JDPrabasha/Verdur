import dish from "/Client/Manager/JS/reports/dish/dish.js";
import dish_serializer from "/Client/Manager/JS/reports/dish/dish_serializer.js";
import { dish_list_reports } from "./dish/dish_list_reports.js";
import { supplier_list_reports } from "/Client/Manager/JS/reports/supplier/supplier_list_reports.js";
import supplier_Serializer from "./supplier/supplier_serializer.js";
import supplier from "./supplier/supplier.js";

$.getScript("/Client/Manager/JS/side_menu.js",
    $(document).ready(function(){

        //adding Header and Footer
        $("#notificationbox").load("/Client/Manager/Manager-Header.html #notification")
        $("#fotter").load("/Client/fotterKM.html #KMfotter")

        //Report Dish List
        $("#topDishesReports").html('');
        let serializedDishList = dish_list_reports.map(i => dish_serializer.doSerialize(i));
        serializedDishList.map(i => new dish(i).printRowRecords());

        //Report Supplier List
        $("#topSuppliersReports").html('');
        let serializedSupplierList = supplier_list_reports.map(i => supplier_Serializer.doSerializer(i));
        serializedSupplierList.map(i => new supplier(i).printRowReports());
        
    })
)