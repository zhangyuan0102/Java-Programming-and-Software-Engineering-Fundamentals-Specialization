
/**
 * 在这里给出对类 Part2 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class Part2 {

    public int howMany(String stringa, String stringb) {
        int count = 0;
        int startIndex = 0;
        while (true) {
            int index = stringb.indexOf(stringa, startIndex);
            if (index == -1) {
                break;
            }
            count++;
            startIndex = index + stringa.length();
        }
        return count;
    }

    public void testHowMany() {
        // Test cases
        System.out.println(howMany("GAA", "ATGAACGAATTGAATC")); // Should return 3
        System.out.println(howMany("AA", "ATAAAA")); // Should return 2
        System.out.println(howMany("A", "AAA")); // Should return 3
        System.out.println(howMany("AB", "ABABABAB")); // Should return 4
        System.out.println(howMany("A", "BBBB")); // Should return 0
    }

    public static void main(String[] args) {
        Part2 part2 = new Part2();
        part2.testHowMany();
    }
}