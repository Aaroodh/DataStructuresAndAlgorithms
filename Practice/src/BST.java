import java.util.ArrayList;

public class BST {


    static class Node{
        int val;
        Node left,right;
        Node(int data){
            this.val = data;
            this.left=null;
            this.right = null;
        }

    }

    public static Node insert(Node root, int val){
        if(root==null){
            root= new Node(val);
            return root;
        }
        if(root.val>val){
          root.left =  insert(root.left,val);
        }
        else{
            root.right = insert(root.right,val);
        }
        return root;
    }

    public static void inorder(Node root)
    {
        if(root==null)return;
        inorder(root.left);
        System.out.print(root.val+"->");
        inorder(root.right);
    }


    public static boolean search(Node root,int key){
        if(root==null)return false;
        if(root.val == key) {
            System.out.println("key found");
            return true;
        }
        if(root.val>key)
            return search(root.left,key);
        else
            return search(root.right,key);
    }

    public static Node delete(Node root,int val){
        if(root==null)return root;
        if(root.val > val)
            root.left =  delete(root.left,val);
        else if (root.val<val)
            root.right = delete(root.right,val);
        else{
            if(root.left == null && root.right==null) {
                return null;
            }
            if(root.left==null) {
                return root.right;
            }
            else if (root.right==null) {
                return root.left;
            }
            Node IS = inorderSuccessor(root.right);
            root.val = IS.val;
            root.right =  delete(root.right,IS.val);
        }
        return root;
    }

    private static Node inorderSuccessor(Node root) {
        while (root.left!=null){
            root=root.left;
        }
        return root;
    }

    public static void printRange(Node root,int X,int Y){
        if(root==null)return;
        if(root.val >=X && root.val<=Y){
            printRange(root.left,X,Y);
            System.out.print(root.val+" ");
            printRange(root.right,X,Y);

        }
        else if(root.val>=Y){
            printRange(root.left,X,Y);
        }
        else {
            printRange(root.right, X, Y);
        }
    }

    public static void rootToLeaf(Node root, ArrayList<Integer> path){
        if(root==null) return;

        path.add(root.val);

        if(root.left==null && root.right == null){
            System.out.println(path.toString());
        }
        else{
            rootToLeaf(root.left, path);
            rootToLeaf(root.right, path);
        }
        path.remove(path.size()-1);
    }
    public static int rangeSumBST(Node root, int low, int high) {
         int ans = 0;
        if(root==null)return 0 ;
        if(root.val>=low && root.val<=high){
            ans=ans+root.val+rangeSumBST(root.left,low,high)+rangeSumBST(root.right,low,high);
        }
        else if(root.val>high){
            return rangeSumBST(root.left,low,high);
        }
        else{
            return rangeSumBST(root.right,low,high);
        }
        return ans;
    }

    public static void main(String[] args) {
        int values[] = {8,5,3,1,4,6,10,11,14};
        Node root = null;
        for(int val : values){
            root = insert(root,val);
        }
        inorder(root);
        System.out.println();
//        System.out.println(search(root,4));
       /* Node delete = delete(root,4);
        inorder(root);*/
//        printRange(root,6,10);
        /*ArrayList<Integer> ans = new ArrayList<>();
        rootToLeaf(root,ans);*/
        System.out.println(rangeSumBST(root,4,10));
    }

}
