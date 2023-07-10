package user;

import exceptions.BookingMgtException;
import model.AppUser;
import model.dto.request.RegistrationRequest;
import model.dto.response.RegistrationResponse;

import java.util.List;

public interface UserService {
    AppUser findUserByEmail(String email) throws BookingMgtException;

    void saveUser(AppUser user);

    RegistrationResponse register(RegistrationRequest registrationRequest) throws BookingMgtException;

    boolean existByEmail(String email);

    List<AppUser> findAllDisabledUsers();

    void disableUser(AppUser appUser);

    void registerFailedLogin(AppUser appUser);
}
