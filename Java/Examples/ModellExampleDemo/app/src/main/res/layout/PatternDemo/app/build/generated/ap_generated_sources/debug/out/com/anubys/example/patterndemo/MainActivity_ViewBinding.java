// Generated code from Butter Knife. Do not modify!
package com.anubys.example.patterndemo;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding implements Unbinder {
  private MainActivity target;

  @UiThread
  public MainActivity_ViewBinding(MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(MainActivity target, View source) {
    this.target = target;

    target.textViewResult = Utils.findOptionalViewAsType(source, R.id.tv_result, "field 'textViewResult'", TextView.class);
    target.editTextInputEmail = Utils.findOptionalViewAsType(source, R.id.et_input_email, "field 'editTextInputEmail'", EditText.class);
    target.buttonCheckInput = Utils.findOptionalViewAsType(source, R.id.btn_heck_input, "field 'buttonCheckInput'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.textViewResult = null;
    target.editTextInputEmail = null;
    target.buttonCheckInput = null;
  }
}
