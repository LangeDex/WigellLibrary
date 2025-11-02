package com.Skoglund.Book;

public class FictionBook extends Book {
    private final String genre;

    public FictionBook(int id, String title, String author, double pricePerDay, String genre) {
        super(id, title, author, pricePerDay);
        this.genre = genre;
    }

    @Override
    public void displayInfo() {
        System.out.println("Fiction: " + getTitle() + " by " + getAuthor() +
                ", Genre: " + genre + ", Price/day: " + getPricePerDay());
    }
}

