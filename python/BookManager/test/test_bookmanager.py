import unittest
import sys
import os
sys.path.append(os.path.abspath(os.path.join(os.path.dirname(__file__), '../src')))
from book_manager import BookManager
from book import Book

class TestBookManager(unittest.TestCase):
    
    def setUp(self):
        self.book_manager = BookManager()
        self.book1 = Book("Cara membedakan kucing dan ayam", "Rama", 2025)
        self.book2 = Book("Cara membedakan kaki kanan dan kiri", "Rina", 2025)
        self.book3 = Book("Cara membedakan baju dan celana", "Rama", 2024)

    # class book
    def test_book_initialization(self):
        """Test the initialization of a Book object"""
        self.assertEqual(self.book1.title, "Cara membedakan kucing dan ayam")
        self.assertEqual(self.book1.author, "Rama")
        self.assertEqual(self.book1.year, 2025)
        
    def test_book_invalid_initialization(self):
        """Test invalid initialization of a Book object"""
        with self.assertRaises(ValueError):
            Book(None, "Author", 2023)
        with self.assertRaises(ValueError):
            Book("   ", "Author", 2023)
        with self.assertRaises(ValueError):
            Book("Title", None, 2023)
        with self.assertRaises(ValueError):
            Book("Title", "   ", 2023)
        with self.assertRaises(ValueError):
            Book("Title", "Author", -1)
        with self.assertRaises(ValueError):
            Book("Title", "Author", 2101)
    
    def test_book_str_representation(self):
        """Test the string representation of a Book object"""
        self.assertEqual(str(self.book1), "Cara membedakan kucing dan ayam by Rama (2025)")
        
    def test_book_equality(self):
        """Test the equality of two Book objects"""
        book_a = Book("Title", "Author", 2023)
        book_b = Book("Title", "Author", 2023)
        book_c = Book("Different Title", "Author", 2023)
        
        self.assertEqual(book_a, book_b)
        self.assertNotEqual(book_a, book_c)
        self.assertNotEqual(book_a, "Not a Book")
        
    def test_hash(self):
        """Test the hash of a Book object"""
        book_a = Book("Title", "Author", 2023)
        book_b = Book("Title", "Author", 2023)
        book_c = Book("Different Title", "Author", 2023)
        self.assertEqual(hash(book_a), hash(book_b))
        self.assertNotEqual(hash(book_a), hash(book_c))
    # class book
    
    # toLower function
    def test_toLower(self):
        """Test the toLower function"""
        self.assertEqual(self.book_manager.toLower("  Hello World  "), "hello world")
        self.assertEqual(self.book_manager.toLower("TEST"), "test")
        self.assertEqual(self.book_manager.toLower("   MiXeD CaSe   "), "mixed case")
        self.assertEqual(self.book_manager.toLower("   "), "")
        self.assertIsNone(self.book_manager.toLower(None))
        self.assertEqual(self.book_manager.toLower(123), 123)
        self.assertEqual(self.book_manager.toLower(["List", "Of", "Strings"]), ["List", "Of", "Strings"])
    # toLower function

    # add book function        
    def test_add_book(self):
        """Test adding books increases the count correctly"""
        self.book_manager.add_book(self.book1)
        self.assertEqual(self.book_manager.get_book_count(), 1)
        self.book_manager.add_book(self.book2)
        self.assertEqual(self.book_manager.get_book_count(), 2)
        
    def test_add_duplicate_book(self):
        """Test adding a duplicate book raises ValueError"""
        self.book_manager.add_book(self.book1)
        with self.assertRaises(ValueError):
            self.book_manager.add_book(self.book1)
    
    def test_add_none_book(self):
        """Test adding None as a book raises ValueError"""
        with self.assertRaises(ValueError):
            self.book_manager.add_book(None)
        
    # add book function
    
    # remove book function
    def test_remove_existing_book(self):
        """Test removing an existing book decreases the count correctly"""
        self.book_manager.add_book(self.book1)
        self.book_manager.add_book(self.book2)

        removed = self.book_manager.remove_book("Cara membedakan kucing dan ayam")

        self.assertTrue(removed)
        self.assertEqual(self.book_manager.get_book_count(), 1)
                
    #Lengkapi Unit Test dibawah untuk buku yang memang tidak terdapat pada list
    def test_remove_non_existing_book(self):
        """Test menghapus buku yang tidak ada"""
        self.book_manager.add_book(self.book1)
        removed = self.book_manager.remove_book("Cara membedakan kaki kanan dan kiri")
        self.assertFalse(removed)

    def test_remove_book_invalid_title(self):
        """Test removing a book with None or empty title raises ValueError"""
        with self.assertRaises(ValueError):
            self.book_manager.remove_book(None)
        with self.assertRaises(ValueError):
            self.book_manager.remove_book("   ")
    # remove book function

    # find by author function
    #Lengkapi Unit Test dibawah untuk mencari buku berdasarkan penulis
    def test_find_books_by_author(self):
        """Test mencari buku berdasarkan author"""
        self.book_manager.add_book(self.book1)
        self.book_manager.add_book(self.book2)
        self.book_manager.add_book(self.book3)

        books_by_rama = self.book_manager.find_books_by_author("Rama")

        self.assertEqual(len(books_by_rama), 2)
        self.assertIn(self.book1, books_by_rama)
        self.assertIn(self.book3, books_by_rama)

    def test_find_books_invalid_author(self):
        """Test searching books with None or empty author raises ValueError"""
        with self.assertRaises(ValueError):
            self.book_manager.find_books_by_author(None)
        with self.assertRaises(ValueError):
            self.book_manager.find_books_by_author("   ")
     # find by author function
     
     # find by year function
    def test_find_books_by_year(self):
        """Test searching books by year"""
        self.book_manager.add_book(self.book1)
        self.book_manager.add_book(self.book2)
        self.book_manager.add_book(self.book3)

        books_2025 = self.book_manager.find_books_by_year(2025)

        self.assertEqual(len(books_2025), 2)
        self.assertIn(self.book1, books_2025)
        self.assertIn(self.book2, books_2025)
        
    def test_find_books_invalid_year(self):
        """Test searching books with invalid year raises ValueError"""
        with self.assertRaises(ValueError):
            self.book_manager.find_books_by_year(-1)
        with self.assertRaises(ValueError):
            self.book_manager.find_books_by_year(2101)
     # find by year function

    #Lengkapi Unit Test dibawah untuk seluruh buku yang ada di dalam list
    def test_get_all_books(self):
        """Test mendapatkan semua buku"""
        self.book_manager.add_book(self.book1)
        self.book_manager.add_book(self.book2)
        self.book_manager.add_book(self.book3)

        all_books = self.book_manager.get_all_books()

        self.assertEqual(self.book_manager.get_book_count(), 3)
        self.assertIn(self.book1, all_books)
        self.assertIn(self.book2, all_books)
        self.assertIn(self.book3, all_books)
        
    # contains book function
    def test_contains_book(self):
        """Test contains book function"""
        self.book_manager.add_book(self.book1)
        self.book_manager.add_book(self.book2)

        self.assertTrue(self.book_manager.contains_book("Cara membedakan kucing dan ayam"))
        self.assertFalse(self.book_manager.contains_book("Cara membedakan baju dan celana"))
        
    def test_contains_book_invalid_title(self):
        """Test contains book with None or empty title raises ValueError"""
        with self.assertRaises(ValueError):
            self.book_manager.contains_book(None)
        with self.assertRaises(ValueError):
            self.book_manager.contains_book("   ")
    # contains book function

if __name__ == '__main__': # pragma: no cover
    unittest.main() # pragma: no cover