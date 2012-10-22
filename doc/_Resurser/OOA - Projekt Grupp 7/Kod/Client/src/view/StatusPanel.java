package view;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

@SuppressWarnings("serial")
/**
 * Is the statuspanel for the chatprogram Sheepchat
 * 
 * @author Tommy Kindmark, Cristian Troncoso
 * @version 1.0
 */
public class StatusPanel extends JPanel {

	private ArrayList<JTextField> textFields;

	/**
	 * Constructs a statuspanel
	 * 
	 * @param cells
	 */
	public StatusPanel(int cells) {
		setLayout(new GridLayout(0, cells));
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		textFields = new ArrayList<JTextField>();
		for (int i = 0; i < cells; i++) {
			JTextField textField = new JTextField();
			textField.setEditable(false);
			textFields.add(i, textField);
			add(textField);
		}
	}

	/**
	 * Writes the information about the status on the panel
	 * 
	 * @param pos
	 * @param text
	 */
	public void setText(int pos, String text) {
		if (pos >= 0 && pos < textFields.size())
			textFields.get(pos).setText(text);
	}

}
