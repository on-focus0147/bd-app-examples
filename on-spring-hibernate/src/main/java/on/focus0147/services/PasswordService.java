package on.focus0147.services;

public interface PasswordService {
    String encrypt(String password);
    String decrypt(String encryptedPassword);
    boolean checkEquals(String password, String encryptedPassword);
}
