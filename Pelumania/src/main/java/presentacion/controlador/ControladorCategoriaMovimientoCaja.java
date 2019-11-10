package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JOptionPane;

import dto.CategoriaMovimientoCajaDTO;
import modelo.Sistema;
import presentacion.vista.VentanaCategoriaMovimentoCaja;

public class ControladorCategoriaMovimientoCaja {
	private static ControladorCategoriaMovimientoCaja INSTANCE;
	private static VentanaCategoriaMovimentoCaja ventanaCategoria;
	private static List<CategoriaMovimientoCajaDTO> categoriaEnTabla;
	private ControladorAgregarCategoriaMovimientoCaja controladorAgregarCategoriaMovimientoCaja;

	private static Sistema sistema;

	private ControladorCategoriaMovimientoCaja(Sistema sist) {
		ventanaCategoria = VentanaCategoriaMovimentoCaja.getInstance();
		ventanaCategoria.getBtnAgregar().addActionListener(a -> agregarCategoria(a));
		ventanaCategoria.getBtnBorrar().addActionListener(b -> borrarCategoria(b));
		ventanaCategoria.getBtnEditar().addActionListener(c -> editarCategoria(c));
		sistema = sist;
	}

	public static ControladorCategoriaMovimientoCaja getInstance(Sistema sistema) {
		if (INSTANCE == null) {
			INSTANCE = new ControladorCategoriaMovimientoCaja(sistema);
		}

		List<CategoriaMovimientoCajaDTO> categoriaEnTabla = sistema.obtenerCategoriasMovimientoCaja();
		INSTANCE.ventanaCategoria.llenarTabla(categoriaEnTabla);
		INSTANCE.ventanaCategoria.mostrar();
		return INSTANCE;
	}

	private void agregarCategoria(ActionEvent a) {
		this.controladorAgregarCategoriaMovimientoCaja = ControladorAgregarCategoriaMovimientoCaja.getInstance(sistema);
	}

	private void editarCategoria(ActionEvent c) {
		categoriaEnTabla = sistema.obtenerCategoriasMovimientoCaja();
		int filaSeleccionada = ventanaCategoria.getTablaCategoria().getSelectedRow();

		if (filaSeleccionada >= 0) {
			int idCategoria = categoriaEnTabla.get(filaSeleccionada).getIdCategoria();
			CategoriaMovimientoCajaDTO categoria_a_editar = sistema.getCategoriaMovimientoCajaById(idCategoria);
			// uso la misma ventana para editar y agregar
			this.controladorAgregarCategoriaMovimientoCaja = ControladorAgregarCategoriaMovimientoCaja
					.getInstance(sistema, idCategoria);
			// seteo los input
			this.controladorAgregarCategoriaMovimientoCaja.llenarInputsEditar(categoria_a_editar);
		} else {
			// no eligio nada
			this.ventanaCategoria.mostrarErrorSinSeleccionar();
		}
	}

	private void borrarCategoria(ActionEvent b) {
		categoriaEnTabla = sistema.obtenerCategoriasMovimientoCaja();
		int filaSeleccionada = ventanaCategoria.getTablaCategoria().getSelectedRow();

		if (filaSeleccionada > 0) {
			if (categoriaEnTabla.get(filaSeleccionada).getEstado().equalsIgnoreCase(("Inactivo"))) {
				JOptionPane.showMessageDialog(null, "No se puede eliminar algo ya eliminado!");
			} else {
				int confirm = this.ventanaCategoria.mostrarConfirmacionBorrar();
				if (confirm == 0) {
					int idCategoria = categoriaEnTabla.get(filaSeleccionada).getIdCategoria();
					CategoriaMovimientoCajaDTO categoria_a_eliminar = sistema
							.getCategoriaMovimientoCajaById(idCategoria);
					sistema.deleteCategoriaMovimientoCaja(categoria_a_eliminar);
					ControladorCategoriaMovimientoCaja.getInstance(sistema);

				}
			}
		} else {
			// no eligio nada
			this.ventanaCategoria.mostrarErrorSinSeleccionar();
		}
	}

}