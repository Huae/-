package com.example.databasetest;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class DatabaseProvider extends ContentProvider {
	// 定义内容常量
	public static final int BOOK_DIR = 0;
	public static final int BOOK_ITEM = 1;
	public static final int CATEGORY_DIR = 2;
	public static final int CATEGORY_ITEM = 3;
	// 权限路径
	public static final String AUTHORITY = "com.example.databasetest.provider";
	// URI匹配器
	private static UriMatcher uriMatcher;
	// 数据库连接帮助类
	private MyDatabaseHelper databaseHelper;
	/**
	 * 数据初始化
	 */
	static {
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		// 添加匹配规则
		uriMatcher.addURI(AUTHORITY, "book", BOOK_DIR);
		uriMatcher.addURI(AUTHORITY, "book/#", BOOK_ITEM);
		uriMatcher.addURI(AUTHORITY, "category", CATEGORY_DIR);
		uriMatcher.addURI(AUTHORITY, "category/#", CATEGORY_ITEM);
	}

	/**
	 * 外部新建提供者时调用
	 */
	public boolean onCreate() {
		databaseHelper = new MyDatabaseHelper(getContext(), "BookStore.db",
				null, 2);
		return true;
	}

	/**
	 * 添加数据
	 */
	public Uri insert(Uri uri, ContentValues values) {
		// 数据库
		SQLiteDatabase db = databaseHelper.getWritableDatabase();
		// 返回Uri
		Uri uriReturn = null;
		switch (uriMatcher.match(uri)) {
		case BOOK_DIR:
		case BOOK_ITEM:
			long newBookId = db.insert("book", null, values);
			uriReturn = Uri.parse("content://" + AUTHORITY + "/book/"
					+ newBookId);
			break;
		case CATEGORY_DIR:
		case CATEGORY_ITEM:
			long newCategoryId = db.insert("category", null, values);
			uriReturn = Uri.parse("content://" + AUTHORITY + "/category/"
					+ newCategoryId);
			break;
		default:
			break;
		}
		return uriReturn;
	}

	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// 数据库
		SQLiteDatabase db = databaseHelper.getWritableDatabase();
		// 删除行
		int deleteRows = 0;
		switch (uriMatcher.match(uri)) {
		case BOOK_DIR:
			deleteRows = db.delete("book", selection, selectionArgs);
			break;
		case BOOK_ITEM:
			String bookId = uri.getPathSegments().get(1);
			deleteRows = db.delete("book", "id = ?", new String[] { bookId });
			break;
		case CATEGORY_DIR:
			deleteRows = db.delete("category", selection, selectionArgs);
			break;
		case CATEGORY_ITEM:
			String categoryId = uri.getPathSegments().get(1);
			deleteRows = db.delete("category", "id = ?",
					new String[] { categoryId });
			break;
		default:
			break;
		}
		return deleteRows;
	}

	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// 数据库
		SQLiteDatabase db = databaseHelper.getWritableDatabase();
		// 更新行
		int updateRows = 0;
		switch (uriMatcher.match(uri)) {
		case BOOK_DIR:
			updateRows = db.update("book", values, selection, selectionArgs);
			break;
		case BOOK_ITEM:
			String bookId = uri.getPathSegments().get(1);
			updateRows = db.update("book", values, "id = ?",
					new String[] { bookId });
			break;
		case CATEGORY_DIR:
			updateRows = db
					.update("category", values, selection, selectionArgs);
			break;
		case CATEGORY_ITEM:
			String categoryId = uri.getPathSegments().get(1);
			updateRows = db.update("categoryId", values, "id = ?",
					new String[] { categoryId });
			break;
		default:
			break;
		}
		return updateRows;
	}

	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// 数据库
		SQLiteDatabase db = databaseHelper.getWritableDatabase();
		Cursor cursor = null;
		switch (uriMatcher.match(uri)) {
		case BOOK_DIR:
			cursor = db.query("book", projection, selection, selectionArgs, null, null, sortOrder);
			break;
		case BOOK_ITEM:
			String bookId = uri.getPathSegments().get(1);
			db.query("book", projection, "id = ?", new String[]{bookId}, null, null, sortOrder);
			break;
		case CATEGORY_DIR:
			cursor = db.query("category", projection, selection, selectionArgs, null, null, sortOrder);
			break;
		case CATEGORY_ITEM:
			String categoryId = uri.getPathSegments().get(1);
			db.query("category", projection, "id = ?", new String[]{categoryId}, null, null, sortOrder);
			break;
		default:
			break;
		}
		return cursor;
	}

	public String getType(Uri uri) {
		switch (uriMatcher.match(uri)) {
		case BOOK_DIR:
			return "vnd.android.cursor.dir/vnd."+AUTHORITY+".book";
		case BOOK_ITEM:
			return "vnd.android.cursor.item/vnd."+AUTHORITY+".book";
		case CATEGORY_DIR:
			return "vnd.android.cursor.dir/vnd."+AUTHORITY+".category";
		case CATEGORY_ITEM:
			return "vnd.android.cursor.item/vnd."+AUTHORITY+".category";
		}
		return null;
	}
}
