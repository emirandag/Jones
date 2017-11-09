package dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bd.ConnectionManager;
import beans.Cliente;

public class ClienteDao {

	static Connection con = null;
	public static SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

	static Logger log = (Logger) LogManager.getLogger(ClienteDao.class);

	public static Cliente loginValid(String dni, String pass) {
		log.info("loginValid()..............." + dni + "\t" + pass);
		Cliente c = new Cliente();

		con = ConnectionManager.getConnection();

		PreparedStatement stat = null;
		
		ResultSet rs = null;
		String query = pass != null ? "clienteLogin" : "clienteRecuperar";

		try {

			Properties prop = new Properties();

			InputStream is = ClienteDao.class.getClassLoader().getResourceAsStream("sql.properties");
			prop.load(is);

			stat = con.prepareStatement(prop.getProperty(query));
			stat.setString(1, dni.toLowerCase());
			if (pass != null) {
				stat.setString(2, pass);
			}

			rs = stat.executeQuery();
			if (rs.next()) {
				c.setNombre(rs.getString("nombre"));
				c.setDni(rs.getString("dni"));
				c.setApellidos(rs.getString("apellidos"));
				c.setFechanac(df.format(rs.getDate("fecha_nacimiento")));
				c.setSexo(rs.getString("sexo"));
				c.setDireccion(rs.getString("dirección"));
				c.setTelefono(rs.getString("telefono"));
				c.setValid(true);
			} else {
				c.setValid(false);
			}
		} catch (SQLException e) {
			log.error("loginValid()................ERROR "+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}

			if (stat != null) {
				try {
					stat.close();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
		log.warn("loginValid()...............Exit");
		return c;
	}

	public static Cliente updateUser(String dni, String nombre, String apellidos, String fechanac, String sexo,
			String direccion, String telefono) {

		Cliente c = new Cliente();
		c.setValid(false);

		con = ConnectionManager.getConnection();

		PreparedStatement stat = null;
		ResultSet rs = null;

		try {

			Properties prop = new Properties();

			InputStream is = ClienteDao.class.getClassLoader().getResourceAsStream("sql.properties");
			prop.load(is);

			stat = con.prepareStatement(prop.getProperty("clienteUpdate"));
			stat.setString(1, nombre);
			stat.setString(2, apellidos);
			stat.setString(3, fechanac);
			stat.setString(4, sexo);
			stat.setString(5, direccion);
			stat.setString(6, telefono);
			stat.setString(7, dni);
			stat.executeUpdate();
			System.out.println(stat.toString() + "****************************");

			System.out.println("Has sido actualizado");

		} catch (SQLException e) {
			System.err.println(e.getMessage());
			System.out.println("No se ha actualizado");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			c = loginValid(dni, null);
			c.setValid(true);
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}

			if (stat != null) {
				try {
					stat.close();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}

		return c;
	}
        
        public static Cliente loginReg(String dni, String nombre, String apellidos, String fechanac, String sexo, String direccion, String telefono, String pass) {
		   
	Cliente c = new Cliente();
        c.setValid(false);
	
	con = ConnectionManager.getConnection();

	PreparedStatement stat = null;
	ResultSet rs = null;
	try {

		Properties prop = new Properties();

		InputStream is = ClienteDao.class.getClassLoader().getResourceAsStream("sql.properties");
		prop.load(is);

		stat = con.prepareStatement(prop.getProperty("clienteInsert"));
		stat.setString(1, dni.toLowerCase());
		stat.setString(2, nombre.toLowerCase());
		stat.setString(3, apellidos.toLowerCase());
		stat.setString(4, fechanac.toLowerCase());
		stat.setString(5, sexo.toUpperCase());
		stat.setString(6, direccion.toLowerCase());
		stat.setString(7, telefono.toLowerCase());
		stat.setString(8, pass);
		
		stat.executeUpdate();
                c.setValid(true);
	} catch (SQLException e) {
		System.err.println(e.getMessage());
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
		c = loginValid(dni, pass);
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

		if (stat != null) {
			try {
				stat.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

	return c;
}

}
