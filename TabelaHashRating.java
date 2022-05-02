import java.util.ArrayList;
import java.util.LinkedList;

public class TabelaHashRating {

    ArrayList<LinkedList<Rating>> value;
    int tam = 30;

    public ArrayList<LinkedList<Rating>> getValue() {
        return value;
    }
    public void setValue(ArrayList<LinkedList<Rating>> value) {
        this.value = value;
    }
    
    public ArrayList<LinkedList<Rating>> addValue(int key, ArrayList<LinkedList<Rating>> list, Rating value) {

        int hashCode = (value.getUserID() % tam);
        list.get(hashCode).add(value);

        return list;
    }

    // Dado um userID como chave, retorna uma lista com todas as avaliações (do tipo "Rating") feitas por aquele usuáruio
    public ArrayList<Rating> findUserID(int key, ArrayList<LinkedList<Rating>> list) {

        ArrayList<Rating> value = new ArrayList<Rating>();

        int hashCode = (key % tam);
        LinkedList<Rating> linkedList = list.get(hashCode);
        for (Rating rating : linkedList) {
            if(rating.getUserID() == key) {
                value.add(rating);
            }
        }

        return value;
    }

}