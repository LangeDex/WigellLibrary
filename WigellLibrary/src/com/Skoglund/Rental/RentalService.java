package com.Skoglund.Rental;

import Member.Member;

import java.util.ArrayList;
import java.util.List;

public class RentalService {
    private final List<Rental> rentals = new ArrayList<>();

    public void rentItem(Member member, Rental rental) {
        rentals.add(rental);
        member.addRental(rental);
    }

    public void endRental(int bookId) {
        for (Rental rental : rentals) {
            if (rental.getBook().getId() == bookId) {
                rentals.remove(rental);
                System.out.println("Bok med ID " + bookId + " har återlämnats.");
                return;
            }
        }
        System.out.println("Fel: Ingen aktiv uthyrning hittades för bok med ID " + bookId);
    }

}


