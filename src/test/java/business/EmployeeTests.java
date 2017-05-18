package business;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

import dataaccess.PersistenceException;
import dbutils.ResetTables;
import org.junit.*;

import dataaccess.EmployeeMapper;
import business.CatalogEmployee;

/**
 * Created by joaomiguelrodrigues on 17/05/17.
 */
public class EmployeeTests {

    private Employee emp;
    private CatalogEmployee employeeCatalog;

    private static SaleSys app;

    @BeforeClass   // run once
    public static void setUpBeforeClass() {

        try {
            app = new SaleSys();
            app.start();

        } catch (Exception e) {
            fail("App didn't start");
        }
    }

    @Test
    public void test_newEmployee() {
        try {
            emp = employeeCatalog.newEmployee("Empr Um", "password", "01/01/2009", 919122432, 545321456);
            assertEquals("Empr Um", emp.getName());
            assertEquals("password", emp.getPwd());
            assertEquals(919122432, emp.getTlm());
            assertEquals(new Date("01/01/2009"), emp.getDate());
            assertEquals(900.25, emp.getSalary());
            assertEquals(545321456, emp.getVat());
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_newEmployeeException() {
        try {
            employeeCatalog.newEmployee("Empr Um", "password", "01/01/2009", 919122432, 545321456);
            employeeCatalog.newEmployee("Empr dois", "password2", "02/02/2009", 919122422, 545321456);
            fail("Expecting ApplicationException");
        } catch (ApplicationException e) {
            assertEquals("Employee already exists", e.getMessage());
        }
    }

    @After
    public void finish() {
        try {
            ResetTables res = new ResetTables();
            res.resetADSDerbyDB();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PersistenceException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void finishAfterClass() {

        try {
            app.stop();
        } catch (Exception e) {
            fail("App unable to finish");
        }
    }
}