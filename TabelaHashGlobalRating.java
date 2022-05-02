import java.util.ArrayList;
import java.util.LinkedList;

public class TabelaHashGlobalRating {

    ArrayList<LinkedList<GlobalRating>> value;
    int tam = 30;

    public ArrayList<LinkedList<GlobalRating>> getValue() {
        return value;
    }
    public void setValue(ArrayList<LinkedList<GlobalRating>> value) {
        this.value = value;
    }
    
    public ArrayList<LinkedList<GlobalRating>> addValue(ArrayList<LinkedList<GlobalRating>> list, GlobalRating value) {

        int hashCode = (value.getSofifaID() % tam);
        list.get(hashCode).add(value);

        return list;
    }

    public GlobalRating findValue(int key, ArrayList<LinkedList<GlobalRating>> list) {

        GlobalRating value = null;

        int hashCode = (key % tam);
        LinkedList<GlobalRating> linkedList = list.get(hashCode);
        for (GlobalRating globalRating : linkedList) {
            if(globalRating.getSofifaID() == key) {
                value = globalRating;
            }
        }

        return value;
    }

}