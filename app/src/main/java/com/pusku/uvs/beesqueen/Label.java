package com.pusku.uvs.beesqueen;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class Label extends Activity {
    LinearLayout llLabel;
    final String LOG_TAG = "myLogs";
    DBHelper dbHelper;
    int[] colors = new int[2];
    static  String str="";



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_label);
        llLabel = (LinearLayout) findViewById(R.id.llLabel);

        View.OnClickListener oclDel = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ad;

                Button btn=(Button)v;
                Label.str =btn.getText().toString();

                ad = new AlertDialog.Builder(getParent());
                ad.setTitle("Удаление");  // заголовок
                ad.setMessage("Настаиваете на удалении?"); // сообщение
                ad.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {


                        // подключаемся к БД
                        SQLiteDatabase db = dbHelper.getWritableDatabase();

                        Log.d(LOG_TAG, "--- Delete from labels: ---");

                        // удаляем по id
                        int delCount = db.delete("labels", "id = "+Label.str , null);
                        Log.d(LOG_TAG, "deleted rows count = " + delCount);

                        onResume();
                }
                });
                ad.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {

                    }
                });
              /*  ad.setCancelable(true);
                ad.setOnCancelListener(new OnCancelListener() {
                    public void onCancel(DialogInterface dialog) {
                        Toast.makeText(context, "Вы ничего не выбрали",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }*/

                ad.show();

            }
        };

        View.OnClickListener oclOk = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn=(Button)v;
                Count.i=Integer.parseInt(btn.getText().toString());
                MainActivity.tabHost.setCurrentTabByTag("tag1");







            }
        };

        colors[0] = Color.parseColor("#FCFC00");
        colors[1] = Color.parseColor("#8C8C8C");

        LinearLayout linLayout = (LinearLayout) findViewById(R.id.linLayout);

        LayoutInflater ltInflater = getLayoutInflater();

        // создаем объект для создания и управления версиями БД
        dbHelper = new DBHelper(this);
        // подключаемся к БД
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Log.d(LOG_TAG, "--- Rows in labels: ---");
        // делаем запрос всех данных из таблицы labels, получаем Cursor
        Cursor c = db.query("labels", null, null, null, null, null, null);

        // ставим позицию курсора на первую строку выборки
        // если в выборке нет строк, вернется false
        if (c.moveToFirst()) {

            // определяем номера столбцов по имени в выборке
            int idColIndex = c.getColumnIndex("id");
            int nameColIndex = c.getColumnIndex("nameoflabel");
            int dateColIndex = c.getColumnIndex("dateoflabel");
           int i=1;
            do {
                int id =c.getInt(idColIndex);

                String text = "ID = " +id + ", nameoflabel = "
                        + c.getString(nameColIndex) + ", dateoflabel = "
                        + c.getString(dateColIndex);
                // получаем значения по номерам столбцов и пишем все в лог
                Log.d(LOG_TAG, text);
                View item = ltInflater.inflate(R.layout.item, linLayout, false);
                TextView tvName = (TextView) item.findViewById(R.id.tvName);
                tvName.setTextColor( colors[(i+1)%2]);
                tvName.setText(c.getString(nameColIndex));

                Button btnDel= (Button) item.findViewById(R.id.btnDel);
                btnDel.setText(""+id);
                btnDel.setWidth(btnDel.getHeight());
                btnDel.setOnClickListener(oclDel);

                Button btnOk= (Button) item.findViewById(R.id.btnOk);
                btnOk.setText(c.getString(dateColIndex));
                btnOk.setOnClickListener(oclOk);

                item.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
                item.setBackgroundColor(colors[i % 2]);
                i++;
                linLayout.addView(item);
                // переход на следующую строку
                // а если следующей нет (текущая - последняя), то false -
                // выходим из цикла

            } while (c.moveToNext());
            // закрываем подключение к БД
            dbHelper.close();
        } else
            Log.d(LOG_TAG, "0 rows");
        c.close();



    }



    @Override
    protected void onResume() {

        super.onResume();
        this.onCreate(null);
    }
}
