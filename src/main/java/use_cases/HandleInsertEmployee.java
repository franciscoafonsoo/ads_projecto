package use_cases;

import business.ApplicationException;
import business.CatalogEmployee;
import business.Employee;

/**
 * Created by sherby on 15/05/2017.
 *
 * HandleInsertEmployee TODO: fazer javadoc
 */
public class HandleInsertEmployee {

    private CatalogEmployee employeeCatalog;

    public HandleInsertEmployee(CatalogEmployee employeeCatalog) {
        this.employeeCatalog = employeeCatalog;
    }

    public Employee newEmployee(String name, String password, String birth, int tlm, int vat) throws ApplicationException {
        return employeeCatalog.newEmployee(name, password, birth, tlm, vat);
    }
}
