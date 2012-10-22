/**
 * A text field displaying a date formatted in Swedish style. 
 * 
 * @author Uno Holmer
 * @version 2010-09-16
 */
public class SwedishDateView extends DateView {

    public SwedishDateView() {
        super(17);
    }
    
    private final static String monthNames[] =
        {"foo","Januari","Februari","Mars","April",
         "Maj","Juni","Juli","Augusti","September",
         "Oktober","November","December"};
         
    @Override
    protected String formatDate(int y,int m,int d) {
        return d + " " + monthNames[m] + " " + y;   
    }
}