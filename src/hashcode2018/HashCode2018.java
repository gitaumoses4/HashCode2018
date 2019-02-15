package hashcode2018;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class HashCode2018 {

    private static final String INPUT_A = "a_example.in";
    private static final String INPUT_B = "b_should_be_easy.in";
    private static final String INPUT_C = "c_no_hurry.in";
    private static final String INPUT_D = "d_metropolis.in";
    private static final String INPUT_E = "e_high_bonus.in";

    private static final String OUTPUT_A = "a_example.out";
    private static final String OUTPUT_B = "b_should_be_easy.out";
    private static final String OUTPUT_C = "c_no_hurry.out";
    private static final String OUTPUT_D = "d_metropolis.out";
    private static final String OUTPUT_E = "e_high_bonus.out";

    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(new File(OUTPUT_B));
        Scanner in = new Scanner(new File(INPUT_B));
        int R, C, F, N, B, T;
        R = in.nextInt();
        C = in.nextInt();
        F = in.nextInt();
        N = in.nextInt();
        B = in.nextInt();
        T = in.nextInt();

        Vehicle vehicles[] = new Vehicle[F];
        for (int i = 0; i < vehicles.length; i++) {
            vehicles[i] = new Vehicle(i);
        }

        ArrayList<Ride> rides = new ArrayList();
        for (int i = 0; i < N; i++) {
            Ride ride = new Ride(in.nextInt(), in.nextInt(),
                    in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt());
            ride.index = i;
            rides.add(ride);
        }

        ArrayList<Ride> other = new ArrayList(rides);
        for (int i = 0; i < T && !other.isEmpty(); i++) {
            Vehicle vehicle = vehicles[0];
            if (!other.isEmpty()) {
                Ride ride = other.get(0);
                int vehicleCost = 0;
                for (Vehicle v : vehicles) {
                    int cost = 0;
                    for (Ride rr : other) {
                        int rideCost = cost(rr, v, i, B);
                        if (rideCost > cost) {
                            ride = rr;
                            cost = rideCost;
                        }
                    }
                    if (cost > vehicleCost) {
                        vehicle = v;
                        vehicleCost = cost;
                    }
                }

                vehicle.occupied = true;
                ride.vehicle = vehicle;
                vehicle.row = ride.endRow;
                vehicle.col = ride.endCol;
                vehicle.distance = ride.getDistance();
                vehicle.startTime = ride.earliestStart;
                vehicle.rides.add(ride.index);
                other.remove(ride);
            }
        }

        for (int i = 0; i < vehicles.length; i++) {
            writer.println(vehicles[i].rides.size() + " " + vehicles[i].getRides());
            System.out.println(vehicles[i].rides.size() + " " + vehicles[i].getRides());
        }
        writer.flush();
        writer.close();
    }

    public static int cost(Ride ride, Vehicle vehicle, int time, int bonus) {
        if(vehicle.occupied){
            return 0;
        }
        int reach = time + ride.distanceFrom(vehicle);
        if (reach <= ride.earliestStart) {
            return ride.getDistance() + bonus;
        } else {
            if (reach + ride.getDistance() <= ride.latestFinish) {
                return ride.getDistance();
            } else {
                return 0;
            }
        }
    }

    public static boolean willMakeIt(Ride ride, int time) {
        return time + ride.getDistance() <= ride.latestFinish;
    }

    public static Vehicle vehicleClosestTo(Vehicle[] vehicles, Ride ride, int t) {
        Vehicle vehicle = null;
        double distance = Integer.MAX_VALUE;
        for (Vehicle v : vehicles) {
            if (v.occupied && t >= v.startTime) {
                v.distance--;
                if (v.distance == 0) {
                    v.occupied = false;
                }
                continue;
            }
            double d = (double) (Math.abs(ride.startRow - v.row) + Math.abs(ride.startCol - v.col));
            if (d < distance) {
                distance = d;
                vehicle = v;
            }
        }
        return vehicle;
    }

}
