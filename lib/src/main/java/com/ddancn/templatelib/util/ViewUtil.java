package com.ddancn.templatelib.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.ddancn.templatelib.lib.R;

/**
 * @author ddan.zhuang
 * @date 2019/9/18
 */
public class ViewUtil {
    private ViewUtil(){}

    public static View getEmptyText(Context context){
        return getEmptyText(context, context.getString(R.string.empty_text));
    }

    public static View getEmptyText(Context context, String text){
        View view = LayoutInflater.from(context).inflate(R.layout.view_empty_text, null ,false);
        TextView textView = view.findViewById(R.id.text);
        textView.setText(text);
        return view;
    }

    public static View getFooterText(Context context){
        return getFooterText(context, context.getString(R.string.footer_text));
    }

    public static View getFooterText(Context context, String text){
        View view = LayoutInflater.from(context).inflate(R.layout.view_footer_text, null ,false);
        TextView emptyView = view.findViewById(R.id.text);
        emptyView.setText(text);
        return view;
    }
}
