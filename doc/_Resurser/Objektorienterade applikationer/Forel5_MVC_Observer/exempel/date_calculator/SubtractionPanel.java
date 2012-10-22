/**
 * AdditionPanel implements the subtraction of days from a date model.
 * 
 * @author Uno Holmer 
 * @version 2010-09-24
 */
public class SubtractionPanel extends AdditionSubtractionPanel {
    public SubtractionPanel(String buttonText) {
        super(buttonText);
    }

    @Override
    protected void updateModel(long days)throws IllegalArgumentException {
        dateModel.subtract(days);
    }
}