
/**
 * 在这里给出对类 EfficientMarkovModel 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class EfficientMarkovModel extends AbstractMarkovModel {
    private int n;
    private HashMap<String, ArrayList<String>> followMap;

    public EfficientMarkovModel(int n) {
        this.n = n;
        myRandom = new Random();
        followMap = new HashMap<>();
    }

    @Override
    public void setTraining(String s) {
        myText = s.trim();
        buildMap();
        printHashMapInfo();
    }

    private void buildMap() {
        followMap.clear();
        for (int i = 0; i <= myText.length() - n; i++) {
            String key = myText.substring(i, i + n);
            String follow = "";
            if (i + n < myText.length()) {
                follow = myText.substring(i + n, i + n + 1);
            }
            followMap.putIfAbsent(key, new ArrayList<>());
            followMap.get(key).add(follow);
        }
    printHashMapInfo(); // For debugging, comment out after verifying
    }

    @Override
    protected ArrayList<String> getFollows(String key) {
        return followMap.getOrDefault(key, new ArrayList<>());
    }

    public String getRandomText(int numChars) {
        if (myText == null || myText.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length() - n);
        String key = myText.substring(index, index + n);
        sb.append(key);
        for (int k = 0; k < numChars - n; k++) {
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }
        return sb.toString();
    }
    public String toString() {
        return "EfficientMarkovModel of order " + n;
    }
    private int getLargestValue() {
        int max = Integer.MIN_VALUE;
        
        for (String key : followMap.keySet()) {
            if (followMap.get(key).size() > max) {
                max = followMap.get(key).size();
            }
        }
        
        return max;
    }
    
    private ArrayList<String> getLargestKey() {
        int max = getLargestValue();
        ArrayList<String> keyList = new ArrayList<String>();
        
        for (String key : followMap.keySet()) {
            if (followMap.get(key).size() == max) {
                keyList.add(key);
            }
        }
        
        return keyList;
    }

    public void printHashMapInfo() {
        for (String key : followMap.keySet()) {
            System.out.println(key + " - " + followMap.get(key));
        }
        
        ArrayList<String> largestKeyList = getLargestKey();
        
        System.out.println("# of keys = " + followMap.size());
        System.out.println("largest value = " + followMap.get(largestKeyList.get(0)).size());
        for (String key : largestKeyList) {
            System.out.println("largest key = " + key);
        }
        
    }
}


