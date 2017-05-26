package com.signatic.utils;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cunoraz.tagview.Tag;
import com.cunoraz.tagview.TagView;
import com.signatic.cupid.R;
import com.signatic.model.TagClass;

import java.util.ArrayList;

/**
 * Created by NhutDu on 08/11/2016.
 */
public class Utils {

    public static void addTagsToView(TagView tagView, ArrayList<TagClass> tagClasses,boolean isDelete){
        ArrayList<Tag> tags = new ArrayList<>();
        Tag tag;
        for(TagClass tagClass:tagClasses){
            tag = new Tag(tagClass.getName());
            tag.layoutColor = Color.parseColor("#17A1CA");
            tag.isDeletable = isDelete;
            tag.radius = 10f;
            tags.add(tag);
        }
        tagView.addTags(tags);
    }

    // disable all view in layout
    public static void disableView(View v) {

        v.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        if (v instanceof ViewGroup) {
            ViewGroup vg = (ViewGroup) v;
            for (int i = 0; i < vg.getChildCount(); i++) {
                View child = vg.getChildAt(i);
                disableView(child);
            }
        }
    }

    //enable all view in layout
    public static void enableView(View v) {

        v.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

        if (v instanceof ViewGroup) {
            ViewGroup vg = (ViewGroup) v;
            for (int i = 0; i < vg.getChildCount(); i++) {
                View child = vg.getChildAt(i);
                enableView(child);
            }
        }
    }

    //check network

}



