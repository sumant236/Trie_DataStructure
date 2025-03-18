import java.util.ArrayList;

class TrieNode{
    char data;
    boolean isTerminating;
    TrieNode[] children;
    int childCount;

    public TrieNode(char data) {
        this.data = data;
        this.isTerminating = false;
        this.children = new TrieNode[26];
        this.childCount = 0;
    }
}

public class Trie {

    public TrieNode root;

    public Trie() {
        this.root = new TrieNode('\0');
    }

    //  helper to add a word
    private void addHelper(TrieNode root, String word){
        if(word.isEmpty()){
            root.isTerminating = true;
            return;
        }
//        check at childIndex of the array whether word exist or not;
        int childIndex = word.charAt(0) - 'a';
        TrieNode child = root.children[childIndex];

//        if word doesn't exist in the array, we will insert new TrieNode i.e. array of 26 size at childIndex of parent array
        if(child == null){
            child = new TrieNode(word.charAt(0));
            root.children[childIndex] = child;
            root.childCount++;
        }

        addHelper(child, word.substring(1));
    }

    //  function to add pattern of a word
    public void add(String word){
        addHelper(root, word);
    }

//    function to search a single word
    public boolean search(String word){
        return search(root, word);
    }

    //    helper to search a single word
    private boolean search(TrieNode root, String word) {
        // implement this function
        if(word.isEmpty()){
            return root.isTerminating;
        }

        int childIndex = word.charAt(0) - 'a';
        TrieNode child = root.children[childIndex];

//        if word doesn't exist in the array, we will return false beacuse the word is not completed and not match fully;
        if(child == null){
            return false;
        }

        return search(child, word.substring(1));
    }

//    function to remove a word
    public void remove(String word){
        remove(root, word);
    }

//    helper to remove a word
    private void remove(TrieNode root, String word){
        if(word.isEmpty()){
            if(root.isTerminating){
                root.isTerminating = false;
                return;
            } else {
                System.out.println("Words does not exist");
                return;
            }
        }

        int childIndex = word.charAt(0) - 'a';
        TrieNode child = root.children[childIndex];

        if(child == null){
            System.out.println("Words does not exist");
            return;
        }

        remove(child, word.substring(1));

        if(!child.isTerminating && child.childCount == 0){
            root.children[childIndex] = null;
            root.childCount--;
        }
    }

//    helper to search a pattern of a word
    private boolean searchPattern(TrieNode root, String pattern) {
        if(pattern.isEmpty())
            return true;
        int childIndex = pattern.charAt(0) - 'a';
        TrieNode child = root.children[childIndex];

        if(child == null){
            return false;
        }

        return searchPattern(child, pattern.substring(1));
    }

//    function to add a word
    public boolean searchPattern(String pattern) {
        return searchPattern(root, pattern);
    }

//  helper to add pattern of a word
    private void addPattern(TrieNode root, String word){
        if(word.isEmpty()){
            root.isTerminating = true;
            return;
        }
//        check at childIndex of the array whether word exist or not;
        int childIndex = word.charAt(0) - 'a';
        TrieNode child = root.children[childIndex];

//        if word doesn't exist in the array, we will insert new TrieNode i.e. array of 26 size at childIndex of parent array
        if(child == null){
            child = new TrieNode(word.charAt(0));
            root.children[childIndex] = child;
            root.childCount++;
        }

        addPattern(child, word.substring(1));
        addPattern(root, word.substring(1));
    }

    //  function to add pattern of a word
    public void addPattern(String s) {
        addPattern(root, s);
    }
}
