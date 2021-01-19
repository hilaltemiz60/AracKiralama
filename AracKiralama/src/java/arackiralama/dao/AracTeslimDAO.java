/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arackiralama.dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import arackiralama.model.Arac;
import arackiralama.model.AracTeslim;
import arackiralama.model.Kiralama;
import arackiralama.model.Musteri;
import arackiralama.model.User;

public class AracTeslimDAO {
		private String jdbcURL ="jdbc:mysql://127.0.0.1:3306/arackiralama?useLegacyDatetimeCode=false&serverTimezone=UTC";
	private String jdbcUsername = "root";
	private String jdbcPassword = "123456";

	private static final String INSERT_ARACTESLIM_SQL = "INSERT INTO aracTeslim" + "  (arac, user,teslimTarihi) VALUES "
			+ " (?, ?,?);";

	private static final String SELECT_ARACTESLIM_BY_ID = "select id,arac,user,teslimTarihi from aracTeslim where id =?";
	private static final String SELECT_ALL_ARACTESLIM = "select * from aracTeslim";
	private static final String DELETE_ARACTESLIM_SQL = "delete from aracTeslim where id = ?;";
	private static final String UPDATE_ARACTESLIM_SQL = "update aracTeslim set arac = ?,user= ?,teslimTarihi= ? where id = ?;";

	public AracTeslimDAO() {
	}
        public Connection getConnection() throws SQLException, ClassNotFoundException {
            String dbDriver = "com.mysql.jdbc.Driver"; 
            Class.forName(dbDriver);
            Class.forName ( "com.mysql.jdbc.Driver" );
            return(Connection) DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        }

	public void insertAracTeslim(AracTeslim aracTeslim) throws SQLException, ClassNotFoundException {
		System.out.println(INSERT_ARACTESLIM_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ARACTESLIM_SQL)) {
			preparedStatement.setInt(1, aracTeslim.getArac().getId());
			preparedStatement.setInt(2, aracTeslim.getUser().getId());
			preparedStatement.setDate(3,java.sql.Date.valueOf(java.time.LocalDate.now()));
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}


	public AracTeslim selectAracTeslim(int id) {
		AracTeslim aracTeslim = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ARACTESLIM_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
			
				Integer aracId = rs.getInt("arac");
				Integer userId = rs.getInt("user");
				Date teslimTarihi = rs.getDate("teslimTarihi");
		
				Arac arac = new AracDAO().selectArac(aracId);
				User user = new UserDAO().selectUser(userId);
				aracTeslim = new AracTeslim(id,arac, user,teslimTarihi);
			}
			} catch (SQLException e) {
			printSQLException(e);
		}catch (Exception e) {}
		return aracTeslim;
	}

	public List<AracTeslim> selectAllAracTeslim() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<AracTeslim> aracTeslimList = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ARACTESLIM);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				Integer aracId = rs.getInt("arac");
				Integer userId = rs.getInt("user");
				Date teslimTarihi = rs.getDate("teslimTarihi");
				Arac arac = new AracDAO().selectArac(aracId);
				User user = new UserDAO().selectUser(userId);
				aracTeslimList.add(new AracTeslim(id, arac, user,teslimTarihi));
			}
			} catch (SQLException e) {
			printSQLException(e);
		}catch (Exception e) {}
		return aracTeslimList;
	}

	public boolean deleteAracTeslim(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_ARACTESLIM_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}catch (Exception e) {
            rowDeleted = false;}
		return rowDeleted;
	}

	public boolean updateAracTeslim(AracTeslim aracTeslim) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_ARACTESLIM_SQL);) {
			statement.setInt(1, aracTeslim.getArac().getId());
			statement.setInt(2, aracTeslim.getUser().getId());
			statement.setDate(3, aracTeslim.getTeslimTarihi());
			statement.setInt(4, aracTeslim.getId());
			rowUpdated = statement.executeUpdate() > 0;
		}catch (Exception e) {
            rowUpdated = false;}
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
}

