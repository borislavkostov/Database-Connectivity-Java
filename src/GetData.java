import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;

public class GetData 
{
	public static void main(String args[])
	{
		NumberFormat currency=NumberFormat.getCurrencyInstance();
		final String DRIVER="org.apache.derby.jdbc.EmbeddedDriver";
		final String CONNECTION="jdbc:derby:AccountDatabase";
		try
		{
			Class.forName(DRIVER).newInstance();
		}catch (InstantiationException e)
		{
			e.printStackTrace();
		}catch (IllegalAccessException e){
			e.printStackTrace();
		}catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		try (Connection connection=DriverManager.getConnection(CONNECTION);
				Statement statement=connection.createStatement();
				ResultSet resultset=statement.executeQuery("select * from ACCOUNTS"))
				{	
				while(resultset.next())
				{
					System.out.print(resultset.getString("NAME"));
					System.out.print(" , ");
					System.out.print(resultset.getString("ADDRESS"));
					System.out.print(" ");
					System.out.println(currency.format(resultset.getFloat("BALANCE")));
				}
				}catch (SQLException e)
				{
					e.printStackTrace();
				}	
	}
}
