package foodSale;

public class Product {
    private String name;
    private String amount;
    private int oldPrice;
    private int newPrice;
    private String imgPath;

    public Product(String name, String amount, int oldPrice, int newPrice, String imgPath) {
        this.name = name;
        this.amount = amount;
        this.oldPrice = oldPrice;
        this.newPrice = newPrice;
        this.imgPath = imgPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public int getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(int oldPrice) {
        this.oldPrice = oldPrice;
    }

    public int getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(int newPrice) {
        this.newPrice = newPrice;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
