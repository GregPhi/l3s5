/**
 * PROJECT PLUGINS
 * @author : Hugot Jean-Michel and Philippot Grégoire
 */

package plugins;

public class PluginCodeCesar implements Plugin{
	/*shift*/
  private int shift;
  /**
   * Create a plugin code cesar, with a shift = 1
   */
  public PluginCodeCesar(){
    this(1);
  }
  /**
   * Create a plugin code cesar
   * @param s : (type-int) shift
   */
  public PluginCodeCesar(int s){
    this.shift = s;
  }
  public String transform(String s){
    String transformMessage = "";
    for(int i = 0; i < s.length(); i++){
      char c = s.charAt(i);
      if(Character.isLetter(c)){
        if(Character.isLowerCase(c)){
          c = (char)(97 + (c - 97 + this.shift) % 26);
        }else{
          c = (char)(65 + (c - 65 + this.shift) % 26);
        }
      }
      transformMessage += c; 
    }
    return transformMessage;
  }
  public String getLabel() {
    return "plugin : code of ceasear with a shift of "+this.shift;
  }
  public String helpMessage(){
    return "Transform all character of a text with the code of ceasear."+"\n"+"The shift is "+this.shift;
  }
  /**
   * Get the sifht
   * @return : (type-int) shift
   */
  public int getShift() {
	  return this.shift;
  }
}
