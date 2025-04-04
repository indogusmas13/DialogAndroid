package com.intishaka.da.dialog.loadingDialog;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.intishaka.da.dialog.base.BaseDialog;
import com.intishaka.da.R;

public class LoadingDialogSetting extends BaseDialog {

    protected CharSequence tvContentValue;
    protected Drawable shapeCanvas = null;
    protected double tvContentSize = 0;
    protected int tvContentColor = 0;

    protected int colorBackground = 0;
    protected int tvContentAlignment = View.TEXT_ALIGNMENT_TEXT_START; //default from view
    private View _view;
    private LinearLayout _dialogCanvas;
    private LinearLayout _dialogCanvasContent;
    private TextView _tvContent;
    private LoadingDancing _loadingDancing;

    private void initView() {
        _dialogCanvas = _view.findViewById(R.id.dialog_canvas);
        _dialogCanvasContent = _view.findViewById(R.id.dialog_canvas_content);
        _tvContent = _view.findViewById(R.id.tv_content);
        _loadingDancing = _view.findViewById(R.id.loading_dancing);

    }

    @Override
    protected int contentView() {
        return R.layout.loading_dialog;
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

        if (tvContentAlignment != View.TEXT_ALIGNMENT_TEXT_START) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                _tvContent.setTextAlignment(tvContentAlignment);
            } else {
                _tvContent.setGravity(tvContentAlignment);
            }
        }
        if(colorBackground != 0 && _loadingDancing != null)
            _loadingDancing.setColor(colorBackground);
    }


    private void initOnClick() {
    }
}
