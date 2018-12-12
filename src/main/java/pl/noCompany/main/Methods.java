package pl.noCompany.main;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class Methods {
    private List<Repository> repositories2 = new ArrayList<>();

    private void writeRepositoriesInConsole() {
        for (Repository repo :repositories2) {
            System.out.println( repositories2.indexOf(repo)+1 + ": \"" + repo.getName()+ "\"");
            System.out.println( "   zaktualizowano: " +  repo.getTime() + " UTC");
            System.out.println("___________________________________________");
        }
   }

    public static String[] informations(String login) throws Exception {



        String[] repos = new String[3];
        repos[0] = "Brak danych";
        repos[1] = "Brak danych";
        repos[2] = "Brak danych";
        URL url = new URL("https://github.com/" + login + "?tab=repositories");

        /**
         * PONIZSZY BLOK SPRAWDZA POLACZENIE Z ADRESEM URL
         */
        HttpURLConnection huc = (HttpURLConnection) url.openConnection();
        huc.setRequestMethod("GET");
        huc.connect();
        int responseCode = huc.getResponseCode();

        if (responseCode == 404) {

            repos[0] = ("Uzytkownik " + login + " nie istnieje.");
            return repos;
        }else
        if ((responseCode >= 400) && (responseCode < 600)) {
            repos[0] = "ERROR " + responseCode;
            return repos;
        }


        Methods repositoryName = new Methods();

        List repoStringList = repositoryName.sourceWriter(url);


        System.out.println("___________________________________________");
        repositoryName.writeRepositoriesInConsole();



        if (repoStringList.size()<2)
        {
            repos[0] = "Uzytkownik " + login + " na puste repozytorium.";
            return repos;

        }

        repos[1] = (String) repoStringList.get(0);
        repos[2] = (String) repoStringList.get(1);

        return repos;
    }

    private static String matterCutterDate(String line) {
        line = line.split("\"")[1];
        line = line.replace('T',' ');
        line = line.substring(0,line.length()-1);

        return line;
    }

    private static String matterCutterName(String line) {

        if (!line.contains("<head>")){

            line = line.split("/")[2];
            line = line.split("\"")[0];

        }

        return line;
    }

    private List sourceWriter(URL sourceAdres) throws Exception {
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