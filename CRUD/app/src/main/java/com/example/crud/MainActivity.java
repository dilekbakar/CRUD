package com.example.crud;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText Ad, SoyAd, Tel;
    Button insert, update, delete, view;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Ad= findViewById(R.id.edtAd);
        SoyAd= findViewById(R.id.edtSoyAd);
        Tel= findViewById(R.id.edtTel);

        insert=findViewById(R.id.btnInsert);
        update=findViewById(R.id.btnUpdate);
        delete=findViewById(R.id.btnDelete);
        view=findViewById(R.id.btnView);

        DB= new DBHelper(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtAd = Ad.getText().toString();
                String txtSoyad = SoyAd.getText().toString();
                String txtTel = Tel.getText().toString();

                Boolean checkinsertdata = DB.insertData(txtAd, txtSoyad, txtTel);

                if (checkinsertdata == true){
                    Toast.makeText(MainActivity.this, "Yeni Kayıt Oluşturuldu",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Yeni Kayıt Oluşturulamadı",Toast.LENGTH_SHORT).show();

                }
            }

        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtAd = Ad.getText().toString();
                String txtSoyad = SoyAd.getText().toString();
                String txtTel = Tel.getText().toString();

                Boolean checkupdatedata = DB.updateData(txtAd, txtSoyad, txtTel);

                if (checkupdatedata == true){
                    Toast.makeText(MainActivity.this, "Kayıt Güncellendi",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Kayıt Güncellenemedi",Toast.LENGTH_SHORT).show();

                }
            }

        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtAd = Ad.getText().toString();

                Boolean checkdeletedata = DB.deleteData(txtAd);

                if (checkdeletedata == true){
                    Toast.makeText(MainActivity.this, "Kayıt Silindi",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Kayıt Silinemedi",Toast.LENGTH_SHORT).show();

                }
            }

        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = DB.getData();

                if (cursor.getCount() == 0){
                    Toast.makeText(MainActivity.this, "Kayıt Bulunamadı",Toast.LENGTH_SHORT).show();
                    return;

                }

                StringBuffer sb = new StringBuffer();
                while (cursor.moveToNext()){
                    sb.append("Ad : " + cursor.getString(0) + "\n");
                    sb.append("Soyad : " + cursor.getString(1) + "\n");
                    sb.append("Telefon : " + cursor.getString(2) + "\n\n");

                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Kullanıcı Bilgileri");
                builder.setMessage(sb.toString());
                builder.show();
            }
        });
    }
}