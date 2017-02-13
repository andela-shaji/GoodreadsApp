package com.goodreadsapp.login;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.goodreadsapp.R;
import com.jakewharton.rxbinding2.widget.RxTextView;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;

public class LoginView extends LinearLayout implements LoginScreen {

    LoginPresenter<LoginView> presenter;

    @BindView(R.id.login) EditText loginEt;
    @BindView(R.id.password) EditText passwordEt;
    @BindView(R.id.login_btn) Button loginBtn;

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    public LoginView(Context context) {
        this(context, null);
    }

    public LoginView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs);
    }

    public LoginView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.login, this, true);
        ButterKnife.bind(this, view);

        presenter = new LoginPresenter<>(this);

        //TODO: move this to present to test it
        compositeDisposable.add(
            Observable.combineLatest(RxTextView.textChanges(loginEt),
            RxTextView.textChanges(passwordEt), (loginSequence, passwordSequence) -> {
                //TODO @Improve and use correct validation rules
                return loginSequence.length() >= 4 && passwordSequence.length() >= 4;
            }).filter(valid -> valid).subscribe(loginBtn::setEnabled));

    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        compositeDisposable.dispose();
    }
}
