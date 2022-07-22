package CardsInfo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Client implements Serializable {
    private String lastname;
    private String firstname;
    private String middlename;
    private String dul;
    private LocalDate birthDate;
    private String email;
    private static List<Client> clients  = new ArrayList<>();

    public Client(String lastname, String firstname, String middlename, String dul, LocalDate birthDate, String email) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.middlename = middlename;
        this.dul = dul;
        this.birthDate = birthDate;
        this.email = email;
    }

    public String toString() {
        return "Client [lastname=" + lastname
                + ", firstname=" + firstname
                + ", middlename=" + middlename
                + ", dul=" + dul
                + ", birthDate=" + birthDate
                + ", email=" + email + "]";
    }

    public String getLastname() {
        return this.lastname;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public String getMiddlename() {
        return this.middlename;
    }

    public String getDul() {return this.dul;}

    public LocalDate getBirthDate() {return this.birthDate;}

    public String getEmail() {
        return this.email;
    }

    public static void initClients(Client client){
        clients.add(client);
    }

    //считаем, что ФИО+ДУЛ+ДР клиента уникальны
    private static Client getClientByFIO(String lastname, String firstname, String middlename, String dul, LocalDate birthDate) {
        Client client = clients.stream()
                .filter(x -> x.getFirstname().equals(firstname)&&x.getMiddlename().equals(middlename)&&x.getLastname().equals(lastname)&&x.getDul().equals(dul)&&x.getBirthDate().equals(birthDate))
                .findAny()
                .orElse(null);
        return client;
    }

    public static Client addClient(String lastname, String firstname, String middlename, String dul, LocalDate birthDate, String email) {
        Client result = Client.getClientByFIO(lastname, firstname, middlename, dul, birthDate);
        if (result == null) {
            Client client = new Client(lastname, firstname, middlename, dul, birthDate, email);
            initClients(client);
            System.out.println("Создали клиента " + client.toString());
            return client;
        } else {
            System.out.println("Уже есть клиент " + result.toString());
            return result;
        }
    }
}
