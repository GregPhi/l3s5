/**
 * PROJECT PLUGINS
 * @author : Hugot Jean-Michel and Philippot Grégoire
 */

package editor.examples;

import java.io.File;
import java.io.FilenameFilter;

import editor.FileChecker;
import editor.event.FileEvent;
import editor.event.FileListener;

public class FileCheckExample {
	/**
	 * Main of file check example
	 * @param args : (type-String)
	 */
    public static void main(String[] args){
        FileChecker fC = new FileChecker(".",new FilenameFilter(){
        
            @Override
            public boolean accept(File dir, String name) {
                return true;
            }
        });
        fC.addFileListener(new FileListener() {
            @Override
            public void fileAdded(FileEvent e){
                System.out.println("file added :"+e.getSource());
            }
            @Override
            public void fileDeleted(FileEvent e){
                System.out.println("file deleted :"+e.getSource());
            }
        });
        while(true){}
    }
}
