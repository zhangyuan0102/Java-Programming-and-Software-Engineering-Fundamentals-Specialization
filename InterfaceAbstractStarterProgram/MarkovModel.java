
/**
 * 在这里给出对类 MarkovModel 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import java.util.ArrayList;
import java.util.Random;

public class MarkovModel extends AbstractMarkovModel {
    private int charsToPredict;
    
    public MarkovModel(int numChars) {
        charsToPredict = numChars;
    }
    
    public String toString() {
        return "MarkovModel of order " + charsToPredict;
    }
    
    public String getRandomText(int setSize){
        if (myText == null){
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-charsToPredict);
        String key = myText.substring(index, index+charsToPredict);
        sb.append(key);
        
        for(int k=0; k < setSize-charsToPredict-1; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            
            int indexFollows = myRandom.nextInt(follows.size());
            String next = follows.get(indexFollows);
            sb.append(next);
            key = key.substring(1) + next;
        }
        
        return sb.toString();
    }
}


