import java.util.ArrayList;
import java.util.LinkedList;

public class TabelaHashGlobalRating {

    int key;
    ArrayList<LinkedList<GlobalRating>> value;

    public int getKey() {
        return key;
    }
    public void setKey(int key) {
        this.key = key;
    }
    public ArrayList<LinkedList<GlobalRating>> getValue() {
        return value;
    }
    public void setValue(ArrayList<LinkedList<GlobalRating>> value) {
        this.value = value;
    }
    
    public ArrayList<LinkedList<GlobalRating>> addValue(ArrayList<LinkedList<GlobalRating>> list, GlobalRating value) {

        int hashIndex = (value.getSofifaID() % 20);
        list.get(hashIndex).add(value);

        return list;
    }

    public GlobalRating findValue(int key, ArrayList<LinkedList<GlobalRating>> list) {

        GlobalRating value = null;

        int hashIndex = (key % 20);
        LinkedList<GlobalRating> linkedList = list.get(hashIndex);
        for (GlobalRating globalRating : linkedList) {
            if(globalRating.getSofifaID() == key) {
                value = globalRating;
            }
        }

        return value;
    }

}