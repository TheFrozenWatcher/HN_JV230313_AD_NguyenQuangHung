package ra.model;

import java.util.Scanner;

public class Singer {
    private static int currentId = 1;
    private int singerId;
    private String singerName;
    private int age;
    private String nationality;
    private boolean gender;
    private String genre;

    //    Constructor
    public Singer() {
        this.singerId = currentId++;
    }

    public Singer(String singerName, int age, String nationality, boolean gender, String genre) {
        this();
        this.singerName = singerName;
        this.age = age;
        this.nationality = nationality;
        this.gender = gender;
        this.genre = genre;
    }

    public static Singer[] singers = new Singer[0];


    //    Getter và Setter
    public int getSingerId() {
        return singerId;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age > 0) {
            this.age = age;
        } else {
            System.err.println("Tuổi phải lớn hơn 0.");
        }
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        if (!nationality.isEmpty()) {
            this.nationality = nationality;
        } else {
            System.err.println("Không được để trống quốc tịch.");
        }
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        if (!genre.isEmpty()) {
            this.genre = genre;
        } else {
            System.err.println("Không được để trống dòng nhạc.");
        }
    }

    // Input data
    public void inputData() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập thông tin ca sĩ");

// Nhập tên ca sĩ

        do {
            System.out.println("Nhập tên ca sĩ:");
            this.singerName = sc.nextLine().trim();
            if (this.singerName.isEmpty()) {
                System.out.println("Tên ca sĩ không được để trống.");
            }
        } while (this.singerName.isEmpty());

        // Nhập tuổi
        do {
            System.out.println("Nhập tuổi (phải lớn hơn 0):");
            while (!sc.hasNextInt()) {
                System.out.println("Vui lòng nhập tuổi là số:");
                sc.next(); // consume invalid input
            }
            this.age = sc.nextInt();
            if (this.age <= 0) {
                System.out.println("Tuổi phải lớn hơn 0.");
            }
        } while (this.age <= 0);

        // Nhập quốc tịch
        do {
            System.out.println("Nhập quốc tịch:");
            this.nationality = sc.nextLine().trim();
            if (this.nationality.isEmpty()) {
                System.out.println("Quốc tịch không được để trống.");
            }
        } while (this.nationality.isEmpty());

        // Nhập giới tính
        while (true) {
            System.out.println("Nhập giới tính (true/false):");
            String genderInput = sc.nextLine().toLowerCase();

            if (genderInput.equals("true") || genderInput.equals("false")) {
                this.gender = Boolean.parseBoolean(genderInput);
                break;
            } else {
                System.out.println("Vui lòng nhập giá trị true hoặc false.");
            }
        }
        // Nhập thể loại
        do {
            System.out.println("Nhập thể loại:");
            this.genre = sc.nextLine().trim();
            if (this.genre.isEmpty()) {
                System.out.println("Thể loại nhạc không được để trống.");
            }
        } while (this.genre.isEmpty());

    }

    // Display data method
    public void displayData() {
        System.out.println("Mã ca sĩ: " + singerId
                + "\nTên ca sĩ: " + singerName
                + "\nTuổi: " + age
                + "\nQuốc tịch: " + nationality
                + "\nGiới tính: " + (gender ? "Nam" : "Nữ")
                + "\nThể loại: " + genre);
    }
}
