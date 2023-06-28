package co.com.telefonica.integration.utility.core.utils;

import co.com.telefonica.integration.utility.core.exception.CauseException;
import co.com.telefonica.integration.utility.core.exception.ValidationException;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.GregorianCalendar;

import static co.com.telefonica.integration.utility.core.constants.Consts.*;
import static co.com.telefonica.integration.utility.core.utils.ValidateUtil.validateString;


@Slf4j
@NoArgsConstructor
@Component
public class TransformationUtil {


    public static final String PATTERN_DATE = "dd-mm-yyyy hh:mm:ss";
    public static final Integer EXPONENT = -2;

    public static XMLGregorianCalendar stringToXMLGregorianCalendar(String dateSource, String pattern) throws ParseException, DatatypeConfigurationException {

        DateFormat format = new SimpleDateFormat(pattern);
        Date date = format.parse(dateSource);

        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);

        return DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
    }

    public static String xmlGregorianCalendarToString(XMLGregorianCalendar dateSource, String pattern) {
        return ObjectUtils.isEmpty(pattern) ? dateSource.toString() : new SimpleDateFormat(pattern)
                .format(dateSource.toGregorianCalendar().getTime());
    }

    public static String monetaryTypeToString(long value, Integer exponent) {

        double monetary = value * Math.pow(10, exponent) * 100;

        return String.valueOf(monetary);

    }

    public long stringToMonetaryType(Integer value) {
        return value * 100;
    }

    public String durationToString(Duration duration) {

        int hours = (duration.getYears() * 365 * 24) + (duration.getMonths() * 30 * 24) + (duration.getDays() * 24) + duration.getHours();

        return String.format(
                hours >= 10 ? "%d:%02d:%02d" : "%02d:%02d:%02d",
                hours,
                duration.getMinutes(),
                duration.getSeconds());
    }

    public static LocalDate transformationDateToStringTest(String value, String pattern) throws ValidationException {
        LocalDate dateTime = null;
        try {
            if (pattern.equals("yy-MM-dd")) {
                if (value != null && !"null".equals(value))
                    dateTime = LocalDate.parse(value);
            } else
                throw new ValidationException(ERROR_MISSING_PARAMS, new CauseException(pattern + MESSAGE_INVALID_FORMAT));
        } catch (DateTimeParseException d) {
            throw new ValidationException(ERROR_MISSING_PARAMS, new CauseException(pattern + MESSAGE_INVALID_FORMAT));
        }

        return dateTime;
    }


    /**
     * Método que valida un atributo de tipo long
     *
     * @param value      String con el valor numérico a validar
     * @param headerName String con el nombre de la variable
     * @throws ValidationException
     */
    public static long validateLong(String value, String headerName) throws ValidationException {
        try {
            validateString(value, headerName);
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            throw new ValidationException(ERROR_MISSING_PARAMS, new CauseException(MESSAGE_HEADER + headerName + MESSAGE_INVALID_FORMAT));
        }
    }

    /**
     * Método que valida un atributo de tipo double
     *
     * @param value      String con el valor numérico a validar
     * @param headerName String con el nombre de la variable
     * @throws ValidationException
     */
    public static double validateDouble(String value, String headerName) throws ValidationException {
        try {
            validateString(value, headerName);
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            throw new ValidationException(ERROR_MISSING_PARAMS, new CauseException(MESSAGE_HEADER + headerName + MESSAGE_INVALID_FORMAT));
        }
    }


}
