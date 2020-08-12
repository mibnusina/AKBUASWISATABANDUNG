package com.example.a10117279_wisatabandung;

//Tanggal : 8 - Agustus - 2020
//NIM : 10117279
//Nama : Muhammad Ibnu Sina
//Kelas : IF-8

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    private DBDataSource dbDataSource;

    public static final String TABLE_NAME = "tbl_wisata";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAMA = "nama";
    public static final String COLUMN_DESKRIPSI = "deskripsi";
    public static final String COLUMN_LATITUDE = "latitude";
    public static final String COLUMN_LONGITUDE = "longitude";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_STATUS = "status";
    private static final String db_name = "db_wisata.db";
    private static final int db_version = 1;

    private static final String db_create = "CREATE TABLE IF NOT EXISTS "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAMA + " VARCHAR(100), "
            + COLUMN_DESKRIPSI + " VARCHAR(1000), "
            + COLUMN_LATITUDE + " VARCHAR(100), "
            + COLUMN_LONGITUDE + " VARCHAR(100),"
            + COLUMN_IMAGE + " VARCHAR(100),"
            + COLUMN_STATUS + " VARCHAR(5));";

    public static final String tangkuban_perahu = "Gunung Tangkuban Parahu adalah salah satu gunung yang terletak " +
            "di Provinsi Jawa Barat, Indonesia. Sekitar 20 km ke arah utara Kota Bandung, dengan rimbun " +
            "pohon pinus dan hamparan kebun teh di sekitarnya, Gunung Tangkuban Parahu mempunyai ketinggian " +
            "setinggi 2.084 meter. Bentuk gunung ini adalah Stratovulcano dengan pusat erupsi yang berpindah " +
            "dari timur ke barat. Jenis batuan yang dikeluarkan melalui letusan kebanyakan adalah lava dan " +
            "sulfur, mineral yang dikeluarkan adalah sulfur belerang, mineral yang dikeluarkan saat gunung " +
            "tidak aktif adalah uap belerang. Daerah Gunung Tangkuban Parahu dikelola oleh Perum Perhutanan. " +
            "Suhu rata-rata hariannya adalah 17 °C pada siang hari dan 2 °C pada malam hari.";

    public static final String kawah_putih = "Kawah Putih adalah sebuah tempat wisata di Jawa Barat yang " +
            "terletak di desa Alam Endah, Kecamatan Rancabali, Kabupaten Bandung Jawa Barat yang terletak di" +
            " kaki Gunung Patuha. Kawah putih merupakan sebuah danau yang terbentuk dari letusan Gunung Patuha." +
            " Tanah yang bercampur belerang di sekitar kawah ini berwarna putih, lalu warna air yang berada di" +
            " kawah ini berwarna putih kehijauan, yang unik dari kawah ini adalah airnya kadang berubah warna." +
            " Danau Kawah Putih sendiri berada pada ketinggian 2194 m tapi luas total Danau Kawah Putih 25 ha" +
            " yang dipakai wisata 5 ha dan lokasi kawah sendiri 3 ha.";

    public static final String gunung_puntang = "Gunung Puntang berlokasi di Desa Cimaung, Kecamatan Banjaran, " +
            "Kabupaten Bandung. Kurang lebih memerlukan waktu sekitar 2 jam dari pusat kota untuk mencapai lokasi. " +
            "Setibanya di Banjaran, mengarahkan kendaraan menuju Pangalengan. Terdapat 2 jalur pendakian, " +
            "bisa melalui pos Perhutani dan pos PGPI (Persatuan Gunung Puntang Indonesia). Sementara tiket " +
            "masuknya masih terjangkau, hanya Rp 22.500 per orang dan parkir Rp 2.000 untuk motor, Rp 10.000 " +
            "untuk mobil.";

    public static final String asia_afrika = "The Great Asia Africa merupakan satu sebuah wahana wisata yang ada di Bandung.\n" +
            "Tepatnya, The Great Asia Africa berada di Lembang, Kabupaten Bandung Barat.\n" +
            "The Great Asia Africa menyuguhkan suasana dan bangunan dari tujuh negara dan dua benua.\n" +
            "Baru di buka tahun 2019 ini, The Great Asia Africa sempat viral di media sosial.\n" +
            "Hal itu tidak lain karena suasananya yang seakan-akan membawa pengunjung seperti liburan di luar negeri.\n" +
            "Seiring dengan berjalannya waktu, wisatawan yang berkunjung ke tempat ini berasal dari beragam daerah, tak hanya dari wilayah Bandung dan Jawa Barat saja.\n" +
            "The Great Asia Africa mengusung konsep edukasi dan budaya.";

    private static final String query = "INSERT INTO " + TABLE_NAME +
            "(nama, deskripsi, latitude, longitude, image, status)" +
            " VALUES ('Tangkuban Perahu', '" + tangkuban_perahu + "', '-6.7596', '107.6098', 'tangkuban_perahu', '0');";

    private static final String query1 = "INSERT INTO " + TABLE_NAME +
            "(nama, deskripsi, latitude, longitude, image, status)" +
            " VALUES ('Kawah Putih', '" + kawah_putih + "', '-7.1662', '107.4021', 'kawah_putih', '0');";

    private static final String query2 = "INSERT INTO " + TABLE_NAME +
            "(nama, deskripsi, latitude, longitude, image, status)" +
            " VALUES ('Gunung Puntang', '" + gunung_puntang + "', '-7.1297', '107.6381', 'gunung_puntang', '0');";

    private static final String query3 = "INSERT INTO " + TABLE_NAME +
            "(nama, deskripsi, latitude, longitude, image, status)" +
            " VALUES ('The Great Asia Afrika', '" + asia_afrika + "', '-6.8331', '107.6042', 'asia_afrika', '0');";


    public DBHelper(Context context) {
        super(context, db_name, null, db_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(db_create);
        db.execSQL(query);
        db.execSQL(query1);
        db.execSQL(query2);
        db.execSQL(query3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DBHelper.class.getName(), "Upgrading database from version "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
