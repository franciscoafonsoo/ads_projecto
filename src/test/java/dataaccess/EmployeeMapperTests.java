package dataaccess;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.AfterClass;
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
            app = new SaleSys(); // assume que a base de dados já está criada
            app.start();
        } catch (Exception e) {
            e.printStackTrace();
            fail("App didn't start or products could not be retrieved");
        }

    }

    @Test
    public void test_newEmplyeeID() {
        try {
            empId = EmployeeMapper.insert("Empr Um", "password", 919122432, new Date("01/01/2009"), 900.25, 545321456);
            assertEquals(2, empId);
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_getEmployeeByID() {
        try {
            Date d = new Date("01/01/2009");
            empId = EmployeeMapper.insert("Empr Um", "password", 919122432, d, 900.25, 545321457);
            emp = EmployeeMapper.getEmployeeById(empId);
            assertEquals("Empr Um", emp.getName());
            assertEquals("password", emp.getPwd());
            assertEquals(919122432, emp.getTlm());
            assertEquals(d, emp.getBirth());
            assertEquals(900.0, emp.getSalary(), 0.0);
            assertEquals(545321457, emp.getVat());
        } catch (PersistenceException e) {
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
