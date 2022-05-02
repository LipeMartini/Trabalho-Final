import java.util.ArrayList;
import java.util.LinkedList;

public class TabelaHashTag {
    
    int key;
    ArrayList<LinkedList<Tag>> value;

    public int getKey() {
        return key;
    }
    public void setKey(int key) {
        this.key = key;
    }
    public ArrayList<LinkedList<Tag>> getValue() {
        return value;
    }
    public void setValue(ArrayList<LinkedList<Tag>> value) {
        this.value = value;
    }
    
    public ArrayList<LinkedList<Tag>> addValue(ArrayList<LinkedList<Tag>> list, Tag value) {

        int hashIndex = (Integer.parseInt(value.getTag()) % 20);
        list.get(hashIndex).add(value);

        return list;
    }

    // Dada uma Tag como entrada em forma de String, retorna uma lista do tipo "Tag" com todas as tags referentes à String de entrada
    public ArrayList<Tag> findValue(String key, ArrayList<LinkedList<Tag>> list) {

        ArrayList<Tag> value = new ArrayList<Tag>();

        int hashIndex = (Integer.parseInt(key) % 20);
        LinkedList<Tag> linkedList = list.get(hashIndex);
        for (Tag tag : linkedList) {
            if(tag.getTag() == key) {
                value.add(tag);
            }
        }

        return value;
    }
    
}
