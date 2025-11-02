package com.Skoglund.Book;

public class NonFictionBook extends Book {
    private final String subject;

    public NonFictionBook(int id, String title, String author, double pricePerDay, String subject) {
        super(id, title, author, pricePerDay);
        this.subject = subject;
    }

    @Override
    public void displayInfo() {
        System.out.println("Non-Fiction: " + getTitle() + " by " + getAuthor() +
                ", Subject: " + subject + ", Price/day: " + getPricePerDay());
    }
}

