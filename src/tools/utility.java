package tools;

import java.util.Scanner;

public class utility {
    public static void ClrScr() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void Wait(int x)
    {
        try{
            Thread.sleep(1000*x);
        }catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }
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
    public static int menu(String[] opzioni, Scanner keyboard) {
        int scelta;

        do {
            ClrScr();
            System.out.println("=== " + opzioni[0] + " ===");
            for (int i = 1; i < opzioni.length; i++) {
                System.out.println(opzioni[i]);
            }

            String input = keyboard.nextLine();

            if (input.isEmpty()) {
                System.out.println("Input vuoto. Riprova.");
                Wait(3);
                scelta = -1; // Impostare una scelta non valida per far ripetere il loop
            } else {
                try {
                    scelta = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Input non valido. Inserisci un numero valido.");
                    Wait(1);
                    scelta = -1; // Impostare una scelta non valida per far ripetere il loop
                }
            }

        } while (scelta < 1 || scelta > opzioni.length - 1);

        return scelta;
    }

}