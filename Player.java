public class Player {

    private int sofifaID;
    private String name;
    private String positions;
    public Double globalRating;
    public int counter;
    
    public Double getGlobalRating() {
        return globalRating;
    }
    public void setGlobalRating(Double globalRating) {
        this.globalRating = globalRating;
    }
    public int getCounter() {
        return counter;
    }
    public void setCounter(int counter) {
        this.counter = counter;
    }
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
        return "sofifa_id = " + sofifaID + ", name = " + name + ", positions = " + positions + ", rating = " + globalRating + ", counter = " + counter;
    }

}