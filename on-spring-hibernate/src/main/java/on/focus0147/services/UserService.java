package on.focus0147.services;

public interface UserService {

    /**
     * @param login логин пользователя (email)
     * @param password пароль пользователя (не зашифрованный)
     * @return Пароль пользователя ли?
     */
    boolean checkPassword(String login, String password);
}
