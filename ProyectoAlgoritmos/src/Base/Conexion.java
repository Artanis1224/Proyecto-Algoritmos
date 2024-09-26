package Base;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    public String db = "sistemagestiondb";
    public String url = "jdbc:mysql://127.0.0.1/" + db;
    public String user = "root";
    public String pass = "";

    public Conexion() { //Constructor vacio
    }

    // Establece la conexión con la base de datos y retorna el objeto Connection
    public Connection conectar() {
        Connection link = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Carga el driver de MySQL
            link = DriverManager.getConnection(this.url, this.user, this.pass); // Establece la conexión
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showConfirmDialog(null, e); // Muestra un diálogo de error si falla la conexión
        }
        return link; // Retorna el objeto Connection (null si hubo un error)
    }
}
