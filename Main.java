import java.util.ArrayList;
import java.util.List;

import org.supercsv.io.CsvBeanReader;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World");
        Reader reader = new Reader();
        List<Tag> tags = reader.readTag();
    }
    
}
