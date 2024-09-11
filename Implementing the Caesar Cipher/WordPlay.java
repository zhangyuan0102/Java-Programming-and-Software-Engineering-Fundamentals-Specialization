
/**
 * 在这里给出对类 Word_Play 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class WordPlay {
    // Method to check if a character is a vowel
    public boolean isVowel(char ch) {
        ch = Character.toLowerCase(ch);
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }

    // Method to replace vowels in a phrase with a specified character
    public String replaceVowels(String phrase, char ch) {
        StringBuilder result = new StringBuilder(phrase);
        for (int i = 0; i < result.length(); i++) {
            if (isVowel(result.charAt(i))) {
                result.setCharAt(i, ch);
            }
        }
        return result.toString();
    }

    // Method to emphasize characters in a phrase based on their position
    public String emphasize(String phrase, char ch) {
        StringBuilder result = new StringBuilder(phrase);
        for (int i = 0; i < result.length(); i++) {
            char currentChar = result.charAt(i);
            if (Character.toLowerCase(currentChar) == Character.toLowerCase(ch)) {
                if (i % 2 == 0) { // Odd location (index 0 is the first character)
                    result.setCharAt(i, '*');
                } else { // Even location
                    result.setCharAt(i, '+');
                }
            }
        }
        return result.toString();
    }

    // Tester method
    public static void main(String[] args) {
        WordPlay wp = new WordPlay();
        
        // Testing isVowel method
        System.out.println("isVowel('F'): " + wp.isVowel('F')); // Expected false
        System.out.println("isVowel('a'): " + wp.isVowel('a')); // Expected true

        // Testing replaceVowels method
        String replacedVowels = wp.replaceVowels("Hello World", '*');
        System.out.println("replaceVowels(\"Hello World\", '*'): " + replacedVowels); // Expected "H*ll* W*rld"

        // Testing emphasize method
        String emphasized = wp.emphasize("dna ctgaaactga", 'a');
        System.out.println("emphasize(\"dna ctgaaactga\", 'a'): " + emphasized); // Expected "dn* ctg+*+ctg+"
        emphasized = wp.emphasize("Mary Bella Abracadabra", 'a');
        System.out.println("emphasize(\"Mary Bella Abracadabra\", 'a'): " + emphasized); // Expected "M+ry Bell+ +br*c*d*br+"
    }
}
