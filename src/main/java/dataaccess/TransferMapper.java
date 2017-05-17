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
            "INSERT INTO transfers (id, vacancy_id, employee_id, score, entry_date)" +
                    " VALUES (DEFAULT, ?, ?, ?, ?)";


    public static int insert(int vacancy_id, int employee_id, double score) throws PersistenceException {
        try (PreparedStatement statement = DataSource.INSTANCE.prepareGetGenKey(INSERT_TRANSFER_SQL)) {

            java.sql.Date entry = new java.sql.Date(new java.util.Date().getTime());

            // set statements
            statement.setInt(1, vacancy_id);
            statement.setInt(2, employee_id);
            statement.setDouble(3, score);
            statement.setDate(4, entry);
            // execute
            statement.executeUpdate();
            try (ResultSet rs = statement.getGeneratedKeys()) {
                rs.next();
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new PersistenceException ("Error inserting a new transfer request!", e);
        }
    }
}
