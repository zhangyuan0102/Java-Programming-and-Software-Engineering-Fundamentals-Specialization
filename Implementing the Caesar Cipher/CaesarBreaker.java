
/**
 * 在这里给出对类 CaesarBreaker 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import edu.duke.FileResource;
public class CaesarBreaker {
    // Method to count the frequency of each letter in the string
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

    // Method to decrypt a string with a single key
    public String decrypt(String encrypted, int key) {
        CaesarCipher cc = new CaesarCipher();
        return cc.encrypt(encrypted, 26 - key);
    }

    // Method to test decryption with a single key
    public void testDecrypt() {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        int key = getKey(encrypted);
        String decrypted = decrypt(encrypted, key);
        System.out.println("Decrypted message: " + decrypted);
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

    // Method to decrypt a string encrypted with two keys
    public String decryptTwoKeys(String encrypted) {
        String half1 = halfOfString(encrypted, 0);
        String half2 = halfOfString(encrypted, 1);

        int key1 = getKey(half1);
        int key2 = getKey(half2);

        System.out.println("Key 1: " + key1);
        System.out.println("Key 2: " + key2);

        CaesarCipher cc = new CaesarCipher();
        return cc.encryptTwoKeys(encrypted, 26 - key1, 26 - key2);
    }

    // Main method for testing
    public void decryptFile(String filename) {
        FileResource fr = new FileResource(filename);
        String encrypted = fr.asString();
        String decrypted = decryptTwoKeys(encrypted);

        System.out.println("Decrypted message: " + decrypted);

        //Print the first five words of the decrypted message
        String[] words = decrypted.split("\\s+");
        System.out.println("First five words: ");
        for (int i = 0; i < 5 && i < words.length; i++) {
            System.out.print(words[i] + " ");
        }
        System.out.println();
    }

    // Main method for testing
    public static void main(String[] args) {
        CaesarBreaker cb = new CaesarBreaker();

        // Decrypt the mysteryTwoKeysPractice.txt file and print the first five words
        cb.decryptFile("mysteryTwoKeysQuiz.txt");
    }
}
