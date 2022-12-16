package users;

import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public final class Credentials {
    private String name;
    private String password;
    private String accountType;
    private String country;

    private int balance;

    /**
     * Deep copies credentials;
     * Used in the user deep copy
     * @param credentials the credentials to be copied
     */
    public Credentials(final Credentials credentials) {
        this.name = credentials.name;
        this.password = credentials.password;
        this.accountType = credentials.accountType;
        this.country = credentials.country;
        this.balance = credentials.balance;
    }

    /**
     * Subtracts the balance of the user
     * @param count the value subtracted
     */
    public void subtractBalance(final int count) {
        balance -= count;
    }

    /**
     * Gets the balance of the user
     * @return the balance in String format
     */
    @JsonGetter("balance")
    public String getBalance() {
        return Integer.toString(balance);
    }
}
