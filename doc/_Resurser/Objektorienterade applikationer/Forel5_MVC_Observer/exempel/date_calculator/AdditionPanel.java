/**
 * AdditionPanel implements the addition of days to a date model.
 * 
 * @author Uno Holmer 
 * @version 2010-09-24
 */
public class AdditionPanel extends AdditionSubtractionPanel {
    public AdditionPanel(String buttonText) {
        super(buttonText);
    }

    @Override
    protected void updateModel(long days) throws IllegalArgumentException {
        dateModel.add(days);
    }
}
