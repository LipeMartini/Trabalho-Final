import java.util.ArrayList;
import java.util.LinkedList;

public class TabelaHashRating {

    ArrayList<LinkedList<Rating>> value;
    int tam = 240000;

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

    // inicia uma hash table de ratings vazia
    public TabelaHashRating iniciaRatingTable(TabelaHashRating table) {

        table.value = new ArrayList<LinkedList<Rating>>();
        for (int i = 0; i < tam; i++) {
            LinkedList<Rating> list = new LinkedList<Rating>();
            table.value.add(list);
        }
        return table;
    }
    
    // recebe um "Rating", adiciona na hash table e retorna a table com o elemento incluído
    public TabelaHashRating addValue(TabelaHashRating list, Rating value) {

        int hashCode = (value.getUserID() % tam);
        list.getValue().get(hashCode).add(value);
        return list;
    }

}