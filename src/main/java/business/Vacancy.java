package business;

import java.sql.Date;


public class Vacancy {
    private final int id;
    private final int store_id;
    private final int section_id;
    private final int number;
    private final Date entry_date;

    public Vacancy(int id, int store_id, int section_id, int number, Date entry_date) {

        this.id = id;
        this.store_id = store_id;
        this.section_id = section_id;
        this.number = number;
        this.entry_date = entry_date;
    }

    public int getId() {
        return id;
    }

    public int getStore_id() {
        return store_id;
    }

    public int getSection_id() {
        return section_id;
    }

    public int getNumber() {
        return number;
    }

    public Date getEntry_date() {
        return entry_date;
    }

    @Override
    public String toString() {
        return "Vacancy @ " +
                "id=" + id +
                ", store_id=" + store_id +
                ", section_id=" + section_id +
                ", number=" + number +
                ", entry_date=" + entry_date +
                '}';
    }
}
