package hackathon.intelife;

import java.util.ArrayList;

public abstract class QuoteBuilder extends ArrayList<QuoteItem> {

    public abstract void update();
    public double discountRate = 0.0;

    public double getSubtotal() {
        double subtotal = 0.0;

        for (QuoteItem quote: this) {
            subtotal += quote.getQuantity()*quote.getPrice();
        }

        return subtotal;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setQuantity(int discountRate) {
        this.discountRate = discountRate;
    }
}
