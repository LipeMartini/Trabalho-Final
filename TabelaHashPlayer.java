import java.util.ArrayList;
import java.util.LinkedList;

public class TabelaHashPlayer {

    ArrayList<LinkedList<Player>> value;
    int tam = 30;

    public ArrayList<LinkedList<Player>> getValue() {
        return value;
    }
    public void setValue(ArrayList<LinkedList<Player>> value) {
        this.value = value;
    }
    
    public ArrayList<LinkedList<Player>> addValue(ArrayList<LinkedList<Player>> list, Player value) {

        int hashCode = (value.getSofifaID() % tam);
        list.get(hashCode).add(value);

        return list;
    }

    public Player findSofifaID(int key, ArrayList<LinkedList<Player>> list) {

        Player value = null;

        int hashCode = (key % tam);
        LinkedList<Player> linkedList = list.get(hashCode);
        for (Player globalRating : linkedList) {
            if(globalRating.getSofifaID() == key) {
                value = globalRating;
            }
        }

        return value;
    }

}