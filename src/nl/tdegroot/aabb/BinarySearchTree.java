package nl.tdegroot.aabb;

public class BinarySearchTree<E extends Comparable<? super E>> {

	private Node<E> root;
	int size = 0;
	int i;

	public void add(E element) {
		if (root == null && element != null) {
			root = new Node<E>(element);
			size++;
		} else if (element != null) {
//			root
		}
	}

}
