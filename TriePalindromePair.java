
import java.util.ArrayList;
import java.util.Scanner;

public class TriePalindromePair {

    private TrieNode root;

    public TriePalindromePair() {
        root = new TrieNode('\0');
    }

    private void print(TrieNode root, String word) {
        if (root == null) {
            return;
        }

        if (root.isTerminating) {
            System.out.println(word);
        }

        for (TrieNode child : root.children) {
            if (child == null) {
                continue;
            }
            String fwd = word + child.data;
            print(child, fwd);
        }
    }

    public void print() {
        print(this.root, "");
    }





    /*..................... Palindrome Pair................... */

//    Reverses a given word
    private static String reverseWord(String word){
        String str = "";
        for (int i = word.length()-1; i >= 0; i--) {
            str += word.charAt(i);
        }

        return str;
    }

    //     Checks if a given word is a palindrome
    private static boolean checkWordForPalindrome(String word){
        int startIndex = 0;
        int endIndex = word.length() - 1;
        while(startIndex<endIndex){
            if(word.charAt(startIndex) != word.charAt(endIndex))
                return false;
            startIndex++;
            endIndex--;
        }
        return true;
    }

    //    Recursively checks if any remaining words in the Trie form a palindrome
    private static boolean checkRemainingBranchesInTrie(TrieNode root, String word){
        if(root.isTerminating){
            if(checkWordForPalindrome(word))
                return true;
        }

//        // Traverse through all possible child nodes
        for (int i = 0; i < 26; i++) {
            TrieNode child = root.children[i];
            if(child==null)
                continue;

//            If we found a child node we need to concatenate it to word to check more following words in child node;
            String fwd = word + child.data;

            if(checkRemainingBranchesInTrie(child, fwd))
                return true;

        }
        return false;
    }

//    Checks if the given word has a palindrome pair in the Trie
    private static boolean doesPairExistFor(TrieNode root, String word, int startIndex){
        if(word.isEmpty())
            return true;

        // If the entire word is traversed, check for termination and remaining branches
        if(startIndex == word.length()){
            if(root.isTerminating)
                return true;
            return checkRemainingBranchesInTrie(root, "");

        }

        int childIndex = word.charAt(startIndex) - 'a';
        TrieNode child = root.children[childIndex];

                // If child is null, check if the remaining word is a palindrome
        if(child == null){
            if(root.isTerminating)
                return checkWordForPalindrome(word.substring(startIndex));
            return false;
        }

        return doesPairExistFor(child, word, startIndex +1);
    }

//         * Checks if any two words in the list form a palindrome pair
    private static boolean isPalindromePair(TrieNode root, ArrayList<String> words){
//        In case there is no word in arraylist
        if(words.isEmpty())
            return false;

        for (String s: words) {
            if(doesPairExistFor(root, s, 0))
                return true;

        }
        return false;
    }

    public static boolean isPalindromePair(ArrayList<String> words) {
        //Your code goes here
        Trie trie = new Trie();
        for (String word : words) {
//            adding the word in reverse order
            trie.add(reverseWord(word));
        }

        return isPalindromePair(trie.root, words);
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<String> vect = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            vect.add(sc.next());
        }
        System.out.println(isPalindromePair(vect));
    }

} 