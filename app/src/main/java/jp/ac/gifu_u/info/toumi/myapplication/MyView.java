package jp.ac.gifu_u.info.toumi.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class MyView extends View {

    private final ArrayList<Integer> array_x;
    private final ArrayList<Integer> array_y;
    private final ArrayList<Boolean> array_status;

    public MyView(Context context) {
        super(context);
        array_x = new ArrayList<>();
        array_y = new ArrayList<>();
        array_status = new ArrayList<>();
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);

        for (int i = 1; i < array_status.size(); i++) {
            if (array_status.get(i)) {
                int x1 = array_x.get(i - 1);
                int x2 = array_x.get(i);
                int y1 = array_y.get(i - 1);
                int y2 = array_y.get(i);
                canvas.drawLine(x1, y1, x2, y2, paint);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                array_x.add(x);
                array_y.add(y);
                array_status.add(false);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
                array_x.add(x);
                array_y.add(y);
                array_status.add(true);
                invalidate();
                break;
        }
        return true;
    }
}
