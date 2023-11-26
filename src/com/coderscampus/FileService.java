package com.coderscampus;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileService {

    public List<Sale> readFile(String fileName) throws FileNotFoundException, IOException{
        SaleService service = new SaleService();
        List<Sale> sales = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String line = reader.readLine();
            line = reader.readLine();
            while(line != null && line.contains(",")){
                sales.add(service.createSaleFromString(line));
                line = reader.readLine();
            }
        }
        return sales;
    }
}
