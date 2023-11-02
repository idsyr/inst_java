package lab_20;
import java.util.Comparator;
import java.util.TreeSet;

class Book implements Comparable{
	public static void main(String[] args) {
		Test.go();
	}
	String title;
	public Book(String t) {
		title = t;
	}
	public int compareTo(Object b) {
		Book book = (Book) b;
		return (title.compareTo(book.title));
	}
}
class BookCompare implements Comparator<Book>{
	public int compare(Book one, Book two) {
		return (one.title.compareTo(two.title));
	}
}
class Test{
	public static void go() {
		Book b1 = new Book("how cats are arranged");
		Book b2 = new Book("build your body anew");
		Book b3 = new Book("in search of Emo");
		BookCompare bCompare = new BookCompare();
		TreeSet<Book> tree1 = new TreeSet<Book>(bCompare);
		TreeSet<Book> tree2 = new TreeSet<Book>();
		tree1.add(b1); tree1.add(b2); tree1.add(b3);
		tree2.add(b1); tree2.add(b2); tree2.add(b3);
		for(Book i : tree1)
			System.out.println(i.title);
		for(Book i : tree2)
			System.out.println(i.title);
	}
}




















