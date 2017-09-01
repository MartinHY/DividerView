package com.martin.dividerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Space;

import java.util.ArrayList;
import java.util.List;

/**
 * author：Martin 2017/8/29 10:37
 * email: yuan.hu@inin88.com
 */
public class DividerSpaceLayout extends LinearLayout {

    private List<Rect> bounds;
    private Drawable divider;

    public DividerSpaceLayout(Context context) {
        super(context);
        viewInit();
    }

    public DividerSpaceLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        viewInit();
    }

    public DividerSpaceLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        viewInit();
    }

    private void viewInit() {
        bounds = new ArrayList<>();
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        drawDivider(canvas);
    }

    private void drawDivider(Canvas canvas) {
        int count = getChildCount();
        if (count > 0 && divider != null) {
            int pc = canvas.save(Canvas.ALL_SAVE_FLAG);
            for (Rect rect : bounds) {
                divider.setBounds(rect);
                divider.draw(canvas);
            }
            canvas.restoreToCount(pc);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        int count = getChildCount() + 1;
        if (count > 0) {
            bounds.clear();
            for (int i = 0; i < count; i++) {
                View child = getChildAt(i);
                if (child instanceof android.support.v4.widget.Space) {
                    bounds.add(new Rect(child.getLeft(), child.getTop(), child.getRight(), child.getBottom()));
                }
                if (child instanceof Space) {
                    bounds.add(new Rect(child.getLeft(), child.getTop(), child.getRight(), child.getBottom()));
                }
            }
        }
    }

    @Override
    public void setBackground(Drawable background) {
        divider = background;//保留一份背景
        super.setBackground(null);//因为最后的回调都是setBackground , 直接在这里进行拦截也是可以的
    }
}
