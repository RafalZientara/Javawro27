package pl.sda.rafal.zientara.programowanie2.lesson4.money;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

class MoneyPresenterTest {
    private MoneyContract.Presenter presenter;
    private MoneyContract.View view;

    @BeforeEach
    public void setup() {
        view = Mockito.mock(MoneyContract.View.class);
        presenter = new MoneyPresenter(view);
        presenter.prepareData();
    }

    @Test

    public void initPresenter() {
        presenter.initData();
        verify(view).refreshList(any());
    }

    @Test
    public void whenQueryChangedRefreshResult() {
        //presenter.initData();
        List<Cost> expecterd = Collections.singletonList(new Cost("test", 1, LocalDate.of(2020, 01, 01)));
        presenter.onWordChange("test");
        verify(view, times(1)).refreshList(expecterd);

    }

    @Test
    public void findAllZabka() {
        //presenter.initData();
        List<Cost> expecterd = Arrays.asList(new Cost("zabka", 12, LocalDate.of(2020, 1, 16)),
                new Cost("zabka", 53.23, LocalDate.of(2020, 1, 23)),
                new Cost("zabka", 12, LocalDate.of(2020, 1, 16)),
                new Cost("zabka", 700.01, LocalDate.of(2020, 1, 23)));
        presenter.onWordChange("zab");
        verify(view, times(1)).refreshList(expecterd);
    }

    @Test
    public void findCostByNameAndPrice() {
        presenter.onWordChange("zabka");
        presenter.onPriceFromChange(400d);
        verify(view, times(2))
                .refreshList(any());
        List<Cost> lastResult = presenter.getLastResult();
        assertEquals(1, lastResult.size());
    }

    @Test
    public void filterByToPrice() {
        presenter.onPriceToChange(15);
        verify(view, times(1)).refreshList(any());
        List<Cost> lastResult = presenter.getLastResult();
        assertEquals(3, lastResult.size());
    }

    @Test
    public void filterByFromDate() {
        presenter.onFromDateChange(LocalDate.of(2020, 1, 20));
        verify(view, times(1)).refreshList(any());
        List<Cost> lastResult = presenter.getLastResult();
        assertEquals(6, lastResult.size());
    }

    @Test
    public void filterByToDate(){
        presenter.onToDateChange(LocalDate.of(2020, 1, 31));
        verify(view, times(1)).refreshList(any());
        List<Cost> lastResult = presenter.getLastResult();
        assertEquals(17, lastResult.size());

    }

}