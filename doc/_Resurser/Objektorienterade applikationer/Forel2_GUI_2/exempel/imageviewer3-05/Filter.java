/**
 * Filter is an abstract interface for all image filters in this
 * application. Filters can be applied to OFImages by invoking the apply 
 * method.
 * 
 * @author Uno Holmer
 * @version 2.0
 */
public interface Filter
{
    void apply(OFImage image);
}
