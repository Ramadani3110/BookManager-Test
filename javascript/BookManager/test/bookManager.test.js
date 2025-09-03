const BookManager = require('../src/bookManager')
const Book = require('../src/book')

describe('BookManager', () => {
    let bookManager

    beforeEach(() => {
        bookManager = new BookManager()
    })

    afterEach(() => {
        bookManager = null
    })

    // book class
    test('Book constructor should create book instance with valid parameters', () => {
        const book = new Book("Cara membedakan kucing dan ayam", "Rama", 2025)
        expect(book.title).toBe("Cara membedakan kucing dan ayam")
        expect(book.author).toBe("Rama")
        expect(book.year).toBe(2025)
    })

    test('Book constructor should throw error when parameters is not valid', () => {
        expect(() => new Book(null, "Rama", 2025)).toThrow("Title cannot be null")
        expect(() => new Book("  ", "Rama", 2025)).toThrow("Title cannot be null")
        expect(() => new Book("Cara membedakan kucing dan ayam", null, 2025)).toThrow("Author cannot be null")
        expect(() => new Book("Cara membedakan kucing dan ayam", "  ", 2025)).toThrow("Author cannot be null")
        expect(() => new Book("Cara membedakan kucing dan ayam", "Rama", -1)).toThrow("Year must between 0 and 2100")
        expect(() => new Book("Cara membedakan kucing dan ayam", "Rama", 2101)).toThrow("Year must between 0 and 2100")
    })

    test('Book equals should return true for same book details', () => {
        const book1 = new Book("Cara membedakan kucing dan ayam", "Rama", 2025)
        const book2 = new Book("Cara membedakan kucing dan ayam", "Rama", 2025)
        expect(book1.equals(book2)).toBe(true)
    })

    test('Book equals should return false for different book details', () => {
        const book1 = new Book("Cara membedakan kucing dan ayam", "Rama", 2025)
        const book2 = new Book("Cara membedakan kaki kanan dan kiri", "Rina", 2025)
        expect(book1.equals(book2)).toBe(false)
    })

    test('Book equals should return false when comparing with non-book object', () => {
        const book = new Book("Cara membedakan kucing dan ayam", "Rama", 2025)
        expect(book.equals(null)).toBe(false)
        expect(book.equals({})).toBe(false)
    })

    test('Book toString should return formatted string', () => {
        const book = new Book("Cara membedakan kucing dan ayam", "Rama", 2025)
        expect(book.toString()).toBe("Cara membedakan kucing dan ayam by Rama (2025)")
    })
    // book class

    // toLower function
    test('toLower should convert string to lowercase', () => {
        expect(bookManager.toLower("RAMA")).toBe("rama");
        expect(bookManager.toLower("TeSt")).toBe("test");
        expect(bookManager.toLower("")).toBe("");
    });

    test('toLower should return param as is if not a string', () => {
        expect(bookManager.toLower(123)).toBe(123);
        expect(bookManager.toLower(null)).toBe(null);
        expect(bookManager.toLower(undefined)).toBe(undefined);
    });
    // toLower function

    // addbook function
    test('addBook should add book to the collection', () => {
        const book = new Book("Cara membedakan kucing dan ayam", "Rama", 2025)
        bookManager.addBook(book)
        expect(bookManager.getBookCount()).toBe(1)
    })

    test('addBook should throw error if book is null', () => {
        expect(() => bookManager.addBook(null)).toThrow("Book cannot empty")
    })
    // addbook function

    // removeBook function
    test('removeBook should remove book by title', () => {
        const book1 = new Book("Cara membedakan kucing dan ayam", "Rama", 2025)
        const book2 = new Book("Cara membedakan kaki kanan dan kiri", "Rina", 2025)
        bookManager.addBook(book1)
        bookManager.addBook(book2)
        const removed = bookManager.removeBook("Cara membedakan kucing dan ayam")
        expect(removed).toBe(true)
        expect(bookManager.getBookCount()).toBe(1)
        expect(bookManager.getAllBooks()).toEqual([book2])
    })

    // lengkapi test unit untuk removeBook jika buku tidak ditemukan
    test('removeBook should return false if book title not found', () => {
        const book = new Book("Cara membedakan kucing dan ayam", "Rama", 2025)
        bookManager.addBook(book)
        const removed = bookManager.removeBook("Non-existing title")
        expect(removed).toBe(false)
        expect(bookManager.getBookCount()).toBe(1)
    })

    test('removeBook should throw error if title is null or empty', () => {
        expect(() => bookManager.removeBook(null)).toThrow("Title cannot be null")
    })
    // removeBook function

    // findBookByAuthor function
    // lengkapi test unit untuk mencari buku berdasarkan author
    test('findBookByAuthor should return books by the specified author', () => {
        const book1 = new Book("Cara membedakan kucing dan ayam", "Rama", 2025)
        const book2 = new Book("Cara membedakan kaki kanan dan kiri", "Rina", 2025)
        const book3 = new Book("Cara merawat kucing", "Rama", 2024)
        bookManager.addBook(book1)
        bookManager.addBook(book2)
        bookManager.addBook(book3)
        const results = bookManager.findBooksByAuthor("Rama")
        expect(results.length).toBe(2)
        expect(results).toEqual([book1, book3])
    })

    test('findBookByAuthor should throw error if author is null or empty', () => {
        expect(() => bookManager.findBooksByAuthor(null)).toThrow("Author cannot be null")
    })
    // findBookByAuthor function

    // getAllBooks function
    // lengkapi test unit untuk seluruh buku yang ada di list
    test('getAllBooks should return all books in the collection', () => {
        const book1 = new Book("Cara membedakan kucing dan ayam", "Rama", 2025)
        const book2 = new Book("Cara membedakan kaki kanan dan kiri", "Rina", 2025)
        bookManager.addBook(book1)
        bookManager.addBook(book2)
        const allBooks = bookManager.getAllBooks()
        expect(allBooks.length).toBe(2)
        expect(allBooks).toEqual([book1, book2])
    })
    // getAllBooks function

    // clearBooks function
    test('clearBooks should remove all books from the collection', () => {
        const book1 = new Book("Cara membedakan kucing dan ayam", "Rama", 2025)
        const book2 = new Book("Cara membedakan kaki kanan dan kiri", "Rina", 2025)
        bookManager.addBook(book1)
        bookManager.addBook(book2)
        bookManager.clearAllBooks()
        expect(bookManager.getBookCount()).toBe(0)
        expect(bookManager.getAllBooks()).toEqual([])
    })
    // clearBooks function


    // findBookByYear function
    test('findBookByYear should return books published in the specified year', () => {
        const book1 = new Book("Cara membedakan kucing dan ayam", "Rama", 2025)
        const book2 = new Book("Cara membedakan kaki kanan dan kiri", "Rina", 2024)
        const book3 = new Book("Cara merawat kucing", "Rama", 2025)
        bookManager.addBook(book1)
        bookManager.addBook(book2)
        bookManager.addBook(book3)
        const results = bookManager.findBookByYear(2025)
        expect(results.length).toBe(2)
        expect(results).toEqual([book1, book3])
    })

    test('findBookByYear should throw error if year is out of range', () => {
        expect(() => bookManager.findBookByYear(-1)).toThrow("Year must between 0 and 2100")
        expect(() => bookManager.findBookByYear(2200)).toThrow("Year must between 0 and 2100")
    })
    // findBookByYear function

    // containsBookTitle function
    test('containsBookTitle should return true if book title exists', () => {
        const book = new Book("Cara membedakan kucing dan ayam", "Rama", 2025)
        bookManager.addBook(book)
        expect(bookManager.containsBookTitle("Cara membedakan kucing dan ayam")).toBe(true)
    })

    test('containsBookTitle should return false if book title does not exist', () => {
        const book = new Book("Cara membedakan kucing dan ayam", "Rama", 2025)
        bookManager.addBook(book)
        expect(bookManager.containsBookTitle("Non-existing title")).toBe(false)
    })

    test('containsBookTitle should throw error if title is null or empty', () => {
        expect(() => bookManager.containsBookTitle(null)).toThrow("Title cannot be null")
    })
    // containsBookTitle function
})