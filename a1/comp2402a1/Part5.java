package comp2402a1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Part5 {
	
	/**
	 * Your code goes here - see Part0 for an example
	 * @param r the reader to read from
	 * @param w the writer to write to
	 * @throws IOException
	 */
	public static void doIt(BufferedReader r, PrintWriter w) throws IOException {
		// Your code goes here - see Part0 for an example
		List<Str> list = new ArrayList<Str>();
		
		while (true) {
			String line = r.readLine();
			if (line != null) {
				list.add(new Str(line));
				
			} else {
				break;
			}
		}
		
		Collections.sort(list);
		
		for (Str str : list) {
			w.println(str.str);
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
	
	//	public class Str implements Comparable<Str> {
	//
	//		String str;
	//
	//		public Str(String str) {
	//			this.str = str;
	//		}
	//
	//		@Override
	//		public int compareTo(Str o) {
	//			int strLen = str.length();
	//			int oLen = o.str.length();
	//
	//			if (strLen > oLen) {
	//				return strLen - oLen;
	//
	//			} else if (strLen < oLen) {
	//				return strLen - oLen;
	//
	//			} else if (strLen == oLen) {
	//				return str.compareTo(o.str);
	//
	//			} else {
	//				System.out.println("Unexpected value");
	//				return 0;
	//			}
	//		}
	//
	//		@Override
	//		public boolean equals(Object obj) {
	//			return str.equals(obj);
	//		}
	//
	//		@Override
	//		public String toString() {
	//			return str.toString();
	//
	//		}
	//
	//
	//	}
}
