
/**
 * 在这里给出对类 TitleLastAndMagnitudeComparator 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import java.util.Comparator;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
    @Override
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        // Extract the last word from the titles
        String lastWord1 = getLastWord(q1.getInfo());
        String lastWord2 = getLastWord(q2.getInfo());

        int titleCompare = lastWord1.compareTo(lastWord2);
        if (titleCompare != 0) {
            return titleCompare;
        }
        // If last words are the same, compare by magnitude
        return Double.compare(q1.getMagnitude(), q2.getMagnitude());
    }

    private String getLastWord(String title) {
        String[] words = title.split("\\s+");
        return words[words.length - 1];
    }
}

