package ru.otus.homework.hw24.web.services;

import org.eclipse.jetty.security.AbstractLoginService;
import org.eclipse.jetty.util.security.Password;
import ru.otus.homework.hw24.data.core.service.DBServiceUser;

public class LoginServiceByDBServiceUser extends AbstractLoginService {

    private final DBServiceUser userServices;

    public LoginServiceByDBServiceUser(DBServiceUser userServices) {
        if (userServices == null) {
            throw new IllegalArgumentException("userServices mustn't be null.");
        }
        this.userServices = userServices;
    }

    @Override
    protected String[] loadRoleInfo(UserPrincipal userPrincipal) {
        return "admin".equals(userPrincipal.getName()) ? new String[] {"admin", "user"} : new String[] {"user"};
    }

    @Override
    protected UserPrincipal loadUserInfo(String login) {
        return userServices.findByLogin(login)
                .map(u -> new UserPrincipal(u.getLogin(), new Password(u.getPassword())))
                .orElse(null);
    }
}
