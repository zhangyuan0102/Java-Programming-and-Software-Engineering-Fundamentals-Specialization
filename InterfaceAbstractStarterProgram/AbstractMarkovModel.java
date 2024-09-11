
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */


import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
    
    protected ArrayList<String> getFollows(String key) {
        ArrayList<String> result = new ArrayList<String>();
        int startPos = 0;
        int keyLength = key.length();
        int textLength = myText.length();
    
        while (true) {
            int index = myText.indexOf(key, startPos);
        
        // Check if the key is found and there's at least one character after the key
            if (index == -1 || index + keyLength >= textLength) {
                break;
            }
        
        // Add the character after the key to the result list
            result.add(myText.substring(index + keyLength, index + keyLength + 1));
        
        // Update startPos to the position right after the current key
            startPos = index + keyLength;
        }
    
        return result;
    }
    abstract public String getRandomText(int setSize);
}

