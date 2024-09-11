
/**
 * 在这里给出对类 Part4 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import edu.duke.*;

public class Part4 {
    public static void youtubeLinks() {
        URLResource dukeurl = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
        for (String line : dukeurl.lines()) {
            int youtubeIndex = line.toLowerCase().indexOf("youtube.com");
            if (youtubeIndex != -1) {
                int quoteStart = line.lastIndexOf("\"", youtubeIndex);
                int quoteStop = line.indexOf("\"", youtubeIndex + 1);
                if (quoteStart != -1 && quoteStop != -1) {
                    System.out.println(line.substring(quoteStart + 1, quoteStop));
                }
            }
        }
    }

    public static void main(String[] args) {
        Part4 part4 = new Part4();
        part4.youtubeLinks();
    }
}
