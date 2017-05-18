package business;

import dataaccess.PersistenceException;
import dataaccess.VacancyMapper;

import java.security.Permission;
import java.util.List;

public class CatalogVacancies {

    /**
     * Gets a list of all vacancies
     *
     * @return a List of Vacancy objects
     * @throws ApplicationException if the select query failed
     */
    public List<Vacancy> consultAllVacancies() throws ApplicationException {
        try {
            return VacancyMapper.getAllVacancies();
        } catch (PersistenceException e1) {
            throw new ApplicationException("Unable to get Vacancies", e1);
        }
    }

    /**
     * Gets a list of all vacancies of a given store
     *
     * @param storeId an Integer representing the store's ID
     * @return a List of Vacancy objects
     * @throws ApplicationException if the select query failed
     */
    public List<Vacancy> consultVacanciesByStore(int storeId) throws ApplicationException {
        try {
            return VacancyMapper.getAllVacanciesByStoreId(storeId);
        } catch (PersistenceException e1) {
            throw new ApplicationException("Unable to get Vacancies", e1);
        }
    }

    /**
     * Gets all vacancies
     *
     * @return a List on Vacancies
     * @throws ApplicationException if the select query failed
     */
    public List<Vacancy> getAllVacancies() throws ApplicationException {
        try {
            return VacancyMapper.getAllVacancies();
        } catch (PersistenceException e) {
            throw new ApplicationException("Unable to get Vacancies", e);
        }
    }

    /**
     * Updates a vacancy
     *
     * @param id an Integer representing the vacancy's ID
     * @param free an Integer representing the number of vacancies
     * @param occupied an Integer representing the number of vacancies filled
     * @throws ApplicationException if the select query failed
     */
    public void updateVacancies(int id, int free, int occupied) throws ApplicationException {
        try {
            VacancyMapper.update(id, free, occupied);
        } catch (PersistenceException e) {
            throw new ApplicationException("Unable to update Vacancies", e);
        }
    }
}
