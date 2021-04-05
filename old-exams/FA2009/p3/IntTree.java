// Solve FA2009 Test#2, problem #3 to merge right a tree and a leaning tree

public class IntTree {
    public final int data;
    public IntTree left, right;
    public IntTree(int data, IntTree left, IntTree right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

   
    public static IntTree mergeRight(IntTree T, IntTree lean) {
        IntTree lptr = T.left;
        IntTree rptr = T.right;

        while (lean != null) {
            if (lean.data < T.data) {
                lptr = mInsert(lean.data, lptr);
            } else if (lean.data > T.data) {
                rptr = mInsert(lean.data, rptr);
            }
            lean = lean.right;
        }
       return T;
    }

    public static IntTree mInsert(int data, IntTree ptr) {
        IntTree ptr2 = ptr;

        while (ptr != null) {
            if (data < ptr.data) {
                ptr2 = ptr;
                ptr = ptr.left;
            } else if (data > ptr.data) {
                ptr2 = ptr;
                ptr = ptr.right;
            } else {
                return ptr;
            }
        }

       
        if (data < ptr2.data) {
            ptr2.left = new IntTree(data, null, null);
        } else {
            ptr2.right = new IntTree(data, null, null);
        } 
       return ptr2;
    }

   
   public void insert(int data){
      insert(data, this);
   }
   private IntTree insert(int data, IntTree node){
      if(node == null){
         return new IntTree(data, null, null);
      }
      if(data < node.data){
         node.left = insert(data, node.left);
      } else if (data > node.data){
         node.right = insert(data, node.right);
      }
      return node;
   }
   
   public static void main(String[] args){
      IntTree t = new IntTree(12, null, null);
      t.insert(3);
      t.insert(10);
      t.insert(8);
      t.insert(16);
      t.insert(14);
      t.insert(27);
      
      IntTree lean = new IntTree(1, null, null);
      lean.insert(11);
      lean.insert(26);
      mergeRight(t, lean);
   }
}
