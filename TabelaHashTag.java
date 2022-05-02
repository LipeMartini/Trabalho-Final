import java.util.ArrayList;
import java.util.LinkedList;

public class TabelaHashTag {

    ArrayList<LinkedList<Tag>> value;
    int tam = 73;

    public int getTam() {
        return tam;
    }

    public void setTam(int tam) {
        this.tam = tam;
    }

    public ArrayList<LinkedList<Tag>> getValue() {
        return value;
    }

    public void setValue(ArrayList<LinkedList<Tag>> value) {
        this.value = value;
    }

    public TabelaHashTag iniciaTagTable(TabelaHashTag table) {

        table.value = new ArrayList<LinkedList<Tag>>();
        for (int i = 0; i < tam; i++) {
            LinkedList<Tag> list = new LinkedList<Tag>();
            table.value.add(list);
        }

        return table;
    }

    public TabelaHashTag addValue(Tag value, TabelaHashTag table) {

        if (value.getTag() != null) {
            int hashCode = 0;
            char[] tag = value.getTag().toCharArray();
            for (int i = 0; i < tag.length; i++) {
                hashCode += (int) tag[i];
            }
            hashCode = (hashCode % tam);
            table.getValue().get(hashCode).add(value);
            //System.out.println(value);
        }
        return table;
    }

}
