package easy.effective.coding.data_structure;

import java.util.*;

/**
 * 二叉树构造、遍历
 * 深度优先遍历－前／中／后序遍历：1、递归法  2、非递归（利用栈）
 * 广度优先遍历－层序遍历（利用队列）
 */
public class BinTree {
    private BinTree lChild;
    private BinTree rChild;
    private BinTree root;
    private Object data;

    public BinTree(){}

    public BinTree(BinTree lChild, BinTree rChild, Object data){
        this.lChild = lChild;
        this.rChild = rChild;
        this.data = data;
    }

    public BinTree(Object data){
        this(null,null,data);
    }

    /**
     * 根据给定的数组构造一颗树
     */
    public void createTree(Object[] objects){
        List<BinTree> datas = new ArrayList<>();
        for(Object obj:objects){
            datas.add(new BinTree(obj));
        }
        root = datas.get(0);
        for (int i=0;i<objects.length/2;i++){
            datas.get(i).lChild = datas.get(2*i+1);
            if(2*i+2 < objects.length) {
                datas.get(i).rChild = datas.get(2 * i + 2);
            }
        }
    }

    /**
     * 先序遍历（递归法）
     * 根－左－右
     */
    public void preorder(BinTree root){
        if(root!=null){
            visit(root.getData());
            preorder(root.lChild);
            preorder(root.rChild);
        }
    }

    /**
     * 中序遍历
     * 左－根－右
     */
    public void inorder(BinTree root){
        if(root!=null){
            inorder(root.lChild);
            visit(root.getData());
            inorder(root.rChild);
        }
    }

    /**
     * 后序遍历
     * 左－右－中
     */
    public void afterorder(BinTree root){
        if(root!=null){
            afterorder(root.lChild);
            afterorder(root.rChild);
            visit(root.getData());
        }
    }

    /**
     * 先序遍历（栈）
     * 根－左－右
     */
    public void preorderWithStack(BinTree root){
        if(root==null){return;}
        Stack<BinTree> stack = new Stack<>();
        stack.push(root);
        while (stack.isEmpty()==false){
            BinTree binTree = stack.pop();
            visit(binTree.getData());
            if(binTree.rChild!=null){
                stack.push(binTree.rChild);
            }
            if(binTree.lChild!=null){
                stack.push(binTree.lChild);
            }
        }
    }

    /**
     * 中序遍历（栈）
     * 左－根 —右
     */
    public void inorderWithStack(BinTree root){
        if(root==null){return;}
        Stack<BinTree> stack = new Stack<>();
        stack.push(root);
        while (stack.isEmpty()==false){
            BinTree binTree = stack.pop();

            if(binTree.rChild!=null){
                stack.push(binTree.rChild);
            }
            if(binTree.lChild!=null){
                stack.push(binTree.lChild);
            }
            visit(binTree.getData());
        }
    }


    /**
     * 层序遍历（利用队列）
     */
    public void levelOrder(BinTree root){
        Queue<BinTree> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            BinTree binTree = queue.poll();
            visit(binTree.getData());
            if(binTree.lChild!=null){
                queue.offer(binTree.lChild);
            }
            if(binTree.rChild!=null){
                queue.offer(binTree.rChild);
            }
        }
    }


    private void visit(Object obj){
        System.out.print(obj+"");
    }

    public BinTree getRoot() {
        return root;
    }

    public Object getData() {
        return data;
    }

    public static void main(String[] args) {
        Object[] objs = {0,1,2,3,4,5,6,};
        BinTree binTree = new BinTree();
        binTree.createTree(objs);

        binTree.preorder(binTree.getRoot());
        System.out.println();
        binTree.inorder(binTree.getRoot());
        System.out.println();
        binTree.afterorder(binTree.getRoot());
        System.out.println();
        binTree.preorderWithStack(binTree.getRoot());
        System.out.println();
        binTree.levelOrder(binTree.getRoot());
    }

}
