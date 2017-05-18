package dataaccess;


import business.Vacancy;

import javax.xml.crypto.Data;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class VacancyMapper {

    static Map<Integer, Vacancy> cachedVacancy;

    static {
        cachedVacancy = new HashMap<Integer, Vacancy>();
    }


    private static final String GET_ALL_VACANCIES_SQL = "SELECT * FROM vacancies";

    /**
     * Gets all Vacancies
     *
     * @return a List of vacancies
     * @throws PersistenceException In case there is an error accessing the database.
     */
    public static List<Vacancy> getAllVacancies() throws PersistenceException {

        try (PreparedStatement statement = DataSource.INSTANCE.prepare(GET_ALL_VACANCIES_SQL)) {
            try (ResultSet rs = statement.executeQuery()) {
                return loadSeveralVacancies(rs);
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to fetch all vacancies", e);
        }
    }

    private static final String GET_ALL_VACANCIES_BY_STORE_ID_SQL = "SELECT * FROM vacancies WHERE store_id = ?";

    /**
     * Gets all vacancies by a store ID
     *
     * @param store_id the store id
     * @return a List of Vacancies
     * @throws PersistenceException In case there is an error accessing the database.
     */
    public static List<Vacancy> getAllVacanciesByStoreId(int store_id) throws PersistenceException {
        try (PreparedStatement statement = DataSource.INSTANCE.prepare(GET_ALL_VACANCIES_BY_STORE_ID_SQL)) {

            statement.setInt(1, store_id);

            try (ResultSet rs = statement.executeQuery()) {
                return loadSeveralVacancies(rs);
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to fetch all vacancies", e);
        }
    }

    /**
     * Creates a list of Vacancies
     *
     * @requires rs.next() was already executed
     * @param rs The result set with the information to create the list.
     * @return a List of Vacancy
     * @throws PersistenceException In case there is an error accessing the database.
     * @throws SQLException
     */
    private static List<Vacancy> loadSeveralVacancies(ResultSet rs) throws PersistenceException, SQLException {
        List<Vacancy> vacancies = new LinkedList<Vacancy>();

        while (rs. next()) {
            int vacancy_id = rs.getInt("id");
            if (cachedVacancy.containsKey(vacancy_id))
                vacancies.add(cachedVacancy.get(vacancy_id));
            else {
                Vacancy vacancy = loadVacancy(rs);
                vacancies.add(vacancy);
                cachedVacancy.put(vacancy_id, vacancy);
            }
        }
        return vacancies;
    }
    /**
     * Creates a Vacancy object from a result set retrieved from the database.
     *
     * @requires rs.next() was already executed
     * @param rs The result set with the information to create a vacancy.
     * @return A new Vacancy loaded from the database.
     * @throws PersistenceException In case there is an error accessing the database.
     */

    private static Vacancy loadVacancy(ResultSet rs) throws PersistenceException {
        Vacancy vacancy;
        try {
            vacancy = new Vacancy(
                    rs.getInt("id"),
                    rs.getInt("store_id"),
                    rs.getInt("section_id"),
                    rs.getInt("free"),
                    rs.getInt("occupied"),
                    rs.getDate("entry_date")
            );
        } catch (SQLException e) {
            throw new RecordNotFoundException("Vacancy not found", e);
        }
        return vacancy;
    }

    private static final String UPDATE_VACANCY_SQL =
            "UPDATE vacancies SET free = ?, occupied = ? WHERE id = ?";

    /**
     * Updates a vacancy
     *
     * @param vacancy_id the vacancy id
     * @param free the nunber of free "slots" in the vacancy
     * @param occupied the nunber of occupied "slots" in the vacancy
     * @throws PersistenceException In case there is an error accessing the database.
     */
    public static void update(int vacancy_id, int free, int occupied) throws PersistenceException {
        try (PreparedStatement statement = DataSource.INSTANCE.prepare(UPDATE_VACANCY_SQL)) {
            statement.setInt(1, free);
            statement.setInt(2, occupied);
            statement.setInt(3, vacancy_id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException("Internal error!", e);
        }
        cachedVacancy.remove(vacancy_id);
    }
}