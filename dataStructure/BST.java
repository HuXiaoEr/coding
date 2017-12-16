package dataStructure;

import java.util.NoSuchElementException;

/*
 * key != null
 * val != null
 * key≤ªƒ‹÷ÿ∏¥
 */
public class BST<Key extends Comparable<Key>, Value> {
    private Node root;             // root of BST

    private class Node {
    	private Key key;           // sorted by key
    	private Value val;         // associated data
    	private Node left, right;  // left and right subtrees

    	private Node(Key key, Value val) {
            this.key = key;
            this.val = val;
        }
    }

    /**
     * Initializes an empty symbol table.
     */
    public BST() {
    }
    
    public boolean isEmpty() {
        return root == null;
    }
    
    // get
    public Value get(Key key) {
    	if (key == null) throw new IllegalArgumentException("called get() with a null key");
        return get(root, key);
    }
    private Value get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else              return x.val;
    }

    // put
    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("called put() with a null key");
        if (val == null) throw new IllegalArgumentException("called put() with a null val");
        root = put(root, key, val);  // °Ô
    }
    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val);
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = put(x.left,  key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else              x.val   = val;
        return x;
    }

    // delete
    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("called delete() with a null key");
        root = delete(root, key);  // °Ô
    }
    private Node delete(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = delete(x.left,  key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else { 
            if (x.right == null) return x.left;
            if (x.left  == null) return x.right;
            Node minNode = min(x.right);
            x.key = minNode.key;
            x.val = minNode.val;
            x.right = delete(x.right, x.key);
        } 
        return x;
    }
    
    /**
     * Returns the largest key in the symbol table less than or equal to {@code key}.
     */
    public Key floor(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to floor() is null");
        if (isEmpty()) throw new NoSuchElementException("called floor() with empty symbol table");
        Node x = floor(root, key);
        if (x == null) return null;
        else return x.key;
    } 
    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp <  0) return floor(x.left, key);
        Node t = floor(x.right, key); 
        if (t != null) return t;
        else return x; 
    }

    /**
     * Returns the smallest key in the symbol table greater than or equal to {@code key}.
     */
    public Key ceiling(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to ceiling() is null");
        if (isEmpty()) throw new NoSuchElementException("called ceiling() with empty symbol table");
        Node x = ceiling(root, key);
        if (x == null) return null;
        else return x.key;
    }
    private Node ceiling(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) { 
            Node t = ceiling(x.left, key); 
            if (t != null) return t;
            else return x; 
        }
        return ceiling(x.right, key); 
    } 
    
    ///////////////////////////////PriorityBlockingQueue
    
    // deleteMin
    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("Symbol table underflow");
        root = deleteMin(root);
    }
    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        return x;
    }

    // deleteMax
    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException("Symbol table underflow");
        root = deleteMax(root);
    }
    private Node deleteMax(Node x) {
        if (x.right == null) return x.left;
        x.right = deleteMax(x.right);
        return x;
    }

    // min
    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("called min() with empty symbol table");
        return min(root).key;
    } 
    private Node min(Node x) { 
        if (x.left == null) return x; 
        else                return min(x.left); 
    } 

    // max
    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("called max() with empty symbol table");
        return max(root).key;
    } 
    private Node max(Node x) {
        if (x.right == null) return x; 
        else                 return max(x.right); 
    }

}

