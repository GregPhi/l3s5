/**
 * PROJECT PLUGINS
 * @author : Hugot Jean-Michel and Philippot Grégoire
 */

package plugins;

public class PluginUpperCase implements Plugin{
  public PluginUpperCase(){

  }
  public String transform(String s){
    return s.toUpperCase();
  }
  public String getLabel() {
    return "plugin : upper case !";
  }
  public String helpMessage(){
    return "Transform all leters of text to upper case !";
  }
}
