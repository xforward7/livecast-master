package com.aidingyun.ynlive.mvp.ui.dialog;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

import com.aidingyun.ynlive.R;
import com.jess.arms.utils.ArmsUtils;


/**
 * 底部弹窗
 */
public class ImageOperateDialog extends FullScreenDialogFragment implements OnClickListener {
    public static final String KEY_BITMAP = "KEY_BITMAP";
    private OnClickListener clickListener;

    public static ImageOperateDialog newInstance(Bitmap resource) {
        ImageOperateDialog fragment = new ImageOperateDialog();
        Bundle args = new Bundle();
        args.putParcelable(KEY_BITMAP, resource);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_layout_image_operate, container, false);

        view.setLayoutParams(new LayoutParams(ArmsUtils.getScreenWidth(getActivity()), LayoutParams.WRAP_CONTENT));
        TextView saveImgToGallery = view.findViewById(R.id.save_img_to_gallery);
        view.findViewById(R.id.touch_outside).setOnClickListener(this);
        saveImgToGallery.setOnClickListener(this);
        setAnim(true, R.style.Down2TopAnimStyle);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.save_img_to_gallery:
                if (clickListener != null) {
                    clickListener.onSaveImgToGallery();
                }
                break;
            default:
                break;
        }
        dismiss();
    }

    public ImageOperateDialog setOnClickListener(OnClickListener clickListener) {
        this.clickListener = clickListener;
        return this;
    }

    public interface OnClickListener {
        void onSaveImgToGallery();
    }

}
