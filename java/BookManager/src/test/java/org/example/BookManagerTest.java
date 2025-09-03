package org.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class BookManagerTest {
    private BookManager bookManager;

    @BeforeEach
    void setUp(){
        bookManager = new BookManager();
    }

    // class book
    @Test
    @DisplayName("Test book initialization")
    void testBookInitialization(){
        Book book = new Book("Cara membedakan kucing dan ayam", "Rama", 2025);
        assertEquals("Cara membedakan kucing dan ayam", book.getBook_Title());
        assertEquals("Rama", book.getAuthor());
        assertEquals(2025, book.getYears());
    }

    @Test
    @DisplayName("Test book invalid initialization")
    void testBookInvalidInitialization(){
        assertThrows(IllegalArgumentException.class, () -> new Book("  ", "Rama", 2025));
        assertThrows(IllegalArgumentException.class, () -> new Book(null, "Rama", 2025));
        assertThrows(IllegalArgumentException.class, () -> new Book("Cara membedakan kucing dan ayam", "   ", 2025));
        assertThrows(IllegalArgumentException.class, () -> new Book("Cara membedakan kucing dan ayam", null, 2025));
        assertThrows(IllegalArgumentException.class, () -> new Book("Cara membedakan kucing dan ayam", "Rama", -1));
        assertThrows(IllegalArgumentException.class, () -> new Book("Cara membedakan kucing dan ayam", "Rama", 2101));
    }

    @Test
    @DisplayName("Test book string representation")
    void testBookStringRepresentation(){
        Book book = new Book("Cara membedakan kucing dan ayam", "Rama", 2025);
        assertEquals("Cara membedakan kucing dan ayam by Rama (2025)", book.toString());
    }

    @SuppressWarnings("AssertBetweenInconvertibleTypes")
    @Test
    @DisplayName("Test book equality")
    void testBookEquality(){
        Book book1 = new Book("Cara membedakan kucing dan ayam", "Rama", 2025);
        Book book2 = new Book("Cara membedakan kucing dan ayam", "Rama", 2025);
        Book book3 = new Book("Cara membedakan kaki kanan dan kaki kiri", "Rina", 2025);
        assertEquals(book1, book2);
        assertNotEquals(book1, book3);
        assertNotEquals("this is not book", book1);
        assertNotEquals(null, book1);
    }

    @Test
    @DisplayName("Test book object hash")
    void testBookHash(){
        Book book1 = new Book("Cara membedakan kucing dan ayam", "Rama", 2025);
        Book book2 = new Book("Cara membedakan kucing dan ayam", "Rama", 2025);
        Book book3 = new Book("Cara membedakan kaki kanan dan kaki kiri", "Rina", 2025);
        assertEquals(book1.hashCode(), book2.hashCode());
        assertNotEquals(book2.hashCode(), book3.hashCode());
    }
    // class book

    // addBook function
    @Test
    @DisplayName("Test add book")
    void testAddBook(){
        Book book = new Book("Cara membedakan kucing dan ayam", "Rama", 2025);
        bookManager.addBook(book);
        assertEquals(1, bookManager.getBookSize(),"After add some book, must return 1 of record data");
    }

    @Test
    @DisplayName("Test add with empty book")
    void testAddEmptyBook(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> bookManager.addBook(null));
        assertEquals("Book can't empty", exception.getMessage(), "When add a empty book should return IllegalArgumentException");
    }

    @Test
    @DisplayName("Test add duplicate book")
    void testAddDuplicateBook(){
        Book book1 = new Book("Cara membedakan kucing dan ayam", "Rama", 2025);
        Book book2 = new Book("Cara membedakan kucing dan ayam", "Rama", 2025);
        bookManager.addBook(book1);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> bookManager.addBook(book2));

        assertEquals("Title already exist", exception.getMessage(), "");
    }

    @Test
    @DisplayName("Book with null title throws exception")
    void testBookWithNullTitle() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> new Book(null, "Rama", 2025));
        assertEquals("Title required", ex.getMessage());
    }

    @Test
    @DisplayName("Book with empty author throws exception")
    void testBookWithEmptyAuthor() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> new Book("Belajar Java", "   ", 2025));
        assertEquals("Author required", ex.getMessage());
    }

    @Test
    @DisplayName("Book with invalid year throws exception")
    void testBookWithInvalidYear() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> new Book("Belajar Java", "Rama", 1999));
        assertEquals("Years must between 2000 and 2100", ex.getMessage());
    }
    // addBook function

    // clear book
    @Test
    @DisplayName("test clear all books")
    void testClearBook(){
        Book book = new Book("Cara membedakan kucing dan ayam", "Rama", 2025);
        bookManager.addBook(book);
        assertEquals(1, bookManager.getBookSize(), "After add book must return 1 record data");
        bookManager.clearAllBooks();
        assertEquals(0, bookManager.getBookSize(), "After clear book must return 0 record data");
    }
    // clear book

    // find book by year
    @Test
    @DisplayName("Test find book by year")
    void testFindBookByYear(){
        Book book = new Book("Cara membedakan kucing dan ayam", "Rama", 2025);
        bookManager.addBook(book);
        assertEquals(1, bookManager.getBookSize(), "After add book must return 1 record data");
        assertEquals(1, bookManager.findBookByYear(2025).size());
    }

    @Test
    @DisplayName("Test find book by year without range < 2000 and > 2100")
    void testFindBookByYearWithoutYear(){
        Exception ex1 = assertThrows(IllegalArgumentException.class, () -> bookManager.findBookByYear(2101));
        Exception ex2 = assertThrows(IllegalArgumentException.class, () -> bookManager.findBookByYear(2));
        assertEquals("Years must between 2000 and 2100", ex1.getMessage());
        assertEquals("Years must between 2000 and 2100", ex2.getMessage());
    }
    // find book by year

    // check contains book
    @Test
    @DisplayName("Test check contains book with empty title")
    void testCheckContainsBookWithoutTitle(){
        Exception exNull = assertThrows(IllegalArgumentException.class, () -> bookManager.containsBook(null));
        Exception exEmpty = assertThrows(IllegalArgumentException.class, () -> bookManager.containsBook("  "));
        assertEquals("Title can't empty", exNull.getMessage());
        assertEquals("Title can't empty", exEmpty.getMessage());
    }
    // check contains book

    //Lengkapi Unit Test dibawah untuk buku yang memang tidak terdapat pada list
    @Test
    @DisplayName("Test menghapus buku yang tidak ada")
    void testRemoveNonExistingBook() {
       assertFalse(bookManager.removeBook("Cara membedakan kucing dan ayam"));
    }
    // additional
    @Test
    @DisplayName("Test remove existing book")
    void testRemoveExistingBook(){
        Book book = new Book("Cara membedakan kucing dan ayam", "Rama", 2025);
        bookManager.addBook(book);
        assertEquals(1, bookManager.getBookSize(), "After add book must return 1 record data");
        bookManager.removeBook("Cara membedakan kucing dan ayam");
        assertEquals(0, bookManager.getBookSize(), "After clear book must return 0 record data");
    }

    @Test
    @DisplayName("Test remove book with no title")
    void testRemoveBookWithoutTitle(){
        Exception exNull = assertThrows(IllegalArgumentException.class, () -> bookManager.removeBook(null));

        Exception exEmpty = assertThrows(IllegalArgumentException.class, () -> bookManager.removeBook(" "));
        assertEquals("Title can't empty", exNull.getMessage());
        assertEquals("Title can't empty", exEmpty.getMessage());
    }

    //Lengkapi Unit Test dibawah untuk bmencari buku berdasarkan penulis
    @Test
    @DisplayName("Test mencari buku berdasarkan author")
    void testFindBooksByAuthor() {
        Book book1 = new Book("Cara membedakan kucing dan ayam", "Rama", 2025);
        Book book2 = new Book("Cara membedakan kaki kiri dan kanan", "Rama", 2025);
        Book book3 = new Book("Cara membuka kulkas", "Rina", 2025);

        bookManager.addBook(book1);
        bookManager.addBook(book2);
        bookManager.addBook(book3);
        assertEquals(3, bookManager.getBookSize());
        assertEquals(1,bookManager.findBookByAuthor("Rina").size());
        assertEquals(2,bookManager.findBookByAuthor("Rama").size());
    }
    // additional
    @Test
    @DisplayName("Find book with null author")
    void testFindBookWithNullAuthor(){
        Exception exNull = assertThrows(IllegalArgumentException.class, () -> bookManager.findBookByAuthor(null));

        Exception exEmpty = assertThrows(IllegalArgumentException.class, () -> bookManager.findBookByAuthor(" "));
        assertEquals("Author can't empty", exNull.getMessage());
        assertEquals("Author can't empty", exEmpty.getMessage());
    }

    //Lengkapi Unit Test dibawah untuk seluruh buku yang ada di dalam list
    @Test
    @DisplayName("Test mendapatkan semua buku")
    void testGetAllBooks() {
        Book book1 = new Book("Cara membedakan kucing dan ayam", "Rama", 2025);
        Book book2 = new Book("Cara membedakan kaki kiri dan kanan", "Rama", 2025);
        Book book3 = new Book("Cara membuka kulkas", "Rina", 2025);

        bookManager.addBook(book1);
        bookManager.addBook(book2);
        bookManager.addBook(book3);

        assertEquals(3, bookManager.getAllBooks().size());
    }
}