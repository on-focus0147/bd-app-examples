package on.focus0147.dao.pojo;

public record User(Integer id,
                   String name,
                   String email,
                   String hashedPassword) {
}
