package filters;

import entities.Role;
import utils.MyUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

public class SecurityUtils {
    // Проверить требует ли данный 'request' входа в систему или нет.
    public static boolean isSecurityPage(HttpServletRequest request) {
        String urlPattern = UrlPatternUtils.getUrlPattern(request);
        Set<Role> roles = SecurityConfig.getAllAppRoles();
        for (Role role : roles) {
            List<String> urlPatterns = SecurityConfig.getUrlPatternsForRole(role);
            if (urlPatterns != null && urlPatterns.contains(urlPattern)) {
                return true;
            }
        }
        return false;
    }

    // Проверить имеет ли данный 'request' подходящую роль?
    public static boolean hasPermission(HttpServletRequest request) {
        String urlPattern = UrlPatternUtils.getUrlPattern(request);
        Role role = MyUtils.getLoginedUser(request.getSession()).getRole();
        List<String> urlPatterns = SecurityConfig.getUrlPatternsForRole(role);
        return urlPatterns != null && urlPatterns.contains(urlPattern);
    }
}