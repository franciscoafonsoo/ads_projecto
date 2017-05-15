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
            double salary = 900.25;

            if(!EmployeeMapper.getEmployeeByVAT(vat)) {
                // como fazer loja e seccao ?
                int employee_id = EmployeeMapper.insert(name, password, tlm, b, salary, vat, 1, 1);
                return EmployeeMapper.getEmployeeById(employee_id);
            }
            else
                throw new ApplicationException("Employee already exists");

            } catch (PersistenceException e) {
                throw new ApplicationException("Unable to create new employee", e);
            }
    }
}
