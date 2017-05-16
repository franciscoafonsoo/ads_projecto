package dataaccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;

import business.Employee;
import business.Sale;
import business.SaleProduct;


public class EmployeeMapper {

    static Map<Integer, Employee> cachedEmployee;

    static {
        cachedEmployee = new HashMap<Integer, Employee>();
    }

    // SQL statement: inserts a new employee
    private static final String INSERT_EMPLOYEE_SQL =
            "INSERT INTO employee (id, name, password, birth, tlm, entry_date, salary, vat, " +
                    "score_one, score_two, score_three, filed)" +
                    " VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, 0, 0, 0, ?)";

    /**
     * Inserts a new employee into the database
     *
     * @param name      Name of the employee
     * @param pwd       Password for access
     * @param tlm       Employee's cellphone
     * @param birth     Date of birth
     * @param salary    Employee's salary
     * @param vat       Employee's VAT Number
     * @return          The employee's id
     *
     */

    public static int insert(String name, String pwd, int tlm, java.util.Date birth, double salary
    , int vat) throws PersistenceException {
        try (PreparedStatement statement = DataSource.INSTANCE.prepareGetGenKey(INSERT_EMPLOYEE_SQL)) {

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

    // SQL statement: updates employee store and section
    private static final String UPDATE_EMPLOYEE_SQL =
            "UPDATE employee SET store_id = ?, section_id = ? WHERE id = ?";

    /**
     * TODO: javadoc
     * Updates the sale's data in the database
     *
     * @param sale_id The sale id to update
     * @param total the new sale total
     * @param status is the sale open or closed?
     * @throws PersistenceException If an error occurs during the operation
     */
    public static void update(int employee_id, int store_id, int section_id) throws PersistenceException {
        try (PreparedStatement statement = DataSource.INSTANCE.prepare(UPDATE_EMPLOYEE_SQL)) {
            statement.setInt(1, store_id);
            statement.setInt(2, section_id);
            statement.setInt(3, employee_id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException ("Internal error!", e);
        }

        cachedEmployee.remove(employee_id);  // sale was changed, remove from cache
    }

    /////////////////////////////////////////////////////////////////////////
    // (id, name, password, birth, tlm, entry_date, salary, vat, "score_one, score_two, score_three, filed, store_id, section_id)
    // SQL statement: selects a sale by its id
    private static final String GET_EMPLOYEE_SQL =

            "SELECT id, name, password, birth, tlm, entry_date, salary, " +
                    "vat, score_one, score_two, score_three, filed, store_id, " +
                    "section_id FROM employee WHERE id = ?";

    /**
     * TODO: javadoc
     * Gets a sale by its id
     *
     * @param sale_id The sale id to search for
     * @return The new object that represents an in-memory sale
     * @throws PersistenceException In case there is an error accessing the database.
     */
    public static Employee getEmployeeById(int employee_id) throws PersistenceException {

        if (cachedEmployee.containsKey(employee_id))
            return cachedEmployee.get(employee_id);

        try (PreparedStatement statement = DataSource.INSTANCE.prepare(GET_EMPLOYEE_SQL)) {
            // set statement arguments
            statement.setInt(1, employee_id);
            // execute SQL
            try (ResultSet rs = statement.executeQuery()) {
                rs.next();
                Employee employee = loadEmployee(rs);
                cachedEmployee.put(employee.getId(), employee);
                return employee;
            }
        } catch (SQLException e) {
            throw new PersistenceException("Internal error getting employee " + employee_id, e);
        }
    }

    private static final String GET_EMPLOYEE_BY_VAT_SQL =

            "SELECT id, name, password, birth, tlm, entry_date, salary, " +
                    "vat, score_one, score_two, score_three, filed, store_id, " +
                    "section_id FROM employee WHERE vat = ?";

    public static boolean getEmployeeByVAT(int vat) throws PersistenceException {

        try (PreparedStatement statement = DataSource.INSTANCE.prepare(GET_EMPLOYEE_BY_VAT_SQL)) {
            statement.setInt(1, vat);
            try (ResultSet rs = statement.executeQuery()) {
                if(!rs.next())
                    return false;
                else {
                    Employee employee = loadEmployee(rs);
                    cachedEmployee.put(employee.getId(), employee);
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new PersistenceException("Internal error getting employee " + vat, e);
        }
    }
    /////////////////////////////////////////////////////////////////////////
    // SQL statement: get all sales
    private static final String GET_ALL_EMPLOYEES_SQL = "SELECT * FROM employee";

    /**
     * TODO: javadoc
     *
     * Retrieve all sales kept on database
     * @return A list with all the sales
     * @throws PersistenceException
     */
    public static List<Employee> getAllEmployees() throws PersistenceException {

        try (PreparedStatement statement = DataSource.INSTANCE.prepare(GET_ALL_EMPLOYEES_SQL)) {
            try (ResultSet rs = statement.executeQuery()) {

                List<Employee> employees = new LinkedList<Employee>();
                while(rs.next()) { // for each sale
                    int employee_id = rs.getInt("id");
                    if (cachedEmployee.containsKey(employee_id))   // check if it is cached
                        employees.add(cachedEmployee.get(employee_id));
                    else {
                        Employee employee = loadEmployee(rs);           // if not, create a new sale object
                        employees.add(employee);                    //  insert it to result list,
                        cachedEmployee.put(employee_id, employee);     //  and cache it
                    }
                }
                return employees;
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to fetch all employee", e);
        }
    }

    /**
     * Creates a sale object from a result set retrieved from the database.
     *
     * @requires rs.next() was already executed
     * @param rs The result set with the information to create the sale.
     * @return A new sale loaded from the database.
     * @throws PersistenceException
     */
     private static Employee loadEmployee(ResultSet rs) throws PersistenceException {
        Sale sale;
        Employee employee;
        try {
            // String name, String pwd, float tlm, java.util.Date birth, float salary, int vat, int store, int section
            employee = new Employee(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("password"),
                    rs.getInt("tlm"),
                    rs.getDate("entry_date"),
                    rs.getFloat("salary"),
                    rs.getInt("vat"),
                    rs.getInt("store_id"),
                    rs.getInt("section_id")
                    );

            /*
             * TODO: adicionar empregado a loja
             */

            /*
            List<SaleProduct> saleProducts = SaleProductMapper.getSaleProducts(rs.getInt("id"));
            for(SaleProduct sp : saleProducts)
                sale.addProductToSale(sp.getProduct(), sp.getQty());

            if (rs.getString("status").equals(Sale.CLOSED))
                sale.close();
                */

        } catch (SQLException e) {
            throw new RecordNotFoundException ("Employee does not exist	", e);
        }
        return employee;
    }
}