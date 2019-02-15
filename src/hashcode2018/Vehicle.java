package hashcode2018;

import java.util.ArrayList;

public class Vehicle {

    public int index;
    public int row;
    public int col;
    public boolean occupied = false;
    public int startTime;
    public int distance;
    public ArrayList<Integer> rides = new ArrayList();

    public Vehicle(int index) {
        this.index = index;
    }

    public String getRides() {
        StringBuilder builder = new StringBuilder();
        for (Integer i : rides) {
            builder.append(" ").append(String.valueOf(i));
        }
        return builder.toString();
    }
}
