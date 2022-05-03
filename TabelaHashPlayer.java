import java.util.ArrayList;
import java.util.LinkedList;

public class TabelaHashPlayer {

    ArrayList<LinkedList<Player>> value;
    int tam = 30;

    public int getTam() {
        return tam;
    }
    public void setTam(int tam) {
        this.tam = tam;
    }
    public ArrayList<LinkedList<Player>> getValue() {
        return value;
    }
    public void setValue(ArrayList<LinkedList<Player>> value) {
        this.value = value;
    }

    public TabelaHashPlayer iniciaPlayerTable(TabelaHashPlayer table) {

        table.value = new ArrayList<LinkedList<Player>>();
        for (int i = 0; i < tam; i++) {
            LinkedList<Player> list = new LinkedList<Player>();
            table.value.add(list);
        }

        return table;
    }
    
    public TabelaHashPlayer addValue(TabelaHashPlayer list, Player value) {

        int hashCode = (value.getSofifaID() % tam);
        list.getValue().get(hashCode).add(value);

        return list;
    }

}