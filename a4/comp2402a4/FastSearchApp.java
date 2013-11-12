package comp2402a4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class FastSearchApp extends JPanel implements DocumentListener {
	private static final long serialVersionUID = 7177482636026020844L;
	protected static String defaultFilename = "wp.txt";

	protected FastSearch pf;
	protected JTextField queryField;
	protected JTextArea resultsArea;
	
	protected char text[];
	
	public FastSearchApp(String filename) {
		// do gui stuff
		super(new GridBagLayout());

		queryField = new JTextField(100);
		queryField.getDocument().addDocumentListener(this);

		resultsArea = new JTextArea(15, 40);
		resultsArea.setEditable(false);
		resultsArea.setLineWrap(true);
		resultsArea.setWrapStyleWord(true);


		GridBagConstraints c = new GridBagConstraints();
		c.gridwidth = GridBagConstraints.REMAINDER;
		
		c.fill = GridBagConstraints.HORIZONTAL;
		add(queryField, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		c.weighty = 1.0;
		add(resultsArea, c);

		// prepare the completion list
		BufferedReader r = null;
		try {
			r = new BufferedReader(new FileReader(filename));
		} catch (FileNotFoundException e) {
			System.err.println("File not found: " + filename);
			System.exit(-1);
		}			
		String line;
		StringBuffer sb = new StringBuffer();
		try {
			while ((line = r.readLine()) != null) {
				sb.append(" ");
				sb.append(line);
			}
		} catch (IOException e) {
			System.err.println("Error reading input file " + filename);
			System.exit(-1);
		}
		text = new char[sb.length()];
		sb.getChars(0, sb.length(), text, 0);
		pf = new FastSearch(text);
	}

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI(String filename) {
        //Create and set up the window.
        JFrame frame = new JFrame("PhraseFinderApp");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add contents to the window.
        frame.add(new FastSearchApp(filename));

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
	
    public void search() {
        String s = queryField.getText();
        
        if (s.length() == 0) {
        	resultsArea.setText("");	
        } else if (!s.matches("^\\w+$")) {
        	resultsArea.setText("Error: only supports single word search");
        } else {
	        Collection<Integer> results = pf.lookup(s, 10);
	        Iterator<Integer> it = results.iterator();
	        String res = "";
	        while (it.hasNext()) {
	        	res = res + sampleText(it.next());
	        	if (it.hasNext()) {
	        		res = res + "\n";
	        	}
	        }
	        resultsArea.setText(res);
        }
    }
    
	public static void main(String[] args) {
		if (args.length != 0)
			defaultFilename = args[0];
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI(defaultFilename);
            }
        });
	}
	
	protected static int max(int a, int b) {
		return a > b ? a : b;
	}

	
	protected static int min(int a, int b) {
		return a < b ? a : b;
	}

	public String sampleText(int i) {
		i = max(0, i-10);
		int len = min(60, text.length - i);
		return "..." + String.copyValueOf(text, i, len) + "...";
	}

	@Override
	public void changedUpdate(DocumentEvent arg0) {
		search();
	}

	@Override
	public void insertUpdate(DocumentEvent arg0) {

		search();
	}

	@Override
	public void removeUpdate(DocumentEvent arg0) {
		search();
	}
}