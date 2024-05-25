import java.util.ArrayList;
import java.util.Scanner;
import static tools.utility.*;

public class Utente {
    Scanner keyboard = new Scanner(System.in);
    public String username;
    public String password;  // Rimosso static
    public boolean isAuthenticated = false;
    public ArrayList<Utente> listaUtenti;
    private final int MAX_UTENTI = 3;
    private int numeroUtenti = 0;
    public double saldoTel;

    public static String[] menuUtente = {
            "IMPOSTAZIONI ACCOUNT",
            "[1]-Visualizza credenziali",
            "[2]-Inserisci nuovo username",
            "[3]-Inserisci nuova password",
            "[4]-Aggiungi nuovo utente ",
            "[5]-Cambia utente ",
            "[6]-Esci"
    };

    public Utente() {
        this.username = "";
        this.password = "";
        this.saldoTel = 10; // saldo impostato di default
        this.listaUtenti = new ArrayList<>();
    }

    public void modificaUtente(Scanner keyboard, Utente myUtente) {
        System.out.println("\nVuoi aggiornare le impostazioni del profilo utente? (si/no)");
        String myAnswer = keyboard.nextLine();

        if (myAnswer.equalsIgnoreCase("si")) {

            //Dichiariamo la variabile generatedPw come static, in maniera tale che anche le altre classi possamo utilizzarala
            if(Main.Autentificazione(myUtente,Main.generatedPw,keyboard)) {
                switch (menu(menuUtente, keyboard)) {
                    case 1:
                        // Visualizza credenziali
                        System.out.println("Username: " + myUtente.username);
                        System.out.println("Password: " + censorPassword(myUtente.password));
                        break;
                    case 2:
                        // Modifica username
                        System.out.println("Modifica username da " + myUtente.username + " a: ");
                        myUtente.username = keyboard.nextLine();
                        Wait(1200);
                        System.out.println("Username modificato correttamente");
                        break;
                    case 3:
                        // Modifica password
                        System.out.println("Modifica password da " + censorPassword(myUtente.password) + " a: ");
                        myUtente.password = keyboard.nextLine();
                        System.out.println("Password modificata correttamente");
                        break;
                    case 4:
                        // Aggiunta nuovo utente:
                        aggiungiNuovoUtente(listaUtenti, myUtente.username, myUtente.password, keyboard);
                        break;
                    case 5:
                        // Cambia utente
                        stampaUtenti(listaUtenti);
                        cambiaUtente(listaUtenti, keyboard);
                        break;

                    default:
                        // Esci dalla modifica del profilo/account
                        break;
                }
            }
            else{
                System.out.println("Username o password errati");
            }
        } else {
            System.out.println("Modifica delle impostazioni del profilo utente annullata.");
        }
    }

    public void stampaUtenti(ArrayList<Utente> listaUtenti) {
        System.out.println("Lista degli utenti:");
        int numUtenti = 1;

        for (Utente utente : listaUtenti) {
            System.out.println(numUtenti + "° Utente");
            System.out.println("Username: " + utente.username);
            System.out.println("Password: " + censorPassword(utente.password));
            System.out.println("Saldo Telefonico: " + utente.saldoTel);
            numUtenti++;
            System.out.println();
        }
    }







    public void cambiaUtente(ArrayList<Utente> listaUtenti, Scanner keyboard) {
        System.out.println("Inserisci le credenziali dell'utente a cui passare:");

        System.out.println("Username:");
        String username = keyboard.nextLine();
        System.out.println("Password:");
        String password = keyboard.nextLine();

        for (Utente utente : listaUtenti) {
            if (utente.username.equals(username) && utente.password.equals(password)) {
                this.username = utente.username;
                this.password = utente.password;
                this.isAuthenticated = true;
                this.saldoTel = utente.saldoTel;

                System.out.println("Utente cambiato correttamente a: " + this.username);
                return;
            }
        }
        System.out.println("Credenziali inserite non valide. Impossibile cambiare utente.");
    }

    public Utente aggiungiNuovoUtente(ArrayList<Utente> listaUtenti, String username, String password, Scanner keyboard) {
        Utente myUtente = new Utente();
        String generatedPw = Main.generaPassword();
        System.out.println("Inserisci l'username del nuovo utente: ");
        myUtente.username = keyboard.nextLine();
        System.out.println("Vuoi richiedere una password del nuovo utente per la gestione delle attività principali? (si/no)");
        String myAnswer = keyboard.nextLine().toLowerCase();

        if (myAnswer.equalsIgnoreCase("si")) {

                myUtente.password = generatedPw;  // Imposta la password generata
                System.out.println("La password del nuovo utente per la gestione delle attività principali: " + generatedPw);
                listaUtenti.add(myUtente);
                System.out.println("Nuovo utente aggiunto correttamente.");

        } else {
            System.out.println("Richiesta di password annullata.");
            Wait(1500);
        }
        return myUtente;
    }

    public static String censorPassword(String password) {
        if (password == null || password.isEmpty()) {
            return "N/A";
        }
        StringBuilder censored = new StringBuilder();
        for (int i = 0; i < password.length(); i++) {
            censored.append('*');
        }
        return censored.toString();
    }

    public void stampaInfo(boolean isAuthenticated) {
        System.out.println("|---------------------------------------|");
        System.out.println("|               IMPOSTAZIONI PROFILO                |");
        System.out.println("|---------------------------------------|");
        System.out.println("| Username          : " + String.format("%-20s", this.username) + "|");
        System.out.println("| Password           : " + String.format("%-20s", censorPassword(this.password)) + "|");
        if (isAuthenticated) {
            System.out.println("| AUTENTIFICAZIONE           :ON");
        } else {
            System.out.println("| AUTENTIFICAZIONE          :OFF");
            System.out.println("| Per autentificarti devi prima accedere. |");
        }
        System.out.println("|---------------------------------------|");
    }
}
