package com.customer.presentation;

import com.customer.model.Customer;
import com.customer.service.CustomerService;

import java.io.IOException;
import java.util.Scanner;

public class MainCustomer {
    public static void main(String[] args) {
        CustomerService customerService = new CustomerService();
        Scanner scanner = new Scanner(System.in);

        try {
            customerService.loadData();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        int choose;
        do {
            customerService.showMenu();
            choose = scanner.nextInt();
            switch (choose) {
                case 1:
                    try {
                        customerService.addCustomer();
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        customerService.editCustomer();
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        customerService.sortCustomer();
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        customerService.print();
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    try {
                        customerService.increaseCustomerBought();
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 6:
                    customerService.searchCustomer();
                    break;
                case 7:
                    try {
                        customerService.deleteCustomer();
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 0:
                    System.out.println("Hệ thống quản lý khách hàng vừa tắt.");
                    break;
                default:
                    System.out.println("Mời nhập lại.");
                    System.out.println("-------------");
                    break;
            }
        } while (choose != 0);
    }
}
