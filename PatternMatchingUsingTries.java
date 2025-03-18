import java.util.ArrayList;
import java.util.Scanner;

public class PatternMatchingUsingTries {
    public static boolean patternMatching(ArrayList<String> vect, String pattern) {
        // Write your code here
        Trie trie= new Trie();
        for (String s : vect) {
            trie.addPattern(s);
        }

        return trie.searchPattern(pattern);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<String> vect = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            vect.add(sc.next());
        }
        System.out.print("Enter the pattern you want to search");
        String pattern = sc.next();
        System.out.println(patternMatching(vect, pattern));
    }
}
