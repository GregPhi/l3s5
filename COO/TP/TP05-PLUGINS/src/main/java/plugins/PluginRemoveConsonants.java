/**
 * PROJECT PLUGINS
 * @author : Hugot Jean-Michel and Philippot Grégoire
 */

package plugins;

public class PluginRemoveConsonants implements Plugin{
  public PluginRemoveConsonants(){

  }
  public String transform(String s){
    return s.replaceAll("[zZrRtTpPqQsSdDfFgGhHjJkKlLmMwWxXcCvVbBnN]","");
  }
  public String getLabel() {
    return "plugin : remove consonants !";
  }
  public String helpMessage(){
    return "Remove all consonants of the text !";
  }
}
