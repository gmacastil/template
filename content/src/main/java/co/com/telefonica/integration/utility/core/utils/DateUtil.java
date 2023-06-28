package co.com.telefonica.integration.utility.core.utils;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DateUtil {

	private DateUtil() {
	}
	
	/**
	 * Metodo para calcular la diferencia de dos fechas en milisegundos
	 * @param begin Fecha de Inicio
	 * @param end	Fecha de Fin
	 * @return		Texto con la diferencia de fechas en milisegundos
	 */
	public static String calculateTime(Date begin, Date end) {
		Double before = Double.valueOf(String.valueOf(begin.getTime()));
		Double after = Double.valueOf(String.valueOf(end.getTime()));
		Double result = (after - before) / 1000;
		return String.valueOf(result).concat("s");
	}
}