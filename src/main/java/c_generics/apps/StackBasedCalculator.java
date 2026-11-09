package c_generics.apps;

import c_generics.utils.GenericComposedStack;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class StackBasedCalculator {
    public static final ArrayList<String> OPERATORS = new ArrayList(Arrays.asList("+", "-", "*", "/"));

    static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your equation (it will be processed in reverse order!) Values and operations should" +
                " be separated with spaces.");
        System.out.println("Equations should not include parentheses");
        String equation = input.nextLine();

        double result = evaluateEquation(equation);
        System.out.println(equation + " = " + result);

    }

    public static double evaluateEquation(String equation){
        String [] components = equation.split(" ");
        GenericComposedStack<String> equationOperators = new GenericComposedStack<>();
        GenericComposedStack<Double> values = new GenericComposedStack<>();

        for (String component : components) {
            if(OPERATORS.contains(component)){
                equationOperators.push(component);
            }else{
                double value = Double.parseDouble(component);
                values.push(value);
            }
        }

        double result = values.pop();
        while(!equationOperators.isEmpty()){
            String operator = equationOperators.pop();
            double value = values.pop();
            switch(operator){
                case "+" -> result = result + value;
                case "-" -> result = result - value;
                case "*" -> result = result * value;
                case "/" -> result = result / value;
            }
        }

        return result;
    }
}
