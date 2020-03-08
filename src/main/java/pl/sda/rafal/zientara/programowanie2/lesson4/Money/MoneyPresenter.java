package pl.sda.rafal.zientara.programowanie2.lesson4.Money;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MoneyPresenter implements MoneyContract.Presenter {

    private final MoneyContract.View view;
    private List<Cost> costs = new ArrayList<>();
    private List<Cost> lastResult = new ArrayList<>();
    private double fromPrice;
    private String word;
    private double toPrice;
    private LocalDate fromDate;
    private LocalDate toDate;
    private double result;


    public MoneyPresenter(MoneyContract.View view) {
        this.view = view;
    }

    @Override
    public void prepareData() {
        File file = new File("zakupy.csv");
        try {
            FileReader reader = new FileReader(file);
            BufferedReader buffer = new BufferedReader(reader);
            String line = buffer.readLine();
            boolean firstIgnored = false;
            while (line!=null){
                if (!firstIgnored){
                    firstIgnored = true;
                } else {
                    Cost cost = parseCost(line);
                    costs.add(cost);
                }
                line = buffer.readLine();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initData() {
        view.refreshList(costs);
    }

    private Cost parseCost(String line) {
        String[] splitLine = line.split(";");
        String shopName = splitLine[0];
        double price = Double.parseDouble(splitLine[1]
                .replace(",",".")
                .replace("\"",""));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern ("yyyy-MM-dd");
        LocalDate date = LocalDate.parse (splitLine[2],formatter);

        return new Cost(shopName,price,date);
    }

    @Override
    public void onWordChange(String word) {
        this.word = word;
        lastResult = costs.stream()
                .filter(cost -> cost.getShopName().contains(word))
                .collect(Collectors.toList());
        view.refreshList(lastResult);
    }
    private void refreshResults(){
        Stream<Cost> stream = costs.stream();
        if (word!= null){
            stream = stream.filter(cost -> cost.getShopName().contains(word));
        }
        if(fromPrice >0){
            stream = stream.filter(cost -> cost.getPrice() >= fromPrice);
        }
        if (toPrice >0){
            stream = stream.filter(cost -> cost.getPrice() <= toPrice);
        }
        if (fromDate != null){
            stream = stream.filter(cost -> !cost.getDate().isBefore(fromDate));
        }
        if (toDate != null){
            stream = stream.filter(cost -> !cost.getDate().isAfter(toDate));
        }
        lastResult = stream.collect(Collectors.toList());
    }

    private double countSum(){
        Optional<Double> reduce = lastResult.stream()
                .map(cost -> cost.getPrice())
                .reduce((acc,value)-> acc + value);
        return  reduce.orElseGet(()->0D);
    }


    @Override
    public void onPriceFromChange(double fromPrice) {
        this.fromPrice = fromPrice;
        refreshResults();
        view.refreshList(lastResult);

    }

    @Override
    public void onPriceToChange(double toPrice) {
        this.toPrice = toPrice;
        refreshResults();
        view.refreshList(lastResult);
    }

    @Override
    public List<Cost> getLastResult() {
        return lastResult;
    }

    @Override
    public void onFromDateChange(LocalDate fromDate) {
        this.fromDate = fromDate;
        refreshResults();
        view.refreshList(lastResult);
    }

    @Override
    public void onToDateChange(LocalDate toDate) {
        this.toDate = toDate;
        refreshResults();
        view.refreshList(lastResult);
    }
}
