package com.whoslast.authorization;

import com.whoslast.ErrorCodes;
import com.whoslast.controllers.UserRepository;
import com.whoslast.entities.User;

/**
 * Sign-up manager
 */
public class SignUpManager extends AuthManager {
    /**
     * Data provided by user to sign up
     */
    public static class UserSignUpData {
        private String name;
        private String email;
        private String password;

        public UserSignUpData(String name, String email, String password) {
            this.name = name;
            this.email = email;
            this.password = password;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }

        /**
         * Is there any empty fields (empty strings)?
         * @return True -- there are empty strings, False -- there aren't any
         */
        public boolean hasEmptyFields() {
            return this.email.isEmpty() || this.name.isEmpty() || this.password.isEmpty();
        }
    }

    private static final String msgSignUpSuccess = "Successful sign up";
    private static final String msgSignUpErrorEmptyFields = "Some of provided fields are empty";
    private static final String msgSignUpErrorUserExists = "A user with provided email already exists";

    private int initialPartyId; //Value used while signing up to make user unbounded from any groups (but he has to do it after) ("dummy value")

    public SignUpManager(UserRepository userDatabase) {
        super(userDatabase);
        this.initialPartyId = 0;
    }

    public SignUpManager(UserRepository userDatabase, int initialPartyId) {
        super(userDatabase);
        this.initialPartyId = initialPartyId;
    }

    /**
     * Build credentials from user-provided data
     * @param signUpData Data provided by user
     * @return Credentials
     * @throws CredentialsManager.HashEnginePerformException Environment fail while building credentials
     * @throws CredentialsManager.BadPasswordException Provided password somehow is bad
     */
    private User buildDatabaseUser(UserSignUpData signUpData) throws CredentialsManager.HashEnginePerformException, CredentialsManager.BadPasswordException {
        CredentialsManager.Credentials credentials = CredentialsManager.buildCredentials(signUpData.getPassword());
        User newDatabaseUser = new User();

        newDatabaseUser.setName(signUpData.getName());
        newDatabaseUser.setEmail(signUpData.getEmail());
        newDatabaseUser.setGroupId(initialPartyId);
        newDatabaseUser.setSalt(credentials.getSalt());
        newDatabaseUser.setHash(credentials.getHash());
        newDatabaseUser.setHashsize(credentials.getHashSize());

        return  newDatabaseUser;
    }

    /**
     * Sign-up procedure
     * @param signUpData Data provided by user
     * @return Response indicating status of operation
     */
    public AuthResponse signUp(UserSignUpData signUpData) {
        AuthResponse response;
        try {
            if (signUpData.hasEmptyFields()) {
                response = new AuthResponse(msgSignUpErrorEmptyFields, AuthResponse.Status.FAIL_USER, ErrorCodes.Authorization.EMPTY_FIELDS);
            } else {
                User foundUser = userDatabase.findUserByEmail(signUpData.getEmail());
                if (foundUser == null) {
                    User newDatabaseUser = buildDatabaseUser(signUpData);
                    userDatabase.save(newDatabaseUser);
                    response = new AuthResponse(msgSignUpSuccess, AuthResponse.Status.SUCCESS, ErrorCodes.NO_ERROR);
                } else {
                    response = new AuthResponse(msgSignUpErrorUserExists, AuthResponse.Status.FAIL_USER, ErrorCodes.Authorization.USER_EXISTS);
                }
            }
        }
        catch (CredentialsManager.HashEnginePerformException e){
            response = new AuthResponse(e.getMessage(),  AuthResponse.Status.FAIL_ENVIRONMENT, ErrorCodes.Authorization.ENVIRONMENT_FAIL);
        }
        catch (CredentialsManager.BadPasswordException e){
            response = new AuthResponse(e.getMessage(), AuthResponse.Status.FAIL_USER, ErrorCodes.Authorization.BAD_PASSWORD);
        }
        return response;
    }
}