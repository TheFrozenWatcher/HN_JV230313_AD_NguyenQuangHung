package ra.service;

import ra.model.Song;

import java.util.Scanner;

public class SongService {
    Scanner sc = new Scanner(System.in);
    public Song[] songs = new Song[0];

    public void addSongs(Scanner sc) {
//        Nhập số lượng cần thêm
        System.out.println("Nhập số lượng cần thêm");
        int n = sc.nextInt();
        if (n < 1) {
            System.out.println("Số lượng không hợp lệ!");
            return;
        }
        Song[] newSongs = new Song[songs.length + n];
        for (int i = 0; i < songs.length; i++) {
            newSongs[i] = songs[i];
        }
        for (int i = 0; i < n; i++) {
            Song newSong = new Song();
            newSong.inputData();
            newSongs[i] = newSong;
        }
        songs = newSongs;
    }

    //  Tìm bài hát qua ID
    public int findSongIndexById(String id) {
        for (int i = 0; i < songs.length; i++) {
            if (String.valueOf(songs[i].getSongId()).equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public void updateSong(Scanner sc) {
        // Nhập id
        System.out.println("Nhập id bài hát cần sửa: ");
        String id = sc.nextLine();
        int indexById = findSongIndexById(id);
        if (indexById != -1) {
            System.out.println("Thông tin cũ");
            System.out.println(songs[indexById]);
            System.out.println("Nhập thông tin mới");
            songs[indexById].inputData();
            System.out.println("Cập nhật thành công");
        } else {
            System.err.println("Không có id này");
        }
    }

    public void deleteSong(Scanner sc) {
        System.out.println("Nhập id cần xoá :");
        String id = sc.nextLine();
        int indexById = findSongIndexById(id);
        if (indexById != -1) {
            Song[] newSongs = new Song[songs.length - 1];
            for (int i = 0; i < songs.length; i++) {
                if (i < indexById) {
                    newSongs[i] = songs[i];
                } else if (i > indexById) {
                    newSongs[i - 1] = songs[i];
                }
            }
            songs = newSongs;
            System.out.println("Xóa thành công");
        } else {
            System.err.println("Không có id này");
        }
    }

    public void displayAllSongs() {
        if (songs.length > 0) {
            System.out.println("Danh sách ca khúc:");
            for (Song song : songs) {
                song.displayData();
            }
        } else {
            System.out.println("Danh sách ca khúc trống.");
        }
    }

    //    Tìm kiếm ca khúc theo tên, ca sỹ hoặc thể loại
    public void searchSongs(String keyword) {
        boolean found = false;
        for (Song song : songs) {
            if (song.getSinger().getSingerName().toLowerCase().contains(keyword.toLowerCase()) ||
                    song.getSinger().getGenre().toLowerCase().contains(keyword.toLowerCase()) ||
                    song.getSongName().toLowerCase().contains(keyword.toLowerCase())) {
                song.displayData();
                found = true;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy ca sĩ.");
        }


    }

}
