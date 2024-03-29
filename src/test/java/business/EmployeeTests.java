package business;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.*;

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
            app = new SaleSys(); // assume que a base de dados já está criada
            app.start();

        } catch (Exception e) {
            fail("App didn't start");
        }
    }

    @Before   // run before each test
    public void setup() {
        emp = null;
        employeeCatalog = new CatalogEmployee();
    }

    @Test
    public void test_newEmployee() {
        try {
            emp = employeeCatalog.newEmployee("Empr Um", "password", "01/01/2009", 919122432, 545321456);
            assertEquals("Empr Um", emp.getName());
            assertEquals("password", emp.getPwd());
            assertEquals(919122432, emp.getTlm());
            assertEquals(new Date("01/01/2009"), emp.getBirth());
            assertEquals(900.0, emp.getSalary(), 0.000);
            assertEquals(545321456, emp.getVat());
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_newEmployeeException() {
        try {
            employeeCatalog.newEmployee("Empr dois", "password2", "02/02/2009", 919122422, 545321456);
            fail("Expecting ApplicationException");
        } catch (ApplicationException e) {
            assertEquals("Employee already exists", e.getMessage());
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