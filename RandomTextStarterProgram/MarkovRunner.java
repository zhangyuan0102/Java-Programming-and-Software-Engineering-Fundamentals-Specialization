
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
import java.util.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class MarkovRunner {

    public void runMarkovOne() {
        FileResource fr = new FileResource(); 
        String st = fr.asString();    
        MarkovOne markov = new MarkovOne();
        markov.setTraining(st);
        markov.setRandom(365);
        String text = markov.getRandomText(500);

        // Print only the first line of the generated text
        if (text.contains("\n")) {
            text = text.substring(0, text.indexOf("\n"));
        }
        System.out.println(text);
    }
    public void runMarkovZero() {
        FileResource fr = new FileResource(); 
        String st = fr.asString();  
        st = st.replace('\n', ' ');         
        MarkovZero markov = new MarkovZero();
        markov.setTraining(st);
        markov.setRandom(1024);
        String text = markov.getRandomText(500);

        // Print only the first line of the generated text
        if (text.contains("\n")) {
            text = text.substring(0, text.indexOf("\n"));
        }
        System.out.println(text);
    }
    private String readFileAsString(String fileName) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName));
            return String.join("\n", lines);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    
    public void runMarkovFour() {
        FileResource fr = new FileResource(); 
        String st = fr.asString();  
        MarkovFour markov = new MarkovFour();
        markov.setTraining(st);
        markov.setRandom(715);
        String text = markov.getRandomText(500);

        // Print only the first line of the generated text
        if (text.contains("\n")) {
            text = text.substring(0, text.indexOf("\n"));
        }
        System.out.println(text);
    }
    
    public void runMarkovModel() {
        FileResource fr = new FileResource(); 
        String st = fr.asString();  
        MarkovModel markov = new MarkovModel(7);
        markov.setTraining(st);
        markov.setRandom(953);
        String text = markov.getRandomText(500);

        // Print only the first line of the generated text
        if (text.contains("\n")) {
            text = text.substring(0, text.indexOf("\n"));
        }
        System.out.println(text);
    }
    
    public static void main(String[] args) {
        MarkovRunner runner = new MarkovRunner();
        runner.runMarkovOne();
        runner.runMarkovFour();
        runner.runMarkovModel();
    }
}
