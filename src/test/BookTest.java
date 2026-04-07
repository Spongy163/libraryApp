/* Brighton Drill
 * Title: Book Test
 * Date: 4/1/2026
 * Description: AI generated testing code
 */
package test;

import libraryItems.Book;

public class BookTest {
	public static void main(String[] args) {

        // Create a Book object
        Book book = new Book("B001", "Penguin", "1984", "George Orwell", "Dystopian");

        System.out.println("===== BOOK TEST START =====\n");

        // -----------------------------
        // Test getItemType()
        // -----------------------------
        System.out.println("Test getItemType()");
        System.out.println("Expected: (Book)");
        System.out.println("Actual:   " + book.getItemType());
        System.out.println();

        // -----------------------------
        // Test checkOut()
        // -----------------------------
        System.out.println("Test checkOut(7)");
        boolean checkoutResult = book.checkOut(7);
        System.out.println("Expected: true");
        System.out.println("Actual:   " + checkoutResult);
        System.out.println();

        // Try checking out again (should fail)
        System.out.println("Test checkOut again (already checked out)");
        boolean checkoutAgain = book.checkOut(7);
        System.out.println("Expected: false");
        System.out.println("Actual:   " + checkoutAgain);
        System.out.println();

        // -----------------------------
        // Test toString()
        // -----------------------------
        System.out.println("Test toString() after checkout");
        System.out.println("Expected: Should include item info, 'Checked-out', and a valid due date");
        System.out.println("Actual:   " + book.toString());
        System.out.println();

        // -----------------------------
        // Test isOverdue()
        // -----------------------------
        System.out.println("Test isOverdue() immediately after checkout");
        System.out.println("Expected: false");
        System.out.println("Actual:   " + book.isOverdue());
        System.out.println();

        // -----------------------------
        // Test returnItem()
        // -----------------------------
        System.out.println("Test returnItem()");
        boolean returnResult = book.returnItem();
        System.out.println("Expected: true");
        System.out.println("Actual:   " + returnResult);
        System.out.println();

        // Try returning again (should fail)
        System.out.println("Test returnItem() again (already returned)");
        boolean returnAgain = book.returnItem();
        System.out.println("Expected: false");
        System.out.println("Actual:   " + returnAgain);
        System.out.println();

        // -----------------------------
        // Test isOverdue() after return
        // -----------------------------
        System.out.println("Test isOverdue() after return");
        System.out.println("Expected: false");
        System.out.println("Actual:   " + book.isOverdue());
        System.out.println();
        
        //null dueDate crash test
        System.out.println(book);
        
        


        System.out.println("===== BOOK TEST END =====");
    }
}
