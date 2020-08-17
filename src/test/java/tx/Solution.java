package tx;

class Solution {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (t == null ) return true;
        if (s == null ) return false;

        return isSubtree(s.left,t) || isSubtree(s.right,t)  || isSameTree(s,t);
    }

    public boolean isSameTree(TreeNode one,TreeNode two){
        if (one == null && two == null){
            return  true;
        }
        if (one == null || two == null){
            return false;
        }

        if (one.value != two.value){
            return false;
        }else {
           return  isSameTree(one.left,two.right) && isSameTree(one.right,two.right);
        }
    }


    class TreeNode{
         int value;
         TreeNode left;
         TreeNode right;
         TreeNode(int value){
             this.value = value;
         }
         TreeNode(int value,TreeNode left,TreeNode right){
             this.value  = value;
             this.left = left;
             this.right = right;
         }
    }
}

