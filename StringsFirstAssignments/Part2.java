
/**
 * 在这里给出对类 Part2 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */

public class Part2 {
    
    // Method to find the gene in the DNA string with given start and stop codons
    public String findSimpleGene(String dna, String startCodon, String stopCodon) {
        // Convert DNA to uppercase to handle case insensitivity
        String dnaUpper = dna.toUpperCase();
        String startCodonUpper = startCodon.toUpperCase();
        String stopCodonUpper = stopCodon.toUpperCase();
        
        // Find the index of the start codon
        int startIndex = dnaUpper.indexOf(startCodonUpper);
        if (startIndex == -1) {
            return ""; // No start codon found, return empty string
        }
        
        // Find the index of the stop codon after the start codon
        int stopIndex = dnaUpper.indexOf(stopCodonUpper, startIndex + 3);
        if (stopIndex == -1) {
            return ""; // No stop codon found, return empty string
        }
        
        // Check if the length of the substring between start codon and stop codon is a multiple of 3
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
        
        // Test each DNA string with specific start and stop codons
        System.out.println("Testing DNA strings with 'ATG' and 'TAA':");

        System.out.println("DNA string: " + dna1);
        System.out.println("Gene: " + findSimpleGene(dna1, "ATG", "TAA"));
        
        System.out.println("DNA string: " + dna2);
        System.out.println("Gene: " + findSimpleGene(dna2, "ATG", "TAA"));
        
        System.out.println("DNA string: " + dna3);
        System.out.println("Gene: " + findSimpleGene(dna3, "ATG", "TAA"));
        
        System.out.println("DNA string: " + dna4);
        System.out.println("Gene: " + findSimpleGene(dna4, "ATG", "TAA"));
        
        System.out.println("DNA string: " + dna5);
        System.out.println("Gene: " + findSimpleGene(dna5, "ATG", "TAA"));
        
        // Test cases with lowercase DNA strings
        String dna6 = "gatgctataat"; // "atg", "taa" and the substring between is a multiple of 3
        String dna7 = "gatgcgctataa"; // "atg", "taa" and the substring between is not a multiple of 3

        System.out.println("Testing DNA strings with 'atg' and 'taa':");
        
        System.out.println("DNA string: " + dna6);
        System.out.println("Gene: " + findSimpleGene(dna6, "atg", "taa"));
        
        System.out.println("DNA string: " + dna7);
        System.out.println("Gene: " + findSimpleGene(dna7, "atg", "taa"));
    }
    
    // Main method to run the test
    public static void main(String[] args) {
        Part2 part2 = new Part2();
        part2.testSimpleGene();
    }
}