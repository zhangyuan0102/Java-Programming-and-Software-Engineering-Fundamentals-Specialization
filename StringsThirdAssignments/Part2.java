
/**
 * 在这里给出对类 Part2 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class Part2 {
    public double cgRatio(String dna) {
        int count = 0;
        for (int i = 0; i < dna.length(); i++) {
            char ch = dna.charAt(i);
            if (ch == 'C' || ch == 'G') {
                count++;
            }
        }
        return (double) count / dna.length();
    }

    public void testCgRatio() {
        String dna1 = "ATGCCATAG";
        System.out.println("DNA sequence: " + dna1);
        System.out.println("CG Ratio: " + cgRatio(dna1)); // Should return approximately 0.4444444

        String dna2 = "CGCGCGCG";
        System.out.println("DNA sequence: " + dna2);
        System.out.println("CG Ratio: " + cgRatio(dna2)); // Should return 1.0

        String dna3 = "ATATATAT";
        System.out.println("DNA sequence: " + dna3);
        System.out.println("CG Ratio: " + cgRatio(dna3)); // Should return 0.0
    }
    public int countCTG(String dna) {
        int count = 0;
        int index = dna.indexOf("CTG");
        while (index != -1) {
            count++;
            index = dna.indexOf("CTG", index + 3);
        }
        return count;
    }

    public void testCountCTG() {
        String dna1 = "CTGCTGCTG";
        System.out.println("DNA sequence: " + dna1);
        System.out.println("CTG count: " + countCTG(dna1)); // Should return 3

        String dna2 = "ATGCCATAGCTG";
        System.out.println("DNA sequence: " + dna2);
        System.out.println("CTG count: " + countCTG(dna2)); // Should return 1

        String dna3 = "ATATATAT";
        System.out.println("DNA sequence: " + dna3);
        System.out.println("CTG count: " + countCTG(dna3)); // Should return 0
    }

    public static void main(String[] args) {
        Part2 part2 = new Part2();
        part2.testCgRatio();
        part2.testCountCTG();
    }
}
