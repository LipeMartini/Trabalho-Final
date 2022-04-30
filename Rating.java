public class Rating {

    private int userID;
    private int sofifaID;
    private double rating;
    
    public int getUserID() {
        return userID;
    }
    public double getRating() {
        return rating;
    }
    public void setRating(double rating) {
        this.rating = rating;
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
        return "Rating [rating=" + rating + ", sofifaID=" + sofifaID + ", userID=" + userID + "]";
    }

}