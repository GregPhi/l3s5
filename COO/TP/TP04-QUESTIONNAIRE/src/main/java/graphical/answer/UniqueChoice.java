/**
 * PROJECT QUESTIONNARY
 * @author : Hugot Jean-Michel and Philippot Gregoire
 */

package graphical.answer;

import java.awt.GridLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class UniqueChoice<A> extends JPanel{

	//ATRIBUTS
	/**/
	private static final long serialVersionUID = 5568167692289559199L;
	/*choices*/
	private Map<JRadioButton, A> cores;
	/*button*/
	private ButtonGroup Bgroup;

	//CONSTRUCTOR
	/**
	 * Create an unique choice
	 * @param choices : (type-List) list of choices
	 */
	public UniqueChoice(List<A> choices) {
		super();
		this.setLayout(new GridLayout(1,2));
		this.Bgroup = new ButtonGroup();
		this.cores = new HashMap<JRadioButton, A>();
		for(A c : choices) {
			JRadioButton j = new JRadioButton(c.toString());
			this.Bgroup.add(j);
			this.cores.put(j,c);
			this.add(j);
		}
	}
	
	//METHODS
	/**
	 * Get the user input
	 * @return : (type-String)
	 */
	public String getUserInput() {
		for(JRadioButton j : this.cores.keySet()) {
			if(j.isSelected()) {
				return this.cores.get(j).toString();
			}
		}
		return null;
	}
	
	/**
	 * Set enable
	 * @param enabled : (type-boolean)
	 */
	public void setEnable(boolean enabled) {
		for(JRadioButton j : this.cores.keySet()) {
			j.setEnabled(enabled);
		}
	}

}
