package com.coderscampus;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
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

    public void salesReport(String fileName) throws FileNotFoundException, IOException {
        FileService service = new FileService();
        List<Sale> sales = new ArrayList<>();
        String modelName = ModelName(fileName);

        System.out.println(modelName + " Yearly Sales Report");
        System.out.println("---------------------------");

        sales = service.readFile(fileName);

        List<Integer> years = sales.stream()
            .map(s -> s.getMonth().getYear())
            .distinct()
            .collect(Collectors.toList());
                
        for (Integer year : years) {
        calculateSales(sales, year);
        }

        System.out.println("");

        bestAndWorst(sales, modelName);

        System.out.println("");

        sales.clear();
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

    public void bestAndWorst(List<Sale> sales, String modelName){
        Sale best = sales.stream()
            .max(Comparator.comparing(Sale::getSales))
            .orElseThrow(NoSuchElementException::new);
        System.out.println("The best month for " + modelName + " was:" + best.getMonth());

        Sale worst = sales.stream()
            .min(Comparator.comparing(Sale::getSales))
            .orElseThrow(NoSuchElementException::new);
        System.out.println("The worst month for " + modelName + " was:" + worst.getMonth());
    }
}
