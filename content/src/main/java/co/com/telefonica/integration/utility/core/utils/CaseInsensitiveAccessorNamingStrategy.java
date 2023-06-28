package co.com.telefonica.integration.utility.core.utils;

import org.mapstruct.ap.spi.DefaultAccessorNamingStrategy;

import javax.lang.model.element.ExecutableElement;
import java.util.Locale;

public class CaseInsensitiveAccessorNamingStrategy extends DefaultAccessorNamingStrategy {

  @Override
  public String getPropertyName(ExecutableElement getterOrSetterMethod) {
    return super.getPropertyName( getterOrSetterMethod ).toLowerCase( Locale.ROOT );
  }

  @Override
  public String getElementName(ExecutableElement adderMethod) {
    return super.getElementName( adderMethod ).toLowerCase( Locale.ROOT );
  }
}
