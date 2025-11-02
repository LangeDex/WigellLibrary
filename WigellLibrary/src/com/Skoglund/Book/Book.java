package com.Skoglund.Book;

public abstract class Book {
    private final int id;
    private final String title;
    private final String author;
    private final double pricePerDay;

    public Book(int id, String title, String author, double pricePerDay) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.pricePerDay = pricePerDay;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public double getPricePerDay() { return pricePerDay; }

    public abstract void displayInfo();

    @Override
    public String toString() {
        return "ID: " + id + " | Titel: " + title + " | Författare: " + author;
    }

}

