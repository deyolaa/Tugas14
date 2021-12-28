import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Gaji implements PTABC {
    //static Scanner scanner;
    static Connection conn;
	String url = "jdbc:mysql://localhost:3306/pbotugas14";

    public Integer noPegawai, jumlahHariMasuk, noJabatan, gajiPokok, potongan, totalGaji;
    public String namaPegawai, jabatan;

    Scanner temp = new Scanner(System.in);

    public void show() throws SQLException {
        String judul = "Info Gaji";
        String judulDua = judul.replace("Info Gaji", "INFORMASI DAFTAR GAJI KARYAWAN"); 
        System.out.println(judulDua);
						
		String sql ="SELECT * FROM gaji";
		conn = DriverManager.getConnection(url,"root","");
		Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(sql);
		
		while(result.next()){
			System.out.print("\nNomor Pegawai\t\t : ");
            System.out.print(result.getString("noPegawai"));
            System.out.print("\nNama Pegawai\t\t : ");
            System.out.print(result.getString("namaPegawai"));
            System.out.print("\nGaji Pokok\t\t\t : ");
            System.out.print(result.getString("gajiPokok"));
            System.out.print("\nJumlah Hari Masuk\t : ");
            System.out.print(result.getInt("jumlahHariMasuk"));
            System.out.print("\nPotongan Gaji\t\t : ");
            System.out.print(result.getInt("potongan"));
            System.out.print("\nTotal gaji\t\t : ");
            System.out.print(result.getInt("totalGaji"));
            System.out.print("\n");
		}
	}

    public void insert() throws SQLException {
        String inputan2 = "\nInput Data Pegawai";
		System.out.println(inputan2.toUpperCase());
		
        try {
        // No Pegawai
        System.out.println("Masukkan Nomor Pegawai : ");
        noPegawai = temp.nextInt();

        // Nama Pegawai
        System.out.println("Masukkan Nama Pegawai : ");
        namaPegawai = temp.next();

        // Pilih Jabatan
        System.out.println("Jabatan : ");
        System.out.println("1 => Komisaris \n2 => Direktur \n3 => Manager \n4 => Pegawai \n5 => Keamanan dan Kebersihan");
        System.out.print("Masukkan Nomor Jabatan: ");
        noJabatan = temp.nextInt();
        
        if(noJabatan == 1){
            jabatan = "Komisaris";
        } 
        else if (noJabatan == 2){
            jabatan = "Direktur";
        } 
        else if (noJabatan == 3){
            jabatan = "Manager";
        } 
        else if (noJabatan == 4){
            jabatan = "Pegawai";
        }
        else if (noJabatan == 5){
            jabatan = "Keamanan dan Kebersihan";
        }
        else{
            System.out.println("Jabatan tidak tersedia");
        }
        
         // Gaji Pokok
        if(noJabatan == 1){
            gajiPokok = 50000000;
        }
        else if(noJabatan == 2){
            gajiPokok = 30000000;
        } 
        else if (noJabatan == 3){
            gajiPokok = 15000000;
        } 
        else if (noJabatan == 4){
            gajiPokok = 10000000;
        } 
        else if (noJabatan == 5){
            gajiPokok = 3000000;
        }
        else{
            System.out.println("\nGaji pokok tidak tersedia");
        }
        System.out.println("\nGaji pokok : Rp" + gajiPokok);

        // Jumlah Hari Masuk
        System.out.print("Inputkan Jumlah Hari Masuk: ");
        jumlahHariMasuk = temp.nextInt();
        
        //potongan
        potongan = (30-jumlahHariMasuk)*100000;
        System.out.println("\nPotongan: Rp" +potongan);

        //total gaji
        totalGaji = gajiPokok - potongan;
        System.out.println("Total gaji: Rp" + totalGaji);

        String sql = "INSERT INTO gaji (noPegawai, namaPegawai, jabatan, gajiPokok, jumlahHariMasuk, potongan, totalGaji) VALUES ('"+noPegawai+"','"+namaPegawai+"','"+jabatan+"','"+gajiPokok+"','"+jumlahHariMasuk+"','"+potongan+"','"+totalGaji+"')";
	    conn = DriverManager.getConnection(url,"root","");
	    Statement statement = conn.createStatement();
	    statement.execute(sql);
	    System.out.println("Input Data Berhasil!!");
    }
    catch (SQLException e) {
        System.err.println("Terjadi kesalahan input data");
    } 
    catch (InputMismatchException e) {
        System.err.println("Inputan harus berupa angka");
       }
    }

    public void edit() throws SQLException{
        System.out.println("\nEdit Data Pegawai");

        try {
            show();
            System.out.print("\nMasukkan Nomor Pegawai yang ingin di ubah : ");
            Integer noPegawai = Integer.parseInt(temp.nextLine());
            
            String sql = "SELECT * FROM gaji WHERE noPegawai = " +noPegawai;
            conn = DriverManager.getConnection(url,"root","");
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            
            if(result.next()){
                
                System.out.print("Nama baru ["+result.getString("namaPegawai")+"]\t: ");
                String namaPegawai = temp.nextLine();
                   
                sql = "UPDATE gaji SET namaPegawai='"+namaPegawai+"' WHERE noPegawai='"+noPegawai+"'";
                //System.out.println(sql);

                if(statement.executeUpdate(sql) > 0){
                    System.out.println("Data telah diperbarui (Nomor "+noPegawai+")");
                }
            }
            statement.close();        
        } 
		catch (SQLException e){
        	System.err.println("Terjadi kesalahan dalam mengedit data");
            System.err.println(e.getMessage());
        }
	}

    public void delete() {

    }

    public void search() throws SQLException {

    }
}
