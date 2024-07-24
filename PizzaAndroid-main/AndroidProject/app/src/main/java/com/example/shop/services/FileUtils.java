package com.example.shop.services;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.OpenableColumns;
import android.text.TextUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtils {

    public static String getPath(Context context, Uri uri) {
        // Create file in the app's cache directory
        File file = createFileFromUri(context, uri);
        if (file != null) {
            return file.getPath();
        }
        return null;
    }

    private static File createFileFromUri(Context context, Uri uri) {
        ContentResolver contentResolver = context.getContentResolver();
        String fileName = getFileName(contentResolver, uri);
        if (TextUtils.isEmpty(fileName)) {
            return null;
        }

        File file = new File(context.getCacheDir(), fileName);
        try (InputStream inputStream = contentResolver.openInputStream(uri);
             FileOutputStream outputStream = new FileOutputStream(file)) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead); // Виправлено тут
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return file;
    }

    private static String getFileName(ContentResolver contentResolver, Uri uri) {
        Cursor cursor = contentResolver.query(uri, null, null, null, null);
        if (cursor != null) {
            int nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
            cursor.moveToFirst();
            String name = cursor.getString(nameIndex);
            cursor.close();
            return name;
        }
        return null;
    }
}
