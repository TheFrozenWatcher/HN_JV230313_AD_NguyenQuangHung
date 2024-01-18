package ra.controller;

import ra.model.Song;
import ra.service.SongService;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

import static ra.model.Song.songs;

public class SongController {
    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }
//  Thêm bài hát
    public void addSongs(Scanner scanner) {
        songService.addSongs(scanner);
    }
//  Cập nhật
    public void updateSong(Scanner scanner) {
        songService.updateSong(scanner);
    }
//  Xóa
    public void deleteSong(Scanner scanner) {
        songService.deleteSong(scanner);
    }
//  Hiện tất cả
    public void displayAllSongs() {
        songService.displayAllSongs();
    }
//    Tìm kiếm
    public void searchSongs(String keyword){
        songService.searchSongs(keyword);
    }
    public void displaySongsInAscendingOrder() {
        Song[] sortedSongs = Arrays.copyOf(songs, songs.length);
        Arrays.sort(sortedSongs, Comparator.comparing(Song::getSongName));

        System.out.println("Danh sách bài hát theo thứ tự tên tăng dần:");
        for (Song song : sortedSongs) {
            song.displayData();
        }
    }

    // Display the 10 latest songs
    public void displayLatestSongs() {
        Song[] latestSongs = Arrays.copyOf(songs, songs.length);
        Arrays.sort(latestSongs, Comparator.comparing(Song::getCreatedDate).reversed());

        System.out.println("10 bài hát mới nhất:");
        int count = Math.min(10, latestSongs.length);
        for (int i = 0; i < count; i++) {
            latestSongs[i].displayData();
        }
    }
}