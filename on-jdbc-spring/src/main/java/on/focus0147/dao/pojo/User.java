package on.focus0147.dao.pojo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public record User(Integer id,
                   String name,
                   String email,
                   String hashedPassword,
                   List<Transaction> transactions) {

    public Map<String, Object> toMap(){
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("ID", id);
        parameters.put("NAME", name);
        parameters.put("EMAIL", email);
        parameters.put("HASHED_PASSWORD", hashedPassword);
        return parameters;
    }
}
