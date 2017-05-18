package business;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;


public class Employee {

    /**
     *  Employee's attributes
     */
    private int     id;
    private String  name;
    private String  pwd;
    private int     tlm;
    private Date    entryDate;
    private Date    birth;
    private float   salary;
    private int     vat;
    private int     store;
    private int     section;
    private double  score;

    /**
     * Constructor for an Employee object.
     * Calculates the employee score based on his three scores
     *
     * @param id an Integer representing the employee's id
     * @param name a String representing the employee's name
     * @param pwd a String representing the employee's password
     * @param tlm an Integer representing the employee's phone number
     * @param entry_date a Date representing the employee's entry date in the system
     * @param birth a Date representing the employee's birthday
     * @param salary a Double representing the employee's salary
     * @param vat an Integer representing the employee's vat number
     * @param store an Integer representing the employee's store ID
     * @param section an Integer representing the employee's section ID
     * @param score_one an Integer representing the employee's first score
     * @param score_two an Integer representing the employee's second score
     * @param score_three an Integer representing the employee's third score
     */
    public Employee(int id, String name, String pwd, int tlm, Date entry_date, Date birth, float salary, int vat, int store, int section,
                    int score_one, int score_two, int score_three) {

        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.tlm = tlm;
        this.entryDate = entry_date;
        this.birth = birth;
        this.salary = salary;
        this.vat = vat;
        this.store = store;
        this.section = section;
        // round by: http://stackoverflow.com/a/2808648
        this.score = round(((score_one * 0.3) + (score_two * 0.2) + (score_three * 0.1)), 3);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getTlm() {
        return tlm;
    }

    public void setTlm(int tlm) {
        this.tlm = tlm;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public Date getBirth() {
        return birth;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public int getVat() {
        return vat;
    }

    public void setVat(int vat) {
        this.vat = vat;
    }

    public int getStore() {
        return store;
    }

    public void setStore(int store) {
        this.store = store;
    }

    public int getSection() {
        return section;
    }

    public void setSection(int section) {
        this.section = section;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    /**
     * Rounds up a Double value, to a given maximum number of digits
     *
     * @param value a Double representing the value to be rounded up
     * @param places an Integer representing the maximum number of digits
     * @return the rounded Double value
     */    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }


    /**
     * Returns a string representing the object
     *
     * @return a string representing the object
     */
    @Override
    public String toString() {
        return "Employee @ " +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", tlm=" + tlm +
                ", Entry_date=" + entryDate +
                ", birth=" + birth +
                ", salary=" + salary +
                ", vat=" + vat +
                ", store=" + store +
                ", section=" + section +
                ", score=" + score;
    }
}
