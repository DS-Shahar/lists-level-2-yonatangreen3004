import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		int[] a = {1, 2, 5, 7, 9};
		int[] a1 = {1, 2, 4, 6, 8};
        int[] a2 = {0, 5, 2, 6, 3};
		Node<Integer> L1 = buildList(a);
		Node<Integer> L2 = buildList(a1);
        Node<Integer> list = buildList(a2);
		System.out.println(ex1(L1,L2));
		System.out.println(ex2(list));
		System.out.println(ex3(L1, 7));
		System.out.println(ex4(L2));
	}

	public static Node<Integer> buildList(int[] a) {
		Node<Integer> h = new Node<Integer>(-1);
		Node<Integer> p = h;
		Node<Integer> x;
		for (int i = 0; i < a.length; i++) {
			x = new Node<Integer>(a[i]);
			p.setNext(x);
			p = p.getNext();
		}
		return h.getNext();
	}

	public static Node<Integer> ex1(Node<Integer> L1, Node<Integer> L2) {

		Node<Integer> p1 = L1;
		Node<Integer> p2 = L2;

		Node<Integer> L3 = new Node<Integer>(0);
		Node<Integer> p3 = L3;

		while (p1 != null && p2 != null) {

			if (p1.getValue() < p2.getValue()) {
				Node<Integer> p4 = new Node<Integer>(p1.getValue());
				p3.setNext(p4);
				p3 = p3.getNext();
				p1 = p1.getNext();
			} else {
				Node<Integer> p4 = new Node<Integer>(p2.getValue());
				p3.setNext(p4);
				p3 = p3.getNext();
				p2 = p2.getNext();
			}
		}

		while (p1 != null) {
			Node<Integer> p4 = new Node<Integer>(p1.getValue());
			p3.setNext(p4);
			p3 = p3.getNext();
			p1 = p1.getNext();
		}

		while (p2 != null) {
			Node<Integer> p4 = new Node<Integer>(p2.getValue());
			p3.setNext(p4);
			p3 = p3.getNext();
			p2 = p2.getNext();
		}

		return L3.getNext();
	}

	public static Node<Integer> min(Node<Integer> L) {

		Node<Integer> pMin = L;
		Node<Integer> p = L;

		while (p != null) {

			if (pMin.getValue() > p.getValue()) {
				pMin = p;
			}

			p = p.getNext();
		}
		return pMin;
	}

	public static Node<Integer> ex2(Node<Integer> L) {

		Node<Integer> L2 = new Node<Integer>(0);
		Node<Integer> p2 = L2;

		while (L != null) {
			Node<Integer> p1 = min(L);
			Node<Integer> p3 = new Node<Integer>(p1.getValue());
			p2.setNext(p3);
			p2 = p2.getNext();

			if (p1 == L) {
				L = L.getNext();
			}

			else {
				Node<Integer> p4 = L;
				while (p4.getNext() != p1) {
					p4 = p4.getNext();
				}
				p4.setNext(p1.getNext());
			}
		}

		return L2.getNext();
	}

	public static int ex3(Node<Integer> L, int x) {
		Node<Integer> p1 = IsExist(L, x);
		if (p1 == null)
			return -1;

		else {
			Node<Integer> p = L;
			int sum = 0;
			int count = 1;

			while (p != p1) {
				sum = sum + count;
				count++;
				p = p.getNext();
			}
			return sum;
		}
	}

	public static Node<Integer> IsExist(Node<Integer> L, int x) {
		Node<Integer> p = L;
		while (p != null) {
			if (p.getValue() == x)
				return p;

			p = p.getNext();
		}

		return null;
	}

	public static int CountNum(Node<Integer> L, int x) {
		int count = 0;
		Node<Integer> p = L;
		while (p != null) {
			if (p.getValue() == x) {
				count++;
			}
			p = p.getNext();
		}
		return count;
	}

	public static boolean ex4(Node<Integer> L) {
		Node<Integer> p = L;
		while (p != null) {
			int x = CountNum(L, p.getValue());
			if (x != 1) {
				return false;
			}
			p = p.getNext();
		}
		return true;
	}

}
