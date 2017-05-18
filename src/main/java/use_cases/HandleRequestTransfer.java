package use_cases;

import business.*;

import java.util.List;


public class HandleRequestTransfer {

    /**
     * attributes
     */
    private CatalogEmployee     employeeCatalog;
    private CatalogTransfers    transfersCatalog;
    private CatalogVacancies    vacanciesCatalog;

    /**
     * Creates a new handle
     *
     * @param employeeCatalog an EmployeeCatalog
     * @param transfersCatalog a TransfersCatalog
     * @param vacanciesCatalog a VacanciesCatalog
     */
    public HandleRequestTransfer(CatalogEmployee employeeCatalog, CatalogTransfers transfersCatalog, CatalogVacancies vacanciesCatalog) {
        this.employeeCatalog  = employeeCatalog;
        this.transfersCatalog = transfersCatalog;
        this.vacanciesCatalog = vacanciesCatalog;
    }

    /**
     * Gets a list of all vacancies
     *
     * @return a List of Vacancy objects
     * @throws ApplicationException if the select query failed
     */
    public List<Vacancy> consultAllVacancies() throws ApplicationException {
        return vacanciesCatalog.consultAllVacancies();
    }

    /**
     * Gets a list of all vacancies of a given store
     *
     * @param store_id an Integer representing the store's ID
     * @return a List of Vacancy objects
     * @throws ApplicationException if the select query failed
     */
    public List<Vacancy> consultVacanciesByStoreId(int store_id) throws ApplicationException {
        return vacanciesCatalog.consultVacanciesByStore(store_id);
    }

    /**
     * Inserts a new request for transfer
     *
     * @param vacancy a Vacancy object representing the vacancy to which the employee is requesting transfer
     * @param employee an Employee object representing the employee requesting transfer
     * @return in Integer representing the request id
     * @throws ApplicationException if the vacancy is null, or if the insert of the request failed
     */
    public int requestTransfer(Vacancy vacancy, Employee employee) throws ApplicationException {
        return transfersCatalog.requestTransfer(vacancy, employee);
    }
}
