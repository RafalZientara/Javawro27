package pl.sda.rafal.zientara.programming_II.lesson_3.money;

import java.time.LocalDate;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cost cost = (Cost) o;
        return Double.compare(cost.price, price) == 0 &&
                Objects.equals(shopName, cost.shopName) &&
                Objects.equals(date, cost.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shopName, date, price);
    }

    public String getShopName() {
        return shopName;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return
                "shopName='" + shopName + '\'' +
                ", price=" + price +
                ", date=" + date;
    }
}
