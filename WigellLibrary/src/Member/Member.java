package Member;

import com.Skoglund.Rental.Rental;

import java.util.ArrayList;
import java.util.List;

public class Member {
    private final int id;
    private final String name;
    private String status;
    private final List<Rental> history;

    public Member(int id, String name, String status) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.history = new ArrayList<>();
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getStatus() { return status; }
    public List<Rental> getHistory() { return history; }

    public void addRental(Rental rental) {
        history.add(rental);
    }

    @Override
    public String toString() {
        return "Member: " + "id = " + id + ", name = " + name + '\'' + ", status = " + status + '\'';
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

