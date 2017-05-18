package business;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;


public class Employee {


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
     * Creates a new Employee with the information needed
     *  @param id
     * @param name Name of Employee
     * @param pwd
     * @param tlm
     * @param entry_date
     * @param salary
     * @param vat
     * @param store
     * @param section
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

    // round score to a places value
    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

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
