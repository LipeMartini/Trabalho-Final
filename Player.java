public class Player {

    private int sofifaID;
    private String name;
    private String positions;
    
    public int getSofifaID() {
        return sofifaID;
    }
    public String getPositions() {
        return positions;
    }
    public void setPositions(String positions) {
        this.positions = positions;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSofifaID(int sofifaID) {
        this.sofifaID = sofifaID;
    }
    
    @Override
    public String toString() {
        return "Player [name=" + name + ", positions=" + positions + ", sofifaID=" + sofifaID + "]";
    }

}