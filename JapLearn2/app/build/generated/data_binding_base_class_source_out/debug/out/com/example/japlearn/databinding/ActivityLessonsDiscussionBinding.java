// Generated by view binder compiler. Do not edit!
package com.example.japlearn.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.japlearn.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityLessonsDiscussionBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageButton btnNext;

  @NonNull
  public final ImageButton btnPrev;

  @NonNull
  public final ImageButton imageButton3;

  @NonNull
  public final ImageButton imageButton4;

  @NonNull
  public final ImageButton imageButton5;

  @NonNull
  public final ImageButton imageButton6;

  @NonNull
  public final ImageButton imageButton8;

  @NonNull
  public final ImageView imageView11;

  @NonNull
  public final ImageView imageView8;

  @NonNull
  public final ImageView ivArrow;

  @NonNull
  public final TextView textView5;

  @NonNull
  public final TextView tvBtnExample;

  @NonNull
  public final TextView tvBtnName;

  @NonNull
  public final TextView tvDesc;

  @NonNull
  public final TextView tvFront;

  @NonNull
  public final TextView tvPronunciation;

  @NonNull
  public final TextView tvRomaji;

  private ActivityLessonsDiscussionBinding(@NonNull ConstraintLayout rootView,
      @NonNull ImageButton btnNext, @NonNull ImageButton btnPrev, @NonNull ImageButton imageButton3,
      @NonNull ImageButton imageButton4, @NonNull ImageButton imageButton5,
      @NonNull ImageButton imageButton6, @NonNull ImageButton imageButton8,
      @NonNull ImageView imageView11, @NonNull ImageView imageView8, @NonNull ImageView ivArrow,
      @NonNull TextView textView5, @NonNull TextView tvBtnExample, @NonNull TextView tvBtnName,
      @NonNull TextView tvDesc, @NonNull TextView tvFront, @NonNull TextView tvPronunciation,
      @NonNull TextView tvRomaji) {
    this.rootView = rootView;
    this.btnNext = btnNext;
    this.btnPrev = btnPrev;
    this.imageButton3 = imageButton3;
    this.imageButton4 = imageButton4;
    this.imageButton5 = imageButton5;
    this.imageButton6 = imageButton6;
    this.imageButton8 = imageButton8;
    this.imageView11 = imageView11;
    this.imageView8 = imageView8;
    this.ivArrow = ivArrow;
    this.textView5 = textView5;
    this.tvBtnExample = tvBtnExample;
    this.tvBtnName = tvBtnName;
    this.tvDesc = tvDesc;
    this.tvFront = tvFront;
    this.tvPronunciation = tvPronunciation;
    this.tvRomaji = tvRomaji;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityLessonsDiscussionBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityLessonsDiscussionBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_lessons_discussion, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityLessonsDiscussionBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnNext;
      ImageButton btnNext = ViewBindings.findChildViewById(rootView, id);
      if (btnNext == null) {
        break missingId;
      }

      id = R.id.btnPrev;
      ImageButton btnPrev = ViewBindings.findChildViewById(rootView, id);
      if (btnPrev == null) {
        break missingId;
      }

      id = R.id.imageButton3;
      ImageButton imageButton3 = ViewBindings.findChildViewById(rootView, id);
      if (imageButton3 == null) {
        break missingId;
      }

      id = R.id.imageButton4;
      ImageButton imageButton4 = ViewBindings.findChildViewById(rootView, id);
      if (imageButton4 == null) {
        break missingId;
      }

      id = R.id.imageButton5;
      ImageButton imageButton5 = ViewBindings.findChildViewById(rootView, id);
      if (imageButton5 == null) {
        break missingId;
      }

      id = R.id.imageButton6;
      ImageButton imageButton6 = ViewBindings.findChildViewById(rootView, id);
      if (imageButton6 == null) {
        break missingId;
      }

      id = R.id.imageButton8;
      ImageButton imageButton8 = ViewBindings.findChildViewById(rootView, id);
      if (imageButton8 == null) {
        break missingId;
      }

      id = R.id.imageView11;
      ImageView imageView11 = ViewBindings.findChildViewById(rootView, id);
      if (imageView11 == null) {
        break missingId;
      }

      id = R.id.imageView8;
      ImageView imageView8 = ViewBindings.findChildViewById(rootView, id);
      if (imageView8 == null) {
        break missingId;
      }

      id = R.id.ivArrow;
      ImageView ivArrow = ViewBindings.findChildViewById(rootView, id);
      if (ivArrow == null) {
        break missingId;
      }

      id = R.id.textView5;
      TextView textView5 = ViewBindings.findChildViewById(rootView, id);
      if (textView5 == null) {
        break missingId;
      }

      id = R.id.tvBtnExample;
      TextView tvBtnExample = ViewBindings.findChildViewById(rootView, id);
      if (tvBtnExample == null) {
        break missingId;
      }

      id = R.id.tvBtnName;
      TextView tvBtnName = ViewBindings.findChildViewById(rootView, id);
      if (tvBtnName == null) {
        break missingId;
      }

      id = R.id.tvDesc;
      TextView tvDesc = ViewBindings.findChildViewById(rootView, id);
      if (tvDesc == null) {
        break missingId;
      }

      id = R.id.tvFront;
      TextView tvFront = ViewBindings.findChildViewById(rootView, id);
      if (tvFront == null) {
        break missingId;
      }

      id = R.id.tvPronunciation;
      TextView tvPronunciation = ViewBindings.findChildViewById(rootView, id);
      if (tvPronunciation == null) {
        break missingId;
      }

      id = R.id.tvRomaji;
      TextView tvRomaji = ViewBindings.findChildViewById(rootView, id);
      if (tvRomaji == null) {
        break missingId;
      }

      return new ActivityLessonsDiscussionBinding((ConstraintLayout) rootView, btnNext, btnPrev,
          imageButton3, imageButton4, imageButton5, imageButton6, imageButton8, imageView11,
          imageView8, ivArrow, textView5, tvBtnExample, tvBtnName, tvDesc, tvFront, tvPronunciation,
          tvRomaji);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
