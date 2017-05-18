package business;

import java.sql.Date;

/**
 * Created by sherby on 17/05/2017.
 */
public class Transfer {

    /**
     *  Transfer's attributes
     */
    private int id;
    private int vacancy_id;
    private int employee_id;
    private double score;
    private Date entry_date;
    private boolean is_processed;

    /**
     * Creates a new Transfer with the information needed
     *
     * @param id an Integer representing the Transfer's ID
     * @param vacancy_id an Integer representing the Transfer's
     * @param employee_id an Integer representing the Transfer's
     * @param score a Double representing the Transfer's
     * @param entry_date a Date representing the Transfer's
     * @param is_processed a boolean representing the Transfer's
     */
    public Transfer(int id, int vacancy_id, int employee_id, double score, Date entry_date, boolean is_processed) {

        this.id = id;
        this.vacancy_id = vacancy_id;
        this.employee_id = employee_id;
        this.score = score;
        this.entry_date = entry_date;
        this.is_processed = is_processed;
    }

    public int getId() {
        return id;
    }

    public int getVacancy_id() {
        return vacancy_id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Date getEntry_date() {
        return entry_date;
    }

    public boolean is_processed() {
        return is_processed;
    }

    /**
     * Returns a string representing the object
     *
     * @return a String that represents a Transfer
     */
    @Override
    public String toString() {
        return "Transfer @" +
                "id=" + id +
                ", vacancy_id=" + vacancy_id +
                ", employee_id=" + employee_id +
                ", score=" + score +
                ", request date=" + entry_date;
    }
}
