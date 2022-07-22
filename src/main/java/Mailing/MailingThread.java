package Mailing;

import CardsInfo.Card;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

// поток, который отвечает за рассылку уведомлений
public class MailingThread implements Runnable{
    //запуск потока
    public void run(){
        try {
            System.out.println("Старт потока " + Thread.currentThread().getName() + ", текущее время : "
                    + LocalDateTime.now());
            List<Card> cardsList = Card.takeCards();
            MailingThread.Mailing(cardsList);
            Card.saveCards(Card.cards);
            System.out.printf("Финиш потока " + Thread.currentThread().getName() + ", текущее время : "
                    + LocalDateTime.now());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //рассылка уведомлений
    public static void Mailing (List<Card> cardsList) throws IOException, ClassNotFoundException {
        LocalDate today = LocalDate.now();
        for (Card card : cardsList) {
            if ((today.isAfter(card.getExpirationDate()))&&(card.getStatus().equals("+"))) {
                String filePath = System.getProperty("user.dir") + "/src/main/resources/file/mailing.txt";
                FileOutputStream fileOutputStream = new FileOutputStream(filePath, true);
                fileOutputStream.write(("mailto: "+card.getClientRef().getEmail()+"\r\nДобрый день, " +card.getClientRef().getFirstname()+ "!\r\nУ вашей карты " +card.getNumCard()+ " истёк срок действия. Она будет заблокирована.\r\n\r\n").getBytes());
                fileOutputStream.close();
                System.out.println(card);
                Card.closed(card);
            }
            Card.cards.add(card);
        }
    }
}

