package user;

import exceptions.BookingMgtException;
import lombok.RequiredArgsConstructor;
import model.AppUser;
import model.Validator;
import model.VerificationToken;
import model.dto.request.RegistrationRequest;
import model.dto.response.RegistrationResponse;
import model.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import verification.VerificationTokenService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements  UserService{

    private final VerificationTokenService verificationTokenService;

    private final UserRepository userRepository;

    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper mapper = new ModelMapper();

    @Override
    public AppUser findUserByEmail(String email) throws BookingMgtException {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new BookingMgtException("Invalid username or password")
        );
    }

    @Override
    public void saveUser(AppUser user) {

    }

    @Override
    public RegistrationResponse register(RegistrationRequest registrationRequest) throws BookingMgtException {
        validateRegistrationRequest(registrationRequest);
        registrationRequest.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        AppUser appUser = mapper.map(registrationRequest, AppUser.class);
        VerificationToken verificationToken = verificationTokenService.createRegistrationToken(appUser.getEmail());
        userRepository.save(appUser);
        emailService.sendRegistrationEmail(appUser, verificationToken.getToken());
        return mapper.map(appUser, RegistrationResponse.class);
    }

    @Override
    public boolean existByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
    private void validateRegistrationRequest(RegistrationRequest registrationRequest) throws BookingMgtException {
//        if (registrationRequest.getRole().equals(Role.ADMIN)) throw new LearningMgtException("cannot sign up as admin");
//        if (registrationRequest.getRole().equals(Role.INSTRUCTOR) && registrationRequest.getInstructorVerificationCode() == null) {
//            throw new BookingMgtException("instructor verification code required");
//        }
//        Validator.ensureValidPhone(registrationRequest.getPhoneNumber());
        Validator.ensureValidEmail(registrationRequest.getEmail(), "email");
//        Validator.ensureValidEmail(registrationRequest.getSecondaryEmailAddress(), "secondary email");
        Validator.ensureValidPassword(registrationRequest.getPassword());
//        Validator.ensureBothPasswordMatches(registrationRequest.getPassword(), registrationRequest.getConfirmPassword());
        if (userRepository.existsByEmail(registrationRequest.getEmail())) {
            throw new BookingMgtException("email already exists");
        }

    }

    @Override
    public List<AppUser> findAllDisabledUsers() {
        Page<AppUser> users = userRepository.findByEnabledFalse(Pageable.ofSize(100));
        return users.getContent();
    }

    @Override
    public void disableUser(AppUser appUser) {
        appUser.setEnabled(false);
        appUser.setTimeLocked(LocalDateTime.now());
        saveUser(appUser);

    }

    @Override
    public void registerFailedLogin(AppUser appUser) {

    }
}
