import org.junit.Ignore;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class JDBC_CRUDTest {
    private static final String ADRESS = "jdbc:mysql://localhost:3306/jdbctest";
    private static final String LOGIN="root";
    private static final String PASSWORD="Viktor1032003";
    private static final String TABLE_NAME="programmers";

    @Test
    public void testClass() {
        try {
            new JDBC_CRUD(ADRESS,LOGIN,PASSWORD,TABLE_NAME);
        }
        catch(SQLException e) {
            e.printStackTrace();
            fail("Failed to connect. SQLException.");
        }
    }


    @Test
    public void testCreate() {
        try {
            JDBC_CRUD db = new JDBC_CRUD(ADRESS,LOGIN,PASSWORD,TABLE_NAME);
            db.createProgrammer(1, "Vasya", "St.Petersburg", "Junior");
            db.createProgrammer(2, "Maksim", "St.Petersburg", "Middle");
            db.createProgrammer(3, "Vova", "Moscow", "Senior");
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Failed to create new programmer. SQLException");
        }
    }

    @Test
    public void testDelete() {
        try {
            JDBC_CRUD db = new JDBC_CRUD(ADRESS,LOGIN,PASSWORD,TABLE_NAME);
            db.deleteProgrammer(1);
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Failed to delete programmer. SQLException");
        }
    }

    @Test
    public void testRead() {
        ResultSet rs=null;
        try {
            JDBC_CRUD db = new JDBC_CRUD(ADRESS,LOGIN,PASSWORD,TABLE_NAME);
            rs=db.readTable();
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Failed to read database. SQLException");
        }
        assertNotNull(rs);
    }

    @Ignore
    @Test
    public void truncateTable() {
        try {
            JDBC_CRUD db = new JDBC_CRUD(ADRESS,LOGIN,PASSWORD,TABLE_NAME);
            db.clear();
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Failed to truncate table. SQLException");
        }
    }

    @Test
    public void testUpdate() {
        try {
            JDBC_CRUD db = new JDBC_CRUD(ADRESS,LOGIN,PASSWORD,TABLE_NAME);
            db.updateProgrammer(2, "Petya", "St.Petersburg", "Junior");
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Failed to update database. SQLException");
        }
    }
}

