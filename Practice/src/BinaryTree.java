import java.util.*;

public class BinaryTree {
    static class Node {
        int val;
        Node left;
        Node right;
        Node(){}
        Node(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

        static int idx = -1;


        public static Node buildTree(int nodes[]) {
            idx++;
            if (nodes[idx] == -1) {
                return null;
            }
            Node newNode = new Node(nodes[idx]);
            newNode.left = buildTree(nodes);
            newNode.right = buildTree(nodes);
            return newNode;
        }

        public static void preorder(Node root){
            if(root==null){
                System.out.print(-1+" ");
                return;
            }
            System.out.print(root.val+" ");
            preorder(root.left);
            preorder(root.right);
        }
        public static void inorder(Node root){
            if(root ==  null){
                return;
            }
            inorder(root.left);
            System.out.print(root.val+" ");
            inorder(root.right);
        }
        public static void postorder(Node root) {
            if (root == null) {
                return;
            }
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.val + " ");

        }
        public static void levelorder(Node root){
            if(root==null)return;
            Queue<Node> q = new LinkedList<>();
            q.add(root);
            q.add(null);
            List<List<Integer>> ans = new ArrayList<>();
            List<Integer> temp= new ArrayList<>();
            while(!q.isEmpty()){
                Node cur = q.remove();
                if(cur==null){
                    System.out.println();
                    ans.add(temp);
                    temp= new ArrayList<>();
                    if(q.isEmpty()){
                        break;
                    }else{
                        q.add(null);
                    }
                }
                else{
                    System.out.print(cur.val+" ");
                    temp.add(cur.val);
                    if(cur.left!=null){
                        q.add(cur.left);
                    }
                    if(cur.right!=null){
                        q.add(cur.right);
                    }
                }
            }
            System.out.println(ans);

        }

        public static int countNode(Node root){
            if(root == null){
                return 0;
            }
            int left=countNode(root.left);
            int right = countNode(root.right);
            return left+right+1;
        }
        public static int sumNodes(Node root){
            if(root == null)return 0;
            int sum =0;
            sum+=root.val+sumNodes(root.left)+sumNodes(root.right);
            return sum;
        }
        public static int heightOfTree(Node root){
            if(root==null)return 0;
            int left = heightOfTree(root.left);
            int right =heightOfTree(root.right);
            return 1+Math.max(left,right);

        }

         static class treeInfo{
            int ht,diam;
            treeInfo(int ht , int diam){
                this.ht = ht;
                this.diam =  diam;
            }
        }
        public static treeInfo diameter(Node root){
            if(root==null)return new treeInfo(0,0);
            treeInfo left=diameter(root.left);
            treeInfo right = diameter(root.right);

            int myHeight= Math.max(left.ht , right.ht)+1;
            int d1= left.diam;
            int d2=right.diam;
            int d3=left.ht+right.ht+1;
            int myDiam=Math.max(Math.max(d1,d2),d3);
            treeInfo myTree = new treeInfo(myHeight,myDiam);
            return myTree;
        }
        static int  tiltans;
        public static  int tilt(Node root){
            if(root==null)return 0;
            int lsum=tilt(root.left);
            int rsum=tilt(root.right);
            int ltilt=Math.abs(lsum-rsum);
            System.out.println("val="+root.val+"tilt="+ltilt);
            tiltans+=ltilt;
            return lsum+rsum+root.val;

        }

        public static void singleChild(Node root){
            if(root==null)return;
            if(root.left!=null && root.right == null){
                System.out.println(root.left.val);
            }
            if(root.left==null && root.right != null){
                System.out.println(root.right.val);
            }
            singleChild(root.left);
            singleChild(root.right);
        }

       static ArrayList<Integer> ans = new ArrayList<>();
        public static boolean nodeToRootPath(Node root,int data){
            if(root==null)return false;
            if(root.val ==data) {
                ans.add(root.val);
                return true;}
            boolean left = nodeToRootPath(root.left,data);
            if(left) {
                ans.add(root.val);
                return true;
            }
            boolean right = nodeToRootPath(root.right,data);
            if(right) {
                ans.add(root.val);
                return true;
            }
            return false;
        }
        public static Node invertTree(Node root) {
            if(root==null)return null;
            Node left = invertTree(root.left);
            Node right = invertTree(root.right);
            root.left = right;
            root.right = left;
            return root;
        }
        //iterative preorder
        public static List<Integer> itrPreorder(Node root){
            List<Integer> ans = new ArrayList<Integer>();
            if(root==null)return ans;
            Stack<Node> st = new Stack<>();
            st.push(root);
            while(!st.isEmpty()){
                root=st.pop();
                ans.add(root.val);
                if(root.right!=null){
                    st.push(root.right);
                }
                if(root.left!=null){
                    st.push(root.left);

                }
            }
            return ans;
        }

    public void rightView(Node root,int height){
        if(root==null)return;

        if(ans.size()==height){
            ans.add(root.val);
        }
        rightView(root.right,height+1);
        rightView(root.left,height+1);
    }


    public static void main(String args[]) {
        int nodes[] = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1,-1};

        Node root = buildTree(nodes);
        System.out.println(root.val);
//        preorder(root);
//        inorder(root);
//        postorder(root);
//        levelorder(root);
//        System.out.println(countNode(root));
//        System.out.println(sumNodes(root));
//        System.out.println(heightOfTree(root));
//        System.out.println(diameter(root).diam);
//        System.out.println(tilt(root)+" tilt="+ tiltans);
//        singleChild(root);
//        nodeToRootPath(root,5);
//        System.out.println(ans);
//       levelorder( invertTree(root));
//        System.out.println(itrPreorder(root));
    }
}
