
/**
 * 在这里给出对类 CaesarCipherTwo 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainKey1;
    private int mainKey2;

    // Constructor that initializes the fields
    public CaesarCipherTwo(int key1, int key2) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        mainKey1 = key1;
        mainKey2 = key2;
    }

    // Method to encrypt the input string
    public String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder(input);
        for (int i = 0; i < input.length(); i++) {
            char currChar = input.charAt(i);
            char newChar = currChar;
            if (i % 2 == 0) { // Use key1 for every other character starting with the first
                if (Character.isUpperCase(currChar)) {
                    int idx = alphabet.indexOf(currChar);
                    if (idx != -1) {
                        newChar = shiftedAlphabet1.charAt(idx);
                    }
                } else if (Character.isLowerCase(currChar)) {
                    int idx = alphabet.toLowerCase().indexOf(currChar);
                    if (idx != -1) {
                        newChar = shiftedAlphabet1.toLowerCase().charAt(idx);
                    }
                }
            } else { // Use key2 for every other character starting with the second
                if (Character.isUpperCase(currChar)) {
                    int idx = alphabet.indexOf(currChar);
                    if (idx != -1) {
                        newChar = shiftedAlphabet2.charAt(idx);
                    }
                } else if (Character.isLowerCase(currChar)) {
                    int idx = alphabet.toLowerCase().indexOf(currChar);
                    if (idx != -1) {
                        newChar = shiftedAlphabet2.toLowerCase().charAt(idx);
                    }
                }
            }
            encrypted.setCharAt(i, newChar);
        }
        return encrypted.toString();
    }

    // Method to decrypt the input string
    public String decrypt(String input) {
        CaesarCipherTwo cc = new CaesarCipherTwo(26 - mainKey1, 26 - mainKey2);
        return cc.encrypt(input);
    }
}

