package com.intishaka.dialogandroid;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.timepicker.TimeFormat;
import com.intishaka.da.constant.ButtonStyle;
import com.intishaka.da.constant.DialogType;
import com.intishaka.da.dialog.confirmDialog.ConfirmDialog;
import com.intishaka.da.dialog.datePickerDialog.multi.MultiDatePickerDialog;
import com.intishaka.da.dialog.datePickerDialog.single.SingleDatePickerDialog;
import com.intishaka.da.dialog.debug.DebugDialog;
import com.intishaka.da.dialog.infoDialog.InfoDialog;
import com.intishaka.da.dialog.loadingDialog.LoadingDialog;
import com.intishaka.da.dialog.numberPicker.NumberPickerDialog;
import com.intishaka.da.dialog.timePickerDialog.TimeDialog;
import com.gzeinnumer.est.SpannableBuilder;

public class MainActivity extends AppCompatActivity {
    CharSequence sequence;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int sizeInDp = 21;
        int color = ContextCompat.getColor(this, R.color.purple_500);

        sequence = new SpannableBuilder(getApplicationContext())
                .text(Typeface.NORMAL,"NORMAL ")
                .textColor(Typeface.BOLD,"BOLD ", color)
                .textSize(Typeface.ITALIC,"ITALIC ", sizeInDp)
                .textSizeColor(Typeface.BOLD_ITALIC,"BOLD_ITALIC ", sizeInDp, color)
                .build();

        confirmDialog();
        infoDialog();
        numberPickerDialog();
        loadingDialog();
        singleDatePickerDialog();
        multiDatePickerDialog();
        timeDialog();
        debugDialog();
    }

    private void confirmDialog() {
        findViewById(R.id.ConfirmDialog).setOnClickListener(v -> new ConfirmDialog(getSupportFragmentManager())
                .setTitle("ini title")
                .setContent("ini content")
//                .setContent(sequence)
                .setButtonStyle(ButtonStyle.ButtonOutlined)
                .onCancelPressedCallBack(() -> Toast.makeText(MainActivity.this, "Cancel", Toast.LENGTH_SHORT).show())
                .onOkPressedCallBack(() -> Toast.makeText(MainActivity.this, "Ok", Toast.LENGTH_SHORT).show())
                .show());
    }

    private void infoDialog() {
        findViewById(R.id.InfoDialog).setOnClickListener(v -> new InfoDialog(getSupportFragmentManager())
                .setAnimationStyle(R.style.CustomDialogStyle)
                .setButtonAllCaps(false)
                .autoDismisOnSecond(5)
                .setButtonStyle(ButtonStyle.ButtonOutlined)
                .setButtonGravity(Gravity.CENTER)
                .setContentAlignment(View.TEXT_ALIGNMENT_CENTER)
                .setTitleColor(getResources().getColor(R.color.black))
//                .setButtonColor(getResources().getColor(R.color.colorPrimary))
                .setDialogType(DialogType.DialogSuccess)
                .setTitle("ini title")
                .setContent("ini content")
//                .setContent(sequence)
                .onOkPressedCallBack(() -> Toast.makeText(MainActivity.this, "Ok", Toast.LENGTH_SHORT).show())
                .show());
    }

    private void numberPickerDialog() {
        findViewById(R.id.NumberPickerDialog).setOnClickListener(v -> new NumberPickerDialog(getSupportFragmentManager())
                .setLastValue(12)
                .setTitle("ini title")
//                .setContent("ini content")
                .setContent(sequence)
                .setButtonStyle(ButtonStyle.ButtonOutlined)
                .onOkPressedCallBack(value -> Toast.makeText(MainActivity.this, "Nilai nya " + value, Toast.LENGTH_SHORT).show())
                .onCancelPressedCallBack(() -> Toast.makeText(MainActivity.this, "Cancel", Toast.LENGTH_SHORT).show())
                .show());
    }

    private void loadingDialog() {
        findViewById(R.id.LoadingDialog).setOnClickListener(v -> {
            LoadingDialog loadingDialog = new LoadingDialog(getSupportFragmentManager())
                .setContent("ini content");
//                    .setContent(sequence);

            loadingDialog.show();

            new Handler().postDelayed(() -> loadingDialog.dismis(), 4000);
        });
    }

    private void singleDatePickerDialog() {
        findViewById(R.id.SingleDatePickerDialog).setOnClickListener(v -> new SingleDatePickerDialog(getSupportFragmentManager())
                .setTimeZone("GMT")
//                .setTitle("Pilih tanggal")
                .setTitle(sequence)
                .setSelectedToday(true)
                .setTimeFormat("dd/MM/yyyy") //pastikan polanya sama
                .setStartDate("1/08/2020") //pastikan polanya sama
                .setEndDate("31/12/2020") //pastikan polanya sama
                .onOkPressedCallBack(value -> Toast.makeText(MainActivity.this, value, Toast.LENGTH_SHORT).show())
                .build()
                .show());
    }

    private void multiDatePickerDialog() {
        findViewById(R.id.MultiDatePickerDialog).setOnClickListener(v -> new MultiDatePickerDialog(getSupportFragmentManager())
                .setTimeZone("GMT")
//                .setTitle("Pilih tanggal")
                .setTitle(sequence)
                .setTimeFormat("dd/MM/yyyy") //pastikan 3 pola ini sama
                .setStartDate("1/08/2020") //pastikan 3 pola ini sama
                .setEndDate("31/12/2020") //pastikan 3 pola ini sama
                .onOkPressedCallBack((firstDate, secondDate) -> Toast.makeText(MainActivity.this, firstDate + " - " + secondDate, Toast.LENGTH_SHORT).show())
                .build()
                .show());
    }

    private void timeDialog() {
        findViewById(R.id.TimeDialog).setOnClickListener(v -> new TimeDialog(getSupportFragmentManager())
//                .setTitle("Time")
                .setTitle(sequence)
                .setHour(17)
                .setMinute(17)
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .addOnPositiveButtonClickListener((hours, minutes) -> Toast.makeText(MainActivity.this, hours + ":" + minutes, Toast.LENGTH_SHORT).show())
                .build()
                .show());
    }

    private void debugDialog(){
        findViewById(R.id.DebugDialog).setOnClickListener(v -> new DebugDialog(getSupportFragmentManager())
                .setAnimationStyle(R.style.CustomDialogStyle)
                .setContent(DumpJSON.msg1)
                .onOkPressedCallBack(() -> {

                })
                .show());
    }
}