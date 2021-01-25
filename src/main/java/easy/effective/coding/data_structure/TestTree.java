package easy.effective.coding.data_structure;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TestTree {

    public static void main(String[] args) {
        Object[] objs = {0,1,2,3,4,5,6};
        List<TreeNode> tree = createTree(objs);

        System.out.println("前序");
        preorder(tree.get(0));
        System.out.println();

        System.out.println("中序");
        inOrderTraverse(tree.get(0));
        System.out.println();

    }

    static List<TreeNode> createTree(Object[] objects){
        List<TreeNode> nodes = new ArrayList<>();
        for(int i =0;i<objects.length;i++){
            nodes.add(new TreeNode(objects[i]));
        }

        for (int i=0;i<objects.length/2;i++){
            nodes.get(i).setlChild(nodes.get(2*i+1));
            if(2*i+2 < objects.length) {
                nodes.get(i).setrChild(nodes.get(2*i+2));
            }
        }

        return nodes;
    }

    /**
     * 先序遍历（递归法）
     * 根－左－右
     */
    public static void preorder(TreeNode node) {
        if(node!=null){
            System.out.print(node.getData());
            preorder(node.getlChild());
            preorder(node.getrChild());
        }
    }

    /**
     * 中序遍历（递归法）
     * 左－根-右
     */
    public static void inorder(TreeNode node) {
        if(node!=null){
            inorder(node.getlChild());
            System.out.print(node.getData());
            inorder(node.getrChild());
        }
    }

    /**
     * 中序遍历（栈）
     * 左－根 —右
     */
    public static void inOrderTraverse(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.getlChild();
            } else {
                TreeNode tem = stack.pop();
                System.out.print(tem.getData() + "->");
                node = tem.getrChild();
            }
        }
    }

}
