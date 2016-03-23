package hackathon.intelife;

public class QuoteItem {

    private int quantity;
    private String description;
    private double price;

    public QuoteItem(int quantity, String description, double price) {
        setQuantity(quantity);
        setDescription(description);
        setPrice(price);
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
