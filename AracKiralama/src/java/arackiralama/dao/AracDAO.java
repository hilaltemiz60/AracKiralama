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
import java.util.ArrayList;
import java.util.List;

import arackiralama.model.Arac;

public class AracDAO {
	private String jdbcURL ="jdbc:mysql://127.0.0.1:3306/arackiralama?useLegacyDatetimeCode=false&serverTimezone=UTC";
	private String jdbcUsername = "root";
	private String jdbcPassword = "123456";

	private static final String INSERT_ARAC_SQL = "INSERT INTO arac" + "  (plakaNo, model,marka,renk) VALUES "
			+ " (?, ?,?,?);";

	private static final String SELECT_ARAC_BY_ID = "select id,plakaNo,model,marka,renk from arac where id =?";
	private static final String SELECT_ALL_ARAC = "select * from arac";
	private static final String DELETE_ARAC_SQL = "delete from arac where id = ?;";
	private static final String UPDATE_ARAC_SQL = "update arac set plakaNo = ?,model= ?,marka= ?,renk= ? where id = ?;";
	public AracDAO() {
	}

	public Connection getConnection() throws SQLException, ClassNotFoundException {
            String dbDriver = "com.mysql.jdbc.Driver"; 
            Class.forName(dbDriver);
            Class.forName ( "com.mysql.jdbc.Driver" );
            return(Connection) DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        }

	public void insertArac(Arac arac) throws SQLException, ClassNotFoundException {
		System.out.println(INSERT_ARAC_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ARAC_SQL)) {
			preparedStatement.setString(1, arac.getPlakaNo());
			preparedStatement.setString(2, arac.getModel());
			preparedStatement.setString(3, arac.getMarka());
			preparedStatement.setString(4, arac.getRenk());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Arac selectArac(int id) {
		Arac arac = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ARAC_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String plakaNo = rs.getString("plakaNo");
				String model = rs.getString("model");
				String marka = rs.getString("marka");
				String renk = rs.getString("renk");

				arac = new Arac(id, plakaNo, model,marka,renk);
			}
			} catch (SQLException e) {
			printSQLException(e);
		}catch (Exception e) {}
		return arac;
	}

	public List<Arac> selectAllArac() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Arac> aracList = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ARAC);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String plakaNo = rs.getString("plakaNo");
				String model = rs.getString("model");

				String marka = rs.getString("marka");
				String renk = rs.getString("renk");
				aracList.add(new Arac(id, plakaNo, model,marka,renk));
			}
			} catch (SQLException e) {
			printSQLException(e);
		}catch (Exception e) {}
		return aracList;
	}

	public boolean deleteArac(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_ARAC_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
                }
                catch (Exception e) {
            rowDeleted = false;}
		return rowDeleted;
	}

	public boolean updateArac(Arac arac) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_ARAC_SQL);) {
			statement.setString(1, arac.getPlakaNo());
			statement.setString(2, arac.getModel());
			statement.setString(3, arac.getMarka());
			statement.setString(4, arac.getRenk());
			statement.setInt(5, arac.getId());

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

