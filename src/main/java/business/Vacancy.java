package business;

import java.sql.Date;
import java.util.List;
import java.util.Objects;


public class Vacancy {
    private int id;
    private int store_id;
    private int section_id;
    private int free;
    private int occupied;
    private Date entry_date;


    public Vacancy(int id, int store_id, int section_id, int free, int occupied, Date entry_date) {
        this.id = id;
        this.store_id = store_id;
        this.section_id = section_id;
        this.free = free;
        this.occupied = occupied;
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

    public int getFree() {
        return free;
    }

    public void setFree(int free) {
        this.free = free;
    }

    public int getOccupied() {
        return occupied;
    }

    public void setOccupied(int occupied) {
        this.occupied = occupied;
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
                ", free=" + free +
                ", occupied=" + occupied +
                ", entry_date=" + entry_date;
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
