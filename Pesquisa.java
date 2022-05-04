import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Pesquisa {

    public void terminal(TabelaHashPlayer playersTable, TabelaHashRating ratingsTable, TabelaHashTag tagsTable,
            List<Player> playersList, Scanner sc) {

        boolean endFlag = false;
        Pesquisa search = new Pesquisa();
        System.out.print("\n$ ");
        String str = sc.nextLine();
        if (str.equals("end"))
            endFlag = true;
        if (!endFlag) {
            String[] strSplit = str.split(" ", 2);
            String pesquisa = strSplit[0];
            String key = strSplit[1];

            if (pesquisa.contains("top")) {
                // retirar N da String pesquisa
                String numberOnly = pesquisa.replaceAll("[^0-9]", "");
                int n = Integer.parseInt(numberOnly);
                search.pesquisaTopPos(n, key, playersList);
            } else {
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
                    case "end":
                        break;
                    default:
                        System.out.println("Pesquisa inválida");
                }
            }
            search.terminal(playersTable, ratingsTable, tagsTable, playersList, sc);
        }

    }

    public void processaGlobalRatings(List<Rating> list, TabelaHashPlayer table) {

        Pesquisa search = new Pesquisa();

        // int sofifaID;
        // Double ratingAtual;

        for (Rating rating : list) {
            // sofifaID = rating.getSofifaID();
            // ratingAtual = rating.getRating();

            // for (Player player : arrayList) {
            // if (sofifaID == player.getSofifaID()) {
            // player.setGlobalRating(player.getGlobalRating() + ratingAtual);
            // player.setCounter(player.getCounter() + 1);
            // }
            // }

            Player player = search.findPlayer(rating.getSofifaID(), table);
            player.setGlobalRating(player.getGlobalRating() + rating.getRating());
            player.setCounter(player.getCounter() + 1);

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
                }
                // idAtual = tag.getSofifaID();
            }
        }
        List<Integer> result = new ArrayList<Integer>();
        result = value.stream()
                .filter(e -> Collections.frequency(value, e) >= nroKeys)
                .distinct()
                .collect(Collectors.toList());
        // System.out.println(value.size());
        // System.out.println(result.size());
        // System.out.println("Nro de keys" + nroKeys / 5);

        // System.out.println(key);

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
        parts.remove(" ");
        parts.remove(" ");

        list = search.findTag(parts, tagTable);

        for (Integer id : list) {
            Player player = search.findPlayer(id, playerTable);
            System.out.printf("sofifa_id = %6d, name = %40s, positions = %12s, rating = %1.6f, counter = %6d \n",
                    player.getSofifaID(), player.getName(), player.getPositions(), player.getGlobalRating(),
                    player.getCounter());
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
        int i = 0;
        for (Rating rating : list) {
            Player player = search.findPlayer(rating.getSofifaID(), playersTable);
            System.out.printf("sofifa_id = %6d, name = %40s, rating = %1.6f, counter = %6d, rating = %1.1f \n",
                    player.getSofifaID(), player.getName(), player.getGlobalRating(), player.getCounter(), rating.getRating());
            i++;
            if (i == 20)
                break;
        }
        System.out.println();
    }

    public void pesquisaNome(String prefix, List<Player> playersList) {

        List<Player> list = new ArrayList<Player>();
        System.out.println();

        for (Player player : playersList) {
            if (player.getName().contains(prefix)) {
                list.add(player);
            }
        }
        for (Player player : list) {
            System.out.printf("sofifa_id = %6d, name = %40s, positions = %12s, rating = %1.6f, counter = %6d \n",
                    player.getSofifaID(), player.getName(), player.getPositions(), player.getGlobalRating(),
                    player.getCounter());
        }
    }

    public void pesquisaTopPos(int n, String pos, List<Player> playersList) {

        ArrayList<Player> list = new ArrayList<Player>();
        System.out.println();
        Scanner sc = new Scanner(pos);
        for (String s; (s = sc.findWithinHorizon("(?<=\\').*?(?=\\')", 0)) != null;) {
            pos = s;
        }
        sc.close();

        for (Player player : playersList) {
            if (player.getPositions().contains(pos)) {
                if (player.getCounter() >= 1000) {
                    list.add(player);
                }
            }
        }
        // aplicar algum sort na lista pela global rating
        int bigger;
        Player temp;
        for (int i = 0; i < list.size(); i++) {
            bigger = i;
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(j).getGlobalRating() > list.get(bigger).getGlobalRating()) // find the index of the minimum
                                                                                        // element
                {
                    bigger = j;
                }
            }

            temp = list.get(bigger); // swap the current element with the minimum element
            list.set(bigger, list.get(i));
            list.set(i, temp);
        }
        //
        int i = 0;
        for (Player player : list) {
            // System.out.printf(player + "\n", player.getGlobalRating());
            System.out.printf("sofifa_id = %6d, name = %40s, positions = %12s, rating = %1.6f, counter = %6d \n",
                    player.getSofifaID(), player.getName(), player.getPositions(), player.getGlobalRating(),
                    player.getCounter());
            i++;
            if (i == n)
                break;
        }
    }

}