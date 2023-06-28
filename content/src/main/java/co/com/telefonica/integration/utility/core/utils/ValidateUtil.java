package co.com.telefonica.integration.utility.core.utils;

import co.com.telefonica.integration.utility.core.exception.CauseException;
import co.com.telefonica.integration.utility.core.exception.ValidationException;
import org.springframework.stereotype.Component;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Objects;

import static co.com.telefonica.integration.utility.core.constants.Consts.*;


@Component
public class ValidateUtil {

    /**
     * Metodo que valida la obligatoriedad de un header
     *
     * @param value      the value
     * @param headerName the name
     * @throws
     */
    public static void validateString(String value, String headerName) throws ValidationException {
        if (value == null || value.isEmpty() || value.equalsIgnoreCase("null")) {
            throw new ValidationException(ERROR_MISSING_PARAMS, new CauseException(MESSAGE_HEADER + headerName + MESSAGE_INVALID_FORMAT));
        }
    }

    /**
     * Metodo que valida un header tipo entero
     *
     * @param value      the value
     * @param headerName the name
     * @throws ValidationException
     */
    public static void validateInteger(String value, String headerName) throws ValidationException {
        try {
            validateString(value,headerName);
            Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new ValidationException(ERROR_MISSING_PARAMS, new CauseException(MESSAGE_HEADER + headerName + MESSAGE_INVALID_FORMAT));
        }
    }

    /**
     * Metodo que valida un header tipo Date
     *
     * @param headerName the name
     * @param value      the value
     * @throws ValidationException
     */
    public static void validateDate(String headerName, String value) throws ValidationException {
        try {
            validateString(value,headerName);
            LocalDateTime.parse(value.trim(), DateTimeFormatter.ISO_DATE_TIME);
        } catch (DateTimeParseException d) {
            throw new ValidationException(ERROR_MISSING_PARAMS, new CauseException(MESSAGE_HEADER + headerName + MESSAGE_INVALID_FORMAT));
        }
    }

    public static int validChoice(Object request) throws ValidationException {
        int countNotNull = 0;
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(request.getClass());
            for (PropertyDescriptor propertyDesc : beanInfo.getPropertyDescriptors()) {
                String propertyName = propertyDesc.getName();
                if (!propertyName.equals("class") && propertyDesc.getReadMethod().invoke(request) != null && !validateType(propertyDesc.getPropertyType().toString())) {
                    countNotNull++;
                }
            }
        } catch (InvocationTargetException | IllegalArgumentException | IllegalAccessException
                | IntrospectionException e) {
            throw new ValidationException("los objetos tiene un formato inválido");
        }
        return countNotNull;
    }

    private static boolean validateType(String type) {
        return !type.contains("java");
    }

    public static <T> int validChoice2(Object request) throws Exception {
        int nonNullCount = 0;

        for (Field field : request.getClass().getDeclaredFields())
        {

            field.setAccessible(true);
            System.out.println(String.valueOf(field.get(request).getClass().getName()));
            Object object = Class.forName(String.valueOf(field.get(request).getClass())).getConstructor().newInstance();

            if (field.get(request) != null || field.get(request).equals(object))
            {
                System.out.println(field.get(request));
                nonNullCount++;
            }
        }
        return nonNullCount;

    }

    /**
     * Metodo que valida si un header tipo Date es correcto y puede ser parseable
     *
     * @param headerName the name
     * @param value      the value
     * @throws ValidationException
     */
    public static void validateDateParam(String headerName, String value) throws ValidationException {
        try {
            new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(value);
        } catch (ParseException d) {
            throw new ValidationException(ERROR_MISSING_PARAMS, new CauseException(MESSAGE_HEADER + headerName + MESSAGE_INVALID_FORMAT));
        }
    }


    public static long countNonNullFields(Object o) {
        return Arrays.stream(o.getClass().getDeclaredFields())
                .map(field -> {
                    try {
                        field.setAccessible(true);
                        return field.get(o);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    throw new IllegalArgumentException();
                })
                .filter(Objects::nonNull)
                .count();
    }

}