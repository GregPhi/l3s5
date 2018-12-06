package editor;

import java.io.File;
import java.io.FilenameFilter;

import plugins.Plugin;

public class PluginFilter implements FilenameFilter{
	@Override
	public boolean accept(File arg0, String arg1) {
        if(!arg1.endsWith(".class")){
            return false;
        }
        String classN = "plugins."+arg1.replaceAll("\\.class", "");
        try{
            Class<?> c = Class.forName(classN);
            if(Plugin.class.isAssignableFrom(c)){
                if(c.getPackage().getName().equals("plugins")){
                    if(c.getConstructor()!=null){
                        return true;
                    }else{

                    }
                }else{
                        
                    }
            }else{
                
            }
        }catch(Exception e){
            return false;
        }
        return false;
	}
}