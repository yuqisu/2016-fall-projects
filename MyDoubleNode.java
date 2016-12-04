public class MyDoubleNode<AnyType> {
	public AnyType data;
	public MyDoubleNode<AnyType> next;
	public MyDoubleNode<AnyType> prev;

	public MyDoubleNode(AnyType data) {
		this.data = data;

	}

	public String toString() {
		return data.toString();
	}

	public void printList() {
		System.out.print(data+ "  ");
	}

	public static void main(String[] args) {
		DoubleLinked<String> names = new DoubleLinked();
		names.insert("sakurai");
		names.insert("zifan");
		names.insert("amy");
		names.insert("lalala");
		names.delete("lalala");
		System.out.print("the sequence of this list is:   ");names.printList();
		System.out.println();
		System.out.print("the reverse of this list is:   ");names.printListRev();
		System.out.println();
		System.out.println("whether the list contains the element: "+names.contains("yuqi"));
		System.out.println("the list contains the element(return null if not): "+names.lookup("zifan"));
		System.out.println("the list is empty: "+names.isEmpty());
	}
}

class DoubleLinked<AnyType> implements DoublyLinkedList<AnyType> {
	public MyDoubleNode<AnyType> head;
	public MyDoubleNode<AnyType> tail;

	DoubleLinked() {
		MyDoubleNode<AnyType> head = new MyDoubleNode<AnyType>(null);
		MyDoubleNode<AnyType> tail = new MyDoubleNode<AnyType>(null);
		head.prev = null;
		head.next = tail;
		tail.prev = head;
		tail.next = null;
	}

	@Override
	public void insert(AnyType x) {
		MyDoubleNode<AnyType> newNode = new MyDoubleNode<AnyType>(x);
		MyDoubleNode<AnyType> pre = null;
		MyDoubleNode<AnyType> curr = head;

			while (curr != null) {
				pre = curr;
				curr = curr.next;
			}
		if (pre == null) {
			head = newNode;
		} else {
			pre.next = newNode;
		}
		newNode.next = curr;
		newNode.prev = pre;

	}

	@Override
	public void delete(AnyType x) {
		MyDoubleNode<AnyType> curr = head;
		while (curr != null && contains(x)) {
			if (x.equals(head.data)) {
				head.next.prev = null;
				head = head.next;
			}
			if (x.equals(curr.data)) {
				if (curr.prev != null)
					(curr.prev).next = curr.next;
				if (curr.next != null)
					(curr.next).prev = curr.prev;
				return;
			}
			curr = curr.next;
		}
	}

	@Override
	public boolean contains(AnyType x) {
		MyDoubleNode<AnyType> theNode = head;
		boolean found = false;

		while (theNode != null && !found) {
			if (x.equals(theNode.data)) {
				found = true;
			} else
				theNode = theNode.next;
		}
		return found;
	}

	@Override
	public AnyType lookup(AnyType x) {
		MyDoubleNode<AnyType> current = head;
		while (current != tail) {
			if (x.equals(current.data))
				return current.data;
			current = current.next;
		}
		return null;

	}

	@Override
	public boolean isEmpty() {
		return (head == null);
	}

	@Override
	/*
	 * (non-Javadoc)
	 * @see DoublyLinkedList#printList()
	 * estimate runtime O(n)
	 */
	public void printList() {
		MyDoubleNode<AnyType> curr = head;
		while (curr != tail) {
			curr.printList();
			curr = curr.next;

		}
	}


	@Override
	public void printListRev() {
		tail = head;
		while (tail.next != null)
			tail = tail.next;
		MyDoubleNode<AnyType> curr = tail;
		while (curr != null) {
			curr.printList();
			curr = curr.prev;
		}

	}

}