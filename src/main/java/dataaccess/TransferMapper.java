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

    /**
     * Inserts a new transfer into the database
     *
     * @param vacancy_id the vacancy id
     * @param employee_id the employee id
     * @param score the employee's score
     * @return the id of the database entry
     * @throws PersistenceException if the insert query failed
     */
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

    private static final String UPDATE_TRANSFER_SQL =
            "UPDATE transfers SET is_processed = ? WHERE id = ?";

    /**
     * Updates a vacancy
     *
     * @param transfer_id the id of the transfer
     * @param is_processed if it was processed or not
     * @throws PersistenceException  if the insert query failed
     */
    public static void update(int transfer_id, boolean is_processed) throws PersistenceException {
        try (PreparedStatement statement = DataSource.INSTANCE.prepare(UPDATE_TRANSFER_SQL)) {
            statement.setBoolean(1, is_processed);
            statement.setInt(2, transfer_id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException("Internal error!", e);
        }
        cachedTransfer.remove(transfer_id);
    }

    private static final String GET_ALL_TRANSFERS_SQL = "SELECT * FROM transfers";

    /**
     * Creates a Transfer object from a result set retrieved from the database.
     *
     * @requires rs.next() was already executed
     * @param rs The result set with the information to create a transfer.
     * @return A new Transfer loaded from the database.
     * @throws PersistenceException In case there is an error accessing the database.
     */
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

    private static final String GET_TRANSFERS_BY_VACANCY_ID = "SELECT * FROM transfers WHERE vacancy_id = ?";

    /**
     * Gets a list of Transfers by vacancy
     *
     * @param vacancy_id the vacancy id
     * @return a List of Transfers
     * @throws PersistenceException In case there is an error accessing the database.
     */
    public static List<Transfer> getTransfersByVacancyId(int vacancy_id) throws PersistenceException {
        try (PreparedStatement statement = DataSource.INSTANCE.prepare(GET_TRANSFERS_BY_VACANCY_ID)) {
            statement.setInt(1, vacancy_id);
            try (ResultSet rs = statement.executeQuery()) {
                return loadSeveralTransfers(rs);
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to fetch all Transfers", e);
        }
    }

    /**
     * Creates a list of Employees
     *
     * @requires rs.next() was already executed
     * @param rs The result set with the information to create the list.
     * @return a List of Transfers
     * @throws PersistenceException In case there is an error accessing the database.
     * @throws SQLException
     */
    public static List<Transfer> loadSeveralTransfers(ResultSet rs) throws SQLException, PersistenceException {
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
}
