package CardsInfo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CardsImport {
    public static void main(String[] args) throws IOException {
        String filePath = System.getProperty("user.dir") + "/src/main/resources/file/cards.csv";
        List<Card> cards = ParseCardsCsv(filePath);
        Card.saveCards(Card.cards);
    }

    //Парсинг CSV файла по указанному пути и получение карт из него
    public static List<Card> ParseCardsCsv(String filePath) throws IOException {
        List<Card> cards = new ArrayList<>();
        List<String> fileLines = Files.readAllLines(Paths.get(filePath));
        for (String fileLine : fileLines) {
            String[] splitedText = fileLine.split(";");
            ArrayList<String> columnList = new ArrayList<String>();
            for (int i = 0; i < splitedText.length; i++) {
                columnList.add(splitedText[i]);
            }
            Client client = Client.addClient(columnList.get(3), columnList.get(4), columnList.get(5), columnList.get(6), LocalDate.parse(columnList.get(7), DateTimeFormatter.ofPattern("dd.MM.yyyy")), columnList.get(8));
            Card card = Card.addCard(columnList.get(0), columnList.get(1), LocalDate.parse(columnList.get(2), DateTimeFormatter.ofPattern("dd.MM.yyyy")), client);
            cards.add(card);
            }
        return cards;
    }
}
