/**
 * A text field displaying a date formatted in ISO style.
 * 
 * @author Uno Holmer
 * @version 2010-09-16
 */
public class IsoDateView extends DateView {

    public IsoDateView() {
        super(10);
    }
    
    @Override
    protected String formatDate(int y,int m,int d) {
        return String.format("%04d",y) + "-" + 
               String.format("%02d",m) + "-" + 
               String.format("%02d",d);   
    }
}
