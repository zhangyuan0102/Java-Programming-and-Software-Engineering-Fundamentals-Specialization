
/**
 * 在这里给出对类 Part1 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class Part1 {
    public void findAbc(String input){
           int index = input.indexOf("abc");
           while (true){
               if (index == -1 || index >= input.length() - 3){
                   break;
               }
               System.out.println("Index: " + index);
               String found = input.substring(index+1, index+4);
               System.out.println(found);
               index = input.indexOf("abc",index+3);
               System.out.println("index after updating " + index);
           }
       }

   public void test(){
       //findAbc("abcd");
       findAbc("abcabcabcabca");
   }

    public static void main(String[] args) {
        Part1 part1 = new Part1();
        part1.test();
    }
}
