import java.util.Scanner;


public class Submission {
    private static Stack stack = new Stack();
    private static StackInt newValues = new StackInt();
    
    public static void main(String[] args) {

        Scanner console = new Scanner(System.in);
        String inputLine = console.nextLine();
        Scanner in = new Scanner(inputLine);

        String input = in.nextLine();
        String[] inputToArray = input.split(" ");

        for (String value: inputToArray) {
            stack.push(value);
        }
        
        if (stack.getSize() == 1) {
            int result = Integer.parseInt(stack.pop());
            System.out.println(result);

        }else if(stack.getSize() == 3) {
            String operator = "";

            int size = stack.getSize();

            for (int i = 0; i < size  + 1; i++){
                String currentStr = stack.peek();

                if (checkOperator(currentStr)) {
                    operator = currentStr;
                } else {
                    stack.pop();
                    newValues.push(Integer.parseInt(currentStr));
                }
            }

            int numberOne = newValues.pop();
            int numberTwo = newValues.pop();   
            int result = calculate(numberOne, numberTwo, operator);

            System.out.println(result);

        } else if (stack.getSize() > 3) {
            int finalResult = 0;

            int size = stack.getSize();
            for (int i = 0; i < size; i++){
                String currentStr = stack.peek();


                if (checkOperator(currentStr)) {
                    int numberOne = newValues.pop();
                    int numberTwo = newValues.pop();  
                    int currentResult = calculate(numberOne, numberTwo, currentStr);
                    stack.pop();
                    newValues.push(currentResult);

                    finalResult = currentResult;


                } else {
                    stack.pop();
                    newValues.push(Integer.parseInt(currentStr));
                }

            }
            System.out.println(finalResult);
            


        }


    }

    public static boolean checkOperator(String value) {
        if(value.equals("-") || value.equals("+")
        || value.equals("x")){
            return true;
        }
        return false;
    }

    public static int calculate(int numberOne, int numberTwo, String operator) {

        int result = 0;
        if (operator.equals("x")) {

            result = numberOne * numberTwo;

        } else if (operator.equals("+")) {
            result = numberOne + numberTwo;
        } else if (operator.equals("-")) {
            result = numberOne - numberTwo;
        }
        return result;
    }



    private static class Stack{
        private class Node{
            String value;
            Node next;
        }

        private Node head;
        private int size = 0;

        public void push(String y){
            Node x = new Node();
            x.value =y;
            x.next = head;
            head = x;
            size++;
        }

        public String pop(){
            Node x = head;
            head = head.next;
            size--;
            return x.value ;

        }

        public int getSize(){
            return size;
        }

        public String peek(){
            Node x = head;
            return x.value;
        }
    }


    public static class StackInt{
        private class Node{
            int value ;
            Node next;
        }
        private Node head;
        private int size = 0;
        public void push(int y){
            Node x = new Node();
            x.value = y;
            x.next = head;
            head = x;
            size ++;
        }
         
       public int pop(){
           Node x = head;
           head = head.next;
           size --;
           return x.value ;
        }
        
        public int getSize(){
            return size;
        }
        public int peek(){
            Node x = head;
            return x.value;
        }
    }

}