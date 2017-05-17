package business;

import java.sql.Date;
import java.util.List;
import java.util.Objects;


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

    // getters and setters

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vacancy vacancy = (Vacancy) o;
        return id == vacancy.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static Vacancy containsId(List<Vacancy> list, int id) {
        for (Vacancy object : list) {
            if (object.getId() == id) {
                return object;
            }
        }
        return null;
    }
}
