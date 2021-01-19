/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arackiralama.dao;

import arackiralama.model.Arac;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;


import arackiralama.model.Kiralama;
import arackiralama.model.Musteri;


public class KiralamaDAO {
	private String jdbcURL ="jdbc:mysql://127.0.0.1:3306/arackiralama?useLegacyDatetimeCode=false&serverTimezone=UTC";
	private String jdbcUsername = "root";
	private String jdbcPassword = "123456";

	private static final String INSERT_KIRALAMA_SQL = "INSERT INTO kiralama" + "  (arac, musteri,baslangicTarihi,bitisTarihi) VALUES "
			+ " (?, ?,?,?);";

	private static final String SELECT_KIRALAMA_BY_ID = "select id,arac,musteri,baslangicTarihi,bitisTarihi from kiralama where id =?";
	private static final String SELECT_ALL_KIRALAMA = "select * from kiralama";
	private static final String DELETE_KIRALAMA_SQL = "delete from kiralama where id = ?;";
	private static final String UPDATE_KIRALAMA_SQL = "update kiralama set arac = ?,musteri= ?,baslangicTarihi= ?,bitisTarihi= ? where id = ?;";
	private static final String SELECT_KIRAARAC_SQL ="select * from arackiralama.kiralama where arac=? and bitisTarihi>=? ;";

	public KiralamaDAO() {
	}

	public Connection getConnection() throws SQLException, ClassNotFoundException {
            String dbDriver = "com.mysql.jdbc.Driver"; 
            Class.forName(dbDriver);
            Class.forName ( "com.mysql.jdbc.Driver" );
            return(Connection) DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        }

	public void insertKiralama(Kiralama kira) throws SQLException, ClassNotFoundException {
		System.out.println(INSERT_KIRALAMA_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();PreparedStatement preparedStatement = connection.prepareStatement(INSERT_KIRALAMA_SQL)) {
			preparedStatement.setInt(1, kira.getArac().getId());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Kiralama selectKiralama(int id) {
		Kiralama kiralama = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_KIRALAMA_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
			
				Integer aracId = rs.getInt("arac");
				Integer musteriId = rs.getInt("musteri");
				Date baslangicTarihi = rs.getDate("baslangicTarihi");
				Date bitisTarihi = rs.getDate("bitisTarihi");
				Arac arac = new AracDAO().selectArac(aracId);
				Musteri musteri = new MusteriDAO().selectMusteri(musteriId);
				kiralama = new Kiralama(id,arac, musteri,baslangicTarihi,bitisTarihi);
			}
		 } catch (SQLException e) {
            printSQLException(e);
        } catch (Exception e) {}
		return kiralama;
	}

	public List<Kiralama> selectAllKiralama() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Kiralama> kiralamaList = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_KIRALAMA);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				Integer aracId = rs.getInt("arac");
				Integer musteriId = rs.getInt("musteri");

				Date baslangicTarihi = rs.getDate("baslangicTarihi");
				Date bitisTarihi = rs.getDate("bitisTarihi");
				Arac arac = new AracDAO().selectArac(aracId);
				Musteri musteri = new MusteriDAO().selectMusteri(musteriId);
				kiralamaList.add(new Kiralama(id, arac, musteri,baslangicTarihi,bitisTarihi));
			}
		   } catch (SQLException e) {
            printSQLException(e);
        } catch (Exception e) {}
		return kiralamaList;
	}

	public boolean deleteKiralama(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_KIRALAMA_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		 } catch (Exception e) {
            rowDeleted = false;}
		return rowDeleted;
        }

	public boolean updateKiralama(Kiralama kiralama) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_KIRALAMA_SQL);) {
			statement.setInt(1, kiralama.getArac().getId());
			statement.setInt(2, kiralama.getMusteri().getId());
			statement.setDate(3, kiralama.getBaslangicTarihi());
			statement.setDate(4, kiralama.getBitisTarihi());
			statement.setInt(5, kiralama.getId());
			System.out.println(kiralama.baslangicTarihi);
			System.out.println(kiralama.bitisTarihi);
			System.out.println(kiralama.getArac().getId());
			System.out.println("*********///////****************");
			rowUpdated = statement.executeUpdate() > 0;
		
        } catch (Exception e) {
            rowUpdated = false;}
		return rowUpdated;
	}
	public List<Kiralama> selectAllKiraArac(int aId,Date bTarihi) {		
		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Kiralama> kiralamaList = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_KIRAARAC_SQL);) {
			preparedStatement.setInt(1, aId);
			preparedStatement.setDate(2, bTarihi);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				Integer aracId = rs.getInt("arac");
				Integer musteriId = rs.getInt("musteri");

				Date baslangicTarihi = rs.getDate("baslangicTarihi");
				Date bitisTarihi = rs.getDate("bitisTarihi");
				Arac arac = new AracDAO().selectArac(aracId);
				Musteri musteri = new MusteriDAO().selectMusteri(musteriId);
				kiralamaList.add(new Kiralama(id, arac, musteri,baslangicTarihi,bitisTarihi));
			}
		  } catch (SQLException e) {
            printSQLException(e);
        } catch (Exception e) {}
		return kiralamaList;
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

