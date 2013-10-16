package comp2402a1;


public class Str implements Comparable<Str> {
	
	String str;
	
	public Str(String str) {
		this.str = str;
		
	}
	
	@Override
	public int compareTo(Str o) {
		int strLen = str.length();
		int oLen = o.str.length();
		
		if (strLen > oLen) {
			return strLen - oLen;
			
		} else if (strLen < oLen) {
			return strLen - oLen;
			
		} else if (strLen == oLen) {
			return str.compareTo(o.str);
			
		} else {
			System.out.println("Unexpected value");
			return 0;
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		return str.equals(obj);
	}
	
	@Override
	public String toString() {
		return str.toString();
		
	}
	
	
}