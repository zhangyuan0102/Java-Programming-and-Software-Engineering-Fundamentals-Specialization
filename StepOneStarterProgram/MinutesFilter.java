
/**
 * 在这里给出对接口 MinutesFilter 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class MinutesFilter implements Filter {
    private int minMinutes;
    private int maxMinutes;

    public MinutesFilter(int min, int max) {
        minMinutes = min;
        maxMinutes = max;
    }

    @Override
    public boolean satisfies(String id) {
        int minutes = MovieDatabase.getMinutes(id);
        return minutes >= minMinutes && minutes <= maxMinutes;
    }
}

