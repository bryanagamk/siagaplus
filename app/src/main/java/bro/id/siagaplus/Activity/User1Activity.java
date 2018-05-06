package bro.id.siagaplus.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import bro.id.siagaplus.Helper.DatabaseHelper;
import bro.id.siagaplus.R;
import bro.id.siagaplus.Utils.AlertDialogManager;
import bro.id.siagaplus.Utils.SessionManager;
import bro.id.siagaplus.Utils.SharedPref;

public class User1Activity extends AppCompatActivity {

    // Database Helper
    DatabaseHelper db;

    // Username
    EditText txtUsername, txtPassword;

    // login button
    Button btnOK;

    // Alert Dialog Manager
    AlertDialogManager alert = new AlertDialogManager();

    // Session Manager Class
    SessionManager session;

    // Session Manager Class
    SharedPref status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user1);

        db = new DatabaseHelper(getApplicationContext());

        // Session Manager
        session = new SessionManager(getApplicationContext());
        status = new SharedPref(getApplicationContext());

        btnOK = (Button) findViewById(R.id.confirm_user);
        txtUsername = (EditText) findViewById(R.id.userNama);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String valueNama = txtUsername.getText().toString();
                status.createStatus(valueNama);
                Toast.makeText(getApplicationContext(), "Halo, Ibu " +valueNama, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getBaseContext(), User11Activity.class);
                intent.putExtra("txtUsername",valueNama);
                startActivity(intent);
                finish();
            }
        });

        initDatablmhamil();
        initTrisemester1();
        initTrisemester2();
        initTrisemester3();
        setelahMelahirkan();

    }

    private void initDatablmhamil(){

        long id1 = db.createChecklist(1, 0, "Tes Urin",0);
        long id2 = db.createChecklist(2, 0, "Pemeriksaan TT(Tetanus Toxoid)",0);
        long id3 = db.createChecklist(3, 0, "Imunisasi Tetanus Toxoid",0);
        long id4 = db.createChecklist(4, 0, "Pemeriksaan TORCH",0);
        long id5 = db.createChecklist(5, 0, "Tes kesehatan umum",0);
        long id6 = db.createChecklist(6, 0, "Tes penyakit seksual menular",0);
        long id7 = db.createChecklist(7, 0, "Tes darah lengkap",0);
        long id8 = db.createChecklist(8, 0, "Tes glukosa darah",0);
        long id9 = db.createChecklist(9, 0, "Hentikan mengkonsumsi alkohol",0);
        long id10 = db.createChecklist(10, 0, "Kurangi konsumsi kafein",0);
        long id11 = db.createChecklist(11, 0, "Mulai Rutin berolahraga",0);
        long id12 = db.createChecklist(12, 0, "Mengkonsumsi sayur dan buah secara rutin",0);
        long id13 = db.createChecklist(13, 0, "Konsumsi air putih miniman 2L perhari",0);
        long id14 = db.createChecklist(14, 0, "Mengkonsumsi suplemen atau vitamin",0);
        long id15 = db.createChecklist(15, 0, "Kontrol ke dokter gigi untuk cek kesehatan gigi dan mulut",0);
        long id16 = db.createChecklist(16, 0, "Sikat gigi rutin 2x sehari",0);
        long id17 = db.createChecklist(17, 0, "Sikat gigi setelah sarapan dan sebelum tidur",0);
        long id18 = db.createChecklist(18, 0, "Pembersihan karang gigi",0);
        long id19 = db.createChecklist(19, 0, "Menambal gigi berlubang (jika ada)",0);
        long id20 = db.createChecklist(20, 0, "Menggunakan benang gigi (dental floss)",0);
        long id21 = db.createChecklist(21, 0, "Mempersiapkan budget",0);
        long id22 = db.createChecklist(22, 0, "Menentukan tanggal dan tempat",0);
        long id23 = db.createChecklist(23, 0, "Menyusun daftar tamu undangan",0);
        long id24 = db.createChecklist(24, 0, "Menghubungi vendor/eo pernikahan",0);
        long id25 = db.createChecklist(25, 0, "Mempersiapkan baju pernikahan",0);
        long id26 = db.createChecklist(26, 0, "Menghubungi catering",0);
    }

    private void initTrisemester1(){
        long id1 = db.createChecklist(1, 1, "Pemeriksaan tekanan darah ketika awal memasuki masa kehamilan",0);
        long id2 = db.createChecklist(2, 1, "Pemeriksaan darah lengkap yang bertujuan untuk mengetahui kemungkinan adanya kelainan pada komponen darah secara keseluruhan",0);
        long id3 = db.createChecklist(3, 1, "Periksa golongan darah dan rhesus",0);
        long id4 = db.createChecklist(4, 1, "Pemeriksaan darah rutin (untuk mengetahui resiko anemia, gangguan pembekuan darah, atau adanya infeksi)",0);
        long id5 = db.createChecklist(5, 1, "Pemeriksaan glukosa darah",0);
        long id6 = db.createChecklist(6, 1, "Pemeriksaan  HbsAg (untuk mendeteksi virus hepatitis B), tes Anti HBs (untuk mendeteksi antibodi pada hepatitis), dan Anti HCV (untuk mendeteksi virus hepatitis C).",0);
        long id7 = db.createChecklist(7, 1, "Pemeriksaan serologi untuk mengetahui ada tidaknya potensi penyakit sifilis",0);
        long id8 = db.createChecklist(8, 1, "Pemeriksaan anti HIV untuk mendeteksi kemungkinan virus HIV yang bisa menular kepada calon bayi",0);
        long id9 = db.createChecklist(9, 1, "Pemeriksaan laboratorium Pemeriksaan hormon ini dilakukan pada hormon bHCG-9 mendeteksi kehamilan di awal kehamilan), hormon progresteron (untuk mendeteksi normal atau tidaknya kadar hormon progresteron), dan juga hormon estradiol (untuk mendukung kehamilan itu sendiri). Jika hormon ibu hamil tidak normal, maka dokter akan bisa memberikan rekomendasi atau cara-cara untuk bisa menormalkan hormon tersebut",0);
        long id10 = db.createChecklist(10, 1, "Pemeriksaan virus TORCH digunakan untuk mengetahui virus yang bisa menyebabkan berbagai penyakit bawaan yang bisa diturunkan ke janin ",0);
        long id11 = db.createChecklist(11, 1, "Istirahat yang cukup ",0);
        long id12 = db.createChecklist(12, 1, "Kurangi aktivitas yang berat",0);
        long id13 = db.createChecklist(13, 1, "Hentikan kebiasaan merokok ",0);
        long id14 = db.createChecklist(14, 1, "Rutin kontrol kehamilan dipuskesmas atau klinik terdekat ",0);
        long id15 = db.createChecklist(15, 1, "Hentikan mengkonsumsi alkohol ",0);
        long id16 = db.createChecklist(16, 1, "Kurangi konsumsi kafein ",0);
        long id17 = db.createChecklist(17, 1, "Mulai Rutin berolahraga atau mengikuti kelas senam hamil ",0);
        long id18 = db.createChecklist(18, 1, "Mengkonsumsi sayur dan buah secara rutin ",0);
        long id19 = db.createChecklist(19, 1, "Konsumsi air putih miniman 2L perhari ",0);
        long id20 = db.createChecklist(20, 1, "Mengkonsumsi suplemen atau vitamin",0);
        long id21 = db.createChecklist(21, 1, "Kontrol ke dokter gigi untuk cek kesehatan gigi dan mulut",0);
        long id22 = db.createChecklist(22, 1, "Sikat gigi rutin 2x sehari  ",0);
        long id23 = db.createChecklist(23, 1, "Sikat gigi setelah sarapan dan sebelum tidur ",0);
        long id24 = db.createChecklist(24, 1, "Pembersihan karang gigi",0);
        long id25 = db.createChecklist(25, 1, "Menggunakan benang gigi (dental floss)",0);
    }

    private void initTrisemester2(){
        long id1 = db.createChecklist(1, 2, "Istirahat yang cukup ",0);
        long id2 = db.createChecklist(2, 2, "Kurangi aktivitas yang berat",0);
        long id3 = db.createChecklist(3, 2, "Hentikan kebiasaan merokok ",0);
        long id4 = db.createChecklist(4, 2, "Rutin kontrol kehamilan dipuskesmas atau klinik terdekat ",0);
        long id5 = db.createChecklist(5, 2, "Hentikan mengkonsumsi alkohol ",0);
        long id6 = db.createChecklist(6, 2, "Kurangi konsumsi kafein ",0);
        long id7 = db.createChecklist(7, 2, "Mulai Rutin berolahraga atau mengikuti kelas senam hamil ",0);
        long id8 = db.createChecklist(8, 2, "Mengkonsumsi sayur dan buah secara rutin ",0);
        long id9 = db.createChecklist(9, 2, "Konsumsi air putih miniman 2L perhari ",0);
        long id10 = db.createChecklist(10, 2, "Mengkonsumsi suplemen atau vitamin",0);
        long id11 = db.createChecklist(11, 2, "Kontrol ke dokter gigi untuk cek kesehatan gigi dan mulut",0);
        long id12 = db.createChecklist(12, 2, "Sikat gigi rutin 2x sehari  ",0);
        long id13 = db.createChecklist(13, 2, "Sikat gigi setelah sarapan dan sebelum tidur ",0);
        long id14 = db.createChecklist(14, 2, "Pembersihan karang gigi pada trimester kedua",0);
        long id15 = db.createChecklist(15, 2, "Menggunakan benang gigi (dental floss)",0);
        long id16 = db.createChecklist(16, 2, "Menambal gigi berlubang (jika ada)",0);
    }

    private void initTrisemester3(){
        long id1 = db.createChecklist(1, 3, "Istirahat yang cukup ",0);
        long id2 = db.createChecklist(2, 3, "Kurangi aktivitas yang berat",0);
        long id3 = db.createChecklist(3, 3, "Hentikan kebiasaan merokok ",0);
        long id4 = db.createChecklist(4, 3, "Rutin kontrol kehamilan dipuskesmas atau klinik terdekat ",0);
        long id5 = db.createChecklist(5, 3, "Hentikan mengkonsumsi alkohol ",0);
        long id6 = db.createChecklist(6, 3, "Kurangi konsumsi kafein ",0);
        long id7 = db.createChecklist(7, 3, "Mulai Rutin berolahraga atau mengikuti kelas senam hamil ",0);
        long id8 = db.createChecklist(8, 3, "Mengkonsumsi sayur dan buah secara rutin ",0);
        long id9 = db.createChecklist(9, 3, "Konsumsi air putih miniman 2L perhari ",0);
        long id10 = db.createChecklist(10, 3, "Mengkonsumsi suplemen atau vitamin ",0);
        long id11 = db.createChecklist(11, 3, "Sikat gigi rutin 2x sehari  ",0);
        long id12 = db.createChecklist(12, 3, "Sikat gigi setelah sarapan dan sebelum tidur ",0);
        long id13 = db.createChecklist(13, 3, "Menggunakan benang gigi (dental floss)",0);
        long id14 = db.createChecklist(14, 3, "Pemilihan rumah sakit yang tepat",0);
        long id15 = db.createChecklist(15, 3, "Pemilihan cara melahirkan sesuai dengan kondisi ibu hamil  ",0);
        long id16 = db.createChecklist(16, 3, "Siap menjadi orang tua siaga",0);
        long id17 = db.createChecklist(17, 3, "Menyiapkan perlengkapan bayi ",0);
        long id18 = db.createChecklist(18, 3, "Menyiapkan perlengkapan menyusui ",0);
    }

    private void setelahMelahirkan(){
        long id1 = db.createChecklist(1, 4, "Kontrol ke dokter gigi untuk cek kesehatan gigi dan mulut",0);
        long id2 = db.createChecklist(2, 4, "Sikat gigi rutin 2x sehari  ",0);
        long id3 = db.createChecklist(3, 4, "Sikat gigi setelah sarapan dan sebelum tidur ",0);
        long id4 = db.createChecklist(4, 4, "Pembersihan karang gigi",0);
        long id5 = db.createChecklist(5, 4, "Menambal gigi berlubang (jika ada)",0);
        long id6 = db.createChecklist(6, 4, "Menggunakan benang gigi (dental floss)",0);
        long id7 = db.createChecklist(7, 4, "Mencabut sisa akar gigi (jika ada)",0);
        long id8 = db.createChecklist(8, 4, "Hentikan mengkonsumsi alkohol ",0);
        long id9 = db.createChecklist(9, 4, "Kurangi konsumsi kafein ",0);
        long id10 = db.createChecklist(10, 4, "Mulai Rutin berolahraga ",0);
        long id11 = db.createChecklist(11, 4, "Mengkonsumsi sayur dan buah secara rutin ",0);
        long id12 = db.createChecklist(12, 4, "Konsumsi air putih miniman 2L perhari ",0);
        long id13 = db.createChecklist(13, 4, "Mengkonsumsi suplemen atau vitamin ",0);
        long id14 = db.createChecklist(14, 4, "Membersihkan gusi anak dengan kasa/waslap",0);
        long id15 = db.createChecklist(15, 4, "Membersihkan lidah anak setelah minum asi",0);
        long id16 = db.createChecklist(16, 4, "Membersihkan mulut bayi dengan soft brush",0);
        long id17 = db.createChecklist(17, 4, "Memeriksakan gigi anak segera setelah gigi susu pertama tumbuh",0);
        long id18 = db.createChecklist(18, 4, "Tes APGAR (Appearance, Pulse, Grimace, Activity, Respiration)",0);
        long id19 = db.createChecklist(19, 4, "Tes Fisik ",0);
        long id20 = db.createChecklist(20, 4, "Tes Mata",0);
        long id21 = db.createChecklist(21, 4, "Tes refleks",0);
        long id22 = db.createChecklist(22, 4, "Tes vitamin K",0);
        long id23 = db.createChecklist(23, 4, "Tes vitamin K",0);
    }
}
