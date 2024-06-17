package com.proyect;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class MapeoOracle {

    public static void RunMapping(Class<?> clazz, String url, String user, String password) {
        try {
            // Conectar a la base de datos de Oracle
            Connection connection = DriverManager.getConnection(url, user, password);

            // Mapea la clase y crea tablas
            mapClassToDatabase(clazz, connection);

            // Cierra la conexión
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void mapClassToDatabase(Class<?> clazz, Connection connection) throws SQLException {
        for (Class<?> innerClass : clazz.getDeclaredClasses()) {
            String tableName = innerClass.getSimpleName().toLowerCase();

            // Comprueba si la tabla ya existe
            if (tableExists(connection, tableName)) {
                System.out.println("La tabla " + tableName + " ya existe.");
                continue;
            }

            StringBuilder createTableSQL = new StringBuilder("CREATE TABLE " + tableName + " (");

            // Agrega la columna ID correspondiente
            String idColumnName = "ID_" + tableName;
            createTableSQL.append(idColumnName).append(" NUMBER PRIMARY KEY, "); // Definición de la columna ID

            Field[] fields = innerClass.getDeclaredFields();
            for (Field field : fields) {
                String columnName = field.getName();
                String columnType = getSQLType(field.getType());
                createTableSQL.append(columnName).append(" ").append(columnType).append(", ");
            }

            createTableSQL.setLength(createTableSQL.length() - 2);  // Elimina la última coma y espacio
            createTableSQL.append(")");

            try (Statement statement = connection.createStatement()) {
                statement.execute(createTableSQL.toString());
                System.out.println("Tabla " + tableName + " creada exitosamente.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static boolean tableExists(Connection connection, String tableName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute("SELECT 1 FROM " + tableName);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    private static String getSQLType(Class<?> fieldType) {
        if (fieldType == String.class) {
            return "VARCHAR2(255)";
        } else if (fieldType == int.class || fieldType == Integer.class) {
            return "NUMBER";
        } else if (fieldType == long.class || fieldType == Long.class) {
            return "NUMBER";
        } else if (fieldType == double.class || fieldType == Double.class) {
            return "NUMBER";
        } else if (fieldType == float.class || fieldType == Float.class) {
            return "NUMBER";
        } else if (fieldType == boolean.class || fieldType == Boolean.class) {
            return "NUMBER(1)";
        } else if (fieldType == java.util.Date.class) {
            return "DATE";
        }
        throw new IllegalArgumentException("Tipo de dato no soportado: " + fieldType.getSimpleName());
    }
}
