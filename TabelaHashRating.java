import java.util.ArrayList;
import java.util.LinkedList;

public class TabelaHashRating {

    int key;
    ArrayList<LinkedList<Rating>> value;

    public int getKey() {
        return key;
    }
    public void setKey(int key) {
        this.key = key;
    }
    public ArrayList<LinkedList<Rating>> getValue() {
        return value;
    }
    public void setValue(ArrayList<LinkedList<Rating>> value) {
        this.value = value;
    }
    
    public ArrayList<LinkedList<Rating>> addValue(int key, ArrayList<LinkedList<Rating>> list, Rating value) {

        int hashIndex = (value.getUserID() % 20);
        list.get(hashIndex).add(value);

        return list;
    }

    // Dado um userID como chave, retorna uma lista com todas as avaliações (do tipo "Rating") feitas por aquele usuáruio
    public ArrayList<Rating> findUserID(int key, ArrayList<LinkedList<Rating>> list) {

        ArrayList<Rating> value = new ArrayList<Rating>();

        int hashIndex = (key % 20);
        LinkedList<Rating> linkedList = list.get(hashIndex);
        for (Rating rating : linkedList) {
            if(rating.getUserID() == key) {
                value.add(rating);
            }
        }

        return value;
    }

}