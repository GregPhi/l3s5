package editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import editor.event.FileEvent;
import editor.event.FileListener;
import plugins.Plugin;

public class Editor extends JFrame{
	private static final long serialVersionUID = -3170366989818983407L;
	/*text area*/
	private JTextArea text;
	/*file checker*/
    private FileChecker check;
    /*menu plugins*/
    private JMenu plugM;
    /*menu help*/
    private JMenu helpM;
    /**
     * Create an editor
     * @param d : (type-String)
     */
    public Editor(String d){
        super("EDITOR");
        this.init();
        this.check = new FileChecker(d+"/"+"plugins",new PluginFilter());
        this.check.addFileListener(new PluginListener());
    }
    /**
     * Initialize the editor
     */
    private void init(){
        this.setDefaultCloseOperation(3);
        this.text = new JTextArea(28,80);
        JMenuBar menuB = new JMenuBar();
        menuB.add(this.initFileMenu());
        this.plugM = new JMenu("PLUGINS");
        menuB.add(this.plugM);
        this.helpM = new JMenu("HELP");
        menuB.add(this.helpM);
        this.setJMenuBar(menuB);
        this.getContentPane().add(new JScrollPane(this.text),"Center");
        this.pack();
    }
    /**
     * Initialize the JMenu
     * @return : (type-JMenu) JMenu 
     */
    private JMenu initFileMenu(){
    	JMenu fileM = new JMenu("File");
    	JMenuItem it = new JMenuItem("New");
    	it.addActionListener(new NewContentListener());
    	fileM.add(it);
    	it = new JMenuItem("Open");
    	it.addActionListener(new OpenFileListener());
    	fileM.add(it);
    	fileM.addSeparator();
    	it = new JMenuItem("Exit");
    	it.addActionListener(new AbstractAction() {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
    			System.exit(0);
    		}
    	});
    	fileM.add(it);
    	return fileM;
    }
    //CLASS INTERN
    /**
     * Help item action listener
     */
    private class HelpItemActionListener implements ActionListener{
        private String msg;
        public HelpItemActionListener(String s){
            this.msg = s;
        }
        public void actionPerformed(ActionEvent e){
            JOptionPane.showMessageDialog(Editor.this,this.msg,"Help",1);
        }
    }
    /**
     * New content listener 
     */
    private class NewContentListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            Editor.this.text.setText("");
        }
    }
    /**
     * Open file listener 
     */
    private class OpenFileListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            StringBuffer c = new StringBuffer("");
            JFileChooser jF = new JFileChooser();
            if(jF.showOpenDialog(Editor.this)==0){
                try{
                    BufferedReader r = new BufferedReader(new FileReader(jF.getSelectedFile()));
                    String l;
                    while((l = r.readLine())!=null){
                        c.append(l+"\n");
                    }
                    r.close();
                }catch(Exception e1){
                    JOptionPane.showMessageDialog(Editor.this, "read error :"+jF.getSelectedFile().getName());
                }
                Editor.this.text.setText(c.toString());
            }
        }
    }
    /**
     * Plugin item
     */
    private class PluginItem extends JMenuItem{
		private static final long serialVersionUID = 1L;
		/*plugin*/
		private Plugin p;
		/**
		 * Create a plugin item
		 * @param p : (type-Plugin) a plugin
		 */
    	public PluginItem(Plugin p) {
    		super(p.getLabel());
    		this.p=p;
    		this.addActionListener(new AbstractAction() {
				private static final long serialVersionUID = 1L;
				public void actionPerformed(ActionEvent e) {
    				text.setText(p.transform(text.getText()));
    			}
    		});
    	}
    }
    /**
     * Plugin listener
     */
    private class PluginListener implements FileListener{
        private Plugin getPluginFromEvent(FileEvent e){
            String n = e.getFileName();
            n = n.substring(0,n.length()-".class".length());
            try{
                Class<?> c = Class.forName("plugins."+n);
                return (Plugin)c.newInstance();
            }catch(ClassNotFoundException | InstantiationException | IllegalAccessException e1){
                throw new RuntimeException(e1);
            }
        }
        @Override
        public void fileAdded(FileEvent e){
            Plugin p = this.getPluginFromEvent(e);
            Editor.this.plugM.add(Editor.this.new PluginItem(p));
            JMenuItem helpI = new JMenuItem(p.getLabel());
            helpI.addActionListener(Editor.this.new HelpItemActionListener(p.helpMessage()));
            Editor.this.helpM.add(helpI);
        }
        @Override
        public void fileDeleted(FileEvent e){
            Plugin p = this.getPluginFromEvent(e);
            for(int i =0; i< Editor.this.plugM.getItemCount();i++){
                if(Editor.this.plugM.getItem(i).getText().equals(p.getLabel())){
                    Editor.this.plugM.remove(i);
                    Editor.this.helpM.remove(i);
                    return;
                }
            }
        }
    }
}