package com.football.football.widget.bottombar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.football.football.R;

public class BottomBarTab extends FrameLayout {

    private ImageView mIcon;

    private int mTabPosition = -1;

    private Context mContext;
    private View mLine;

    public BottomBarTab(Context context, @DrawableRes int icon) {
        this(context, null, icon);
    }

    public BottomBarTab(Context context, AttributeSet attrs, @DrawableRes int icon) {
        this(context, attrs, 0, icon);
    }

    public BottomBarTab(Context context, AttributeSet attrs, int defStyleAttr, @DrawableRes int icon) {
        super(context, attrs, defStyleAttr);
        init(context, icon);
    }

    private void init(Context context, int icon) {
        this.mContext = context;
        TypedArray typedArray = context.obtainStyledAttributes(
                new int[]{R.attr.selectableItemBackgroundBorderless});
        Drawable drawable = typedArray.getDrawable(0);
        setBackgroundDrawable(drawable);
        typedArray.recycle();

        mIcon = new ImageView(context);
        int size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 22, getResources().getDisplayMetrics());
        LayoutParams params = new LayoutParams(size, size);
        params.gravity = Gravity.CENTER;
        mIcon.setImageResource(icon);
        mIcon.setLayoutParams(params);
        mIcon.setColorFilter(Color.parseColor("#999999"));
        addView(mIcon);

        mLine = new View(context);
        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics());
        LayoutParams params1 = new LayoutParams(size, height);
        params1.gravity = Gravity.CENTER|Gravity.BOTTOM;
        mLine.setLayoutParams(params1);
        mLine.setBackgroundColor(getResources().getColor(R.color.white));
        mLine.setVisibility(GONE);
        addView(mLine);
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        if (selected) {
            mIcon.setColorFilter(ContextCompat.getColor(mContext, R.color.white));
            mLine.setVisibility(VISIBLE);
        } else {
            mIcon.setColorFilter(ContextCompat.getColor(mContext, R.color.gray999999));
            mLine.setVisibility(GONE);
        }
    }

    public void setTabPosition(int position) {
        mTabPosition = position;
        if (position == 0) {
            setSelected(true);
        }
    }

    public int getTabPosition() {
        return mTabPosition;
    }
}
