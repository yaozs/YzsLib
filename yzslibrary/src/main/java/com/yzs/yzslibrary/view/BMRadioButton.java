package com.yzs.yzslibrary.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RadioButton;

import com.yzs.yzslibrary.R;


/** * 
 * @ClassName BMRadioButton
 * @author 赵金岩
 * @date 2015-11-21
 * @Description: 可调节图片大小的单选按钮
 */
public class BMRadioButton extends RadioButton {
    private static final String LOG_TAG = "BMRadioButton";
    
    private int drawableSize; // xml文件中设置的大小
    private int drawableWidth;
    private int drawableHeight;
    
    public BMRadioButton(Context context) {
        super(context);
    }

    public BMRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        Drawable drawableLeft = null; 
        Drawable drawableTop = null; 
        Drawable drawableRight = null; 
        Drawable drawableBottom = null;
        TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.BMRadioButton); 
        int n = typedArray.getIndexCount();
        Log.v("LOG_TAG", "n:" + n);
        for (int i = 0; i < n; i++) {
            final int attr = typedArray.getIndex(i);
            Log.v("LOG_TAG", "attr:" + attr);
            if (R.styleable.BMRadioButton_BMdrawableSize == attr) {
                drawableSize = typedArray.getDimensionPixelSize(R.styleable.BMRadioButton_BMdrawableSize, 0);
                Log.v("BMRadioButton_drawableSize", "drawableSize:" + drawableSize);
            } else if (R.styleable.BMRadioButton_BMdrawableWidth == attr) {
                drawableWidth = typedArray.getDimensionPixelSize(R.styleable.BMRadioButton_BMdrawableWidth, 0);
                Log.v("BMRadioButton_drawableSize", "drawableWidth:" + drawableWidth);
            } else if (R.styleable.BMRadioButton_BMdrawableHeight == attr) {
                drawableHeight = typedArray.getDimensionPixelSize(R.styleable.BMRadioButton_BMdrawableHeight, 0);
                Log.v("BMRadioButton_drawableSize", "drawableHeight:" + drawableHeight);
            } else if (R.styleable.BMRadioButton_BMdrawableTop == attr) {
                drawableTop = typedArray.getDrawable(attr);
            } else if (R.styleable.BMRadioButton_BMdrawableBottom == attr) {
                drawableBottom = typedArray.getDrawable(attr);
            } else if (R.styleable.BMRadioButton_BMdrawableRight == attr) {
                drawableRight = typedArray.getDrawable(attr);                
            } else if (R.styleable.BMRadioButton_BMdrawableLeft == attr) {
                drawableLeft = typedArray.getDrawable(attr);
            } else {
                Log.w(LOG_TAG, "no such attribute");
            }
        }
        typedArray.recycle();
         
        setCompoundDrawablesWithIntrinsicBounds(drawableLeft, drawableTop, drawableRight, drawableBottom);
     
    }
    
    public void setCompoundDrawablesWithIntrinsicBounds(Drawable left,
            Drawable top, Drawable right, Drawable bottom) { 
        setDrawableBounds(left);
        setDrawableBounds(right);
        setDrawableBounds(top);
        setDrawableBounds(bottom);        
        setCompoundDrawables(left, top, right, bottom);
    }
    
    private void setDrawableBounds(Drawable drawableObj) {
        if (null != drawableObj) {
            int width = drawableObj.getIntrinsicWidth();
            int height = drawableObj.getIntrinsicHeight();
            if (0 != drawableSize) {
                final int maxSize = drawableSize;
                if (width > height) {                
                    height = (maxSize * height) / width;
                    width = maxSize;
                } else if (width < height) {
                    width = (maxSize * width) / height;
                    height = maxSize;
                } else {
                    width = maxSize;
                    height = maxSize;
                }
            } else {
                if (0 != drawableWidth && 0 == drawableHeight) {
                    height = (drawableWidth * height) / width;
                    width = drawableWidth;
                } else if (0 == drawableWidth && 0 != drawableHeight) {
                    width = (drawableHeight * width) / height;
                    height = drawableHeight;
                } else if (0 != drawableWidth && 0 != drawableHeight) {
                    width = drawableWidth;
                    height = drawableHeight;
                } else {
                    
                }
            }
            drawableObj.setBounds(0, 0, width, height);
        }
    }
}
