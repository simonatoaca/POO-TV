package users;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class Credentials {
    private String name;
    private String password;
    private String accountType;
    private String country;

    private int balance;

    public void subtractBalance(int count) {
        balance -= count;
    }

    @JsonGetter("balance")
    public String getBalance() {
        return Integer.toString(balance);
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", accountType='" + accountType + '\'' +
                ", country='" + country + '\'' +
                ", balance=" + balance +
                '}';
    }
}
