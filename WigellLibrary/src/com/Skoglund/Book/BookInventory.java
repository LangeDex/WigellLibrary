package com.Skoglund.Book;

import java.util.ArrayList;
import java.util.List;

public class BookInventory {
    private final List<Book> books = new ArrayList<>();

    public void addBook(Book book) { books.add(book); }
    public List<Book> getBooks() { return books; }

    public Book findBookById(int id) {
        for (Book book : books) {
            if (book.getId() == id) return book;
        }
        return null;
    }

    public void listBooksByType(String type) {
        System.out.println("Böcker av typ: " + type);
        int count = 0;

        for (Book b : books) {
            if (type.equalsIgnoreCase("fiction") && b instanceof FictionBook) {
                System.out.println(b);
                count++;
            } else if (type.equalsIgnoreCase("nonfiction") && b instanceof NonFictionBook) {
                System.out.println(b);
                count++;
            }
        }

        if (count == 0) {
            System.out.println("Inga böcker hittades av typen " + type + ".");
        } else {
            System.out.println("Totalt antal: " + count);
        }
    }
}

