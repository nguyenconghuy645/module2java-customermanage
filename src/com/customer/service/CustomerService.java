package com.customer.service;

import com.customer.dal.CustomerDB;
import com.customer.model.Customer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.customer.dal.CustomerDB.customersList;

public class CustomerService {
    public CustomerDB customerDB = new CustomerDB();
    public Customer cus = new Customer();

    Scanner scanner = new Scanner(System.in);

    public void add(Customer customer) throws IOException {
        customerDB.add(customer);
        customerDB.saveFile();
    }

    public void print() throws IOException {
        for (Map.Entry<Integer, Customer> entry : customersList.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue().toString());
        }
        System.out.println();
        System.out.println("                       Tong cong co " + customersList.size() + " khach hang trong he thong.");
        System.out.println("                                          --------------");
    }

    public void loadData() throws IOException {
        customerDB.readFile();
    }

    public  static void showMenu(){
        System.out.println(
                """
                        Chào mừng bạn đến với hệ thống quản ly khách hàng:
                        Bấm 1 để nhập khách hàng.
                        Bấm 2 để sửa chữa khách hàng.
                        Bam 3 để sắp xếp theo số luợng đơn hàng đã mua.
                        Bam 4 để in toàn bộ danh sách khách hàng.
                        Bam 5 để tăng số đơn hàng cho khách.
                        Bam 6 để tìm kiếm khách hàng.
                        Bam 7 để xoá khách hàng.
                        Bam 0 để thoát.
                        ==========================================
                        """);
    }

    public void addCustomer() throws IOException {
        cus.inputCustomer();
        for (Map.Entry<Integer, Customer> entry : customersList.entrySet()) {
            if (entry.getValue().getEmail().equals(cus.getEmail()) || entry.getValue().getTelephone() == cus.getTelephone()) {
                System.out.println("Khách hàng đã tồn tại, Hệ thống vừa cập nhật thông tin.\n" + "Chọn menu để thực hiện tiếp.\n" + "==========================================\n");
                return;
            }
        }
        int count = customersList.size();
        cus.setId(++count);
        add(cus);
        customerDB.saveFile();
        System.out.println(" Bạn vừa thêm mới khách hàng  '" + cus.getName() + "'  thành công!");
        System.out.println("Chọn menu để thực hiện tiếp.\n" +
                "==========================================");
    }

    public void searchCustomer() {
        if (customersList.size() == 0) {
            System.err.println("Danh sách khách hàng đang rỗng!");
            return;
        }
        int telephone = getTelephone();
        System.out.println("Hệ thống đang tìm kiếm...\n" + "Kết quả:\n");
        boolean check = true;
        for (Map.Entry<Integer, Customer> entry : customersList.entrySet()) {
            if (entry.getValue().getTelephone() == telephone) {
                System.out.println(cus);
                check = false;
                break;
            }
        }
        if (check) {
            System.out.println("Không tồn tại khách hàng \n" + "Chọn menu để thực hiện tiếp.\n" + "==========================================\n" + "\n");
        }
    }

    public void increaseCustomerBought() throws IOException {
        if (customersList.size() == 0) {
            System.err.println("Danh sách khách hàng đang rỗng!");
            return;
        }
        int telephone = getTelephone();
        boolean check = true;
        for (Map.Entry<Integer, Customer> entry : customersList.entrySet()) {
            if (entry.getValue().getTelephone() == telephone) {
                entry.getValue().setCountToBuy(cus.getCountToBuy() + 1);
                System.out.println("Thông tin sau khi tăng 1 đơn hàng.");
                System.out.println(entry);
                System.out.println("                                          --------------");
                check = false;
                customerDB.saveFile();
                break;
            }
        }
        if (check) {
            System.out.println("Không tồn tại khách hàng. \n" +
                    "Chọn menu để thực hiện tiếp.\n" +
                    "==========================================\n" +
                    "\n");
        }
    }

    public void sortCustomer() throws IOException {
        ArrayList<Customer> arrayList = new ArrayList<>();
        arrayList.add((Customer) customersList.entrySet());
        if (arrayList.size() == 0) {
            System.err.println("Danh sách khách hàng đang rỗng!");
            return;
        }
        arrayList.sort((o2, o1) -> {
            if (o1.getId() < o2.getId()) {
                return -1;
            }
            return 1;
        });
        System.out.println("Đã sắp xếp!");
        System.out.println("------------");
        for (Map.Entry<Integer, Customer> entry : customersList.entrySet()) {
            System.out.println(entry.toString());
        }
    }

    public void editCustomer() throws IOException {
        if (customersList.size() == 0) {
            System.err.println("Danh sách khách hàng đang rỗng!");
            return;
        }
        boolean check = true;
        int telephone = getTelephone();
        for (Map.Entry<Integer, Customer> entry : customersList.entrySet()) {
            if (entry.getValue().getTelephone() == telephone) {
                entry.getValue().inputCustomer();
                System.out.println("                                          --------------");
                customerDB.saveFile();
                check = false;
            }
        }
        if (check) {
            System.out.println("Không tồn tại khách hàng. \n" +
                    "Chọn menu để thực hiện tiếp.\n" +
                    "==========================================\n" +
                    "\n");
        }
    }

    public void deleteCustomer() throws IOException {
        if (customersList.size() == 0) {
            System.err.println("Customer Manager File is Empty!");
            return;
        }
        boolean check = true;
        int telephone = getTelephone();
        for (Map.Entry<Integer, Customer> entry : customersList.entrySet()) {
            if (entry.getValue().getTelephone() == telephone) {
                customersList.remove(entry);
                System.out.println("Đã xóa thành không!");
                System.out.println("                                          --------------");
                customerDB.saveFile();
                check = false;
            }
        }
        if (check) {
            System.out.println("Không tồn tại khách hàng. \n" +
                    "Chọn menu để thực hiện tiếp.\n" +
                    "==========================================\n" +
                    "\n");
        }
    }

    private int getTelephone() {
        String regex = "^0+[1-9]\\d{8}$";
        System.out.println("Nhập số điện thoại khách hàng:");
        String telephone = scanner.nextLine();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(telephone);

        while (!matcher.find()) {
            System.out.println("Vui lòng nhập đúng số điện thoại!");
            telephone = scanner.nextLine();
            matcher = pattern.matcher(telephone);
        }
        return Integer.parseInt(telephone);
    }
}
