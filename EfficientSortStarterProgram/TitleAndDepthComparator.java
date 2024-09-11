
/**
 * 在这里给出对类 TitleAndDepthComparator 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import java.util.Comparator;

public class TitleAndDepthComparator implements Comparator<QuakeEntry> {
    @Override
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        int titleCompare = q1.getInfo().compareTo(q2.getInfo());
        if (titleCompare != 0) {
            return titleCompare;
        }
        return Double.compare(q1.getDepth(), q2.getDepth());
    }
}
