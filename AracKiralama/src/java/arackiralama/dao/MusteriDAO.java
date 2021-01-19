/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arackiralama.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import arackiralama.model.Arac;
import arackiralama.model.Musteri;

public class MusteriDAO {
		private String jdbcURL ="jdbc:mysql://127.0.0.1:3306/arackiralama?useLegacyDatetimeCode=false&serverTimezone=UTC";
	private String jdbcUsername = "root";
	private String jdbcPassword = "123456";

	private static final String INSERT_MUSTERI_SQL = "INSERT INTO musteri" + "  (ad, soyad,telefon,adres) VALUES "
			+ " (?, ?,?,?);";

	private static final String SELECT_MUSTERI_BY_ID = "select id,ad,soyad,telefon,adres from musteri where id =?";
	private static final String SELECT_ALL_MUSTERI = "select * from musteri";
	private static final String DELETE_MUSTERI_SQL = "delete from musteri where id = ?;";
	private static final String UPDATE_MUSTERI_SQL = "update musteri set ad = ?,soyad= ?,telefon= ?,adres= ? where id = ?;";

	public MusteriDAO() {
	}

	public Connection getConnection() throws SQLException, ClassNotFoundException {
            String dbDriver = "com.mysql.jdbc.Driver"; 
            Class.forName(dbDriver);
            Class.forName ( "com.mysql.jdbc.Driver" );
            return(Connection) DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        }

	public void insertMusteri(Musteri musteri) throws SQLException, ClassNotFoundException {
		System.out.println(INSERT_MUSTERI_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MUSTERI_SQL)) {
			preparedStatement.setString(1, musteri.getAd());
			preparedStatement.setString(2, musteri.getSoyad());
			preparedStatement.setString(3, musteri.getTelefon());
			preparedStatement.setString(4, musteri.getAdres());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Musteri selectMusteri(int id) {
		Musteri musteri = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MUSTERI_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String ad = rs.getString("ad");
				String soyad = rs.getString("soyad");
				String telefon = rs.getString("telefon");
				String adres = rs.getString("adres");

				musteri = new Musteri(id, ad, soyad,telefon,adres);
			}
			} catch (SQLException e) {
			printSQLException(e);
		}catch (Exception e) {}
		return musteri;
	}

	public List<Musteri> selectAllMusteri() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Musteri> musteriList = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MUSTERI);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String ad = rs.getString("ad");
				String soyad = rs.getString("soyad");

				String telefon = rs.getString("telefon");
				String adres = rs.getString("adres");
				musteriList.add(new Musteri(id, ad, soyad,telefon,adres));
			}
			} catch (SQLException e) {
			printSQLException(e);
		}catch (Exception e) {}
		return musteriList;
	}

	public boolean deleteMusteri(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_MUSTERI_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		catch (Exception e) {
            rowDeleted = false;}
		return rowDeleted;
	}

	public boolean updateMusteri(Musteri musteri) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_MUSTERI_SQL);) {
			statement.setString(1, musteri.getAd());
			statement.setString(2, musteri.getSoyad());
			statement.setString(3, musteri.getTelefon());
			statement.setString(4, musteri.getAdres());
			statement.setInt(5, musteri.getId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		catch (Exception e) {
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

