package com.techelevator.model;

public class BookUser {
    private int userId;
    private String isbn;
    private int minutesRead;
    private int pagesRead;
    private boolean isReadOutLoudReader;
    private boolean isReadOutLoudListener;
    private String notes;
    private boolean completed;

    public BookUser(int userId, String isbn, int minutesRead, boolean isReadOutLoudReader, boolean isReadOutLoudListener, String notes, boolean completed, int pagesRead) {
        this.userId = userId;
        this.isbn = isbn;
        this.minutesRead = minutesRead;
        this.isReadOutLoudReader = isReadOutLoudReader;
        this.isReadOutLoudListener = isReadOutLoudListener;
        this.notes = notes;
        this.completed = completed;
        this.pagesRead = pagesRead;
    }

    public int getPagesRead() {
        return pagesRead;
    }

    public void setPagesRead(int pagesRead) {
        this.pagesRead = pagesRead;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public BookUser() {};

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getMinutesRead() {
        return minutesRead;
    }

    public void setMinutesRead(int minutesRead) {
        this.minutesRead = minutesRead;
    }

    public boolean isReadOutLoudReader() {
        return isReadOutLoudReader;
    }

    public void setReadOutLoudReader(boolean readOutLoudReader) {
        isReadOutLoudReader = readOutLoudReader;
    }

    public boolean isReadOutLoudListener() {
        return isReadOutLoudListener;
    }

    public void setReadOutLoudListener(boolean readOutLoudListener) {
        isReadOutLoudListener = readOutLoudListener;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
