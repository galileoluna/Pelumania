package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import dto.CitaDTO;
import modelo.Sistema;

public class TimerEstadosCitas {
	 Timer timer;
	 TimerTask task;
	 Sistema sistema;
	 
	public TimerEstadosCitas( Sistema sistema ){
		this.sistema = sistema;
		this.timer = new Timer();
		this.task =	inicializarTask(sistema);
	}
	
	/////////////////////////////////////////////////////////////////////
	
		//CADA 10 MIN SE VAN A ACTUALIZAR LAS CITAS DEL DIA//
	
	/////////////////////////////////////////////////////////////////////
	
	private TimerTask inicializarTask(Sistema sistema) {
		task = new TimerTask() {
			@Override
	        public void run() {
				
				LocalDate localDate = LocalDate.now();
				String hoy = DateTimeFormatter.ofPattern("yyyy/MM/dd").format(localDate);
				List<CitaDTO> listaCitas = sistema.getCitasPorDia(hoy);
	        
				for (CitaDTO cita : listaCitas) {
//					if(cita.estaEnCurso()) {
//						cita.setEstado("En curso"); //estado a modo de ejemplo
//						sistema.editarCita(cita);
//						System.out.println("--------------------------------------------------------------");
//						System.out.println(cita);
//						System.out.println(cita.getEstado());
//					}
					
						// falta agregar la logica de los demas casos	
				}
			
			}
		};
		return task ;
	}

	public void iniciar() {
		this.timer.schedule(this.task, 0, 1000);
		
	}

}
