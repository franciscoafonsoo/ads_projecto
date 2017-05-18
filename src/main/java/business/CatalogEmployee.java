package business;

import dataaccess.EmployeeMapper;
import dataaccess.PersistenceException;

/**
 * Created by sherby on 15/05/2017.
 *
 * Catalog Employee
 */
public class CatalogEmployee {


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
        try {
            java.util.Date b = new java.util.Date(birth);

            // value should be calculated according to other parameters, for now this is good.
            double salary = 900.0;

            if(!EmployeeMapper.getEmployeeByVAT(vat)) {
                int employee_id = EmployeeMapper.insert(name, password, tlm, b, salary, vat);
                return EmployeeMapper.getEmployeeById(employee_id);
            }
            else
                throw new ApplicationException("Employee already exists");

            } catch (PersistenceException e) {
                throw new ApplicationException("Unable to create new employee", e);
            }
    }

    /**
     * Gets and employee by it's ID
     *
     * @param id an Integer representing the employee's ID
     * @return an Employee object representing the employee requested
     * @throws ApplicationException if the select from the database failed
     */
    public Employee getEmployee(int id) throws ApplicationException {
        try {
            return EmployeeMapper.getEmployeeById(id);
        } catch (PersistenceException e) {
            throw new ApplicationException("Unable to fetch employee", e);
        }
    }

    /**
     * Updates an Employee
     *
     * @param id an Integer representing the employee's id
     * @param store_id an Integer representing the employee's store ID
     * @param section_id an Integer representing the employee's section ID
     * @throws ApplicationException if the update query failed
     */
    public void changeEmployee(int id, int store_id, int section_id) throws ApplicationException {
        try {
            EmployeeMapper.update(id, store_id, section_id);
        } catch (PersistenceException e) {
            throw new ApplicationException("Unable to update employee", e);
        }
    }
}
