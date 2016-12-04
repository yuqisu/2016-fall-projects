public class LinkedStacks<AnyType> extends MyLinkedList<AnyType> implements Stack<AnyType> {


	LinkedStacks() {

	}

	public static void main(String[] args) {
		LinkedStacks<String> numbers = new LinkedStacks();
		numbers.push("Xabbb");
		numbers.push("Y");
		numbers.push("Z");
		numbers.printList();
		System.out.println(numbers.peek());
		numbers.pop();
		numbers.printList();
		System.out.println(numbers.peek());
	}

	@Override
	public boolean isEmpty() {
		if (head == null) {
			return true;
		}
		return false;
	}

	@Override
	public void push(AnyType x) {

		// TODO Auto-generated method stub
		MyNode newNode = new MyNode();
		newNode.data = x;
		newNode.next = head;
		head = newNode;

	}

	@Override
	public AnyType pop() {
		if (isEmpty()) {
			return null;
		} else {
			MyNode<AnyType> result = head;
			head = head.next;
			return result.data;
		}
	}

	@Override
	public AnyType peek() {
		if (head==null){
			return null;
		}
		return head.data;
	}
}
