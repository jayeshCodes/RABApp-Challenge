package animal.crud.machadolabtest.mapper;

import animal.crud.machadolabtest.dto.SignupRequest;
import animal.crud.machadolabtest.entity.AppUser;

public class SignUpMapper {
    // public static SignupRequest mapToSignupRequest(AppUser appUser){
    //     return new SignupRequest(
    //         appUser.getId(),
    //         appUser.getEmail(),
    //         appUser.getName(),
    //         appUser.getPassword()
    //     );
    // }

    public static AppUser mapToAppUser(SignupRequest signupRequest){
        return new AppUser(
            signupRequest.getName(),
            signupRequest.getEmail(),
            signupRequest.getPassword()
        );
    }
}
