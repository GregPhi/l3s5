/**
 * PROJECT PLUGINS
 * @author : Hugot Jean-Michel and Philippot Grégoire
 */

package editor.event;

import java.util.EventListener;

public interface FileListener extends EventListener{
	default void fileAdded(FileEvent e) {}
	default void fileDeleted(FileEvent e) {}
}
