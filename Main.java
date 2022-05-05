import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) throws IOException {
        System.out.println("\nFazendo a leitura e o pré processamento dos dados...");
        Reader reader = new Reader();
        Pesquisa search = new Pesquisa();
        // pré-processamento e construção das listas
        long time = System.currentTimeMillis();
        List<Tag> tagsList = reader.readTags("tags.csv"); // lê o arquivo com as Tags e retorna uma lista com todas
        List<Player> playersList = reader.readPlayers("players.csv"); // lê o arquivo com os Players e retorna uma lista com todos
        List<Rating> ratingsList = reader.readRatings("rating.csv"); // lê o arquivo com as Ratings e retorna uma lista com todas
        // construção das tabelas hash
        TabelaHashTag tagsTable = new TabelaHashTag();
        tagsTable = tagsTable.iniciaTagTable(tagsTable);
        for (Tag tag : tagsList) {
            tagsTable = tagsTable.addValue(tag, tagsTable);
        }
        TabelaHashPlayer playersTable = new TabelaHashPlayer();
        playersTable = playersTable.iniciaPlayerTable(playersTable);
        for (Player player : playersList) {
            playersTable = playersTable.addValue(playersTable, player);
        }
        TabelaHashRating ratingsTable = new TabelaHashRating();
        ratingsTable = ratingsTable.iniciaRatingTable(ratingsTable);
        for (Rating rating : ratingsList) {
            ratingsTable = ratingsTable.addValue(ratingsTable, rating);
        }
        // cálculo das médias de rating
        search.processaGlobalRatings(ratingsList, playersTable);
        for (Player player : playersList) {
            player.setGlobalRating(player.getGlobalRating() / player.getCounter());
        }
        // print de quanto tempo demorou para fazer toda a fase de leitura e pré processamento das estruturas de dados
        System.out.println("Tempo para construir todas as estruturas = " + (System.currentTimeMillis() - time) + "ms");
        // inicia-se o terminal e as pesquisas pelo usuário
        System.out.println("Pesquise da seguinte forma:");
        System.out.println("$ player <name or prefix> para pesquisar jogadores pelo nome");
        System.out.println("$ user <userID>           para pesquisar todas avaliações feitas por um usuário");
        System.out.println("$ top<N> '<position>'     para pesquisar o top <N> melhores jogadores pela posição");
        System.out.println("$ tags <list of tags>     para pesquisar jogadores que tenham determinadas tags");
        System.out.println("$ end                     para terminar as pesquisas");
        Scanner sc = new Scanner(System.in);
        search.terminal(playersTable, ratingsTable, tagsTable, playersList, sc); // inicia o terminal
        sc.close();
    }
}