package com.example.tarningsspel;

import java.util.Scanner;

public class Tarningsspel {
    public static void main(String[] args){

        Scanner scan = new Scanner(System.in);
        boolean avslut = false;
        int[] highScores = {0, 0, 0};

        do{

            System.out.print("Antal spelare: ");
            int antalSpelare = scan.nextInt();
            scan.nextLine();
            String[] namn = bestamNamn(antalSpelare);
            if (antalSpelare == 1){
                antalSpelare++;
            }

            System.out.print("Antal tärningar: ");
            int antalTarningar = scan.nextInt();

            System.out.print("Antal kast: ");
            int antalKast = scan.nextInt();
            scan.nextLine();
            System.out.println();

            int[] resultat = new int[antalSpelare];
            int antalSidor = 6;

            for (int i = 0; i<antalKast; i++){

                    int resultatForOmgangen = 0;

                    System.out.println("Resultat för omgång " + (i+1) + ": ");
                    for (int s = 0; s < antalSpelare; s++){
                        int sammanlagt = 0;
                        System.out.print(namn[s] + " fick\t");
                        for (int t = 0; t < antalTarningar; t++){
                            resultatForOmgangen = 1 + (int)(Math.random()*antalSidor);
                            System.out.print(resultatForOmgangen + "\t");
                            sammanlagt += resultatForOmgangen;
                        }
                        System.out.println();
                        System.out.println("Sammanlagt blev det " + sammanlagt + (" poäng."));
                        resultat[s] += sammanlagt;
                        System.out.println("Totalt har " + namn[s] + " " + resultat[s] + " poäng.\n");
                    }

                    if (i == antalKast-1) break;

                    System.out.print("Tryck k för nästa kast: ");
                    String redo = scan.nextLine();

                    while (!redo.equalsIgnoreCase("k")){
                        System.out.println("Felaktig inmatning");
                        System.out.print("Mata in k för nästa kast:");
                        redo = scan.nextLine();
                        System.out.println();
                    }
            }

            int vinnarNummer = visaResultat(resultat);

            if(vinnarNummer == -1){
                System.out.println("Det blev oavgjort!");
            }
            else {
                System.out.println(namn[vinnarNummer] + " vann!");
            }

            highScores = visaHighScore(resultat, highScores);

            System.out.print("Vill du spela igen? (J/N) ");
            String fortsatta = scan.nextLine();
            while(!fortsatta.equalsIgnoreCase("J")){
                if (fortsatta.equalsIgnoreCase("N")){
                    avslut = true;
                    break;
                }
                else{
                    System.out.println("Ogiltigt val. Försök igen.");
                    System.out.print("Vill du spela igen? (J/N) ");
                    fortsatta = scan.nextLine();
                }
            }

        }while (avslut==false);



    }

    static String[] bestamNamn(int antalSpelare){

        Scanner scan = new Scanner(System.in);
        boolean datorDeltar = true;

        if (antalSpelare>1) {
            System.out.print("Ska datorn vara en av spelarna? (J/N) ");
            String spelaMotDatorn = scan.nextLine();
            if(spelaMotDatorn.equalsIgnoreCase("N")){
                datorDeltar = false;
            }
        }

        else if (antalSpelare == 1){
            System.out.println("Du får spela mot datorn.");
            antalSpelare++;
        }

        String[] namn = new String[antalSpelare];
        int antalNamn = antalSpelare;

        if (datorDeltar) {
            namn[antalSpelare - 1] = "Datorn";
            antalNamn--;
        }

        for (int i = 0; i<antalNamn; i++){
            System.out.print("Namn på spelare " + (i+1) + ": ");
            namn[i] = scan.nextLine();
        }
        return namn;
    }


    static int visaResultat(int[] resultat){
        int maxVarde = 0;
        int vinnandeSpelare = 0;

        for (int i = 0; i < resultat.length; i++){
            int varde = resultat[i];

           if(varde==maxVarde){
                vinnandeSpelare = -1;
            }
           if(varde>maxVarde){
               maxVarde = varde;
               vinnandeSpelare = i;
           }
        }

        return vinnandeSpelare;
    }
    static int[] visaHighScore(int[] resultat, int[] highScore){

        int[] nyHighScore = {highScore[0], highScore[1], highScore[2]};

        for(int i = 0; i<resultat.length; i++){
            if (resultat[i]>nyHighScore[2]){
                if (resultat[i]>nyHighScore[1]){
                    if (resultat[i]>nyHighScore[0]){
                        nyHighScore[2] = nyHighScore[1];
                        nyHighScore[1] = nyHighScore[0];
                        nyHighScore[0] = resultat[i];
                    }
                    else{
                        nyHighScore[2] = nyHighScore[1];
                        nyHighScore[1] = resultat[i];
                    }
                }
                else{
                    nyHighScore[2] = resultat[i];
                }
            }
        }
        if (nyHighScore[0]>highScore[0]){
            System.out.println("Det är nytt rekord! \n");
        }
        System.out.println("Highscorelista: 1. " + nyHighScore[0] + "\t2. " + nyHighScore[1] + "\t3. " + nyHighScore[2]);
        return nyHighScore;
    }
}
