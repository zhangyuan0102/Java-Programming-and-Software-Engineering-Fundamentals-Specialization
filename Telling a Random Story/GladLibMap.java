
/**
 * 在这里给出对类 GlaLib 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import edu.duke.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Map;

public class GladLibMap {
    private HashMap<String, ArrayList<String>> myMap;
    private ArrayList<String> usedWords;
    private ArrayList<String> usedCategories;
    private Random myRandom;

    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";

    public GladLibMap() {
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }

    public GladLibMap(String source) {
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(source);
        myRandom = new Random();
    }

    private void initializeFromSource(String source) {
        String[] categories = {"adjective", "noun", "color", "country", "name", "animal", "timeframe", "verb", "fruit"};
        for (String category : categories) {
            ArrayList<String> list = readIt(source + "/" + category + ".txt");
            myMap.put(category, list);
        }
        usedWords = new ArrayList<String>();
        usedCategories = new ArrayList<String>();
    }

    private String randomFrom(ArrayList<String> source) {
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    private String getSubstitute(String label) {
        if (myMap.containsKey(label)) {
            if (!usedCategories.contains(label)) {
                usedCategories.add(label);
            }
            return randomFrom(myMap.get(label));
        }
        if (label.equals("number")) {
            return "" + myRandom.nextInt(50) + 5;
        }
        return "**UNKNOWN**";
    }

    private String processWord(String w) {
        int first = w.indexOf("<");
        int last = w.indexOf(">", first);
        if (first == -1 || last == -1) {
            return w;
        }
        String prefix = w.substring(0, first);
        String suffix = w.substring(last + 1);
        String sub = getSubstitute(w.substring(first + 1, last));

        while (usedWords.contains(sub)) {
            sub = getSubstitute(w.substring(first + 1, last));
        }
        usedWords.add(sub);

        return prefix + sub + suffix;
    }

    private void printOut(String s, int lineWidth) {
        int charsWritten = 0;
        for (String w : s.split("\\s+")) {
            if (charsWritten + w.length() > lineWidth) {
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w + " ");
            charsWritten += w.length() + 1;
        }
        System.out.println();
    }

    private String fromTemplate(String source) {
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for (String word : resource.words()) {
                story = story + processWord(word) + " ";
            }
        } else {
            FileResource resource = new FileResource(source);
            for (String word : resource.words()) {
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }

    private ArrayList<String> readIt(String source) {
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for (String line : resource.lines()) {
                list.add(line);
            }
        } else {
            FileResource resource = new FileResource(source);
            for (String line : resource.lines()) {
                list.add(line);
            }
        }
        return list;
    }

    public void makeStory() {
        usedWords.clear();
        usedCategories.clear();
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\nTotal number of words replaced: " + usedWords.size());
        System.out.println("Total words in map: " + totalWordsInMap());
        System.out.println("Total words considered: " + totalWordsConsidered());
    }

    private int totalWordsInMap() {
        int total = 0;
        for (ArrayList<String> list : myMap.values()) {
            total += list.size();
        }
        return total;
    }

    private int totalWordsConsidered() {
        int total = 0;
        for (String category : usedCategories) {
            total += myMap.get(category).size();
        }
        return total;
    }

    public static void main(String[] args) {
        GladLibMap gl = new GladLibMap();
        gl.makeStory();
    }
}


