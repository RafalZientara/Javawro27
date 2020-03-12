package pl.sda.rafal.zientara.tdd.tddExcercises.ex2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LettersExcTest {

    @Test
    void testing1(){
        assertEquals(LettersExc.firstLetterToUpperCase("mateusz niedbal"),"Mateusz Niedbal");
    }
    @Test
    void testing2(){
        assertEquals(LettersExc.firstLetterToUpperCase("kedzierzyn - kozle"),"Kedzierzyn - Kozle");
    }
    @Test
    void testing3(){
        assertEquals(LettersExc.firstLetterToUpperCase("ala ma kota, ktory zdechl"),"Ala Ma Kota, Ktory Zdechl");
    }
    @Test
    void testing4(){
        assertEquals(LettersExc.firstLetterToUpperCase("szklarska poreba"),"Szklarska Poreba");
    }
    @Test
    void testing5(){
        assertEquals(LettersExc.firstLetterToUpperCase(null),null);
    }
    @Test
    void testing6(){
        assertEquals(LettersExc.firstLetterToUpperCase(""),"");
    }
    @Test
    void testing7(){
        assertEquals(LettersExc.firstLetterToUpperCase(" , ")," , ");
    }
    @Test
    void testing8(){
        assertEquals(LettersExc.firstLetterToUpperCase("Zenek dąbrowski-nowacki"),"Zenek Dąbrowski-Nowacki");
    }
    @Test
    void testing9(){
        assertEquals(LettersExc.firstLetterToUpperCase("banan"),"Banan");
    }
    @Test
    void testing10(){
        assertEquals(LettersExc.firstLetterToUpperCase("MARCIN SZWED"),"MARCIN SZWED");
    }
    @Test
    void testing11(){
        assertEquals(LettersExc.firstLetterToUpperCase("kędzierzyn koźle"),"Kędzierzyn Koźle");
    }
    @Test
    void testing12(){
        assertEquals(LettersExc.firstLetterToUpperCase("nOWA szZKOla"),"NOWA SzZKOla");
    }


}