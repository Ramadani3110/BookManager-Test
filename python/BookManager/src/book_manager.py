from book import Book
from typing import List

class BookManager:
    def __init__(self):
        self.books = []

    def toLower(self, s: str) -> str:
        if isinstance(s, str):
            return s.strip().lower()
        return s

    def add_book(self, book: Book):
        if book is None:
            raise ValueError("Book cannot be None")
        if book in self.books:
            raise ValueError("This book is already in the collection")
        self.books.append(book)

    def remove_book(self, title: str) -> bool:
        if title is None or not title.strip():
            raise ValueError("Title cannot be empty or None")
        initial_count = len(self.books)
        self.books = [book for book in self.books if book.title.lower() != self.toLower(title)]
        return len(self.books) < initial_count

    def find_books_by_author(self, author: str) -> List[Book]:
        if author is None or not author.strip():
            raise ValueError("Author cannot be empty or None")
        return [book for book in self.books if book.author.lower() == self.toLower(author)]

    def find_books_by_year(self, year: int) -> List[Book]:
        if year < 0 or year > 2100:
            raise ValueError("Year must be between 0 and 2100")
        return [book for book in self.books if book.year == year]

    def contains_book(self, title: str) -> bool:
        if title is None or not title.strip():
            raise ValueError("Title cannot be empty or None")
        return any(book.title.lower() == self.toLower(title) for book in self.books)

    def get_all_books(self) -> List[Book]:
        return self.books
    
    def get_book_count(self) -> int:
        return len(self.books)