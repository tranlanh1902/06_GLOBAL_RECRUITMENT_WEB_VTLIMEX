package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.serenitybdd.core.Serenity;

public class CommonJDBC extends NamedParamStatement {
	static Connection connection;

	public static Connection getConnectionMySqlGolobalRecruitmentDomain() throws ClassNotFoundException, SQLException {
		String hostName = Constants.HOST_NAME;
		String dbNameOrSid = Constants.DB_NAME_OR_SID;
		String userName = Constants.USER_NAME;
		String password = Constants.PASS_WORD;

		Class.forName("com.mysql.jdbc.Driver");
		String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbNameOrSid + "?useUnicode=yes&characterEncoding=UTF-8";
		connection = DriverManager.getConnection(connectionURL, userName, password);
		System.out.println(connection);
		return connection;
	}

//	public static int getRowCountDB(Connection connection, String query, Map<String, Object> mapNameValueParameter) throws Exception {
//		PreparedStatement preparedStatement = getPreparedStatementName(connection, query, mapNameValueParameter);
//		ResultSet resultSet = preparedStatement.executeQuery();
//		resultSet.last();
//		int rowCount = resultSet.getRow();
//		resultSet.beforeFirst();
//		Serenity.recordReportData().withTitle("Database evidence").andContents(String.valueOf(rowCount));
//		return rowCount;
//	}

	public static int getRowCountDB(Connection connection, String query, Map<String, Object> mapNameValueParameter) throws Exception {
		ResultSet resultSet = null;
		int rowCount = 0;

		PreparedStatement preparedStatement = getPreparedStatementName(connection, query, mapNameValueParameter);

		resultSet = preparedStatement.executeQuery();

		try {
			resultSet.last();
			rowCount = resultSet.getRow();
			resultSet.beforeFirst();
		} catch (Exception e) {
			return 0;
		} finally {

			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (SQLException se1) {
				se1.printStackTrace();
			}
		}
		Serenity.recordReportData().withTitle("Database evidence").andContents(String.valueOf(rowCount));

		return rowCount;
	}

	public static PreparedStatement getPreparedStatementName(Connection connection, String query, Map<String, Object> mapNameValueParameter) throws SQLException {
		PreparedStatement preparedStatement = getPreparedStatementName(connection, query);

		Set<String> set = mapNameValueParameter.keySet();
		for (String key : set) {
			Object value = mapNameValueParameter.get(key);
			if (value instanceof String) {
				setString(key, (String) value);
			} else if (value instanceof Integer) {
				setInt(key, (Integer) value);
			} else if (value instanceof Long) {
				setLong(key, (Long) value);
			} else if (value instanceof Float) {
				setFloat(key, (Float) value);
			} else if (value instanceof Double) {
				setDouble(key, (Double) value);
			} else if (value instanceof Timestamp) {
				setTimestamp(key, (Timestamp) value);
			} else if (value instanceof Date) {
				setDate(key, (Date) value);
			}
		}

		return preparedStatement;

	}

	public static String castColumnValueDatapool(String columnTypeDB, String columnValueDatapool) {
		String datapoolValueString = null;

		if (columnTypeDB.equalsIgnoreCase("float")) {
			datapoolValueString = String.valueOf(Float.parseFloat(columnValueDatapool));

		} else if (columnTypeDB.equalsIgnoreCase("double")) {
			datapoolValueString = String.valueOf(Double.parseDouble(columnValueDatapool));
		} else {
			datapoolValueString = String.valueOf(columnValueDatapool);
		}
		return datapoolValueString;
	}

	public static List<String> listColumnValueDB(Connection connection, String query, String columnNameDB, Map<String, Object> mapNameValueParameter) throws Exception {
		List<String> lstcolumnValueDB = new ArrayList<String>();

		PreparedStatement preparedStatement = getPreparedStatementName(connection, query, mapNameValueParameter);

		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			String columnValueDB = resultSet.getString(columnNameDB);
			lstcolumnValueDB.add(columnValueDB);
		}
		Serenity.recordReportData().withTitle("Database evidence").andContents(String.valueOf(lstcolumnValueDB));
		return lstcolumnValueDB;
	}

	// return 1 value
	public static String getColumnValueDB(Connection connection, String query, String columnNameDB, Map<String, Object> mapNameValueParameter) throws Exception {
		String columnValueDB = null;
		PreparedStatement preparedStatement = getPreparedStatementName(connection, query, mapNameValueParameter);

		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			columnValueDB = resultSet.getString(columnNameDB);
		}
		Serenity.recordReportData().withTitle("Database evidence").andContents(String.valueOf(columnValueDB));
		return columnValueDB;
	}

}
