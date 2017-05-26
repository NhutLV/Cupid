package com.signatic.adapter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.signatic.cupid.R;

/**
 * Created by DefaultAccount on 10/29/2016.
 */
public class InviteDividerItemDecoration extends RecyclerView.ItemDecoration {
            private Drawable mDivider;

            public InviteDividerItemDecoration(Context context) {
                    mDivider = context.getResources().getDrawable(R.drawable.custom_item_nav);
                    }

            @Override
            public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
                    int left = 10;
                    int right = parent.getWidth() - 0;

                    int childCount = parent.getChildCount();
                    for (int i = 0; i < childCount; i++) {
                    View child = parent.getChildAt(i);

                    RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

                    int top = child.getBottom() + 2;
                    int bottom = top + 2;

                    mDivider.setBounds(left, top, right, bottom);
                    mDivider.draw(c);
                    }
                    }
        }
