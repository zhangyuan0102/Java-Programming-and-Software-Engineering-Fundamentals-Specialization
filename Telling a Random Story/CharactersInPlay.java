
/**
 * 在这里给出对类 CharactersInPlay 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import java.util.ArrayList;
import edu.duke.FileResource;

public class CharactersInPlay {
    private ArrayList<String> characters;
    private ArrayList<Integer> counts;

    public CharactersInPlay() {
        characters = new ArrayList<String>();
        counts = new ArrayList<Integer>();
    }

    private void update(String person) {
        int index = characters.indexOf(person);
        if (index == -1) {
            characters.add(person);
            counts.add(1);
        } else {
            int value = counts.get(index);
            counts.set(index, value + 1);
        }
    }

    public void findAllCharacters() {
        characters.clear();
        counts.clear();
        
        FileResource fr = new FileResource("errors.txt");
        
        for (String line : fr.lines()) {
            int periodIndex = line.indexOf(".");
            if (periodIndex != -1) {
                String person = line.substring(0, periodIndex).trim();
                update(person);
            }
        }
    }

    public void tester() {
        findAllCharacters();
        for (int i = 0; i < characters.size(); i++) {
            if (counts.get(i) > 1) {
                System.out.println(characters.get(i) + "\t" + counts.get(i));
            }
        }
        
        charactersWithNumParts(10, 15);  // Example usage
    }

    public void charactersWithNumParts(int num1, int num2) {
        System.out.println("Characters with number of speaking parts between " + num1 + " and " + num2 + ":");
        for (int i = 0; i < characters.size(); i++) {
            if (counts.get(i) >= num1 && counts.get(i) <= num2) {
                System.out.println(characters.get(i) + "\t" + counts.get(i));
            }
        }
    }

    public static void main(String[] args) {
        CharactersInPlay cip = new CharactersInPlay();
        cip.tester();
    }
}

