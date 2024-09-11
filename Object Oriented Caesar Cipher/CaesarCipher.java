
/**
 * 在这里给出对类 CaesarCipher 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;

    // Constructor that initializes the fields
    public CaesarCipher(int key) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        mainKey = key;
    }

    // Method to encrypt the input string
    public String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder(input);
        for (int i = 0; i < input.length(); i++) {
            char currChar = input.charAt(i);
            char newChar = currChar;
            if (Character.isUpperCase(currChar)) {
                int idx = alphabet.indexOf(currChar);
                if (idx != -1) {
                    newChar = shiftedAlphabet.charAt(idx);
                }
            } else if (Character.isLowerCase(currChar)) {
                int idx = alphabet.toLowerCase().indexOf(currChar);
                if (idx != -1) {
                    newChar = shiftedAlphabet.toLowerCase().charAt(idx);
                }
            }
            encrypted.setCharAt(i, newChar);
        }
        return encrypted.toString();
    }

    // Method to decrypt the input string
    public String decrypt(String input) {
        CaesarCipher cc = new CaesarCipher(26 - mainKey);
        return cc.encrypt(input);
    }
}
