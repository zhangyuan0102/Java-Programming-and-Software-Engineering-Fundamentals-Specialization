
/**
 * 在这里给出对类 Part1 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class Part1 {

    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        while (currIndex != -1) {
            if ((currIndex - startIndex) % 3 == 0) {
                return currIndex;
            } else {
                currIndex = dna.indexOf(stopCodon, currIndex + 1);
            }
        }
        return dna.length();
    }

    public void testFindStopCodon() {
        // Test cases
        String dna1 = "xxxyyyzzzTAAxxxyyyzzz";
        System.out.println(findStopCodon(dna1, 0, "TAA")); // Should return 9

        String dna2 = "xxxyyyzzzTAATAGxxxyyyzzz";
        System.out.println(findStopCodon(dna2, 0, "TAG")); // Should return 12

        String dna3 = "xxxyyyzzzTAA";
        System.out.println(findStopCodon(dna3, 3, "TAA")); // Should return dna3.length() since no valid stopCodon
    }

    public String findGene(String dna) {
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) {
            return "";
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int minIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));
        if (minIndex == dna.length()) {
            return "";
        }
        return dna.substring(startIndex, minIndex + 3);
    }

    public void testFindGene() {
        // Test cases
        String dna1 = "AATGCTAACTAGCTGACTAAT";
        System.out.println("DNA sequence: " + dna1);
        System.out.println("Gene: " + findGene(dna1)); // Should return "ATGCTAA"

        String dna2 = "ATGCCCTAG";
        System.out.println("DNA sequence: " + dna2);
        System.out.println("Gene: " + findGene(dna2)); // Should return "ATGCCCTAG"

        String dna3 = "ATGCCCATAAGTAG";
        System.out.println("DNA sequence: " + dna3);
        System.out.println("Gene: " + findGene(dna3)); // Should return "ATGCCCATAAG"

        String dna4 = "ATGCCCCCCCC";
        System.out.println("DNA sequence: " + dna4);
        System.out.println("Gene: " + findGene(dna4)); // Should return ""

        String dna5 = "CCCCCCCCCCCC";
        System.out.println("DNA sequence: " + dna5);
        System.out.println("Gene: " + findGene(dna5)); // Should return ""
    }

    public void printAllGenes(String dna) {
        int startIndex = 0;
        while (true) {
            String currentGene = findGene(dna.substring(startIndex));
            if (currentGene.isEmpty()) {
                break;
            }
            System.out.println("Gene: " + currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
    }

    public static void main(String[] args) {
        Part1 part1 = new Part1();
        part1.testFindStopCodon();
        part1.testFindGene();

        String dna = "AATGCTAACTAGCTGACTAATATGCCCTAA";
        part1.printAllGenes(dna); // Should print all genes found in the DNA string
    }
}
