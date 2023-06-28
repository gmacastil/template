package co.com.telefonica.integration.utility.core.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Setter
@Getter
@Component
@ConfigurationProperties(value = "service")
public class ValueMapperUtils {

    private Map<String, String> dvm;

    private Map<String, List<Map<String, String>>> dvmMapInformation;


    @PostConstruct
    public void setUpErrorMap() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        dvmMapInformation = new HashMap<>();
        for (String key : dvm.keySet()) {
            String dvmInfo = dvm.get(key);
            List<Map<String, String>> value = mapper.readValue(dvmInfo, new TypeReference<List<Map<String, String>>>() {
            });
            dvmMapInformation.put(key, value);
        }
    }

    public String getDVM(String key, String file) {
        List<Map<String, String>> dvmValue = dvmMapInformation.get(file);

        String filter = null;

        for (Map<String, String> dvmFilter : dvmValue) {
            if (dvmFilter.containsKey(key)) {
                filter = dvmFilter.get(key);
            }
        }
        return filter;
    }

}
