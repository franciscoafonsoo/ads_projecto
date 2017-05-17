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

    public static void requestTransfer(Vacancy vacancy, Employee employee) throws ApplicationException{
        try {
            if (vacancy != null) {

                TransferMapper.insert(vacancy.getId(), employee.getId(), vacancy.getSection_id());
            }
            else
                throw new ApplicationException("Choose a valid vacancy");
        } catch (PersistenceException e1) {
            throw new ApplicationException("Unable to request Transfer", e1);
        }
    }
}
