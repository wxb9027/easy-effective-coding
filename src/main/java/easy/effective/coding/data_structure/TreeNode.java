package easy.effective.coding.data_structure;

public class TreeNode {

    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public TreeNode getlChild() {
        return lChild;
    }

    public void setlChild(TreeNode lChild) {
        this.lChild = lChild;
    }

    public TreeNode getrChild() {
        return rChild;
    }

    public void setrChild(TreeNode rChild) {
        this.rChild = rChild;
    }

    private TreeNode lChild;
    private TreeNode rChild;

    TreeNode(Object data){
        this.data = data;
    }

}
