import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Pesquisa {

    public void terminal(TabelaHashPlayer playersTable, TabelaHashRating ratingsTable, TabelaHashTag tagsTable,
            List<Player> playersList) {

        Pesquisa search = new Pesquisa();
        Scanner sc = new Scanner(System.in);
        System.out.print("\n$ ");
        String str = sc.nextLine();
        String[] strSplit = str.split(" ", 2);

        String pesquisa = strSplit[0];
        String key = strSplit[1];

        if (pesquisa.contains("top")) {
            // search.pesquisaTopN (); {
            //
            // }
        } else
            switch (pesquisa) {
                case "player":
                    search.pesquisaNome(key, playersList);
                    break;
                case "user":
                    int userID = Integer.parseInt(key);
                    search.pesquisaUser(userID, ratingsTable, playersTable);
                    break;
                case "tags":
                    search.pesquisaTag(key, tagsTable, playersTable);
                    break;
            }

        sc.close();
        if (pesquisa != "end")
            search.terminal(playersTable, ratingsTable, tagsTable, playersList);
    }

    public void processaGlobalRatings(List<Rating> list, TabelaHashPlayer table) {

        ArrayList<Player> arrayList = new ArrayList<Player>();
        Pesquisa search = new Pesquisa();

        int sofifaID;
        Double ratingAtual;
        Boolean found = false;

        for (Rating rating : list) {
            sofifaID = rating.getSofifaID();
            ratingAtual = rating.getRating();
            found = false;

            for (Player globalRating : arrayList) {
                if (sofifaID == globalRating.getSofifaID()) {
                    globalRating.setGlobalRating(globalRating.getGlobalRating() + ratingAtual);
                    globalRating.setCounter(globalRating.getCounter() + 1);
                    found = true;
                }
            }

            Player player = search.findPlayer(rating.getSofifaID(), table);
            player.setGlobalRating(player.getGlobalRating() + rating.getRating());
            player.setCounter(player.getCounter() + 1);

        }

        for (Player globalRating : arrayList) {
            globalRating.setGlobalRating(globalRating.getGlobalRating() / globalRating.getCounter());
        }
    }

    public Player findPlayer(int key, TabelaHashPlayer table) {

        Player value = null;
        int tam = table.getTam();

        int hashCode = (key % tam);
        LinkedList<Player> linkedList = table.getValue().get(hashCode);
        for (Player player : linkedList) {
            if (player.getSofifaID() == key) {
                value = player;
            }
        }

        return value;
    }

    // Dado um userID como chave, retorna uma lista com todas as avaliações (do tipo
    // "Rating") feitas por aquele usuáruio
    public ArrayList<Rating> findRating(int key, TabelaHashRating table) {

        ArrayList<Rating> value = new ArrayList<Rating>();

        int tam = table.getTam();

        int hashCode = (key % tam);
        LinkedList<Rating> linkedList = table.getValue().get(hashCode);
        for (Rating rating : linkedList) {
            if (rating.getUserID() == key) {
                value.add(rating);
            }
        }

        return value;
    }

    // Dada uma Tag como entrada em forma de String, retorna uma lista do tipo "Tag"
    // com todas as tags referentes à String de entrada
    public List<Integer> findTag(ArrayList<String> key, TabelaHashTag table) {

        System.out.println(key);

        int tam = table.getTam();
        List<Integer> value = new ArrayList<Integer>();
        int hashCode = 0;

        int nroKeys = 5 * key.size();

        for (String parte : key) {

            char[] tagCA = parte.toCharArray();
            for (int i = 0; i < tagCA.length; i++) {
                hashCode += (int) tagCA[i];
            }
            hashCode = (hashCode % tam);

            LinkedList<Tag> linkedList = table.getValue().get(hashCode);
            // int idAtual = linkedList.get(0).getSofifaID();

            for (Tag tag : linkedList) {
                if (tag.getTag().equals(parte)) {
                    // if(tag.getSofifaID() != idAtual)
                    value.add(tag.getSofifaID());
                    System.out.println(tag);
                }
                // idAtual = tag.getSofifaID();
            }
        }
        List<Integer> result = new ArrayList<Integer>();
        result = value.stream()
                .filter(e -> Collections.frequency(value, e) >= nroKeys)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(value.size());
        System.out.println(result.size());
        System.out.println("Nro de keys" + nroKeys / 5);

        System.out.println(key);

        // char[] tagCA = key.toCharArray();
        // for (int i = 0; i < tagCA.length; i++) {
        // hashCode += (int) tagCA[i];
        // }
        // hashCode = (hashCode % tam);

        // LinkedList<Tag> linkedList = table.getValue().get(hashCode);
        // for (Tag tag : linkedList) {
        // if (tag.getTag().equals(key)) {
        // value.add(tag);
        // // System.out.println(tag);
        // }
        // }

        return result;
    }

    public void pesquisaTag(String key, TabelaHashTag tagTable, TabelaHashPlayer playerTable) {

        List<Integer> list = new ArrayList<Integer>();
        Pesquisa search = new Pesquisa();
        ArrayList<String> parts = new ArrayList<String>();

        Scanner sc = new Scanner(key);
        for (String s; (s = sc.findWithinHorizon("(?<=\\').*?(?=\\')", 0)) != null;) {
            // System.out.println(s);
            parts.add(s);
        }

        parts.remove(" ");

        list = search.findTag(parts, tagTable);

        for (Integer id : list) {
            Player player = search.findPlayer(id, playerTable);
            Player globalRating = search.findPlayer(id, playerTable);
            System.out.println("SofifaID = "           + id +
                               ", Nome = "             + player.getName() +
                               ", player positions = " + player.getPositions() +
                               ", global rating = "    + globalRating.getGlobalRating() +
                               ", counter = "          + globalRating.getCounter());
        }

        // list = search.findTag(s, tagTable);
        // Player playerAtual = search.findPlayer(list.get(0).getSofifaID(),
        // playerTable);
        // for (Tag tag : list) {
        // if (search.findPlayer(tag.getSofifaID(), playerTable) != playerAtual) {
        // list.add(tag);
        // }
        // playerAtual = search.findPlayer(tag.getSofifaID(), playerTable);
        // }

        sc.close();

    }

    public void pesquisaUser(int key, TabelaHashRating ratingTable, TabelaHashPlayer playersTable) {

        ArrayList<Rating> list = new ArrayList<Rating>();
        Pesquisa search = new Pesquisa();
        list = search.findRating(key, ratingTable);
        int i=0;
        for (Rating rating : list) {
            Player player = search.findPlayer(rating.getSofifaID(), playersTable);
            System.out.print("\nsofifa_id = " + player.getSofifaID());
            System.out.print(", name = " + player.getName());
            System.out.print(", global_rating = " + player.getGlobalRating());
            System.out.print(", counter = " + player.getCounter());
            System.out.print(", rating = " + rating.getRating());
            i++;
            if (i==20) break;
        }
    }

    public void pesquisaNome(String prefix, List<Player> playersList) {

        List<Player> list = new ArrayList<Player>();

        for (Player player : playersList) {
            if (player.getName().contains(prefix)) {
                list.add(player);
            }
        }
        for (Player player : list) {
            System.out.println(player);
        }
    }

}
