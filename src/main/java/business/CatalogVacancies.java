package business;

import dataaccess.PersistenceException;
import dataaccess.VacancyMapper;

import java.security.Permission;
import java.util.List;


public class CatalogVacancies {
    public List<Vacancy> consultAllVacancies() throws ApplicationException {
        try {
            return VacancyMapper.getAllVacancies();
        } catch (PersistenceException e1) {
            throw new ApplicationException("Unable to get Vacancies", e1);
        }
    }

    public List<Vacancy> consultVacanciesByStore(int storeId) throws ApplicationException {
        try {
            return VacancyMapper.getAllVacanciesByStoreId(storeId);
        } catch (PersistenceException e1) {
            throw new ApplicationException("Unable to get Vacancies", e1);
        }
    }

    public List<Vacancy> getAllVacancies() throws ApplicationException {
        try {
            return VacancyMapper.getAllVacancies();
        } catch (PersistenceException e) {
            throw new ApplicationException("Unable to get Vacancies", e);
        }
    }

    public void updateVacancies(int id, int free, int occupied) throws ApplicationException {
        try {
            VacancyMapper.update(id, free, occupied);
        } catch (PersistenceException e) {
            throw new ApplicationException("Unable to update Vacancies", e);
        }
    }
}
