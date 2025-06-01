package on.focus0147.services.impl;

import jakarta.annotation.PostConstruct;
import on.focus0147.services.PasswordService;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service("passwordService")
@PropertySource("classpath:encryption/pass.properties")
public class PasswordServiceImpl implements PasswordService {

    @Value("${pass.word}")
    private  String word;

    private static final StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();

    @PostConstruct
    public void init() {
        encryptor.setPassword(word);
    }
    

    @Override
    public String encrypt(String password) {
        return encryptor.encrypt(password);
    }

    @Override
    public String decrypt(String encryptedPassword) {
        return encryptor.decrypt(encryptedPassword);
    }

    @Override
    public boolean checkEquals(String password, String encryptedPassword) {
        return password.equals(decrypt(encryptedPassword));
    }
}
