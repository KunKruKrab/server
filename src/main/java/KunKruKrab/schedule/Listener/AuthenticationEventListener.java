package KunKruKrab.schedule.Listener;

import KunKruKrab.schedule.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationEventListener {

    Logger logger =
            LoggerFactory.getLogger(AuthenticationEventListener.class);

    @Autowired
    private AuthService authService;

    @EventListener
    public void authenticationFailed(AuthenticationFailureBadCredentialsEvent event) {

        String email = (String) event.getAuthentication().getPrincipal();

        if (authService.isUsernameAvailable(email))
            logger.warn("Failed login attempt [incorrect Email or Password]");
        else
            logger.warn("Failed login attempt [incorrect Email or Password]");
    }
}

