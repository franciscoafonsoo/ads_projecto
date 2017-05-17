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


    public static void insert(int vacancy_id, int employee_id, double score) throws PersistenceException {
        try (PreparedStatement statement = DataSource.INSTANCE.prepareGetGenKey(INSERT_TRANSFER_SQL)) {
            // set statements
            statement.setInt(1, vacancy_id);
            statement.setInt(2, employee_id);
            statement.setDouble(3, score);
            // execute
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException ("Error inserting a new transfer request!", e);
        }
    }
}
