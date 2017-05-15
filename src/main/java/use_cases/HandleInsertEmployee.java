package use_cases;

import business.ApplicationException;
import business.CatalogEmployee;
import business.CatalogStore;
import business.Employee;

/**
 * Created by sherby on 15/05/2017.
 *
 * HandleInsertEmployee TODO: fazer javadoc
 */
public class HandleInsertEmployee {

    private CatalogEmployee employeeCatalog;
    private CatalogStore storeCatalog;

    public HandleInsertEmployee(CatalogEmployee employeeCatalog) {
        this.employeeCatalog = employeeCatalog;
        this.storeCatalog = storeCatalog;
    }

    public Employee newEmployee(String name, String password, String birth, int tlm, int vat) throws ApplicationException {
        return employeeCatalog.newEmployee(name, password, birth, tlm, vat);
    }

    public void addEmployeeToStore(Employee e) throws ApplicationException {
        storeCatalog.addEmployeeToStore(e);
    }
}
