import javax.swing.*;
import java.sql.*;

public class Main {
    public static void main(String[] args) {

        String usuario = "root";
        String password = "";
        String db = "biblioteca";
        String ip = "localhost";
        String puerto = "3306";

        String url = "jdbc:mysql://"+ ip + ":" + puerto + "/" + db ;

        Connection conexion;
        Statement statement;
        ResultSet response;


        /* usamos un try / catch por que puede fallar o no */
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            conexion = DriverManager.getConnection(url, usuario, password);
            statement = conexion.createStatement();

            //-----Limpiar----
            System.out.println("Limpiando... para ver mejor el ejemplo");
            statement.executeUpdate("DELETE FROM libros");

           //-----Crear----
            System.out.println("Creando...");
            statement.executeUpdate("INSERT INTO libros (title, author, year) VALUES ('Cien años de soledad', 'Gabriel García Márquez', 1967)");
            statement.executeUpdate("INSERT INTO libros (title, author, year) VALUES ('Don Quijote de la Mancha', 'Miguel de Cervantes', 1600)");
            statement.executeUpdate("INSERT INTO libros (title, author, year) VALUES ('El principito', 'Antoine de Saint-Exupéry', 1943)");


            //-----Listar----
            System.out.println("Mostrando...");
            response =  statement.executeQuery("SELECT * FROM libros");
            while (response.next()){
                System.out.printf("ID:%d  Title:%s  Author:%s  Year:%d%n",
                        response.getInt("id"),
                        response.getString("title"),
                        response.getString("author"),
                        response.getInt("year"));
            }

            //-----Actualizar----
            System.out.println("Actualizando...");
            statement.executeUpdate("UPDATE libros SET year = 1605 WHERE title = 'Don Quijote de la Mancha' ");

            //-----Listar----
            System.out.println("Mostrando Lista Actualizada...");
            response =  statement.executeQuery("SELECT * FROM libros");
            while (response.next()){
                System.out.printf("ID:%d  Title:%s  Author:%s  Year:%d%n",
                        response.getInt("id"),
                        response.getString("title"),
                        response.getString("author"),
                        response.getInt("year"));
            }

            //-----Eliminar----
            System.out.println("Eliminado...");
            statement.executeUpdate("DELETE FROM libros WHERE title = 'Don Quijote de la Mancha' ");

            //-----Listar----
            System.out.println("Mostrando Lista con Registro Eliminado...");
            response =  statement.executeQuery("SELECT * FROM libros");
            while (response.next()){
                System.out.printf("ID:%d  Title:%s  Author:%s  Year:%d%n",
                        response.getInt("id"),
                        response.getString("title"),
                        response.getString("author"),
                        response.getInt("year"));
            }

        } catch (SQLException e) {
            //throw new RuntimeException(e);
            JOptionPane.showMessageDialog(null, "no se pudo conectar a la base de datos, error: "+ e);
        }


    }
}

