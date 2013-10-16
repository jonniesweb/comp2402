package comp2402a1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedHashSet;
import java.util.Set;

public class Part1 {
	
	/**
	 * Your code goes here - see Part0 for an example
	 * @param r the reader to read from
	 * @param w the writer to write to
	 * @throws IOException
	 */
	public static void doIt(BufferedReader r, PrintWriter w) throws IOException {
		// Your code goes here - see Part0 for an example
		//		Set<String> s = new HashSet<String>(100);
		//		String line;
		//		while (true) {
		//			line = r.readLine();
		//			if (line != null) {
		//				s.add(line);
		//				w.println(line);
		//			} else {
		//				break;
		//			}
		//
		//		}
		
		//		ArrayList<String> list = new ArrayList<String>(100);
		//		String line;
		//		while (true) {
		//			line = r.readLine();
		//			if (line != null) {
		//				if (!list.contains(line)) {
		//					list.add(line);
		//					w.println(line);
		//				}
		//			} else {
		//				break;
		//			}
		//		}
		
		Set<String> s = new LinkedHashSet<String>(100);
		String line;
		while (true) {
			line = r.readLine();
			if (line != null) {
				s.add(line);
			} else {
				break;
			}
		}
		
		for (String string : s) {
			w.println(string);
		}
		
	}
	
	/**
	 * The driver.  Open a BufferedReader and a PrintWriter, either from System.in
	 * and System.out or from filenames specified on the command line, then call doIt.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			BufferedReader r;
			PrintWriter w;
			if (args.length == 0) {
				r = new BufferedReader(new InputStreamReader(System.in));
				w = new PrintWriter(System.out);
			} else if (args.length == 1) {
				r = new BufferedReader(new FileReader(args[0]));
				w = new PrintWriter(System.out);
			} else {
				r = new BufferedReader(new FileReader(args[0]));
				w = new PrintWriter(new FileWriter(args[1]));
			}
			long start = System.nanoTime();
			doIt(r, w);
			w.flush();
			long stop = System.nanoTime();
			System.out.println("Execution time: " + 10e-9 * (stop-start));
		} catch (IOException e) {
			System.err.println(e);
			System.exit(-1);
		}
	}
}
