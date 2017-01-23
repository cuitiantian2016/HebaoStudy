package com.li.hebaostudy.dialog.fragmentdialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.li.hebaostudy.R;
import com.li.hebaostudy.view.paydetail.Callback;
import com.li.hebaostudy.view.paydetail.MDProgressBar;
import com.li.hebaostudy.view.paydetail.PasswordKeyboard;
import com.li.hebaostudy.view.paydetail.PasswordView;


/**
 * 底部弹窗Fragment
 */
public class PayDetailFragment extends DialogFragment {
    private RelativeLayout rePayWay, rePayDetail, reBalance;
    private LinearLayout LinPayWay,linPass;
    private ListView lv;
    private Button btnPay;
    private EditText gridPasswordView;

    private ImageView imageCloseOne,imageCloseTwo,imageCloseThree;
    private TextView errorMsgTv;
    private RelativeLayout passwordContainer;
    private MDProgressBar progressBar;
    private PasswordView passwordView;
    private PasswordKeyboard numberKeyBoard;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙。
        Dialog dialog = new Dialog(getActivity(), R.style.BottomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        dialog.setContentView(R.layout.dialog_fragment_pay_detail);
        dialog.setCanceledOnTouchOutside(true); // 外部点击取消
        // 设置宽度为屏宽, 靠近屏幕底部。
        final Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.AnimBottom);
        final WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.BOTTOM; // 紧贴底部
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        lp.height = getActivity().getWindowManager().getDefaultDisplay().getHeight() * 3 / 5;
        window.setAttributes(lp);

        initView(dialog);


        if (getDialog() != null) {
            getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface anInterface, int keyCode, KeyEvent event) {
                    if(keyCode==KeyEvent.KEYCODE_ENTER&&event.getAction()==KeyEvent.ACTION_DOWN){
                        if(!TextUtils.isEmpty(gridPasswordView.getText().toString().trim())){
                            if("123456".equals(gridPasswordView.getText().toString().trim())){
                                //TODO 跳转支付宝支付
                            }
                        }
                    }else{
                        Toast.makeText(getContext(),"密码不能为空",Toast.LENGTH_SHORT).show();
                    }
                    return false;
                }
            });
        }



        return dialog;
    }

    private void initView(Dialog dialog) {
        rePayWay = (RelativeLayout) dialog.findViewById(R.id.re_pay_way);
        rePayDetail = (RelativeLayout) dialog.findViewById(R.id.re_pay_detail);//付款详情
        LinPayWay = (LinearLayout) dialog.findViewById(R.id.lin_pay_way);//付款方式
        lv = (ListView) dialog.findViewById(R.id.lv_bank);//付款方式（银行卡列表）
        reBalance = (RelativeLayout) dialog.findViewById(R.id.re_balance);//付款方式（余额）
        btnPay = (Button) dialog.findViewById(R.id.btn_confirm_pay);
        linPass = (LinearLayout)dialog.findViewById(R.id.lin_pass);
        imageCloseOne= (ImageView) dialog.findViewById(R.id.close_one);
        imageCloseTwo= (ImageView) dialog.findViewById(R.id.close_two);
        rePayWay.setOnClickListener(listener);
        reBalance.setOnClickListener(listener);
        btnPay.setOnClickListener(listener);
        imageCloseOne.setOnClickListener(listener);
        imageCloseTwo.setOnClickListener(listener);


        errorMsgTv = (TextView) dialog.findViewById(R.id.error_msg);
        TextView forgetPasswordTv = (TextView) dialog.findViewById(R.id.forget_password);
        TextView cancelTv = (TextView) dialog.findViewById(R.id.cancel_dialog);

        passwordContainer = (RelativeLayout) dialog.findViewById(R.id.password_content);
        progressBar = (MDProgressBar) dialog.findViewById(R.id.password_progressBar);
        progressBar.setOnPasswordCorrectlyListener(new MDProgressBar.OnPasswordCorrectlyListener() {
            @Override
            public void onPasswordCorrectly() {
                    if (mCallback != null) {
                        mCallback.onPasswordCorrectly();
                    }
            }
        });
        passwordView = (PasswordView) dialog.findViewById(R.id.password_inputBox);
        //设置密码长度
        if (passwordCount > 0) {
            passwordView.setPasswordCount(passwordCount);
        }

        numberKeyBoard = (PasswordKeyboard) dialog.findViewById(R.id.password_keyboard);
        numberKeyBoard.setOnPasswordInputListener(new PasswordKeyboard.OnPasswordInputListener() {
            @Override
            public void onInput(String number) {
                    if (PasswordKeyboard.DEL.equals(number)) {
                        if (mPasswordBuffer.length() > 0) {
                            mPasswordBuffer.delete(mPasswordBuffer.length() - 1, mPasswordBuffer.length());
                        }
                    } else if (PasswordKeyboard.DONE.equals(number)) {
                        dismiss();
                    } else {
                        if (!passwordState) {
                            if (!TextUtils.isEmpty(errorMsgTv.getText())) {
                                errorMsgTv.setText("");
                            }
                        }
                        mPasswordBuffer.append(number);
                    }
                    passwordView.setPassword(mPasswordBuffer);
                    if (mPasswordBuffer.length() == passwordView.getPasswordCount()) {
                        startLoading(mPasswordBuffer);
                    }
            }
        });

        cancelTv.setOnClickListener(listener);
        forgetPasswordTv.setOnClickListener(listener);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Animation slide_left_to_left = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_left_out);
            Animation slide_right_to_left = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_right_in);
            Animation slide_left_to_right = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_right_out);
            Animation slide_left_to_left_in = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_left_in);
            switch (view.getId()) {
                case R.id.re_pay_way://选择方式
                    rePayDetail.startAnimation(slide_left_to_left);
                    rePayDetail.setVisibility(View.GONE);
                    LinPayWay.startAnimation(slide_right_to_left);
                    LinPayWay.setVisibility(View.VISIBLE);
                    break;
                case R.id.re_balance:
                    rePayDetail.startAnimation(slide_left_to_left_in);
                    rePayDetail.setVisibility(View.VISIBLE);
                    LinPayWay.startAnimation(slide_left_to_right);
                    LinPayWay.setVisibility(View.GONE);
                    break;
                case R.id.btn_confirm_pay:
                    //确认付款
                    rePayDetail.startAnimation(slide_left_to_left);
                    rePayDetail.setVisibility(View.GONE);
                    linPass.startAnimation(slide_right_to_left);
                    linPass.setVisibility(View.VISIBLE);
                    break;
                case R.id.close_one:
                    getDialog().dismiss();
                    break;
                case R.id.close_two:
                    getDialog().dismiss();
                    break;
                case R.id.cancel_dialog:
                    if (mCallback != null) {
                        mCallback.onCancel();
                    }
                    dismiss();
                    break;
                case R.id.forget_password:
                    if (mCallback != null) {
                        mCallback.onForgetPassword();
                    }
                    break;
                default:
                    break;
            }
        }
    };
    private StringBuffer mPasswordBuffer = new StringBuffer();
    private Callback mCallback;
    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        if (context instanceof Callback) {
            mCallback = (Callback) context;
        }
    }
    /**
     * 设置密码长度
     */
    public void setPasswordCount(int passwordCount) {
        this.passwordCount = passwordCount;
    }
    private int passwordCount;

    private boolean passwordState = true;
    public void setCallback(Callback callBack) {
        this.mCallback = callBack;
    }

    public void setPasswordState(boolean correct) {
        setPasswordState(correct, "");
    }

    public void setPasswordState(boolean correct, String msg) {
        passwordState = correct;
        if (correct) {
            progressBar.setSuccessfullyStatus();
        } else {
            numberKeyBoard.resetKeyboard();
            passwordView.clearPassword();
            progressBar.setVisibility(View.GONE);
            passwordContainer.setVisibility(View.VISIBLE);
            errorMsgTv.setText(msg);
        }
    }



    private void startLoading(CharSequence password) {
        passwordContainer.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        if (mCallback != null) {
            mCallback.onInputCompleted(password);
        }
    }



    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (mPasswordBuffer.length() > 0) {
            mPasswordBuffer.delete(0, mPasswordBuffer.length());
        }
    }
}
