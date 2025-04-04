package com.intishaka.da.dialog.debug;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.intishaka.da.R;
import com.intishaka.da.dialog.base.BaseDialog;

public class DebugDialogSetting extends BaseDialog {

    protected DebugDialog.OnCancelPressed onCancelPressed;

    protected DebugDialog.OnOkPressed onOkPressed;

    protected int buttonColor = 0;
    protected CharSequence tvContentValue;
    protected String dBtnOkValue;
    protected Drawable shapeCanvas = null;
    protected double tvTitleSize = 0;
    protected double tvContentSize = 0;
    protected double dBtnTextSize = 0;
    protected int tvContentColor = 0;
    protected int btnTextColorOk = 0;
    protected int okIconL = 0;
    protected int okIconT = 0;
    protected int okIconR = 0;
    protected int okIconB = 0;
    protected int tvTitleAlignment = View.TEXT_ALIGNMENT_CENTER; //default from view
    private View _view;
    private LinearLayout _dialogCanvas;
    private LinearLayout _dialogCanvasContent;
    private LinearLayout _parentButton;
    private TextView _tvContent;
    private Button _dBtnOkMBT;
    private Button _dBtnCopyMBT;
    private int gravity = Gravity.CENTER;

    private void initView() {
        _dialogCanvas = _view.findViewById(R.id.dialog_canvas);
        _dialogCanvasContent = _view.findViewById(R.id.dialog_canvas_content);
        _parentButton = _view.findViewById(R.id.parent_button);
        _tvContent = _view.findViewById(R.id.tv_content);
        _dBtnOkMBT = _view.findViewById(R.id.d_btn_ok_MBT);
        _dBtnCopyMBT = _view.findViewById(R.id.d_btn_copy_MBT);
    }

    @Override
    protected int contentView() {
        return R.layout.debug_dialog;
    }

    public void onResume() {
        super.onResume();

        Window window = getDialog().getWindow();
        Point size = new Point();

        Display display = window.getWindowManager().getDefaultDisplay();
        display.getSize(size);

        int width;
        int height;

        width = size.x;
        height = WindowManager.LayoutParams.WRAP_CONTENT;

        window.setLayout((int) (width * 0.95), height);
        window.setGravity(gravity);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this._view = view;
        initView();
        initDesign();
        initOnClick();
    }

    private void initDesign() {
        if (shapeCanvas != null)
            _dialogCanvas.setBackground(shapeCanvas);

        if (tvContentValue != null)
            _tvContent.setText(tvContentValue);
        else
            _tvContent.setVisibility(View.GONE);

        if (tvContentSize != 0)
            _tvContent.setTextSize((float) tvContentSize);

        if (tvContentColor != 0)
            _tvContent.setTextColor(tvContentColor);
    }


    private void initOnClick() {
        _dBtnOkMBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onOkPressed != null)
                    onOkPressed.onOkPressed();
                getDialog().dismiss();
            }
        });
        _dBtnCopyMBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tvContentValue != null){
                    setClipboard(requireContext(), tvContentValue.toString());
                    Toast.makeText(requireContext(), "Copied To Clipboard", Toast.LENGTH_SHORT).show();
                }
                getDialog().dismiss();
            }
        });
    }

    private void setClipboard(Context context, String text) {
        if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            clipboard.setText(text);
        } else {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", text);
            clipboard.setPrimaryClip(clip);
        }
    }
}
