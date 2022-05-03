import java.util.ArrayList;
import java.util.LinkedList;

public class TabelaHashRating {

    ArrayList<LinkedList<Rating>> value;
    int tam = 30;

    public int getTam() {
        return tam;
    }
    public void setTam(int tam) {
        this.tam = tam;
    }
    public ArrayList<LinkedList<Rating>> getValue() {
        return value;
    }
    public void setValue(ArrayList<LinkedList<Rating>> value) {
        this.value = value;
    }

    public TabelaHashRating iniciaRatingTable(TabelaHashRating table) {

        table.value = new ArrayList<LinkedList<Rating>>();
        for (int i = 0; i < tam; i++) {
            LinkedList<Rating> list = new LinkedList<Rating>();
            table.value.add(list);
        }

        return table;
    }
    
    public TabelaHashRating addValue(TabelaHashRating list, Rating value) {

        int hashCode = (value.getUserID() % tam);
        list.getValue().get(hashCode).add(value);

        return list;
    }

}