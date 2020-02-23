package pl.sda.rafal.zientara.programowanie2.lesson4.money;

import java.time.LocalDate;

public class Cost {
    private final String shopName;
    private final LocalDate date;
    private final double price;

    public Cost(String shopName, double price, LocalDate date) {
        this.shopName = shopName;
        this.price = price;
        this.date = date;
    }

    @Override
    public String toString() {
        return "shopName " + shopName + '\'' +
                " price " + price +
                " date " + date;
    }
}
