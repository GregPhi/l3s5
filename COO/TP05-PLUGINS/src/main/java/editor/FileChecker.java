package editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.util.List;

import javax.swing.Timer;

import editor.event.FileEvent;
import editor.event.FileListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class FileChecker{
	/*file listener*/
    private final List<FileListener> fileL;
    /*files*/
    private final List<String> files;
    /*timer*/
    private final Timer tim;
    /*file name filter*/
    private final FilenameFilter filt;
    /*file*/
    private final File file;
    /**
     * Create a file checker
     * @param folderW : (type-String) file
     * @param filt : (type-FilenameFilter) filter
     */
    public FileChecker(String folderW, FilenameFilter filt){
        this.file = new File(folderW);
        this.fileL = new ArrayList<FileListener>();
        this.files = new ArrayList<String>();
        this.filt=filt;
        this.tim=new Timer(40,new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                checkFiles();
            }
        });
        tim.start();
    }
    /**
     * Find
     * @return : (type-List) 
     */
    private List<String> find(){
        String[] l = file.list(filt);
        if(l!=null){
            return Arrays.asList(l);
        }else{
            return Collections.emptyList();
        }
    }
    /**
     * Get timer
     * @return : (type-Timer)
     */
    public Timer getTimer(){
        return this.tim;
    }
    /**
     * Get files
     * @return : (type-List)
     */
    public List<String> getFiles(){
        return Collections.unmodifiableList(files);
    }
    /**
     * Check files
     */
    private void checkFiles(){
        List<String> c = find();
        for(String s : c){
            if(!files.contains(s)){
                files.add(s);
                fireFileAdded(s);
            }
        }
        List<String> toBeRemov = new ArrayList<String>();
        for(String f : files){
            if(!c.contains(f)){
                toBeRemov.add(f);
                fireFileDeleted(f);
            }
        }
        files.removeAll(toBeRemov);
    }
    /**
     * Fire file added
     * @param s : (type-String)
     */
    private void fireFileAdded(String s){
        FileEvent e = new FileEvent(s);
        for(FileListener f : fileL){
            f.fileAdded(e);
        }
    }
    /**
     * Fire file deleted
     * @param s : (type-String)
     */
    private void fireFileDeleted(String s){
        FileEvent e = new FileEvent(s);
        for(FileListener f : fileL){
            f.fileDeleted(e);
        }
    }
    /**
     * Add file listener
     * @param pluginFilter : (type-FileListener)
     */
    public synchronized void addFileListener(FileListener pluginFilter){
        if(!fileL.contains(pluginFilter)){
            fileL.add(pluginFilter);
        }
    }
    /**
     * Remove file listener
     * @param idx : (type-int) idx of file listener
     * @return : (type-FileListener) file listener
     */
    public synchronized FileListener removeFileListener(int idx){
        return fileL.remove(idx);
    }
}