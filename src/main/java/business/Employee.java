package business;

import java.sql.Date;


public class Employee {


    private int     id;
    private String  name;
    private String  pwd;
    private int     tlm;
    private Date    date;
    private float   salary;
    private int     vat;
    private int     store;
    private int     section;

    public Employee(int id, String name, String pwd, int tlm, Date date, float salary, int vat, int store, int section) {

        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.tlm = tlm;
        this.date = date;
        this.salary = salary;
        this.vat = vat;
        this.store = store;
        this.section = section;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    @Override
    public String toString() {
        return "Employee @ " +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", tlm=" + tlm +
                ", date=" + date +
                ", salary=" + salary +
                ", vat=" + vat +
                ", store=" + store +
                ", section=" + section +
                '}';
    }
}
