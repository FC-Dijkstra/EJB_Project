public class HistoryItem implements Serializable{
    public long ID;
    public double delta;

    public HistoryItem(long ID, double delta) {
        this.ID = ID;
        this.delta = delta;
    }
}
