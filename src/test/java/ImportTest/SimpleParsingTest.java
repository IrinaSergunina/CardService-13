package ImportTest;

import CardsInfo.Card;
import CardsInfo.CardsImport;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SimpleParsingTest {
    public static List<Card> cards = new ArrayList<>();

    @Before
    public void prep() throws IOException {
        String filePath = System.getProperty("user.dir") + "/src/test/resources/cards1.csv";
        SimpleParsingTest.cards = CardsImport.ParseCardsCsv(filePath);
    }

    //проверка, что есть хотя бы одна карта
    @Test
    public void simpleParsingTest1() {
        Optional<Card> card1 = SimpleParsingTest.cards.stream().findAny();
        Assert.assertTrue(card1.isPresent());
    }

    //проверка, что карта только одна
    @Test
    public void simpleParsingTest2() {
        Assert.assertEquals(1, SimpleParsingTest.cards.stream().count());
    }

    //проверка, что карта создана корректно
    @Test
    public void simpleParsingTest3() {
        Optional<Card> card3 = SimpleParsingTest.cards.stream().findAny();
        Assert.assertEquals("Optional[Card [numCard=427438F01ED29204, status=+, expirationDate=2025-02-28, clientRef=Client [lastname=Халабурдина, firstname=Юлия, middlename=Алексеевна, dul=6008 123789, birthDate=1990-02-12, email=mail@mail.ru]]]", card3.toString());
    }
}
