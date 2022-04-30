import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

public class Reader {

    private static String csvFileName = "tags.csv";

    public List<Tag> readTag() {

        ArrayList<Tag> arrayList = new ArrayList<Tag>();

        ICsvBeanReader beanReader = null;
        try {
            beanReader = new CsvBeanReader(new FileReader(csvFileName), CsvPreference.STANDARD_PREFERENCE);

            // the header elements are used to map the values to the bean (names must match)
            final String[] header = beanReader.getHeader(true);
            final CellProcessor[] processors = getProcessors();
            final String[] columns = new String[] {
                "userID",
                "sofifaID",
                "tag",
        };

            Tag tag;
            while ((tag = beanReader.read(Tag.class, columns, processors)) != null) {
                //System.out.println(String.format("lineNo=%s, rowNo=%s, tag=%s", beanReader.getLineNumber(),
                //        beanReader.getRowNumber(), tag));
                arrayList.add(tag);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (beanReader != null) {
                try {
                    beanReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return arrayList;
    }

    private CellProcessor[] getProcessors() {
        final CellProcessor[] processors = new CellProcessor[] {
                new ParseInt(),
                new ParseInt(),
                //new NotNull(), 
                new Optional(),
        };

        return processors;
    }
}
