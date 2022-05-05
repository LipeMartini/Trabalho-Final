public class Tag {
    
    private int userID; // ID do usuário que deu a Tag
    private int sofifaID; // ID do jogador que tem a Tag
    private String tag; // Tag referente a uma característica do jogador

    
    public int getUserID() {
        return userID;
    }
    public String getTag() {
        return tag;
    }
    public void setTag(String tag) {
        this.tag = tag;
    }
    public int getSofifaID() {
        return sofifaID;
    }
    public void setSofifaID(int sofifaID) {
        this.sofifaID = sofifaID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }
    
    @Override
    public String toString() {
        return "Tag [sofifaID=" + sofifaID + ", tag=" + tag + ", userID=" + userID + "]";
    }
    
}
