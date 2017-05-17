package business;


import dataaccess.PersistenceException;
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
}
