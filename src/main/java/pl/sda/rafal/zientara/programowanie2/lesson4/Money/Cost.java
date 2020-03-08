package pl.sda.rafal.zientara.programowanie2.lesson4.Money;

import java.time.LocalDate;
import java.util.Objects;

public class Cost {
    private final String shopName;
    private final double price;
    private final LocalDate date;

    public Cost(String shopName, double price, LocalDate date) {
        this.shopName = shopName;
        this.date = date;
        this.price = price;
    }

    public String getShopName() {
        return shopName;
    }

    public double getPrice() {
        return price;
    }

    public LocalDate getDate() {
        return date;
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
        return Objects.hash(shopName, price, date);
    }

    @Override
    public String toString() {
        return "shop='" + shopName + '\'' +
                ", price=" + price +
                ", date=" + date;
    }
}
