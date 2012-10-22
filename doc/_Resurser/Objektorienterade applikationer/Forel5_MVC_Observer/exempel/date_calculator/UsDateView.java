/**
 * A text field a date formatted in US style. 
 * 
 * @author Uno Holmer
 * @version 2010-09-16
 */
public class UsDateView extends DateView {

    public UsDateView() {
        super(8);
    }
    
    @Override
    protected String formatDate(int y,int m,int d) {
        return String.format("%02d",m) + "/" +
               String.format("%02d",d) + "/" + 
               String.format("%02d",y % 100);   
    }
}