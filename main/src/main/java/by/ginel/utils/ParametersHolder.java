package by.ginel.utils;

import by.ginel.annotation.Component;
import by.ginel.annotation.Value;

@Component
public class ParametersHolder {
    @Value("my.param.db")
    private String someText;

    public String getSomeText() {
        return someText;
    }
}
