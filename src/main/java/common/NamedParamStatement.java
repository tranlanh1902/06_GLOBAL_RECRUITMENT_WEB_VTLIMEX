package common;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class NamedParamStatement {

	private static PreparedStatement preparedStatement;

	@SuppressWarnings("rawtypes")
	private static Map indexMap;

	@SuppressWarnings("rawtypes")
	public static PreparedStatement getPreparedStatementName(Connection connection, String query) throws SQLException {
		indexMap = new HashMap();
		String parsedQuery = parse(query, indexMap);
		preparedStatement = connection.prepareStatement(parsedQuery);
		return preparedStatement;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	static final String parse(String query, Map paramMap) {
		int length = query.length();
		StringBuilder parsedQuery = new StringBuilder(length);
		boolean inSingleQuote = false;
		boolean inDoubleQuote = false;
		int index = 1;

		for (int i = 0; i < length; i++) {
			char c = query.charAt(i);
			if (inSingleQuote) {
				if (c == '\'') {
					inSingleQuote = false;
				}
			} else if (inDoubleQuote) {
				if (c == '"') {
					inDoubleQuote = false;
				}
			} else {
				if (c == '\'') {
					inSingleQuote = true;
				} else if (c == '"') {
					inDoubleQuote = true;
				} else if (c == ':' && i + 1 < length && Character.isJavaIdentifierStart(query.charAt(i + 1))) {
					int j = i + 2;
					while (j < length && Character.isJavaIdentifierPart(query.charAt(j))) {
						j++;
					}
					String name = query.substring(i + 1, j);
					c = '?'; // replace the parameter with a question mark
					i += name.length(); // skip past the end if the parameter

					List indexList = (List) paramMap.get(name);
					if (indexList == null) {
						indexList = new LinkedList();
						paramMap.put(name, indexList);
					}
					indexList.add(Integer.valueOf(index));

					index++;
				}
			}
			parsedQuery.append(c);
		}

		// replace the lists of Integer objects with arrays of ints
		for (Iterator itr = paramMap.entrySet().iterator(); itr.hasNext();) {
			Map.Entry entry = (Map.Entry) itr.next();
			List list = (List) entry.getValue();
			int[] indexes = new int[list.size()];
			int i = 0;
			for (Iterator itr2 = list.iterator(); itr2.hasNext();) {
				Integer x = (Integer) itr2.next();
				indexes[i++] = x.intValue();
			}
			entry.setValue(indexes);
		}

		return parsedQuery.toString();
	}

	@SuppressWarnings("rawtypes")
	public static Set getParameterNames() {
		return indexMap.keySet();
	}

	private static int[] getIndexes(String name) {
		int[] indexes = (int[]) indexMap.get(name);
		if (indexes == null) {
			throw new IllegalArgumentException("Parameter not found: " + name);
		}
		return indexes;
	}

	public static void setObject(String name, Object value) throws SQLException {
		int[] indexes = getIndexes(name);
		for (int i = 0; i < indexes.length; i++) {
			preparedStatement.setObject(indexes[i], value);
		}
	}

	public static void setString(String name, String value) throws SQLException {
		int[] indexes = getIndexes(name);
		for (int i = 0; i < indexes.length; i++) {
			preparedStatement.setString(indexes[i], value);
		}
	}

	public static void setInt(String name, int value) throws SQLException {
		int[] indexes = getIndexes(name);
		for (int i = 0; i < indexes.length; i++) {
			preparedStatement.setInt(indexes[i], value);
		}
	}

	public static void setLong(String name, long value) throws SQLException {
		int[] indexes = getIndexes(name);
		for (int i = 0; i < indexes.length; i++) {
			preparedStatement.setLong(indexes[i], value);
		}
	}

	public static void setFloat(String name, float value) throws SQLException {
		int[] indexes = getIndexes(name);
		for (int i = 0; i < indexes.length; i++) {
			preparedStatement.setFloat(indexes[i], value);
		}
	}

	public static void setDouble(String name, double value) throws SQLException {
		int[] indexes = getIndexes(name);
		for (int i = 0; i < indexes.length; i++) {
			preparedStatement.setDouble(indexes[i], value);
		}
	}

	public static void setBigDecimal(String name, BigDecimal value) throws SQLException {
		int[] indexes = getIndexes(name);
		for (int i = 0; i < indexes.length; i++) {
			preparedStatement.setBigDecimal(indexes[i], value);
		}
	}

	public static void setBytes(String name, byte[] data) throws SQLException {
		int[] indexes = getIndexes(name);
		for (int i = 0; i < indexes.length; i++) {
			preparedStatement.setBytes(indexes[i], data);
		}
	}

	public static void setTimestamp(String name, Timestamp value) throws SQLException {
		int[] indexes = getIndexes(name);
		for (int i = 0; i < indexes.length; i++) {
			preparedStatement.setTimestamp(indexes[i], value);
		}
	}

	public static void setDate(String name, Date value) throws SQLException {
		int[] indexes = getIndexes(name);
		for (int i = 0; i < indexes.length; i++) {
			preparedStatement.setDate(indexes[i], new java.sql.Date(value.getTime()));
		}
	}

	public static PreparedStatement getStatement() {
		return preparedStatement;
	}

	public static boolean execute() throws SQLException {
		return preparedStatement.execute();
	}

	public static ResultSet executeQuery() throws SQLException {
		return preparedStatement.executeQuery();
	}

	public static int executeUpdate() throws SQLException {
		return preparedStatement.executeUpdate();
	}

	public static void close() throws SQLException {
		preparedStatement.close();
	}

	public static void addBatch() throws SQLException {
		preparedStatement.addBatch();
	}

	public static int[] executeBatch() throws SQLException {
		return preparedStatement.executeBatch();
	}
}
