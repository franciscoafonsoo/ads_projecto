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

    private static final String GET_VACANCY_BY_ID = "SELECT * FROM vacancies WHERE id = ?";

    public static Vacancy getVacancyById(int vacancy_id) throws PersistenceException {

        if(cachedVacancy.containsKey(vacancy_id))
            return cachedVacancy.get(vacancy_id);

        try (PreparedStatement statement = DataSource.INSTANCE.prepare(GET_VACANCY_BY_ID)) {
            statement.setInt(1, vacancy_id);

            try (ResultSet rs = statement.executeQuery()) {
                rs.next();
                Vacancy vacancy = loadVacancy(rs);
                cachedVacancy.put(vacancy.getId(), vacancy);
                return vacancy;
            }
        } catch (SQLException e) {
            throw new RecordNotFoundException("Vacancy not found", e);
        }
    }

    private static final String UPDATE_VACANCY_SQL =
            "UPDATE vacancies SET free = ?, occupied = ? WHERE id = ?";

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