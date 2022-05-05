import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Pesquisa {

    // terminal que recebe uma entrada do usuário, decide qual pesquisa deve ser feita, e chama a pesquisa
    public void terminal(TabelaHashPlayer playersTable, TabelaHashRating ratingsTable, TabelaHashTag tagsTable,
            List<Player> playersList, Scanner sc) {

        boolean endFlag = false; // flag usada para decidir se o terminal deve ser chamado recursivamente ou não
        Pesquisa search = new Pesquisa();
        System.out.print("\n$ ");
        String str = sc.nextLine(); // lê a entrada do usuária, que vai indicar o quê deve ser pesquisado a seguir
        if (str.equals("end"))
            endFlag = true; // sinaliza que o terminal não deve ser chamado recursivamente, terminando assim o programa
        if (!endFlag) {
            String[] strSplit = str.split(" ", 2); // separa a primeira palavra digitada pelo usuário
            String pesquisa = strSplit[0]; // determina qual tipo de pesquisa deve ser feita
            String key = strSplit[1]; // informação relativa à pesquisa, que deve ser passada como parâmetro

            if (pesquisa.contains("top")) {
                // separa o número de jogadores a serem pesquisados para ser passado como parâmetro
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
            // chama recursivamente o terminal, a não ser que o usuário tenha digitado "end"
            search.terminal(playersTable, ratingsTable, tagsTable, playersList, sc);
        }
    }

    // calcula a média de avaliações de todos os jogadores e conta quantas avaliações foram feitas sobre cada jogador
    public void processaGlobalRatings(List<Rating> list, TabelaHashPlayer table) {

        Pesquisa search = new Pesquisa();
        // encontra o jogador avaliado na hash table de jogadores e faz a soma da avaliação e do contador referente às Ratings
        for (Rating rating : list) {
            Player player = search.findPlayer(rating.getSofifaID(), table);
            player.setGlobalRating(player.getGlobalRating() + rating.getRating());
            player.setCounter(player.getCounter() + 1);
        }
    }

    // Dado um sofifaID como chave, retorna o "Player" referente àquele sofifaID
    public Player findPlayer(int key, TabelaHashPlayer table) {

        Player value = null;
        int tam = table.getTam();
        int hashCode = (key % tam); // calcula o hash code referente ao jogador para achá-lo na hash table
        LinkedList<Player> linkedList = table.getValue().get(hashCode); // encontra a LinkedList que contém o jogador
        for (Player player : linkedList) { // encontra o jogador e o guarda na variável a ser retornada
            if (player.getSofifaID() == key) {
                value = player;
            }
        }
        return value;
    }

    // Dado um userID como chave, retorna uma lista com todas as avaliações (do tipo "Rating") feitas por aquele usuáruio
    public ArrayList<Rating> findRating(int key, TabelaHashRating table) {

        ArrayList<Rating> value = new ArrayList<Rating>();
        int tam = table.getTam();
        int hashCode = (key % tam); // calcula o hash code referente ao usuário que fez a avaliação para achá-lo na hash table
        LinkedList<Rating> linkedList = table.getValue().get(hashCode); // encontra a LinkedList que contém o usuário
        for (Rating rating : linkedList) { // encontra as Ratings feitas pelo usuário e as guarda na lista a ser retornada
            if (rating.getUserID() == key) {
                value.add(rating);
            }
        }
        return value;
    }

    // Dada uma lista de Tags em forma de Strings como entrada, retorna uma lista com os sofifaID referentes aos jogadores que possuem essas Tags
    public List<Integer> findTag(ArrayList<String> key, TabelaHashTag table) {

        int tam = table.getTam();
        List<Integer> value = new ArrayList<Integer>();
        int hashCode = 0;
        int nroKeys = 5 * key.size(); // o número de chaves é multiplicado por 5 pois cada Tag aparece 5 vezes para cada jogador no arquivo .csv
        for (String parte : key) {
            // calcula o hashCode referente à essa tag
            hashCode = 0;
            char[] tagCA = parte.toCharArray();
            for (int i = 0; i < tagCA.length; i++) {
                hashCode += (int) tagCA[i];
            }
            hashCode = (hashCode % tam);
            // encontra a LinkedList referente ao hashCode calculado acima
            LinkedList<Tag> linkedList = table.getValue().get(hashCode);
            // adiciona numa lista todos os IDs referentes aos jogadores que têm determinada tag
            for (Tag tag : linkedList) {
                if (tag.getTag().equals(parte)) {
                    value.add(tag.getSofifaID());
                }
            }
        }
        List<Integer> result = new ArrayList<Integer>();

        result = value.stream() // retira os IDs dos jogadores que não têm TODAS as tags pesquisadas e retirada os IDs duplicados do jogadores que têm as tags
                .filter(e -> Collections.frequency(value, e) >= nroKeys)
                .distinct()
                .collect(Collectors.toList());
        return result; // retorna a lista com os IDs dos jogadores que têm TODAS as tags pesquisadas
    }

    // Dada uma String que contém todas as tags a serem pesquisadas, imprime as informações dos jogadores que têm TODAS as tags
    public void pesquisaTag(String key, TabelaHashTag tagTable, TabelaHashPlayer playerTable) {

        List<Integer> list = new ArrayList<Integer>();
        Pesquisa search = new Pesquisa();
        ArrayList<String> parts = new ArrayList<String>();
        Scanner sc = new Scanner(key);
        // separa as tags da String e retira as 'aspas' de cada tag
        for (String s; (s = sc.findWithinHorizon("(?<=\\').*?(?=\\')", 0)) != null;) {
            parts.add(s);
        }
        parts.remove(" "); // remove os espaços vazios da lista que foram adicionado no processo acima
        parts.remove(" ");
        parts.remove(" ");
        parts.remove(" ");
        parts.remove(" ");
        list = search.findTag(parts, tagTable); // encontra ss IDs de todos os jogadores que têm as tags pesquisadas
        // imprime todas as informações sobre os jogadores da lista
        for (Integer id : list) {
            Player player = search.findPlayer(id, playerTable);
            System.out.printf("sofifa_id = %6d; name = %45s; positions = %12s; rating = %1.6f; counter = %6d \n",
                    player.getSofifaID(), player.getName(), player.getPositions(), player.getGlobalRating(),
                    player.getCounter());
        }
        sc.close();
    }

    // Dado um userID como entrada, imprime as informações sobre todas as avaliações feitas pelo usuário
    public void pesquisaUser(int key, TabelaHashRating ratingTable, TabelaHashPlayer playersTable) {

        ArrayList<Rating> list = new ArrayList<Rating>();
        Pesquisa search = new Pesquisa();
        list = search.findRating(key, ratingTable); // encontra todas as avaliações feitas pelo usuário pesquisado
        int i = 0;
        for (Rating rating : list) {
            Player player = search.findPlayer(rating.getSofifaID(), playersTable); // encontra o jogador avaliado para imprimir suas informações
            System.out.printf("sofifa_id = %6d; name = %45s; rating = %1.6f; counter = %6d; rating = %1.1f \n",
                    player.getSofifaID(), player.getName(), player.getGlobalRating(), player.getCounter(),
                    rating.getRating());
            i++;
            if (i == 20) // limita a impressão para no máximo 20 avaliações
                break;
        }
        System.out.println();
    }

    // Dado um nome ou parte de um nome, imprime as informações sobre todos os jogadores que contém a chave pesquisada em seus nomes
    public void pesquisaNome(String prefix, List<Player> playersList) {

        List<Player> list = new ArrayList<Player>();
        System.out.println();
        for (Player player : playersList) {
            if (player.getName().contains(prefix)) {
                list.add(player); // adiciona à lista todos os jogadores que contém a chave pesquisada em seus nomes
            }
        }
        for (Player player : list) { // imprime as informações dos jogadores da lista
            System.out.printf("sofifa_id = %6d; name = %45s; positions = %12s; rating = %1.6f; counter = %6d \n",
                    player.getSofifaID(), player.getName(), player.getPositions(), player.getGlobalRating(),
                    player.getCounter());
        }
    }

    // Dado um número de jogadores e uma posição, retorna os N jogadores mais bem avaliados que jogam em determinada posição
    public void pesquisaTopPos(int n, String pos, List<Player> playersList) {

        ArrayList<Player> list = new ArrayList<Player>();
        System.out.println();
        Scanner sc = new Scanner(pos);
        for (String s; (s = sc.findWithinHorizon("(?<=\\').*?(?=\\')", 0)) != null;) { // retira as 'aspas' da String para ser pesquisada
            pos = s;
        }
        sc.close();
        for (Player player : playersList) { // adiciona a uma lista todos jogadores da posição que têm pelo menos 1000 avaliações de usuários
            if (player.getPositions().contains(pos)) {
                if (player.getCounter() >= 1000) {
                    list.add(player);
                }
            }
        }
        int bigger;
        Player temp;
        for (int i = 0; i < list.size(); i++) { // ordena os jogadores pela média de avaliações usando Selection
            bigger = i;
            for (int j = i + 1; j < list.size(); j++) { // encontra o jogador com maior média de avaliações no resto da lista
                if (list.get(j).getGlobalRating() > list.get(bigger).getGlobalRating()) {
                    bigger = j;
                }
            }
            temp = list.get(bigger); // troca de posição o jogador atual com o de maior média da avaliações do resto da lista
            list.set(bigger, list.get(i));
            list.set(i, temp);
        }
        int i = 0;
        for (Player player : list) { // imrpime as informações dos jogadores encontrados na pesquisa
            System.out.printf("sofifa_id = %6d; name = %45s; positions = %12s; rating = %1.6f; counter = %6d \n",
                    player.getSofifaID(), player.getName(), player.getPositions(), player.getGlobalRating(),
                    player.getCounter());
            i++;
            if (i == n) // limita o número de jogadores a serem impressos ao número dado pelo usuário na pesquisa
                break;
        }
    }
}