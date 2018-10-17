package pl.noCompany.main;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

class Methods {
    private List<Repository> repositories2 = new ArrayList<>();

   void writeRepositoriesInConsole() {
        for (Repository repo :repositories2) {
            System.out.println( repositories2.indexOf(repo)+1 + ": \"" + repo.getName()+ "\"");
            System.out.println( "   zaktualizowano: " +  repo.getTime() + " UTC");
            System.out.println("___________________________________________");
        }
   }


    private static String matterCutterDate(String line) {
        line = line.split("\"")[1];
        line = line.replace('T',' ');
        line = line.substring(0,line.length()-1);

        return line;
    }

    private static String matterCutterName(String line) {
        char[] charArray = line.toCharArray();
        char[] outputString = new char[40];
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

    List sourceWriter(URL sourceAdres) throws Exception {
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

                repositories.add(tab[0]);
            }


            if (inputLine.contains("Updated <relative-time datetime="))
            {
                tab[1] = matterCutterDate(inputLine);
                this.repositories2.add(new Repository(tab[0],tab[1]));
                repositories.add(tab[1]);
            }

        }

        repositories.remove(0);
        return repositories;



    }


}