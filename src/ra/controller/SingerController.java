package ra.controller;

import ra.service.SingerService;

import java.util.Scanner;

public class SingerController {
    private final SingerService singerService;


    public SingerController(SingerService singerService) {
        this.singerService = singerService;
    }
//Thêm  ca sỹ
    public void addSingers(Scanner scanner) {
        singerService.addSingers(scanner);
    }
// Cập nhật thông tin
    public void updateSinger(Scanner scanner) {
        singerService.updateSinger(scanner);
    }
//Xóa khỏi danh sách
    public void deleteSinger(Scanner scanner) {
        singerService.deleteSinger(scanner);
    }
// Hiện tất cả
    public void displayAllSingers() {
        singerService.displayAllSingers();
    }
//    Tìm kiếm
    public void searchSingers(String keyword){
        singerService.searchSingers(keyword);
    }
}
