package funtion.one.entity;

import java.util.Stack;

public class BinaryTree {

	static class Node {
		int value;
		Node leftChild;
		Node rightChild;

		Node(int value) {
			this.value = value;
		}
	}

	public void insert(int value, Node node) {
		if (value < node.value) {
			if (node.leftChild != null) {
				insert(value, node.leftChild);
			} else {
				node.leftChild = new Node(value);
			}
		} else if (value > node.value) {
			if (node.rightChild != null) {
				insert(value, node.rightChild);
			} else {
				node.rightChild = new Node(value);
			}
		}
	}

	public void preTraveerse(Node node) {
		System.out.println(node.value + "");
		if (node.leftChild != null) {
			preTraveerse(node.leftChild);
			System.out.println("*******");
		}
		if (node.rightChild != null) {
			preTraveerse(node.rightChild);
			System.out.println("============");
		}
	}

	public void pretraverseNoRecursion(Node node) {
		Stack<Node> stack = new Stack<Node>();

	}

	public static void main(String[] args) {
		Node root = new Node(5);
		BinaryTree btree = new BinaryTree();
		btree.insert(4, root);
		btree.insert(7, root);
		btree.insert(1, root);
		btree.insert(3, root);
		btree.insert(8, root);
		btree.preTraveerse(root);
	}

}
