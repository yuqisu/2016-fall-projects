
import java.io.*;
import java.util.*;
import java.util.Comparator;
import java.util.concurrent.ExecutionException;

/**
 * Created by yuqisu on 10/5/16.
 */
public class InfixCalculator {


       public static LinkedQueue<String> output = new LinkedQueue<String>();
       public static LinkedStacks<Operator> tokens = new LinkedStacks<Operator>();
        public static LinkedStacks<Double> calculate = new LinkedStacks<Double>();
        public static doubleCompare dc = new doubleCompare();
    public static String twoNumbers = "+-*/%<>&|^=";
    public static String oneNumber = "!sincostan()";

public static void main(String[]args){
    prefix("prefix.txt","result.txt");
    }
    public static void prefix(String infileName,String outfileName){

        try {

            Scanner sc = new Scanner(new File(infileName));
            PrintWriter out = new PrintWriter(new FileWriter(new File(outfileName)));
            while (sc.hasNext()) {
                String x = sc.nextLine();
                x=redoString(x);

                String[] inputs = x.split(" ");
                for (int i = 0; i < inputs.length; i++) {

                    if (isInteger(inputs[i])) {
                        output.enqueue(inputs[i]);
                    } else if (oneNumber.contains(inputs[i]) || twoNumbers.contains(inputs[i])){

                        Operator newOp = new Operator(inputs[i]);
                        Operator top = tokens.peek();

                        if (inputs[i].equals("(")){
                            tokens.push(newOp);}
                        else if (inputs[i].equals(")")){
                            while (top!=null && !top.operator().equals("(")){
                                output.enqueue(top.operator());
                                tokens.pop();
                                top = tokens.peek();

                            }
                            tokens.pop();

                        }else{
                            while (top != null && top.precedence()>newOp.precedence()) {
                            output.enqueue(top.operator());
                            tokens.pop();
                                top=tokens.peek();

                        }
                        tokens.push(newOp);

                        }
                }else{
                        out.println("unrecognizable token appears,please check the tokens ");

                        break;
                    }

                }


                while (tokens.peek()!=null){
                   output.enqueue(tokens.pop().operator());

              }

                output.printQueue();
                out.println(calculate(output));
                System.out.println();



            }

                sc.close();
                out.close();






            }catch(IOException e){

            }
        }



    public static double calculate(LinkedQueue<String> q){

        while (!q.isEmpty()){
            String c = q.peek();
           if (isInteger(c)){
               calculate.push(Double.parseDouble(q.dequeue()));
           }else if (twoNumbers.contains(q.peek())){
               double left = calculate.pop();
               double right = calculate.pop();
               if (q.peek().equals("+")){
                calculate.push(left+right);
               }
               if (q.peek().equals("-")){
                   calculate.push(right-left);
               }
               if (q.peek().equals("*")){
                   calculate.push(left*right);
               }
               if (q.peek().equals("/")){
                   calculate.push(right/left);
               }
               if (q.peek().equals("%")){
                   calculate.push(right%left);
               }
               if (q.peek().equals(">")){
                   double compare = dc.compare(left,right);
                   if (compare==-1){
                       calculate.push(1.0);
                   }else if(compare==1)
                       calculate.push(0.0);
               }
               if (q.peek().equals("<")){
                   double compare = dc.compare(left,right);
                   if (compare==1){
                       calculate.push(1.0);
                   }else if(compare==-1)
                       calculate.push(0.0);
               }
               if (q.peek().equals("=")){
                  double compare = dc.compare(left,right);
                   if (compare==0){
                       calculate.push(1.0);
                   }else
                       calculate.push(0.0);
               }
               if (q.peek().equals("&")){
                   if (left==1 &&right==1)
                   calculate.push(1.0);
                   else calculate.push(0.0);
               }
               if (q.peek().equals("|")){
                   if (left==1 || right==1)
                       calculate.push(1.0);
                   else calculate.push(0.0);
               }
               if (q.peek().equals("^")){
                  double h = Math.pow(right,left);
                   calculate.push(h);
               }
               q.dequeue();
           }else if (oneNumber.contains(q.peek())){
             double number = calculate.pop();
               if(q.peek().equals("!")){
                if (number==1){
                     calculate.push(0.0);
                   }else {
                    calculate.push(1.0);
                }
               }
               if(q.peek().equals("sin")){
                   calculate.push(Math.sin(number));
               }
               if(q.peek().equals("cos")){
                  calculate.push(Math.cos(number));
               }
               if(q.peek().equals("tan")){
                   calculate.push(Math.tan(number));
               }

               q.dequeue();
           }
        }
//        System.out.println(calculate.peek());

        return calculate.pop();
    }

    public static boolean isInteger(String x){
        try{
        Double c = Double.parseDouble(x);

        }
        catch(NumberFormatException nfe){
            return false;
        }
        return true;

    }
    public static String redoString(String x){
        x = x.replaceAll("\\(","( ");
        x = x.replaceAll("\\)"," )");
        x = x.replaceAll("!","! ");
        x = x.replaceAll("sin\\(","sin ( ");
        x = x.replaceAll("cos\\(","cos ( ");
        x = x.replaceAll("tan\\(","tan ( ");
        return x;
    }




}
 class Operator{
   private String operator;
     Operator(String op){
         operator = op;
     }
    public String operator(){
        return  operator;
    }
    public String toString(){
        return operator;
    }
     public int precedence(){
         if (operator.equals("("))
             return 1;
         else if (operator.equals("!"))
             return 9;
         else if (operator.equals("^") || operator.equals("sin") || operator.equals("cos") || operator.equals("tan"))
             return 8;
         else if (operator.equals("*")|| operator.equals("/") || operator.equals("%"))
             return 7;
         else if (operator.equals("+")|| operator.equals("-"))
             return 6;
         else if (operator.equals("<")|| operator.equals(">"))
             return 5;
         else if (operator.equals("="))
             return 4;
         else if (operator.equals("&"))
             return 3;
         else if (operator.equals("|")  )
             return 2;

         return -1;
     }
}
class doubleCompare<T extends Comparable<T>> implements Comparator<T>
{
    public int compare( T lhs, T rhs )
    {
        return lhs.compareTo( rhs );
    }
}