package dataaccess;


import business.Transfer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class TransferMapper {

    static Map<Integer, Transfer> cachedTransfer;

    static {
        cachedTransfer = new HashMap<Integer, Transfer>();
    }

    private static final String INSERT_TRANSFER_SQL =
            "INSERT INTO transfer (id, vacancy_id, employee_id, score)" +
                    " VALUES (DEFAULT, ?, ?, ?)";

    public static int insert(String name, String pwd, int tlm, java.util.Date birth, double salary
            , int vat) throws PersistenceException {
        try (PreparedStatement statement = DataSource.INSTANCE.prepareGetGenKey(INSERT_TRANSFER_SQL)) {

            java.sql.Date bir = new java.sql.Date(birth.getTime());
            java.sql.Date entry = new java.sql.Date(new java.util.Date().getTime());

            // set statement arguments
            statement.setString(1, name);
            statement.setString(2, pwd);
            statement.setDate(3, bir);
            statement.setInt(4, tlm);
            statement.setDate(5, entry);
            statement.setDouble(6, salary);
            statement.setInt(7, vat);
            statement.setBoolean(8, false);
            // execute SQL
            statement.executeUpdate();
            // get sale Id generated automatically by the database engine
            try (ResultSet rs = statement.getGeneratedKeys()) {
                rs.next();
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new PersistenceException ("Error inserting a new employee!", e);
        }
    }

    public static void insert(int vacancy_id, int employee_id, int score) throws PersistenceException {
    }
}
