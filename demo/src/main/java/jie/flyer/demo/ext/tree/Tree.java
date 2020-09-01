package jie.flyer.demo.ext.tree;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author kain
 * @since 2020-04-17
 */
@Getter
@Setter
public class Tree {

    private Integer value;

    private Tree left;

    private Tree right;

    public Tree(Integer value, Tree left, Tree right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public Tree(int value) {
        this.value = value;
    }


    public static List<Integer> preOrder(Tree tree) {
        if (tree == null) {
            return Collections.emptyList();
        }
        ArrayList<Integer> list = new ArrayList<>();
        list.add(tree.getValue());
        list.addAll(preOrder(tree.getLeft()));
        list.addAll(preOrder(tree.getRight()));
        return list;
    }

    public static List<Integer> inOrder(Tree tree) {
        if (tree == null) {
            return Collections.emptyList();
        }
        ArrayList<Integer> list = new ArrayList<>(inOrder(tree.getLeft()));
        list.add(tree.value);
        list.addAll(inOrder(tree.getRight()));
        return list;
    }
}
