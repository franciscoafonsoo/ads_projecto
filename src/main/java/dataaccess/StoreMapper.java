package dataaccess;

import business.Store;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class StoreMapper {

    static Map<Integer, Store> cachedStore;

    static {
        cachedStore = new HashMap<Integer, Store>();
    }

    /////////////////////////////////////////////////////////////////////////
    // (id, name, password, birth, tlm, entry_date, salary, vat, "score_one, score_two, score_three, filed, store_id, section_id)
    // SQL statement: selects a sale by its id
    private static final String GET_STORE_SQL =

            "SELECT id, name, password, birth, tlm, entry_date, salary, " +
                    "vat, score_one, score_two, score_three, filed, store_id, " +
                    "section_id FROM employee WHERE id = ?";

    /**
     * TODO: javadoc
     * Gets a sale by its id
     *
     * @param store_id The sale id to search for
     * @return The new object that represents an in-memory sale
     * @throws PersistenceException In case there is an error accessing the database.
     */
    public static Store getStoreById(int store_id) throws PersistenceException {

        if (cachedStore.containsKey(store_id))
            return cachedStore.get(store_id);

        try (PreparedStatement statement = DataSource.INSTANCE.prepare(GET_STORE_SQL)) {
            // set statement arguments
            statement.setInt(1, store_id);
            // execute SQL
            try (ResultSet rs = statement.executeQuery()) {
                rs.next();
                Store store = loadStore(rs);
                // TODO: descomentar
                // cachedStore.put(store.getId(), store);
                return store;
            }
        } catch (SQLException e) {
            throw new PersistenceException("Internal error getting employee " + store_id, e);
        }
    }

    private static Store loadStore(ResultSet rs) throws PersistenceException {
        return null;
    }
}
