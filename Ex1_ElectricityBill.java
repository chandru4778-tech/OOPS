import java.util.Scanner;

class ElectricityBill {
    int consumerNo;
    String consumerName;
    int previousReading, currentReading;
    String type;
    double bill;

    void getData() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Consumer Number: ");
        consumerNo = sc.nextInt();
        System.out.print("Enter Consumer Name: ");
        consumerName = sc.next();
        System.out.print("Enter Previous Reading: ");
        previousReading = sc.nextInt();
        System.out.print("Enter Current Reading: ");
        currentReading = sc.nextInt();
        System.out.print("Enter Connection Type (Domestic/Commercial): ");
        type = sc.next();
    }

    void calculate() {
        int units = currentReading - previousReading;
        if (type.equalsIgnoreCase("Domestic")) {
            if (units <= 100)
                bill = units * 1.0;
            else if (units <= 200)
                bill = 100 * 1.0 + (units - 100) * 2.5;
            else if (units <= 500)
                bill = 100 * 1.0 + 100 * 2.5 + (units - 200) * 4.0;
            else
                bill = 100 * 1.0 + 100 * 2.5 + 300 * 4.0 + (units - 500) * 6.0;
        } else {
            if (units <= 100)
                bill = units * 2.0;
            else if (units <= 200)
                bill = 100 * 2.0 + (units - 100) * 4.5;
            else if (units <= 500)
                bill = 100 * 2.0 + 100 * 4.5 + (units - 200) * 6.0;
            else
                bill = 100 * 2.0 + 100 * 4.5 + 300 * 6.0 + (units - 500) * 7.5;
        }
    }

    void display() {
        int units = currentReading - previousReading;
        System.out.println("\n------ Electricity Bill ------\n");
        System.out.println("Consumer Number : " + consumerNo);
        System.out.println("Consumer Name   : " + consumerName);
        System.out.println("Connection Type : " + type);
        System.out.println("Units Consumed  : " + units);
        System.out.println("Total Bill      : Rs. " + bill);
    }
}

public class Ex1_ElectricityBill {
    public static void main(String[] args) {
        ElectricityBill obj = new ElectricityBill();
        obj.getData();
        obj.calculate();
        obj.display();
    }
}
