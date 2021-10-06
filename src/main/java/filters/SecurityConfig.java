package filters;

import entities.Role;

import java.util.*;

public class SecurityConfig {

    // String: Role
    // List<String>: urlPatterns.
    private static final Map<Role, List<String>> mapConfig = new HashMap<Role, List<String>>();

    static {
        init();
    }

    private static void init() {

        // Конфигурация для роли "USER".
        List<String> urlPatterns1 = new ArrayList<String>();

        urlPatterns1.add("/showMyTickets");
        urlPatterns1.add("/findFlight");

        mapConfig.put(Role.USER, urlPatterns1);

        // Конфигурация для роли "ADMIN".
        List<String> urlPatterns2 = new ArrayList<String>();

        urlPatterns2.add("/makeAdmin");
        urlPatterns2.add("/createPlane");
        urlPatterns2.add("/createFlight");

        mapConfig.put(Role.ADMIN, urlPatterns2);
    }

    public static Set<Role> getAllAppRoles() {
        return mapConfig.keySet();
    }

    public static List<String> getUrlPatternsForRole(Role role) {
        return mapConfig.get(role);
    }

}