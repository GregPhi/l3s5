/**
 * PROJECT PLUGINS
 * @author : Hugot Jean-Michel and Philippot Grégoire
 */

package plugins;

public class PluginAddSignature implements Plugin{
  public PluginAddSignature(){

  }
  public String transform(String s){
    return s+"\n\n\n\n\n"+"Create by :"+"\n"+"Hugot Jean-Michel   "+"\n"+"Philippot Grégoire";
  }
  public String getLabel() {
    return "plugin : add signature !";
  }
  public String helpMessage(){
    return "Add a signature to the end of the text !";
  }
}
