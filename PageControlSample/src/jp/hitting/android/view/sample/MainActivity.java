package jp.hitting.android.view.sample;

import jp.hitting.android.view.PageControl;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.OnGestureListener;
import android.view.View.OnTouchListener;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;

public class MainActivity extends Activity {

	private static int pageNum;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_main);

		final ViewFlipper flipper = (ViewFlipper) this
				.findViewById(R.id.flipper);
		final PageControl pageControl = (PageControl) this
				.findViewById(R.id.pageControl);
		pageNum = 0;

		final Context context = this;
		final GestureDetector gestureDetector = new GestureDetector(this,
				new OnGestureListener() {
					@Override
					public boolean onSingleTapUp(MotionEvent e) {
						return false;
					}

					@Override
					public void onShowPress(MotionEvent e) {
					}

					@Override
					public boolean onScroll(MotionEvent e1, MotionEvent e2,
							float distanceX, float distanceY) {
						return false;
					}

					@Override
					public void onLongPress(MotionEvent e) {
					}

					@Override
					public boolean onFling(MotionEvent e1, MotionEvent e2,
							float velocityX, float velocityY) {
						float dx = Math.abs(velocityX);
						float dy = Math.abs(velocityY);
						if (dx > dy && dx > 300) {
							if (e1.getX() < e2.getX()) {
								flipper.setInAnimation(AnimationUtils
										.loadAnimation(context, R.anim.left_in));
								flipper.setOutAnimation(AnimationUtils
										.loadAnimation(context,
												R.anim.right_out));
								flipper.showPrevious();
								pageNum--;
								if (pageNum < 0) {
									pageNum = 2;
								}
							} else {
								flipper.setInAnimation(AnimationUtils
										.loadAnimation(context, R.anim.right_in));
								flipper.setOutAnimation(AnimationUtils
										.loadAnimation(context, R.anim.left_out));
								flipper.showNext();
								pageNum++;
								if (pageNum > 2) {
									pageNum = 0;
								}
							}
							pageControl.setCurrentPage(pageNum);
							return true;
						}
						return false;
					}

					@Override
					public boolean onDown(MotionEvent e) {
						return false;
					}
				});

		flipper.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				gestureDetector.onTouchEvent(event);
				return true;
			}
		});
	}

}
