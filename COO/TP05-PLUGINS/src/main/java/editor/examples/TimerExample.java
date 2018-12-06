/**
 * PROJECT PLUGINS
 * @author : Hugot Jean-Michel and Philippot Grégoire
 */

package editor.examples;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import javax.swing.Timer;

public class TimerExample {
	/**
	 * Main of timer example
	 * @param args : (type-String)
	 */
	public static void main(String args[]){
		Timer t = new Timer(600, new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println(LocalDateTime.now());
			}
		});
		t.start();
		while(true){}
	}
}
