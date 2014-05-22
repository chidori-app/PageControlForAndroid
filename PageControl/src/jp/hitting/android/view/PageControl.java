package jp.hitting.android.view;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PageControl extends LinearLayout {

	private static final int PAGE_CONTROL_PADDING = 15;

	private ArrayList<TextView> pageCircleList;

	public PageControl(Context context, AttributeSet attrs) {
		super(context, attrs);

		TypedArray typedAry = context.obtainStyledAttributes(attrs,
				R.styleable.PageControl);
		int columnNum = typedAry.getInteger(R.styleable.PageControl_column_num,
				0);
		int color = typedAry.getColor(R.styleable.PageControl_color,
				Color.WHITE);
		typedAry.recycle();

		this.pageCircleList = new ArrayList<TextView>();
		for (int i = 0; i < columnNum; i++) {
			TextView circle = new TextView(context);
			circle.setText("●");
			circle.setTextColor(color);
			if (i != 0) {
				circle.setPadding(PAGE_CONTROL_PADDING, 0, 0, 0);
			}
			this.pageCircleList.add(circle);
			this.addView(circle, new LinearLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		}

		this.setCurrentPage(0);
	}

	public void setCurrentPage(int page) {
		for (int i = 0; i < this.pageCircleList.size(); i++) {
			TextView circle = this.pageCircleList.get(i);
			circle.setAlpha((i == page) ? 1.0f : 0.3f);
		}
	}

}
