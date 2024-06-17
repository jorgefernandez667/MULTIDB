package com.proyect;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class CRUDOperationsMySQL {

    private static Connection getConnection(String url, String user, String password) throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public static void InsertRecord(String tableName, String url, String user, String password, Object object, int idValue) {
        String idColumnName = "ID_" + tableName;

        StringBuilder insertSQL = new StringBuilder("INSERT INTO " + tableName + " (");
        StringBuilder valuesSQL = new StringBuilder(" VALUES (");

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            // Construir la consulta INSERT
            insertSQL.append(idColumnName).append(",");
            valuesSQL.append("?,");
            
            for (Field field : object.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                insertSQL.append(field.getName()).append(",");
                valuesSQL.append("?,");
            }
            insertSQL.deleteCharAt(insertSQL.length() - 1).append(")");
            valuesSQL.deleteCharAt(valuesSQL.length() - 1).append(")");
            String sql = insertSQL.toString() + valuesSQL.toString();

            // Preparar la declaración SQL
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                // Configurar el valor del ID
                preparedStatement.setObject(1, idValue);
                
                // Configurar los valores de los campos
                int index = 2;
                for (Field field : object.getClass().getDeclaredFields()) {
                    field.setAccessible(true);
                    preparedStatement.setObject(index++, field.get(object));
                }

                // Ejecutar la inserción
                preparedStatement.executeUpdate();
                System.out.println("Registro insertado en MySQL.");
            }
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void ReadRecords(Class<?> clazz, String url, String user, String password) {
        String tableName = clazz.getSimpleName().toLowerCase();

        try (Connection connection = getConnection(url, user, password)) {
            String selectSQL = "SELECT * FROM " + tableName;
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    for (Field field : clazz.getDeclaredFields()) {
                        field.setAccessible(true);
                        System.out.println(field.getName() + ": " + resultSet.getObject(field.getName()));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void UpdateRegistry(String tableName, String url, String user, String password, String keyField, int keyValue, String fieldName, Object nuevoValor) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String updateSQL = "UPDATE " + tableName + " SET " + fieldName + " = ? WHERE " + keyField + " = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
                preparedStatement.setObject(1, nuevoValor);
                preparedStatement.setInt(2, keyValue);  // Cambiado a setInt para un valor entero
                preparedStatement.executeUpdate();
                System.out.println("Registro actualizado en MySQL.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void UpdateTableStructure(Class<?> clazz, String url, String user, String password) {
        String tableName = clazz.getSimpleName().toLowerCase();

        try (Connection connection = getConnection(url, user, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet columns = metaData.getColumns(null, null, tableName, null);

            // Map of existing columns in the database
            Map<String, String> existingColumns = new HashMap<>();
            while (columns.next()) {
                existingColumns.put(columns.getString("COLUMN_NAME"), columns.getString("TYPE_NAME"));
            }

            for (Field field : clazz.getDeclaredFields()) {
                String columnName = field.getName();
                String columnType = getSQLType(field.getType());

                if (!existingColumns.containsKey(columnName)) {
                    // Add the new column
                    String addColumnSQL = "ALTER TABLE " + tableName + " ADD " + columnName + " " + columnType;
                    try (Statement statement = connection.createStatement()) {
                        statement.executeUpdate(addColumnSQL);
                        System.out.println("Columna " + columnName + " agregada a la tabla " + tableName + ".");
                    }
                } else if (!existingColumns.get(columnName).equalsIgnoreCase(columnType)) {
                    // Update the column type if it doesn't match
                    String modifyColumnSQL = "ALTER TABLE " + tableName + " MODIFY " + columnName + " " + columnType;
                    try (Statement statement = connection.createStatement()) {
                        statement.executeUpdate(modifyColumnSQL);
                        System.out.println("Columna " + columnName + " modificada en la tabla " + tableName + ".");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static String getSQLType(Class<?> fieldType) {
        if (fieldType == String.class) {
            return "VARCHAR(255)";
        } else if (fieldType == int.class || fieldType == Integer.class) {
            return "INT";
        } else if (fieldType == long.class || fieldType == Long.class) {
            return "BIGINT";
        } else if (fieldType == double.class || fieldType == Double.class) {
            return "DOUBLE";
        } else if (fieldType == float.class || fieldType == Float.class) {
            return "FLOAT";
        } else if (fieldType == boolean.class || fieldType == Boolean.class) {
            return "BOOLEAN";
        } else if (fieldType == java.util.Date.class) {
            return "DATE";
        }
        throw new IllegalArgumentException("Tipo de dato no soportado: " + fieldType.getSimpleName());
    }



    public static void DeleteField(String url, String user, String password, String tableName, String fieldName) {
        try (Connection connection = getConnection(url, user, password)) {
            String alterTableSQL = "ALTER TABLE " + tableName + " DROP COLUMN " + fieldName;
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(alterTableSQL);
                System.out.println("Columna eliminada de MySQL.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void DeleteRecord(String tableName, String url, String user, String password, int id) {
        String sql = "DELETE FROM " + tableName + " WHERE ID_" + tableName + " = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Registro eliminado en MySQL.");
            } else {
                System.out.println("No se encontró ningún registro para eliminar en MySQL.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}