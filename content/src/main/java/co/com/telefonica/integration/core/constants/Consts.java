package co.com.telefonica.integration.core.constants;

/**
 * The type Customer detail const.
 */
public class Consts {

    /**
     * Route customer detail
     */
    public static final String ROUTE = "direct-vm:${{values.name}}";
    public static final String SERVICE_PRODUCER = "${{values.name}}-producer";

    /**
     * Messages for the service log
     */
    public static final String SERVICE = "${{values.name}}";
}
