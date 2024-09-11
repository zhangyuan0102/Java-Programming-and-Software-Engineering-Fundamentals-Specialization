
/**
 * 在这里给出对类 CaesarCipher 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import edu.duke.FileResource;
public class CaesarCipher {
    // Method to encrypt a single character
    private char encryptChar(char ch, int key) {
        if (Character.isLetter(ch)) {
            char base = Character.isLowerCase(ch) ? 'a' : 'A';
            return (char) ((ch - base + key) % 26 + base);
        }
        return ch;
    }

    // Method to encrypt a string with a single key
    public String encrypt(String input, int key) {
        StringBuilder encrypted = new StringBuilder(input);
        for (int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            char newChar = encryptChar(currChar, key);
            encrypted.setCharAt(i, newChar);
        }
        return encrypted.toString();
    }

    // Method to encrypt a string with two keys
    public String encryptTwoKeys(String input, int key1, int key2) {
        StringBuilder encrypted = new StringBuilder(input);
        for (int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            if (i % 2 == 0) {
                char newChar = encryptChar(currChar, key1);
                encrypted.setCharAt(i, newChar);
            } else {
                char newChar = encryptChar(currChar, key2);
                encrypted.setCharAt(i, newChar);
            }
        }
        return encrypted.toString();
    }

    // Method to test the encryption with a single key
    public void testCaesar() {
        // Assuming you have a FileResource class that reads files
        FileResource fr = new FileResource();
        String message = fr.asString();
        int key = 23;
        String encrypted = encrypt(message, key);
        System.out.println("Key is " + key + "\n" + encrypted);
    }

    // Tester method
    public static void main(String[] args) {
        CaesarCipher cc = new CaesarCipher();

        // Testing encrypt method with a single key
        String encrypted1 = cc.encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 15);
        System.out.println("encrypt(\"FIRST LEGION ATTACK EAST FLANK!\", 23): " + encrypted1); // Expected "CFOPQ IBDFLK XQQXZH BXPQ CIXKH!"



        // Testing encryptTwoKeys method
        String encrypted4 = cc.encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21);
        System.out.println("encryptTwoKeys(\"First Legion\", 23, 17): " + encrypted4); // Expected "Czojq Ivdzle"
    }
}
