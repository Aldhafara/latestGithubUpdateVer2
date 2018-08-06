package pl.noCompany.main;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class Methods {
    public static String matterCutterDate(String line) {
        char[] charArray = line.toCharArray();
        char[] outputString = new char[100];
        int i = 0;


        for (int j = 0; charArray[j] != 'Z'; j++) {
            if (j >= 41) {

                outputString[i] = charArray[j];
                i++;
                if (charArray[j] == 'T') {
                    outputString[i - 1] = ' ';
                    i++;
                }
            }
        }

        String output = new String(outputString);
        return output;
    }

    private static String matterCutterDate2(String line) {
        char[] charArray = line.toCharArray();
        char[] outputString = new char[100];
        int i = 0;


        for (int j = 0; charArray[j] != 'Z'; j++) {
            if (j >= 41) {

                outputString[i] = charArray[j];
                i++;
                if (charArray[j] == 'T') {
                    outputString[i - 1] = ' ';
                    i++;
                }
            }
        }

        String output = new String(outputString);
        return output;
    }


    private static String matterCutterName(String line) {
        char[] charArray = line.toCharArray();
        char[] outputString = new char[100];
        int i = 0;

        for (int j = 0; charArray[j] != '<'; j++) {


            if (charArray[j] != ' ') {
                outputString[i] = charArray[j];
                i++;
            }
        }

        String output = new String(outputString);
        return output;
    }


    public static List sourceWriter(URL sourceAdres) throws Exception {
        List<String> repositories = new ArrayList<>();

        BufferedReader line = new BufferedReader(
                new InputStreamReader(sourceAdres.openStream()));

        String inputLine;
        int i = 0;
        String[] tab = new String[2];
        while ((inputLine = line.readLine()) != null){
            if (inputLine.contains("<li class=\"col-12"))
                i = 0;

            i++;
            if (i == 5){
                tab[0] = matterCutterName(inputLine);
                System.out.println(tab[0]);
                repositories.add(tab[0]);
            }


            if (inputLine.contains("Updated <relative-time datetime="))
            {
                tab[1] = matterCutterDate2(inputLine);
                System.out.println(tab[1]);
                repositories.add(tab[1]);
                //break;
            }

        }

        repositories.remove(0);
        return repositories;
    }


    public static String[] sourceReader(File file) throws IOException {


        /**
         * Ta metoda ma za zadanie przyjąć plik
         * wyszukać w nim wg klucza nazwę i zapisać do pierwszej komórki tablicy
         * wyszukać wg klucza czas i zapisać do drugiej komórki tablicy
         */


        String[] tab = new String[2];


        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        String inputLine = new String();

        int i = 10;


        while (inputLine != null) {
            inputLine = reader.readLine();


            if ((inputLine == null) && (tab[0] == null) && (tab[1] == null))
                break;


            if (inputLine.contains("<li class=\"col-12") == true)
                i = 0;


            i++;
            if (i == 5)
                tab[0] = inputLine;

            if (inputLine.contains("Updated <relative-time datetime=") == true)
            {
                tab[1] = inputLine;
                break;
            }
        }

        reader.close();

        return tab;
    }

}