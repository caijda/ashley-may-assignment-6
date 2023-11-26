package com.coderscampus;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class SaleService {

    public Sale createSaleFromString(String line) {
        String[] parsedLine = new String[2];
        parsedLine = line.split(",");
        Sale sale = new Sale(YearMonth.parse(parsedLine[0], DateTimeFormatter.ofPattern("MMM-yy")),
                Integer.parseInt(parsedLine[1]));
        return sale;
    }

    public String ModelName(String fileName) {
        String modelName;
        if (fileName.contains("3")) {
            modelName = "Model 3";
        } else if (fileName.contains("x")) {
            modelName = "Model X";
        } else{
            modelName = "Model S";
        }
        return modelName;
    }

    public void salesReport(List<Sale> sales, String fileName) throws FileNotFoundException, IOException {
        FileService service = new FileService();
        String modelName = ModelName(fileName);

        System.out.println(modelName + " Yearly Sales Report");
        System.out.println("---------------------------");

        sales = service.readFile(fileName);

        calculateSales(sales, 2016);
        calculateSales(sales, 2017);
        calculateSales(sales, 2018);
        calculateSales(sales, 2019);

        System.out.println("");



    }

    public void calculateSales(List<Sale> sales, Integer year) {
        Integer totalSales = sales.stream()
                .filter(s -> s.getMonth()
                        .isAfter(YearMonth.parse("12" + (year - 1), DateTimeFormatter.ofPattern("MMyyyy"))))
                .filter(s -> s.getMonth()
                        .isBefore(YearMonth.parse("01" + (year + 1), DateTimeFormatter.ofPattern("MMyyyy"))))
                .map(s -> s.getSales())
                .collect(Collectors.summingInt(Integer::intValue));

        if (totalSales != 0) {
            System.out.println(year + " -> " + totalSales);
        }
    }

}
