package com.sebastian.foodSale;

import javafx.collections.FXCollections;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ProductData {

    private static ProductData instance = new ProductData();
    private static String fileName = "productData.txt";
    private static String report = "report.txt";
    private List<Product> productList;

    private ProductData() {
    }

    public static ProductData getInstance() {
        return instance;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    //    saves productData in productData.txt
    public void loadProductData() throws IOException {

        productList = FXCollections.observableArrayList();
        Path path = Paths.get("Resources/data/" + fileName);

        try (BufferedReader br = Files.newBufferedReader(path)) {
            String input;
            while ((input = br.readLine()) != null) {
                String[] itemPieces = input.split("\t");

                String name = itemPieces[0];
                String amount = itemPieces[1];
                double oldPrice = Double.parseDouble(itemPieces[2]);
                double newPrice = Double.parseDouble(itemPieces[3]);
                String imgPath = itemPieces[4];
                String description = itemPieces[5];

                Product productItem = new Product(name, amount, oldPrice, newPrice, imgPath, description);
                productList.add(productItem);
            }

        }
    }

    //    reads stored productData form productData.txt
    public void storeProductData() throws IOException {

        Path path = Paths.get("Resources/data/" + fileName);
        try (BufferedWriter bw = Files.newBufferedWriter(path)) {
            for (Product item : productList) {
                bw.write(String.format("%s\t%s\t%s\t%s\t%s\t%s",
                        item.getName(),
                        item.getAmount(),
                        item.getOldPrice(),
                        item.getNewPrice(),
                        item.getImgPath(),
                        item.getDescription()));
                bw.newLine();
            }

        }
    }

    public void printReport() throws IOException {

        Path path = Paths.get(report);
        try (BufferedWriter bw = Files.newBufferedWriter(path)) {
            for (Product item : productList) {
                bw.write(String.format("Name: %s\tAmount: %s\tOld Price: %s\tNew Price: %s",
                        String.valueOf(item.getName()),
                        String.valueOf(item.getAmount()),
                        String.valueOf(item.getOldPrice()),
                        String.valueOf(item.getNewPrice())));
                bw.newLine();
            }
        }
    }

}
