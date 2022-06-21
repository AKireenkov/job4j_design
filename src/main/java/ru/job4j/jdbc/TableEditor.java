package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private static Connection connection;

    private static Properties properties;

    private static Statement statement;

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
        statement = connection.createStatement();
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
        }
        try (TableEditor tableEditor = new TableEditor(config)
        ) {
            String tableName = "demo_table";
            createTable(tableName);
            addColumn(tableName, "test", "text");
            dropColumn(tableName, "test");
            renameColumn(tableName, "name", "TEST");
            dropTable(tableName);
        }
    }

    public static void createTable(String tableName) throws Exception {
        String sql = String.format(
                "create table if not exists %s (%s);",
                tableName,
                "id serial primary key"
        );
        statement.execute(sql);
        System.out.println(getTableScheme(connection, tableName));
    }

    public static void dropTable(String tableName) throws Exception {
        String sql = String.format(
                "drop table %s;",
                tableName
        );
        statement.execute(sql);
        System.out.println("table " + tableName + " is dropped");
    }

    public static void addColumn(String tableName, String columnName, String type) throws Exception {
        String sql = String.format(
                "ALTER TABLE %s ADD COLUMN %s %s;",
                tableName,
                columnName,
                type
        );
        statement.execute(sql);
        System.out.println(getTableScheme(connection, tableName));
    }

    public static void dropColumn(String tableName, String columnName) throws Exception {
        String sql = String.format(
                "ALTER TABLE %s DROP COLUMN %s ;",
                tableName,
                columnName
        );
        statement.execute(sql);
        System.out.println(getTableScheme(connection, tableName));
    }

    public static void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
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
            statement.close();
            connection.close();
        }
    }
}