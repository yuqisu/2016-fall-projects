public class MyNode<AnyType>{
	AnyType data;
	MyNode<AnyType> next;

	public String toString(){
		return this.data.toString();
	}

	public static void main(String[]args){
		MyLinkedList<String> names= new MyLinkedList<>();
		names.insert("sho");
		names.insert("gakki");
		names.insert("nino");
		names.printList();
		names.delete("gakki");
		names.printList();

		System.out.println("whether the list contains this element : "+names.contains("sho"));
		System.out.println("the list has: "+names.lookup("sho"));
		

		System.out.println("whether the list is empty: "+names.isEmpty());
	}

}
class MyLinkedList<AnyType> implements SimpleLinkedList<AnyType> {

	MyNode<AnyType> head;

	@Override
	//Prepend an element into the linked list
	public void insert(AnyType x) {
		// TODO Auto-generated method stub
		MyNode newNode = new MyNode();
		newNode.data = x;
		newNode.next = head;
		head = newNode;
	}

	@Override
	//delete a certain element
	public void delete(AnyType x) {
		if (!contains(x)) {//In case of no such element, just do nothing.
			System.out.println("No such entry");
		} else if (head.data.equals(x)) {
			head = head.next;
		} else {
			MyNode temp = head;
			MyNode prev = null;
			while (temp != null && !temp.data.equals(x)) {
				prev = temp;
				temp = temp.next;
			}
			prev.next = temp.next;

		}
	}


	@Override
	public boolean contains(AnyType x) {
		// TODO Auto-generated method stub
		//A for loop in order to go through from head to the end
		for (MyNode temp = head; temp != null; temp = temp.next) {
			if (temp.data.equals(x)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public AnyType lookup(AnyType x) {
		return null;
	}


	public AnyType lookUp(AnyType x) {
		for (MyNode temp = head; temp != null; temp = temp.next) {
			if (temp.data.equals(x)) {
				return x;
			}
		}
		//if no such an element, just return null
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if (head == null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void printList() {
		// TODO Auto-generated method stub
		for (MyNode temp = head; temp != null; temp = temp.next) {
			System.out.print(temp.data.toString() + " ");
		}
		System.out.println();
	}

	@Override
	public AnyType delete() {
		return null;
	}

}

