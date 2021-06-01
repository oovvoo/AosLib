package com.scode.commlib.ui;

import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.widget.TextView;
import androidx.annotation.ColorInt;

public class UIUtils {

    /**
     * TextView의 특정 단어를 원하는 색으로 강조
     * @param textView 적용할 textView
     * @param targetText 적용할 단어
     * @param color 글자색
     */
    public static void setTextViewColor(TextView textView, String targetText, @ColorInt int color) {
        String pMsg = textView.getText().toString();
        Spannable spannable = new SpannableStringBuilder(pMsg);
        spannable.setSpan(new ForegroundColorSpan(color),pMsg.indexOf(targetText),pMsg.indexOf(targetText)+targetText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new StyleSpan(Typeface.BOLD),pMsg.indexOf(targetText),pMsg.indexOf(targetText)+targetText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spannable);
    }

    /**
     * TextView의 특정 단어를 원하는 색으로 강조 + 배경색
     * @param textView 적용할 TextView
     * @param targetText 적용 할 단어
     * @param color 글자색
     * @param backColor 배경색
     */
    public static void setTextViewColor(TextView textView, String targetText, @ColorInt int color,@ColorInt int backColor) {
        String pMsg = textView.getText().toString();
        Spannable spannable = new SpannableStringBuilder(pMsg);
        spannable.setSpan(new ForegroundColorSpan(color),pMsg.indexOf(targetText),pMsg.indexOf(targetText)+targetText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new BackgroundColorSpan(backColor),pMsg.indexOf(targetText),pMsg.indexOf(targetText)+targetText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new StyleSpan(Typeface.BOLD),pMsg.indexOf(targetText),pMsg.indexOf(targetText)+targetText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spannable);
    }

}
