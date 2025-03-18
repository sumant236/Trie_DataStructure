import java.util.ArrayList;
import java.util.Scanner;

public class AutoComplete {
    private TrieNode root;
    public AutoComplete() {
        root = new TrieNode('\0');
    }

    private static void print(TrieNode root, String s){
        if(root==null)
            return;

//        if the current TrieNode represents the end of a word, print the word formed so far
        if(root.isTerminating){
            System.out.println(s);
        }
//        Traverse through the children of the current TrieNode
        for (int i = 0; i < 26; i++) {
            if(root.children[i]!=null){
//                Append the character represented by the current child TrieNode to the current string 's'
                String t = s+root.children[i].data;
//                Recursively call the print function with the child trienode and the updated string
                print(root.children[i], t);
            }
        }
    }

    private static void autoCompleteHelper(TrieNode root, String word, String output){
//        Base Case: if current TrieNode is null, return;
        if(root == null){
            return;
        }

        if(word.isEmpty()){
            // If we reach a terminating node, print the output
            if(root.isTerminating)
                System.out.println(output);

            // Print all possible completions
            for (int i = 0; i < 26; i++) {
                if(root.children[i]!=null){
                    String s = output + root.children[i].data;
                    print(root.children[i], s);
                }
            }
            return;
        }

        int childIndex = word.charAt(0) - 'a';
        output += word.charAt(0);
        autoCompleteHelper(root.children[childIndex], word.substring(1), output);
    }

    public static void autoComplete(ArrayList<String> input, String word) {
        // Write your code here
        Trie trie= new Trie();
        for (String s : input) {
            trie.add(s);
        }

        String output = "";

        autoCompleteHelper(trie.root, word, output);
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
        autoComplete(vect, pattern);
    }
}
