package ru.job4j.jdbc;

import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private static Connection connection;

    private static Properties properties;

    public TableEditor(Properties properties) throws SQLException, ClassNotFoundException {
        this.properties = properties;
        initConnection(this.properties);
    }

    private static void initConnection(Properties properties) throws SQLException, ClassNotFoundException {
        Class.forName(properties.getProperty("driver"));
        String url = properties.getProperty("url");
        String login = properties.getProperty("login");
        String password = properties.getProperty("password");
        connection = DriverManager.getConnection(url, login, password);
    }

    public static void main(String[] args) throws Exception {
        Reader reader = new FileReader("./src/main/resources/app.properties");
        Properties properties = new Properties();
        properties.load(reader);

        TableEditor tableEditor = new TableEditor(properties);
        try (Statement statement = connection.createStatement()) {
            String tableName = "demo_table";
            createTable(tableName, statement);
            addColumn(tableName, "test", "text", statement);
            dropColumn(tableName, "test", statement);
            renameColumn(tableName, "name", "TEST", statement);
            dropTable(tableName, statement);
        }
        tableEditor.close();
    }

    public static void createTable(String tableName, Statement statement) throws Exception {
        String sql = String.format(
                "create table if not exists %s (%s, %s);",
                tableName,
                "id serial primary key",
                "name text"
        );
        statement.execute(sql);
        System.out.println(getTableScheme(connection, tableName));
    }

    public static void dropTable(String tableName, Statement statement) throws Exception {
        String sql = String.format(
                "drop table %s;",
                tableName
        );
        statement.execute(sql);
        System.out.println("table " + tableName + " is dropped");
    }

    public static void addColumn(String tableName, String columnName, String type, Statement statement) throws Exception {
        String sql = String.format(
                "ALTER TABLE %s ADD COLUMN %s %s;",
                tableName,
                columnName,
                type
        );
        statement.execute(sql);
        System.out.println(getTableScheme(connection, tableName));
    }

    public static void dropColumn(String tableName, String columnName, Statement statement) throws Exception {
        String sql = String.format(
                "ALTER TABLE %s DROP COLUMN %s ;",
                tableName,
                columnName
        );
        statement.execute(sql);
        System.out.println(getTableScheme(connection, tableName));
    }

    public static void renameColumn(String tableName, String columnName, String newColumnName, Statement statement) throws Exception {
        String sql = String.format(
                "ALTER TABLE %s RENAME COLUMN %s TO %s;",
                tableName,
                columnName,
                newColumnName
        );
        statement.execute(sql);
        System.out.println(getTableScheme(connection, tableName));
    }


    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}