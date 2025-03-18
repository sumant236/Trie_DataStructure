public class TrieUse {
    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.add("their");
        trie.add("there");
        System.out.println(trie.search("the"));
        System.out.println(trie.search("there"));
        System.out.println(trie.search("theres"));

        trie.add("palindrome");
        trie.add("palind");
        trie.remove("pal");
        trie.remove("palind");
        System.out.println(trie.search("palind"));
    }
}
