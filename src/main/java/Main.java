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
		Queue<Integer> q2 = new Queue<>();
		q2.insert(2);
		q2.insert(5);
		q2.insert(4);
		q2.insert(0);
		q2.insert(4);
		q2.insert(1);
		q2.insert(0);
		q2.insert(3);
		q2.insert(6);
		q2.insert(0);

		System.out.println(listMax(q2));
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
// יעילות: O(n+m) כאשר n = אורך L1 ו-m = אורך L2
// (כל איבר משתי הרשימות עובר לכל היותר פעם אחת)

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
// יעילות: O(n) כאשר n = אורך הרשימה L


public static Node<Integer> ex2(Node<Integer> L) {

    Node<Integer> L2 = new Node<Integer>(0);
    Node<Integer> p2 = L2;

    while (L != null) {
        Node<Integer> p1 = min(L);                // O(k) בכל איטרציה (k משתנה)
        Node<Integer> p3 = new Node<Integer>(p1.getValue());
        p2.setNext(p3);
        p2 = p2.getNext();

        if (p1 == L) {
            L = L.getNext();
        }

        else {
            Node<Integer> p4 = L;
            while (p4.getNext() != p1) {          // חיפוש קודם למינימום: O(k) בכל איטרציה
                p4 = p4.getNext();
            }
            p4.setNext(p1.getNext());
        }
    }

    return L2.getNext();
}
// יעילות: O(n^2) כאשר n = אורך הרשימה המקורית
// הסבר: בכל סיבוב עושים min (O(k)) וגם לפעמים עוד חיפוש כדי למחוק (O(k)),
// וסוכמים על פני k=n,n-1,...,1 => O(n^2)

public static int ex3(Node<Integer> L, int x) {
    Node<Integer> p1 = IsExist(L, x); // O(n)
    if (p1 == null)
        return -1;

    else {
        Node<Integer> p = L;
        int sum = 0;
        int count = 1;

        while (p != p1) { // במקרה הגרוע עוברים עד שמגיעים ל-x: O(n)
            sum = sum + count;
            count++;
            p = p.getNext();
        }
        return sum;
    }
}
// יעילות: O(n)
// (IsExist הוא O(n) + הלולאה עד p1 היא O(n) => עדיין O(n))

public static Node<Integer> IsExist(Node<Integer> L, int x) {
    Node<Integer> p = L;
    while (p != null) {
        if (p.getValue() == x)
            return p;

        p = p.getNext();
    }

    return null;
}
// יעילות: O(n) כאשר n = אורך הרשימה
// (סריקה לינארית עד שמוצאים או עד הסוף)

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
// יעילות: O(n) כאשר n = אורך הרשימה
// (סופרים מופעים ע"י סריקה מלאה)

public static boolean ex4(Node<Integer> L) {
    Node<Integer> p = L;
    while (p != null) {
        int x = CountNum(L, p.getValue()); // O(n) בכל איטרציה
        if (x != 1) {
            return false;
        }
        p = p.getNext();
    }
    return true;
}


  public static int max0(Queue<Integer> q){
        if(q.isEmpty())
            return 0;
        int x = q.head();
        while (q.head()!=0){
            if(q.head()>x){
                x=q.head();
            }
            q.remove();
        }
         q.remove();
        return x;
    }

    
    public static Node<Integer> listMax(Queue<Integer> q){
        Queue<Integer> qCopy = copyQueue(q);
        Node<Integer> p = new Node<Integer>(-1);
        Node<Integer> h = p;
        
        while (!qCopy.isEmpty()){
           
            int x=max0(qCopy);
            Node<Integer> maxNum = new Node<Integer>(x);
            p.setNext(maxNum);
            p=p.getNext();
        
        }
        return h.getNext();
    }

}

