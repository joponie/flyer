package demo.concurrent.tree;

/**
 * @author kain
 * @since 2020-04-17
 */
public class TreeBuildTest {

    public static void main(String[] args) {
        Tree leftTree = new Tree(200, new Tree(34), new Tree(123));
        Tree rightTree = new Tree(343, new Tree(45), new Tree(11));
        Tree integerTree = new Tree(100, leftTree, rightTree);

        Tree tree = createTree(new int[]{100, 200, 34, 123, 343, 45, 11}, new int[]{34, 200, 123, 100, 45, 343, 11});
        tree.getValue();

//        Tree tree = createTree(Tree.preOrder(integerTree).toArray(), Tree.inOrder(integerTree).toArray());
//        if (tree == null) {
//            System.out.println("--");
//        } else {
//            System.out.println(tree.getValue());
//        }
    }

    public static Tree createTree(int[] preOrderArray, int[] inOrderArray) {
        if (preOrderArray == null || inOrderArray == null) {
            return null;
        }
        return rebuildBinaryTreeCore(preOrderArray, 0, preOrderArray.length - 1, inOrderArray, 0, inOrderArray.length - 1);
    }

    public static Tree rebuildBinaryTreeCore(int preorder[], int startPreorder,
                                             int endPreorder, int inorder[], int startInorder, int endInorder) {
        if (startPreorder > endPreorder || startInorder > endInorder) { //停止递归的条件
            return null;
        }
        Tree root = new Tree(preorder[startPreorder]);
        for (int i = startInorder; i <= endInorder; i++) {
            if (preorder[startPreorder] == inorder[i]) {
                //左子树
                root.setLeft(rebuildBinaryTreeCore(preorder, startPreorder + 1,
                        startPreorder + (i - startInorder), inorder,
                        startInorder, i - 1));
                //右子树
                root.setRight(rebuildBinaryTreeCore(preorder, (i - startInorder)
                                + startPreorder + 1, endPreorder, inorder, i + 1,
                        endInorder));
            }
        }
        return root;
    }
}
