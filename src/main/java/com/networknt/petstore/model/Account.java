package com.networknt.petstore.model;

import java.util.Arrays;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Account  {

    private String accountNo;
    private String userId;
    private String accountType;
    private String firstName;
    private String lastName;
    private String status;

    public Account () {
    }

    @JsonProperty("accountNo")
    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    @JsonProperty("userId")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @JsonProperty("accountType")
    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    @JsonProperty("firstName")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty("lastName")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Account Account = (Account) o;

        return Objects.equals(accountNo, Account.accountNo) &&
               Objects.equals(userId, Account.userId) &&
               Objects.equals(accountType, Account.accountType) &&
               Objects.equals(firstName, Account.firstName) &&
               Objects.equals(lastName, Account.lastName) &&
               Objects.equals(status, Account.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNo, userId, accountType, firstName, lastName, status);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Account {\n");
        sb.append("    accountNo: ").append(toIndentedString(accountNo)).append("\n");        sb.append("    userId: ").append(toIndentedString(userId)).append("\n");        sb.append("    accountType: ").append(toIndentedString(accountType)).append("\n");        sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");        sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");        sb.append("    status: ").append(toIndentedString(status)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
