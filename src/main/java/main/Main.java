package main;

import tree.Tree;

import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        TreeMap<Integer, Integer> tree = new TreeMap<>();
        new Main().run();
    }

    private void run() {
        Tree<Integer> tree = new Tree<>();
//        tree.add(7);
//        tree.add(12);
//        tree.add(10);
//        tree.add(1);
//        tree.add(5);
//        tree.add(14);
//        tree.add(13);
        tree.add(5);
        tree.add(8);
        tree.add(2);
        tree.add(3);
        tree.add(9);
        tree.add(6);
        tree.add(11);
        tree.add(1);
        tree.traverse();

        System.out.println(tree.contains(17));
        System.out.println(tree.contains(10));
        tree.remove(8);
        tree.traverse();
        tree.remove(9);
        tree.traverse();
    }
}
