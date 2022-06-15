package com.example.bankapp20;

public class Users {

    String name;
    String lastName;
    String age;
    String email;
    String uid;
    String amount;
    String incomes;
    String expenses;

    public Users() {
    }

    public Users(String name, String lastName, String age, String email, String uid, String amount) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.uid = uid;
        this.amount = amount;
        this.incomes = incomes;
        this.expenses = expenses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getIncomes() {
        return incomes;
    }

    public void setIncomes(String incomes) {
        this.incomes = incomes;
    }

    public String getExpenses() {
        return expenses;
    }

    public void setExpenses(String expenses) {
        this.expenses = expenses;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}