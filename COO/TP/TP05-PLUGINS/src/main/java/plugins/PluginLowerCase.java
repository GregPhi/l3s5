/**
 * PROJECT PLUGINS
 * @author : Hugot Jean-Michel and Philippot Grégoire
 */

package plugins;

public class PluginLowerCase implements Plugin{
  public PluginLowerCase(){

  }
  public String transform(String s){
    return s.toLowerCase();
  }
  public String getLabel() {
    return "plugin : lower case !";
  }
  public String helpMessage(){
    return "Transform all leters of text to lower case !";
  }
}
