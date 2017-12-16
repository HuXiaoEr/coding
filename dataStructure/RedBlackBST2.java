//package dataStructure;
//
//import java.util.TreeMap.Entry;
//
//import dataStructure.RedBlackBST.RedBlackBSTNode;
//
//public class RedBlackBST2<T extends Comparable<T>> {
//    private final RedBlackBSTNode<T> root;
//    //node number
//    private java.util.concurrent.atomic.AtomicLong size = 
//                    new java.util.concurrent.atomic.AtomicLong(0);
//
//    //in overwrite mode,all node's value can not  has same    value
//    //in non-overwrite mode,node can have same value, suggest don't use non-overwrite mode.
//    private volatile boolean overrideMode=true;
//
//    public RedBlackBST2(){
//        this.root = new RedBlackBSTNode<T>();
//    }
//
//    public RedBlackBST2(boolean overrideMode){
//        this();
//        this.overrideMode=overrideMode;
//    }
//
//
//    public boolean isOverrideMode() {
//        return overrideMode;
//    }
//
//    public void setOverrideMode(boolean overrideMode) {
//        this.overrideMode = overrideMode;
//    }
//
//    /**
//     * number of tree number
//     * @return
//     */
//    public long getSize() {
//        return size.get();
//    }
//    /**
//     * get the root node
//     * @return
//     */
//    private RedBlackBSTNode<T> getRoot(){
//        return root.getLeft();
//    }
//
//    /**
//     * add value to a new node,if this value exist in this tree,
//     * if value exist,it will return the exist value.otherwise return null
//     * if override mode is true,if value exist in the tree,
//     * it will override the old value in the tree
//     * 
//     * @param value
//     * @return
//     */
//    public T addNode(T value){
//        RedBlackBSTNode<T> t = new RedBlackBSTNode<T>(value);
//        return addNode(t);
//    }
//    
//    
//    
//    
//    private void fixAfterInsertion(Entry<K,V> x) {
//        x.color = RED;
//        //循环 直到 x不是根节点，且x的父节点不为红色  
//        while (x != null && x != root && x.parent.color == RED) {
//        	//如果X的父节点（P）是其父节点的父节点（G）的左节点
//            if (parentOf(x) == leftOf(parentOf(parentOf(x)))) {
//            	//获取X的叔节点(U)
//                Entry<K,V> y = rightOf(parentOf(parentOf(x)));
//                //如果X的叔节点（U） 为红色   情况 1 ：父结点是红色，祖父结点的另一个子结点（叔叔结点）是红色
//                if (colorOf(y) == RED) {
//                    setColor(parentOf(x), BLACK);
//                    setColor(y, BLACK);
//                    setColor(parentOf(parentOf(x)), RED);
//                    x = parentOf(parentOf(x));
//                } 
//                //如果X的叔节点（U为黑色）
//                else {
//                    if (x == rightOf(parentOf(x))) {
//                        x = parentOf(x);
//                        rotateLeft(x);
//                    }
//                    setColor(parentOf(x), BLACK);
//                    setColor(parentOf(parentOf(x)), RED);
//                    rotateRight(parentOf(parentOf(x)));
//                }
//            } else {
//                Entry<K,V> y = leftOf(parentOf(parentOf(x)));
//                if (colorOf(y) == RED) {
//                    setColor(parentOf(x), BLACK);
//                    setColor(y, BLACK);
//                    setColor(parentOf(parentOf(x)), RED);
//                    x = parentOf(parentOf(x));
//                } else {
//                    if (x == leftOf(parentOf(x))) {
//                        x = parentOf(x);
//                        rotateRight(x);
//                    }
//                    setColor(parentOf(x), BLACK);
//                    setColor(parentOf(parentOf(x)), RED);
//                    rotateLeft(parentOf(parentOf(x)));
//                }
//            }
//        }
//        root.color = BLACK;
//    }
//    
//    
//    
//    
//    
//    /**
//     * find the value by give value(include key,key used for search,
//     * other field is not used,@see compare method).if this value not exist return null
//     * @param value
//     * @return
//     */
//    public T find(T value){
//        RedBlackBSTNode<T> dataRoot = getRoot();
//        while(dataRoot!=null){
//            int cmp = dataRoot.getValue().compareTo(value);
//            if(cmp<0){
//                dataRoot = dataRoot.getRight();
//            }else if(cmp>0){
//                dataRoot = dataRoot.getLeft();
//            }else{
//                return dataRoot.getValue();
//            }
//        }
//        return null;
//    }
//    /**
//     * remove the node by give value,if this value not exists in tree return null
//     * @param value include search key
//     * @return the value contain in the removed node
//     */
//    public T remove(T value){
//        RedBlackBSTNode<T> dataRoot = getRoot();
//        RedBlackBSTNode<T> parent = root;
//
//        while(dataRoot!=null){
//            int cmp = dataRoot.getValue().compareTo(value);
//            if(cmp<0){
//                parent = dataRoot;
//                dataRoot = dataRoot.getRight();
//            }else if(cmp>0){
//                parent = dataRoot;
//                dataRoot = dataRoot.getLeft();
//            }else{
//                if(dataRoot.getRight()!=null){
//                    RedBlackBSTNode<T> min = removeMin(dataRoot.getRight());
//                    //x used for fix color balance
//                    RedBlackBSTNode<T> x = min.getRight()==null ? min.getParent() : min.getRight();
//                    boolean isParent = min.getRight()==null;
//
//                    min.setLeft(dataRoot.getLeft());
//                    setParent(dataRoot.getLeft(),min);
//                    if(parent.getLeft()==dataRoot){
//                        parent.setLeft(min);
//                    }else{
//                        parent.setRight(min);
//                    }
//                    setParent(min,parent);
//
//                    boolean curMinIsBlack = min.isBlack();
//                    //inherit dataRoot's color
//                    min.setRed(dataRoot.isRed());
//
//                    if(min!=dataRoot.getRight()){
//                        min.setRight(dataRoot.getRight());
//                        setParent(dataRoot.getRight(),min);
//                    }
//                    //remove a black node,need fix color
//                    if(curMinIsBlack){
//                        if(min!=dataRoot.getRight()){
//                            fixRemove(x,isParent);
//                        }else if(min.getRight()!=null){
//                            fixRemove(min.getRight(),false);
//                        }else{
//                            fixRemove(min,true);
//                        }
//                    }
//                }else{
//                    setParent(dataRoot.getLeft(),parent);
//                    if(parent.getLeft()==dataRoot){
//                        parent.setLeft(dataRoot.getLeft());
//                    }else{
//                        parent.setRight(dataRoot.getLeft());
//                    }
//                    //current node is black and tree is not empty
//                    if(dataRoot.isBlack() && !(root.getLeft()==null)){
//                        RedBlackBSTNode<T> x = dataRoot.getLeft()==null 
//                                            ? parent :dataRoot.getLeft();
//                        boolean isParent = dataRoot.getLeft()==null;
//                        fixRemove(x,isParent);
//                    }
//                }
//                setParent(dataRoot,null);
//                dataRoot.setLeft(null);
//                dataRoot.setRight(null);
//                if(getRoot()!=null){
//                    getRoot().setRed(false);
//                    getRoot().setParent(null);
//                }
//                size.decrementAndGet();
//                return dataRoot.getValue();
//            }
//        }
//        return null;
//    }
//    /**
//     * fix remove action
//     * @param node
//     * @param isParent
//     */
//    private void fixRemove(RedBlackBSTNode<T> node,boolean isParent){
//        RedBlackBSTNode<T> cur = isParent ? null : node;
//        boolean isRed = isParent ? false : node.isRed();
//        RedBlackBSTNode<T> parent = isParent ? node : node.getParent();
//
//        while(!isRed && !isRoot(cur)){
//            RedBlackBSTNode<T> sibling = getSibling(cur,parent);
//            //sibling is not null,due to before remove tree color is balance
//
//            //if cur is a left node
//            boolean isLeft = parent.getRight()==sibling;
//            if(sibling.isRed() && !isLeft){//case 1
//                //cur in right
//                parent.makeRed();
//                sibling.makeBlack();
//                rotateRight(parent);
//            }else if(sibling.isRed() && isLeft){
//                //cur in left
//                parent.makeRed();
//                sibling.makeBlack();
//                rotateLeft(parent);
//            }else if(isBlack(sibling.getLeft()) && isBlack(sibling.getRight())){//case 2
//                sibling.makeRed();
//                cur = parent;
//                isRed = cur.isRed();
//                parent=parent.getParent();
//            }else if(isLeft && !isBlack(sibling.getLeft()) 
//                                    && isBlack(sibling.getRight())){//case 3
//                sibling.makeRed();
//                sibling.getLeft().makeBlack();
//                rotateRight(sibling);
//            }else if(!isLeft && !isBlack(sibling.getRight()) 
//                                            && isBlack(sibling.getLeft()) ){
//                sibling.makeRed();
//                sibling.getRight().makeBlack();
//                rotateLeft(sibling);
//            }else if(isLeft && !isBlack(sibling.getRight())){//case 4
//                sibling.setRed(parent.isRed());
//                parent.makeBlack();
//                sibling.getRight().makeBlack();
//                rotateLeft(parent);
//                cur=getRoot();
//            }else if(!isLeft && !isBlack(sibling.getLeft())){
//                sibling.setRed(parent.isRed());
//                parent.makeBlack();
//                sibling.getLeft().makeBlack();
//                rotateRight(parent);
//                cur=getRoot();
//            }
//        }
//        if(isRed){
//            cur.makeBlack();
//        }
//        if(getRoot()!=null){
//            getRoot().setRed(false);
//            getRoot().setParent(null);
//        }
//
//    }
//    //get sibling node
//    private RedBlackBSTNode<T> getSibling(RedBlackBSTNode<T> node,RedBlackBSTNode<T> parent){
//        parent = node==null ? parent : node.getParent();
//        if(node==null){
//            return parent.getLeft()==null ? parent.getRight() : parent.getLeft();
//        }
//        if(node==parent.getLeft()){
//            return parent.getRight();
//        }else{
//            return parent.getLeft();
//        }
//    }
//
//    private boolean isBlack(RedBlackBSTNode<T> node){
//        return node==null || node.isBlack();
//    }
//    private boolean isRoot(RedBlackBSTNode<T> node){
//        return root.getLeft() == node && node.getParent()==null;
//    }
//    /**
//     * find the successor node
//     * @param node current node's right node
//     * @return
//     */
//    private RedBlackBSTNode<T> removeMin(RedBlackBSTNode<T> node){
//        //find the min node
//        RedBlackBSTNode<T> parent = node;
//        while(node!=null && node.getLeft()!=null){
//            parent = node;
//            node = node.getLeft();
//        }
//        //remove min node
//        if(parent==node){
//            return node;
//        }
//
//        parent.setLeft(node.getRight());
//        setParent(node.getRight(),parent);
//
//        //don't remove right pointer,it is used for fixed color balance
//        //node.setRight(null);
//        return node;
//    }
//
//
//
//    private T addNode(RedBlackBSTNode<T> node){
//        node.setLeft(null);
//        node.setRight(null);
//        node.setRed(true);
//        setParent(node,null);
//        if(root.getLeft()==null){
//            root.setLeft(node);
//            //root node is black
//            node.setRed(false);
//            size.incrementAndGet();
//        }else{
//            RedBlackBSTNode<T> x = findParentNode(node);
//            int cmp = x.getValue().compareTo(node.getValue());
//
//            if(this.overrideMode && cmp==0){
//                T v = x.getValue();
//                x.setValue(node.getValue());
//                return v;
//            }else if(cmp==0){
//                //value exists,ignore this node
//                return x.getValue();
//            }
//
//            setParent(node,x);
//
//            if(cmp>0){
//                x.setLeft(node);
//            }else{
//                x.setRight(node);
//            }
//
//            fixInsert(node);
//            size.incrementAndGet();
//        }
//        return null;
//    }
//
//    /**
//     * find the parent node to hold node x,if parent value equals x.value return parent.
//     * @param x
//     * @return
//     */
//    private RedBlackBSTNode<T> findParentNode(RedBlackBSTNode<T> x){
//        RedBlackBSTNode<T> dataRoot = getRoot();
//        RedBlackBSTNode<T> child = dataRoot;
//
//        while(child!=null){
//            int cmp = child.getValue().compareTo(x.getValue());
//            if(cmp==0){
//                return child;
//            }
//            if(cmp>0){
//                dataRoot = child;
//                child = child.getLeft();
//            }else if(cmp<0){
//                dataRoot = child;
//                child = child.getRight();
//            }
//        }
//        return dataRoot;
//    }
//
//    /**
//     * red black tree insert fix.
//     * @param x
//     */
//    private void fixInsert(RedBlackBSTNode<T> x){
//        RedBlackBSTNode<T> parent = x.getParent();
//
//        while(parent!=null && parent.isRed()){
//            RedBlackBSTNode<T> uncle = getUncle(x);
//            if(uncle==null){//need to rotate
//                RedBlackBSTNode<T> ancestor = parent.getParent();
//                //ancestor is not null due to before before add,tree color is balance
//                if(parent == ancestor.getLeft()){
//                    boolean isRight = x == parent.getRight();
//                    if(isRight){
//                        rotateLeft(parent);
//                    }
//                    rotateRight(ancestor);
//
//                    if(isRight){
//                        x.setRed(false);
//                        parent=null;//end loop
//                    }else{
//                        parent.setRed(false);
//                    }
//                    ancestor.setRed(true);
//                }else{
//                    boolean isLeft = x == parent.getLeft();
//                    if(isLeft){
//                        rotateRight(parent);
//                    }
//                    rotateLeft(ancestor);
//
//                    if(isLeft){
//                        x.setRed(false);
//                        parent=null;//end loop
//                    }else{
//                        parent.setRed(false);
//                    }
//                    ancestor.setRed(true);
//                }
//            }else{//uncle is red
//                parent.setRed(false);
//                uncle.setRed(false);
//                parent.getParent().setRed(true);
//                x=parent.getParent();
//                parent = x.getParent();
//            }
//        }
//        getRoot().makeBlack();
//        getRoot().setParent(null);
//    }
//    /**
//     * get uncle node
//     * @param node
//     * @return
//     */
//    private RedBlackBSTNode<T> getUncle(RedBlackBSTNode<T> node){
//        RedBlackBSTNode<T> parent = node.getParent();
//        RedBlackBSTNode<T> ancestor = parent.getParent();
//        if(ancestor==null){
//            return null;
//        }
//        if(parent == ancestor.getLeft()){
//            return ancestor.getRight();
//        }else{
//            return ancestor.getLeft();
//        }
//    }
//    
//    public V put(K key, V value) {  
//        //用t表示二叉树的当前节点  
//         Entry<K,V> t = root;  
//         //t为null表示一个空树，即TreeMap中没有任何元素，直接插入  
//         if (t == null) {  
//             //比较key值，个人觉得这句代码没有任何意义，空树还需要比较、排序？  
//             compare(key, key); // type (and possibly null) check  
//             //将新的key-value键值对创建为一个Entry节点，并将该节点赋予给root  
//             root = new Entry<>(key, value, null);  
//             //容器的size = 1，表示TreeMap集合中存在一个元素  
//             size = 1;  
//             //修改次数 + 1  
//             modCount++;  
//             return null;  
//         }  
//         int cmp;     //cmp表示key排序的返回结果  
//         Entry<K,V> parent;   //父节点  
//         // split comparator and comparable paths  
//         Comparator<? super K> cpr = comparator;    //指定的排序算法  
//         //如果cpr不为空，则采用既定的排序算法进行创建TreeMap集合  
//         if (cpr != null) {  
//             do {  
//                 parent = t;      //parent指向上次循环后的t  
//                 //比较新增节点的key和当前节点key的大小  
//                 cmp = cpr.compare(key, t.key);  
//                 //cmp返回值小于0，表示新增节点的key小于当前节点的key，则以当前节点的左子节点作为新的当前节点  
//                 if (cmp < 0)  
//                     t = t.left;  
//                 //cmp返回值大于0，表示新增节点的key大于当前节点的key，则以当前节点的右子节点作为新的当前节点  
//                 else if (cmp > 0)  
//                     t = t.right;  
//                 //cmp返回值等于0，表示两个key值相等，则新值覆盖旧值，并返回新值  
//                 else  
//                     return t.setValue(value);  
//             } while (t != null);  
//         }  
//         //如果cpr为空，则采用默认的排序算法进行创建TreeMap集合  
//         else {  
//             if (key == null)     //key值为空抛出异常  
//                 throw new NullPointerException();  
//             /* 下面处理过程和上面一样 */  
//             Comparable<? super K> k = (Comparable<? super K>) key;  
//             do {  
//                 parent = t;  
//                 cmp = k.compareTo(t.key);  
//                 if (cmp < 0)  
//                     t = t.left;  
//                 else if (cmp > 0)  
//                     t = t.right;  
//                 else  
//                     return t.setValue(value);  
//             } while (t != null);  
//         }  
//         //将新增节点当做parent的子节点  
//         Entry<K,V> e = new Entry<>(key, value, parent);  
//         //如果新增节点的key小于parent的key，则当做左子节点  
//         if (cmp < 0)  
//             parent.left = e;  
//       //如果新增节点的key大于parent的key，则当做右子节点  
//         else  
//             parent.right = e;  
//         /*  
//          *  上面已经完成了排序二叉树的的构建，将新增节点插入该树中的合适位置  
//          *  下面fixAfterInsertion()方法就是对这棵树进行调整、平衡，具体过程参考上面的五种情况  
//          */  
//         fixAfterInsertion(e);  
//         //TreeMap元素数量 + 1  
//         size++;  
//         //TreeMap容器修改次数 + 1  
//         modCount++;  
//         return null;  
//     }  
//    
//
//    private void rotateRight(RedBlackBSTNode<T> node){
//        RedBlackBSTNode<T> left = node.getLeft();
//        if(left==null){
//            throw new java.lang.IllegalStateException("left node is null");
//        }
//        RedBlackBSTNode<T> parent = node.getParent();
//        node.setLeft(left.getRight());
//        setParent(left.getRight(),node);
//
//        left.setRight(node);
//        setParent(node,left);
//
//        if(parent==null){
//            root.setLeft(left);
//            setParent(left,null);
//        }else{
//            if(parent.getLeft()==node){
//                parent.setLeft(left);
//            }else{
//                parent.setRight(left);
//            }
//            setParent(left,parent);
//        }
//    }
//
//
//    private void setParent(RedBlackBSTNode<T> node,RedBlackBSTNode<T> parent){
//        if(node!=null){
//            node.setParent(parent);
//            if(parent==root){
//                node.setParent(null);
//            }
//        }
//    }
//    /**
//     * debug method,it used print the given node and its children nodes,
//     * every layer output in one line
//     * @param root
//     */
//    public void printTree(RedBlackBSTNode<T> root){
//        java.util.LinkedList<RedBlackBSTNode<T>> queue =new java.util.LinkedList<RedBlackBSTNode<T>>();
//        java.util.LinkedList<RedBlackBSTNode<T>> queue2 =new java.util.LinkedList<RedBlackBSTNode<T>>();
//        if(root==null){
//            return ;
//        }
//        queue.add(root);
//        boolean firstQueue = true;
//
//        while(!queue.isEmpty() || !queue2.isEmpty()){
//            java.util.LinkedList<RedBlackBSTNode<T>> q = firstQueue ? queue : queue2;
//            RedBlackBSTNode<T> n = q.poll();
//
//            if(n!=null){
//                String pos = n.getParent()==null ? "" : ( n == n.getParent().getLeft() 
//                                                                        ? " LE" : " RI");
//                String pstr = n.getParent()==null ? "" : n.getParent().toString();
//                String cstr = n.isRed()?"R":"B";
//                cstr = n.getParent()==null ? cstr : cstr+" ";
//                System.out.print(n+"("+(cstr)+pstr+(pos)+")"+"\t");
//                if(n.getLeft()!=null){
//                    (firstQueue ? queue2 : queue).add(n.getLeft());
//                }
//                if(n.getRight()!=null){
//                    (firstQueue ? queue2 : queue).add(n.getRight());
//                }
//            }else{
//                System.out.println();
//                firstQueue = !firstQueue;
//            }
//        }
//    }
//
//    private class RedBlackBSTNode<T extends Comparable<T>> {
//        private T value;//node value
//        private RedBlackBSTNode<T> left;//left child pointer
//        private RedBlackBSTNode<T> right;//right child pointer
//        private RedBlackBSTNode<T> parent;//parent pointer
//        private boolean red;//color is red or not red
//
//        public RedBlackBSTNode(){}
//        public RedBlackBSTNode(T value){this.value=value;}
//        public RedBlackBSTNode(T value,boolean isRed){this.value=value;this.red = isRed;}
//
//        public T getValue() {
//            return value;
//        }
//        void setValue(T value) {
//            this.value = value;
//        }
//        RedBlackBSTNode<T> getLeft() {
//            return left;
//        }
//        void setLeft(RedBlackBSTNode<T> left) {
//            this.left = left;
//        }
//        RedBlackBSTNode<T> getRight() {
//            return right;
//        }
//        void setRight(RedBlackBSTNode<T> right) {
//            this.right = right;
//        }
//        RedBlackBSTNode<T> getParent() {
//            return parent;
//        }
//        void setParent(RedBlackBSTNode<T> parent) {
//            this.parent = parent;
//        }
//        boolean isRed() {
//            return red;
//        }
//        boolean isBlack(){
//            return !red;
//        }
//        /**
//        * is leaf node
//        **/
//        boolean isLeaf(){
//            return left==null && right==null;
//        }
//
//        void setRed(boolean red) {
//            this.red = red;
//        }
//
//        void makeRed(){
//            red=true;
//        }
//        void makeBlack(){
//            red=false;
//        }
//        @Override
//        public String toString(){
//            return value.toString();
//        }
//    }
//    public static void main(String[] args) {
//        RedBlackBST2<String> bst = new RedBlackBST2<String>();
//        bst.addNode("d");
//        bst.addNode("d");
//        bst.addNode("c");
//        bst.addNode("c");
//        bst.addNode("b");
//        bst.addNode("f");
//
//        bst.addNode("a");
//        bst.addNode("e");
//
//        bst.addNode("g");
//        bst.addNode("h");
//
//
//        bst.remove("c");
//
//        bst.printTree(bst.getRoot());
//    }
//}