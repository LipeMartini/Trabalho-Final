import java.util.ArrayList;
import java.util.LinkedList;

public class TabelaHashPlayer {

    int key;
    ArrayList<LinkedList<Player>> value;

    public int getKey() {
        return key;
    }
    public void setKey(int key) {
        this.key = key;
    }
    public ArrayList<LinkedList<Player>> getValue() {
        return value;
    }
    public void setValue(ArrayList<LinkedList<Player>> value) {
        this.value = value;
    }
    
    public ArrayList<LinkedList<Player>> addPlayer(ArrayList<LinkedList<Player>> list, Player value) {

        int hashIndex = (value.getSofifaID() % 20);
        list.get(hashIndex).add(value);

        return list;
    }

    public Player findSofifaID(int key, ArrayList<LinkedList<Player>> list) {

        Player value = null;

        int hashIndex = (key % 20);
        LinkedList<Player> linkedList = list.get(hashIndex);
        for (Player globalRating : linkedList) {
            if(globalRating.getSofifaID() == key) {
                value = globalRating;
            }
        }

        return value;
    }

}