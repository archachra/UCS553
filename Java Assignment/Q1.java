 public class Q1 {
    public static void main(String[] args) {

    try {
        int a = 10;
          int b = 0;
            System.out.println(a/ b);
        }
              catch (ArithmeticException e) {
            System.out.println("ArithmeticException: Can't divide by zero");
        }
           try {
            String s = null;
            System.out.println(s.length());
        }
          catch (NullPointerException e) {
            System.out.println("NullPointerException: String is null");
        }
 
          try {
            int[] arr = {10, 20, 30};
              System.out.println(arr[5]);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ArrayIndexOutOfBoundsException: Invalid index");
        }
        try {
            String number = "abc";
                int n = Integer.parseInt(number);
              System.out.println(n);
        }
        catch (NumberFormatException e) {
            System.out.println("NumberFormatException: Invalid number format");
        }
    }
}