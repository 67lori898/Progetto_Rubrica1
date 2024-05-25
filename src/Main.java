import static tools.utility.*;

import java.util.Arrays;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static String generatedPw;

    public static void main(String[] args) {
        String[] operazioni = {"VODAFONE",
                "[1] Inserimento",
                "[2] Visualizzazione",  //chiederemo all'utente se vuole visualizzare anche i contatti nascosti
                "[3] Ricerca",
                "[4] Ricerca Numero telefonico",
                "[5] Modifica contatto",
                "[6] Cancellazione",
                "[7] Inserisci saldo",
                "[8] Telefona",
                "[9]Visualizza chiamate recenti",
                "[10] Nascondi contatto dall'elenco",
                "[11] Ordina", //lista contatti o chiamate recenti
                "[12] Impostazioni utente",
                "[13] Fine"};
        boolean Sitel = true;
        final int nMax = 3;
         int contrattiVenduti = 0;
        int posizione = 0;
        boolean anotherPw = false;
        String [] recentCalls=new String[5];
        Scanner keyboard = new Scanner(System.in);
        String fileName = "";
        Contatto[] gestore = new Contatto[nMax];
        Utente saldoUtente = new Utente();
        Utente myUtente = new Utente();
        System.out.println("=========================================");
        System.out.println("              AUTENTICAZIONE             ");
        System.out.println("=========================================");
        System.out.println("Per accedere al sistema, è necessario effettuare il login.");
        System.out.println("Inserisci l'username: ");
        myUtente.username = keyboard.nextLine();
        System.out.println("Vuoi richiedere una password per la gestione delle attività principali? (si/no)");
        String myAnswer = keyboard.nextLine().toLowerCase();
        //Login nel programma:
        if (myAnswer.equalsIgnoreCase("si")) {
            generatedPw = generaPassword();
            System.out.println("La tua password per la gestione dei contatti nascosti è: " + generatedPw);
            // Qui puoi salvare l'username e la password se necessario
            //Wait(1000);
        } else {
            System.out.println("Richiesta di password annullata.");
            Wait(1500);
        }

        boolean fine = true;
        do {
            switch (menu(operazioni, keyboard)) {
                case 1:

                    if (contrattiVenduti < nMax) {
                        //Chiedo all'utente di accedere
                        System.out.println("Per aggiungere un nuovo contatto devi prima effettuare l'acesso");
                        if (Autentificazione(myUtente, generatedPw, keyboard)) {
                            //firma contratto
                            gestore[contrattiVenduti] = leggiPersona(Sitel, keyboard);
                            contrattiVenduti++;
                        }else{
                            System.out.println("Username o password errati");
                        }
                    } else {
                        System.out.println("Non ci sono più contratti da vendere");
                    }
                    break;
                case 2: {
                    if (contrattiVenduti != 0)
                        visualizza(gestore,contrattiVenduti,myUtente,generatedPw,keyboard);
                    else
                        System.out.println("Non ci sono contratti\n");
                    break;
                }

                case 3: {
                    if (contrattiVenduti != 0) {
                        //Ci sono contratti venduti
                        //lettura, ricerca, visualizzazione
                        if (ricerca(gestore, leggiPersona(false, keyboard), contrattiVenduti)) {
                            System.out.println("Il contatto esiste");

                        } else {
                            System.out.println("Il contatto non esiste");
                        }
                    } else {
                        System.out.println("Non sono ancora presenti contratti venduti");
                    }
                    break;
                }

                case 4:
                    if (contrattiVenduti != 0) {
                        posizione = RicercaIndex(gestore, leggiPersona(false, keyboard), contrattiVenduti);
                        if (posizione != -1) {
                            System.out.println(gestore[posizione].cognome + " " + gestore[posizione].nome + ": " + gestore[posizione].telefono);
                        } else {
                            System.out.println("Contatto inesistente");
                        }
                    } else {
                        System.out.println("Non sono ancora presenti contratti venduti");
                    }
                    break;

                case 5:
                    Contatto numero = new Contatto();
                    int scelta;
                    if (contrattiVenduti != 0) {
                        posizione = RicercaIndex(gestore, leggiPersona(false, keyboard), contrattiVenduti);
                        if (posizione != -1) {
                            System.out.println("Vuoi modificare il numero telefonico (si = 1 | no = 0): ");
                            scelta = keyboard.nextInt();
                            keyboard.nextLine();
                            if (scelta == 1) {
                                System.out.println("Modifica numero telefonico: ");
                                numero.telefono = keyboard.nextLine();
                                gestore[posizione].telefono = numero.telefono;
                            } else {
                                System.out.println("Numero telefonico non modificato");
                            }
                        } else {
                            System.out.println("Contatto inesistente");
                        }
                    } else {
                        System.out.println("Non sono ancora presenti contratti venduti");
                    }
                    break;
                case 6:
                    if (contrattiVenduti != 0) {
                        posizione = RicercaIndex(gestore, leggiPersona(false, keyboard), contrattiVenduti);
                        if (posizione != -1) {
                            contrattiVenduti = cancellazione(gestore, posizione, contrattiVenduti);
                        } else {
                            System.out.println("Contatto inesistente");
                        }

                    } else
                        System.out.println("Non sono ancora presenti contratti venduti");

                    break;
                case 7:
                    //Aggiungere saldo al mio Utente.

                    if(Autentificazione(myUtente, generatedPw, keyboard)) {
                        System.out.println("Inserisci il saldo telefonico: ");
                        saldoUtente.saldoTel= keyboard.nextDouble();
                    }else{
                        System.out.println("Username o password errati");
                    }
                    break;

                case 8:
                    //Effettua una chiamata

                    String callInfo = myCalls(contrattiVenduti, gestore,saldoUtente, keyboard,recentCalls);
                    if (callInfo != null) {
                        System.out.println("Chiamata effettuata:\n" + callInfo);
                    }
                    break;

                case 9:
                    // Visualizza chiamate recenti
                    viewRecentCalls(recentCalls);
                    System.out.println("\nDesideri ordinare le chiamate recenti [si/no]?");
                    String risposta = keyboard.nextLine().toLowerCase();
                    if (risposta.equals("si")) {
                        ordinaChiamateRecenti(recentCalls);
                        System.out.println("Chiamate recenti ordinate per durata.");
                        viewRecentCalls(recentCalls);
                    }
                    break;


                case 10:
                    //nascondi contatto dall'elenco:
                       // se l'utente all'inizio del programma ha deciso di impostare una password

                    if (Autentificazione(myUtente, generatedPw, keyboard)) {
                        System.out.println("Username e Password corretti. Seleziona il contatto da nascondere: ");
                        posizione = RicercaIndex(gestore, leggiPersona(false, keyboard), contrattiVenduti);
                        if (posizione != -1) {
                            gestore[posizione].contactHidden = true;
                            System.out.println("Contatto nascosto correttamente.");
                        } else {
                            System.out.println("Contatto non trovato.");
                        }
                    } else {
                        System.out.println("Username o Password errata.");
                    }
                    break;


                case 11:

                    //Ordinamento tramite bubble-sort
                    bubbleSort(gestore, contrattiVenduti);

                    break;
                /*DEBUG:case 11:
                    try {
                        //FileName è un parametro.
                        scriviFile("fileName.csv", gestore, contrattiVenduti);
                    } catch (IOException ex) {

                        //l'eccezzione che verra sollevate è un'errore di inserimento
                        System.out.println(ex.toString());

                    }

                    break;
                case 12:
                    //carica file;

                    break;*/
                case 12:
                    //Profilo: funzione che permette di gestire le impostazioni dell'account:

                    if (myUtente != null) {
                        myUtente.stampaInfo(myUtente.isAuthenticated);
                        myUtente.modificaUtente(keyboard,myUtente);
                    } else {
                        System.out.println("Impossibile accedere alle impostazioni utente");
                    }
                    break;


                default:
                    fine = false;
                    break;
            }
        } while (fine) ;

    }
  /*  private static boolean Autentificazione(Utente myUtente, String generatedPw, Scanner keyboard) {
        System.out.println("Inserisci l'username: ");
        String myUsername = keyboard.nextLine();
        System.out.println("Inserisci la password: ");
        String myPw = keyboard.nextLine();

        if (myUsername.equals(myUtente.username) && myPw.equals(generatedPw)) {
            System.out.println("Autenticazione avvenuta con successo.");
            return true;
        } else {
            System.out.println("Username o Password errata.");
            return false;
        }
    }*/


   //Metodo che consente di aggiungere un nuovo contatto
    private static Contatto leggiPersona(boolean Sitel, Scanner keyboard) {
        //Sitel è true quando dobbiamo leggere
        String[] tipoC = {"Telefono", "1]abitazione", "2]cellulare", "3]aziendale"};
        //Istanziato un oggetto di tipo contatto:
        Contatto persona = new Contatto();
        System.out.println("\nInserisci il nome del contatto: ");
        persona.nome = keyboard.nextLine();
        System.out.println("\nInserisci il cognome contatto: ");
        persona.cognome = keyboard.nextLine();

        if (Sitel) {
            System.out.println("\nInserisci il numero di telefono: ");
            persona.telefono = keyboard.nextLine();  //Vado a leggere il numero di telefono
            //I valori assegnati all'attributo sono compresi nel range
            switch (menu(tipoC, keyboard)) {
                case 1 -> persona.tipo = tipoContratto.abitazione;
                case 2 -> persona.tipo = tipoContratto.cellulare;
                default -> persona.tipo = tipoContratto.aziendale;

            }
        }

        return persona;
    }
    /*metodo file che mi consente di scrivere su un file: il file lo salveremo con il formato CSV :
         archivio.csv è il nome dek file*/

    private static void scriviFile(String fileName,Contatto [] gestore, int contrattiVenduti)throws IOException
    {
        //Per scrivere all'interno del file avremmo bisogno dell'ogetto di tipo file writer
        FileWriter out= new FileWriter(fileName);  //Gli passo fileName come parametro

        for(int i=0; i<contrattiVenduti;i++){
            //write è un metodo del FileWriter
            out.write(gestore[i].toString()+"+\r\n");
            // \r crea una riga vuota
        }
        out.flush(); //serve per registrare il file su disco.
        out.close(); //chiude il file



    }
    public static String myCalls(int contrattiVenduti, Contatto[] gestore, Utente saldoUtente, Scanner keyboard, String[] recentCalls) {
        if (contrattiVenduti != 0) {
            System.out.println("Inserisci il nome o il numero di telefono della persona a cui vuoi telefonare: ");
            String nomeOTelefono = keyboard.nextLine();

            // Cerca il contatto nella lista
            boolean trovato = false;
            for (int i = 0; i < contrattiVenduti; i++) {
                if (gestore[i].nome.equalsIgnoreCase(nomeOTelefono) || gestore[i].telefono.equals(nomeOTelefono)) {
                    trovato = true;
                    Contatto contatto = gestore[i]; // Contatto trovato

                    // Controlla il saldo prima di effettuare la chiamata
                    double costoChiamata = 0.50; // Costo per ogni minuto di chiamata
                    if (saldoUtente.saldoTel >= costoChiamata) {
                        System.out.println("Stai telefonando a " + contatto.nome + " " + contatto.cognome + " al numero " + contatto.telefono);
                        int durata = 0; // Durata della chiamata in secondi

                        // Simula la telefonata
                        while (saldoUtente.saldoTel >= costoChiamata) {
                            saldoUtente.saldoTel -= costoChiamata; // Decrementa il saldo ogni minuto di chiamata
                            durata += 60; // Incrementa la durata della chiamata di 60 secondi
                            System.out.println("Durata della chiamata: " + durata + " secondi");
                            System.out.println("Saldo rimanente: " + saldoUtente.saldoTel);

                            // Controllo se c'è abbastanza saldo per un altro minuto di chiamata
                            if (saldoUtente.saldoTel < costoChiamata) {
                                System.out.println("Saldo insufficiente per continuare la chiamata.");
                                break; // Esci dal ciclo se il saldo non è sufficiente per un altro minuto di chiamata
                            }

                            // Qui puoi aggiungere ulteriori azioni per la telefonata
                        }

                        // Formatta il risultato della chiamata
                        String callInfo = String.format("Nome: %s, Telefono: %s, Durata: %d secondi", contatto.nome, contatto.telefono, durata);
                        // Aggiungi la chiamata alla lista delle chiamate recenti
                        addToRecentCalls(callInfo, recentCalls);
                        return callInfo;
                    } else {
                        System.out.println("Saldo insufficiente per effettuare la chiamata.");
                    }
                    break; // Esci dal ciclo una volta trovato il contatto
                }
            }

            if (!trovato) {
                System.out.println("Nessun contatto trovato con questo nome o numero di telefono.");
            }
        } else {
            System.out.println("Non sono ancora presenti contratti venduti.");
        }
        return null;
    }

    private static void addToRecentCalls(String callInfo, String[] recentCalls) {
        // Sposta tutte le chiamate recenti verso l'alto per fare spazio alla nuova chiamata
        for (int i = recentCalls.length - 1; i > 0; i--) {
            recentCalls[i] = recentCalls[i - 1];
        }
        // Aggiungi la nuova chiamata alla prima posizione della lista delle chiamate recenti
        recentCalls[0] = callInfo;
    }
    /**
     * @param recentCalls: is for the array string of recentCalls
     *
     */

    private static void viewRecentCalls(String[] recentCalls) {
        System.out.println("Chiamate recenti:");
        for (String call : recentCalls) {
            if (call != null) {
                System.out.println(call);
            }
        }
    }
    public static void bubbleSort(Contatto [] gestore, int contrattiVenduti) {
        int n =contrattiVenduti;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                // Confronta le stringhe utilizzando il metodo compareTo
                if (gestore[j+1].cognome.compareTo(gestore[j].cognome) > 0) {
                    // Scambia le stringhe se sono in ordine sbagliato
                    Contatto temp = gestore[j];
                    gestore[j] = gestore[j + 1];
                    gestore[j + 1] = temp;
                }
                else if(gestore[j+1].cognome.compareTo(gestore[j].cognome)==0){

                    bubbleSortName(gestore);


                }
            }
        }
    }

    private static void bubbleSortName(Contatto [] gestore) {

        int n = gestore.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                // Confronta le stringhe utilizzando il metodo compareTo
                if (gestore[j + 1].cognome.compareTo(gestore[j].cognome) > 0) {
                    // Scambia le stringhe se sono in ordine sbagliato
                    Contatto temp = gestore[j];
                    gestore[j] = gestore[j + 1];
                    gestore[j + 1] = temp;
                }

            }
        }
    }
    //Ordina le chiamate recenti in base alla loro durata
    public static void ordinaChiamateRecenti(String[] recentCalls) {
        for (int i = 0; i < recentCalls.length - 1; i++) {
            for (int j = 0; j < recentCalls.length - 1 - i; j++) {
                if (recentCalls[j] != null && recentCalls[j + 1] != null) {
                    int durata1 = Integer.parseInt(recentCalls[j].split(",")[2].split(" ")[2]);
                    int durata2 = Integer.parseInt(recentCalls[j + 1].split(",")[2].split(" ")[2]);
                    if (durata1 < durata2) {
                        String temp = recentCalls[j];
                        recentCalls[j] = recentCalls[j + 1];
                        recentCalls[j + 1] = temp;
                    }
                }
            }
        }
    }

     /*DEBUG: metodo che permette di ordinare la lista di contatti usando il selection sort
     *
     * private static void selectionSort(Contatto [] gestore){
      }
     *
     *
     * */

    private static boolean ricerca(Contatto[] gestore, Contatto contatto, int contrattiVenduti) {
        //Controllo se il nome e il cognome del contatto e ugale al nome e cogome del gestore
        boolean ricerca = false;

        for (int i = 0; i < contrattiVenduti; i++) {
            if (contatto.nome.equals(gestore[i].nome) && contatto.cognome.equals(gestore[i].cognome)) {
                ricerca = true;
            }
        }
        return ricerca;
    }

    private static int RicercaIndex(Contatto[] gestore, Contatto ricerca, int contrattiVenduti) {

        int indice = -1;

        for (int i = 0; i < contrattiVenduti; i++) {
            if (ricerca.nome.equals(gestore[i].nome) && ricerca.cognome.equals(gestore[i].cognome)) {
                indice = i;
                break;
            }
        }
        return indice;

    }

    private static void visualizza(Contatto[] gestore, int contrattiVenduti, Utente myUtente, String generatedPw, Scanner keyboard) {
        System.out.println("Vuoi visualizzare anche i contatti nascosti? (sì/no)");
        String risposta = keyboard.nextLine().toLowerCase();

        boolean mostraNascosti = false;

        if (risposta.equalsIgnoreCase("sì")) {
            // Chiede le credenziali all'utente
            if (Autentificazione(myUtente, generatedPw, keyboard)) {
                mostraNascosti = true;
            } else {
                System.out.println("Username o Password errata. Non è possibile visualizzare i contatti nascosti.");
              return; //ritorna al menu principale
            }
        }

        for (int i = 0; i < contrattiVenduti; i++) {
            if (!gestore[i].contactHidden || mostraNascosti) {
                System.out.println(gestore[i].stampa());
            }
        }
    }
    private static int contaContattiAbitazione(Contatto[] gestore, int contrattiVenduti) {
        int contAbitazione = 0;
        for (int i = 0; i < contrattiVenduti; i++) {
            if (gestore[i].tipo == tipoContratto.abitazione) {
                contAbitazione++;
            }


        }
        return contAbitazione;
    }

    public static void ricerca(Contatto[] gestore) {
        System.out.println("Inserisci il nome del contatto");
    }

    public static void salvaBackup(Contatto[] gestore, int contrattiVenduti, Scanner keyboard) {
        System.out.println("Inserisci il nome del file di backup: ");
        String filename = keyboard.nextLine();
        try (FileWriter writer = new FileWriter(filename)) {
            for (int i = 0; i < contrattiVenduti; i++) {
                writer.write(gestore[i].toString() + "\n");
            }
            System.out.println("Backup salvato correttamente.");
        } catch (IOException e) {
            System.out.println("Errore durante il salvataggio del backup: " + e.getMessage());
        }
    }


    public static int cancellazione(Contatto[] gestore, int posizione, int contrattiVenduti) {
        if (posizione != gestore.length - 1)
            for (int i = posizione; i < contrattiVenduti - 1; i++)
                gestore[i] = gestore[i+1];
        return --contrattiVenduti;
    }

    private static void SaldoTelefonico(Contatto[] gestore, Scanner keyboard, int posizione){
        int saldoTele=0;
        System.out.println("Inserisci saldo telefonico: ");
        saldoTele = Integer.parseInt(keyboard.nextLine());
    }
    public static boolean Autentificazione(Utente myUtente, String generatedPw, Scanner keyboard) {
        System.out.println("Inserisci l'username: ");
        String username = keyboard.nextLine();
        System.out.println("Inserisci la password: ");
        String password = keyboard.nextLine();

        if (myUtente.username.equals(username) && generatedPw.equals(password)) {
            myUtente.isAuthenticated = true;
            return true;
        } else {
            myUtente.isAuthenticated = false;
            return false;
        }
    }


    public static String generaPassword() {
        // Definisci i caratteri che potrebbero essere inclusi nella password
        String caratteri = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+";

        // Imposta la lunghezza desiderata della password
        int lunghezzaPassword = 10;

        // Crea un oggetto Random
        Random rand = new Random();

        // StringBuilder per costruire la password
        StringBuilder password = new StringBuilder();

        // Genera la password casuale carattere per carattere
        for (int i = 0; i < lunghezzaPassword; i++) {
            // Ottieni un carattere casuale dalla stringa di caratteri
            char carattereCasuale = caratteri.charAt(rand.nextInt(caratteri.length()));

            // Aggiungi il carattere casuale alla password
            password.append(carattereCasuale);
        }

        // Restituisci la password generata
        return password.toString();
    }
}
