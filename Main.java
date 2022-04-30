import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.supercsv.io.CsvBeanReader;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World");
        Reader reader = new Reader();
        try {
            List<Tag> tags = reader.readTags("tags.csv");
            List<Player> players = reader.readPlayers("players.csv");
            System.out.println("Loaded tags: " + tags.size());
            System.out.println("Loaded players: " + players.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
