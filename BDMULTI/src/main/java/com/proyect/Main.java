package com.proyect;
import java.util.Scanner;

import com.proyect.classautomovil.ClassAutomovil;
import com.proyect.classsmartphone.ClassSmartphone;
import com.proyect.classweapon.ClassWeapon;
public class Main {

    
    public static void main(String[] args) {
        // Parámetros de conexión para MongoDB
        String connectionMongo = "mongodb://localhost:27017";  // Ajusta la URL según tu base de datos
        String databaseNameMongo = "Smartphone";  // Nombre de la base de datos
        // Parámetros de conexión para MySQL
        String urlMySQL = "jdbc:mysql://localhost:3306/weapon";  // Ajusta la URL según tu base de datos
        String userMySQL = "root";  // Ajusta el nombre de usuario
        String passwordMySQL = "";  // Ajusta la contraseña
        // Parámetros de conexión para Oracle
        String urlOracle = "jdbc:oracle:thin:@//localhost:1521/xe";  // Ajusta la URL según tu base de datos
        String userOracle = "sys as sysdba";  // Ajusta el nombre de usuario
        String passwordOracle = "jorgeoliver667";  // Ajusta la contraseña

   Scanner scanner = new Scanner(System.in);
        int option = 0;
        int option2 = 0;
        String variable1="";
        String variable2="";
        String variable6="";
        String variable8="";
        int variable3=0;
        int variable4=0;
        int variable7=0;
        
        ClassWeapon.AK AKALL=new ClassWeapon.AK(variable1, variable2, variable3);
        ClassAutomovil.Mustang MustangAll= new ClassAutomovil.Mustang(variable1, variable2, variable3);
        ClassSmartphone.Samsung SamsungAll= new ClassSmartphone.Samsung(variable1, variable2, variable3);
        while (option != 5) {
            System.out.println("\nSelecciona una opción:");
            System.out.println("1. MySQL");
            System.out.println("2. Oracle");
            System.out.println("3. MongoDB");
            System.out.println("4. Mapeo de todas las clases");
            System.out.println("5. Salir");

            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.println("Has seleccionado MySQL");
                    

                    while (option2 != 8) {
                        System.out.println("\nSelecciona una operación:");
                        System.out.println("1. Insertar objeto");
                        System.out.println("2. Leer tablas");
                        System.out.println("3. Actualizar Objeto");
                        System.out.println("4. Actualizar tabla");
                        System.out.println("5. Eliminar columna");
                        System.out.println("6. Eliminar objeto");
                        System.out.println("7. Mapear clase");
                        System.out.println("8. Salir");
            
                        option2 = scanner.nextInt();
                        scanner.nextLine(); // Limpiar el buffer de entrada después de leer el número
            
                        switch (option2) {
                            case 1:
                            System.out.println("porfavor ingrese el nombre de la tabla que ingresara el objeto");
                            variable6=scanner.nextLine();
                            System.out.println("porfavor ingrese el nombre del arma");
                            variable1=scanner.nextLine();
                            System.out.println("porfavor ingrese la municion que usa");
                            variable2=scanner.nextLine();
                            System.out.println("porfavor ingrese el precio del arma");
                            variable3=scanner.nextInt();
                            variable4++;
                            scanner.nextLine();
                            AKALL.setmodel(variable1);
                            AKALL.setammunition(variable2);
                            AKALL.setprice(variable3);
                            CRUDOperationsMySQL.InsertRecord(variable6, urlMySQL, userMySQL, passwordMySQL, AKALL, variable4);
                                break;
                            case 2:
                                CRUDOperationsMySQL.ReadRecords(ClassWeapon.class, urlMySQL, userMySQL, passwordMySQL);
                                break;
                            case 3:
                            System.out.println("porfavor ingrese el nombre de la tabla donde se encuestra el objeto");
                            variable6=scanner.nextLine();
                            System.out.println("porfavor ingrese el ID unico del objeto");
                            variable3=scanner.nextInt();
                            scanner.nextLine();
                            System.out.println("porfavor ingrese el parametro a cambiar del objeto");
                            variable1=scanner.nextLine();
                            variable8="ID_"+variable6;
                            while(variable7!=1 || variable7!=2){
                            System.out.println("el valor es un entero o una cadena de palabras?");
                            System.out.println("entero=1 o cadena de palabras= 2");
                            variable7=scanner.nextInt();
                            scanner.nextLine();
                            if((variable7==1)||(variable7==2)){

                            }else{
                            System.out.println("porfavor ingrese un valor aceptado (1 o 2)");
                            }
                            }

                            if (variable7==1){
                                System.out.println("porfavor ingrese el valor a cambiar");
                                variable7=scanner.nextInt();
                                scanner.nextLine();
                                CRUDOperationsMySQL.UpdateRegistry(variable6, urlMySQL, userMySQL, passwordMySQL, variable8, variable3, variable1, variable7);
                            }else if(variable7==2){
                                System.out.println("porfavor ingrese el valor a cambiar");
                                variable2=scanner.nextLine();
                                scanner.nextLine();
                                CRUDOperationsMySQL.UpdateRegistry(variable6, urlMySQL, userMySQL, passwordMySQL, variable8, variable3, variable1, variable2);
                            }
                                break;
                            case 4:
                            CRUDOperationsMySQL.UpdateTableStructure(ClassWeapon.class, urlMySQL, userMySQL, passwordMySQL);
                                break;
                            case 5:
                                System.out.println("porfavor ingrese el nombre la tabla de donde quiere eliminar la columna");
                                variable1=scanner.nextLine();
                                System.out.println("porfavor ingrese el nombre la columna que quiere eliminar");
                                variable2=scanner.nextLine();
                              CRUDOperationsMySQL.DeleteField(urlMySQL, userMySQL, passwordMySQL, variable1, variable2);
                                break;
                            case 6:
                                System.out.println("Porfavor ingrese el nombre de la tabla donde se encuentra el objeto");
                                variable1=scanner.nextLine();
                                System.out.println("Porfavor ingrese el ID unico del objeto que quiere eliminar");
                                variable3=scanner.nextInt();
                                scanner.nextLine();
                                CRUDOperationsMySQL.DeleteRecord(variable1, urlMySQL, userMySQL, passwordMySQL, variable3);
                                break;
                            case 7:
                            MapeoMySQL.RunMapping(ClassWeapon.class, urlMySQL, userMySQL, passwordMySQL);
                                break;

                                case 8:
                                System.out.println("Saliendo del programa...");
                                break;
                            default:
                                System.out.println("Opción no válida. Por favor, selecciona una opción válida del 1 al 7");
                                break;
                        }
                    }
            
                    
                    break;
                case 2:
                System.out.println("Has seleccionado Oracle");

                while (option2 != 8) {
                    System.out.println("\nSelecciona una operación:");
                    System.out.println("1. Insertar objeto");
                    System.out.println("2. Leer tablas");
                    System.out.println("3. Actualizar Objeto");
                    System.out.println("4. Actualizar tabla");
                    System.out.println("5. Eliminar columna");
                    System.out.println("6. Eliminar objeto");
                    System.out.println("7. Mapear clase");
                    System.out.println("8. Salir");

                    option2 = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer de entrada después de leer el número

                    switch (option2) {
                        case 1:
                            System.out.println("Por favor, ingrese el nombre de la tabla donde ingresará el objeto:");
                            variable6 = scanner.nextLine();
                            System.out.println("Por favor, ingrese el modelo del auto:");
                            variable1 = scanner.nextLine();
                            System.out.println("Por favor, ingrese el motor que posee:");
                            variable2 = scanner.nextLine();
                            System.out.println("Por favor, ingrese el precio del auto:");
                            variable3 = scanner.nextInt();
                            variable4++;
                            scanner.nextLine();
                            MustangAll.setModel(variable1);
                            MustangAll.setMotor(variable2);
                            MustangAll.setPrice(variable3);
                            CRUDOperationsOracle.InsertRecord(variable6, urlOracle, userOracle, passwordOracle, MustangAll, variable4);
                            break;
                        case 2:
                            CRUDOperationsOracle.ReadRecords(ClassAutomovil.class, urlOracle, userOracle, passwordOracle);
                            break;
                        case 3:
                            System.out.println("Por favor, ingrese el nombre de la tabla donde se encuentra el objeto:");
                            variable6 = scanner.nextLine();
                            System.out.println("Por favor, ingrese el ID único del objeto:");
                            variable3 = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println("Por favor, ingrese el parámetro a cambiar del objeto:");
                            variable1 = scanner.nextLine();
                            variable8 = "ID_" + variable6;
                            while (variable7 != 1 && variable7 != 2) {
                                System.out.println("¿El valor es un entero o una cadena de palabras?");
                                System.out.println("1. Entero");
                                System.out.println("2. Cadena de palabras");
                                variable7 = scanner.nextInt();
                                scanner.nextLine();
                                if (variable7 != 1 && variable7 != 2) {
                                    System.out.println("Por favor, ingrese un valor aceptado (1 o 2)");
                                }
                            }

                            if (variable7 == 1) {
                                System.out.println("Por favor, ingrese el valor a cambiar:");
                                int valorEntero = scanner.nextInt();
                                scanner.nextLine();
                                CRUDOperationsOracle.UpdateRegistry(variable6, urlOracle, userOracle, passwordOracle, variable8, variable3, variable1, valorEntero);
                            } else if (variable7 == 2) {
                                System.out.println("Por favor, ingrese el valor a cambiar:");
                                String valorCadena = scanner.nextLine();
                                CRUDOperationsOracle.UpdateRegistry(variable6, urlOracle, userOracle, passwordOracle, variable8, variable3, variable1, valorCadena);
                            }
                            break;
                        case 4:
                            CRUDOperationsOracle.UpdateTableStructure(ClassAutomovil.class, urlOracle, userOracle, passwordOracle);
                            break;
                        case 5:
                            System.out.println("Por favor, ingrese el nombre de la tabla donde quiere eliminar la columna:");
                            variable6 = scanner.nextLine();
                            System.out.println("Por favor, ingrese el nombre de la columna que quiere eliminar:");
                            variable1 = scanner.nextLine();
                            CRUDOperationsOracle.DeleteField(urlOracle, userOracle, passwordOracle, variable6, variable1);
                            break;
                        case 6:
                            System.out.println("Por favor, ingrese el nombre de la tabla donde se encuentra el objeto:");
                            variable6 = scanner.nextLine();
                            System.out.println("Por favor, ingrese el ID único del objeto que quiere eliminar:");
                            variable3 = scanner.nextInt();
                            scanner.nextLine();
                            CRUDOperationsOracle.DeleteRecord(variable6, urlOracle, userOracle, passwordOracle, variable3);
                            break;
                        case 7:
                            MapeoOracle.RunMapping(ClassAutomovil.class, urlOracle, userOracle, passwordOracle);
                            break;
                        case 8:
                            System.out.println("Saliendo del menú Oracle...");
                            break;
                        default:
                            System.out.println("Opción no válida. Por favor, selecciona una opción válida del 1 al 8");
                            break;
                    }
                }
                    break;
                case 3:
                System.out.println("Has seleccionado MongoDB");

                while (option2 != 8) {
                    System.out.println("\nSelecciona una operación:");
                    System.out.println("1. Insertar objeto");
                    System.out.println("2. Leer documentos");
                    System.out.println("3. Actualizar documento");
                    System.out.println("4. Actualizar estructura de colección");
                    System.out.println("5. Eliminar campo");
                    System.out.println("6. Eliminar documento");
                    System.out.println("7. Mapear clase");
                    System.out.println("8. Salir");

                    option2 = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer de entrada después de leer el número

                    switch (option2) {
                        case 1:
                            System.out.println("Por favor, ingrese el nombre de la colección donde ingresará el documento:");
                            variable6 = scanner.nextLine();
                            System.out.println("Por favor, ingrese el modelo del smartphone:");
                            variable1 = scanner.nextLine();
                            System.out.println("Por favor, ingrese el procesador del smartphone:");
                            variable2 = scanner.nextLine();
                            System.out.println("Por favor, ingrese el precio del smartphone:");
                            variable3 = scanner.nextInt();
                            variable4++;
                            scanner.nextLine();
                            SamsungAll.setmodel(variable1);
                            SamsungAll.setprocessor(variable2);
                            SamsungAll.setprice(variable3);
                            CRUDOperationsMongo.insertDocument(variable6, connectionMongo, databaseNameMongo, SamsungAll, variable4);
                            break;
                        case 2:
                            CRUDOperationsMongo.ReadDocuments(ClassSmartphone.class, connectionMongo, databaseNameMongo);
                            break;
                        case 3:
                            System.out.println("Por favor, ingrese el nombre de la colección donde se encuentra el documento:");
                            variable6 = scanner.nextLine();
                            System.out.println("Por favor, ingrese el ID único del documento:");
                            variable3 = scanner.nextInt();
                            scanner.nextLine();
                            variable8 = "ID_" + variable6;
                            System.out.println("Por favor, ingrese el parámetro a cambiar del documento:");
                            variable1 = scanner.nextLine();
                            while (variable7 != 1 && variable7 != 2) {
                                System.out.println("¿El valor es un entero o una cadena de palabras?");
                                System.out.println("1. Entero");
                                System.out.println("2. Cadena de palabras");
                                variable7 = scanner.nextInt();
                                scanner.nextLine();
                                if (variable7 != 1 && variable7 != 2) {
                                    System.out.println("Por favor, ingrese un valor aceptado (1 o 2)");
                                }
                            }

                            if (variable7 == 1) {
                                System.out.println("Por favor, ingrese el valor a cambiar:");
                                variable7 = scanner.nextInt();
                                scanner.nextLine();
                                CRUDOperationsMongo.UpdateDocument(variable6, connectionMongo, databaseNameMongo, variable8, variable3, variable1,variable7);
                            } else if (variable7 == 2) {
                                System.out.println("Por favor, ingrese el valor a cambiar:");
                                variable2 = scanner.nextLine();
                                scanner.nextLine();
                                CRUDOperationsMongo.UpdateDocument(variable6, connectionMongo, databaseNameMongo, variable8, variable3, variable1,variable2);
                            }
                            break;
                        case 4:
                            CRUDOperationsMongo.UpdateStructureCollection(ClassSmartphone.class, connectionMongo, databaseNameMongo);
                            break;
                        case 6:
                            System.out.println("Por favor, ingrese el nombre de la colección donde se encuentra el documento:");
                            variable1 = scanner.nextLine();
                            System.out.println("Por favor, ingrese el ID único del documento que quiere eliminar:");
                            variable3 = scanner.nextInt();
                            scanner.nextLine();
                            CRUDOperationsMongo.DeleteDocument(variable1, connectionMongo, databaseNameMongo, variable3);
                            break;
                        case 7:
                            MapeoMongo.RunMapping(ClassSmartphone.class, connectionMongo, databaseNameMongo);
                            break;
                        case 8:
                            System.out.println("Saliendo del programa...");
                            break;
                        default:
                            System.out.println("Opción no válida. Por favor, selecciona una opción válida del 1 al 8");
                            break;
                    }
                }
                    break;

                case 4:
                    ejecutarMapeoTotal();
                    System.out.println("sea mapeado todas las clases");
                break;
                case 5:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, selecciona una opción válida del 1 al 4");
                    break;
            }
        

        
    }
scanner.close();
        
    }
    public static void ejecutarMapeoTotal() {
         // Parámetros de conexión para MongoDB
         String connectionMongo = "mongodb://localhost:27017";  // Ajusta la URL según tu base de datos
         String databaseNameMongo = "Smartphone";  // Nombre de la base de datos

        // Llama a la función para mapear las clases y crear las colecciones en MongoDB
        MapeoMongo.RunMapping(ClassSmartphone.class, connectionMongo, databaseNameMongo);

        // Parámetros de conexión para MySQL
        String urlMySQL = "jdbc:mysql://localhost:3306/weapon";  // Ajusta la URL según tu base de datos
        String userMySQL = "root";  // Ajusta el nombre de usuario
        String passwordMySQL = "";  // Ajusta la contraseña

        // Llama a la función para mapear las clases y crear las tablas en MySQL
        MapeoMySQL.RunMapping(ClassWeapon.class, urlMySQL, userMySQL, passwordMySQL);

        // Parámetros de conexión para Oracle
        String urlOracle = "jdbc:oracle:thin:@//localhost:1521/xe";  // Ajusta la URL según tu base de datos
        String userOracle = "sys as sysdba";  // Ajusta el nombre de usuario
        String passwordOracle = "jorgeoliver667";  // Ajusta la contraseña

        // Llama a la función para mapear las clases y crear las tablas en Oracle
        MapeoOracle.RunMapping(ClassAutomovil.class, urlOracle, userOracle, passwordOracle);
    }
}