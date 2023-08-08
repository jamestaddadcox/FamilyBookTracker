package com.techelevator.model;

public class BookUser {
    private int userId;
    private String isbn;
    private int minutesRead;
    private boolean isReadOutLoudReader;
    private boolean isReadOutLoudListener;
    private String notes;

    public BookUser(int userId, String isbn, int minutesRead, boolean isReadOutLoudReader, boolean isReadOutLoudListener, String notes) {
        this.userId = userId;
        this.isbn = isbn;
        this.minutesRead = minutesRead;
        this.isReadOutLoudReader = isReadOutLoudReader;
        this.isReadOutLoudListener = isReadOutLoudListener;
        this.notes = notes;
    }

<<<<<<< HEAD
=======
    public BookUser() {};

>>>>>>> main
    public int getUserId() {
        return userId;
    }

<<<<<<< HEAD
=======
    public void setUserId(int userId) {
        this.userId = userId;
    }
>>>>>>> main

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
