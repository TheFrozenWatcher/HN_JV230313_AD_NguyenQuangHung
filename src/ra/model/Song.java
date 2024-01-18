package ra.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import ra.service.SingerService;

import static ra.model.Singer.singers;

public class Song {
    Scanner sc = new Scanner(System.in);
    // Các thuộc tính private
    private String songId;
    private String songName;
    private String descriptions;
    private Singer singer;
    private String songWriter;
    private Date createdDate;
    private boolean songStatus;
    //Định dạng ngày
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    // Constructor
    public Song() {
        // Mặc định createdDate là thời gian hệ thống
        this.createdDate = new Date();
    }

    public Song(String songId, String songName, String descriptions, Singer singer, String songWriter, Date createdDate, boolean songStatus){
        this.songId=songId;
        this.songName=songName;
        this.descriptions=descriptions;
        this.singer=singer;
        this.songWriter=songWriter;
        this.createdDate=createdDate;
        this.songStatus=songStatus;
    }

    public static Song[] songs = new Song[0];


    // Các phương thức getter/setter
    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public Singer getSinger() {
        return singer;
    }

    public void setSinger(Singer singer) {
        this.singer = singer;
    }

    public String getSongWriter() {
        return songWriter;
    }

    public void setSongWriter(String songWriter) {
        this.songWriter = songWriter;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public boolean isSongStatus() {
        return songStatus;
    }

    public void setSongStatus(boolean songStatus) {
        this.songStatus = songStatus;
    }

    // Triển khai phương thức inputData()
    public void inputData() {
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        // Nhập mã bài hát
        do {
            System.out.println("Nhập mã bài hát (4 ký tự, bắt đầu bằng 'S'):");
            this.songId = sc.nextLine().trim();
        } while (!this.songId.matches("S\\d{3}") || !isUniqueSongId(this.songId));

        // Nhập tên bài hát
        do {
            System.out.println("Nhập tên bài hát:");
            this.songName = sc.nextLine().trim();
        } while (this.songName.isEmpty());

        // Nhập mô tả
        System.out.println("Nhập mô tả bài hát:");
        this.descriptions = sc.nextLine().trim();

        // Chọn ca sĩ
        this.singer=chooseSinger(SingerService.singers,sc);

        // Nhập người sáng tác
        do {
            System.out.println("Nhập người sáng tác:");
            this.songWriter = sc.nextLine().trim();
        } while (this.songWriter.isEmpty());

        // Trạng thái bài hát
        System.out.println("Nhập trạng thái bài hát (true - Hoạt động, false - Ngừng hoạt động):");
        this.songStatus = sc.nextBoolean();
    }

    // Triển khai phương thức displayData()
    public void displayData() {
        System.out.println("Mã bài hát: " + songId
                + "\nTên bài hát: " + songName
                + "\nMô tả: " + descriptions
                + "\nCa sĩ: " + singer.getSingerName()
                + "\nNgười sáng tác: " + songWriter
                + "\nNgày tạo: " + dateFormat.format(createdDate)
                + "\nTrạng thái: " + (songStatus ? "Hoạt động" : "Ngừng hoạt động"));
    }

    // Phương thức kiểm tra mã bài hát có duy nhất không
    private boolean isUniqueSongId(String id) {
        int i = findSongIndexById(id);
        if (i == -1) {
            return true;  // Mã bài hát là duy nhất
        } else {
            return false;
        }

    }

    //  Tìm bài hát qua ID
    public int findSongIndexById(String id) {
        for (int i = 0; i < songs.length; i++) {
            if (songs[i].getSongId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    // Phương thức chọn ca sĩ từ danh sách
    private Singer chooseSinger(Singer[] singers, Scanner sc) {
        // Hiển thị danh sách ca sĩ cho người dùng chọn
        System.out.println("Danh sách ca sĩ:");

        // Hiển thị danh sách ca sĩ (thực hiện từ database hoặc danh sách đã lưu trước đó)
        for (int i = 0; i < singers.length; i++) {
            System.out.println("ID: " + singers[i].getSingerId() + ", Tên ca sĩ: " + singers[i].getSingerName());
        }

        // Chọn ca sĩ theo id nhập vào
        do {
            System.out.println("Nhập mã ca sĩ cho bài hát:");
            String singerId = sc.nextLine().trim();

            // Lấy thông tin ca sĩ từ danh sách ca sĩ
            int index = SingerService.findSingerIndexById(singerId, singers);

            if (index != -1) {
                return singers[index];
            } else {
                System.out.println("Không tìm thấy ca sĩ với mã id đã nhập. Vui lòng thử lại.");
            }
        } while (true);
    }

}

