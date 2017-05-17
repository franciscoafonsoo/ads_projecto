package business;


import dataaccess.PersistenceException;
import dataaccess.TransferMapper;
import dataaccess.VacancyMapper;

import java.util.List;

public class CatalogTransfers {

    public static void consultVacancies() {

    }

    public static List<Vacancy> consultAllVacancies() throws ApplicationException {
        try {
            return VacancyMapper.getAllVacancies();
        } catch (PersistenceException e1) {
            throw new ApplicationException("Unable to get Vacancies", e1);
        }
    }

    public static List<Vacancy> consultVacanciesByStore(int storeId) throws ApplicationException {
        try {
            return VacancyMapper.getAllVacanciesByStoreId(storeId);
        } catch (PersistenceException e1) {
            throw new ApplicationException("Unable to get Vacancies", e1);
        }
    }

    public static int requestTransfer(Vacancy vacancy, Employee employee) throws ApplicationException{
        try {
            if (vacancy != null)
                return TransferMapper.insert(vacancy.getId(), employee.getId(), employee.getScore());
            else
                throw new ApplicationException("Choose a valid vacancy");
        } catch (PersistenceException e1) {
            throw new ApplicationException("Unable to request Transfer", e1);
        }
    }

    public static List<Transfer> confirmTransfers() throws ApplicationException {
        try {
            List<Transfer> t = TransferMapper.getAllTransfers();
            return t;
        } catch (PersistenceException e) {
            throw new ApplicationException("Unable to process Transfers", e);
        }
    }
}
