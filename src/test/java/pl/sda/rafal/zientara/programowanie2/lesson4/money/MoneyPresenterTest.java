package pl.sda.rafal.zientara.programowanie2.lesson4.money;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

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
        Mockito.verify(view).refreshList(any());
    }

    @Test
    public void whenQueryChangedRefreshResult() {
        List<Cost> expected = Arrays.asList(
                new Cost("test", 1,
                        LocalDate.of(2020, 1, 16)));
        presenter.onWordChange("test");
        Mockito.verify(view, Mockito.times(1))
                .refreshList(expected);
    }

    @Test
    public void findAllFrogs() {
        List<Cost> expected = Arrays.asList(
                new Cost("zabka", 53.23, LocalDate.of(2020, 1, 23)),
                new Cost("zabka", 12, LocalDate.of(2020, 1, 16)),
                new Cost("zabka", 700.01, LocalDate.of(2020, 1, 23)));
        presenter.onWordChange("zab");
        Mockito.verify(view, Mockito.times(1))
                .refreshList(expected);
    }

    @Test
    public void findCostByNameAndPrice() {
        presenter.onWordChange("zabka");
        presenter.onPriceFromChange(500.);
        Mockito.verify(view, Mockito.times(2)).refreshList(any());
        List<Cost> lastResult = presenter.getLastResult();
        assertEquals(1, lastResult.size());
    }

    @Test
    public void filterByToPrice() {
        presenter.onPriceToChange(15);
        Mockito.verify(view, Mockito.times(1)).refreshList(any());
        List<Cost> lastResult = presenter.getLastResult();
        assertEquals(2, lastResult.size());
    }

    @Test
    public void filterByFromDate() {
        presenter.onFromDateChange(LocalDate.of(2020, 1, 20));
        Mockito.verify(view, Mockito.times(1)).refreshList(any());
        List<Cost> lastResult = presenter.getLastResult();
        assertEquals(6, lastResult.size());
    }

    @Test
    public void filterToDate() {
        presenter.onToDateChange(LocalDate.of(2019, 12, 31));
        Mockito.verify(view, Mockito.times(1)).refreshList(any());
        List<Cost> lastResult = presenter.getLastResult();
        assertEquals(6, lastResult.size());
    }


}