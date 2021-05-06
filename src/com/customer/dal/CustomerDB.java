package com.customer.dal;

import com.customer.model.Customer;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class CustomerDB {
    public static Map<Integer, Customer> customersList;
    public static int count;
    static {
        customersList = new HashMap<>();
        count = 0;
    }

    public void loadCustomer() throws IOException {
        File file = new File("CustomerData.csv");
        if ((file.exists())) {
            return;
        }
        FileReader fis = new FileReader("CustomerData.cvs");
        BufferedReader bis = new BufferedReader(fis);
        String line;
        while ((line = bis.readLine()) != null) {
            String[] array = line.split(",");
            Customer customer = new Customer(Integer.parseInt(array[0]), array[1], array[2], Integer.parseInt(array[3]), array[4], array[5], Integer.parseInt(array[6]));
            add(customer);
        }
    }

    public void saveFile() throws IOException {
        File file = new File("CustomerData.csv");
        if (!(file.exists())) {
            file.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream("CustomerData.csv");
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        for (Map.Entry<Integer, Customer> entry : customersList.entrySet()) {
            bos.write(entry.getValue().toStringCSV().getBytes());
        }
        bos.flush();
        bos.close();
        fos.close();
    }

    public void readFile() throws IOException {
        File file = new File("CustomerData.csv");
        if ((file.exists())) {
            return;
        }
        FileReader fis = new FileReader("CustomerData.cvs");
        BufferedReader bis = new BufferedReader(fis);
        String line;
        while ((line = bis.readLine()) != null) {
            String[] array = line.split(",");
            Customer customer = new Customer(Integer.parseInt(array[0]), array[1], array[2], Integer.parseInt(array[3]), array[4], array[5], Integer.parseInt(array[6]));
            add(customer);
        }
    }

    public void add(Customer customer) throws IOException {
        count++;
        customer.setId(count);
        customersList.put(count, customer);
    }
}
