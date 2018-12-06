/**
 * PROJECT PLUGINS
 * @author : Hugot Jean-Michel and Philippot Grégoire
 */

package editor.examples;

import java.awt.List;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Collections;

public class FileFinder {
	/*filename filter classFinder*/
    private final static FilenameFilter classFinder = new FilenameFilter(){
    
        @Override
        public boolean accept(File dir, String name) {
            return name.endsWith(".class");
        }
    };
    /*filename filter startWithC*/
    private final static FilenameFilter startWithC = new FilenameFilter(){
    
        @Override
        public boolean accept(File dir, String name) {
            return name.startsWith("C");
        }
    };
    /*path*/
    private final String path;
    /*File*/
    private final File file;
	/**
	 * Create a file finder 
	 * @param p : (type-String) path
	 */
    public FileFinder(String p){
        this.path = p;
        this.file = new File(p);
    }
    /**
     * Find
     * @param filt : (type-FilenameFilter) filter
     * @return : (type-List)
     */
    private List find(FilenameFilter filt){
        String[] l = this.file.list(filt);
        if(l!=null){
            return (List) Arrays.asList(l);
        }else{
            return (List) Collections.emptyList();
        }
    }
    /**
     * Get classes
     * @return : (type-List)
     */
    public List getClasses(){
        return find(classFinder);
    }
    /**
     * Get starring with C
     * @return : (type-List)
     */
    public List getStarringWithC(){
        return find(startWithC);
    }
}
