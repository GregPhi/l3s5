/**
 * PROJECT PLUGINS
 * @author : Hugot Jean-Michel and Philippot Grégoire
 */

package plugins;

public class PluginRemoveVowels implements Plugin{
  public PluginRemoveVowels(){

  }
  public String transform(String s){
    return s.replaceAll("[aAeEiIoOuUyY]","");
  }
  public String getLabel() {
    return "plugin : remove vowels !";
  }
  public String helpMessage(){
    return "Remove all vowels of the text !";
  }
}
