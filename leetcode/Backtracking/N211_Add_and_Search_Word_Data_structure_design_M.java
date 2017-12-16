package leetcode.Backtracking;
/**
Design a data structure that supports the following two operations:

void addWord(word)
bool search(word)
search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.

For example:

addWord("bad")
addWord("dad")
addWord("mad")
search("pad") -> false
search("bad") -> true
search(".ad") -> true
search("b..") -> true
Note:
You may assume that all words are consist of lowercase letters a-z.
 */

//YES
class TreeNode {
	private static final int LEGNTH_OF_CHILDREN = 26;
	boolean isEndOfWord;  //бя
	TreeNode children[];  //бя   no need the field : char value
	TreeNode(){
		isEndOfWord = false;
		children = new TreeNode[LEGNTH_OF_CHILDREN];
	}
}
public class N211_Add_and_Search_Word_Data_structure_design_M {

	/**
	 * Your WordDictionary object will be instantiated and called as such:
	 * WordDictionary obj = new WordDictionary();
	 * obj.addWord(word);
	 * boolean param_2 = obj.search(word);
	 */
    /** Initialize your data structure here. */
	private TreeNode root;
    public N211_Add_and_Search_Word_Data_structure_design_M() {
        root = new TreeNode();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TreeNode runner = root;
        for(char c : word.toCharArray()){
        	if(runner.children[c-'a'] == null) runner.children[c-'a'] = new TreeNode();
        	runner = runner.children[c-'a'];
        }
        runner.isEndOfWord = true;
    }
    
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
    	return search(root, word);
    }
	private boolean search(TreeNode node, String word) {
		TreeNode runner = node;
		boolean res = false;
		for(int j = 0; j < word.length(); j++){
			char c = word.charAt(j);
			if(c == '.'){
				for(int i = 0; i < 26; i++){
					if(runner.children[i] != null) res |= search(runner.children[i], word.substring(j+1));
					if(res) return res;
				}
				return false;
			}
			else{
				if(runner.children[c-'a'] == null) return false;
				runner = runner.children[c-'a'];
			}
		}
		return runner.isEndOfWord;
	}
	/*
	 * better than mine
	 * 
	public boolean search(String word) {
        return search(word, 0, root);
    }
    private boolean search(String word, int index, WordNode node) {
        if (index == word.length()) {
            return node.isLeaf;
        }

        for (int i = index; i < word.length(); i++) {
            char c = word.charAt(i);
            if (c == '.') {
                for (WordNode child : node.children) {
                    if (child != null) {
                        if (search(word, i + 1, child)) {
                            return true;
                        }
                    }
                }
                return false;
            } else {
                if (node.children[c - 'a'] == null) {
                    return false;
                }
                node = node.children[c - 'a'];
            }
        }
        return node.isLeaf;
    }
	 */
}
