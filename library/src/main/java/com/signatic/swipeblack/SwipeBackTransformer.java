package com.signatic.swipeblack;

import android.app.Activity;
import android.view.View;

/**
 * A {@link SwipeBackTransformer} is responsible to manipulate the
 * swipe back view according to its current state (opening or closing)
 * {@link #onSwiping(SwipeBack, float, int)} (com.hannesdorfmann.swipeback.SwipeBack, float, int, int)} with some nice animations.
 * Also {@link #onSwipeBackCompleted(SwipeBack, Activity)} (com.hannesdorfmann.swipeback.SwipeBack, Activity)} will be called
 * after the swipe back has been completed to let you finish the activity by setting your own animation transition.
 * Created by Hannes Dorfmann on 03.01.14.
 */
public interface SwipeBackTransformer {

	/**
	 * This is called if the swipe back view has been created. As parameter you get the concrete
	 * view and you can setup your internal things like getting references to the child view by using {@link View#findViewById(int)} etc.
	 * @param swipeBack The reference to the SwipeBack object
	 * @param activity Tha activity the swipe back is attached to
	 * @param swipeBackView The view that has been inflated as swipe back view
	 */
	public void onSwipeBackViewCreated(SwipeBack swipeBack, Activity activity, View swipeBackView);

	/**
	 * This method is called after the swipe back has completed. This means that
	 * the swipe back view is fully shown and the use has released the finger
	 * (up) from the display. Usually you use this method to finish the activity
	 * by calling or {@link Activity#finish()} and you may set the
	 * activity animations by using
	 * {@link Activity#overridePendingTransition(int, int)}. <b>You
	 * should use {@link Activity#finish()} and not
	 * {@link Activity#onBackPressed()}</b>, because onBackPressed()
	 * may cause a {@link IllegalStateException} if the user rotates the device
	 * (screen rotation from landscape to portrait or vice versa), while the
	 * SwipeBackView is fully open, but the user has not released yet the finger
	 * (finger is still on the screen).
	 *
	 * @param swipeBack
	 *            The reference to the SwipeBack object
	 * @param activity
	 */
	public void onSwipeBackCompleted(SwipeBack swipeBack, Activity activity);

	/**
	 * This method is called if the swipe back has been canceled / reseted. So the swipe back view is no longer
	 * visible, only the main activity layout is visible. This is done if the user closes the swipe back view before it was completed {@link #onSwipeBackCompleted(SwipeBack, Activity)} (Activity)}.
	 * @param  swipeBack The reference to the SwipeBack object
	 * @param activity Tha activity the swipe back is attached to
	 */
	public void onSwipeBackReseted(SwipeBack swipeBack, Activity activity);


	/**
	 * Called while the swipe back view is opening or closing.
	 * This is the point where you do your frame by frame (step by step) animation for your swipe back view
	 *
	 * @param swipeBack The reference to the SwipeBack object
	 * @param openRatio a value between 0 and 1 that indicates how much open (visible) the swiping view is
	 * @param pixelOffset The number of pixels visible of the swipe back view
	 */
	public void onSwiping(SwipeBack swipeBack, float openRatio, int pixelOffset);



}
