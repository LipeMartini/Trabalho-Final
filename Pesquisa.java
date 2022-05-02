import java.util.ArrayList;
import java.util.LinkedList;

public class Pesquisa {

    // Dada uma Tag como entrada em forma de String, retorna uma lista do tipo "Tag" com todas as tags referentes à String de entrada
    public ArrayList<Tag> findTag(String key, TabelaHashTag table) {

        int tam = table.getTam();
        ArrayList<Tag> value = new ArrayList<Tag>();
        int hashCode = 0;

        char[] tagCA = key.toCharArray();
        for (int i = 0; i < tagCA.length; i++) {
            hashCode += (int) tagCA[i];
        }
        hashCode = (hashCode % tam);

        LinkedList<Tag> linkedList = table.getValue().get(hashCode);
        for (Tag tag : linkedList) {
            if (tag.getTag().equals(key)) {
                value.add(tag);
                //System.out.println(tag);
            }
        }

        return value;
    }
}
