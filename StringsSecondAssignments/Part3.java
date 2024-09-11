
/**
 * 在这里给出对类 Part3 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class Part3 {

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

    public int countGenes(String dna) {
        int startIndex = 0;
        int count = 0;
        while (true) {
            String currentGene = findGene(dna.substring(startIndex));
            if (currentGene.isEmpty()) {
                break;
            }
            count++;
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
        return count;
    }

    public void testCountGenes() {
        // 测试用例
        String dna1 = "ATGTAAGATGCCCTAGT";
        System.out.println("DNA sequence: " + dna1);
        System.out.println("Number of genes: " + countGenes(dna1)); // 应该返回 2

        String dna2 = "ATGCCCTAGATGAAATGA";
        System.out.println("DNA sequence: " + dna2);
        System.out.println("Number of genes: " + countGenes(dna2)); // 应该返回 1

        String dna3 = "ATGAAATGAAAATGAAATGAAA";
        System.out.println("DNA sequence: " + dna3);
        System.out.println("Number of genes: " + countGenes(dna3)); // 应该返回 3

        String dna4 = "ATGATGATGTAA";
        System.out.println("DNA sequence: " + dna4);
        System.out.println("Number of genes: " + countGenes(dna4)); // 应该返回 1

        String dna5 = "TAAATGTAGATGTGA";
        System.out.println("DNA sequence: " + dna5);
        System.out.println("Number of genes: " + countGenes(dna5)); // 应该返回 2
    }

    public static void main(String[] args) {
        Part3 part3 = new Part3();
        part3.testCountGenes();
    }
}