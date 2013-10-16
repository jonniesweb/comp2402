package comp2402a1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Part2 {
	
	/**
	 * Your code goes here - see Part0 for an example
	 * @param r the reader to read from
	 * @param w the writer to write to
	 * @throws IOException
	 */
	public static void doIt(BufferedReader r, PrintWriter w) throws IOException {
		// Your code goes here - see Part0 for an example
		
		/*
		 * Slower code
		 */
		Set<String> s = new HashSet<String>(100);
		String line;
		while (true) {
			line = r.readLine();
			if (line != null) {
				s.add(line);
			} else {
				break;
			}
			
		}
		
		List<String> list = new ArrayList<String>(s);
		Collections.sort(list);
		for (String string : list) {
			w.println(string);
		}
		
		/*
		 * Faster code
		 */
		//		ArrayList<String> s = new ArrayList<String>(100);
		//		String line;
		//		while (true) {
		//			line = r.readLine();
		//			if (line != null) {
		//				s.add(line);
		//				//				w.println(line);
		//			} else {
		//				break;
		//			}
		//		}
		//
		//		Collections.sort(s);
		//		for (int i = s.size() -1; i > 0; i--) {
		//			String compare = s.get(i);
		//			if (compare.equals(s.get(i - 1))) {
		//				s.remove(i);
		//			} else {
		//				w.println(compare);
		//			}
		//		}
		
		//		for (String string : s) {
		//			w.println(string);
		//		}
	}
	
	private int compareString(String firstString, String secondString) {
		int s1len = firstString.length();
		int s2len = secondString.length();
		int lim = Math.min(s1len, s2len);
		char v1[] = firstString.toCharArray();
		char v2[] = secondString.toCharArray();
		
		int i = 0;
		while (i < lim) {
			char c1 = v1[i];
			char c2 = v2[i];
			if (c1 != c2) {
				return c1 - c2;
			}
			i++;
		}
		return s1len - s2len;
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
