package business;


import java.util.ArrayList;
import java.util.List;

public class Store {


    private int id;
    private String address;
    private String district;
    private int tlm;
    private int fax;
    private String email;

    /**
     * Creates a new Store with the information needed
     *
     * @param id store if
     * @param address
     * @param district
     * @param tlm
     * @param fax
     * @param email
     */
    public Store(int id, String address, String district, int tlm, int fax, String email) {
        this.id = id;
        this.address = address;
        this.district = district;
        this.tlm = tlm;
        this.fax = fax;
        this.email = email;
    }

    /**
     * @return the store's id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id sets the store's id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the store's address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address sets the store's address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the store's district
     */
    public String getDistrict() {
        return district;
    }

    /**
     * @param district sets the store's district
     */
    public void setDistrict(String district) {
        this.district = district;
    }

    /**
     * @return the store phone number
     */
    public int getTlm() {
        return tlm;
    }

    /**
     * @param tlm sets the store's phone number
     */
    public void setTlm(int tlm) {
        this.tlm = tlm;
    }

    /**
     * @return the store's fax number
     */
    public int getFax() {
        return fax;
    }

    /**
     * @param fax sets the store's fax number
     */
    public void setFax(int fax) {
        this.fax = fax;
    }

    /**
     * @return the store's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email sets the store's email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return a String that represents a Store
     */
    @Override
    public String toString() {
        return "Store @ " +
                "id=" + id +
                ", address='" + address + '\'' +
                ", district='" + district + '\'' +
                ", tlm=" + tlm +
                ", fax=" + fax +
                ", email='" + email;
    }
}
