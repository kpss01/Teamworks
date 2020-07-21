package com.example.teamwork.fragments;


import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.teamwork.R;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class TabTwoFragment extends Fragment {

    final int CAMERA_CAPTURE = 121;
    final int CROP_PIC = 2;
    private Uri picUri;
    final int PICK_FROM_FILE = 3;
    String imagebase;
    ImageView back;
    String avatarcode;

    Button getImage;

    ImageView imageView;

    public TabTwoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.tab_fragment_two, container, false);

        getImage=view.findViewById(R.id.getImage);

        imageView=view.findViewById(R.id.imageView);

        getImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //showcustomdialog();
                if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                        && ContextCompat.checkSelfPermission(getActivity(),Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED ) {
                    CropImage.activity().setGuidelines(CropImageView.Guidelines.ON)
                            .setAspectRatio(1, 1)
                            .start(getActivity(),TabTwoFragment.this);

                   // ImagePicker.Companion.with(this)
                }
                else{
                    requestStoragePermission();
                }
            }
        });
        return view;

    }


    private int permission_all=111;
    private void requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE)) {

            new AlertDialog.Builder(getActivity())
                    .setTitle("Permission needed")
                    .setMessage("To capture photos , Allow Teamwork to access your camera and media file")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(getActivity(),
                                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, permission_all);
                        }
                    })
                    .create().show();

        } else {
            ActivityCompat.requestPermissions(getActivity(),
                    PERMISSIONS, permission_all);
        }
    }


    String[] PERMISSIONS = {
            //android.Manifest.permission.READ_CONTACTS,
            //android.Manifest.permission.WRITE_CONTACTS,
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            // android.Manifest.permission.READ_SMS,
            android.Manifest.permission.CAMERA
    };
    // here we set image to imageview  and send to server

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==permission_all){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED ){
                CropImage.activity().setGuidelines(CropImageView.Guidelines.ON)
                        .setAspectRatio(1, 1)
                        .start(getActivity(),this);
                //GetImageFromCamera();
            }
            else{
                boolean showRationale = shouldShowRequestPermissionRationale(permissions[0]);
                if (!showRationale) {
                    final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                    alertDialogBuilder.setTitle("Permissions Required")
                            .setMessage("You have forcefully denied required permissions " +
                                    "for this action. Please open settings, go to permissions and allow them.")
                            .setPositiveButton("Settings", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                            Uri.fromParts("package", getActivity().getPackageName(), null));
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                }
                            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                            .setCancelable(false)
                            .create()
                            .show();
                }
                else {
                    // Toast.makeText(Home.this, "Permission DENIED", Toast.LENGTH_SHORT).show();
                    checkContactPermission();
                }
            }
        }
    }

    public void checkContactPermission(){

        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            CropImage.activity().setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1, 1)
                    .start(getActivity(),this);
        } else {
            requestStoragePermission();
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE)
            {
                //this request is pre declared
                // progressBar.setVisibility(View.VISIBLE);
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                getImage.setVisibility(View.GONE);
                imageView.setVisibility(View.VISIBLE);

                System.out.println("INside============");
                if (resultCode == RESULT_OK)
                {

                    Uri resultUri = result.getUri();
                    Bitmap selectedImage = null;
                    try {
                        Uri imageUri = result.getUri();
                        //Uri imageUri = data.getData();

                        System.out.println("INside============"+imageUri);


                        selectedImage= MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),imageUri);

                        if(Build.VERSION.SDK_INT < 28) {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(
                                    getActivity().getContentResolver(),imageUri
                            );
                            imageView.setImageBitmap(bitmap);
                        } else {
                            ImageDecoder.Source source = ImageDecoder.createSource(getActivity().getContentResolver(), imageUri);
                            Bitmap bitmap = ImageDecoder.decodeBitmap(source);
                            imageView.setImageBitmap(bitmap);
                        }


                        selectedImage = getResizedBitmap(selectedImage, 300);// 400 is for example, replace with desired size

                        ByteArrayOutputStream boas = new ByteArrayOutputStream();
                        selectedImage.compress(Bitmap.CompressFormat.JPEG,100,boas);
                        byte[] b = boas.toByteArray();

                        StringBuilder encodedImage = new StringBuilder();
                        encodedImage.append(Base64.encodeToString(b,Base64.DEFAULT));
                        imagebase = encodedImage.toString();
                        //System.out.println("image base 64 :"+imagebase);
                        imageView.setImageBitmap(selectedImage);



                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }




                }
                else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                    Exception error = result.getError();
                }

            }




        }

    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float)width / (float) height;

        System.out.println("bitmap ratio is :"+bitmapRatio);
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

}
