package MailingTest;

import CardsInfo.Card;
import CardsInfo.CardsImport;
import Mailing.MailingThread;
import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class SimpleMailingTest {
    @Test
    public void simpleMailingTest() throws IOException, ClassNotFoundException {
        // загружаем карты для тестирования
        String filePath = System.getProperty("user.dir") + "/src/test/resources/cards2.csv";
        List<Card> cards = CardsImport.ParseCardsCsv(filePath);
        //проверяем, что карты заблокированы
        int result = 0;
        MailingThread.Mailing(cards);
        for (Card card : cards) {
            if ((LocalDate.now().isAfter(card.getExpirationDate()))&&(card.getStatus().equals("Y"))) {
                result+=1;
            }
        }
        Assert.assertEquals(2, result);
    }
}

