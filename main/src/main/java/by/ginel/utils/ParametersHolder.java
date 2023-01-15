package by.ginel.utils;

import by.ginel.Component;
import by.ginel.Value;

@Component
public class ParametersHolder {
    @Value("my.param.db")
    private String someText;

    public String getSomeText() {
        return someText;
    }
}
