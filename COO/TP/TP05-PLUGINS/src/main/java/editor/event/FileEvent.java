/**
 * PROJECT PLUGINS
 * @author : Hugot Jean-Michel and Philippot Grégoire
 */

package editor.event;

import java.util.EventObject;

public class FileEvent extends EventObject{
	private static final long serialVersionUID = -1573672231835254910L;
	/*file name*/
	private String fileN;
	/**
	 * Create a file event
	 * @param s : (type-String) file
	 */
	public FileEvent(String s) {
		super(s);
		this.fileN=s;
	}
	/**
	 * Get file name
	 * @return : (type-String) file name
	 */
	public String getFileName() {
		return this.fileN;
	}
}
