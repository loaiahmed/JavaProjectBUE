import java.util.*;
import java.util.regex.Pattern;

public class User {
    protected int userID;
    protected String username;
    protected String email;
    protected String password;
    static int count = 0;

    public User(String username, String email, String password) {
        this.userID = count;
        this.username = username;
        this.email = email;
        this.password = password;
        count++;
    }
    public User() {
        this.userID = count;
        count++;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password=" + password +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userID == user.userID && Objects.equals(username, user.username) && Objects.equals(email, user.email) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, username, email, password);
    }

    public boolean createAccount(HashSet<User> users) {
        Scanner scan = new Scanner(System.in);
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);

        System.out.print("Username : ");
        this.username = scan.next();

        do {
            System.out.print("Email : ");
            this.email = scan.next();
        } while (!(pat.matcher(email).matches()));

        System.out.print("Password : ");
        this.password = scan.next();

        users.add(this);

        return true;
    }

}
