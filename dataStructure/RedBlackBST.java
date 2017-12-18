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
			// ���µ�key-value��ֵ�Դ���Ϊһ��Entry�ڵ㣬�����ýڵ㸳���root
			root = new Node(key, value, null);
			// ������size = 1����ʾTreeMap�����д���һ��Ԫ��
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
		// �������ڵ㵱��parent���ӽڵ�
		Node newNode = new Node(key, value, parent);
		// ��������ڵ��keyС��parent��key���������ӽڵ�
		if (cmp < 0)
			parent.left = newNode;
		// ��������ڵ��key����parent��key���������ӽڵ�
		else
			parent.right = newNode;
		/*
		 * �����Ѿ����������������ĵĹ������������ڵ��������еĺ���λ��
		 * ����fixAfterInsertion()�������Ƕ���������е�����ƽ�⣬������̲ο�������������
		 */
		fixAfterInsertion(newNode);
		// TreeMapԪ������ + 1
		size++;
		// TreeMap�����޸Ĵ��� + 1
		return null;
	}

	/**
	 * �����ڵ����޸����� x ��ʾ�����ڵ�
	 */
	private void fixAfterInsertion(Node x) {
		x.color = RED; // �����ڵ����ɫΪ��ɫ

		// ѭ�� ֱ�� x���Ǹ��ڵ㣬��x�ĸ��ڵ㲻Ϊ��ɫ
		while (x != null && x != root && x.parent.color == RED) {
			// ���X�ĸ��ڵ㣨P�����丸�ڵ�ĸ��ڵ㣨G������ڵ�
			if (x.parent == x.parent.parent.left) {
				// ��ȡX����ڵ�(U)
				Node y = x.parent.parent.right;
				// ���X����ڵ㣨U�� Ϊ��ɫ���������
				if (colorOf(y) == RED) {
					// ��X�ĸ��ڵ㣨P������Ϊ��ɫ
					x.parent.color = BLACK;
					// ��X����ڵ㣨U������Ϊ��ɫ
					y.color = BLACK;
					// ��X�ĸ��ڵ�ĸ��ڵ㣨G�����ú�ɫ
					x.parent.parent.color = RED;
					x = x.parent.parent;
				}
				// ���X����ڵ㣨UΪ��ɫ�������������������������ġ�����壩
				else {
					// ���X�ڵ�Ϊ�丸�ڵ㣨P���������������������ת������ģ�
					if (x == x.parent.right) {
						// ��X�ĸ��ڵ���ΪX
						x = x.parent;
						// ����ת
						rotateLeft(x);
					}
					// ������壩
					// ��X�ĸ��ڵ㣨P������Ϊ��ɫ
					x.parent.color = BLACK;
					// ��X�ĸ��ڵ�ĸ��ڵ㣨G�����ú�ɫ
					x.parent.parent.color = RED;
					// ��X�ĸ��ڵ�ĸ��ڵ㣨G��Ϊ��������ת
					rotateRight(x.parent.parent);
				}
			}
			// ���X�ĸ��ڵ㣨P�����丸�ڵ�ĸ��ڵ㣨G�����ҽڵ�
			else {
				// ��ȡX����ڵ㣨U��
				Node y = x.parent.parent.left;
				// ���X����ڵ㣨U�� Ϊ��ɫ���������
				if (colorOf(y) == RED) {
					// ��X�ĸ��ڵ㣨P������Ϊ��ɫ
					x.parent.color = BLACK;
					// ��X����ڵ㣨U������Ϊ��ɫ
					y.color = BLACK;
					// ��X�ĸ��ڵ�ĸ��ڵ㣨G�����ú�ɫ
					x.parent.parent.color = RED;
					x = x.parent.parent;
				}
				// ���X����ڵ㣨UΪ��ɫ�������������������������ġ�����壩
				else {
					// ���X�ڵ�Ϊ�丸�ڵ㣨P���������������������ת������ģ�
					if (x == x.parent.left) {
						// ��X�ĸ��ڵ���ΪX
						x = x.parent;
						// ����ת
						rotateRight(x);
					}
					// ������壩
					// ��X�ĸ��ڵ㣨P������Ϊ��ɫ
					x.parent.color = BLACK;
					// ��X�ĸ��ڵ�ĸ��ڵ㣨G�����ú�ɫ
					x.parent.parent.color = RED;
					// ��X�ĸ��ڵ�ĸ��ڵ㣨G��Ϊ��������ת
					rotateLeft(x.parent.parent);
				}
			}
		}
		// �����ڵ�Gǿ������Ϊ��ɫ
		root.color = BLACK;
	}

	private void rotateLeft(Node node) {
		if (node != null) {
			// ��ȡnode�����ӽڵ㣬��ʵ������൱�������ڵ�N������Ķ��ԣ�
			Node right = node.right;
			// ��right������������Ϊnode��������
			right.right = right.left;
			// ��right����������Ϊ�գ���node����Ϊright�������ĸ���
			if (right.left != null)
				right.left.parent = node;
			// ��node�ĸ�������right�ĸ���
			right.parent = node.parent;
			// ���node�ĸ���Ϊ�գ���right����Ϊ���ڵ�
			if (node.parent == null)
				root = right;
			// ���nodeΪ�丸�ڵ㣨G��������������right����Ϊnode���ڵ�(G)������
			else if (node.parent.left == node)
				node.parent.left = right;
			// ����right����Ϊnode�ĸ��ڵ㣨G����������
			else
				node.parent.right = right;
			// ��node����Ϊright��������
			right.left = node;
			// ��right����Ϊnode�ĸ��ڵ�
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
		size--; // Ԫ�ظ��� -1
		/*
		 * ��ɾ���ڵ��������������������Ϊ�գ���ô���� p�ڵ�������̽ڵ���� p �ڵ�
		 * successor(P)����ΪѰ��P������ڵ㡣�������ҷ�֧����ߣ����� ���֧���ұߵĽڵ�
		 * ---------------------��1��
		 */
		if (p.left != null && p.right != null) {
			Node s = successor(p);
			p.key = s.key;
			p.val = s.val;
			p = s;
		}

		// replacementΪ����ڵ㣬���P��������������ô������������������������������
		Node replacement = (p.left != null ? p.left : p.right);

		/*
		 * ɾ���ڵ㣬��Ϊ�����ᵽ��������� -----------------------��2��
		 */
		// �������ڵ㲻Ϊ��
		if (replacement != null) {
			replacement.parent = p.parent;
			/*
			 * replacement�����P�ڵ�
			 */
			// ��Pû�и��ڵ㣬����ڵ�ֱ�ӱ��replacement
			if (p.parent == null)
				root = replacement;
			// ���PΪ��ڵ㣬����replacement�����Ϊ��ڵ�
			else if (p == p.parent.left)
				p.parent.left = replacement;
			// ���PΪ�ҽڵ㣬����replacement�����Ϊ�ҽڵ�
			else
				p.parent.right = replacement;

			// ͬʱ��P�ڵ����������޳���
			p.left = p.right = p.parent = null;

			/*
			 * ��PΪ��ɫֱ��ɾ�������������ƽ�� ������PΪ��ɫ������Ҫ���������ʹ�䱣��ƽ��
			 */
			if (p.color == BLACK)
				fixAfterDeletion(replacement);
		} else if (p.parent == null) { // pû�и��ڵ㣬��ʾΪP���ڵ㣬ֱ��ɾ������
			root = null;
		} else { // P�ڵ㲻�����ӽڵ㣬ֱ��ɾ������
			if (p.color == BLACK) // ���P�ڵ����ɫΪ��ɫ���Ժ�������е���
				fixAfterDeletion(p);

			// ɾ��P�ڵ�
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
        // ɾ���ڵ���Ҫһֱ������֪�� ֱ�� x ���Ǹ��ڵ㣬�� x ����ɫ�Ǻ�ɫ  
        while (x != root && colorOf(x) == BLACK) {  
            if (x == x.parent.left) {      //��X�ڵ�Ϊ��ڵ�  
                //��ȡ���ֵܽڵ�  
                Node sib = x.parent.right;  
  
                /* 
                 * ����ֵܽڵ�Ϊ��ɫ----�����3.1�� 
                 * ���ԣ��ı�W��P����ɫ��Ȼ�����һ������ת 
                 */  
                if (colorOf(sib) == RED) {  
                	sib.color = BLACK;
                	x.parent.color = RED;
                    rotateLeft(x.parent);  
                    sib = x.parent.right;  
                }  
  
                /* 
                 * ���ֵܽڵ�������ӽڵ㶼Ϊ��ɫ----�����3.2�� 
                 * ���ԣ����ֵܽڵ��̺�ɫ 
                 */  
                if (colorOf(sib.left)  == BLACK &&  
                    colorOf(sib.right) == BLACK) {  
                	sib.color = RED;
                    x = x.parent;  
                }   
                else {  
                    /* 
                     * ����ֵܽڵ�ֻ��������Ϊ��ɫ----�����3.3�� 
                     * ���ԣ����ֵܽڵ�����������������ɫ����Ȼ�������ת 
                     * ��ʱ�����ת��Ϊ3.4 
                     */  
                    if (colorOf(sib.right) == BLACK) {  
                    	sib.left.color = BLACK;
                    	sib.color = RED;
                        rotateRight(sib);  
                        sib = x.parent.right;  
                    }  
                    /* 
                     *----���3.4 
                     *���ԣ������ֵܽڵ�͸��ڵ����ɫ�� 
                     *ͬʱ���ֵܽڵ�����������Ϊ��ɫ���������ת 
                     */  
                    sib.color = x.parent.color;
                    x.parent.color = BLACK;
                    sib.right.color = BLACK;
                    rotateLeft(x.parent);  
                    x = root;  
                }  
            }   
              
            /** 
             * X�ڵ�Ϊ�ҽڵ�����Ϊ���ڵ㴦����̲�࣬����Ͳ��������� 
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
         * Ѱ������������������ 
         */  
        else if (t.right != null) {  
            Node p = t.right;  
            while (p.left != null)  
                p = p.left;  
            return p;  
        }   
        /* 
         * ѡ������������������ 
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
			// ��L����Ϊnode��������
			Node left = node.left;
			// ��left������������Ϊnode��������
			node.left = left.right;
			// ��left����������Ϊ�գ���node����left���������ĸ��ڵ�
			if (left.right != null)
				left.right.parent = node;
			// ��node�ĸ��ڵ�����Ϊleft�ĸ��ڵ�
			left.parent = node.parent;
			// ���node�ĸ��ڵ�Ϊ�գ���left���ø��ڵ�
			if (node.parent == null)
				root = left;
			// ��nodeΪ�丸�ڵ������������left����Ϊnode�ĸ��ڵ��������
			else if (node.parent.right == node)
				node.parent.right = left;
			// ����left����Ϊnode�ĸ��ڵ��������
			else
				node.parent.left = left;
			// ��node����Ϊleft��������
			left.right = node;
			// ��left����Ϊnode�ĸ��ڵ�
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