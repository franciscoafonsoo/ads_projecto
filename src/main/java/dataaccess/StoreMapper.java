package dataaccess;

import business.Employee;
import business.Store;

import javax.xml.crypto.Data;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StoreMapper {

    static Map<Integer, Store> cachedStore;

    static {
        cachedStore = new HashMap<Integer, Store>();
    }

    /////////////////////////////////////////////////////////////////////////
    // TODO: add manager later
    // SQL statement: selects a store by its id
    private static final String GET_STORE_SQL =
            "SELECT id, address, district, tlm, fax, email FROM store WHERE id = ?";

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

        List<Employee> employeeList = EmployeeMapper.getAllEmployeesByStoreId(store_id);

        try (PreparedStatement statement = DataSource.INSTANCE.prepare(GET_STORE_SQL)) {
            // set statement arguments
            statement.setInt(1, store_id);
            // execute SQL
            try (ResultSet rs = statement.executeQuery()) {
                rs.next();
                Store store = loadStore(rs);
                cachedStore.put(store.getId(), store);
                return store;
            }
        } catch (SQLException e) {
            throw new PersistenceException("Internal error getting employee " + store_id, e);
        }
    }

    private static Store loadStore(ResultSet rs) throws PersistenceException {
        Store store;
        try {
            store = new Store(
                    rs.getInt("id"),
                    rs.getString("address"),
                    rs.getString("district"),
                    rs.getInt("tlm"),
                    rs.getInt("fax"),
                    rs.getString("email")
            );
        } catch (SQLException e) {
            throw new RecordNotFoundException ("Employee does not exist	", e);
        }
        return store;
    }


    public static void addEmployeeToStore(int id, int id1, int sectionId) {
    }
}
