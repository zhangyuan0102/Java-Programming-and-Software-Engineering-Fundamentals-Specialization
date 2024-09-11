import java.util.Arrays;

public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);

    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt " + index);
        }
        return myWords[index];
    }

    public int length() {
        return myWords.length;
    }

    public String toString() {
        return String.join(" ", myWords);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WordGram wordGram = (WordGram) o;
        return Arrays.equals(myWords, wordGram.myWords);
    }

    @Override
    public int hashCode() {
        if (myHash == 0) {
            myHash = Arrays.hashCode(myWords);
        }
        return myHash;
    }

    public WordGram shiftAdd(String word) {
        String[] newWords = new String[myWords.length];
        System.arraycopy(myWords, 1, newWords, 0, myWords.length - 1);
        newWords[myWords.length - 1] = word;
        return new WordGram(newWords, 0, newWords.length);
    }

    public static void main(String[] args) {
        String[] words = {"this", "is", "just", "a", "test"};
        WordGram wg = new WordGram(words, 0, 4);
        System.out.println(wg); // should print: this is just a

        WordGram wg2 = wg.shiftAdd("yes");
        System.out.println(wg2); // should print: is just a yes

        System.out.println(wg.length()); // should print: 4
        System.out.println(wg.wordAt(2)); // should print: just

        System.out.println(wg.equals(wg2)); // should print: false

        WordGram wg3 = new WordGram(words, 0, 4);
        System.out.println(wg.equals(wg3)); // should print: true
    }
}
