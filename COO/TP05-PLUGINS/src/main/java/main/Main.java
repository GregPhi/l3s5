/**
 * PROJECT PLUGINS
 * @author : Hugot Jean-Michel and Philippot Grégoire
 */

package main;

import editor.Editor;

public class Main {
	/**
	 * Main, create a editor
	 * @param args : (type-String)
	 */
	public static void main(String args[]) {
		Editor edit = new Editor("repository");
		edit.setVisible(true);
	}
}
