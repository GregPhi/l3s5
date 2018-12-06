/**
 * PROJECT PLUGINS
 * @author : Hugot Jean-Michel and Philippot Grégoire
 */

package plugins;

public interface Plugin{
  /**
   * Transform a message <code>s</code>
   * @param s : (type-String) the message to transform
   * @return : (type-String) the transformed message
   */
  public String transform(String s);
  /**
   * Get the plugin
   * @return : (type-String) label
   */
  public String getLabel();
  /**
   * Return a message to indicate the plugin's usefulness
   * @return : (type-String) help message
   */
  public String helpMessage();
}
