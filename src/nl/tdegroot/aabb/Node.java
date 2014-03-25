package nl.tdegroot.aabb;

public class Node<E extends Comparable<? super E>> {

	public Node left, right;
	public final E value;

	public Node(E value) {
		this.value = value;
	}

	public Node(Node<E> node) {
		left = node.left;
		right = node.right;
		value = node.value;
	}

}
