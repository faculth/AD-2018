package db;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;




public class DbConnection 
{
	private Connection con;
	
	public void connect()
	{
		try
		{
            String userName = "A_Interactivas_01";
            String password = "A_Interactivas_01";
            String url = "jdbc:sqlserver://bd:1433";
            
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            con = DriverManager.getConnection (url, userName, password);
           
        }
        catch (Exception e)
        {
            System.err.println ("Cannot connect to database server");
        }
		
	}
	public ResultSet buscarPersona(String dni)
	{
		try
		{
			PreparedStatement s = con.prepareStatement("select * from A_Interactivas_01.dbo.clientes where dni = ?");
			s.setString(1,dni);
			ResultSet rs = s.executeQuery();
		    return rs;
		   			   
		}
		catch (Exception e)
		{
			System.out.println("Error Query: " + e.getMessage());
			return null;
		}
	}


}