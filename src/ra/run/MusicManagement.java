package ra.run;

import ra.controller.SingerController;
import ra.controller.SongController;
import ra.service.SingerService;
import ra.service.SongService;

import java.util.Scanner;

public class MusicManagement {
    private static final Scanner scanner = new Scanner(System.in);
    private static final SingerService singerService = new SingerService();
    private static final SongService songService = new SongService();
    private static final SingerController singerController = new SingerController(singerService);
    private static final SongController songController = new SongController(songService);
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        SingerService singerService = new SingerService();
        SongService songService = new SongService();

        SingerController singerController = new SingerController(singerService);
        SongController songController = new SongController(songService);

        boolean exitProgram = false;

        do {
            System.out.println("************************MUSIC-MANAGEMENT*************************");
            System.out.println("1. Quản lý ca sĩ");
            System.out.println("2. Quản lý bài hát");
            System.out.println("3. Tìm kiếm bài hát");
            System.out.println("4. Thoát");
            System.out.print("Nhập lựa chọn: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    singerManagement();
                    break;
                case 2:
                    songManagement();
                    break;
                case 3:
                    searchManagement(scanner,singerController,songController);
                    break;
                case 4:
                    exitProgram = true;
                    System.out.println("Thoát khỏi chương trình.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        } while (!exitProgram);

        scanner.close();
    }

    private static void singerManagement() {
        boolean exitSingerManagement = false;

        do {
            System.out.println("**********************SINGER-MANAGEMENT*************************");
            System.out.println("1. Nhập thông tin ca sĩ mới");
            System.out.println("2. Hiển thị danh sách ca sĩ");
            System.out.println("3. Thay đổi thông tin ca sĩ theo mã id");
            System.out.println("4. Xóa ca sĩ theo mã id");
            System.out.println("5. Thoát");
            System.out.print("Nhập lựa chọn: ");

            int singerChoice = scanner.nextInt();
            scanner.nextLine();

            switch (singerChoice) {
                case 1:
                    singerController.addSingers(scanner);
                    break;
                case 2:
                    singerController.displayAllSingers();
                    break;
                case 3:
                    singerController.updateSinger(scanner);
                    break;
                case 4:
                    singerController.deleteSinger(scanner);
                    break;
                case 5:
                    exitSingerManagement = true;
                    System.out.println("Thoát khỏi quản lý ca sĩ.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        } while (!exitSingerManagement);
    }

    private static void songManagement() {
        boolean exitSongManagement = false;

        do {
            System.out.println("**********************SONG-MANAGEMENT*************************");
            System.out.println("1. Nhập thông tin bài hát mới");
            System.out.println("2. Hiển thị danh sách bài hát");
            System.out.println("3. Thay đổi thông tin bài hát theo mã id");
            System.out.println("4. Xóa bài hát theo mã id");
            System.out.println("5. Thoát");

            System.out.print("Chọn: ");
            int songChoice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (songChoice) {
                case 1:
                    songController.addSongs(scanner);
                    break;
                case 2:
                    songController.displayAllSongs();
                    break;
                case 3:
                    songController.updateSong(scanner);
                    break;
                case 4:
                    songController.deleteSong(scanner);
                    break;
                case 5:
                    exitSongManagement = true;
                    System.out.println("Thoát khỏi quản lý bài hát.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
                    break;
            }
        } while (!exitSongManagement);
    }

    private static void searchManagement(Scanner scanner, SingerController singerController, SongController songController) {
        boolean exitSearchManagement = false;

        do {
            System.out.println("*********************SEARCH-MANAGEMENT************************");
            System.out.println("1. Tìm kiếm bài hát theo tên ca sĩ hoặc thể loại [5 điểm]");
            System.out.println("2. Tìm kiếm ca sĩ theo tên hoặc thể loại [5 điểm]");
            System.out.println("3. Hiển thị danh sách bài hát theo thứ tự tên tăng dần [5 điểm]");
            System.out.println("4. Hiển thị 10 bài hát được đăng mới nhất [10 điểm]");
            System.out.println("5. Thoát");
            System.out.print("Nhập lựa chọn: ");

            int searchChoice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (searchChoice) {
                case 1:
                    searchSongsBySingerOrGenre(scanner, songController);
                    break;
                case 2:
                    searchSingersByNameOrGenre(scanner, singerController);
                    break;
                case 3:
                    displaySongsInAscendingOrder(songController);
                    break;
                case 4:
                    displayLatestSongs(songController);
                    break;
                case 5:
                    exitSearchManagement = true;
                    System.out.println("Thoát khỏi quản lý tìm kiếm.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
                    break;
            }
        } while (!exitSearchManagement);
    }

    private static void searchSongsBySingerOrGenre(Scanner scanner, SongController songController) {
        System.out.print("Nhập tên ca sĩ hoặc thể loại: ");
        String keyword = scanner.nextLine();
        songController.searchSongs(keyword);
    }

    private static void searchSingersByNameOrGenre(Scanner scanner, SingerController singerController) {
        System.out.print("Nhập tên hoặc thể loại ca sĩ: ");
        String keyword = scanner.nextLine();
        singerController.searchSingers(keyword);
    }

    private static void displaySongsInAscendingOrder(SongController songController) {
        songController.displaySongsInAscendingOrder();
    }

    private static void displayLatestSongs(SongController songController) {
        songController.displayLatestSongs();
    }
}