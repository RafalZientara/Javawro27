package pl.sda.rafal.zientara.programming_II.lesson_3.money;

import java.time.LocalDate;

public class Cost {

    private final String shopName;
    private final LocalDate date;
    private final double price;
//zmiana kolejnoœci parametrów, przez prawy przycik, refaktor, change signature
    public Cost(String shopName, double price, LocalDate date) {
        this.shopName = shopName;
        this.date = date;
        this.price = price;
    }

    @Override
    public String toString() {
        return
                "shopName='" + shopName + '\'' +
                ", price=" + price +
                ", date=" + date;
    }
}
