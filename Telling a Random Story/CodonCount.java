
/**
 * 在这里给出对类 CodonCount 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import edu.duke.*;
import java.util.HashMap;
import java.util.Map;

public class CodonCount {
    private HashMap<String, Integer> codonMap;

    public CodonCount() {
        codonMap = new HashMap<String, Integer>();
    }

    public void buildCodonMap(int start, String dna) {
        codonMap.clear();
        dna = dna.toUpperCase().trim();
        for (int i = start; i < dna.length() - 2; i += 3) {
            String codon = dna.substring(i, i + 3);
            if (codonMap.containsKey(codon)) {
                codonMap.put(codon, codonMap.get(codon) + 1);
            } else {
                codonMap.put(codon, 1);
            }
        }
    }

    public String getMostCommonCodon() {
        int maxCount = 0;
        String mostCommonCodon = "";
        for (Map.Entry<String, Integer> entry : codonMap.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostCommonCodon = entry.getKey();
            }
        }
        return mostCommonCodon;
    }

    public void printCodonCounts(int start, int end) {
        for (Map.Entry<String, Integer> entry : codonMap.entrySet()) {
            int count = entry.getValue();
            if (count >= start && count <= end) {
                System.out.println(entry.getKey() + " " + count);
            }
        }
    }

    public void tester() {
        FileResource fr = new FileResource();
        String dna = fr.asString();
        dna = dna.trim();

        for (int frame = 0; frame < 3; frame++) {
            buildCodonMap(frame, dna);
            System.out.println("Reading frame starting with " + frame + " results in " + codonMap.size() + " unique codons");
            String mostCommonCodon = getMostCommonCodon();
            System.out.println("and most common codon is " + mostCommonCodon + " with count " + codonMap.get(mostCommonCodon));
            System.out.println("Counts of codons between 1 and 5 inclusive are:");
            printCodonCounts(1, 7);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        CodonCount cc = new CodonCount();
        cc.tester();
    }
}

