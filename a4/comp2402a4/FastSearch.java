package comp2402a4;

import java.util.ArrayList;
import java.util.List;

public class FastSearch {
    char[] text;

    /**
     * Initialize this PhraseFinder with the contents of r
     * @param r
     * @throws IOException
     */
    public FastSearch(char[] text) { 
            this.text = text;
    }

    protected boolean matches(String s, int i) {
            for (int j = 0; j < s.length(); j++) {
                    if (Character.toLowerCase(s.charAt(j)) != Character.toLowerCase(text[i+j])) return false;
            }
            return true;
    }
    
    public List<Integer> lookup(String s, int r) {
            List<Integer> occurrences = new ArrayList<Integer>();
            int m = s.length();
            boolean word = true;
            for (int i = 0; i < text.length-m; i++) {
                    word = word && Character.isLetter(text[i]);
                    if (word && matches(s, i)) {
                            occurrences.add(i);
                            if (occurrences.size() >= r) break;
                    }
                    word = !Character.isLetter(text[i]);
            }
            return occurrences;
    }
}
