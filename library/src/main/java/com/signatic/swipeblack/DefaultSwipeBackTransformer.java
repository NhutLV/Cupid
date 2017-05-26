package com.signatic.swipeblack;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.widget.TextView;

import com.example.library.R;

public class DefaultSwipeBackTransformer implements SwipeBackTransformer {

	protected View arrowTop;
	protected View arrowBottom;
	protected TextView textView;

	@Override
	public void onSwipeBackViewCreated(SwipeBack swipeBack, Activity activity,
									   final View swipeBackView) {

		textView = (TextView) swipeBackView.findViewById(R.id.text);

		onSwipeBackReseted(swipeBack, activity);

	}

	@Override
	public void onSwipeBackCompleted(SwipeBack swipeBack, Activity activity) {
		activity.finish();
		activity.overridePendingTransition(R.anim.swipeback_stack_to_front,
				R.anim.swipeback_stack_right_out);
	}

	@SuppressLint("NewApi")
	@Override
	public void onSwipeBackReseted(SwipeBack swipeBack, Activity activity) {

		// Reset the values to the initial state
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			textView.setAlpha(0);
		} else {
			// Pre Honeycomb
		}
	}

	@SuppressLint("NewApi")
	@Override
	public void onSwiping(SwipeBack swipeBack, float openRatio, int pixelOffset) {

		// Do step by step animations
		float startAlphaAt = 0.5f;

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			// Android 3 and above

			// Animate the textview
			textView.setAlpha(MathUtils.mapPoint(openRatio, startAlphaAt, 1f,
					0f, 1f));

		} else {
			// Pre Honeycomb (Android 2.x)

			// No good idea how to animate without nineold androids ( I will not
			// bring dependencies to nineold android into the library)
		}

	}

}
