package com.xylia.java.learn.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    private String name;
    private int points;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return points == customer.points &&
                Objects.equals(name, customer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, points);
    }

    public static class CustomerBuilder {

        private String name;
        private int points;

        public CustomerBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public CustomerBuilder withPoints(int points) {
            this.points = points;
            return this;
        }

        public Customer build() {
            return new Customer(name, points);
        }
    }
}
