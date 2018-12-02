package com.example.choosepictest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
	protected static final int TAKE_PHOTO = 0;
	private static final int CROP_PHOTO = 1;
	protected static final int CHOOSE_PHOTO = 2;
	private Button takePhoto;
	private Button choosePic;
	private ImageView image;
	private Uri imageUri;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		takePhoto = (Button) findViewById(R.id.take_photo);
		choosePic = (Button) findViewById(R.id.choose_photo);
		image = (ImageView) findViewById(R.id.image);

		// 添加事件
		takePhoto.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// 创建文件，存储拍照后的 图片
				File photo = new File(
						Environment.getExternalStorageDirectory(),
						"tempPhoto.jpg");
				try {
					if (photo.exists()) {
						photo.delete();
					}
					photo.createNewFile();
				} catch (IOException e) {

					e.printStackTrace();
				}
				// 图片Uri对象
				imageUri = Uri.fromFile(photo);
				Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
				intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri); // 设置拍照后文件保存位置Uri
				startActivityForResult(intent, TAKE_PHOTO); // 跳转
			}
		});

		choosePic.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// 创建文件，存储拍照后的 图片
				File photo = new File(
						Environment.getExternalStorageDirectory(),
						"tempPhoto.jpg");
				try {
					if (photo.exists()) {
						photo.delete();
					}
					photo.createNewFile();
				} catch (IOException e) {

					e.printStackTrace();
				}
				// 图片Uri对象
				imageUri = Uri.fromFile(photo);
				Intent intent = new Intent("android.intent.action.GET_CONTENT");
				intent.setType("image/*");
				startActivityForResult(intent, CHOOSE_PHOTO);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case TAKE_PHOTO:
			// 相机返回
			if (resultCode == RESULT_OK) {
				// 拍照完成，跳转到编辑界面
				Intent intent = new Intent("com.android.camera.action.CROP");
				// 设置相关属性
				intent.setDataAndType(imageUri, "image/*");
				intent.putExtra("scale", true);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
				// 跳转
				startActivityForResult(intent, CROP_PHOTO);
			}
			break;
		case CROP_PHOTO:
			// 图片编辑完返回
			if (resultCode == RESULT_OK) {
				// image.setImageURI(imageUri); //设置图片
				Bitmap drawable;
				try {
					drawable = BitmapFactory.decodeStream(getContentResolver()
							.openInputStream(imageUri));
					//drawable = compressImage(drawable);	// 压缩图片
					image.setImageBitmap(drawable); // 设置图片
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
			break;
		case CHOOSE_PHOTO:
			if (resultCode == RESULT_OK) {
				Uri uri = data.getData();
				// 跳转到裁剪程序
				Intent intent = new Intent("com.android.camera.action.CROP");
				intent.setDataAndType(uri, "image/*");
				// 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
				intent.putExtra("crop", "true");

				/*
				 * 设置裁剪图片相关属性
				 * 
				 * // 该参数可以不设定用来规定裁剪区的宽高比 intent.putExtra("aspectX", 2);
				 * intent.putExtra("aspectY", 1); // 该参数设定为你的imageView的大小
				 * intent.putExtra("outputX", 600); intent.putExtra("outputY",
				 * 300); intent.putExtra("scale", true);
				 */

				// 是否返回bitmap对象
				// intent.putExtra("return-data", false);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
				/*
				 * intent.putExtra("outputFormat",Bitmap.CompressFormat.JPEG.
				 * toString());// 输出图片的格式 intent.putExtra("noFaceDetection",
				 * true); // 头像识别
				 */
				startActivityForResult(intent, CROP_PHOTO);
			}
			break;
		default:
			break;
		}
	}

	private Bitmap compressImage(Bitmap image) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
		int options = 100;
		while (baos.toByteArray().length / 1024 > 100) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
			baos.reset();// 重置baos即清空baos
			image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
			options -= 10;// 每次都减少10
		}
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
		return bitmap;
	}

}
