package nl.tdegroot.aabb;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree<E extends Comparable<? super E>> {

	private Node<E> root;
	int size = 0;
	int i;

	public static void testTree() {
		BinarySearchTree<String> tree = new BinarySearchTree<String>();
		tree.add("Hanno");
		tree.add("Zacharias");
		tree.add("Berhard");
		List<String> result = tree.toList();
		for (String s : result) {
			System.out.println(s);
		}
	}

	public void add(E element) {
		if (root == null && element != null) {
			root = new Node<E>(element);
			size++;
		} else if (element != null) {
			root = insert(root, element);
		}
	}

	private Node<E> insert(Node<E> node, E value) {
		Node<E> result = new Node<E>(node);
		int compare = result.value.compareTo(value);
		if (compare == 0) return result;
		if (compare > 0) {
			if (result.left != null) {
				result.left = insert(result.left, value);
			} else {
				result.left = new Node<E>(value);
				size++;
			}
		} else if (compare < 0) {
			if (result.right != null) {
				result.right = insert(result.right, value);
			} else {
				result.right = new Node<E>(value);
				size++;
			}
		}
		return result;
	}

	public E get(E key) {
		if (root == null) return null;
		Node<E> node = root;
		int compareResult;
		while ((compareResult = node.value.compareTo(key)) != 0) {
			if (compareResult > 0) {
				if (node.left != null) {
					node = node.left;
				} else {
					return null;
				}
			} else {
				if (node.right != null) {
					node = node.right;
				} else {
					return null;
				}
			}
		}
		return node.value;
	}

	public List<E> toList() {
		List<E> result = new ArrayList<E>();
		treeToList(root, result);
		return result;
	}

	public void treeToList(Node<E> node, List<E> goal) {
		if (node != null) {
			treeToList(node.left, goal);
			goal.add(node.value);
			treeToList(node.right, goal);
		}
	}

}
