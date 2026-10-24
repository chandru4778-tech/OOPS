import java.io.*;

interface Mystack {
    void push();
    void pop();
    void display();
}

class Stack_array implements Mystack {
    final int n = 5;
    int stack[] = new int[n];
    int top = -1;

    public void push() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            if (top == n - 1) {
                System.out.println("Stack Overflow");
                return;
            }
            System.out.print("Enter the element: ");
            int ele = Integer.parseInt(br.readLine());
            stack[++top] = ele;
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void pop() {
        if (top == -1) {
            System.out.println("Stack Underflow");
            return;
        }
        System.out.println("Popped Element: " + stack[top]);
        top--;
    }

    public void display() {
        if (top == -1) {
            System.out.println("Stack is Empty");
            return;
        }
        System.out.println("Stack Elements:");
        for (int i = top; i >= 0; i--) {
            System.out.println(stack[i]);
        }
    }
}

public class Ex5_StackException {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack_array stk = new Stack_array();
        int ch;
        do {
            System.out.println("\n*** STACK USING ARRAY ***");
            System.out.println("1. Push");
            System.out.println("2. Pop");
            System.out.println("3. Display");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            ch = Integer.parseInt(br.readLine());
            switch (ch) {
                case 1:
                    stk.push();
                    break;
                case 2:
                    stk.pop();
                    break;
                case 3:
                    stk.display();
                    break;
                case 4:
                    System.out.println("Program Ended");
                    break;
                default:
                    System.out.println("Invalid Choice");
            }
        } while (ch != 4);
    }
}
