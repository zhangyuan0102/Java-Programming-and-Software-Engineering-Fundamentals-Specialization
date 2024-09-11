
/**
 * 在这里给出对类 MarkovWordTwo 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import java.util.ArrayList;
import java.util.Random;

public class MarkovWordTwo implements IMarkovModel {
    private String[] myText;
    private Random myRandom;

    public MarkovWordTwo() {
        myRandom = new Random();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text){
        myText = text.split("\\s+");
    }

    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length - 2);  // random word to start with
        String key1 = myText[index];
        String key2 = myText[index + 1];
        sb.append(key1).append(" ").append(key2).append(" ");
        for (int k = 0; k < numWords - 2; k++) {
            ArrayList<String> follows = getFollows(key1, key2);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next).append(" ");
            key1 = key2;
            key2 = next;
        }
        return sb.toString().trim();
    }

    private int indexOf(String[] words, String target1, String target2, int start) {
        for (int i = start; i < words.length - 1; i++) {
            if (words[i].equals(target1) && words[i + 1].equals(target2)) {
                return i;
            }
        }
        return -1;
    }

    public void testIndexOf() {
        String[] words = "this is just a test yes this is a simple test".split("\\s+");
        System.out.println(indexOf(words, "this", "is", 0)); // should print 0
        System.out.println(indexOf(words, "this", "is", 3)); // should print 6
        System.out.println(indexOf(words, "frog", "test", 0)); // should print -1
        System.out.println(indexOf(words, "frog", "test", 5)); // should print -1
        System.out.println(indexOf(words, "simple", "test", 2)); // should print 8
        System.out.println(indexOf(words, "a", "simple", 5)); // should print 7
    }

    private ArrayList<String> getFollows(String key1, String key2) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while (pos < myText.length - 1) {
            int start = indexOf(myText, key1, key2, pos);
            if (start == -1) {
                break;
            }
            if (start + 2 < myText.length) {
                follows.add(myText[start + 2]);
            }
            pos = start + 2;
        }
        return follows;
    }
}

