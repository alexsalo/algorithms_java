package strings;

public class Rotation {

    public static void main(String[] args) {
        System.out.println(isSubstring("ababc", "a"));
        System.out.println(isSubstring("ababc", "abc"));
        System.out.println(isSubstring("abab", "abc"));
        System.out.println(isSubstring("ababc", "abcd"));
        System.out.println();
        System.out.println(isRotation("vasek", "sekva"));
    }

    // return true if s2 is a rotation of s1
    public static boolean isRotation(String s1, String s2) {
        // Trivia case
        if (s1.length() != s2.length())
            return false;

        // Base case check
        return isSubstring(s2 + s2, s1);
    }

    public static boolean isSubstring(String s1, String s2) { // ~O(N)
        char start = s2.charAt(0);
        for (int i = 0; i <= s1.length() - s2.length(); i++) {
            if (s1.charAt(i) == start) {
                int pos = 1;
                while (pos < s2.length()
                        && s1.charAt(i + pos) == s2.charAt(pos)) {
                    pos++;
                }
                if (pos == s2.length()) {
                    return true;
                }
                i += pos - 1;
            }
        }
        return false;
    }

}
