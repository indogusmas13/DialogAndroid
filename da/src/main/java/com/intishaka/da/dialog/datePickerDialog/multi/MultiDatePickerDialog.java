package com.intishaka.da.dialog.datePickerDialog.multi;

import androidx.fragment.app.FragmentManager;

import com.intishaka.da.constant.DateScreenStyle;

public class MultiDatePickerDialog extends MultiDatePickerDialogSetting {

    private static final String TAG = "CustomDialog";
    private FragmentManager context;

    public MultiDatePickerDialog() {
    }

    public MultiDatePickerDialog(FragmentManager context) {
        this.context = context;
        builder = buildBuilder();
    }

    //TITLE
    public MultiDatePickerDialog setTitle(String title) {
        this.tvTitleValue = (CharSequence)title;
        return this;
    }

    public MultiDatePickerDialog setTitle(CharSequence title) {
        this.tvTitleValue = title;
        return this;
    }

    //TIME ZONE
    public MultiDatePickerDialog setTimeZone(String timeZone) {
        this.timeZone = timeZone;
        return this;
    }

    //ACTION CALLBACK
    public MultiDatePickerDialog onCancelPressedCallBack(OnCancelPressed callBack) {
        this.onCancelPressed = callBack;
        return this;
    }

    public MultiDatePickerDialog onOkPressedCallBack(OnOkPressed callBack) {
        this.onOkPressed = callBack;
        return this;
    }

    public MultiDatePickerDialog build() {
        buildDialog();
        return this;
    }

    public void show() {
        showDialog(context);
    }

    public MultiDatePickerDialog setTimeFormat(String timeFormat) {
        this.timeFormat = timeFormat;
        return this;
    }

    public MultiDatePickerDialog setStartDate(String startDate) {
        this.startDate = startDate;
        return this;
    }

    public MultiDatePickerDialog setEndDate(String endDate) {
        this.endDate = endDate;
        return this;
    }

    public MultiDatePickerDialog setDialogScreen(DateScreenStyle dateScreenStyle) {
        this.dateScreenStyle = dateScreenStyle;
        return this;
    }

    public MultiDatePickerDialog setTheme(int dialogTheme) {
        this.dialogTheme = dialogTheme;
        return this;
    }

    //callback
    public interface OnCancelPressed {
        void onCancelPressed();
    }

    public interface OnOkPressed {
        void onOkPressed(String firstDate, String secondDate);
    }
}