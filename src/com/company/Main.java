package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String key_1;
        String key_2;
        String plainText = "";
        String encryptedText = "";
        char[][] plot = new char[3][25];
        char remove = 'Q';

        System.out.println("Algorithm  Four Square Cipher");

        Scanner in = new Scanner(System.in);
        System.out.print("Input Key 1: ");
        key_1 = in.next();
        System.out.print("Input Key 2: ");
        key_2 = in.next();

        String process;
        while (true) {
            System.out.print("Type 'E' to encrypt or 'D' to decrypt: ");
            process = in.next();

            if (process.equals("E")) {
                System.out.print("Input Plain Text: ");
                plainText = in.next();
                break;
            }
            if (process.equals("D")) {
                System.out.print("Input  Text: ");
                encryptedText = in.next();
                break;
            }
        }

        System.out.println("Character to remove 1: " + remove);
        System.out.println("Key 1: " + key_1);
        System.out.println("Key 2: " + key_2);

        //largojme(fshime) duplikate nga celsi
        String filtered_key_1 = removeDuplicate(key_1);
        String filtered_key_2 = removeDuplicate(key_2);

        System.out.println("Filtered key 1 : " + filtered_key_1);
        System.out.println("Filtered key 2 : " + filtered_key_2);

        plotAlphabets(plot[0], remove);
        plotKey(plot[1], filtered_key_1, remove);
        plotKey(plot[2], filtered_key_2, remove);

        // kontrollo mbushjen
        System.out.println("Q1/Q4: " + Arrays.toString(plot[0]));
        System.out.println("Q2: " + Arrays.toString(plot[1]));
        System.out.println("Q3: " + Arrays.toString(plot[2]));


        if (process.equals("E")) {
            // E per enkriptim
            encrypt(plot, plainText);
        }

        if (process.equals("D")) {
            //dekriptimi
            decrypt(plot, encryptedText);
        }
    }

        private static void encrypt(char[][] plot, String plainText) {
            if (plainText.length() %2 !=0)
                plainText = plainText + "X";

            char[] pairs = new char[2];
            String encryptedText = "";
            String[] pEncryptText = new String[plainText.length() / 2];

            int cursor = 0;
            for (int i = 0; i < pEncryptText.length; i++) {
                pEncryptText[i] = "" + plainText.charAt(cursor) + plainText.charAt(cursor + 1);
                // hapi eshte nga 2
                cursor = cursor + 2;
            }

            // Tregohet çifti
            System.out.println("Pairs: " + Arrays.toString(pEncryptText));


            for (int i = 0; i < pEncryptText.length; i++) {
                int column_a = 0;
                int row_a = 0;
                int column_b = 0;
                int row_b = 0;

                // merri kete qift
                pairs[0] = pEncryptText[i].charAt(0);
                pairs[1] = pEncryptText[i].charAt(1);

                // gjeje qiftin e qelsit ne alfabet
                for (int j = 0; j < plot[0].length; j++) {
                    // gjeje qifin e pare nga alfabeti
                    if(pairs[0] == plot[0][j]) {
                        // vargu eshte  5 nga 25 dhe ne duhet me gjete rreshtin, e konvertojme ne 5x5 (pjestojm 5)
                        row_a = (j / 5) * 5;
                        // gjejem kolonen per me pa mete, bejme modulo(mod) nga 5 (j % 5)
                        column_a = j % 5;
                    }

                    // gjeje qifin e dyte nga alfabeti
                    if(pairs[1] == plot[0][j]) {
                        row_b = (j / 5) * 5;
                        column_b = j % 5;
                    }
                }

                // e kthejme(permbysim) kolonen dhe rreshtin ne dy qelsa vargu
                encryptedText += plot[1][row_a + column_b]; //gjejme kombinimin e pare(rresht, kolon)
                encryptedText += plot[2][row_b + column_a];  //gjejme kombinimin e dyte(rreshti, kolon)
            }

            // Teksti i enkriptuar
            System.out.println("Encrypted Text: " + encryptedText);
        }

        //mbushja =  mbushja e array ne lenth 25

        public static void plotAlphabets(char[] plot, char remove) {
            int cursor = 0;
            for (int i =0; i<plot.length;i++) {
                if ((char)('A' + i) != remove) {
                    plot[i] = (char)  ('A'  + cursor);
                }
                else {
                    cursor++;
                    plot[i] = (char) ('A' + cursor);
                }
                cursor++;
            }
        }

        //largimi ose fshirja e duplikateve dhe filtrimi i stringut
        public  static String removeDuplicate(String string) {

            char[] characters = string.toCharArray();
            String filterd = "";
            // iiterojme ne secilin karakter te arry
            for (int i=0;i<characters.length;i++) {
                boolean isReapeated = false;
                for (int j=0; j < i; j++) {
                    // krahasojme nje karakter me te gjtha te tjerat
                    if (characters[i] ==characters[j]) {
                        isReapeated = true;
                        break;
                    }
                }
                // nese karakteri nuk perseritet shto ne dalje
                if (!isReapeated) {
                    filterd = filterd + characters[i];
                }
            }
            //kthe stringun e filtruar
            return filterd;
        }

    private static void plotKey(char[] plot, String key, char remove) {
        int cursor = 0;
        char[] ckey = key.toCharArray(); // konvertojme  String ne varg char

        // mbushim celesin
        for (int i = 0; i < ckey.length; i++) {
            plot[i] = ckey[i];
        }

        for (int i = ckey.length; i < plot.length; i++) {

            if((char) ('A' + cursor) == remove) {
                // nëse shkronja aktuale është ajo që hiqet, atëherë anashkaloni
                cursor++;
            }

            int checks = 2; // numri i kontrolleve
            for (int j = 0; j < checks; j++) {
                for (int k = 0; k < ckey.length; k++) {
                    // iterimi me celes dhe krahasimi i secilit karater aktual
                    if(ckey[k] == (char) ('A' + cursor)) {
                        cursor++;
                        break;
                    }
                }
            }

            if((char) ('A' + cursor) == remove) { // kontrolla prap ose rikontroll
                // nëse shkronja aktuale është ajo që hiqet, atëherë anashkaloni
                cursor++;
            }

            plot[i] = (char) ('A' + cursor);
            cursor++;

        }
    }
    private static void decrypt(char[][] plot, String encryptedText) {

        if(encryptedText.length() %2 !=0)
            encryptedText = encryptedText + "X";

        char[] pairs = new char[2];
        String plainText = "";
        String[] pEncryptText = new String[encryptedText.length() / 2 ];

        int cursor = 0;
        for (int i=0;i< pEncryptText.length;i++) {
            pEncryptText[i] = ""+ encryptedText.charAt(cursor) + encryptedText.charAt(cursor + 1);
            cursor = cursor + 2;//hapi me nga 2
        }

        System.out.println("Pairs : " + Arrays.toString(pEncryptText));

        for (int i=0;i<pEncryptText.length;i++) {
            int column_a = 0;
            int row_a = 0;
            int column_b = 0;
            int row_b = 0;

            // krijo ciftin
            pairs[0] = pEncryptText[i].charAt(0);
            pairs[1] = pEncryptText[i].charAt(1);

            //gjeje ciftin ne cels ne NormalAlfabet
            for (int j =0;j< plot[0].length;j++) {
                //gjeje ciftin e pare nga alfabeti
                if (pairs[0] == plot[1][j]) {
                    //mbushja eshte 5 nga 25 dhe na nevojitet me gjete rreshtin, ne e konvertojme ate 5*5(pjestu 5)
                    row_a = (j/5) * 5;
                    //me gjet kolonen na nevojitet mbetja, ne e modelojme me 5 ose (j % 5)
                    column_a = j % 5;
                }

                //gjeje ciftin e 2 nga alfabeti
                if (pairs[1] == plot[2][j]) {
                    row_b = (j / 5) * 5;
                    column_b = j % 5;
                }
            }
            plainText += plot[0][row_a + column_b];
            plainText += plot[0][row_b + column_a];
        }
        //Teksti i dekriptuar
        System.out.println("Decrypted text : " + plainText);
    }
}

