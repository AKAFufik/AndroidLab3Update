package com.example.mydatabaseupdate;
import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button dataBase,enter,update;
    DataBaseHelper dbHelper;
    public int count=5;
    public String[] strArray1= new String[] {"Наумов","Молчанов","Лановой","Дементьев","Моисеев","Абрамов","Константинов ","Осипов ","Лебедев","Вишняков"};
    public String[] strArray2= new String[] {" Юлиан"," Цезарь"," Евстахий"," Шарль"," Даниил"," Борис"," Йошка"," Борис"," Марк"," Георгий"};
    public String[] strArray3= new String[] {" Леонидович"," Романович"," Антонинович"," Владимирович"," Вадимович"," Брониславович"," Александрович"," Андреевич"," Анатольевич"," Борисович"};
    Date currentDate = new Date();
    DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
    String timeText = timeFormat.format(currentDate);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper= new DataBaseHelper(this);

        final SQLiteDatabase database = dbHelper.getWritableDatabase();
        final ContentValues contentValues = new ContentValues();
        View.OnClickListener oclBtn1 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = database.query(DataBaseHelper.TABLE_CONTACTS, null, null, null, null, null, null);
                ArrayList BD = new ArrayList();
                if (cursor.moveToFirst()) {
                    int idIndex = cursor.getColumnIndex(DataBaseHelper.KEY_ID);
                    int nameIndex1 = cursor.getColumnIndex(DataBaseHelper.F);
                    int nameIndex2 = cursor.getColumnIndex(DataBaseHelper.I);
                    int nameIndex3 = cursor.getColumnIndex(DataBaseHelper.O);
                    int dateIndex = cursor.getColumnIndex(DataBaseHelper.dateOfAdd);

                    do {
                        BD.add("ID:" + cursor.getInt(idIndex)+" ФИО:" + cursor.getString(nameIndex1) + cursor.getString(nameIndex2) + cursor.getString(nameIndex3) +" время:"+ cursor.getString(dateIndex));
                        Log.d("mLog", "ID = " + cursor.getInt(idIndex) +
                                ", name = " + cursor.getString(nameIndex1) + cursor.getString(nameIndex2)+ cursor.getString(nameIndex1)+
                                ", email = " + cursor.getString(dateIndex));
                    } while (cursor.moveToNext());
                } else
                    Log.d("mLog","0 rows");
                cursor.close();
                Intent intent = new Intent(getApplicationContext(),StudentList.class);
                intent.putExtra("list", BD);
                startActivity(intent);
            }
        };
        View.OnClickListener oclBtn2 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentDate = new Date();
                timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
                timeText = timeFormat.format(currentDate);
                Random random = new Random();
                int num1 = random.nextInt(11);
                int num2 = random.nextInt(11);
                int num3 = random.nextInt(11);
                contentValues.put(DataBaseHelper.F, (strArray1[num1]));
                contentValues.put(DataBaseHelper.I, (strArray2[num2]));
                contentValues.put(DataBaseHelper.O, (strArray3[num3]));
                contentValues.put(DataBaseHelper.dateOfAdd,timeText);
                database.insert(DataBaseHelper.TABLE_CONTACTS, null, contentValues);
                count+=1;
            }
        };
        View.OnClickListener oclBtn3 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentDate = new Date();
                timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
                timeText = timeFormat.format(currentDate);
                ContentValues cv=new ContentValues();
                cv.put(DataBaseHelper.F,"Иванов ");
                cv.put(DataBaseHelper.I,"Иван");
                cv.put(DataBaseHelper.O," Иванович");
                cv.put(DataBaseHelper.dateOfAdd, timeText);
                database.update(DataBaseHelper.TABLE_CONTACTS,cv,"_id = ?",new String[] { (Integer.toString(count)) });
            }

        };
        dataBase = (Button) findViewById(R.id.db);
        dataBase.setOnClickListener(oclBtn1);

        enter= (Button) findViewById(R.id.enter);
        enter.setOnClickListener(oclBtn2);

        update= (Button) findViewById(R.id.ivan);
        update.setOnClickListener(oclBtn3);

        database.delete(DataBaseHelper.TABLE_CONTACTS, null, null);
        for (int x = 0; x < 5; x = x + 1) {
            Random random = new Random();
            int num1 = random.nextInt(11);
            int num2 = random.nextInt(11);
            int num3 = random.nextInt(11);
            contentValues.put(DataBaseHelper.F, strArray1[num1]);
            contentValues.put(DataBaseHelper.I,strArray2[num2]);
            contentValues.put(DataBaseHelper.O,strArray3[num3]);
            contentValues.put(DataBaseHelper.dateOfAdd,timeText);
            database.insert(DataBaseHelper.TABLE_CONTACTS, null, contentValues);
        }

    }




}