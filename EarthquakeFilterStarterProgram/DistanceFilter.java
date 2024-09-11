
/**
 * 在这里给出对类 DistanceFilter 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class DistanceFilter implements Filter {
    private Location location;
    private double maxDistance;
    private String name;
    public DistanceFilter(Location loc, double maxDist) {
        location = loc;
        maxDistance = maxDist;
        name = "";
    }
    public DistanceFilter(Location loc, double maxDist,String name) {
        location = loc;
        maxDistance = maxDist;
        this.name = name;       
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {
        return qe.getLocation().distanceTo(location) < maxDistance;
    }
    @Override
    public String getName() {
        return name;
    }    
}
