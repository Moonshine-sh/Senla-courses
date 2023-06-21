package by.ginel.event;

import by.ginel.dto.PersonDto;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

public class OnRegistrationCompleteEvent extends ApplicationEvent {

    private final String appUrl;
    private final Locale locale;
    private final PersonDto user;

    public OnRegistrationCompleteEvent(PersonDto user, Locale locale, String appUrl) {
        super(user);

        this.appUrl = appUrl;
        this.locale = locale;
        this.user = user;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public Locale getLocale() {
        return locale;
    }

    public PersonDto getUser() {
        return user;
    }
}
