const Book = require("./book")

class BookManager {
    constructor() {
        this.books = []
    }

    /**
     * 
     * @param {string} param 
     * @returns 
     */
    toLower(param) {
        if (typeof param === 'string') {
            return param.toLowerCase();
        }
        return param;
    }

    /**
     * 
     * @param {Book} book 
     * @returns {void}
     */
    addBook(book) {
        if (book == null) {
            throw new Error("Book cannot empty")
        }
        this.books.push(book)
    }

    /**
     * 
     * @param {string} title 
     * @returns {boolean}
     */
    removeBook(title) {
        if (title === null || !title.trim()) {
            throw new Error("Title cannot be null")
        }
        const initialLength = this.books.length
        this.books = this.books.filter(book =>
            book.title.toLowerCase() != this.toLower(title.trim())
        );
        return this.books.length < initialLength
    }


    /**
     * 
     * @returns {Book[]}
     */
    getAllBooks() {
        return [...this.books]
    }

    /**
     * 
     * @param {string} author 
     */
    findBooksByAuthor(author) {
        if (author === null || !author.trim()) {
            throw new Error("Author cannot be null")
        }
        return this.books.filter(book =>
            book.author.toLowerCase() === this.toLower(author.trim())
        )
    }

    /**
     * 
     * @param {int} year 
     * @returns {Book[]}
     */
    findBookByYear(year){
        if(year < 0 || year > 2100){
            throw new Error("Year must between 0 and 2100")
        }
        return this.books.filter(book => book.year === year)
    }

    /**
     * 
     * @returns {int}
     */
    getBookCount(){
        return this.books.length
    }

    /**
     * 
     * @param {string} title 
     * @returns {boolean}
     */
    containsBookTitle(title){
        if (title === null || !title.trim()) {
            throw new Error("Title cannot be null")
        }
        return this.books.some(book =>
            book.title.toLowerCase() === this.toLower(title.trim())
        )
    }

    /**
     * @returns {void}
     */
    clearAllBooks(){
        this.books = []
    }
}

module.exports = BookManager