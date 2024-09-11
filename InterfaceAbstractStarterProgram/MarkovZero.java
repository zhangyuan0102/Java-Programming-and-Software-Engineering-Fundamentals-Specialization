
/**
 * 在这里给出对类 MarkovZero 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import java.util.Random;

public class MarkovZero extends AbstractMarkovModel {

    public String getRandomText(int numChars) {
        if (myText == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < numChars; k++) {
            int index = myRandom.nextInt(myText.length());
            sb.append(myText.charAt(index));
        }
        return sb.toString();
    }
    public String toString() {
        return "MarkovModel of order 0.";
    }
}


