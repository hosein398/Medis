package com.holding.medis.tools;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

import java.util.ArrayList;

/**
 * Created by hosein on 5/3/2017.
 */

public class AnimHelper {

    public static void visibleBlobjectAnim(final View view,int duration) {
        final AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(duration);
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        ArrayList<Animator> animatorList = new ArrayList<Animator>();
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(view, "ScaleX", 0f, 1.2f, 1f);
        animatorList.add(scaleXAnimator);
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(view, "ScaleY", 0f, 1.2f, 1f);
        animatorList.add(scaleYAnimator);
        animatorSet.playTogether(animatorList);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                view.setVisibility(View.VISIBLE);
                animatorSet.start();

            }
        }, 0);
    }

    public static void hideBlobjectAnim(final View view,int duration) {
        final AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(duration);
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        ArrayList<Animator> animatorList = new ArrayList<Animator>();
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(view, "ScaleX", 1f, -0.2f, 0f);
        animatorList.add(scaleXAnimator);
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(view, "ScaleY", 1f, -0.2f, 0f);
        animatorList.add(scaleYAnimator);
        animatorSet.playTogether(animatorList);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                view.setVisibility(View.GONE);
                animatorSet.start();

            }
        }, 0);
    }

    public static void startInRevealAnim(View view) {
        // previously invisible view

        // get the center for the clipping circle
        int cx = view.getMeasuredWidth() / 2;
        int cy = view.getMeasuredHeight() / 2;

        // get the final radius for the clipping circle
        int finalRadius = Math.max(view.getWidth(), view.getHeight()) / 2;

        // create the animator for this view (the start radius is zero)
        Animator anim =
                null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0, finalRadius);
        }

        // make the view visible and start the Controls.animation
        view.setVisibility(View.VISIBLE);
        anim.start();
    }
    public static void startOutRevealAnim(final View view) {
        // previously visible view

        // get the center for the clipping circle
        int cx = view.getMeasuredWidth() / 2;
        int cy = view.getMeasuredHeight() / 2;

        // get the initial radius for the clipping circle
        int initialRadius = view.getWidth() / 2;

        // create the Controls.animation (the final radius is zero)
        Animator anim =
                null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, initialRadius, 0);
        }

        // make the view invisible when the Controls.animation is done
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view.setVisibility(View.INVISIBLE);
            }
        });

        // start the Controls.animation
        anim.start();
    }
    public static void startAnimation(Context context,View view,int animId) {
        Animation anim = AnimationUtils.loadAnimation(context, animId);
        view.startAnimation(anim);
    }

    public static void rotate(View view,float fromdegree,float todegree){
        RotateAnimation rotate = new RotateAnimation(fromdegree, todegree, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(150);
        rotate.setFillAfter(true);
        rotate.setFillEnabled(true);
        rotate.setInterpolator(new LinearInterpolator());
        view.startAnimation(rotate);
    }
}
