package com.martin.dividerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * author：Martin 2017/8/29 10:37
 * email: yuan.hu@inin88.com
 */
public class DividerLinearLayout extends LinearLayout {

    private List<Rect> bounds;
    private Drawable divider;

    public DividerLinearLayout(Context context) {
        super(context);
        viewInit();
    }

    public DividerLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        viewInit();
    }

    public DividerLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.RED);
            paint.setStrokeWidth(10);
            for (Rect rect : bounds) {
                divider.setBounds(rect);
                divider.draw(canvas);
//                canvas.drawRect(rect, paint);
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
                if (getOrientation() == HORIZONTAL) {
                    if (i == 0) {
                        View child = getChildAt(i);
                        if (child.getLeft() != 0)
                            bounds.add(new Rect(0, 0, child.getLeft(), getHeight()));
                    } else if (i == count - 1) {
                        View child = getChildAt(i - 1);
                        if (child.getRight() != getWidth())
                            bounds.add(new Rect(child.getRight(), child.getTop(), getWidth(), getHeight()));
                    } else {
                        View child = getChildAt(i);
                        View lastChild = getChildAt(i - 1);
                        if (child.getLeft() != lastChild.getRight())
                            bounds.add(new Rect(lastChild.getRight(), 0, child.getLeft(), getHeight()));
                    }
                    if (i < count - 1) {
                        View child = getChildAt(i);
                        if (child.getTop() != 0)
                            bounds.add(new Rect(child.getLeft(), 0, child.getRight(), child.getTop()));
                        if (child.getBottom() != getHeight())
                            bounds.add(new Rect(child.getLeft(), child.getBottom(), child.getRight(), getHeight()));
                    }
                } else {
                    if (i == 0) {
                        View child = getChildAt(i);
                        if (child.getTop() != 0)
                            bounds.add(new Rect(0, 0, getWidth(), child.getTop()));
                    } else if (i == count - 1) {
                        View child = getChildAt(i - 1);
                        if (child.getTop() != getHeight())
                            bounds.add(new Rect(0, child.getBottom(), getWidth(), getHeight()));
                    } else {
                        View child = getChildAt(i);
                        View lastChild = getChildAt(i - 1);
                        if (child.getTop() != lastChild.getBottom()){
                            int first = lastChild.getBottom() < child.getTop() ? lastChild.getBottom() : child.getTop();
                            int last = lastChild.getBottom() < child.getTop() ? child.getTop() : lastChild.getBottom();
                            bounds.add(new Rect(0, first, getWidth(), last));
                        }
                    }
                    if (i < count - 1) {
                        View child = getChildAt(i);
                        if (child.getLeft() != 0)
                            bounds.add(new Rect(0, child.getTop(), child.getLeft(), child.getBottom()));
                        if (child.getRight() != getWidth())
                            bounds.add(new Rect(child.getRight(), child.getTop(), getWidth(), child.getBottom()));
                    }
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
