package business;

import java.sql.Date;
import java.util.List;
import java.util.Objects;


public class Vacancy {

    /**
     * Vacancy's attributes
     */
    private int id;
    private int store_id;
    private int section_id;
    private int free;
    private int occupied;
    private Date entry_date;

    /**
     * Creates a new Vacancy with the information needed.
     * Each Vacancy object refers to a specific section and store, and can have more than one "slot" to fill
     *
     * @param id an Integer representing the vacancy's ID
     * @param store_id an Integer representing the store ID to which the vacancy refers
     * @param section_id an Integer representing the section ID to which the vacancy refers
     * @param free an Integer representing the number of vacancies
     * @param occupied an Integer representing the number of vacancies filled
     * @param entry_date a Date representing the date of origin of the vacancy
     */
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

    /**
     * Returns a string representing the object
     *
     * @return a String that represents a Vacancy
     */
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

    /**
     * Compares this object with the given one
     *
     * @param o an Object to be compared
     * @return true if it's the same object, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vacancy vacancy = (Vacancy) o;
        return id == vacancy.id;
    }

    /**
     * Hashes the vacancy's ID
     *
     * @return an Integer representing the hashed ID
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Checks if a Vacancy object with the given id exists in the given list, and returns it if so
     *
     * @param list a List fo Vacancy objects
     * @param id an Integer representing the ID of the Vacancy to search for
     * @return the Vacancy object if it exists in the list, null otherwise
     */
    public static Vacancy containsId(List<Vacancy> list, int id) {
        for (Vacancy object : list) {
            if (object.getId() == id) {
                return object;
            }
        }
        return null;
    }
}
