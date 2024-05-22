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
                    System.out.println("Input non valido. Inserisci un numero.");
                    Wait(3);
                    scelta = -1; // Impostare una scelta non valida per far ripetere il loop
                }
            }

        } while (scelta < 1 || scelta > opzioni.length - 1);

        return scelta;
    }

}