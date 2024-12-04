package maville.equipe27.controllers;

import maville.equipe27.enums.RoleChoices;
import maville.equipe27.helpers.AuthHelper;
import maville.equipe27.helpers.UserDataStore;
import maville.equipe27.models.Resident;
import maville.equipe27.models.User;
import maville.equipe27.views.AuthView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.beans.PropertyChangeSupport;
import java.time.LocalDate;

import static org.mockito.Mockito.*;
// test
class AuthControllerTest {
    @Mock
    private UserDataStore userDataStore; // Mock for UserDataStore

    @Mock
    private AuthView view; // Mock for View

    @Mock
    private PropertyChangeSupport support; // Mock for PropertyChangeSupport

    @Mock
    private AuthHelper authHelper; // AuthHelper will use the mocked UserDataStore

    private AuthController authController; // Class containing the `register` method

    private AutoCloseable mocks;

    @BeforeEach
    void setUp() {
        mocks = MockitoAnnotations.openMocks(this);
        authController = new AuthController(view, authHelper);
    }

    @Test
    @DisplayName("Register with valid user object")
    void attemptRegister_with_valid_user() {
        User validUser = new Resident("mail@mail.com",
                "some-password",
                RoleChoices.RÉSIDENT,
                "Firstname",
                "Lastname",
                LocalDate.of(1990, 10, 10),
                "344-444-4444",
                "3,Rue des merveilles,8,H3Y 2S5");

        when(authHelper.register(validUser)).thenReturn(true);

        // sot
        authController.attemptRegister(validUser);

        verify(view).registerSuccess(validUser.getFirstname());
        verify(view, never()).registerFailure();
    }

    @Test
    void attemptRegister_with_invalid_user() {
        User invalidUser = new Resident("mail@mail.com",
                "some-password",
                RoleChoices.RÉSIDENT,
                "Firstname",
                "Lastname",
                LocalDate.of(1990, 10, 10),
                "344-444-4444",
                "3,Rue des merveilles,8,H3Y 2S5");

        when(authHelper.register(invalidUser)).thenReturn(false);

        authController.attemptRegister(invalidUser);

        verify(view).registerFailure();
        verify(view, never()).registerSuccess(anyString());
    }

    @Test
    void login_with_valid_email_password() {
        String email = "validuser@mail.com";
        String password = "validpassword";
        User validUser = new Resident(
                email,
                password,
                RoleChoices.RÉSIDENT,
                "Valid",
                "User",
                LocalDate.of(1990, 10, 10),
                "1234567890",
                "3,Rue des merveilles,8,H3Y 2S5"
        );

        when(authHelper.login(email, password)).thenReturn(validUser);

        authController.attemptLogin(email, password);

        verify(view).loginSuccess("Valid");
        verify(view, never()).loginFailure();
    }

    @Test
    void login_with_invalid_credentials() {
        String email = "invalid@mail.com";
        String password = "invalidpassword";

        when(authHelper.login(email, password)).thenReturn(null);

        authController.attemptLogin(email, password);

        verify(view).loginFailure();
        verify(view, never()).loginSuccess(anyString());
    }
}