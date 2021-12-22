package com.laureanti.daniil.android.criminalintent;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import java.io.File;

public class ShowImageFragment extends DialogFragment {

    public static final String EXTRA_IMAGE = "com.laureanti.daniil.android.criminalintent.image";
    private static final String ARG_IMAGE = "image";
    private ImageView photoView;

    public static ShowImageFragment newInstance(File photoFile){
        Bundle args = new Bundle();
        args.putSerializable(ARG_IMAGE, photoFile);
        ShowImageFragment fragment = new ShowImageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_detailed_image, null);

        photoView = (ImageView) v.findViewById(R.id.full_image);

        File photoFile = (File) getArguments().getSerializable(ARG_IMAGE);
        if (photoFile == null || !photoFile.exists()) {
            photoView.setImageDrawable(null);
        } else {
            Bitmap bitmap = PictureUtils.getScaledBitmap(photoFile.getPath(), getActivity());
            photoView.setImageBitmap(bitmap);
        }

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .create();
    }
}
