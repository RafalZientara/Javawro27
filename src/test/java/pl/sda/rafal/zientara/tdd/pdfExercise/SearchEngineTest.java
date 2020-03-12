package pl.sda.rafal.zientara.tdd.pdfExercise;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SearchEngineTest {

    private SearchEngine searchEngine;
    private String text = "Włócznia - ogólna nazwa długiej prostej broni drzewcowej.";

    @BeforeEach
    void setup() {
        searchEngine = new SearchEngine();
        searchEngine.getResourceList().add(text);
    }

    @Test
    void inputShouldBeFoundInResource() {
        boolean isItThere = searchEngine.isSearchedItemInResource("prostej broni drzewcowej.");
        assertTrue(isItThere);
    }

    @Test
    void isTitleWellSeparated() {
        boolean isItThere = searchEngine.isSearchedItemInResource("prostej broni drzewcowej.");
        assertEquals("Włócznia", searchEngine.getTitlesList().get(0));
    }

    @Test
    void isContentWellSplited() {
        boolean isItThere = searchEngine.isSearchedItemInResource("prostej broni drzewcowej.");
        assertEquals("ogólna nazwa długiej prostej broni drzewcowej.", searchEngine.getContentList().get(0));
    }

    @Test
    void isNoEntriesMessageWorkingFine() {
        String regex = "Ala ma kota.";
        assertEquals("Brak wyników.", searchEngine.noEntriesMessage(regex));
    }

    @Test
    void isNoEntriesMessageWorkingFine2() {
        String regex = "prostej broni drzewcowej.";
        assertNull(searchEngine.noEntriesMessage(regex));
    }

}