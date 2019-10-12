package persistencia.dao.interfaz;


public interface DAOAbstractFactory {

	public ClienteDAO createClienteDAO();

	public ProfesionalDAO createProfesionalDAO();

	public ServicioDAO createServicioDAO();

	public HorarioDAO createHorarioDAO();

	public SucursalDAO createSucursalDAO();

	public CitaDAO createCitaDAO();

	public PromocionDAO createPromocionDAO();
	
	public MovimientoCajaDAO createMovimientoCajaDAO();
	
	public CategoriaMovimientoCajaDAO createCategoriaMovimientoCajaDAO();
	}
