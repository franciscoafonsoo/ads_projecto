package dataaccess;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import business.ApplicationException;
import dbutils.ResetTables;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import business.CatalogEmployee;
import business.Employee;
import business.SaleSys;

/**
 * Created by joaomiguelrodrigues on 17/05/17.
 */
public class EmployeeMapperTests {

    private static SaleSys app;

    private CatalogEmployee catalogEmp;
    private Employee emp;
    private int empId;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

        try {
            app = new SaleSys();
            app.start();

        } catch (Exception e) {
            fail("App didn't start or products could not be retrieved");
        }

    }

    @Test
    public void test_newEmplyeeID() {
        try {
            empId = EmployeeMapper.insert("Empr Um", "password", 919122432, new Date("01/01/2009"), 900.25, 545321456);
            assertEquals(1, empId);
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_getEmployeeByID() {
        try {
            Date d = new Date("01/01/2009");
            empId = EmployeeMapper.insert("Empr Um", "password", 919122432, d, 900.25, 545321456);
            emp = EmployeeMapper.getEmployeeById(empId);
            assertEquals("Empr Um", emp.getName());
            assertEquals("password", emp.getPwd());
            assertEquals(919122432, emp.getTlm());
            assertEquals(d, emp.getDate());
            assertEquals(900.25, emp.getSalary());
            assertEquals(545321456, emp.getVat());
        } catch (PersistenceException e) {
            e.printStackTrace();
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
