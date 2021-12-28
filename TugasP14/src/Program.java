import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Program {
    //static Scanner scanner;
	static Connection conn;

    public static void main(String[] args) throws Exception {
        try (Scanner inputan = new Scanner (System.in)) {
            String pilihanUser;
            boolean isLanjutkan = true;
            
            String url = "jdbc:mysql://localhost:3306/pbotugas14";

            String salamSapa = "Pagi, Semoga Harimu Luar Biasa";
            String sapa = salamSapa.replace("Pagi", "Haii :)"); //method replace()

            System.out.println(sapa.toLowerCase()); //method toLowerCase()
            
            try (Scanner input = new Scanner(System.in)) {
                System.out.println("Masukkan Password : ");
                String password1 = input.nextLine();
                String password2="cipacipa"; 
                System.out.println("Password " +password1.equals(password2)); //method equals()

                if(password1.equals(password2)){
                    System.out.println("\nPROGRAM MENGHITUNG GAJI KARYAWAN");

                    try{
                        TerimaGaji uang = new TerimaGaji();
                        
                        Class.forName("com.mysql.cj.jdbc.Driver");
            	        conn = DriverManager.getConnection(url,"root","");
            	        System.out.println("Class Driver ditemukan!!!");

                        while (isLanjutkan) {
                            System.out.println("----------------------");
            		        System.out.println("Database Pegawai");
            		        System.out.println("----------------------");
            		        System.out.println("1. Lihat Data Pegawai");
            		        System.out.println("2. Tambah Data Pegawai");
            		        System.out.println("3. Ubah Data Pegawai");
            		        System.out.println("4. Hapus Data Pegawai");
            		        System.out.println("5. Cari Data Pegawai");
            		
            		        System.out.print("\nPilihan anda (1/2/3/4/5): ");
            		        pilihanUser = inputan.next();
            		
            		        switch (pilihanUser){
                            case "1":
            			        uang.show();
            			        break;
            		        case "2":
            			        uang.insert();
            			        break;
            		        case "3":
            			        uang.edit();
            			        break;
            		        case "4":
            			        uang.delete();
            			        break;
            		        case "5":
            			        uang.search();
            			        break;
            		        default:
            			    System.err.println("\nInput anda tidak ditemukan\nSilakan pilih [1-5]");
                        }

                        System.out.print("\nApakah Anda ingin melanjutkan [y/n]? ");
            		    pilihanUser = inputan.next();
            		    isLanjutkan = pilihanUser.equalsIgnoreCase("y");
                    }
                    
            	    System.out.println("Program selesai...");
                }
                catch(ClassNotFoundException ex) {
                    System.err.println("Driver Error");
                    System.exit(0);
                }
                catch(SQLException e){
                    System.err.println("Tidak berhasil koneksi");
                }
                finally{
                    System.out.println("\nProgram Selesai\n");
                    LocalDate tanggalSekarang = LocalDate.now();
                    LocalTime waktuSekarang = LocalTime.now();
                    
                    System.out.println("Diakses pada :");
                    System.out.println("Tanggal\t= "+tanggalSekarang.toString());
                    System.out.println("Waktu\t= "+waktuSekarang.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
                    System.out.println();
                }
            }    
   }
        }             
}
}
