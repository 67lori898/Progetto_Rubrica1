import java.util.Scanner;
import static tools.utility.*;


public class Utente {
    Scanner keyboard = new Scanner(System.in);
    public String username;
    public static String password;
    //public static boolean autentified=Autentificazione;
    public static String[] menuUtente = {
            "MODIFICA PROFILO",
            "[1]-Visualizza credenziali",
            "[2]-Inserisci nuovo username",
            "[3]-Inserisci nuova password"
    };

    public Utente() {
        this.username = username;
        this.password = password;
    }

    public  void modificaUtente(Scanner keyboard, Utente myUtente) {
        System.out.println("\nVuoi modificare le impostazioni del profilo utente? (si/no)");
        String myAnswer = keyboard.nextLine();
        //Se la risposta è si e se l'autentificazione è avvenuta con successo

        if (myAnswer.equalsIgnoreCase("si")) {
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
                   // Wait(1200);
                    System.out.println("Password modificata correttamente");
                    break;
                default:
                    // Esci dalla modifica del profilo
                    break;
            }
        } else {
            System.out.println("Modifica delle impostazioni del profilo utente annullata.");
        }
    }
    //Querto metodo ritorna la password censurata
    public static String censorPassword(String password) {
        if (password == null) {
            return "N/A"; //Se la passworde non è stata ancora inizializzata
        }
        // Crea una nuova StringBuilder per costruire la password censurata
        StringBuilder censored = new StringBuilder();

        // Itera su ogni carattere della password
        for (int i = 0; i < password.length(); i++) {
            // Aggiungi un asterisco (*) per ogni carattere della password originale
            censored.append('*');
        }

        // Converti la StringBuilder in una String e restituiscila
        return censored.toString();
    }

    // METODI ACCESSORI: questo metodo permette di stampare il profilo dell'utente
    void stampaInfo() {
        System.out.println("|---------------------------------------|");
        System.out.println("|               IMPOSTAZIONI PROFILO                |");
        System.out.println("|---------------------------------------|");
        System.out.println("| Username          : " + String.format("%-20s", this.username) + "|");
        System.out.println("| Password           : " + String.format("%-20s", censorPassword(password)) + "|");
       // if (Autentificazione=true) {
            System.out.println("| SUPER-UTENTE           :ON");//se è off aggiungo che per diventare super utente devi prima accedere
            System.out.println("|---------------------------------------|");
        }

    }



