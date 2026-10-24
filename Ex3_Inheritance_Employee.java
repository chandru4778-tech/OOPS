import java.util.*;

class Employee {
    String emp_name;
    int emp_id;
    String address;
    String mail_id;
    String mob_number;

    Employee(String emp_name, int emp_id, String address, String mail_id, String mob_number) {
        this.emp_name = emp_name;
        this.emp_id = emp_id;
        this.address = address;
        this.mail_id = mail_id;
        this.mob_number = mob_number;
    }

    void display() {
        System.out.println("Employee Name : " + emp_name);
        System.out.println("Employee ID : " + emp_id);
        System.out.println("Address : " + address);
        System.out.println("Mail ID : " + mail_id);
        System.out.println("Mobile Number : " + mob_number);
    }
}

class Programmer extends Employee {
    double basicpay, da, hra, pf, fund, grosssalary, netsalary;

    Programmer(String emp_name, int emp_id, String address, String mail_id, String mob_number, double bp) {
        super(emp_name, emp_id, address, mail_id, mob_number);
        basicpay = bp;
    }

    void getPaySlip() {
        da = basicpay * 97 / 100;
        hra = basicpay * 10 / 100;
        pf = basicpay * 12 / 100;
        fund = basicpay * 0.1 / 100;
        grosssalary = basicpay + da + hra + pf + fund;
        netsalary = grosssalary - pf - fund;
    }

    void disp() {
        System.out.println("\n***** PROGRAMMER PAY SLIP *****");
        display();
        System.out.println("Basic Pay : " + basicpay);
        System.out.println("Gross Salary : " + grosssalary);
        System.out.println("Net Salary : " + netsalary);
    }
}

class AssistantProfessor extends Employee {
    double basicpay, da, hra, pf, fund, grosssalary, netsalary;

    AssistantProfessor(String emp_name, int emp_id, String address, String mail_id, String mob_number, double bp) {
        super(emp_name, emp_id, address, mail_id, mob_number);
        basicpay = bp;
    }

    void getPaySlip() {
        da = basicpay * 97 / 100;
        hra = basicpay * 10 / 100;
        pf = basicpay * 12 / 100;
        fund = basicpay * 0.1 / 100;
        grosssalary = basicpay + da + hra + pf + fund;
        netsalary = grosssalary - pf - fund;
    }

    void disp() {
        System.out.println("\n***** ASSISTANT PROFESSOR PAY SLIP *****");
        display();
        System.out.println("Basic Pay : " + basicpay);
        System.out.println("Gross Salary : " + grosssalary);
        System.out.println("Net Salary : " + netsalary);
    }
}

class AssociateProfessor extends Employee {
    double basicpay, da, hra, pf, fund, grosssalary, netsalary;

    AssociateProfessor(String emp_name, int emp_id, String address, String mail_id, String mob_number, double bp) {
        super(emp_name, emp_id, address, mail_id, mob_number);
        basicpay = bp;
    }

    void getPaySlip() {
        da = basicpay * 97 / 100;
        hra = basicpay * 10 / 100;
        pf = basicpay * 12 / 100;
        fund = basicpay * 0.1 / 100;
        grosssalary = basicpay + da + hra + pf + fund;
        netsalary = grosssalary - pf - fund;
    }

    void disp() {
        System.out.println("\n***** ASSOCIATE PROFESSOR PAY SLIP *****");
        display();
        System.out.println("Basic Pay : " + basicpay);
        System.out.println("Gross Salary : " + grosssalary);
        System.out.println("Net Salary : " + netsalary);
    }
}

class Professor extends Employee {
    double basicpay, da, hra, pf, fund, grosssalary, netsalary;

    Professor(String emp_name, int emp_id, String address, String mail_id, String mob_number, double bp) {
        super(emp_name, emp_id, address, mail_id, mob_number);
        basicpay = bp;
    }

    void getPaySlip() {
        da = basicpay * 97 / 100;
        hra = basicpay * 10 / 100;
        pf = basicpay * 12 / 100;
        fund = basicpay * 0.1 / 100;
        grosssalary = basicpay + da + hra + pf + fund;
        netsalary = grosssalary - pf - fund;
    }

    void disp() {
        System.out.println("\n***** PROFESSOR PAY SLIP *****");
        display();
        System.out.println("Basic Pay : " + basicpay);
        System.out.println("Gross Salary : " + grosssalary);
        System.out.println("Net Salary : " + netsalary);
    }
}

public class Ex3_Inheritance_Employee {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter Employee Name: ");
        String name = in.nextLine();
        System.out.print("Enter Employee ID: ");
        int id = in.nextInt();
        in.nextLine();
        System.out.print("Enter Address: ");
        String add = in.nextLine();
        System.out.print("Enter Mail ID: ");
        String mail = in.nextLine();
        System.out.print("Enter Mobile Number: ");
        String mob = in.nextLine();
        System.out.print("Enter Basic Pay: ");
        double bp = in.nextDouble();

        System.out.println("\nChoose Designation");
        System.out.println("1. Programmer");
        System.out.println("2. Assistant Professor");
        System.out.println("3. Associate Professor");
        System.out.println("4. Professor");
        System.out.println("5. Exit");
        System.out.print("Enter Choice: ");
        int desg = in.nextInt();

        switch (desg) {
            case 1:
                Programmer p = new Programmer(name, id, add, mail, mob, bp);
                p.getPaySlip();
                p.disp();
                break;
            case 2:
                AssistantProfessor ap = new AssistantProfessor(name, id, add, mail, mob, bp);
                ap.getPaySlip();
                ap.disp();
                break;
            case 3:
                AssociateProfessor asp = new AssociateProfessor(name, id, add, mail, mob, bp);
                asp.getPaySlip();
                asp.disp();
                break;
            case 4:
                Professor pr = new Professor(name, id, add, mail, mob, bp);
                pr.getPaySlip();
                pr.disp();
                break;
            case 5:
                System.out.println("Program Exited.");
                break;
            default:
                System.out.println("Invalid Choice!");
        }
        in.close();
    }
}
