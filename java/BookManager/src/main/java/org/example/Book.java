package org.example;

import java.util.Objects;

public class Book {
    private final String Book_Title;
    private final String Author;
    private final int Years;

    public Book(String book_Title, String author, int years){
        if(book_Title == null || book_Title.trim().isEmpty()){
            throw new IllegalArgumentException("Title required");
        }

        if(author == null || author.trim().isEmpty()){
            throw new IllegalArgumentException("Author required");
        }

        if(years < 2000 || years > 2100){
            throw new IllegalArgumentException("Years must between 2000 and 2100");
        }

        this.Book_Title = book_Title;
        this.Author = author;
        this.Years = years;
    }

    public String getBook_Title(){
        return Book_Title;
    }
    public String getAuthor(){
        return Author;
    }
    public Integer getYears(){
        return Years;
    }

    @Override
    public String toString(){
        return String.format("%s by %s (%d)", Book_Title, Author, Years);
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Book book = (Book) obj;
        return Objects.equals(Years, book.Years)
                && Book_Title.equals(book.Book_Title)
                && Author.equals(book.Author);
    }

    @Override
    public int hashCode(){
        return Objects.hash(Book_Title, Author, Years);
    }
}
