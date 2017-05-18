package use_cases;

import business.*;

import java.util.List;


public class HandleRequestTransfer {
    private CatalogEmployee     employeeCatalog;
    private CatalogTransfers    transfersCatalog;
    private CatalogVacancies    vacanciesCatalog;

    public HandleRequestTransfer(CatalogEmployee employeeCatalog, CatalogTransfers transfersCatalog, CatalogVacancies vacanciesCatalog) {
        this.employeeCatalog  = employeeCatalog;
        this.transfersCatalog = transfersCatalog;
        this.vacanciesCatalog = vacanciesCatalog;
    }

    public List<Vacancy> consultAllVacancies() throws ApplicationException {
        return vacanciesCatalog.consultAllVacancies();
    }

    public List<Vacancy> consultVacanciesByStoreId(int store_id) throws ApplicationException {
        return vacanciesCatalog.consultVacanciesByStore(store_id);
    }


    public int requestTransfer(Vacancy vacancy, Employee employee) throws ApplicationException {
        return transfersCatalog.requestTransfer(vacancy, employee);
    }
}
