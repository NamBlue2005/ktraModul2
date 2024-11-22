package regex;

import entity.Contact.Gender;

import java.time.LocalDate;
import java.util.Scanner;

public class RegexContact {

    private String nameRegex = "^[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểễệỉịọỏốồổỗộớờởỡợụủứừửữựỳỵỷỹ\\s]{2,}$";
    private String phoneRegex = "^(84|0[3|5|7|8|9])+([0-9]{8})$";
    private String addressRegex = "^[a-zA-Z0-9ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểễệỉịọỏốồổỗộớờởỡợụủứừửữựỳỵỷỹ,\\s]{5,}$";
    private String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    private Scanner scanner;

    public RegexContact(Scanner scanner) {
        this.scanner = scanner;
    }

    public String inputName() {
        String name;
        System.out.println("Mời bạn nhập tên liên hệ: ");
        name = scanner.nextLine();
        while (!name.matches(nameRegex)) {
            System.out.println("Tên liên hệ không hợp lệ. Mời bạn nhập lại.");
            name = scanner.nextLine();
        }
        return name;
    }

    public String inputPhone() {
        String phone;
        System.out.println("Mời bạn nhập số điện thoại: ");
        phone = scanner.nextLine();
        while (!phone.matches(phoneRegex)) {
            System.out.println("Số điện thoại không hợp lệ. Mời bạn nhập lại.");
            phone = scanner.nextLine();
        }
        return phone;
    }

    public String inputAddress() {
        String address;
        System.out.println("Mời bạn nhập địa chỉ: ");
        address = scanner.nextLine();
        while (!address.matches(addressRegex)) {
            System.out.println("Địa chỉ không hợp lệ. Mời bạn nhập lại.");
            address = scanner.nextLine();
        }
        return address;
    }

    public String inputEmail() {
        String email;
        System.out.println("Mời bạn nhập email: ");
        email = scanner.nextLine();
        while (!email.matches(emailRegex)) {
            System.out.println("Email không hợp lệ. Mời bạn nhập lại.");
            email = scanner.nextLine();
        }
        return email;
    }

    public Gender inputGender() {
        System.out.println("Mời bạn chọn giới tính (Nam/Nữ/Khác): ");
        while (true) {
            String genderInput = scanner.nextLine().toUpperCase();
            switch (genderInput) {
                case "NAM":
                    return Gender.MALE;
                case "NỮ":
                    return Gender.FEMALE;
                case "KHÁC":
                    return Gender.OTHER;
                default:
                    System.out.println("Giới tính không hợp lệ. Mời bạn nhập lại.");
            }
        }
    }

    public LocalDate inputDateOfBirth() {
        System.out.println("Mời bạn nhập ngày sinh (yyyy-MM-dd): ");
        while (true) {
            try {
                String dateInput = scanner.nextLine();
                return LocalDate.parse(dateInput);
            } catch (Exception e) {
                System.out.println("Ngày sinh không hợp lệ. Vui lòng nhập lại theo định dạng yyyy-MM-dd.");
            }
        }
    }
}