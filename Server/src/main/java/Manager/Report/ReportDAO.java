package Manager.Report;

import User.ConnectionFactory.DB;


import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ReportDAO {
    private Connection conn;

    private static String getTopDishQuery ="select d.dishID,sum(c.quantity) as unitsSold ,sum(c.price) as sumSales,d.name,d.price,d.image from orders o join hasdish h on h.orderID = o.orderID join customizeddish c on h.cdishID = c.cdishID join dish d on d.dishID =c.dishID where o.`timestamp` >= ? group by d.dishID order by unitsSold desc limit 6 ";
    private static String getRating = "select dishID ,avg(rating) as rating from rating where dishid = ? group by dishID ";
    private static String getSalesBetweenQuery = "select d.dishID,sum(c.quantity) as unitsSold from orders o join hasdish h on h.orderID = o.orderID join customizeddish c on h.cdishID = c.cdishID join dish d on d.dishID =c.dishID where d.dishID =? and o.timestamp >= ? and o.timestamp <= ? group by d.dishID";
    private static String getTopCustomersQuery = "select o.custID, sum(p.amount) as amount,a.image ,u.firstName ,u.lastName from orders o join payment p on o.orderID = p.orderID join customer c on c.custID = o.custID join avatar a on a.avatarID = c.avatarID join `user` u on u.userID = c.userID where o.`timestamp` >= ? group by custID order by amount desc limit 3";

    private static String getTotalSalesQuery = "select sum(amount) as total from orders o join payment p on o.orderID =p.orderID where timestamp >= ?";
    private static String getTotalExpenses = "select sum(price) as price from restockorder r join restockrequest r2 on r.restockID =r2.restockID where r2.status ='delivered' and deliveryDate >= ?";

    private static String getTotalSalesRangeQuery = "select sum(amount) as total from orders o join payment p on o.orderID =p.orderID where timestamp >= ? and timestamp <= ?";
    private static String getTotalExpensesRangeQuery = "select sum(price) as price from restockorder r join restockrequest r2 on r.restockID =r2.restockID where r2.status ='delivered' and deliveryDate >= ? and deliveryDate <= ?";

    private static String dishCostQuery = "select sum(r.price*h.defaultValue*i2.weight/i3.weight) cost from hasingredient h join ingredient i on h.ingID =i.ingID join restockrequest r on r.ingID =i.ingID join restockorder r3  on r.restockID = r3.restockID join ingredientweight i2 on (i2.ingID = i.ingID and i2.unit = h.unit ) JOIN ingredientweight i3 on(i2.ingID=i3.ingID and i.unit=i3.unit) where r3.deliveryDate =(select max(r2.deliveryDate) from restockorder r2 join restockrequest r4 on r2.restockID =r4.restockID where r4.ingID = r.ingID and r2.status='delivered') and  h.dishID = ?";

    private static String getTopSuppliersQuery = "select r.supplierID,u.firstName ,u.lastName,s.organization ,count(r.invoiceNo) as ordersDone ,sum(r2.price*r2.quantity) as amount from restockorder r join supplier s on r.supplierID = s.supplierID join `user` u on s.userID = u.userID join restockrequest r2 on r.restockID =r2.restockID where r.deliveryDate >= ? and r.status ='delivered' group by r.supplierID ";
    private static String getRejectedOrderCountQuery = "select count(restockID)as rejectedCount from restockrequest r where approvalStatus = 'managerApproved' and expired = 1 and supplierID = ? and requestedAt >=? " ;

    private static String getAllTotalDishSalesQuery = "select sum(c.quantity) as unitsSold from orders o join hasdish h on h.orderID = o.orderID join customizeddish c on h.cdishID = c.cdishID where o.`timestamp` >= ?";
    private static String getAllSupplierOrdersQuery = "select count(restockID) as ordersDone from restockorder r where deliveryDate >= ? and status='delivered'";

    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
    private DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private LocalDateTime now;

    private SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");


    ReportDAO(){
        try {
            this.conn = DB.initializeDB();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<reportsDish> getTopDishHome(){
        List<reportsDish>topDish = new ArrayList<>();
        try {
            PreparedStatement topDishQ = this.conn.prepareStatement(getTopDishQuery);
            now = LocalDateTime.now();
            topDishQ.setString(1,dtf.format(now.minusDays(30)));

            //
            ResultSet rs = topDishQ.executeQuery();
            while (rs.next()){
                int dishID = rs.getInt("d.dishID");
                int unitsSold = rs.getInt("unitsSold");
                String name = rs.getString("d.name");
                int price = rs.getInt("d.price");
                String image = rs.getString("image");
                PreparedStatement getRatingQ = this.conn.prepareStatement(getRating);
                getRatingQ.setInt(1,dishID);
                ResultSet ratingrs = getRatingQ.executeQuery();
                double rating = 0;
                while (ratingrs.next()){
                    rating = ratingrs.getDouble("rating");
                }
                topDish.add(new reportsDish(dishID,name,image,unitsSold,price,rating,lastMonthSales(dishID)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return topDish;
    }

    public Map<String, Integer> lastMonthSales(int id){
        Map<String,Integer> lastSales = new HashMap<>();
        int x = 0;
        now = LocalDateTime.now();
        int month = now.getMonthValue();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH,1);

        while(x<3){
            x+=1;
            cal.set(Calendar.DAY_OF_MONTH,1);
            cal.set(Calendar.MONTH,month-x-1);
            String startDate = format1.format(cal.getTime());
            int lastday = cal.getActualMaximum(Calendar.DATE);
            String monthname = now.minusMonths(x).getMonth().toString();
            cal.set(Calendar.DAY_OF_MONTH,lastday);
            String lastDate = (format1.format(cal.getTime()));
            int sales = 0;
            try {
                PreparedStatement getLastMonthSales = this.conn.prepareStatement(getSalesBetweenQuery);
                getLastMonthSales.setString(2,startDate);
                getLastMonthSales.setString(3,lastDate);
                getLastMonthSales.setInt(1,id);
                ResultSet rs = getLastMonthSales.executeQuery();
                while (rs.next()){
                    sales = rs.getInt("unitsSold");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return null;
            }
            lastSales.put(monthname,sales);
        }
        return lastSales;
    }

    public List<reportCustomer> getTopCustomersHome(){
        List<reportCustomer> topCust = new ArrayList<>();
        try {
            PreparedStatement topCustomersQ = this.conn.prepareStatement(getTopCustomersQuery);
            now = LocalDateTime.now();
            topCustomersQ.setString(1,dtf.format(now.minusDays(30)));
            ResultSet rs = topCustomersQ.executeQuery();
            while (rs.next()){
                int custID = rs.getInt("custID");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String image = rs.getString("image");
                topCust.add(new reportCustomer(custID,firstName,lastName,image));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return topCust;
    }

    public int getTotalSales(){
        try {
            PreparedStatement getTotalSalesQ = this.conn.prepareStatement(getTotalSalesQuery);
            now = LocalDateTime.now();
            getTotalSalesQ.setString(1,dtf.format(now.minusDays(30)));
            ResultSet rs = getTotalSalesQ.executeQuery();
            int sales=0;
            while (rs.next()){
                sales = rs.getInt("total");
            }
            return sales;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getTotalExpenses(){
        try {
            PreparedStatement getTotalExpensesQ = this.conn.prepareStatement(getTotalExpenses);
            getTotalExpensesQ.setString(1,dtf.format(now.minusDays(30)));
            ResultSet rs = getTotalExpensesQ.executeQuery();
            int expenses=0;
            while (rs.next()){
                expenses = rs.getInt("price");
            }
            return expenses;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getTodaysRevenue(){
        try {
            PreparedStatement getTodaysRevenurQ = this.conn.prepareStatement(getTotalSalesQuery);
            now = LocalDateTime.now();
            getTodaysRevenurQ.setString(1,dtf2.format(now));
            ResultSet rs = getTodaysRevenurQ.executeQuery();
            int sales=0;
            while (rs.next()){
                sales = rs.getInt("total");
            }
            return sales;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List getLastMonthSales(){
        List salesExpenses = new ArrayList();
        int x = 0;
        now = LocalDateTime.now();
        int month = now.getMonthValue();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH,1);

        while(x<3) {
            x += 1;
            cal.set(Calendar.DAY_OF_MONTH, 1);
            cal.set(Calendar.MONTH, month - x - 1);
            String startDate = format1.format(cal.getTime());
            int lastday = cal.getActualMaximum(Calendar.DATE);
            String monthname = now.minusMonths(x).getMonth().toString();
            cal.set(Calendar.DAY_OF_MONTH, lastday);
            String lastDate = (format1.format(cal.getTime()));
            int sales = 0;
            int expense = 0;
            try {
                PreparedStatement getLastMonthSales = this.conn.prepareStatement(getTotalSalesRangeQuery);
                getLastMonthSales.setString(1, startDate);
                getLastMonthSales.setString(2, lastDate);
                ResultSet rs = getLastMonthSales.executeQuery();
                while (rs.next()) {
                    sales = rs.getInt("total");
                }
                PreparedStatement getLastMonthExpenses = this.conn.prepareStatement(getTotalExpensesRangeQuery);
                getLastMonthExpenses.setString(1, startDate);
                getLastMonthExpenses.setString(2, lastDate);
                ResultSet rs2 = getLastMonthExpenses.executeQuery();
                while (rs2.next()) {
                    expense = rs2.getInt("price");
                    System.out.println(expense);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return null;
            }
            String[] val = {monthname, String.valueOf(sales), String.valueOf(expense)};
            salesExpenses.add(val);
        }
        return salesExpenses;
    }

    public List<reportsDish> getTopDish(){
        List<reportsDish> topDishes = new ArrayList<>();
        try {
            PreparedStatement getTopDishesQ = this.conn.prepareStatement(getTopDishQuery);
            now = LocalDateTime.now();
            getTopDishesQ.setString(1,dtf.format(now.minusDays(90)));
            ResultSet rs = getTopDishesQ.executeQuery();
            while (rs.next()) {
                int dishID = rs.getInt("d.dishID");
                int unitsSold = rs.getInt("unitsSold");
                String name = rs.getString("d.name");
                int price = rs.getInt("d.price");
                String image = rs.getString("image");
                int sumSales = rs.getInt("sumSales");
                int cost = 0;
                PreparedStatement getDishCostQ = this.conn.prepareStatement(dishCostQuery);
                getDishCostQ.setInt(1, dishID);
                ResultSet rs2 = getDishCostQ.executeQuery();
                while (rs2.next()) {
                    cost = rs2.getInt("cost");
                }
                double profit = 0;
//                System.out.println(cost);
                if (cost != 0) {
//                    System.out.println("profit exists");
                    profit = (price - cost) *1.0/ cost *100;
                }
//                double x = 200*1.0/1000;
//                System.out.println(x);
                topDishes.add(new reportsDish(dishID, name,image, unitsSold, price, profit,sumSales));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return topDishes;
    }

    public List<reportSupplier> getTopSuppliers(){
        List<reportSupplier> topSuppliers = new ArrayList<>();
        try {
            PreparedStatement getTopSuppliersQ = this.conn.prepareStatement(getTopSuppliersQuery);
            now = LocalDateTime.now();
            getTopSuppliersQ.setString(1,dtf.format(now.minusDays(90)));
            ResultSet rs = getTopSuppliersQ.executeQuery();
            while (rs.next()){
                int supplierID = rs.getInt("r.supplierID");
                String name = rs.getString("firstName")+" "+ rs.getString("lastName");
                String Org=rs.getString("organization");
                int ordersDone = rs.getInt("ordersDone");
                int amount = rs.getInt("amount");

                PreparedStatement getRejectedCountQ = this.conn.prepareStatement(getRejectedOrderCountQuery);
                getRejectedCountQ.setString(2,dtf.format(now.minusDays(90)));
                getRejectedCountQ.setInt(1,supplierID);
                ResultSet rs2 = getRejectedCountQ.executeQuery();
                int rejectCount = 0;
                while (rs2.next()){
                    rejectCount = rs2.getInt("rejectedCount");
                }
                double AcceptPercentage = (ordersDone-rejectCount)*1.0/(ordersDone+rejectCount)*100;
                topSuppliers.add(new reportSupplier(supplierID,name,Org,ordersDone,AcceptPercentage,amount));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return topSuppliers;
    }

    public Integer getAllTotalDishSales(){
        try {
            PreparedStatement getTotalDishSales = this.conn.prepareStatement(getAllTotalDishSalesQuery);
            now = LocalDateTime.now();
            getTotalDishSales.setString(1,dtf.format(now.minusDays(90)));
            ResultSet rs = getTotalDishSales.executeQuery();
            int x = 0;
            while (rs.next()){
                x = rs.getInt("unitsSold");
            }
            return x;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Integer getAllSupplierOrders(){
        try {
            PreparedStatement getAllSupplierOrdersQ = this.conn.prepareStatement(getAllSupplierOrdersQuery);
            now = LocalDateTime.now();
            getAllSupplierOrdersQ.setString(1,dtf.format(now.minusDays(90)));
            ResultSet rs = getAllSupplierOrdersQ.executeQuery();
            int x = 0;
            while (rs.next()){
                x = rs.getInt("ordersDone");
            }
            return x;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
