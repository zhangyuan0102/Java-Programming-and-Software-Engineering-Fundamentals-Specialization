
/**
 * 在这里给出对类 TestCaesarCipherTwo 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import edu.duke.FileResource;

public class TestCaesarCipherTwo {

    // Method to count letter frequencies
    public int[] countLetters(String message) {
        int[] counts = new int[26];
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < message.length(); i++) {
            char ch = Character.toLowerCase(message.charAt(i));
            int index = alphabet.indexOf(ch);
            if (index != -1) {
                counts[index]++;
            }
        }
        return counts;
    }

    // Method to find the index of the maximum value in an array
    public int maxIndex(int[] values) {
        int maxIndex = 0;
        for (int i = 1; i < values.length; i++) {
            if (values[i] > values[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    // Method to get half of the string starting from a specific position
    public String halfOfString(String message, int start) {
        StringBuilder half = new StringBuilder();
        for (int i = start; i < message.length(); i += 2) {
            half.append(message.charAt(i));
        }
        return half.toString();
    }

    // Method to find the key used to encrypt a string
    public int getKey(String s) {
        int[] freqs = countLetters(s);
        int maxDex = maxIndex(freqs);
        int dKey = maxDex - 4;
        if (maxDex < 4) {
            dKey = 26 - (4 - maxDex);
        }
        return dKey;
    }

    // Method to break the Caesar cipher by determining the keys
    public String breakCaesarCipher(String input) {
        String half1 = halfOfString(input, 0);
        String half2 = halfOfString(input, 1);

        int key1 = getKey(half1);
        int key2 = getKey(half2);

        System.out.println("Key 1: " + key1);
        System.out.println("Key 2: " + key2);

        CaesarCipherTwo cc = new CaesarCipherTwo(key1, key2);
        return cc.decrypt(input);
    }

    // Simple test method
    public void simpleTests() {
        //FileResource fr = new FileResource();
        //String message = fr.asString();
        CaesarCipherTwo cc = new CaesarCipherTwo(21,8);
        String encrypted = cc.encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?");
        System.out.println("Encrypted: " + encrypted);

        String decrypted = cc.decrypt(encrypted);
        System.out.println("Decrypted: " + decrypted);

        String autoDecrypted = breakCaesarCipher(encrypted);
        System.out.println("Automatically Decrypted: " + autoDecrypted);
    }

    // Main method for testing
    public static void main(String[] args) {
        TestCaesarCipherTwo tester = new TestCaesarCipherTwo();
        tester.simpleTests();
    }
}

