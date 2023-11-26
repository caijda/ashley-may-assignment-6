package com.coderscampus;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        SaleService service = new SaleService();
        List<Sale> sales = new ArrayList<>();

        service.salesReport(sales, "model3.csv");
        service.salesReport(sales, "models.csv");
        service.salesReport(sales, "modelx.csv");
    }
}
