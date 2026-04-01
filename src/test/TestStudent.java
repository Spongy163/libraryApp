package test;

import libraryItems.Book;
import users.Student;

public class TestStudent {
	public static void main(String[] args) {
		// ----------------------------
		// Create test objects
		// ----------------------------
		Student student1 = new Student("S001", "Brighton Drill", "Computer Science", "Sophomore");
		Student student2 = new Student("S002", "Jane Smith", "Mathematics", "Junior");

		Book book1 = new Book("B001", "Penguin", "1984", "George Orwell", "Dystopian");
		Book book2 = new Book("B002", "HarperCollins", "Brave New World", "Aldous Huxley", "Science Fiction");
		Book book3 = new Book("B003", "Scribner", "The Great Gatsby", "F. Scott Fitzgerald", "Classic");
		Book book4 = new Book("B004", "Random House", "The Hobbit", "J.R.R. Tolkien", "Fantasy");
		Book book5 = new Book("B005", "Scholastic", "The Hunger Games", "Suzanne Collins", "Adventure");
		Book book6 = new Book("B006", "Macmillan", "Of Mice and Men", "John Steinbeck", "Classic");
		Book book7 = new Book("B007", "Simon & Schuster", "Dune", "Frank Herbert", "Science Fiction");

		System.out.println("==================================================");
		System.out.println("                STUDENT TEST CASES                ");
		System.out.println("==================================================");

		// ----------------------------
		// Test 1: normal checkout
		// ----------------------------
		System.out.println("\nTest 1: Check out one available book");
		System.out.println("Expected result: true");
		System.out.println("Actual result:   " + student1.addItem(book1));
		System.out.println("Expected checked out count: 1");
		System.out.println("Actual checked out count:   " + student1.getCheckedOutCount());
		System.out.println("Expected hasItem(B001): true");
		System.out.println("Actual hasItem(B001):   " + student1.hasItem("B001"));

		// ----------------------------
		// Test 2: checkout already checked out book
		// ----------------------------
		System.out.println("\nTest 2: Check out a book that is already checked out");
		System.out.println("Expected result: false");
		System.out.println("Actual result:   " + student1.addItem(book1));
		System.out.println("Expected checked out count: 1");
		System.out.println("Actual checked out count:   " + student1.getCheckedOutCount());

		// ----------------------------
		// Test 3: normal return
		// ----------------------------
		System.out.println("\nTest 3: Return a book the student has");
		System.out.println("Expected result: true");
		System.out.println("Actual result:   " + student1.removeItem(book1));
		System.out.println("Expected checked out count: 0");
		System.out.println("Actual checked out count:   " + student1.getCheckedOutCount());
		System.out.println("Expected hasItem(B001): false");
		System.out.println("Actual hasItem(B001):   " + student1.hasItem("B001"));

		// ----------------------------
		// Test 4: return a book not checked out by this student
		// ----------------------------
		System.out.println("\nTest 4: Return a book not checked out by this student");
		System.out.println("Expected result: false");
		System.out.println("Actual result:   " + student1.removeItem(book2));

		// ----------------------------
		// Test 5: student2 checks out a book, student1 tries to return it
		// ----------------------------
		System.out.println("\nTest 5: Return a book owned by another student");
		student2.addItem(book2);
		System.out.println("Expected result: false");
		System.out.println("Actual result:   " + student1.removeItem(book2));
		System.out.println("Expected student2 hasItem(B002): true");
		System.out.println("Actual student2 hasItem(B002):   " + student2.hasItem("B002"));

		// ----------------------------
		// Test 6: max limit reached
		// ----------------------------
		System.out.println("\nTest 6: Check out books until max limit is reached");
		student1.addItem(book1);
		student1.addItem(book3);
		student1.addItem(book4);
		student1.addItem(book5);
		student1.addItem(book6);

		System.out.println("Expected checked out count: 5");
		System.out.println("Actual checked out count:   " + student1.getCheckedOutCount());

		System.out.println("Attempting one more checkout past the limit...");
		System.out.println("Expected result: false");
		System.out.println("Actual result:   " + student1.addItem(book7));
		System.out.println("Expected checked out count: 5");
		System.out.println("Actual checked out count:   " + student1.getCheckedOutCount());

		// ----------------------------
		// Test 7: return one after max reached, then checkout again
		// ----------------------------
		System.out.println("\nTest 7: Return one book, then check out another");
		System.out.println("Expected return result: true");
		System.out.println("Actual return result:   " + student1.removeItem(book6));
		System.out.println("Expected checked out count after return: 4");
		System.out.println("Actual checked out count after return:   " + student1.getCheckedOutCount());

		System.out.println("Expected checkout result: true");
		System.out.println("Actual checkout result:   " + student1.addItem(book7));
		System.out.println("Expected checked out count: 5");
		System.out.println("Actual checked out count:   " + student1.getCheckedOutCount());

		// ----------------------------
		// Test 8: return same book twice
		// ----------------------------
		System.out.println("\nTest 8: Return the same book twice");
		System.out.println("Expected first return: true");
		System.out.println("Actual first return:   " + student1.removeItem(book7));
		System.out.println("Expected second return: false");
		System.out.println("Actual second return:   " + student1.removeItem(book7));

		// ----------------------------
		// Test 9: toString check
		// ----------------------------
		System.out.println("\nTest 9: Student toString()");
		System.out.println("Expected: String containing user ID, name, major, check-out limit, loan period, and class standing");
		System.out.println("Actual:   " + student1.toString());

		// ----------------------------
		// Test 10: verify checked out book toString
		// ----------------------------
		System.out.println("\nTest 10: Book toString() after checkout");
		Book book8 = new Book("B008", "Oxford", "Hamlet", "William Shakespeare", "Tragedy");
		student1.removeItem(book1);
		student1.removeItem(book3);
		student1.removeItem(book4);
		student1.removeItem(book5);
		student1.addItem(book8);

		System.out.println("Expected: Book string should show Checked-out and a due date");
		System.out.println("Actual:   " + book8.toString());

		System.out.println("\n==================================================");
		System.out.println("                  END OF TESTS                    ");
		System.out.println("==================================================");
	}
}
