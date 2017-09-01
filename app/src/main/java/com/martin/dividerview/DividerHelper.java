package com.martin.dividerview;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.ColorInt;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import static android.graphics.Path.Direction.CCW;
import static android.graphics.Region.Op.DIFFERENCE;

/**
 * author：Martin 2017/8/29 16:14
 * email: yuan.hu@inin88.com
 */
public class DividerHelper {

    private ViewGroup group;
    private int color;
    private Paint paint;
    private List<Rect> Bounds;

    public DividerHelper(ViewGroup group) {
        this.group = group;
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        Bounds = new ArrayList<>();
    }

    public void setBackgroundColor(@ColorInt int color) {
        this.color = color;
    }

    public boolean onLayout(){
        int count = group.getChildCount();
        if (count > 0) {//setChildFrame 方法是私有的 不然就简单多了
            for (int i = 0; i < count; i++) {
                View child = group.getChildAt(i);
                Bounds.add(new Rect(child.getLeft(), child.getTop(), child.getRight(), child.getBottom()));
            }
            return true;
        }
        return false;
    }

    private void drawDivider(Canvas canvas) {
        int count = group.getChildCount();
        if (count > 0) {
            paint.reset();
            paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            int pc = canvas.save(Canvas.ALL_SAVE_FLAG);
            Path path = new Path();
            for (Rect rect : Bounds) {
                path.addRect(new RectF(rect), CCW);
            }
            paint.setColor(color);
            Path pathG = new Path();
            pathG.addRect(new RectF(0, 0, canvas.getWidth(), canvas.getHeight()), CCW);
//            pathG.op(path, Path.Op.DIFFERENCE);//path的交集无法向下兼容直接使用clip也是一样的
            canvas.clipPath(path, DIFFERENCE);
            canvas.drawPath(pathG, paint);
            canvas.restoreToCount(pc);
        }
    }
}
