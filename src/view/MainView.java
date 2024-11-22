package view;

import controller.ContactController;
import entity.Contact;
import regex.RegexContact;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MainView {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ContactController contactController = new ContactController();
        RegexContact regexContact = new RegexContact(scanner);

        while (true) {
            System.out.println("--- Chương trình quản lý danh bạ ---");
            System.out.println("Chọn chức năng theo số (để tiếp tục): ");
            System.out.println("1. Xem danh sách");
            System.out.println("2. Thêm mới");
            System.out.println("3. Cập nhật");
            System.out.println("4. Xóa");
            System.out.println("5. Tìm kiếm");
            System.out.println("6. Đọc từ file");
            System.out.println("7. Ghi vào file");
            System.out.println("8. Thoát");

            int choice = inputChoice();

            switch (choice) {
                case 1:
                    List<Contact> contacts = contactController.getAll();
                    display(contacts);
                    break;
                case 2:
                    Contact contact = inputContact(regexContact); // Sử dụng RegexContact
                    contactController.save(contact);
                    System.out.println("Thêm mới liên hệ thành công!");
                    break;
                case 3:
                    updateContact(contactController, regexContact); // Sử dụng RegexContact
                    break;
                case 4:
                    deleteContact(contactController);
                    break;
                case 5:
                    searchContactByName(contactController);
                    break;
                case 6:
                    contactController.readFromFile();
                    System.out.println("Đọc dữ liệu từ file thành công!");
                    break;
                case 7:
                    contactController.writeToFile();
                    System.out.println("Ghi dữ liệu vào file thành công!");
                    break;
                case 8:
                    System.out.println("Thoát chương trình!");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ! Mời bạn nhập lại.");
            }
        }
    }

    private static void updateContact(ContactController contactController, RegexContact regexContact) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập tên liên hệ cần cập nhật:");
        String name = regexContact.inputName();
        Contact existingContact = contactController.searchByName(name).stream()
                .findFirst()
                .orElse(null);

        if (existingContact != null) {
            System.out.println("Nhập thông tin mới cho liên hệ:");
            Contact updatedContact = inputContact(regexContact);  // Sử dụng RegexContact
            contactController.update(existingContact, updatedContact);
            System.out.println("Cập nhật thông tin liên hệ thành công!");
        } else {
            System.out.println("Không tìm thấy liên hệ với tên " + name);
        }
    }

    private static void deleteContact(ContactController contactController) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập tên liên hệ cần xóa:");
        String name = scanner.nextLine();

        List<Contact> contacts = contactController.searchByName(name);
        if (contacts.isEmpty()) {
            System.out.println("Không tìm thấy liên hệ với tên " + name);
            return;
        }

        System.out.println("Bạn có chắc chắn muốn xóa liên hệ với tên \"" + name + "\" không? (Y/N): ");
        String confirmation = scanner.nextLine();

        if ("Y".equalsIgnoreCase(confirmation)) {
            contactController.deleteByName(name);
            System.out.println("Xóa liên hệ thành công!");

            List<Contact> updatedContacts = contactController.getAll();
            display(updatedContacts);
        } else {
            System.out.println("Hủy xóa liên hệ.");
        }
    }

    private static void searchContactByName(ContactController contactController) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập tên liên hệ cần tìm:");
        String name = scanner.nextLine();
        List<Contact> contacts = contactController.searchByName(name);
        display(contacts);
    }

    private static Contact inputContact(RegexContact regexContact) {
        String name = regexContact.inputName();
        String phoneNumber = regexContact.inputPhone();
        String email = regexContact.inputEmail();
        String address = regexContact.inputAddress();
        Contact.Gender gender = regexContact.inputGender();  // Nhập giới tính
        LocalDate dateOfBirth = regexContact.inputDateOfBirth();  // Nhập ngày sinh

        return new Contact(name, phoneNumber, email, address, gender, dateOfBirth);
    }

    private static void display(List<Contact> contacts) {
        System.out.println("Danh sách liên hệ:");
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }

    private static int inputChoice() {
        Scanner scanner = new Scanner(System.in);
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Nhập sai lựa chọn. Mời bạn nhập lại.");
            return 0;
        }
    }
}