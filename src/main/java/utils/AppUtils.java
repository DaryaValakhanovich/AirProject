package utils;

import entities.Account;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AppUtils {

    private static final String ATT_NAME_USER_NAME = "ATTRIBUTE_FOR_STORE_USER_NAME_IN_COOKIE";

    // Сохранить информацию пользователя, который вошел в систему (login) в Session.
    public static void storeLoginedUser(HttpSession session, Account loginedUser) {
        // В JSP можно получить доступ через ${loginedUser}
        session.setAttribute("loginedUser", loginedUser);
    }

    // Получить информацию пользователя, сохраненная в Session.
    public static Account getLoginedUser(HttpSession session) {
        return (Account) session.getAttribute("loginedUser");
    }

    // Сохранить информацию пользователя в Cookie.
    public static void storeUserCookie(HttpServletResponse response, Account user) {
        System.out.println("Store user cookie");
        Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, user.getEmail());
        // 1 день (Конвертированный в секунды)
        cookieUserName.setMaxAge(24 * 60 * 60);
        response.addCookie(cookieUserName);
    }

    // Удалить Cookie пользователя
    public static void deleteUserCookie(HttpServletResponse response) {
        Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, null);
        // 0 секунд. (Данный Cookie будет сразу недействителен)
        cookieUserName.setMaxAge(0);
        response.addCookie(cookieUserName);
    }
}