/**
 * PROJECT PLUGINS
 * @author : Hugot Jean-Michel and Philippot Grégoire
 */

package editor.examples;

import editor.FileChecker;
import editor.PluginFilter;
import editor.event.FileEvent;
import editor.event.FileListener;

public class SimplePluginObserver {
	/*file checker*/
    private final FileChecker fileC;
    /**
     * Create a simple plugin observer
     * @param f : (type-String)
     */
    public SimplePluginObserver(String f){
        this.fileC = new FileChecker(f, new PluginFilter());
        this.fileC.addFileListener(new FileListener() {
            @Override
            public void fileAdded(FileEvent e){
                System.out.println("Class added :"+e.getSource());
            }
            @Override
            public void fileDeleted(FileEvent e){
                System.out.println("Class deleted :"+e.getSource());
            }
        });
    }
    /**
     * Main of simple plugin observer
     * @param args : (type-String)
     */
    public static void main(String args[]){
        new SimplePluginObserver("target/classes/plugins");
        while(true){}
    }
}
