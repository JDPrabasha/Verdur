package Manager.Report;

import java.util.Map;

public class reportsDish {
    private Integer dishID;
    private String name;
    private String image;
    private Integer unitsSold;
    private Integer price;
    private Double rating;
    private Double profitPercentage;
    private Integer totalSales;
    private Map<String,Integer> lastMonthSales;

    public reportsDish(Integer dishID, String name, String image, Integer unitsSold, Integer price, Double rating,Map<String,Integer> lastMonthSales) {
        this.dishID = dishID;
        this.name = name;
        this.image = image;
        this.unitsSold = unitsSold;
        this.price = price;
        this.rating = rating;
        this.lastMonthSales = lastMonthSales;
    }

    public reportsDish(Integer dishID, String name, String image, Integer unitsSold, Integer price, Double profitPercentage,Integer totalSales) {
        this.dishID = dishID;
        this.name = name;
        this.image = image;
        this.unitsSold = unitsSold;
        this.price = price;
        this.profitPercentage = profitPercentage;
        this.totalSales = totalSales;
    }
}
