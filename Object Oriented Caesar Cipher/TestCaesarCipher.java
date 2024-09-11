
/**
 * 在这里给出对类 TestCaesarCipher 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import edu.duke.FileResource;
public class TestCaesarCipher {

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

    // Method to break the Caesar cipher by determining the key
    public String breakCaesarCipher(String input) {
        int[] freqs = countLetters(input);
        int maxDex = maxIndex(freqs);
        int dKey = maxDex - 4;
        if (maxDex < 4) {
            dKey = 26 - (4 - maxDex);
        }
        CaesarCipher cc = new CaesarCipher(dKey);
        return cc.decrypt(input);
    }

    // Simple test method
    public void simpleTests() {
        //FileResource fr = new FileResource();
        //String message = fr.asString();
        CaesarCipher cc = new CaesarCipher(15);
        String encrypted = cc.encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?");
        System.out.println("Encrypted: " + encrypted);

        String decrypted = cc.decrypt(encrypted);
        System.out.println("Decrypted: " + decrypted);

        String autoDecrypted = breakCaesarCipher(encrypted);
        System.out.println("Automatically Decrypted: " + autoDecrypted);
    }

    // Main method for testing
    public static void main(String[] args) {
        TestCaesarCipher tester = new TestCaesarCipher();
        tester.simpleTests();
    }
}

