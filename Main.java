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
            List<Player> players = reader.readPlayers("players.csv");
            List<Rating> ratings = reader.readRatings("minirating.csv");
            System.out.println("Loaded tags: " + tags.size());
            System.out.println("Loaded players: " + players.size());
            System.out.println("Loaded ratings: " + ratings.size());
            //System.out.println("Tempo para buildar as listas " + (System.currentTimeMillis() - time));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
