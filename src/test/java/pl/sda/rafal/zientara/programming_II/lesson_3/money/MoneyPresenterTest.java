package pl.sda.rafal.zientara.programming_II.lesson_3.money;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class MoneyPresenterTest {

    private MoneyContract.Presenter presenter;
    private MoneyContract.View view;

    @BeforeEach
    public void setup(){
        view = Mockito.mock(MoneyContract.View.class); //przekazujemy obiekt na którym implementujemy interfejs
        presenter = new MoneyPresenter(view);
        presenter.prepareData();
    }

    @Test
    public void initPresenter() {
        presenter.initData();//ma wyœwietliæ dane
        Mockito.verify(view).refreshLIst(any());
    }

    @Test
    public void whenQueryChangeRefreshResults(){
        List<Cost> expected = Arrays.asList(new Cost("test", 1, LocalDate.of(2020,1,1)));
        presenter.onWordChange("test");
        Mockito.verify(view, Mockito.times(1)).refreshLIst(expected);//times 1 - 1 raz

    }

    @Test
    public void findAllZabka(){
        List<Cost> expected = Arrays.asList(
                new Cost("zabka",12, LocalDate.of(2020,1,16)),
                new Cost("zabka",53.23, LocalDate.of(2020,1,23)),
                new Cost("zabka",12, LocalDate.of(2020,1,16)),
                new Cost("zabka",700.01, LocalDate.of(2020,1,23)));
        presenter.onWordChange("zab");
        Mockito.verify(view).refreshLIst(expected);//times 1 - 1 raz

    }
    @Test
    public void findCostByNameAndPrice(){
            presenter.onWordChange("zabka");
            presenter.onPriceFromChange(400.);
            Mockito.verify(view, Mockito.times(2)).refreshLIst(any());

            List<Cost> lastResult = presenter.getLastResult();
            assertEquals(5, lastResult.size());
    }

    @Test
    public void filterByToPrice(){
        presenter.onPriceToChange(15);
        Mockito.verify(view, Mockito.times(1)).refreshLIst(any());
        List<Cost> lastResult = presenter.getLastResult();
        assertEquals(16, lastResult.size());
    }

    @Test
    public void filterByFromDate(){
        presenter.onFromDateChange(LocalDate.of(2020, 1, 20));
        Mockito.verify(view, Mockito.times(1)).refreshLIst(any());
        List<Cost> lastResult = presenter.getLastResult();
        assertEquals(6, lastResult.size());
    }

    @Test
    public void filterByToDate(){
        presenter.onToDateChange(LocalDate.of(2020, 1, 31));
        Mockito.verify(view, Mockito.times(1)).refreshLIst(any());
        List<Cost> lastResult = presenter.getLastResult();
        assertEquals(15, lastResult.size());
    }

    //todo write test to count sum


}