
/**
 * 在这里给出对类 MarkovOne 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import java.util.ArrayList;
import java.util.Random;
import java.util.ArrayList;
import java.util.Random;

import java.util.ArrayList;
import java.util.Random;

public class MarkovOne {
    private String myText;
    private Random myRandom;

    public MarkovOne() {
        myRandom = new Random();
    }

    public void setTraining(String s) {
        myText = s.trim();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<>();
        int pos = 0;
        while (pos < myText.length() - key.length()) {
            int start = myText.indexOf(key, pos);
            if (start == -1) {
                break;
            }
            int index = start + key.length();
            if (index >= myText.length()) {
                break;
            }
            String next = myText.substring(index, index + 1);
            follows.add(next);
            pos = start + 1;
        }
        return follows;
    }

    public String getRandomText(int numChars) {
        if (myText == null || myText.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length() - 1);
        String key = myText.substring(index, index + 1);
        sb.append(key);
        for (int k = 0; k < numChars - 1; k++) {
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = next;
        }
        return sb.toString();
    }
}
