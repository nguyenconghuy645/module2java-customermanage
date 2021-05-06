package com.customer.model;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Customer {
    Scanner scanner = new Scanner(System.in);
    private int id;
    private String name;
    private String address;
    private int telephone;
    private String email;
    private String gender;
    private int countToBuy;

    public Customer() {

    }

    public Customer(int id, String name, String address, int telephone, String email, String gender, int countToBuy) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.telephone = telephone;
        this.email = email;
        this.gender = gender;
        this.countToBuy = countToBuy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getCountToBuy() {
        return countToBuy;
    }

    public void setCountToBuy(int countToBuy) {
        this.countToBuy = countToBuy;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", telephone=" + telephone +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", countToBuy=" + countToBuy +
                '}';
    }

    public String toStringCSV() {
        return id + "," + name + "," + address + "," + telephone + "," +
                email + "," + gender + "," + countToBuy + "\n";
    }

    public void inputCustomer() {
        System.out.println("Nhập tên:");
        this.name = scanner.nextLine();
        System.out.println("Nhập địa chỉ:");
        this.address = scanner.nextLine();
        String regexEmail ="^\\w[a-z0-9]*@[a-z0-9]*.[a-z0-9]*$";
        System.out.println("Nhập email:");
        this.email = scanner.nextLine();
        Pattern pattern1 = Pattern.compile(regexEmail);
        Matcher matcher1 = pattern1.matcher(this.email);
        while(!matcher1.find()){
            System.out.println("Vui lòng nhập đúng email!");
            this.email= scanner.nextLine();
            matcher1 = pattern1.matcher(this.email);
        }
        String regex = "^0+[1-9]\\d{8}$";
        System.out.println("Nhập số điện thoại khách hàng:");
        String telephone = scanner.nextLine();
        Pattern pattern2 = Pattern.compile(regex);
        Matcher matcher2 = pattern2.matcher(telephone);
        while (!matcher2.find()) {
            System.out.println("Vui lòng nhập đúng số điện thoại!");
            telephone = scanner.nextLine();
            matcher2 = pattern2.matcher(telephone);
        }
        this.telephone = Integer.parseInt(telephone);
        System.out.println("Nhap gioi tinh");
        this.gender = scanner.nextLine();
    }
}
