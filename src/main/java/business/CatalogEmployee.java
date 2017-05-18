package business;

import dataaccess.EmployeeMapper;
import dataaccess.PersistenceException;

/**
 * Created by sherby on 15/05/2017.
 *
 * Catalog Employee
 */
public class CatalogEmployee {


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

    public Employee getEmployee(int id) throws ApplicationException {
        try {
            return EmployeeMapper.getEmployeeById(id);
        } catch (PersistenceException e) {
            throw new ApplicationException("Unable to fetch employee", e);
        }
    }

    public void changeEmployee(int id, int store_id, int section_id) throws ApplicationException {
        try {
            EmployeeMapper.update(id, store_id, section_id);
        } catch (PersistenceException e) {
            throw new ApplicationException("Unable to update employee", e);
        }
    }
}
