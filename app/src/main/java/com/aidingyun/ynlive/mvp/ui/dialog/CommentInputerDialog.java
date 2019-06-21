package com.aidingyun.ynlive.mvp.ui.dialog;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageView;

import com.aidingyun.core.utils.KeyboardUtil;
import com.aidingyun.ynlive.R;
import com.aidingyun.ynlive.app.utils.GlobalUtils;
import com.aidingyun.ynlive.app.utils.SimpleTextWatcher;
import com.aidingyun.ynlive.app.utils.SoftKeyboardStateHelper;
import com.aidingyun.ynlive.app.utils.ToastUtils;
import com.jakewharton.rxbinding2.view.RxView;
import com.jess.arms.utils.ArmsUtils;

import java.util.concurrent.TimeUnit;


/**
 * 登录二次验证 -> 底部弹窗
 *
 * @author Mango
 */
public class CommentInputerDialog extends FullScreenDialogFragment {
    private static final String KEY_HINT = "HINT";
    private EditText mCommentInput;


    public static CommentInputerDialog newInstance(String hint) {
        CommentInputerDialog fragment = new CommentInputerDialog();
        Bundle args = new Bundle();
        args.putString(KEY_HINT, hint);
        fragment.setArguments(args);
        return fragment;
    }

    public static CommentInputerDialog newInstance() {
        CommentInputerDialog fragment = new CommentInputerDialog();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_layout_input_comment, container, false);
        String hintStr = getArguments().getString(KEY_HINT);
        view.setLayoutParams(new LayoutParams(ArmsUtils.getScreenWidth(getActivity()), LayoutParams.WRAP_CONTENT));
        mCommentInput = view.findViewById(R.id.editext_input);
        ImageView mSend = view.findViewById(R.id.img_send);
        view.findViewById(R.id.v_touch_outside).setOnClickListener(v -> dismiss());
        setAnim(true, R.style.ActionSheetDialogStyle);
        mCommentInput.setHint(hintStr);
        mCommentInput.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                mSend.setEnabled(calcInputLength(s));
            }
        });
        controlKeyboardLayout(view, view.findViewById(R.id.root_view));
        RxView.clicks(mSend).throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(o -> {
                    String commentStr = mCommentInput.getText().toString().trim();
                    if (TextUtils.isEmpty(commentStr)) {
                        ToastUtils.show(getContext(), "输入内容不能为空!");
                        return;
                    }
                    clickListener.onClickListener(commentStr);
                    dismiss();
                });
        KeyboardUtil.showKeyboard(mCommentInput);
        /**
         * 监听软键盘的收起
         */
        SoftKeyboardStateHelper softKeyboardStateHelper = new SoftKeyboardStateHelper(mCommentInput);
        softKeyboardStateHelper.addSoftKeyboardStateListener(new SoftKeyboardStateHelper.SoftKeyboardStateListener() {
            @Override
            public void onSoftKeyboardOpened(int keyboardHeightInPx) {

            }

            @Override
            public void onSoftKeyboardClosed() {
                CommentInputerDialog.this.dismiss();
            }
        });

        return view;
    }

    private boolean calcInputLength(Editable editable) {
        String inputer = editable.toString();
        boolean maxLines = GlobalUtils.getLineCount(inputer) <= 15;
        if (!maxLines) {
            ToastUtils.show(getActivity(),"最多支持输入15行");
            return false;
        }
        return true;
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.add(this, tag).addToBackStack(null);
        transaction.commitAllowingStateLoss();
    }

    private OnClickListener clickListener;

    public CommentInputerDialog setOnClickListener(OnClickListener clickListener) {
        this.clickListener = clickListener;
        return this;
    }

    public interface OnClickListener {
        void onClickListener(String commentStr);
    }


    /**
     * @param root         最外层布局，需要调整的布局
     * @param scrollToView 被键盘遮挡的scrollToView,滚动root，使scrollToView在root可视区域的底部
     */
    private void controlKeyboardLayout(final View root, final View scrollToView) {
        root.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();
                //获取root在窗体的可视区域
                root.getWindowVisibleDisplayFrame(rect);
                //获取root在窗体的不可视区域高度(被其他View遮挡的区域高度)
                int rootInvisibleHeight = root.getRootView().getHeight() - rect.bottom;
                //若不可视区域高度大于100，则键盘显示
                if (rootInvisibleHeight > 100) {
                    int[] location = new int[2];
                    //获取scrollToView在窗体的坐标
                    scrollToView.getLocationInWindow(location);
                    //计算root滚动高度，使scrollToView在可见区域
                    int srollHeight = (location[1] + scrollToView.getHeight()) - rect.bottom;
                    root.scrollTo(0, srollHeight);
                } else {
                    //键盘隐藏
                    root.scrollTo(0, 0);
                }
            }
        });
    }
}
