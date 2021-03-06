package com.cv.hy.learnopencv;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class MainActivity extends AppCompatActivity {
    private Button btn,btn1;
    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btn = (Button) findViewById(R.id.btn);
        btn1 = (Button) findViewById(R.id.btn1);
        img = (ImageView) findViewById(R.id.img);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.fangao)).getBitmap();
                int w = bitmap.getWidth(),h = bitmap.getHeight();
                int[] pix = new int[w * h];
                bitmap.getPixels(pix, 0, w, 0, 0, w, h);
                int[] resultPixes = OpenCVHelper.gray(pix, w, h);
                Bitmap result = Bitmap.createBitmap(w, h, Bitmap.Config.RGB_565);
                result.setPixels(resultPixes, 0, w, 0, 0, w, h);
                img.setImageBitmap(result);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenCVLoader.initDebug();
                Mat rgbMat = new Mat();
                Mat grayMat = new Mat();
                Bitmap srcBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fangao);
                Bitmap grayBitmap = Bitmap.createBitmap(srcBitmap.getWidth(), srcBitmap.getHeight(), Bitmap.Config.RGB_565);
                Utils.bitmapToMat(srcBitmap, rgbMat);//convert original bitmap to Mat, R G B.
                Imgproc.cvtColor(rgbMat, grayMat, Imgproc.COLOR_RGB2GRAY);//rgbMat to gray grayMat
                Utils.matToBitmap(grayMat, grayBitmap); //convert mat to bitmap
                img.setImageBitmap(grayBitmap);
            }
        });
    }
}
