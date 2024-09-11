
/**
 * 在这里给出对类 WordsInFiles 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import edu.duke.*;
import java.io.File;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

public class WordsInFiles {
    private HashMap<String, ArrayList<String>> wordFileMap;

    public WordsInFiles() {
        wordFileMap = new HashMap<String, ArrayList<String>>();
    }

    private void addWordsFromFile(File f) {
        FileResource fr = new FileResource(f);
        String fileName = f.getName();
        for (String word : fr.words()) {
            if (!wordFileMap.containsKey(word)) {
                wordFileMap.put(word, new ArrayList<String>());
            }
            ArrayList<String> fileList = wordFileMap.get(word);
            if (!fileList.contains(fileName)) {
                fileList.add(fileName);
            }
        }
    }

    public void buildWordFileMap() {
        wordFileMap.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }

    public int maxNumber() {
        int max = 0;
        for (ArrayList<String> fileList : wordFileMap.values()) {
            if (fileList.size() > max) {
                max = fileList.size();
            }
        }
        return max;
    }

    public ArrayList<String> wordsInNumFiles(int number) {
        ArrayList<String> words = new ArrayList<String>();
        for (Map.Entry<String, ArrayList<String>> entry : wordFileMap.entrySet()) {
            if (entry.getValue().size() == number) {
                words.add(entry.getKey());
            }
        }
        return words;
    }

    public void printFilesIn(String word) {
        ArrayList<String> fileList = wordFileMap.get(word);
        if (fileList != null) {
            for (String fileName : fileList) {
                System.out.println(fileName);
            }
        } else {
            System.out.println("The word \"" + word + "\" does not appear in any file.");
        }
    }

    public void tester() {
        buildWordFileMap();
        
        // 问题1：多少单词出现在五个文件中？
        ArrayList<String> wordsInFiveFiles = wordsInNumFiles(7);
        System.out.println("Number of words that occur in five files: " + wordsInFiveFiles.size());
        
        // 问题2：多少单词出现在四个文件中？
        ArrayList<String> wordsInFourFiles = wordsInNumFiles(4);
        System.out.println("Number of words that occur in four files: " + wordsInFourFiles.size());
        
        // 问题3：哪个文件中没有出现单词 "sad"？
        System.out.println("The word 'laid' does NOT appear in:");
        ArrayList<String> filesWithSad = wordFileMap.get("laid");
        String[] allFiles = {"caesar.txt", "confucius.txt","errors.txt","hamlet.txt", "likeit.txt", "macbeth.txt", "romeo.txt"};
        for (String file : allFiles) {
            if (filesWithSad == null || !filesWithSad.contains(file)) {
                System.out.println(file);
            }
        }
        
        // 问题4：单词 "red" 出现在哪些文件中？
        System.out.println("The word 'tree' appears in:");
        printFilesIn("tree");

        // Optional: Print the entire map for debugging
    }

    public static void main(String[] args) {
        WordsInFiles wif = new WordsInFiles();
        wif.tester();
    }
}

