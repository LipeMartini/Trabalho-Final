import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.supercsv.io.CsvBeanReader;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World");
        Reader reader = new Reader();
        // pré-processamento e construção das listas
        try {
            long time = System.currentTimeMillis();

            List<Tag> tags = reader.readTags("tags.csv");
            System.out.println("Loaded tags: " + tags.size());
            List<Player> players = reader.readPlayers("players.csv");
            System.out.println("Loaded players: " + players.size());
            List<Rating> ratings = reader.readRatings("minirating.csv");
            System.out.println("Loaded ratings: " + ratings.size());
            List<GlobalRating> ratingsCounter = reader.readGlobalRating();
            System.out.println("Loaded ratingCounters: " + ratingsCounter.size());

            

            System.out.println("Tempo para construir todas as estruturas = " + (System.currentTimeMillis() - time));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // inicia-se o terminal e as pesquisas pelo usuário

    }
}