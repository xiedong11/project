package com.example.project.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.project.R;

public class MoveImageView extends AppCompatActivity implements View.OnTouchListener {
    ImageView _view,_view2;
    ViewGroup _root;
    private int lastX, lastY;
    final static int IMAGE_SIZE = 72;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        _root = (ViewGroup) findViewById(R.id.root);

        _view = (ImageView) findViewById(R.id.id_text);
        _view2 = (ImageView) findViewById(R.id.id_text2);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                IMAGE_SIZE, IMAGE_SIZE);
        layoutParams.leftMargin = IMAGE_SIZE;
        layoutParams.topMargin = IMAGE_SIZE;

        _view.setLayoutParams(layoutParams);
        _view.setOnTouchListener(this);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(
                IMAGE_SIZE, IMAGE_SIZE);
        layoutParams2.leftMargin = 3*IMAGE_SIZE;
        layoutParams2.topMargin = IMAGE_SIZE;

        _view2.setLayoutParams(layoutParams2);
        _view2.setOnTouchListener(this);
    }





    public boolean onTouch(View view, MotionEvent event) {
        final int X = (int) event.getRawX();
        final int Y = (int) event.getRawY();
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) view
                        .getLayoutParams();
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                break;
            case MotionEvent.ACTION_POINTER_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                int dx = (int) event.getRawX() - lastX;
                int dy = (int) event.getRawY() - lastY;
                int left = view.getLeft() + dx;
                int top = view.getTop() + dy;
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view
                        .getLayoutParams();
                layoutParams.height=IMAGE_SIZE;
                layoutParams.width = IMAGE_SIZE;
                layoutParams.leftMargin =left;
                layoutParams.topMargin =top;
                view.setLayoutParams(layoutParams);
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                break;
        }
        _root.invalidate();
        return true;
    }
}