public class Q2 {
    
    public static void main(String[] args){

        try{
            System.out.println("outer try ");

            try {
                System.out.println("inner try");

                int a=10;
                 int b=0;

                 System.out.println(a/b);
            }

            catch (ArithmeticException e){
                System.out.println("Inner catch");

            }
             int[] arr = {10, 20, 30};
            System.out.println(arr[5]);
        }
catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Outer catch: Invalid array index");
    }
}
}
