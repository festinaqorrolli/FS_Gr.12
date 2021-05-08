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
        }

        System.out.println("Character to remove 1: " + remove);
        System.out.println("Key 1: " + key_1);
        System.out.println("Key 2: " + key_2);

        //largojme(fshime) duplikate nga celsi
        String filtered_key_1 = removeDuplicate(key_1);
        String filtered_key_2 = removeDuplicate(key_1);

        System.out.println("Filtered key 1 : " + filtered_key_1);
        System.out.println("Filtered key 2 : " + filtered_key_2);


        if (process.equals("E")) {
            // E per enkriptim
            encrypt(plot, plainText);
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
    }
}

