package animal.crud.machadolabtest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest {
    private String email;
    private String name;
    private String password;

    public SignupRequest(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }
}
