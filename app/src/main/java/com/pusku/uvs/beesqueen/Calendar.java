package com.pusku.uvs.beesqueen;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class Calendar extends Activity {

    final String LOG_TAG = "myLogs";
    static ImageView imgOut;
    Button btnLeft;
    Button btnRight;
    Button btnSave;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        // найдем View-элементы
        imgOut = (ImageView) findViewById(R.id.imgOut);
        btnLeft = (Button) findViewById(R.id.btnLeft);
        btnRight = (Button) findViewById(R.id.btnRight);
        btnSave = (Button) findViewById(R.id.btnSave);



       View.OnClickListener oclBtnLeft = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Count.i < 30) Count.i++;
                else Count.i = 1;

                choice(Count.i);
            }
        };

        View.OnClickListener oclBtnRight = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Count.i > 1) Count.i--;
                else Count.i = 30;

                choice(Count.i);

            }
        };

       View.OnClickListener oclBtnSave = new View.OnClickListener() {
            @Override
            public void onClick(View v) {







                //Получаем вид с файла prompt.xml, который применим для диалогового окна:
                LayoutInflater li = getLayoutInflater();
                View promptsView = li.inflate(R.layout.prompt, null);

                //Создаем AlertDialog
                AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(getParent());

                //Настраиваем prompt.xml для нашего AlertDialog:
                mDialogBuilder.setView(promptsView);

                //Настраиваем отображение поля для ввода текста в открытом диалоге:
                final EditText userInput = (EditText) promptsView.findViewById(R.id.input_text);
                                userInput.setText("");
                //Настраиваем сообщение в диалоговом окне:
                if (mDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {



                                        Cursor c = null;
                                        // подключаемся к БД
                                        SQLiteDatabase db = dbHelper.getWritableDatabase();
                                        String name = userInput.getText().toString();
                                        String date = Count.i + "";
                                        String selection = null;
                                        String[] selectionArgs = null;

                                        Log.d(LOG_TAG, "--- Read on if: ---");
                                        selection = "nameoflabel = ?";
                                        selectionArgs = new String[]{name};
                                        c = db.query("labels", null, selection, selectionArgs, null, null, null);
                                            if (c.moveToFirst()||(name.length()<1)||(name.length()>11)) {
                                                Context context = getApplicationContext();
                                                CharSequence text = "Имя метки должно быть оригинальным и содержать от 1 до 10 символов ";
                                                int duration = Toast.LENGTH_SHORT;

                                                Toast toast = Toast.makeText(context, text, duration);
                                                toast.show();

                                            }

                                            else {
                                                // создаем объект для данных
                                                ContentValues cv = new ContentValues();
                                                Log.d(LOG_TAG, "--- Insert in labels: ---");
                                                // подготовим данные для вставки в виде пар: наименование столбца -
                                                // значение
                                                cv.put("nameoflabel", name);
                                                cv.put("dateoflabel", date);
                                                // вставляем запись и получаем ее ID
                                                long rowID = db.insert("labels", null, cv);
                                                Log.d(LOG_TAG, "row inserted, ID = " + rowID);
                                                // закрываем подключение к БД
                                                dbHelper.close();
                                            }

                                    }
                                })
                        .setNegativeButton("Отмена",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                }) != null) {

                }

                //Создаем AlertDialog:
                AlertDialog alertDialog = mDialogBuilder.create();

                //и отображаем его:
                alertDialog.show();










            }
        };

        btnLeft.setOnClickListener(oclBtnLeft);
        btnRight.setOnClickListener(oclBtnRight);
        btnSave.setOnClickListener(oclBtnSave);

        // создаем объект для создания и управления версиями БД
       dbHelper = new DBHelper(this);
    }



    static  void choice(int count) {
        //((BitmapDrawable)imgOut.getDrawable()).getBitmap().recycle();
        imgOut.destroyDrawingCache();
        switch (count) {

            case 1:
                imgOut.setImageResource(R.drawable.c01);
                break;
            case 2:
                imgOut.setImageResource(R.drawable.c02);
                break;
            case 3:
                imgOut.setImageResource(R.drawable.c03);
                break;
            case 4:
                imgOut.setImageResource(R.drawable.c04);
                break;
            case 5:
                imgOut.setImageResource(R.drawable.c05);
                break;
            case 6:
                imgOut.setImageResource(R.drawable.c06);
                break;
            case 7:
                imgOut.setImageResource(R.drawable.c07);
                break;
            case 8:
                imgOut.setImageResource(R.drawable.c08);
                break;
            case 9:
                imgOut.setImageResource(R.drawable.c09);
                break;
            case 10:
                imgOut.setImageResource(R.drawable.c10);
                break;
            case 11:
                imgOut.setImageResource(R.drawable.c11);
                break;
            case 12:
                imgOut.setImageResource(R.drawable.c12);
                break;
            case 13:
                imgOut.setImageResource(R.drawable.c13);
                break;
            case 14:
                imgOut.setImageResource(R.drawable.c14);
                break;
            case 15:
                imgOut.setImageResource(R.drawable.c15);
                break;
            case 16:
                imgOut.setImageResource(R.drawable.c16);
                break;
            case 17:
                imgOut.setImageResource(R.drawable.c17);
                break;
            case 18:
                imgOut.setImageResource(R.drawable.c18);
                break;
            case 19:
                imgOut.setImageResource(R.drawable.c19);
                break;
            case 20:
                imgOut.setImageResource(R.drawable.c20);
                break;
            case 21:
                imgOut.setImageResource(R.drawable.c21);
                break;
            case 22:
                imgOut.setImageResource(R.drawable.c22);
                break;
            case 23:
                imgOut.setImageResource(R.drawable.c23);
                break;
            case 24:
                imgOut.setImageResource(R.drawable.c24);
                break;
            case 25:
                imgOut.setImageResource(R.drawable.c25);
                break;
            case 26:
                imgOut.setImageResource(R.drawable.c26);
                break;
            case 27:
                imgOut.setImageResource(R.drawable.c27);
                break;
            case 28:
                imgOut.setImageResource(R.drawable.c28);
                break;
            case 29:
                imgOut.setImageResource(R.drawable.c29);
                break;
            case 30:
                imgOut.setImageResource(R.drawable.c30);
                break;


            default:
                imgOut.setImageResource(R.drawable.c01);
                break;
        }

    }





}
