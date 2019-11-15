package persistencia.dao.mariadb;


	import java.math.BigDecimal;
	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Time;
	import java.util.ArrayList;
	import java.util.List;

	import dto.CitaDTO;
	import dto.ProductoDTO;
import dto.ServicioDTO;
import persistencia.conexion.Conexion;
	import persistencia.dao.interfaz.ProductoDAO;

	public class ProductoDAOSQL implements ProductoDAO{
		
		private static final String insert = "INSERT INTO Producto(IdProducto,Nombre,PrecioLocal,PrecioDolar,Puntos,Estado) "
											+ "VALUES(?,?,?,?,?,?)";
		private static final String update = "UPDATE  Producto SET Nombre=?, PrecioLocal=?, PrecioDolar=?,Puntos=?, Estado=? WHERE idProducto=?";
		private static final String delete = "UPDATE  Producto SET Estado=? WHERE IdProducto = ?";
		private static final String readall = " SELECT * FROM Producto";
		private static final String dameId = " SELECT IdProducto FROM Producto where Nombre=?";
		private static final String getById = "SELECT * FROM Producto WHERE IdProducto = ?";
		private static final String deleteRealProducto = "DELETE FROM Producto WHERE IdProducto = ?";
		private static final String readBuscador = "SELECT * FROM Producto WHERE ? LIKE ?";
		private static final String ESTADO_INACTIVO = "Inactivo";


		public boolean insert(ProductoDTO Producto) {
			PreparedStatement statement;
			Connection conexion = Conexion.getConexion().getSQLConexion();
			boolean isInsertExitoso = false;
			try
			{
				statement = conexion.prepareStatement(insert);
				statement.setInt(1, Producto.getIdProducto());
				statement.setString(2, Producto.getNombre());
				statement.setBigDecimal(3, Producto.getPrecioLocal());
				statement.setBigDecimal(4, Producto.getPrecioDolar());
				statement.setInt(5, Producto.getPuntos());
				statement.setString(6, Producto.getEstado());	
				if(statement.executeUpdate() > 0)
				{
					conexion.commit();
					isInsertExitoso = true;
				}
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
				try {
					conexion.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			
			return isInsertExitoso;
		}
		
		public boolean update (ProductoDTO Producto_a_actualizar) {
			
			PreparedStatement statement;
			int chequeoUpdate = 0;
			Conexion conexion = Conexion.getConexion();
			try 
			{
				statement = conexion.getSQLConexion().prepareStatement(update);
				
				statement.setString(1, Producto_a_actualizar.getNombre());
				statement.setBigDecimal(2, Producto_a_actualizar.getPrecioLocal());
				statement.setBigDecimal(3, Producto_a_actualizar.getPrecioDolar());
				statement.setInt(4, Producto_a_actualizar.getPuntos());
				statement.setString(5, Producto_a_actualizar.getEstado());
				statement.setInt(6, Producto_a_actualizar.getIdProducto());
				
				chequeoUpdate = statement.executeUpdate();
				conexion.getSQLConexion().commit();
				if(chequeoUpdate > 0)
						return true;
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			
			return false;
		}

		public boolean delete(ProductoDTO Producto_a_eliminar) {
			PreparedStatement statement;
			Connection conexion = Conexion.getConexion().getSQLConexion();
			boolean isdeleteExitoso = false;
			try {
				statement = conexion.prepareStatement(delete);
				statement.setString(1, ESTADO_INACTIVO);
				statement.setInt(2, Producto_a_eliminar.getIdProducto());
				if(statement.executeUpdate() > 0){
					conexion.commit();
					isdeleteExitoso = true;
					
				}
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
			return isdeleteExitoso;
		}

	

		public List<ProductoDTO> readAll() {

			PreparedStatement statement;
			ResultSet resultSet; //Guarda el resultado de la query
			ArrayList<ProductoDTO> Productos = new ArrayList<ProductoDTO>();
			Conexion conexion = Conexion.getConexion();
			try {
				statement = conexion.getSQLConexion().prepareStatement(readall);
				resultSet = statement.executeQuery();
				while(resultSet.next()){
					Productos.add(getProductoDTO(resultSet));
				}
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
			return Productos;
		}
		
		
		
		public int obtenerID(String nombre) {
			PreparedStatement statement;
			ResultSet resultSet;
			Conexion conexion = Conexion.getConexion();
			int sucursal;
			try
			{
				statement = conexion.getSQLConexion().prepareStatement("Select idProducto FROM producto WHERE nombre = ?");

				statement.setString(1, nombre);
				resultSet = statement.executeQuery();
				if (resultSet.next()){
					sucursal = resultSet.getInt("idProducto");
					return sucursal;
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			return 0;
		}
		
		public ProductoDTO getById(int idServicio)
		{
			PreparedStatement statement;
			ResultSet resultSet; //Guarda el resultado de la query
			Connection conexion = Conexion.getConexion().getSQLConexion();
			List<ProductoDTO> Productos = new ArrayList<ProductoDTO>();
			try 
			{
				statement = conexion.prepareStatement(getById);
				statement.setInt(1, idServicio);
				resultSet = statement.executeQuery();
				
				while(resultSet.next())
				{
					Productos.add(getProductoDTO(resultSet));
				}
		
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			return Productos.get(0);
		}
		
		public ProductoDTO obtenerPorId(int idProducto)
		{
			PreparedStatement statement;
			ResultSet resultSet; //Guarda el resultado de la query
			Connection conexion = Conexion.getConexion().getSQLConexion();
			List<ProductoDTO> Productos = new ArrayList<ProductoDTO>();
			try 
			{
				statement = conexion.prepareStatement(getById);
				statement.setInt(1, idProducto);
				resultSet = statement.executeQuery();
				
				while(resultSet.next())
				{
					Productos.add(getProductoDTO(resultSet));
				}
		
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			return Productos.get(0);
		}
		
		
		public boolean deleteRealProducto(ProductoDTO Producto_a_eliminar) {
			PreparedStatement statement;
			Connection conexion = Conexion.getConexion().getSQLConexion();
			boolean isdeleteExitoso = false;
			try {
				statement = conexion.prepareStatement(deleteRealProducto);
				statement.setInt(1, Producto_a_eliminar.getIdProducto());
				if(statement.executeUpdate() > 0){
					conexion.commit();
					isdeleteExitoso = true;
				}
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
			return isdeleteExitoso;
		}
		
		protected static ProductoDTO getProductoDTO(ResultSet resultSet) throws SQLException{
			int id = resultSet.getInt("idProducto");
			String nombre = resultSet.getString("Nombre");
			BigDecimal precioLocal = resultSet.getBigDecimal("PrecioLocal");
			BigDecimal precioDolar =resultSet.getBigDecimal("PrecioDolar");
			int puntos = resultSet.getInt("Puntos");
			String estado = resultSet.getString("Estado");
		
			return new ProductoDTO(id,nombre,precioLocal,precioDolar,puntos,estado);
		}
		
		@Override
		public List<ProductoDTO> obtenerProductoBuscado(String variable, String value) {
			PreparedStatement statement;
			ResultSet resultSet; //Guarda el resultado de la query
			ArrayList<ProductoDTO> Producto = new ArrayList<ProductoDTO>();
			Conexion conexion = Conexion.getConexion();
			try {
				if(variable.equals("Todos")) {
					statement = conexion.getSQLConexion().prepareStatement(readall);
				}else {
					statement = conexion.getSQLConexion().prepareStatement(readall+" WHERE "+variable+ " LIKE '"+value+"'");
				}
				resultSet = statement.executeQuery();
				while(resultSet.next()){
					Producto.add(getProductoDTO(resultSet));
				}
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
			return Producto;
		}

		@Override
		public ProductoDTO obtenerProductoById(int id) {
			// TODO Auto-generated method stub
			return null;
		}

	


}
