
/**
 * 在这里给出对类 Tester 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Tester {
    public void testGetFollows() {
        MarkovOne markov = new MarkovOne();
        markov.setTraining("this is a test yes this is a test.");
        ArrayList<String> follows = markov.getFollows("t");
        System.out.println(follows);
        System.out.println(follows.size());
        
        follows = markov.getFollows("e");
        System.out.println(follows);
        System.out.println(follows.size());
        
        follows = markov.getFollows("es");
        System.out.println(follows);
        System.out.println(follows.size());
        
        follows = markov.getFollows(".");
        System.out.println(follows);
        System.out.println(follows.size());
        
        follows = markov.getFollows("t.");
        System.out.println(follows);
        System.out.println(follows.size());
    }
    
    public void testGetFollowsWithFile() {
        try {
            File file = new File("data/confucius.txt");
            Scanner scanner = new Scanner(file);
            StringBuilder sb = new StringBuilder();
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine()).append(" ");
            }
            scanner.close();
            
            // Set the training text
            MarkovOne markov = new MarkovOne();
            markov.setTraining(sb.toString().trim());
            
            // Get follows for the string "o"
            ArrayList<String> follows = markov.getFollows("he");
            System.out.println("Number of characters that follow 'he': " + follows.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        Tester tester = new Tester();
        tester.testGetFollowsWithFile();
    }
}

