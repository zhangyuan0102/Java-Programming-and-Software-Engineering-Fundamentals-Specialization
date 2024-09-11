 
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.ArrayList;
public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        //MarkovWordOne markovWord = new MarkovWordOne(); 
        //runModel(markovWord, st, 200); 
    } 

    public void runMarkovWord() {
        FileResource fr = new FileResource("data/confucius.txt");
        String st = fr.asString();
        st = st.replace('\n', ' '); 
        MarkovWord mw = new MarkovWord(5);
        runModel(mw, st, 200, 844);
    }

    private int getTotalWordCount(ArrayList<Integer> wordsPerLine) {
        int total = 0;
        for (int count : wordsPerLine) {
            total += count;
        }
        return total;
    }

    private void printFormattedText(String text, ArrayList<Integer> wordsPerLine) {
        String[] words = text.split("\\s+");
        int wordIndex = 0;

        for (int count : wordsPerLine) {
            for (int i = 0; i < count; i++) {
                if (wordIndex < words.length) {
                    System.out.print(words[wordIndex] + " ");
                    wordIndex++;
                }
            }
            System.out.println();
        }
    }
    public void testHashMap() {
        FileResource fr = new FileResource("data/confucius.txt");
        String st = fr.asString();
        st = st.replace('\n', ' ');         
        EfficientMarkovWord markov = new EfficientMarkovWord(3); // order 2
        markov.setTraining(st);
        markov.setRandom(371);
        markov.printHashMapInfo();
        System.out.println(markov.getRandomText(50));
    }

    public void testHashMap2() {
        FileResource fr = new FileResource("data/confucius.txt");
        String st = fr.asString();
        st = st.replace('\n', ' '); 
        
        EfficientMarkovWord markov = new EfficientMarkovWord(2); // order 2
        markov.setTraining(st);
        markov.setRandom(65);
        markov.printHashMapInfo();
        System.out.println(markov.getRandomText(50));
    }

    public void compareMethods() {
        FileResource fr = new FileResource("data/hawthorne.txt");
        String text = fr.asString();
        text = text.replace('\n', ' ');

        MarkovWord markovWord = new MarkovWord(2);
        markovWord.setTraining(text);
        markovWord.setRandom(42);

        EfficientMarkovWord efficientMarkovWord = new EfficientMarkovWord(2);
        efficientMarkovWord.setTraining(text);
        efficientMarkovWord.setRandom(42);

        int numWords = 100;
        System.out.println("Running MarkovWord...");
        long start = System.nanoTime();
        for (int i = 0; i < 3; i++) {
            System.out.println(markovWord.getRandomText(numWords));
        }
        long end = System.nanoTime();
        System.out.println("Time taken for MarkovWord: " + (end - start) / 1e9 + " seconds");

        System.out.println("Running EfficientMarkovWord...");
        start = System.nanoTime();
        for (int i = 0; i < 3; i++) {
            System.out.println(efficientMarkovWord.getRandomText(numWords));
        }
        end = System.nanoTime();
        System.out.println("Time taken for EfficientMarkovWord: " + (end - start) / 1e9 + " seconds");
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
