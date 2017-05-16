package business;


public class Store {


    private int id;
    private String address;
    private String district;
    private int tlm;
    private int fax;
    private String email;

    public Store(int id, String address, String district, int tlm, int fax, String email) {
        this.id = id;
        this.address = address;
        this.district = district;
        this.tlm = tlm;
        this.fax = fax;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getTlm() {
        return tlm;
    }

    public void setTlm(int tlm) {
        this.tlm = tlm;
    }

    public int getFax() {
        return fax;
    }

    public void setFax(int fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Store @ " +
                "id=" + id +
                ", address='" + address + '\'' +
                ", district='" + district + '\'' +
                ", tlm=" + tlm +
                ", fax=" + fax +
                ", email='" + email + '\'';
    }
}
