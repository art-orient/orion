package com.art.orion.model.entity;

/**
 * Bean class of model layer represents user
 *
 * @author Aliaksandr Artsikhovich
 * @version 1.0
 */
public class User {
    /**
     * Unique identification {@code String} value for user's account, also used during authorization
     * process.
     */
    private String username;
    /**
     * Secret {@code String} value that is determined by user and used during process of
     * authorization. Represents encrypted value of original password.
     */
    private String password;
    /**
     * First name {@code String} of the user.
     */
    private String firstName;
    /**
     * Last name {@code String} of the user.
     */
    private String lastName;
    /**
     * E-mail {@code String} of the user.
     */
    private String email;
    /**
     * {@code Role} value represents user's role and defines user's account privileges.
     */
    private Role role;
    /**
     * {@code boolean} value represents status of the user account. {@code true} - for active
     * account, {@code false} - for inactive accounts.
     */
    private boolean active;

    /**
     * Explicit default constructor.
     */
    public User() {
    }

    /**
     * The constructor with parameters.
     */
    public User(String username, String firstName, String lastName, String email) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    /**
     * The constructor with parameters.
     */
    public User(String username, String password, String firstName, String lastName, String email, Role role,
                boolean active) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.active = active;
    }

    /**
     * Standard getter method to access private class member.
     *
     * @return {@code String} username that represents unique identification name for the user's
     * account.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Standard setter method to access private class member.
     *
     * @param username {@code String} value represents unique identification name for the user's
     * account.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Standard getter method to access private class member.
     *
     * @return {@code String} value that represents encrypted value of the secret user's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Standard setter method to access private class member.
     *
     * @param password {@code String} value that represents encrypted value of the secret user's
     * password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Standard getter method to access private class member.
     *
     * @return {@code String} that represents the first name of the user.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Standard setter method to access private class member.
     *
     * @param firstName {@code String} represents first name of the user.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Standard getter method to access private class member.
     *
     * @return {@code String} that represents the last name of the user
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Standard setter method to access private class member.
     *
     * @param lastName {@code String} represents last name of the user
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Standard getter method to access private class member.
     *
     * @return {@code String} that represents the e-mail of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Standard getter method to access private class member.
     *
     * @return {@code Role} value represents user's role and defines user's account privileges
     */
    public Role getRole() {
        return role;
    }

    /**
     * Standard setter method to access private class member.
     *
     * @param role {@code Role} value represents user's role and defines user's account privileges
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Standard getter method to access private class member.
     *
     * @return {@code boolean} value that represents status of the user account. {@code true} - for
     * active account, {@code false} - for inactive accounts
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Standard setter method to access private class member.
     *
     * @param active {@code boolean} value represents status of the user account. {@code true} - for
     *               active account, {@code false} - for inactive accounts
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return username.equals(user.username);
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

    @Override
    public String toString() {
        return new StringBuilder("User {username = ").append(username)
                .append(", firstName = ").append(firstName)
                .append(", lastName = ").append(lastName)
                .append(", email = ").append(email)
                .append(", role = ").append(role)
                .append(", active = ").append(active)
                .append('}').toString();
    }
}
