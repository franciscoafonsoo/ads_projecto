package use_cases;

import business.*;

/**
 * Created by sherby on 15/05/2017.
 *
 * HandleInsertEmployee TODO: fazer javadoc
 */
public class HandleInsertEmployee {

    private CatalogEmployee employeeCatalog;
    private CatalogStore storeCatalog;

    public HandleInsertEmployee(CatalogEmployee employeeCatalog, CatalogStore storeCatalog) {
        this.employeeCatalog = employeeCatalog;
        this.storeCatalog = storeCatalog;
    }

    public Employee newEmployee(String name, String password, String birth, int tlm, int vat) throws ApplicationException {
        return employeeCatalog.newEmployee(name, password, birth, tlm, vat);
    }

    public Store addEmployeeToStore(Employee e, int storeId, int sectionId) throws ApplicationException {
        return storeCatalog.addEmployeeToStore(e, storeId, sectionId);
    }

    public Employee getEmployee(int id) throws ApplicationException {
        return employeeCatalog.getEmployee(id);
    }
}
