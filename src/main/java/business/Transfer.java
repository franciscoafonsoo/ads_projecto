package business;

import java.sql.Date;

/**
 * Created by sherby on 17/05/2017.
 */
public class Transfer {
    private final int id;
    private final int vacancy_id;
    private final int employee_id;
    private final double score;
    private final Date entry_date;
    private final boolean is_processed;

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

    public Date getEntry_date() {
        return entry_date;
    }

    public boolean is_processed() {
        return is_processed;
    }

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
