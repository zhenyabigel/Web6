import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC_CRUD {
    private final Connection connection;
    private final String tableName;

    public JDBC_CRUD(String adress, String login, String password, String tableName) throws SQLException {
        connection= DriverManager.getConnection(adress,login,password);
        this.tableName=tableName;
    }

    public void createProgrammer(int id, String name, String location, String qualification) throws SQLException {
        String query = "INSERT INTO " + tableName + " (Programmer_id, Name, Location, Qualification) VALUES (?,?,?,?);";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, id);
        pstmt.setString(2, name);
        pstmt.setString(3, location);
        pstmt.setString(4, qualification);
        pstmt.executeUpdate();
    }
    public void deleteProgrammer(int id) throws SQLException {
        String query = "DELETE FROM "+tableName+" WHERE Programmer_id=?;";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
    }
    public ResultSet readTable() throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM "+tableName);
        int count=rs.getMetaData().getColumnCount();
        while(rs.next()) {
            for(int i=0; i<count; i++) {
                System.out.print(rs.getString(i+1)+" ");
            }
            System.out.print("\n");
        }
        return rs;
    }
    public void updateProgrammer(int id, String name, String location, String qualification) throws SQLException {
        String query = "UPDATE "+tableName+" SET Name=?, Location=?, Qualification=? WHERE Programmer_id=?;";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, name);
        pstmt.setString(2, location);
        pstmt.setString(3, qualification);
        pstmt.setInt(4, id);
        pstmt.executeUpdate();
    }

    /**
     * Truncates table.
     * @throws SQLException
     */
    public void clear() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("TRUNCATE TABLE "+tableName);
    }

}

