

public class LinkedQueue<AnyType> extends DoubleLinked<AnyType> implements Queue<AnyType> {

	LinkedQueue(){

	}
	public static void main(String[]args){
		LinkedQueue<String> numbers = new LinkedQueue<String>();
		numbers.enqueue("X");
		numbers.enqueue("Y");
		numbers.enqueue("Z");
		numbers.printQueue();
		System.out.println();
		System.out.println(numbers.peek());
		numbers.dequeue();
		System.out.println(numbers.peek());
		numbers.printQueue();
	}
	@Override
	public boolean isEmpty() {
		return(head==null);
	}

	@Override
	public void enqueue(AnyType x) {
		MyDoubleNode<AnyType> newNode = new MyDoubleNode(x);
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
	public AnyType dequeue() {
		MyDoubleNode<AnyType> curr = head;
//				head.next.prev = null;
				head = head.next;
			return curr.data;
	}

	@Override
	public AnyType peek() {
		if (head==null){
			return null;
		}
		return head.data;
	}

	public void printQueue(){
		printList();
	}

}
