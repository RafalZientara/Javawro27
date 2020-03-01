package pl.sda.rafal.zientara.programowanie2.lesson4.money;

import java.time.LocalDate;
import java.util.Objects;

public class Cost {
    final String shopName;
    final LocalDate date;
    final double price;

    public Cost(String shopName, double price, LocalDate date) {
        this.shopName = shopName;
        this.price = price;
        this.date = date;
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

    @Override
    public String toString() {
        return "shopName " + shopName + '\'' +
                " price " + price +
                " date " + date;
    }
}
