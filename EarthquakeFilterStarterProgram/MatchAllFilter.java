
/**
 * 在这里给出对类 MatchAllFilter 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import java.util.ArrayList;

public class MatchAllFilter implements Filter {
    private ArrayList<Filter> filters;

    public MatchAllFilter() {
        filters = new ArrayList<>();
    }

    public void addFilter(Filter f) {
        filters.add(f);
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {
        for (Filter f : filters) {
            if (!f.satisfies(qe)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String getName() {
        StringBuilder names = new StringBuilder();
        for (Filter f : filters) {
            if (names.length() > 0) {
                names.append(", ");
            }
            names.append(f.getName());
        }
        return names.toString();
    }
}

