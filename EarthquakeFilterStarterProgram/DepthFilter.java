
/**
 * 在这里给出对类 DepthFilter 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class DepthFilter implements Filter {
    private double minDepth;
    private double maxDepth;
    private String name;
    public DepthFilter(double min, double max) {
        minDepth = min;
        maxDepth = max;
        name = "";
    }
    public DepthFilter(double min, double max,String name) {
        minDepth = min;
        maxDepth = max;
        this.name = name;    
    }    
    @Override
    public boolean satisfies(QuakeEntry qe) {
        return qe.getDepth() >= minDepth && qe.getDepth() <= maxDepth;
    }
    @Override
    public String getName() {
        return name;
    }
}
