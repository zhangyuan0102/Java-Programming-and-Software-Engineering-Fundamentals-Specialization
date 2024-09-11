
/**
 * Write a description of class MinMaxFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MinMagFilter implements Filter
{
    private double minMag;

    public MinMagFilter(double min) {
        minMag = min;
    }

    public boolean satisfies(QuakeEntry qe) {
        return qe.getMagnitude() >= minMag; 
    }
}
