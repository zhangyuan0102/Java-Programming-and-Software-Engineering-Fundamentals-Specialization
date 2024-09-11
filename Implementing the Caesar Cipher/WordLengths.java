
/**
 * 在这里给出对类 wordLengths 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import edu.duke.FileResource;

public class WordLengths {
    
    // Method to count word lengths in a file
    public void countWordLengths(FileResource resource, int[] counts) {
        for (String word : resource.words()) {
            int wordLength = getWordLength(word);
            if (wordLength >= counts.length) {
                counts[counts.length - 1]++;
            } else {
                counts[wordLength]++;
            }
        }
    }

    // Helper method to get the length of a word, ignoring punctuation
    private int getWordLength(String word) {
        int length = 0;
        for (int i = 0; i < word.length(); i++) {
            if (Character.isLetter(word.charAt(i)) || (i > 0 && i < word.length() - 1)) {
                length++;
            }
        }
        return length;
    }

    // Method to find the index of the maximum value in an array
    public int indexOfMax(int[] values) {
        int maxIndex = 0;
        for (int i = 1; i < values.length; i++) {
            if (values[i] > values[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    // Method to test countWordLengths and print results
    public void testCountWordLengths() {
        FileResource fr = new FileResource();
        int[] counts = new int[31]; // array to hold counts of word lengths from 0 to 30
        countWordLengths(fr, counts);
        
        // Print the number of words of each length
        for (int i = 1; i < counts.length; i++) {
            if (counts[i] > 0) {
                System.out.println("Number of words of length " + i + ": " + counts[i]);
            }
        }

        // Find and print the most common word length
        int maxIndex = indexOfMax(counts);
        System.out.println("The most common word length in the file is " + maxIndex);
    }

    // Main method for testing
    public static void main(String[] args) {
        WordLengths wl = new WordLengths();
        wl.testCountWordLengths();
    }
}
