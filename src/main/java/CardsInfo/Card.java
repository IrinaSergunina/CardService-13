package CardsInfo;

import java.io.*;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.*;

public class Card implements Serializable {
    private String numCard;
    private String status;
    private LocalDate expirationDate;
    private Client clientRef;
    public static List<Card> cards = new ArrayList<>();

    //простой конструктор
    public Card(String numCard, String status, LocalDate expirationDate, Client clientRef) {
        this.numCard = numCard;
        this.status = status;
        this.expirationDate = expirationDate;
        this.clientRef = clientRef;
    }

    public String toString() {
        return "Card [numCard=" + numCard
                + ", status=" + status
                + ", expirationDate=" + expirationDate
                + ", clientRef=" + clientRef + "]";
    }

    public String getStatus() {
        return this.status;
    }

    public String getNumCard() {
        return this.numCard;
    }


    public LocalDate getExpirationDate() {
        return this.expirationDate;
    }

    public Client getClientRef() {
        return this.clientRef;
    }

    //добавление карты в коллекцию
    public static void initCards(Card card) {
        cards.add(card);
    }

    //поиск карты в коллекции по номеру карты
    private static Card getCardByNum(String numCard) {
        Card card = cards.stream()
                .filter(x -> x.getNumCard().equals(numCard))
                .findAny()
                .orElse(null);
        return card;
    }

    //создание карты с проверкой на дубли по номеру карты
    public static Card addCard(String numCard, String status, LocalDate expirationDate, Client clientRef) throws IOException {
        Card result = Card.getCardByNum(numCard);
        if (result == null) {
            Card card = new Card(numCard, status, expirationDate, clientRef);
            initCards(card);
            System.out.println("Добавили карту " + card.toString());
            return card;
        } else {
            System.out.println("Уже есть карта " + result.toString());
            return result;
        }
    }

    //изменение статуса карты
    public static void changeStatus(Card card, String status){
        if (!card.getStatus().equalsIgnoreCase(status)){
            card.setStatus(status);
            System.out.println("Установлен статус " + status + " для карты " + card);
        }
    }

    private void setStatus(String status) {
        this.status = status;
    }

    //закрытие карты
    public static void closed(Card card){
        changeStatus(card,"Y");
    }

    //сериализация карт
    public static void saveCards( List<Card> cardsList) {
        try {
            FileOutputStream fos = new FileOutputStream("temp.out");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (Card card: cardsList) {
                oos.writeObject(card);
            }
            oos.flush();
            oos.close();
        }catch(EOFException eof){
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    //десериализация карт
    public static List<Card> takeCards() throws IOException {
        FileInputStream fis = new FileInputStream("temp.out");
        ObjectInputStream oin = new ObjectInputStream(fis);
        ArrayList<Card> cardsList = new ArrayList();
        try {
            while (fis.available() != -1) {
                //Read object from file
                Card card = (Card) oin.readObject();
                cardsList.add(card);
                System.out.println("Извлекли из кэша карту " + card);
            }
        } catch (EOFException ex) {
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    return cardsList;}
}