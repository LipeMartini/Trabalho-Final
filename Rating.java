public class Rating {

    private int userID; // ID do usuário que fez a avaliação
    private int sofifaID; // ID do jogador que foi avaliado
    private double rating; // nota dada pelo usuário ao jogador, de 1 a 5
    
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
        return "userID=" + userID + ", sofifaID=" + sofifaID + ", Rating=" + rating ;
    }

}