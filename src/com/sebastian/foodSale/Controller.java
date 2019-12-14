package com.sebastian.foodSale;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Controller {
    @FXML
    public ImageView productImage;

    @FXML
    public TextArea productDescription;

    @FXML
    public Button updateBtn;

    @FXML
    public Button reportBtn;

    @FXML
    private TextField name;

    @FXML
    private TextField amount;

    @FXML
    private TextField oldPrice;

    @FXML
    private TextField newPrice;

    @FXML
    private ListView<Product> productListView;

//    private List<Product> productList;

    public void initialize() {

//        not needed since data comes form productData.txt

//        productList = new ArrayList<>();
//        productList.add(new Product("Pfeffer", "1 Stück", 3.49, 2.79, "Resources/images/pfeffer__600x600.jpg", "Schwarzer Pfeffer verleiht Ihren Speisen eine pikante Schärfe, besonders wenn er länger mitgekocht wird."));
//        productList.add(new Product("Schafmilchkäse", "200 Gramm Packung", 2.59, 1.99, "Resources/images/cheese_salakis__600x600.jpg", "Hier gibt es keine Beschreibung, weil unsere Handelskette kennst sich nur bedingt damit aus, wie man eine Werbebeschreibung schreibt."));
//        productList.add(new Product("Vöslauer", "1.5 Liter Flasche", 0.75, 0.49, "Resources/images/voslauer__600x600.jpg", "Spritziges Vöslauer Mineralwasser."));
//        productList.add(new Product("Zucker", "500 Gramm Paket", 1.39, 0.89, "Resources/images/zucker__600x600.jpg", "Natürliches Gelieren wird durch Apfelpektin unterstützt, welches im richtigen Verhältnis mit Zitronensäure und Kristallzucker abgemischt wurde."));
//        ProductData.getInstance().setProductList(productList);

        productListView.getSelectionModel().selectedItemProperty().addListener((observableValue, product, t1) -> {
            if (t1 != null) {
                Product item = productListView.getSelectionModel().getSelectedItem();
                name.setText(item.getName());
                amount.setText(item.getAmount());
                productDescription.setText(item.getDescription());
                try {
                    productImage.setImage(new Image(new FileInputStream(item.getImgPath())));
                    newPrice.setText(String.valueOf(item.getNewPrice()));
                    oldPrice.setText(String.valueOf(item.getOldPrice()));
                } catch (FileNotFoundException | NumberFormatException err) {
                    System.out.println(err.toString());
                }
            }
        });

        oldPrice.addEventHandler(KeyEvent.KEY_RELEASED, e -> {
            evaluateUpdateBtnStatus();
        });

        newPrice.addEventHandler(KeyEvent.KEY_RELEASED, e -> {
            evaluateUpdateBtnStatus();
        });

        updateBtn.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
            try {
                Product item = productListView.getSelectionModel().getSelectedItem();
                item.setNewPrice(Double.parseDouble(newPrice.getText()));
                item.setOldPrice(Double.parseDouble(oldPrice.getText()));
                productListView.getItems().setAll(ProductData.getInstance().getProductList());
                productListView.getSelectionModel().select(item);
                evaluateUpdateBtnStatus();
                ProductData.getInstance().storeProductData();
            } catch (NumberFormatException | IOException err) {
                System.out.println(err.toString());
            }
        });

        reportBtn.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
            try {
                ProductData.getInstance().printReport();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        productListView.getItems().setAll(ProductData.getInstance().getProductList());
        productListView.getSelectionModel().selectFirst();
    }

    private void evaluateUpdateBtnStatus() {

        try {
            Product item = productListView.getSelectionModel().getSelectedItem();
            boolean valueIsChanged = (((Double.parseDouble(oldPrice.getText()) != item.getOldPrice()) || Double.parseDouble(newPrice.getText()) != item.getNewPrice())
                    && !((oldPrice.getText()).isEmpty() && newPrice.getText().isEmpty()));
            if (valueIsChanged) updateBtn.setDisable(false);
            else updateBtn.setDisable(true);
        } catch (NumberFormatException | NullPointerException err) {
            updateBtn.setDisable(true);
            System.out.println(err.toString());
        }
    }
}
