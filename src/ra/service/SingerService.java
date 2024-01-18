package ra.service;

import ra.model.Singer;

import java.util.Scanner;

public class SingerService {
    Scanner sc = new Scanner(System.in);
    public static Singer[] singers = new Singer[0];

    public void addSingers(Scanner sc) {
        int n;
//        Nhập số lượng cần thêm
        System.out.println("Nhập số lượng cần thêm: ");
        while (true) {
            if (sc.hasNextInt()) {
                n = sc.nextInt();
                if (n >= 1) {
                    break;
                } else {
                    System.out.println("Số lượng không hợp lệ! Nhập lại:");
                }
            } else {
                System.out.println("Vui lòng nhập một số nguyên. Nhập lại:");
                sc.next();
            }
        }
        Singer[] newSingers = new Singer[singers.length + n];
        for (int i = 0; i < singers.length; i++) {
            newSingers[i] = singers[i];
        }
        for (int i = 0; i < n; i++) {
            Singer newSinger = new Singer();
            newSinger.inputData();
            newSingers[i] = newSinger;
        }
        singers = newSingers;
    }

    //  Tìm bài hát qua ID
    public static int findSingerIndexById(String id, Singer[] singers) {
        for (int i = 0; i < singers.length; i++) {
            if (String.valueOf(singers[i].getSingerId()).equals(id)) {
                return i;
            }
        }
        return -1;
    }

    //    Cập nhật thông tin ca sĩ
    public void updateSinger(Scanner sc) {
        // Nhập id
        System.out.println("Nhập id ca sĩ cần sửa: ");
        String id = sc.nextLine();
        int indexById = findSingerIndexById(id, singers);
        if (indexById != -1) {
            System.out.println("Thông tin cũ");
            System.out.println(singers[indexById]);
            System.out.println("Nhập thông tin mới");
            singers[indexById].inputData();
            System.out.println("Cập nhật thành công");
        } else {
            System.err.println("Không có id này");
        }
    }

    //      Xóa ca sĩ khỏi danh sách
    public void deleteSinger(Scanner sc) {
        System.out.println("Nhập id cần xoá :");
        String id = sc.nextLine();
        int indexById = findSingerIndexById(id, singers);
        if (indexById != -1) {
            Singer[] newSingers = new Singer[singers.length - 1];
            for (int i = 0; i < singers.length; i++) {
                if (i < indexById) {
                    newSingers[i] = singers[i];
                } else if (i > indexById) {
                    newSingers[i - 1] = singers[i];
                }
            }
            singers = newSingers;
            System.out.println("Xóa thành công");
        } else {
            System.err.println("Không có id này");
        }
    }

    //    Hiện thông tin các ca sĩ
    public void displayAllSingers() {
        if (singers.length > 0) {
            System.out.println("Danh sách ca sĩ:");
            for (Singer singer : singers) {
                singer.displayData();
            }
        } else {
            System.out.println("Danh sách ca sĩ trống.");
        }
    }

    //    Tìm kiếm ca sĩ theo tên hoặc thể loại
    public void searchSingers(String keyword) {
        boolean found = false;
        for (Singer singer : singers) {
            if (singer.getSingerName().toLowerCase().contains(keyword.toLowerCase()) ||
                    singer.getGenre().toLowerCase().contains(keyword.toLowerCase())) {
                singer.displayData();
                found=true;
            }
        }
        if (!found){
            System.out.println("Không tìm thấy ca sĩ.");
        }


    }
}