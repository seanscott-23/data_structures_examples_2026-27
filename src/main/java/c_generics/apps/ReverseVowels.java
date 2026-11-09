package c_generics.apps;

import c_generics.utils.GenericComposedStack;

import java.util.ArrayList;
import java.util.Arrays;

public class ReverseVowels {
    static void main(String[] args) {
        String text = "Baking powder";
        String result = reverseVowels(text);
        System.out.println("Reversing the vowels in \"" + text + "\" produces \"" + result + "\"");
    }

    public static String reverseVowels(String input){
        GenericComposedStack<Character> stack = new GenericComposedStack<>();
        input = input.toLowerCase();

        ArrayList<Character> vowels = new ArrayList<>(
                Arrays.asList('a', 'e', 'i', 'o', 'u')
        );

        StringBuilder text = new StringBuilder();
        // Detect and store vowels in a stack (for reverse order access later)
        for (int i = 0; i < input.length(); i++) {
            char currentCharacter = input.charAt(i);
            if(vowels.contains(currentCharacter)){
                stack.push(currentCharacter);
            }
        }

        // Rebuild text, retaining non-vowels in order. Where a vowel is identified,
        // it is replaced with one from the stack, rebuilding them in reverse order
        for (int i = 0; i < input.length(); i++) {
            char currentCharacter = input.charAt(i);
            if(vowels.contains(currentCharacter)){
                text.append(stack.pop());
            }else{
                text.append(currentCharacter);
            }
        }

        return text.toString();
    }
}
