package com.Skoglund.Rental;

import com.Skoglund.Price.PricePolicy;
import com.Skoglund.Book.Book;
import Member.Member;

import java.time.LocalDate;

public class Rental {
    private final Member member;
    private final Book book;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public Rental(Member member, Book book, LocalDate startDate, LocalDate endDate) {
        this.member = member;
        this.book = book;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public double calculatePrice(PricePolicy policy) {
        long days = java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate) + 1;
        return policy.calculatePrice(book.getPricePerDay(), days);
    }

    public Member getMember() {
        return member;
    }
    public Book getBook() {
        return book;
    }

    @Override
    public String toString() {
        return "Rental {" + "member = " + member.getName() + ", book = " + book.getTitle() +
                ", from = " + startDate + ", to = " + endDate + '}';
    }
}

