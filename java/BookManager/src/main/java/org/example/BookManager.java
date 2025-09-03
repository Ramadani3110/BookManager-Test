package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
public class BookManager {

    private final List<Book> Books;

    public BookManager(){
        this.Books = new ArrayList<>();
    }

    public void addBook(Book book){
        if(book == null){
            throw new IllegalArgumentException("Book can't empty");
        }
        if(containsBook(book.getBook_Title())){
            throw new IllegalArgumentException("Title already exist");
        }
        Books.add(book);
    }

    public boolean removeBook(String title){
        if(title == null || title.trim().isEmpty()){
            throw new IllegalArgumentException("Title can't empty");
        }
        return Books.removeIf(book ->
            book.getBook_Title().equalsIgnoreCase(title.trim())
        );
    }

    public List<Book> getAllBooks(){
        return new ArrayList<>(Books);
    }

    public List<Book> findBookByAuthor(String author){
        if(author == null || author.trim().isEmpty()){
            throw new IllegalArgumentException("Author can't empty");
        }
        return Books.stream()
                .filter(book ->
                        book.getAuthor().equalsIgnoreCase(author.trim())
                ).collect(Collectors.toList());
    }

    public List<Book> findBookByYear(int year){
        if(year < 2000 || year > 2100){
            throw new IllegalArgumentException("Years must between 2000 and 2100");
        }
        return Books.stream()
                .filter(book ->
                        book.getYears() == year
                ).collect(Collectors.toList());
    }

    public boolean containsBook(String title){
        if(title == null || title.trim().isEmpty()){
            throw new IllegalArgumentException("Title can't empty");
        }
        return Books.stream()
                .anyMatch(book -> book.getBook_Title().equalsIgnoreCase(title.trim()));
    }

    public int getBookSize(){
        return Books.size();
    }

    public void clearAllBooks(){
        Books.clear();
    }
}
