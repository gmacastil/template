package co.com.telefonica.integration.utility.core.exception.handler;

import org.apache.camel.Handler;
import org.springframework.stereotype.Component;

@Component
public class LoggerHandler {

	/**
	 * Metodo que permite construir un log de los headers de entrada a la ruta camel
	 * @param service	Nombre del Servicio
	 * @param message	Mensaje para el Log 
	 * @return			Cadena Final del Log
	 */
	@Handler
	public String buildLog(String service, String message) {
		StringBuilder messageBuilder = new StringBuilder();
		messageBuilder.append("Service: ");
		messageBuilder.append(service);
		messageBuilder.append(" | System: ${headers.system}");
		messageBuilder.append(" | Originator: ${headers.originator}");
		messageBuilder.append(" | Operation: ${headers.operation}");
		messageBuilder.append(" | ExecId: ${headers.execId}");
		messageBuilder.append(" | MsgId: ${headers.msgId}");
		messageBuilder.append(" | Timestamp: ${headers.timestamp}");
		messageBuilder.append(" | MsgType: ${headers.msgType}");
		messageBuilder.append(" | VarArg: ${headers.varArg}");
		messageBuilder.append(" | Message: ");
		messageBuilder.append(message);
		return messageBuilder.toString();
	}
}
