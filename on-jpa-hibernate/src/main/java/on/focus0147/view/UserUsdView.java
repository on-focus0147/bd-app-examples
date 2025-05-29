package on.focus0147.view;

import java.io.Serializable;

public class UserUsdView  implements Serializable {
    private static final long serialVersionUID = 6L;
    private String email;

    public UserUsdView(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
