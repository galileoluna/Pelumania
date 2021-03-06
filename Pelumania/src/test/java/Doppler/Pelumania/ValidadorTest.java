package Doppler.Pelumania;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import util.Validador;

class ValidadorTest {
	Validador validador;
	
	@Before
	public void before() {
		validador=new Validador();
	}
	@Test
	void esNombreValidoCorrectotest() {
		String nombre="santiago";
		
		assertTrue(validador.esNombreValido(nombre));
	}

	@Test
	void esNombreValidoSeparadotest() {
		String nombre="sant iago";
		
		assertFalse(validador.esNombreValido(nombre));
	}
	
	@Test
	void esNombreValidoIncorrectoCompuestotest() {
		String nombre="sant iago123";
		
		assertFalse(validador.esNombreValido(nombre));
	}
	
	@Test
	void esNombreValidoIncorrectoSimpletest() {
		String nombre="3";
		
		assertFalse(validador.esNombreValido(nombre));
	}
	
	@Test
	void esNombreValidoIncorrectoSignotest() {
		String nombre="santi:";
		
		assertFalse(validador.esNombreValido(nombre));
	}
	
	@Test
	void esNombreValidoIncorrectoVaciotest() {
		String nombre="";
		
		assertFalse(validador.esNombreValido(nombre));
	}
	
	@Test
	void esNombreValidoIncorrectoSoloSignotest() {
		String nombre=",";
		
		assertFalse(validador.esNombreValido(nombre));
	}
	
	@Test
	void esNombreValidoIncorrectoEspaciotest() {
		String nombre="";
		
		assertFalse(validador.esNombreValido(nombre));
	}
	
	@Test
	void esNombreConEspaciosValidoCorrectotest() {
		String nombre="santiago";
		
		assertTrue(validador.esNombreConEspaciosValido(nombre));
	}

	@Test
	void esNombreConEspaciosValidoSeparadotest() {
		String nombre="sant iago";
		
		assertTrue(validador.esNombreConEspaciosValido(nombre));
	}
	
	@Test
	void esNombreConEspaciosValidoIncorrectoCompuestotest() {
		String nombre="sant iago123";
		
		assertFalse(validador.esNombreConEspaciosValido(nombre));
	}
	
	@Test
	void esNombreConEspaciosValidoIncorrectoSimpletest() {
		String nombre="3";
		
		assertFalse(validador.esNombreConEspaciosValido(nombre));
	}
	
	@Test
	void esNombreConEspaciosValidoIncorrectoSignotest() {
		String nombre="santi:";
		
		assertFalse(validador.esNombreConEspaciosValido(nombre));
	}
	
	@Test
	void esNombreConEspaciosValidoIncorrectoVaciotest() {
		String nombre="";
		
		assertFalse(validador.esNombreConEspaciosValido(nombre));
	}
	
	@Test
	void esNombreConEspaciosValidoIncorrectoSoloSignotest() {
		String nombre=",";
		
		assertFalse(validador.esNombreConEspaciosValido(nombre));
	}
	
	@Test
	void esNombreConEspaciosValidoIncorrectoEspaciotest() {
		String nombre="";
		
		assertFalse(validador.esNombreConEspaciosValido(nombre));
	}
	
	@Test
	void esMailCorrectoBasico() {
		String mail="juan@hotmail.com";
		
		assertTrue(validador.esMail(mail));
	}
	
	@Test
	void esMailCorrectoDeNumeros() {
		String mail="123@123.ar";
		
		assertTrue(validador.esMail(mail));
	}
	
	@Test
	void esMailCorrectoMinimoIndispensable() {
		String mail="asddd1@adafrr.co";
		
		assertTrue(validador.esMail(mail));
	}
	@Test
	void esMailIncorrectoSinArroba() {
		String mail="juan123";
		
		assertFalse(validador.esMail(mail));
	}
	
	@Test
	void esMailIncorrectoVacio() {
		String mail="";
		
		assertFalse(validador.esMail(mail));
	}
	
	@Test
	void esMailIncorrectoVacioConEspacio() {
		String mail=" ";
		
		assertFalse(validador.esMail(mail));
	}
	
	@Test
	void esMailIncorrectoSinPuntoSinContinuacion() {
		String mail="santy@";
		
		assertFalse(validador.esMail(mail));
	}
	
	@Test
	void esMailIncorrectoSinPunto() {
		String mail="santy@etrg";
		
		assertFalse(validador.esMail(mail));
	}
	
	@Test
	void esMailIncorrectoTodoNumeros() {
		String mail="123@123.1";
		
		assertFalse(validador.esMail(mail));
	}
	
	@Test
	void esMailIncorrectoFinalNumeros() {
		String mail="santi@gmail.1";
		
		assertFalse(validador.esMail(mail));
	}
	
	@Test
	void esMailIncorrectoSinInicio() {
		String mail="@hotmail.ar";
		
		assertFalse(validador.esMail(mail));
	}
	
	@Test
	void esMailIncorrectoSinMitad() {
		String mail="san@.ar";
		
		assertFalse(validador.esMail(mail));
	}
	
	@Test
	void esMailIncorrectoSinFinal() {
		String mail="san@hotmail.";
		
		assertFalse(validador.esMail(mail));
	}
	
	@Test
	void esMailIncorrectoFinalMixto() {
		String mail="santy@hotmail.com123";
		
		assertFalse(validador.esMail(mail));
	}
	
	@Test
	void esMailIncorrectoMinimoIndispensableFinal() {
		String mail="asddd1@adafrr.c";
		
		assertFalse(validador.esMail(mail));
	}
	
	@Test
	void esMailIncorrectoMinimoIndispensable() {
		String mail="a@a.co";
		
		assertFalse(validador.esMail(mail));
	}
	
	@Test
	void esTelefonoCorrecto() {
		String numero="1537896016";
		
		assertTrue(validador.esTelefono(numero));
	}
	
	@Test
	void esTelefono9Digitos() {
		String numero="1537859601";
		
		assertTrue(validador.esTelefono(numero));
	}
	
	@Test
	void esTelefono8Digitos() {
		String numero="37896016";
		
		assertFalse(validador.esTelefono(numero));
	}
	
	@Test
	void esTelefonoTodosCeros() {
		String numero="0000000";
		
		assertFalse(validador.esTelefono(numero));
	}
	
	@Test
	void esTelefono1Digito() {
		String numero="1";
		
		assertFalse(validador.esTelefono(numero));
	}
	
	@Test
	void esTelefonoLetra() {
		String numero="a";
		
		assertFalse(validador.esTelefono(numero));
	}
	
	@Test
	void esTelefonoLetraNumero() {
		String numero="15231564a51";
		
		assertFalse(validador.esTelefono(numero));
	}
	
	@Test
	void esTelefono12Digito() {
		String numero="153789654787";
		
		assertFalse(validador.esTelefono(numero));
	}
	
	@Test
	void esTelefonoSignos() {
		String numero=":1234";
		
		assertFalse(validador.esTelefono(numero));
	}
	
	
	@Test
	void esPuntosValidooCorrecto() {
		String precio="24";
		
		assertTrue(validador.esPuntosValido(precio));
	}
	
	@Test
	void esPuntosValidoCorrectoMinimo() {
		String precio="2";
		
		assertTrue(validador.esPuntosValido(precio));
	}
	
	@Test
	void esPuntosValidoCorrectoMax() {
		String precio="2235";
		
		assertTrue(validador.esPuntosValido(precio));
	}
	
	@Test
	void esPuntosValidoConComa() {
		String precio="22,22";
		
		assertFalse(validador.esPuntosValido(precio));
	}
	
	@Test
	void esPuntosValidoCon5Digitos() {
		String precio="22435";
		
		assertFalse(validador.esPuntosValido(precio));
	}
	
	@Test
	void esPuntosValidoConLetra() {
		String precio="22a5";
		
		assertFalse(validador.esPuntosValido(precio));
	}
	
	@Test
	void esPuntosValidoConEspacio() {
		String precio="22 ";
		
		assertFalse(validador.esPuntosValido(precio));
	}
	
	
	@Test
	void esEstadoServicioValidoCorrectoActivo() {
		String estado="activo";
		
		assertTrue(validador.esEstadoServicioValido(estado));
	}
	
	@Test
	void esEstadoServicioValidoCorrectoInactivo() {
		String estado="inactivo";
		
		assertTrue(validador.esEstadoServicioValido(estado));
	}
	
	@Test
	void esEstadoServicioValidoInvalido() {
		String estado="ina";
		
		assertFalse(validador.esEstadoServicioValido(estado));
	}
	
	@Test
	void esEstadoServicioValidoIncorrectoActivoConEspacio() {
		String estado="activo ";
		
		assertFalse(validador.esEstadoServicioValido(estado));
	}
	
	@Test
	void esEstadoServicioValidoIncorrectoInactivoConEspacio() {
		String estado="inactivo ";
		
		assertFalse(validador.esEstadoServicioValido(estado));
	}
	
	@Test
	void esEstadoServicioValidoIncorrectoActivoConLetra() {
		String estado="activo1";
		
		assertFalse(validador.esEstadoServicioValido(estado));
	}
	
	@Test
	void esEstadoServicioValidoIncorrectoVacio() {
		String estado="";
		
		assertFalse(validador.esEstadoServicioValido(estado));
	}
	
	@Test
	void esEstadoServicioValidoIncorrectoConEspacio() {
		String estado=" ";
		
		assertFalse(validador.esEstadoServicioValido(estado));
	}
	
	@Test
	void esEstadoClienteValidoCorrectoActivo() {
		String estado="activo";
		
		assertTrue(validador.esEstadoClienteValido(estado));
	}
	
	@Test
	void esEstadoClienteValidoCorrectoInactivo() {
		String estado="inactivo";
		
		assertTrue(validador.esEstadoClienteValido(estado));
	}
	
	@Test
	void esEstadoClienteValidoCorrectoMoroso() {
		String estado="moroso";
		
		assertTrue(validador.esEstadoClienteValido(estado));
	}
	
	@Test
	void esEstadoClienteValidoCorrectoVip() {
		String estado="vip";
		
		assertTrue(validador.esEstadoClienteValido(estado));
	}
	
	@Test
	void esEstadoClienteValidoPrimeroMayus() {
		String estado="Vip";
		
		assertTrue(validador.esEstadoClienteValido(estado));
	}
	
	@Test
	void esEstadoClienteValidoAllMayus() {
		String estado="ACTIVO";
		
		assertTrue(validador.esEstadoClienteValido(estado));
	}
	
	@Test
	void esEstadoClienteValidoVacio() {
		String estado="";
		
		assertFalse(validador.esEstadoClienteValido(estado));
	}
	
	@Test
	void esEstadoClienteValidoEspacio() {
		String estado=" ";
		
		assertFalse(validador.esEstadoClienteValido(estado));
	}
	
	@Test
	void esEstadoClienteValidoIncorrecto() {
		String estado="perro";
		
		assertFalse(validador.esEstadoClienteValido(estado));
	}
	
	@Test
	void esEstadoClienteValidoIncorrectoPorPoco() {
		String estado="activo1";
		
		assertFalse(validador.esEstadoClienteValido(estado));
	}
	
	@Test
	void esEstadoClienteValidoIncorrectoPorEspacio() {
		String estado="activo ";
		
		assertFalse(validador.esEstadoClienteValido(estado));
	}
		
	@Test
	void esTipoMovimientoValidoCorrectoEgreso() {
		String mov="egreso";
		
		assertTrue(validador.esTipoMovimientoValido(mov));
	}
	
	@Test
	void esTipoMovimientoValidoCorrectoIngreso() {
		String mov="ingreso";
		
		assertTrue(validador.esTipoMovimientoValido(mov));
	}
	
	@Test
	void esTipoMovimientoValidoPrimerMayus() {
		String mov="Ingreso";
		
		assertTrue(validador.esTipoMovimientoValido(mov));
	}
	
	@Test
	void esTipoMovimientoValidoAllMayus() {
		String mov="EGRESO";
		
		assertTrue(validador.esTipoMovimientoValido(mov));
	}
	
	@Test
	void esTipoMovimientoValidoVacio() {
		String mov="";
		
		assertFalse(validador.esTipoMovimientoValido(mov));
	}
	
	@Test
	void esTipoMovimientoValidoEspacio() {
		String mov=" ";
		
		assertFalse(validador.esTipoMovimientoValido(mov));
	}
	
	@Test
	void esTipoMovimientoValidoIncorrecto() {
		String mov="perro";
		
		assertFalse(validador.esTipoMovimientoValido(mov));
	}
	
	@Test
	void esTipoMovimientoValidoIncorrectoPorPoco() {
		String mov="egreso_";
		
		assertFalse(validador.esTipoMovimientoValido(mov));
	}
	
	@Test
	void esTipoMovimientoValidoIncorrectoPorEspacio() {
		String mov="egreso ";
		
		assertFalse(validador.esTipoMovimientoValido(mov));
	}
	
	@Test
	void esPrecioValidoCorrectoEntero() {
		String precio="10";
		
		assertTrue(validador.esPrecioValido(precio));
	}
	
	@Test
	void esPrecioValidoCorrectoDecimal() {
		String precio="10.1";
		
		assertTrue(validador.esPrecioValido(precio));
	}
	
	@Test
	void esPrecioValidoCorrecto2DigitosDecimal() {
		String precio="10.15";
		
		assertTrue(validador.esPrecioValido(precio));
	}
	
	@Test
	void esPrecioValidoCorrectoConComa() {
		String precio="10,15";
		
		assertFalse(validador.esPrecioValido(precio));
	}
	
	@Test
	void esPrecioValidoCorrecto6Digitos() {
		String precio="101554.15";
		
		assertTrue(validador.esPrecioValido(precio));
	}
	
	@Test
	void esPrecioValidoCorrecto9Digitos() {
		String precio="101554123.15";
		
		assertTrue(validador.esPrecioValido(precio));
	}
	
	@Test
	void esPrecioValidoCorrecto10Digitos() {
		String precio="1015541234.15";
		
		assertTrue(validador.esPrecioValido(precio));
	}
	
	@Test
	void esPrecioValidoIncorrectoVacio() {
		String precio="";
		
		assertFalse(validador.esPrecioValido(precio));
	}
	
	@Test
	void esPrecioValidoIncorrectoDobleComa() {
		String precio="12.12.2";
		
		assertFalse(validador.esPrecioValido(precio));
	}
	
	@Test
	void esEstadoSucursalValidoCorrectoActivo() {
		String estado="activo";
		
		assertTrue(validador.esEstadoSucursalValido(estado));
	}
	
	@Test
	void esEstadoSucursalValidoCorrectoInactivo() {
		String estado="inactivo";
		
		assertTrue(validador.esEstadoSucursalValido(estado));
	}
	
	@Test
	void esEstadoSucursalValidoIncorrectoVacio() {
		String estado="";
		
		assertFalse(validador.esEstadoSucursalValido(estado));
	}
	
	@Test
	void esEstadoSucursalValidoIncorrectoConEspacioVacio() {
		String estado=" ";
		
		assertFalse(validador.esEstadoSucursalValido(estado));
	}
	
	@Test
	void esEstadoSucursalValidoIncorrectoConError() {
		String estado="activo,";
		
		assertFalse(validador.esEstadoSucursalValido(estado));
	}
	
	@Test
	void esEstadoSucursalValidoIncorrectoConEspacio() {
		String estado="activo ";
		
		assertFalse(validador.esEstadoSucursalValido(estado));
	}
	
	@Test
	void esEstadoSucursalValidoIncompleto() {
		String estado="acti";
		
		assertFalse(validador.esEstadoSucursalValido(estado));
	}
	
	@Test
	void esDescripcionValidaCorrecto() {
		String descripcion="hola lalla";
		
		assertTrue(validador.esDescripcionValida(descripcion));
	}
	
	@Test
	void esDescripcionValidaConNumeros() {
		String descripcion="hola lalla2353";
		
		assertTrue(validador.esDescripcionValida(descripcion));
	}
	
	@Test
	void esDescripcionValidaVacio() {
		String descripcion="";
		
		assertTrue(validador.esDescripcionValida(descripcion));
	}
	
	@Test
	void esDescripcionValidaVacioConEspacio() {
		String descripcion=" ";
		
		assertTrue(validador.esDescripcionValida(descripcion));
	}
	
	@Test
	void esDescripcionValidaCon79Palabras() {
		String descripcion="hola lalladfjnfjnfjnjnfkjnfkjnfdjnjdknfkjndkjfdnfkjnfkfmklfmlfkfklmfklmkflmlfflkmflkmfklmflkfnfkfkf";
		
		assertTrue(validador.esDescripcionValida(descripcion));
	}
	
	@Test
	void esDescripcionValidaCon80Palabras() {
		String descripcion="hola alladfjnyfjnfjnfjnfkjnfkjnfdjnjdknfkjndkjfdnfkjnfkfmklfmlfkfklmfklmkflmlfflkmflkmfklmflkfnfkfkf";
		
		assertTrue(validador.esDescripcionValida(descripcion));
	}
	
	@Test
	void esDescripcionValidaCon81Palabras() {
		String descripcion="hola lalladfjnfjnffgjnjnfkjnfkjnfdjnjdknfkjndkjfdnfkjnfkfmklfmlfkfklmfklmkflmlfflkmflkmfklmflkfnfkfkf";
		
		assertFalse(validador.esDescripcionValida(descripcion));
	}
	
	@Test
	void esNumeroSucursalValidoCon81Palabras() {
		String descripcion="hola lalladfjnfjnffgjnjnfkjnfkjnfdjnjdknfkjndkjfdnfkjnfkfmklfmlfkfklmfklmkflmlfflkmflkmfklmflkfnfkfkf";
		
		assertFalse(validador.esPrecioValido(descripcion));
	}
	
	@Test
	void esNumeroSucursalValidoCorrectoEntero() {
		String sucursal="10";
		
		assertTrue(validador.esNumeroSucursalValido(sucursal));
	}
	
	@Test
	void esNumeroSucursalValidoCorrectoDecimal() {
		String sucursal="10.1";
		
		assertTrue(validador.esNumeroSucursalValido(sucursal));
	}
	
	@Test
	void esNumeroSucursalValidoCorrecto2DigitosDecimal() {
		String sucursal="10.15";
		
		assertTrue(validador.esNumeroSucursalValido(sucursal));
	}
	
	@Test
	void esNumeroSucursalValidoCorrectoConComa() {
		String sucursal="10,15";
		
		assertFalse(validador.esNumeroSucursalValido(sucursal));
	}
	
	@Test
	void esNumeroSucursalValidoCorrecto6Digitos() {
		String sucursal="101554,15";
		
		assertFalse(validador.esNumeroSucursalValido(sucursal));
	}
	
	@Test
	void esNumeroSucursalValidoCorrecto9Digitos() {
		String sucursal="101554123,15";
		
		assertFalse(validador.esNumeroSucursalValido(sucursal));
	}
	
	@Test
	void esNumeroSucursalValidoIncorrecto10Digitos() {
		String sucursal="1015541234,15";
		
		assertFalse(validador.esNumeroSucursalValido(sucursal));
	}
	
	@Test
	void esNumeroSucursalValidoIncorrectoVacio() {
		String sucursal="";
		
		assertFalse(validador.esNumeroSucursalValido(sucursal));
	}
	
	@Test
	void esNumeroSucursalValidoIncorrectoDobleComa() {
		String sucursal="12,12,2";
		
		assertFalse(validador.esNumeroSucursalValido(sucursal));
	}
	
	
	@Test
	void esTipoCambioValidoCorrecto() {
		String cambio="efectivo";
		
		assertTrue(validador.esTipoCambioValido(cambio));
	}
	
	@Test
	void esTipoCambioValidoIncorrecto() {
		String cambio="puntos1";
		
		assertFalse(validador.esTipoCambioValido(cambio));
	}
	
	@Test
	void esTipoCambioValidoVacio() {
		String cambio="";
		
		assertFalse(validador.esTipoCambioValido(cambio));
	}
	
	@Test
	void esTipoCambioValidoVacioEspacio() {
		String cambio="v";
		
		assertFalse(validador.esTipoCambioValido(cambio));
	}
	
	@Test
	void esTipoCambioValidoFiar() {
		String cambio="fiar";
		
		assertFalse(validador.esTipoCambioValido(cambio));
	}
}