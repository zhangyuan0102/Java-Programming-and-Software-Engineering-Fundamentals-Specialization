
/**
 * 在这里给出对类 EfficientMarkovWord 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class EfficientMarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<WordGram, ArrayList<String>> myMap;

    public EfficientMarkovWord(int order) {
        myOrder = order;
        myRandom = new Random();
        myMap = new HashMap<>();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text) {
        myText = text.split("\\s+");
        buildMap();
    }

    private void buildMap() {
        for (int i = 0; i <= myText.length - myOrder; i++) {
            WordGram key = new WordGram(myText, i, myOrder);
            String follow = (i + myOrder < myText.length) ? myText[i + myOrder] : null;

            if (!myMap.containsKey(key)) {
                myMap.put(key, new ArrayList<>());
            }
            if (follow != null) {
                myMap.get(key).add(follow);
            }
        }
    }

    private ArrayList<String> getFollows(WordGram kGram) {
        return myMap.getOrDefault(kGram, new ArrayList<>());
    }

    public void printHashMapInfo() {
        if (myMap.size() <= 10) {
            for (Map.Entry<WordGram, ArrayList<String>> entry : myMap.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }
        }
        System.out.println("Number of keys: " + myMap.size());

        int maxSize = 0;
        for (ArrayList<String> follows : myMap.values()) {
            if (follows.size() > maxSize) {
                maxSize = follows.size();
            }
        }
        System.out.println("Largest value size: " + maxSize);

        for (Map.Entry<WordGram, ArrayList<String>> entry : myMap.entrySet()) {
            if (entry.getValue().size() == maxSize) {
                System.out.println("Key with max size: " + entry.getKey());
            }
        }
    }

    public String getRandomText(int numWords) {
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length - myOrder);  // random word to start with
        WordGram key = new WordGram(myText, index, myOrder);
        sb.append(key.toString());
        sb.append(" ");
        for (int k = 0; k < numWords - myOrder; k++) {
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = key.shiftAdd(next);
        }
        return sb.toString().trim();
    }
}

