package use_cases;

import business.*;
import dataaccess.PersistenceException;
import sun.net.www.ApplicationLaunchException;

import java.util.List;
import java.util.Scanner;


public class HandleRequestTransfer {
    private CatalogEmployee     employeeCatalog;
    private CatalogStore        storeCatalog;
    private CatalogTransfers    vancanciesCatalog;

    public HandleRequestTransfer(CatalogEmployee employeeCatalog, CatalogTransfers vancanciesCatalog) {
        this.employeeCatalog    = employeeCatalog;
        this.storeCatalog       = storeCatalog;
        this.vancanciesCatalog  = vancanciesCatalog;
    }

    public List<Vacancy> consultAllVacancies() throws ApplicationException {
        return CatalogTransfers.consultAllVacancies();
    }

    public List<Vacancy> consultVacanciesByStoreId(int store_id) throws ApplicationException {
        return CatalogTransfers.consultVacanciesByStore(store_id);
    }


    public void requestTransfer(Vacancy vacancy, Employee employee) throws ApplicationException {
        CatalogTransfers.requestTransfer(vacancy, employee);
    }
}
