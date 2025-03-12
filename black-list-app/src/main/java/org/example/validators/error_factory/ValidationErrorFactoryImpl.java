package org.example.validators.error_factory;

import org.example.validators.ValidationError;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Properties;

@Component
@Scope("singleton")
public class ValidationErrorFactoryImpl implements ValidationErrorFactory{
    private Properties properties;

    public ValidationErrorFactoryImpl() throws IOException{
        Resource resource = new ClassPathResource("errorCodes.properties");
        properties = PropertiesLoaderUtils.loadProperties(resource);
    }

    @Override
    public ValidationError buildError(String errorCode) {
        return new ValidationError(errorCode, properties.getProperty(errorCode));
    }
}
