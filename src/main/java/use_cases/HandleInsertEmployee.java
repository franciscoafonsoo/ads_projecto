package use_cases;

import business.*;

/**
 * Created by sherby on 15/05/2017.
 *
 * HandleInsertEmployee
 */
public class HandleInsertEmployee {

    /**
     * attributes
     */
    private CatalogEmployee employeeCatalog;
    private CatalogStore storeCatalog;

    /**
     * Creates a new handle
     *
     * @param employeeCatalog an EmployeeCatalog
     * @param storeCatalog a StoreCatalog
     */
    public HandleInsertEmployee(CatalogEmployee employeeCatalog, CatalogStore storeCatalog) {
        this.employeeCatalog = employeeCatalog;
        this.storeCatalog = storeCatalog;
    }

    /**
     * Adds a new employee to the database
     *
     * @param name a String representing the employee's name
     * @param password a String representing the employee's password
     * @param birth a Date representing the employee's birthday
     * @param tlm an Integer representing the employee's phone number
     * @param vat an Integer representing the employee's vat number
     * @return an Employee object representing the employee inserted into the database
     * @throws ApplicationException if there's already an employee with the given vat number, or if the insert failed
     */
    public Employee newEmployee(String name, String password, String birth, int tlm, int vat) throws ApplicationException {
        return employeeCatalog.newEmployee(name, password, birth, tlm, vat);
    }

    /***
     * Adds an Employee to a section in a store
     *
     * @param e The employee
     * @param storeId Id of store
     * @param sectionId Id of section
     * @throws ApplicationException if the store, or the section doesn't exist
     */
    public Store addEmployeeToStore(Employee e, int storeId, int sectionId) throws ApplicationException {
        return storeCatalog.addEmployeeToStore(e, storeId, sectionId);
    }

    /**
     * Gets and employee by it's ID
     *
     * @param id an Integer representing the employee's ID
     * @return an Employee object representing the employee requested
     * @throws ApplicationException if the select from the database failed
     */
    public Employee getEmployee(int id) throws ApplicationException {
        return employeeCatalog.getEmployee(id);
    }
}
