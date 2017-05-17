package dataaccess;


import business.Transfer;
import com.sun.org.apache.regexp.internal.RE;
import com.sun.xml.internal.ws.api.ha.StickyFeature;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
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

    private static final String GET_ALL_TRANSFERS_SQL = "SELECT * FROM transfers";

    public static List<Transfer> getAllTransfers() throws PersistenceException {

        try (PreparedStatement statement = DataSource.INSTANCE.prepare(GET_ALL_TRANSFERS_SQL)) {
            try (ResultSet rs = statement.executeQuery()) {
                List<Transfer> transfers = new LinkedList<Transfer>();

                while(rs.next()) {
                    int transfer_id = rs.getInt("id");
                    if (cachedTransfer.containsKey(transfer_id))
                        transfers.add(cachedTransfer.get(transfer_id));
                    else {
                        Transfer transfer = loadTransfer(rs);
                        transfers.add(transfer);
                        cachedTransfer.put(transfer_id, transfer);
                    }
                }
                return transfers;
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to fetch all Transfers", e);
        }
    }

    private static Transfer loadTransfer(ResultSet rs) throws PersistenceException {
        Transfer transfer;
        try {
            transfer = new Transfer(
                    rs.getInt("id"),
                    rs.getInt("vacancy_id"),
                    rs.getInt("employee_id"),
                    rs.getDouble("score"),
                    rs.getDate("entry_date"),
                    rs.getBoolean("is_processed"));
        } catch (SQLException e) {
            throw new RecordNotFoundException("Transfer does not exist", e);
        }
        return transfer;
    }
}
