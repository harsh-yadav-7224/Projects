// ===============================
// Railway Reservation System 
// ===============================

import java.util.*;

class Passenger {

    int id;
    String name;

    Passenger(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

public class RailwayReservationSystem {

    static int availableSeats = 2;

    static Queue<Passenger> waitingList = new LinkedList<>();

    static ArrayList<Passenger> confirmedPassengers = new ArrayList<>();

    static Scanner sc = new Scanner(System.in);

    // Book Ticket
    static void bookTicket() {

        System.out.print("Enter Passenger ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Passenger Name: ");
        String name = sc.nextLine();

        Passenger p = new Passenger(id, name);

        if (availableSeats > 0) {

            confirmedPassengers.add(p);

            availableSeats--;

            System.out.println("Ticket Confirmed!");
        } else {

            waitingList.add(p);

            System.out.println("Added to Waiting List.");
        }
    }

    // Cancel Ticket
    static void cancelTicket() {

        System.out.print("Enter Passenger ID to Cancel: ");

        int id = sc.nextInt();

        boolean found = false;

        for (int i = 0; i < confirmedPassengers.size(); i++) {

            if (confirmedPassengers.get(i).id == id) {

                confirmedPassengers.remove(i);

                availableSeats++;

                found = true;

                System.out.println("Ticket Cancelled.");

                // Move waiting passenger to confirmed
                if (!waitingList.isEmpty()) {

                    Passenger wp = waitingList.poll();

                    confirmedPassengers.add(wp);

                    availableSeats--;

                    System.out.println(
                            wp.name + " moved from Waiting List to Confirmed.");
                }

                break;
            }
        }

        if (!found) {
            System.out.println("Passenger Not Found.");
        }
    }

    // Display Confirmed Passengers
    static void displayPassengers() {

        System.out.println("\nConfirmed Passengers:");

        if (confirmedPassengers.isEmpty()) {

            System.out.println("No Confirmed Passengers.");
        } else {

            for (Passenger p : confirmedPassengers) {

                System.out.println(
                        "ID: " + p.id + " Name: " + p.name);
            }
        }
    }

    // Display Waiting List
    static void displayWaitingList() {

        System.out.println("\nWaiting List:");

        if (waitingList.isEmpty()) {

            System.out.println("No Waiting Passengers.");
        } else {

            for (Passenger p : waitingList) {

                System.out.println(
                        "ID: " + p.id + " Name: " + p.name);
            }
        }
    }

    // Search Passenger
    static void searchPassenger() {

        System.out.print("Enter Passenger ID to Search: ");

        int id = sc.nextInt();

        for (Passenger p : confirmedPassengers) {

            if (p.id == id) {

                System.out.println(
                        "Passenger Found: " + p.name);

                return;
            }
        }

        System.out.println("Passenger Not Found.");
    }

    public static void main(String[] args) {

        int choice;

        do {

            System.out.println("\n===== Railway Reservation System =====");

            System.out.println("1. Book Ticket");
            System.out.println("2. Cancel Ticket");
            System.out.println("3. Display Confirmed Passengers");
            System.out.println("4. Display Waiting List");
            System.out.println("5. Search Passenger");
            System.out.println("6. Exit");

            System.out.print("Enter Choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    bookTicket();
                    break;

                case 2:
                    cancelTicket();
                    break;

                case 3:
                    displayPassengers();
                    break;

                case 4:
                    displayWaitingList();
                    break;

                case 5:
                    searchPassenger();
                    break;

                case 6:
                    System.out.println("Thank You!");
                    break;

                default:
                    System.out.println("Invalid Choice.");
            }

        } while (choice != 6);
    }
}