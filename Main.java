import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Hello World");
        Reader reader = new Reader();
        // pré-processamento e construção das listas
        long time = System.currentTimeMillis();

        List<Tag> tags = reader.readTags("tags.csv");
        System.out.println("Loaded tags: " + tags.size());
        List<Player> players = reader.readPlayers("players.csv");
        System.out.println("Loaded players: " + players.size());
        List<Rating> ratings = reader.readRatings("minirating.csv");
        System.out.println("Loaded ratings: " + ratings.size());
        List<GlobalRating> ratingsCounters = reader.readGlobalRating();
        System.out.println("Loaded ratingCounters: " + ratingsCounters.size());
        // construção das tabelas hash
        TabelaHashTag tabelaHashTags = new TabelaHashTag();
        tabelaHashTags = tabelaHashTags.iniciaTagTable(tabelaHashTags);
        for (Tag tag : tags) {
            tabelaHashTags = tabelaHashTags.addValue(tag, tabelaHashTags);
        }

        System.out.println("Tempo para construir todas as estruturas = " + (System.currentTimeMillis() - time));

        // inicia-se o terminal e as pesquisas pelo usuário
        ArrayList<Tag> listaTeste = new ArrayList<Tag>();
        Pesquisa searchTag = new Pesquisa();
        listaTeste = searchTag.findTag("Brazil", tabelaHashTags);

    }
}