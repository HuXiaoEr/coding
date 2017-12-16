package dataStructure;

public class RedBlackBST<Key extends Comparable<Key>, Value> {
	private Node root;
	private int size;
	private static final boolean RED = false;
	private static final boolean BLACK = true;

	public RedBlackBST() {
	}

	private <K, V> boolean colorOf(Node p) {
		return (p == null ? BLACK : p.color);
	}

	public Value put(Key key, Value value) {
		if (key == null)
			throw new NullPointerException();
		Node t = root;
		if (t == null) {
			// 将新的key-value键值对创建为一个Entry节点，并将该节点赋予给root
			root = new Node(key, value, null);
			// 容器的size = 1，表示TreeMap集合中存在一个元素
			size = 1;
			return null;
		}
		Node parent;
		int cmp;
		do {
			parent = t;
			cmp = key.compareTo(t.key);
			if (cmp < 0)
				t = t.left;
			else if (cmp > 0)
				t = t.right;
			else {
				Value oldValue = t.val;
				t.val = value;
				return oldValue;
			}
		} while (t != null);
		// 将新增节点当做parent的子节点
		Node newNode = new Node(key, value, parent);
		// 如果新增节点的key小于parent的key，则当做左子节点
		if (cmp < 0)
			parent.left = newNode;
		// 如果新增节点的key大于parent的key，则当做右子节点
		else
			parent.right = newNode;
		/*
		 * 上面已经完成了排序二叉树的的构建，将新增节点插入该树中的合适位置
		 * 下面fixAfterInsertion()方法就是对这棵树进行调整、平衡，具体过程参考上面的五种情况
		 */
		fixAfterInsertion(newNode);
		// TreeMap元素数量 + 1
		size++;
		// TreeMap容器修改次数 + 1
		return null;
	}

	/**
	 * 新增节点后的修复操作 x 表示新增节点
	 */
	private void fixAfterInsertion(Node x) {
		x.color = RED; // 新增节点的颜色为红色

		// 循环 直到 x不是根节点，且x的父节点不为红色
		while (x != null && x != root && x.parent.color == RED) {
			// 如果X的父节点（P）是其父节点的父节点（G）的左节点
			if (x.parent == x.parent.parent.left) {
				// 获取X的叔节点(U)
				Node y = x.parent.parent.right;
				// 如果X的叔节点（U） 为红色（情况三）
				if (colorOf(y) == RED) {
					// 将X的父节点（P）设置为黑色
					x.parent.color = BLACK;
					// 将X的叔节点（U）设置为黑色
					y.color = BLACK;
					// 将X的父节点的父节点（G）设置红色
					x.parent.parent.color = RED;
					x = x.parent.parent;
				}
				// 如果X的叔节点（U为黑色）；这里会存在两种情况（情况四、情况五）
				else {
					// 如果X节点为其父节点（P）的右子树，则进行左旋转（情况四）
					if (x == x.parent.right) {
						// 将X的父节点作为X
						x = x.parent;
						// 右旋转
						rotateLeft(x);
					}
					// （情况五）
					// 将X的父节点（P）设置为黑色
					x.parent.color = BLACK;
					// 将X的父节点的父节点（G）设置红色
					x.parent.parent.color = RED;
					// 以X的父节点的父节点（G）为中心右旋转
					rotateRight(x.parent.parent);
				}
			}
			// 如果X的父节点（P）是其父节点的父节点（G）的右节点
			else {
				// 获取X的叔节点（U）
				Node y = x.parent.parent.left;
				// 如果X的叔节点（U） 为红色（情况三）
				if (colorOf(y) == RED) {
					// 将X的父节点（P）设置为黑色
					x.parent.color = BLACK;
					// 将X的叔节点（U）设置为黑色
					y.color = BLACK;
					// 将X的父节点的父节点（G）设置红色
					x.parent.parent.color = RED;
					x = x.parent.parent;
				}
				// 如果X的叔节点（U为黑色）；这里会存在两种情况（情况四、情况五）
				else {
					// 如果X节点为其父节点（P）的右子树，则进行左旋转（情况四）
					if (x == x.parent.left) {
						// 将X的父节点作为X
						x = x.parent;
						// 右旋转
						rotateRight(x);
					}
					// （情况五）
					// 将X的父节点（P）设置为黑色
					x.parent.color = BLACK;
					// 将X的父节点的父节点（G）设置红色
					x.parent.parent.color = RED;
					// 以X的父节点的父节点（G）为中心右旋转
					rotateLeft(x.parent.parent);
				}
			}
		}
		// 将根节点G强制设置为黑色
		root.color = BLACK;
	}

	private void rotateLeft(Node node) {
		if (node != null) {
			// 获取node的右子节点，其实这里就相当于新增节点N（情况四而言）
			Node right = node.right;
			// 将right的左子树设置为node的右子树
			right.right = right.left;
			// 若right的左子树不为空，则将node设置为right左子树的父亲
			if (right.left != null)
				right.left.parent = node;
			// 将node的父亲设置right的父亲
			right.parent = node.parent;
			// 如果node的父亲为空，则将right设置为跟节点
			if (node.parent == null)
				root = right;
			// 如果node为其父节点（G）的左子树，则将right设置为node父节点(G)左子树
			else if (node.parent.left == node)
				node.parent.left = right;
			// 否则right设置为node的父节点（G）的右子树
			else
				node.parent.right = right;
			// 将node设置为right的左子树
			right.left = node;
			// 将right设置为node的父节点
			node.parent = right;
		}
	}

	public Value remove(Key key) {
		Node p = getNode(key);
		if (p == null)
			return null;

		Value oldValue = p.val;
		deleteNode(p);
		return oldValue;
	}

	private void deleteNode(Node p) {
		size--; // 元素个数 -1
		/*
		 * 被删除节点的左子树和右子树都不为空，那么就用 p节点的中序后继节点代替 p 节点
		 * successor(P)方法为寻找P的替代节点。规则是右分支最左边，或者 左分支最右边的节点
		 * ---------------------（1）
		 */
		if (p.left != null && p.right != null) {
			Node s = successor(p);
			p.key = s.key;
			p.val = s.val;
			p = s;
		}

		// replacement为替代节点，如果P的左子树存在那么就用左子树替代，否则用右子树替代
		Node replacement = (p.left != null ? p.left : p.right);

		/*
		 * 删除节点，分为上面提到的三种情况 -----------------------（2）
		 */
		// 如果替代节点不为空
		if (replacement != null) {
			replacement.parent = p.parent;
			/*
			 * replacement来替代P节点
			 */
			// 若P没有父节点，则跟节点直接变成replacement
			if (p.parent == null)
				root = replacement;
			// 如果P为左节点，则用replacement来替代为左节点
			else if (p == p.parent.left)
				p.parent.left = replacement;
			// 如果P为右节点，则用replacement来替代为右节点
			else
				p.parent.right = replacement;

			// 同时将P节点从这棵树中剔除掉
			p.left = p.right = p.parent = null;

			/*
			 * 若P为红色直接删除，红黑树保持平衡 但是若P为黑色，则需要调整红黑树使其保持平衡
			 */
			if (p.color == BLACK)
				fixAfterDeletion(replacement);
		} else if (p.parent == null) { // p没有父节点，表示为P根节点，直接删除即可
			root = null;
		} else { // P节点不存在子节点，直接删除即可
			if (p.color == BLACK) // 如果P节点的颜色为黑色，对红黑树进行调整
				fixAfterDeletion(p);

			// 删除P节点
			if (p.parent != null) {
				if (p == p.parent.left)
					p.parent.left = null;
				else if (p == p.parent.right)
					p.parent.right = null;
				p.parent = null;
			}
		}
	}
	private void fixAfterDeletion(Node x) {  
        // 删除节点需要一直迭代，知道 直到 x 不是根节点，且 x 的颜色是黑色  
        while (x != root && colorOf(x) == BLACK) {  
            if (x == x.parent.left) {      //若X节点为左节点  
                //获取其兄弟节点  
                Node sib = x.parent.right;  
  
                /* 
                 * 如果兄弟节点为红色----（情况3.1） 
                 * 策略：改变W、P的颜色，然后进行一次左旋转 
                 */  
                if (colorOf(sib) == RED) {  
                	sib.color = BLACK;
                	x.parent.color = RED;
                    rotateLeft(x.parent);  
                    sib = x.parent.right;  
                }  
  
                /* 
                 * 若兄弟节点的两个子节点都为黑色----（情况3.2） 
                 * 策略：将兄弟节点编程红色 
                 */  
                if (colorOf(sib.left)  == BLACK &&  
                    colorOf(sib.right) == BLACK) {  
                	sib.color = RED;
                    x = x.parent;  
                }   
                else {  
                    /* 
                     * 如果兄弟节点只有右子树为黑色----（情况3.3） 
                     * 策略：将兄弟节点与其左子树进行颜色互换然后进行右转 
                     * 这时情况会转变为3.4 
                     */  
                    if (colorOf(sib.right) == BLACK) {  
                    	sib.left.color = BLACK;
                    	sib.color = RED;
                        rotateRight(sib);  
                        sib = x.parent.right;  
                    }  
                    /* 
                     *----情况3.4 
                     *策略：交换兄弟节点和父节点的颜色， 
                     *同时将兄弟节点右子树设置为黑色，最后左旋转 
                     */  
                    sib.color = x.parent.color;
                    x.parent.color = BLACK;
                    sib.right.color = BLACK;
                    rotateLeft(x.parent);  
                    x = root;  
                }  
            }   
              
            /** 
             * X节点为右节点与其为做节点处理过程差不多，这里就不在累述了 
             */  
            else {  
                Node sib = x.parent.left;  
  
                if (colorOf(sib) == RED) {
                	sib.color = BLACK;
                	x.parent.color = RED;
                    rotateRight(x.parent);  
                    sib = x.parent.left;
                }  
  
                if (colorOf(sib.right) == BLACK &&  
                    colorOf(sib.left) == BLACK) {  
                	sib.color = RED;
                    x = x.parent;  
                } else {  
                    if (colorOf(sib.left) == BLACK) { 
                    	sib.right.color = BLACK;
                    	sib.color = RED;
                        rotateLeft(sib);  
                        sib = x.parent.left;  
                    }  
                    sib.color = x.parent.color;
                    x.parent.color = BLACK;
                    sib.left.color = BLACK;
                    rotateRight(x.parent);  
                    x = root;  
                }  
            }  
        }  
        x.color = BLACK;
    }  
	
	private  Node successor(Node t) {  
        if (t == null)  
            return null;  
        /* 
         * 寻找右子树的最左子树 
         */  
        else if (t.right != null) {  
            Node p = t.right;  
            while (p.left != null)  
                p = p.left;  
            return p;  
        }   
        /* 
         * 选择左子树的最右子树 
         */  
        else {  
            Node p = t.parent;  
            Node ch = t;  
            while (p != null && ch == p.right) {  
                ch = p;  
                p = p.parent;  
            }  
            return p;  
        }  
    } 

	public Value get(Key key) {
		Node p = getNode(key);
		return (p == null ? null : p.val);
	}

	final Node getNode(Key key) {
		if (key == null)
			throw new NullPointerException();
		Node p = root;
		while (p != null) {
			int cmp = key.compareTo(p.key);
			if (cmp < 0)
				p = p.left;
			else if (cmp > 0)
				p = p.right;
			else
				return p;
		}
		return null;
	}

	private void rotateRight(Node node) {
		if (node != null) {
			// 将L设置为node的左子树
			Node left = node.left;
			// 将left的右子树设置为node的左子树
			node.left = left.right;
			// 若left的右子树不为空，则将node设置left的右子树的父节点
			if (left.right != null)
				left.right.parent = node;
			// 将node的父节点设置为left的父节点
			left.parent = node.parent;
			// 如果node的父节点为空，则将left设置根节点
			if (node.parent == null)
				root = left;
			// 若node为其父节点的右子树，则将left设置为node的父节点的右子树
			else if (node.parent.right == node)
				node.parent.right = left;
			// 否则将left设置为node的父节点的左子树
			else
				node.parent.left = left;
			// 将node设置为left的右子树
			left.right = node;
			// 将left设置为node的父节点
			node.parent = left;
		}
	}

	private class Node {
		private Key key; // sorted by key
		private Value val; // associated data
		private Node left, right, parent; // left and right subtrees
		private boolean color;

		public Node(Key key, Value val, Node parent) {
			this.key = key;
			this.val = val;
			this.parent = parent;
		}
	}

}