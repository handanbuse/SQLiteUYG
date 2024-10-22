package com.example.sqlte_uyg;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            database= this.openOrCreateDatabase("okul",MODE_PRIVATE, null ); // okul adında database oluştu
            database.execSQL("CREATE TABLE IF NOT EXISTS ogrenciler(ad VARCHAR,soyad VARCHAR,ogrenci_no INT)"); // oğrenciler tablosu oluştu ve kolonlar tanımlandı

        } catch (Exception e){
            // burada hata yakalamak için
            // logcate yazar
            e.printStackTrace();

        }


    }

    public void sqlite_btnclick(View v){
        if (v.getId()==R.id.sqlite_btnekle){
           try {
               database.execSQL("INSERT INTO ogrenciler(ad,soyad, ogrenci_no) VALUES('ahmetcan', 'pala', 123)");// kayıt eklendi
               getData();
               Toast.makeText(getApplicationContext(),"kayıt başarıyla eklendi",Toast.LENGTH_SHORT).show();

           }catch (Exception e){
               e.printStackTrace();
           }


        }
         else if (v.getId()==R.id.sqlite_btngüncelle){

             try {
                 database.execSQL("UPDATE ogrenciler SET ogrenci_no= 777 WHERE ad='handanbuse'");
                 getData();
                 Toast.makeText(getApplicationContext(),"kayıt güncellendi",Toast.LENGTH_SHORT).show();
             }catch (Exception e){
                 e.printStackTrace();
             }
        }

     else   if (v.getId()==R.id.sqlite_btnsil){

         try {
             database.execSQL("DELETE FROM ogrenciler WHERE ad='handan buse '");
             getData();
             Toast.makeText(getApplicationContext(),"kayıt silindi" ,Toast.LENGTH_SHORT).show();
         }
         catch (Exception e){
             e.printStackTrace();
         }
        }

     else   if (v.getId()==R.id.sqlite_btntablosil){

         try{
             database.execSQL("DROP TABLE ogrenciler");
         }catch (Exception e){
             e.printStackTrace();
         }
        }







    }
    private  void getData(){

        // kayıtları çek
        Cursor cursor= database.rawQuery("SELECT* FROM ogrenciler",null );

        int adIndex=cursor.getColumnIndex("ad");// kolon indexini buraya attık
        int soyadIndex= cursor.getColumnIndex("soyad");
        int ogrenci_noIndex= cursor.getColumnIndex("ogrenci_no");

        // verileri baştan sona alsın while döngüsü ile
        while (cursor.moveToNext())// bir sonrakine geçebilmeyi kontrol ediyor.

            System.out.println("ad=" +cursor.getString(adIndex)+ "soyad="+ cursor.getString(soyadIndex) + "öğrenci no =" + cursor.getInt(ogrenci_noIndex));
        cursor.close(); // tanımlanan nesneyi kapatmak gerek

    }
}
