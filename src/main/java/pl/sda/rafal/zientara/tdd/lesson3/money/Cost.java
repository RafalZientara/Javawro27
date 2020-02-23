package pl.sda.rafal.zientara.tdd.lesson3.money;

import java.time.LocalDate;

public class Cost {
    private final String shopName;
    private final LocalDate date;
    private final double price;

    public Cost(String shopName, double price, LocalDate date) {
        this.shopName = shopName;
        this.date = date;
        this.price = price;
    }

    @Override
    public String toString() {
        return  "shopName='" + shopName + '\'' +
                ", date=" + date +
                ", price=" + price +
                '}';
    }
}
