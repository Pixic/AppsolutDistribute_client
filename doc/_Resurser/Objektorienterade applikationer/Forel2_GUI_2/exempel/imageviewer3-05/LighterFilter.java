/**
 * An image filter to make the image a bit lighter.
 * 
 * @author Michael Kolling and David J Barnes 
 * @version 1.0
 */
public class LighterFilter implements Filter
{
    /**
     * Apply this filter to an image.
     * 
     * @param  image  The image to be changed by this filter.
     */
    public void apply(OFImage image)
    {
        int height = image.getHeight();
        int width = image.getWidth();
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                image.setPixel(x, y, image.getPixel(x, y).brighter());
            }
        }
    }

}
