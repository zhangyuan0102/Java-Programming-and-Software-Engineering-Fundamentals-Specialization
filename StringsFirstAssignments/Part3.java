
/**
 * 在这里给出对类 Part3 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class Part3 {
    // Method to check if stringa appears at least twice in stringb
    public boolean twoOccurrences(String stringa, String stringb) {
        int firstOccurrence = stringb.indexOf(stringa);
        if (firstOccurrence == -1) {
            return false; // stringa not found at all
        }
        int secondOccurrence = stringb.indexOf(stringa, firstOccurrence + stringa.length());
        return secondOccurrence != -1; // true if stringa appears a second time
    }

    // Method to find the part of stringb that follows the first occurrence of stringa
    public String lastPart(String stringa, String stringb) {
        int startIndex = stringb.indexOf(stringa);
        if (startIndex == -1) {
            return stringb; // stringa not found, return entire stringb
        }
        return stringb.substring(startIndex + stringa.length());
    }

    // Method to test twoOccurrences and lastPart methods
    public void testing() {
        // Test cases for twoOccurrences
        System.out.println("Testing twoOccurrences:");
        System.out.println("twoOccurrences(\"by\", \"A story by Abby Long\"): " + twoOccurrences("by", "A story by Abby Long"));
        System.out.println("twoOccurrences(\"a\", \"banana\"): " + twoOccurrences("a", "banana"));
        System.out.println("twoOccurrences(\"atg\", \"ctgtatgta\"): " + twoOccurrences("atg", "ctgtatgta"));
        System.out.println("twoOccurrences(\"an\", \"banana\"): " + twoOccurrences("an", "banana"));
        System.out.println("twoOccurrences(\"zoo\", \"forest\"): " + twoOccurrences("zoo", "forest"));

        // Test cases for lastPart
        System.out.println("\nTesting lastPart:");
        System.out.println("The part of the string after an in banana is: " + lastPart("an", "banana"));
        System.out.println("The part of the string after zoo in forest is: " + lastPart("zoo", "forest"));
        System.out.println("The part of the string after by in A story by Abby Long is: " + lastPart("by", "A story by Abby Long"));
        System.out.println("The part of the string after na in banana is: " + lastPart("na", "banana"));
        System.out.println("The part of the string after atg in ctgtatgta is: " + lastPart("atg", "ctgtatgta"));
    }
    
    // Main method to run the tests
    public static void main(String[] args) {
        Part3 part3 = new Part3();
        part3.testing();
    }
}
