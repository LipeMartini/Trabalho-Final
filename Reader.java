import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseDouble;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

public class Reader {

    public List<Tag> readTags(String csvFileName) throws IOException {

        ArrayList<Tag> arrayList = new ArrayList<Tag>();

        ICsvBeanReader beanReader = null;
        try {
            beanReader = new CsvBeanReader(new FileReader(csvFileName), CsvPreference.STANDARD_PREFERENCE);

            final String[] header = beanReader.getHeader(true);
            final CellProcessor[] processors = new CellProcessor[] {
                    new ParseInt(),
                    new ParseInt(),
                    new Optional(),
            };
            final String[] columns = new String[] {
                    "userID",
                    "sofifaID",
                    "tag",
            };

            Tag tag;
            while ((tag = beanReader.read(Tag.class, columns, processors)) != null) {
                arrayList.add(tag);
            }

        } finally {
            if (beanReader != null) {
                beanReader.close();
            }
        }

        return arrayList;
    }

    public List<Player> readPlayers(String csvFileName) throws IOException {

        ArrayList<Player> arrayList = new ArrayList<Player>();

        ICsvBeanReader beanReader = null;
        try {
            beanReader = new CsvBeanReader(new FileReader(csvFileName), CsvPreference.STANDARD_PREFERENCE);

            final String[] header = beanReader.getHeader(true);
            final CellProcessor[] processors = new CellProcessor[] {
                    new ParseInt(),
                    new Optional(),
                    new Optional(),
            };
            final String[] columns = new String[] {
                    "sofifaID",
                    "name",
                    "positions",
            };

            Player player;
            while ((player = beanReader.read(Player.class, columns, processors)) != null) {
                player.setGlobalRating(0.0);
                player.setCounter(0);
                arrayList.add(player);
                // System.out.println(player);
            }

        } finally {
            if (beanReader != null) {
                beanReader.close();
            }
        }

        return arrayList;
    }

    public List<Rating> readRatings(String csvFileName) throws IOException {

        ArrayList<Rating> arrayList = new ArrayList<Rating>();

        ICsvBeanReader beanReader = null;
        try {
            beanReader = new CsvBeanReader(new FileReader(csvFileName), CsvPreference.STANDARD_PREFERENCE);

            final String[] header = beanReader.getHeader(true);
            final CellProcessor[] processors = new CellProcessor[] {
                    new ParseInt(),
                    new ParseInt(),
                    new ParseDouble(),
            };
            final String[] columns = new String[] {
                    "userID",
                    "sofifaID",
                    "rating",
            };

            Rating rating;
            while ((rating = beanReader.read(Rating.class, columns, processors)) != null) {
                arrayList.add(rating);
                // System.out.println(rating);
            }

        } finally {
            if (beanReader != null) {
                beanReader.close();
            }
        }

        return arrayList;
    }

}
