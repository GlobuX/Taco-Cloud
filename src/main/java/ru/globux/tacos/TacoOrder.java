package ru.globux.tacos;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.CreditCardNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TacoOrder {
    @NotBlank(message = "Delivery name is required")
    private String deliveryName;

    @NotBlank(message = "Street is required")
    private String deliveryStreet;

    @NotBlank(message = "City is required")
    private String deliveryCity;

    @NotBlank(message = "State is required")
    private String deliveryState;

    @NotBlank(message = "Zip code is required")
    private String deliveryZip;

    @CreditCardNumber(message = "Not valid credit card number")
    private String ccNumber;

    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
            message ="Must be formatted MM/YY")
    private String ccExpiration;

    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String ccCVV;

    private List<Taco> tacos = new ArrayList<>();

    public TacoOrder() {
    }

    public void addTaco(Taco taco) {
        this.tacos.add(taco);
    }

    public String getDeliveryName() {
        return deliveryName;
    }

    public void setDeliveryName(String deliveryName) {
        this.deliveryName = deliveryName;
    }

    public String getDeliveryStreet() {
        return deliveryStreet;
    }

    public void setDeliveryStreet(String deliveryStreet) {
        this.deliveryStreet = deliveryStreet;
    }

    public String getDeliveryCity() {
        return deliveryCity;
    }

    public void setDeliveryCity(String deliveryCity) {
        this.deliveryCity = deliveryCity;
    }

    public String getDeliveryState() {
        return deliveryState;
    }

    public void setDeliveryState(String deliveryState) {
        this.deliveryState = deliveryState;
    }

    public String getDeliveryZip() {
        return deliveryZip;
    }

    public void setDeliveryZip(String deliveryZip) {
        this.deliveryZip = deliveryZip;
    }

    public String getCcNumber() {
        return ccNumber;
    }

    public void setCcNumber(String ccNumber) {
        this.ccNumber = ccNumber;
    }

    public String getCcExpiration() {
        return ccExpiration;
    }

    public void setCcExpiration(String ccExpiration) {
        this.ccExpiration = ccExpiration;
    }

    public String getCcCVV() {
        return ccCVV;
    }

    public void setCcCVV(String ccCVV) {
        this.ccCVV = ccCVV;
    }

    public List<Taco> getTacos() {
        return tacos;
    }

    public void setTacos(List<Taco> tacos) {
        this.tacos = tacos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TacoOrder tacoOrder = (TacoOrder) o;
        return Objects.equals(deliveryName, tacoOrder.deliveryName)
                && Objects.equals(deliveryStreet, tacoOrder.deliveryStreet)
                && Objects.equals(deliveryCity, tacoOrder.deliveryCity)
                && Objects.equals(deliveryState, tacoOrder.deliveryState)
                && Objects.equals(deliveryZip, tacoOrder.deliveryZip)
                && Objects.equals(ccNumber, tacoOrder.ccNumber)
                && Objects.equals(ccExpiration, tacoOrder.ccExpiration)
                && Objects.equals(ccCVV, tacoOrder.ccCVV)
                && Objects.equals(tacos, tacoOrder.tacos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deliveryName, deliveryStreet, deliveryCity,
                deliveryState, deliveryZip, ccNumber, ccExpiration, ccCVV, tacos);
    }

    @Override
    public String toString() {
        return "TacoOrder{" +
                "deliveryName='" + deliveryName + '\'' +
                ", deliveryStreet='" + deliveryStreet + '\'' +
                ", deliveryCity='" + deliveryCity + '\'' +
                ", deliveryState='" + deliveryState + '\'' +
                ", deliveryZip='" + deliveryZip + '\'' +
                ", ccNumber='" + ccNumber + '\'' +
                ", ccExpiration='" + ccExpiration + '\'' +
                ", ccCVV='" + ccCVV + '\'' +
                ", tacos=" + tacos +
                '}';
    }
}
