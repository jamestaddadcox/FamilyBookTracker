package com.techelevator.model;

public class Book {

    private String isbn;
    private String title;
    private String author;
    private String description;
//    private String format;
    private int pages;

    public Book(String isbn, String title, String author, String description, String format, int pages) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.description = description;
//        this.format = format;
        this.pages = pages;
    }

    public Book() {};

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public String getFormat() {
//        return format;
//    }

//    public void setFormat(String format) {
//        this.format = format;
//    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
