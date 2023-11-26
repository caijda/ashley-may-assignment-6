package com.coderscampus;

import java.time.YearMonth;

public class Sale {
    private YearMonth month;
    private Integer sales;
    public Sale(){}
    public Sale (YearMonth month, Integer sales){
        this.month = month;
        this.sales = sales;
    }
    public YearMonth getMonth() {
        return month;
    }
    public void setMonth(YearMonth month) {
        this.month = month;
    }
    public Integer getSales() {
        return sales;
    }
    public void setSales(Integer sales) {
        this.sales = sales;
    }
    @Override
    public String toString() {
        return "Sale [month=" + month + ", sales=" + sales + "]";
    }
    
}
