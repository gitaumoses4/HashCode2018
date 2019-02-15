package hashcode2018;

public class Ride implements Comparable<Ride> {

    public int index;
    public int startRow;
    public int startCol;
    public int endRow;
    public int endCol;
    public int earliestStart;
    public int latestFinish;
    public Vehicle vehicle;
    public int timeReleased;

    public Ride(int startRow, int startCol, int endRow, int endCol, int earliestStart, int latestFinish) {
        this.startRow = startRow;
        this.startCol = startCol;
        this.endRow = endRow;
        this.endCol = endCol;
        this.earliestStart = earliestStart;
        this.latestFinish = latestFinish;
    }

    public int getDistance() {
        return Math.abs(startRow - endRow) + Math.abs(endCol - startCol);
    }

    public double getPriority() {
        return getDistance();
    }

    public int distanceFrom(Vehicle vehicle) {
        return Math.abs(vehicle.row - startRow) + Math.abs(vehicle.col - startCol);
    }

    @Override
    public int compareTo(Ride o) {
        return Double.compare(o.getPriority(), getPriority());
    }

    @Override
    public String toString() {
        return String.format("[(%d %d) - (%d %d) time [%d to %d]] ", startRow, startCol, endRow, endCol, earliestStart, latestFinish);
    }

}
