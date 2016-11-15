package com.yzs.yzslibrary.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yzs.yzslibrary.R;
import com.yzs.yzslibrary.util.ArithmeticUtils;

/**
 * Author 姚智胜
 * Version V1.0版本
 * Description: 购物车加减控件
 * Date: 2016/11/15
 */
public class QuantityView extends LinearLayout implements View.OnClickListener {

    private Drawable quantityBackground, addButtonBackground, removeButtonBackground;

    private String addButtonText, removeButtonText;
    /**
     * 初始值
     */
    private float quantity;
    /**
     * 增加步长
     */
    private float quantityStep;
    /**
     * 是否有中间点击事件
     */
    private boolean quantityClick;


    /**
     * 保留小数位数
     */
    private int reservePlaces;
    private int maxQuantity = Integer.MAX_VALUE, minQuantity = Integer.MAX_VALUE;
    private int quantityPadding;


    public boolean isQuantityClick() {
        return quantityClick;
    }

    public void setQuantityClick(boolean quantityClick) {
        this.quantityClick = quantityClick;
    }

    public float getQuantityStep() {
        return quantityStep;
    }

    public void setQuantityStep(float quantityStep) {
        this.quantityStep = quantityStep;
        mTextViewQuantity.setText(String.format("%." + reservePlaces + "f", quantity) + quantityUnit);
    }


    public int getReservePlaces() {
        return reservePlaces;
    }

    public void setReservePlaces(int reservePlaces) {
        this.reservePlaces = reservePlaces;
        mTextViewQuantity.setText(String.format("%." + reservePlaces + "f", quantity) + quantityUnit);
    }

    public String getQuantityUnit() {
        return quantityUnit;
    }

    public void setQuantityUnit(String quantityUnit) {
        this.quantityUnit = quantityUnit;
        mTextViewQuantity.setText(String.format("%." + reservePlaces + "f", quantity) + quantityUnit);
    }

    private String quantityUnit;

    private int quantityTextColor, addButtonTextColor, removeButtonTextColor;

    private Button mButtonAdd, mButtonRemove;
    private TextView mTextViewQuantity;

    private String labelDialogTitle = "Change Quantity";
    private String labelPositiveButton = "Change";
    private String labelNegativeButton = "Cancel";

    public interface OnQuantityChangeListener {
        void onQuantityChanged(float newQuantity, boolean programmatically);

        void onLimitReached();
    }

    private OnQuantityChangeListener onQuantityChangeListener;
    private OnClickListener mTextViewClickListener;

    public QuantityView(Context context) {
        super(context);
        init(null, 0);
    }

    public QuantityView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public QuantityView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void init(AttributeSet attrs, int defStyle) {
        final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.QuantityView, defStyle, 0);

        addButtonText = getResources().getString(R.string.qv_add);
        if (a.hasValue(R.styleable.QuantityView_qv_addButtonText)) {
            addButtonText = a.getString(R.styleable.QuantityView_qv_addButtonText);
        }
        addButtonBackground = ContextCompat.getDrawable(getContext(), R.drawable.qv_btn_selector);
        if (a.hasValue(R.styleable.QuantityView_qv_addButtonBackground)) {
            addButtonBackground = a.getDrawable(R.styleable.QuantityView_qv_addButtonBackground);
        }
        addButtonTextColor = a.getColor(R.styleable.QuantityView_qv_addButtonTextColor, Color.BLACK);

        removeButtonText = getResources().getString(R.string.qv_remove);
        if (a.hasValue(R.styleable.QuantityView_qv_removeButtonText)) {
            removeButtonText = a.getString(R.styleable.QuantityView_qv_removeButtonText);
        }
        removeButtonBackground = ContextCompat.getDrawable(getContext(), R.drawable.qv_btn_selector);
        if (a.hasValue(R.styleable.QuantityView_qv_removeButtonBackground)) {
            removeButtonBackground = a.getDrawable(R.styleable.QuantityView_qv_removeButtonBackground);
        }
        removeButtonTextColor = a.getColor(R.styleable.QuantityView_qv_removeButtonTextColor, Color.BLACK);

        quantityUnit = getResources().getString(R.string.qv_unit);
        if (a.hasValue(R.styleable.QuantityView_qv_unit)) {
            quantityUnit = a.getString(R.styleable.QuantityView_qv_unit);
        }
        quantity = a.getFloat(R.styleable.QuantityView_qv_quantity, 0);
        reservePlaces = a.getInt(R.styleable.QuantityView_qv_reservePlaces, 0);
        quantityStep = a.getFloat(R.styleable.QuantityView_qv_quantityStep, 1);
        maxQuantity = a.getInt(R.styleable.QuantityView_qv_maxQuantity, Integer.MAX_VALUE);
        minQuantity = a.getInt(R.styleable.QuantityView_qv_minQuantity, 0);
        quantityClick = a.getBoolean(R.styleable.QuantityView_qv_click, false);

        quantityPadding = (int) a.getDimension(R.styleable.QuantityView_qv_quantityPadding, pxFromDp(24));
        quantityTextColor = a.getColor(R.styleable.QuantityView_qv_quantityTextColor, Color.BLACK);
        quantityBackground = ContextCompat.getDrawable(getContext(), R.drawable.qv_bg_selector);
        if (a.hasValue(R.styleable.QuantityView_qv_quantityBackground)) {
            quantityBackground = a.getDrawable(R.styleable.QuantityView_qv_quantityBackground);
        }

        a.recycle();
        int dp10 = pxFromDp(10);

        mButtonAdd = new Button(getContext());
        mButtonAdd.setGravity(Gravity.CENTER);
        mButtonAdd.setPadding(dp10, dp10, dp10, dp10);
        mButtonAdd.setMinimumHeight(0);
        mButtonAdd.setMinimumWidth(0);
        mButtonAdd.setMinHeight(0);
        mButtonAdd.setMinWidth(0);
        setAddButtonBackground(addButtonBackground);
        setAddButtonText(addButtonText);
        setAddButtonTextColor(addButtonTextColor);

        mButtonRemove = new Button(getContext());
        mButtonRemove.setGravity(Gravity.CENTER);
        mButtonRemove.setPadding(dp10, dp10, dp10, dp10);
        mButtonRemove.setMinimumHeight(0);
        mButtonRemove.setMinimumWidth(0);
        mButtonRemove.setMinHeight(0);
        mButtonRemove.setMinWidth(0);
        setRemoveButtonBackground(removeButtonBackground);
        setRemoveButtonText(removeButtonText);
        setRemoveButtonTextColor(removeButtonTextColor);

        mTextViewQuantity = new TextView(getContext());
        mTextViewQuantity.setGravity(Gravity.CENTER);
        setQuantityTextColor(quantityTextColor);
        setQuantity(quantity);
        setQuantityBackground(quantityBackground);
        setQuantityPadding(quantityPadding);

        setOrientation(HORIZONTAL);

        addView(mButtonRemove, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        addView(mTextViewQuantity, LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        addView(mButtonAdd, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        mButtonAdd.setOnClickListener(this);
        mButtonRemove.setOnClickListener(this);
        mTextViewQuantity.setOnClickListener(this);
    }


    public void setQuantityClickListener(OnClickListener ocl) {
        mTextViewClickListener = ocl;
    }

    @Override
    public void onClick(View v) {
        if (v == mButtonAdd) {
            if (quantity + quantityStep > maxQuantity) {
                if (onQuantityChangeListener != null) onQuantityChangeListener.onLimitReached();
            } else {
                quantity = ArithmeticUtils.add(String.valueOf(quantity), String.valueOf(quantityStep)).floatValue();
                Log.e("add", "---" + quantity);
                mTextViewQuantity.setText(String.format("%." + reservePlaces + "f", quantity) + quantityUnit);
                if (onQuantityChangeListener != null)
                    onQuantityChangeListener.onQuantityChanged(quantity, false);
            }
        } else if (v == mButtonRemove) {
            if (quantity - quantityStep < minQuantity) {
                if (onQuantityChangeListener != null) onQuantityChangeListener.onLimitReached();
            } else {
                quantity = ArithmeticUtils.sub(String.valueOf(quantity), String.valueOf(quantityStep)).floatValue();
                Log.e("subtract", "---" + quantity);
                mTextViewQuantity.setText(String.format("%." + reservePlaces + "f", quantity) + quantityUnit);
                if (onQuantityChangeListener != null)
                    onQuantityChangeListener.onQuantityChanged(quantity, false);
            }
        } else if (v == mTextViewQuantity) {
            if (!quantityClick) {
                return;
            }
            if (mTextViewClickListener != null) {
                mTextViewClickListener.onClick(v);
                return;
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle(labelDialogTitle);

            final View inflate = LayoutInflater.from(getContext()).inflate(R.layout.qv_dialog_changequantity, null, false);
            final EditText et = (EditText) inflate.findViewById(R.id.qv_et_change_qty);
            et.setText(String.valueOf(quantity));

            builder.setView(inflate);
            builder.setPositiveButton(labelPositiveButton, null);
            final AlertDialog dialog = builder.show();
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    String newQuantity = et.getText().toString();
                    if (isValidNumber(newQuantity)) {
                        int intNewQuantity = Integer.parseInt(newQuantity);
                        Log.d(VIEW_LOG_TAG, "newQuantity " + intNewQuantity + " max " + maxQuantity);
                        if (intNewQuantity > maxQuantity) {
                            Toast.makeText(getContext(), "Maximum quantity allowed is " + maxQuantity, Toast.LENGTH_LONG).show();
                        } else if (intNewQuantity < minQuantity) {
                            Toast.makeText(getContext(), "Minimum quantity allowed is " + minQuantity, Toast.LENGTH_LONG).show();
                        } else {
                            setQuantity(intNewQuantity);
                            hideKeyboard(et);
                            dialog.dismiss();
                        }

                    } else {
                        Toast.makeText(getContext(), "Enter valid number", Toast.LENGTH_LONG).show();
                    }
                }
            });


        }
    }

    public void hideKeyboard(View focus) {
        InputMethodManager inputManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (focus != null) {
            inputManager.hideSoftInputFromWindow(focus.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


    public OnQuantityChangeListener getOnQuantityChangeListener() {
        return onQuantityChangeListener;
    }

    public void setOnQuantityChangeListener(OnQuantityChangeListener onQuantityChangeListener) {
        this.onQuantityChangeListener = onQuantityChangeListener;
    }

    public Drawable getQuantityBackground() {
        return quantityBackground;
    }

    public void setQuantityBackground(Drawable quantityBackground) {
        this.quantityBackground = quantityBackground;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mTextViewQuantity.setBackground(quantityBackground);
        } else {
            mTextViewQuantity.setBackgroundDrawable(quantityBackground);
        }
    }

    public Drawable getAddButtonBackground() {
        return addButtonBackground;
    }

    public void setAddButtonBackground(Drawable addButtonBackground) {
        this.addButtonBackground = addButtonBackground;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mButtonAdd.setBackground(addButtonBackground);
        } else {
            mButtonAdd.setBackgroundDrawable(addButtonBackground);
        }
    }

    public Drawable getRemoveButtonBackground() {
        return removeButtonBackground;
    }

    public void setRemoveButtonBackground(Drawable removeButtonBackground) {
        this.removeButtonBackground = removeButtonBackground;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mButtonRemove.setBackground(removeButtonBackground);
        } else {
            mButtonRemove.setBackgroundDrawable(removeButtonBackground);
        }
    }

    public String getAddButtonText() {
        return addButtonText;
    }

    public void setAddButtonText(String addButtonText) {
        this.addButtonText = addButtonText;
        mButtonAdd.setText(addButtonText);
    }

    public String getRemoveButtonText() {
        return removeButtonText;
    }

    public void setRemoveButtonText(String removeButtonText) {
        this.removeButtonText = removeButtonText;
        mButtonRemove.setText(removeButtonText);
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        boolean limitReached = false;

        if (quantity > maxQuantity) {
            quantity = maxQuantity;
            limitReached = true;
            if (onQuantityChangeListener != null) onQuantityChangeListener.onLimitReached();
        }
        if (quantity < minQuantity) {
            quantity = minQuantity;
            limitReached = true;
            if (onQuantityChangeListener != null) onQuantityChangeListener.onLimitReached();
        }


        if (!limitReached && onQuantityChangeListener != null)
            onQuantityChangeListener.onQuantityChanged(quantity, true);

        this.quantity = quantity;
        mTextViewQuantity.setText(String.format("%." + reservePlaces + "f", quantity) + quantityUnit);
    }

    public int getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(int maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public int getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(int minQuantity) {
        this.minQuantity = minQuantity;
    }

    public int getQuantityPadding() {
        return quantityPadding;
    }

    public void setQuantityPadding(int quantityPadding) {
        this.quantityPadding = quantityPadding;
        mTextViewQuantity.setPadding(quantityPadding, 0, quantityPadding, 0);
    }

    public int getQuantityTextColor() {
        return quantityTextColor;
    }

    public void setQuantityTextColor(int quantityTextColor) {
        this.quantityTextColor = quantityTextColor;
        mTextViewQuantity.setTextColor(quantityTextColor);
    }

    public void setQuantityTextColorRes(int quantityTextColorRes) {
        this.quantityTextColor = ContextCompat.getColor(getContext(), quantityTextColorRes);
        mTextViewQuantity.setTextColor(quantityTextColor);
    }

    public int getAddButtonTextColor() {
        return addButtonTextColor;
    }

    public void setAddButtonTextColor(int addButtonTextColor) {
        this.addButtonTextColor = addButtonTextColor;
        mButtonAdd.setTextColor(addButtonTextColor);
    }

    public void setAddButtonTextColorRes(int addButtonTextColorRes) {
        this.addButtonTextColor = ContextCompat.getColor(getContext(), addButtonTextColorRes);
        mButtonAdd.setTextColor(addButtonTextColor);
    }

    public int getRemoveButtonTextColor() {
        return removeButtonTextColor;
    }

    public void setRemoveButtonTextColor(int removeButtonTextColor) {
        this.removeButtonTextColor = removeButtonTextColor;
        mButtonRemove.setTextColor(removeButtonTextColor);
    }

    public void setRemoveButtonTextColorRes(int removeButtonTextColorRes) {
        this.removeButtonTextColor = ContextCompat.getColor(getContext(), removeButtonTextColorRes);
        mButtonRemove.setTextColor(removeButtonTextColor);
    }

    public String getLabelDialogTitle() {
        return labelDialogTitle;
    }

    public void setLabelDialogTitle(String labelDialogTitle) {
        this.labelDialogTitle = labelDialogTitle;
    }

    public String getLabelPositiveButton() {
        return labelPositiveButton;
    }

    public void setLabelPositiveButton(String labelPositiveButton) {
        this.labelPositiveButton = labelPositiveButton;
    }

    public String getLabelNegativeButton() {
        return labelNegativeButton;
    }

    public void setLabelNegativeButton(String labelNegativeButton) {
        this.labelNegativeButton = labelNegativeButton;
    }

    private int dpFromPx(final float px) {
        return (int) (px / getResources().getDisplayMetrics().density);
    }

    private int pxFromDp(final float dp) {
        return (int) (dp * getResources().getDisplayMetrics().density);
    }


    public static boolean isValidNumber(String string) {
        try {
            return Float.parseFloat(string) <= Integer.MAX_VALUE;
        } catch (Exception e) {
            return false;
        }
    }


}

