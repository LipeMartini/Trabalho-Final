import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Construindo listas e pré processamento");
        Reader reader = new Reader();
        Pesquisa search = new Pesquisa();

        // pré-processamento e construção das listas
        long time = System.currentTimeMillis();
        List<Tag> tagsList = reader.readTags("tags.csv");
        // System.out.println("Loaded tags: " + tagsList.size());
        List<Player> playersList = reader.readPlayers("players.csv");
        // System.out.println("Loaded players: " + playersList.size());
        List<Rating> ratingsList = reader.readRatings("rating.csv");
        // System.out.println("Loaded ratings: " + ratingsList.size());

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

        search.processaGlobalRatings(ratingsList, playersTable);
        for (Player player : playersList) {
            player.setGlobalRating(player.getGlobalRating() / player.getCounter());
        }

        System.out.println("Tempo para construir todas as estruturas = " + (System.currentTimeMillis() - time));

        // inicia-se o terminal e as pesquisas pelo usuário

        System.out.println("Pesquise da seguinte forma:");
        System.out.println("$ player <name or prefix> para pesquisar jogadores pelo nome");
        System.out.println("$ user <userID>           para pesquisar todas avaliações feitas por um usuário");
        System.out.println("$ top<N> '<position>'     para pesquisar o top <N> melhores jogadores pela posição");
        System.out.println("$ tags <list of tags>     para pesquisar jogadores que tenham determinadas tags");
        System.out.println("$ end                     para terminar as pesquisas");

        Scanner sc = new Scanner(System.in);
        search.terminal(playersTable, ratingsTable, tagsTable, playersList, sc);
        sc.close();

        // search.pesquisaTag("tags 'English Premier League' 'Brazil'", tagsTable,
        // playersTable);
        // search.pesquisaUser(110250, ratingsTable, playersTable);
        // for (int i=0; i<20; i++)
        // System.out.println(playersList.get(i).getGlobalRating());

    }
}