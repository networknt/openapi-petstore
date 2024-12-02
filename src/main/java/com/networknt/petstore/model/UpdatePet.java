package com.networknt.petstore.model;

import java.util.Arrays;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdatePet  {

    private Integer petAge;
    private java.util.List<String> petToys;
    private String ownerEmail;
    private String ownerSsn;

    public UpdatePet () {
    }

    @JsonProperty("petAge")
    public Integer getPetAge() {
        return petAge;
    }

    public void setPetAge(Integer petAge) {
        this.petAge = petAge;
    }

    @JsonProperty("petToys")
    public java.util.List<String> getPetToys() {
        return petToys;
    }

    public void setPetToys(java.util.List<String> petToys) {
        this.petToys = petToys;
    }

    @JsonProperty("ownerEmail")
    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    @JsonProperty("ownerSsn")
    public String getOwnerSsn() {
        return ownerSsn;
    }

    public void setOwnerSsn(String ownerSsn) {
        this.ownerSsn = ownerSsn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UpdatePet UpdatePet = (UpdatePet) o;

        return Objects.equals(petAge, UpdatePet.petAge) &&
               Objects.equals(petToys, UpdatePet.petToys) &&
               Objects.equals(ownerEmail, UpdatePet.ownerEmail) &&
               Objects.equals(ownerSsn, UpdatePet.ownerSsn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(petAge, petToys, ownerEmail, ownerSsn);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class UpdatePet {\n");
        sb.append("    petAge: ").append(toIndentedString(petAge)).append("\n");        sb.append("    petToys: ").append(toIndentedString(petToys)).append("\n");        sb.append("    ownerEmail: ").append(toIndentedString(ownerEmail)).append("\n");        sb.append("    ownerSsn: ").append(toIndentedString(ownerSsn)).append("\n");
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
