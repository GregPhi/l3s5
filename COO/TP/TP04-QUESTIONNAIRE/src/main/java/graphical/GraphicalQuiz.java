/**
 * PROJECT QUESTIONNARY
 * @author : Hugot Jean-Michel and Philippot Gregoire
 */

package graphical;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

//import java.io.FileNotFoundException;
import java.io.IOException;
//import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
//import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

//import graphical.answer.GraphicalAnswer;
import graphical.answer.UserInput;
import quiz.answer.BadValueException;
import quiz.questionnary.Question;
import quiz.questionnary.Quiz;


public class GraphicalQuiz extends JFrame{

	//ATTRIBUTS
	/**/
	private static final long serialVersionUID = 5091583575304174551L;
	/*left pan in window*/
	private JPanel leftPan;
	/*right pan in window*/
	private JPanel rightPan;
	/*file of questionnary*/
	private JTextField filePath;
	/*list of question*/
	private List<Question> questList;
	/*counter*/
	private Counter count;
	
	//CONSTRUCTOR
	/**
	 * Create a graphical quiz
	 * @param text : (type-String) name of window
	 */
	public GraphicalQuiz(String text) {
		super(text);
		this.count = new Counter();
		this.count.incNbRep();
		this.addWindowListener(new CloseWindowEvent());
		BorderLayout mainW = new BorderLayout();
		this.setLayout(mainW);
		this.rightPan = new JPanel();
		this.rightPan.setPreferredSize(new Dimension(650,500));
		this.add(this.rightPan,BorderLayout.EAST);
		this.leftPan = new JPanel();
		this.leftPan.setPreferredSize(new Dimension(300,500));
		this.add(this.leftPan,BorderLayout.WEST);
		JButton filev1 = new JButton("Select quiz file version 1");
		JButton filev2 = new JButton("Select quiz file version 2");
		JButton searchF = new JButton("Select quiz file");
		FileButtonEventv1 fileBeventv1 = new FileButtonEventv1();
		filev1.addActionListener(fileBeventv1);
		FileButtonEventv2 fileBeventv2 = new FileButtonEventv2();
		filev2.addActionListener(fileBeventv2);
		FileButtonEvent fileBevent = new FileButtonEvent();
		searchF.addActionListener(fileBevent);
		this.leftPan.add(filev1);
		this.leftPan.add(filev2);
		this.leftPan.add(searchF);
		this.filePath = new JTextField("",20);
		this.leftPan.add(this.filePath);
		JButton createQuest = new JButton("Créer le questionnaire");
		ValidationButtonEvent validBevent = new ValidationButtonEvent();
		createQuest.addActionListener(validBevent);
		this.leftPan.add(createQuest);
		this.pack();
	}
	
	//METHODS
	/**
	 * Build right pan
	 * @throws BadValueException : bad value exception
	 */
	public void buildRightPanel() throws BadValueException {
		this.rightPan.removeAll();
		this.count.reset();
		for(Question qu : this.questList) {
			this.rightPan.add(UserInput.uif.craftUserInput(qu,this.count));
			this.count.incNbQuest();
			this.count.incMaxPoints(qu.getNbOfPoints());
		}
		this.rightPan.revalidate();
		this.rightPan.repaint();
	}
	
	//MAIN
	/**
	 * Create the window
	 * @param args : (type-String)
	 */
	public static void main(String args[]) {
		JFrame mainW = new GraphicalQuiz("Questionnary");
		mainW.pack();
		mainW.setVisible(true);
	}
	
	//CLASS INTERNAL
	//CLASS FileButtonEvent
	/**
	 * Create a file button event : for search file
	 */
	private class FileButtonEvent implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			JFileChooser fc = new JFileChooser();
			fc.showOpenDialog(null);
			GraphicalQuiz.this.filePath.setText(fc.getSelectedFile().toString());
		}
	}
	
	//CLASS FileButtonEvent for quiz 1
	/**
	 * Create a file button eventv1 : for question_tolkien.txt questionnary
	 */
	private class FileButtonEventv1 implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			GraphicalQuiz.this.filePath.setText("question_tolkien.txt");
		}
	}

	//CLASS FileButtonEvent for quiz 2
	/**
	 * Create a file button eventv2 : for question_tolkien_2.txt questionnary
	 */
	private class FileButtonEventv2 implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			GraphicalQuiz.this.filePath.setText("question_tolkien_2.txt");
		}
	}
		
	//CLASS ValidationButtonEvent
	/**
	 * Create a validation button event : for validate your choice of questionnary
	 */
	private class ValidationButtonEvent implements ActionListener{
		public void actionPerformed(ActionEvent arg0){
			String fileN = GraphicalQuiz.this.filePath.getText();
			try {
				Quiz q = new Quiz(fileN);
				q.add();
				GraphicalQuiz.this.questList = q.getListOfQuestion();
				GraphicalQuiz.this.buildRightPanel();
			}catch(SecurityException e) {
				e.printStackTrace();
			}catch(IOException e) {
				e.printStackTrace();
			}catch(BadValueException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	//CLASS CloseWindowEvent
	/**
	 * Close window event
	 */
	private class CloseWindowEvent extends WindowAdapter{
		@Override
		public void windowClosing(java.awt.event.WindowEvent e) {
			System.exit(0);
		}
	}
}
