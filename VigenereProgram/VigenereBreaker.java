import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder result = new StringBuilder();
        for (int i = whichSlice; i < message.length(); i += totalSlices) {
            result.append(message.charAt(i));
        }
        return result.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cracker = new CaesarCracker(mostCommon);
        for (int i = 0; i < klength; i++) {
            String slice = sliceString(encrypted, i, klength);
            int k = cracker.getKey(slice);
            key[i] = k;
        }
        return key;
    }

    // Read dictionary file into a HashSet
    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> dictionary = new HashSet<>();
        for (String line : fr.lines()) {
            dictionary.add(line.toLowerCase());
        }
        return dictionary;
    }

    // Count valid words in the message
    public int countWords(String message, HashSet<String> dictionary) {
        String[] words = message.split("\\W+");
        int count = 0;
        for (String word : words) {
            if (dictionary.contains(word.toLowerCase())) {
                count++;
            }
        }
        return count;
    }
    public char mostCommonCharIn(HashSet<String> dictionary) {
        HashMap<Character, Integer> charCounts = new HashMap<>();
        for (String word : dictionary) {
            for (char c : word.toCharArray()) {
                if (Character.isLetter(c)) {
                    c = Character.toLowerCase(c);
                    charCounts.put(c, charCounts.getOrDefault(c, 0) + 1);
                }
            }
        }
        char mostCommon = ' ';
        int maxCount = 0;
        for (char c : charCounts.keySet()) {
            if (charCounts.get(c) > maxCount) {
                maxCount = charCounts.get(c);
                mostCommon = c;
            }
        }
        return mostCommon;
    }
    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
        int maxValidWords = 0;
        String bestDecryption = "";
        String bestLanguage = "";
        
        for (String language : languages.keySet()) {
            HashSet<String> dictionary = languages.get(language);
            String decrypted = breakForLanguage(encrypted, dictionary);
            int validWordCount = countWords(decrypted, dictionary);
            System.out.println("Valid words in " + language + ": " + validWordCount);
            if (validWordCount > maxValidWords) {
                maxValidWords = validWordCount;
                bestDecryption = decrypted;
                bestLanguage = language;
            }
        }
        
        System.out.println("Best language: " + bestLanguage);
        System.out.println("Decrypted message: ");
        System.out.println(bestDecryption);
    }    // Break Vigenere cipher for a given language using the dictionary
    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        int maxValidWords = 0;
        String bestDecryption = "";
        char mostCommonChar = mostCommonCharIn(dictionary);
        
        for (int klength = 1; klength <= 100; klength++) {
            int[] key = tryKeyLength(encrypted, klength, mostCommonChar);
            VigenereCipher vc = new VigenereCipher(key);
            String decrypted = vc.decrypt(encrypted);
            int validWordCount = countWords(decrypted, dictionary);
            if (validWordCount > maxValidWords) {
                maxValidWords = validWordCount;
                bestDecryption = decrypted;
            }
        }
        return bestDecryption;
    }

    // Break Vigenere cipher for unknown key length
    public void breakVigenere() {
        FileResource fr = new FileResource("secretmessage4.txt");
        String encrypted = fr.asString();
        
        String[] languages = {"Danish", "Dutch", "English", "French", "German", "Italian", "Portuguese", "Spanish"};
        HashMap<String, HashSet<String>> dictionaries = new HashMap<>();
        
        for (String language : languages) {
            FileResource frDictionary = new FileResource("dictionaries/" + language);
            HashSet<String> dictionary = readDictionary(frDictionary);
            dictionaries.put(language, dictionary);
            System.out.println("Read " + language + " dictionary.");
        }
        
        breakForAllLangs(encrypted, dictionaries);
    }

    public static void main(String[] args) {
        VigenereBreaker breaker = new VigenereBreaker();
        breaker.breakVigenere();
    }
}
