
/**
 * 在这里给出对类 Part1 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */

public class Part1 {
    
    // Method to find the gene in the DNA string
    public String findSimpleGene(String dna) {
        System.out.println("Finding gene in DNA: " + dna); // Debug output
        // Find the index of the start codon "ATG"
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) {
            return ""; // No "ATG" found, return empty string
        }
        
        // Find the index of the stop codon "TAA" after "ATG"
        int stopIndex = dna.indexOf("TAA", startIndex + 3);
        if (stopIndex == -1) {
            return ""; // No "TAA" found, return empty string
        }
        
        // Check if the length of the substring between "ATG" and "TAA" is a multiple of 3
        if ((stopIndex - startIndex) % 3 == 0) {
            return dna.substring(startIndex, stopIndex + 3); // Return the gene
        } else {
            return ""; // Length is not a multiple of 3, return empty string
        }
    }

    // Method to test the findSimpleGene method with various DNA strings
    public void testSimpleGene() {
        // Define test cases
        String dna1 = "AAGTCCGTAACGTAAGC"; // No "ATG"
        String dna2 = "AAGTCCATGCGTGC"; // No "TAA"
        String dna3 = "AGTCCGTAACGTAAGC"; // No "ATG" or "TAA"
        String dna4 = "AAGTCCATGCGTTAAGC"; // "ATG", "TAA" and the substring between is a multiple of 3
        String dna5 = "AAGTCCATGCCTAGTAA"; // "ATG", "TAA" and the substring between is not a multiple of 3
        
        // Test each DNA string
        System.out.println("Testing DNA strings:"); // Debug output

        System.out.println("DNA string: " + dna1);
        System.out.println("Gene: " + findSimpleGene(dna1));
        
        System.out.println("DNA string: " + dna2);
        System.out.println("Gene: " + findSimpleGene(dna2));
        
        System.out.println("DNA string: " + dna3);
        System.out.println("Gene: " + findSimpleGene(dna3));
        
        System.out.println("DNA string: " + dna4);
        System.out.println("Gene: " + findSimpleGene(dna4));
        
        System.out.println("DNA string: " + dna5);
        System.out.println("Gene: " + findSimpleGene(dna5));
    }
    
    // Main method to run the test
    public static void main(String[] args) {
        Part1 part1 = new Part1();
        part1.testSimpleGene();
    }
}
