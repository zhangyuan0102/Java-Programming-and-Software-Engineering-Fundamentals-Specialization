
/**
 * 在这里给出对类 MagnitudeFilter 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class MagnitudeFilter implements Filter {
    private double minMag;
    private double maxMag;
    private String name;
    public MagnitudeFilter(double min, double max) {
        minMag = min;
        maxMag = max;
        name = "";
    }
    public MagnitudeFilter(double min, double max,String name) {
        minMag = min;
        maxMag = max;
        this.name = name;
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {
        return qe.getMagnitude() >= minMag && qe.getMagnitude() <= maxMag;
    }

    @Override
    public String getName() {
        return name;
    }
}
