
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*; 
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setRandom(seed);
        markov.setTraining(text);
        
        System.out.println(markov.toString());
        for(int k=0; k < 3; k++){
            String st = markov.getRandomText(size);
            printOut(st);
        }
    }
    
    public void runMarkov() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        

        
        //MarkovZero mz = new MarkovZero();
        //runModel(mz, st, size, seed);
    
        //MarkovOne mOne = new MarkovOne();
        //runModel(mOne, st, 500, 365);
        
        //MarkovModel mThree = new MarkovModel(3);
        //runModel(mThree, st, size, seed);
        
        //MarkovFour mFour = new MarkovFour();
        //runModel(mFour, st, 500, 715);
        MarkovModel mm = new MarkovModel(7);
        runModel(mm, st, 500, 953);    
        
        
        //EfficientMarkovModel mFive = new EfficientMarkovModel(5);
        //runModel(mFive, st, size, seed);
    }

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println();
                psize = 0;
            }
        }
        System.out.println("\n----------------------------------");
    }
}
