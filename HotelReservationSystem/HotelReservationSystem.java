import java.util.ArrayList;
import java.util.Scanner;

class Room {

    private int roomNumber;
    private String category;
    private double price;
    private boolean booked;

    private String customerName;
    private String phoneNumber;
    private int days;

    public Room(int roomNumber, String category, double price) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.price = price;
        this.booked = false;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public boolean isBooked() {
        return booked;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getDays() {
        return days;
    }

    public void bookRoom(String customerName, String phoneNumber, int days) {
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.days = days;
        this.booked = true;
    }

    public void cancelBooking() {
        booked = false;
        customerName = "";
        phoneNumber = "";
        days = 0;
    }

    public double getTotalAmount() {
        return price * days;
    }
}

public class HotelReservationSystem {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Room> rooms = new ArrayList<>();

    // Initialize Rooms
    public static void initializeRooms() {

        rooms.add(new Room(101, "Standard", 1500));
        rooms.add(new Room(102, "Standard", 1500));
        rooms.add(new Room(103, "Standard", 1500));
        rooms.add(new Room(104, "Standard", 1500));
        rooms.add(new Room(105, "Standard", 1500));

        rooms.add(new Room(201, "Deluxe", 3000));
        rooms.add(new Room(202, "Deluxe", 3000));
        rooms.add(new Room(203, "Deluxe", 3000));
        rooms.add(new Room(204, "Deluxe", 3000));
        rooms.add(new Room(205, "Deluxe", 3000));

        rooms.add(new Room(301, "Suite", 5000));
        rooms.add(new Room(302, "Suite", 5000));
        rooms.add(new Room(303, "Suite", 5000));
        rooms.add(new Room(304, "Suite", 5000));
        rooms.add(new Room(305, "Suite", 5000));
    }

    // View Rooms
    public static void viewRooms() {

        System.out.println("\n================ AVAILABLE ROOMS ================");

        System.out.printf("%-10s %-15s %-10s %-12s\n",
                "Room No", "Category", "Price", "Status");

        System.out.println("----------------------------------------------------------");

        for (Room room : rooms) {

            String status = room.isBooked() ? "Booked" : "Available";

            System.out.printf("%-10d %-15s %-10.0f %-12s\n",
                    room.getRoomNumber(),
                    room.getCategory(),
                    room.getPrice(),
                    status);
        }
    }

    // Search Room by Category
    public static void searchRoom() {

        System.out.print("\nEnter Category (Standard/Deluxe/Suite): ");
        String category = sc.nextLine();

        boolean found = false;

        System.out.println("\nAvailable Rooms:");

        for (Room room : rooms) {

            if (room.getCategory().equalsIgnoreCase(category)
                    && !room.isBooked()) {

                System.out.println("Room "
                        + room.getRoomNumber()
                        + "  Price: ₹"
                        + room.getPrice());

                found = true;
            }
        }

        if (!found) {
            System.out.println("No Available Rooms.");
        }
    }

    // Find Room
    public static Room findRoom(int roomNumber) {

        for (Room room : rooms) {

            if (room.getRoomNumber() == roomNumber)
                return room;
        }

        return null;
    }
        // Book Room
    public static void bookRoom() {

        System.out.print("\nEnter Room Number: ");
        int roomNumber = sc.nextInt();
        sc.nextLine();

        Room room = findRoom(roomNumber);

        if (room == null) {
            System.out.println("Invalid Room Number!");
            return;
        }

        if (room.isBooked()) {
            System.out.println("Sorry! This room is already booked.");
            return;
        }

        System.out.print("Enter Customer Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Phone Number: ");
        String phone = sc.nextLine();

        System.out.print("Enter Number of Days: ");
        int days = sc.nextInt();
        sc.nextLine();

        double total = room.getPrice() * days;

        System.out.println("\n========== PAYMENT ==========");
        System.out.println("Total Amount : Rs." + total);

        System.out.println("Choose Payment Method");
        System.out.println("1. UPI");
        System.out.println("2. Card");
        System.out.println("3. Cash");

        System.out.print("Enter Choice: ");
        int payment = sc.nextInt();
        sc.nextLine();

        switch (payment) {

            case 1:
                System.out.println("Payment through UPI Successful.");
                break;

            case 2:
                System.out.println("Payment through Card Successful.");
                break;

            case 3:
                System.out.println("Cash Payment Received.");
                break;

            default:
                System.out.println("Invalid Payment Option.");
                return;
        }

        room.bookRoom(name, phone, days);

        System.out.println("\nRoom Booked Successfully!");
        System.out.println("Booking Confirmed.");
    }

    // View Booking Details
    public static void viewBookings() {

        boolean found = false;

        System.out.println("\n================ BOOKING DETAILS ================");

        for (Room room : rooms) {

            if (room.isBooked()) {

                found = true;

                System.out.println("-------------------------------------------");

                System.out.println("Room Number : " + room.getRoomNumber());
                System.out.println("Category    : " + room.getCategory());
                System.out.println("Customer    : " + room.getCustomerName());
                System.out.println("Phone       : " + room.getPhoneNumber());
                System.out.println("Days        : " + room.getDays());
                System.out.println("Price/Day   : Rs." + room.getPrice());
                System.out.println("Total Bill  : Rs." + room.getTotalAmount());

                System.out.println("-------------------------------------------");
            }
        }

        if (!found) {

            System.out.println("No Bookings Available.");
        }
    }

    // Cancel Reservation
    public static void cancelReservation() {

        System.out.print("\nEnter Room Number to Cancel Booking: ");

        int roomNumber = sc.nextInt();
        sc.nextLine();

        Room room = findRoom(roomNumber);

        if (room == null) {

            System.out.println("Invalid Room Number!");
            return;
        }

        if (!room.isBooked()) {

            System.out.println("Room is already available.");
            return;
        }

        room.cancelBooking();

        System.out.println("Booking Cancelled Successfully!");
    }
        // Main Method
    public static void main(String[] args) {

        initializeRooms();

        int choice;

        do {

            System.out.println("\n=========================================");
            System.out.println("      HOTEL RESERVATION SYSTEM");
            System.out.println("=========================================");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Search Room by Category");
            System.out.println("3. Book Room");
            System.out.println("4. View Booking Details");
            System.out.println("5. Cancel Reservation");
            System.out.println("6. Exit");
            System.out.println("=========================================");

            System.out.print("Enter Your Choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    viewRooms();
                    break;

                case 2:
                    searchRoom();
                    break;

                case 3:
                    bookRoom();
                    break;

                case 4:
                    viewBookings();
                    break;

                case 5:
                    cancelReservation();
                    break;

                case 6:
                    System.out.println("\nThank You for Using Hotel Reservation System!");
                    break;

                default:
                    System.out.println("\nInvalid Choice! Please Try Again.");
            }

        } while (choice != 6);

        sc.close();
    }
}
