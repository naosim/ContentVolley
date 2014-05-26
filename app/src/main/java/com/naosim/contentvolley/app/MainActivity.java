package com.naosim.contentvolley.app;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Cursor cursor = getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(new ImageCursorAdapter(this, cursor, true));
    }

    private static class ImageCursorAdapter extends CursorAdapter {

        private final ImageLoader imageLoader;

        public ImageCursorAdapter(Context context, Cursor c, boolean autoRequery) {
            super(context, c, autoRequery);
            imageLoader = ContentVolley.newImageLoader(context);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
            return new NetworkImageView(context);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            Uri imageUri = getImageUri(cursor);

            NetworkImageView imageView = (NetworkImageView)view;
            imageView.setImageUrl(imageUri.toString(), imageLoader);
        }

        private static Uri getImageUri(Cursor c) {
            int fieldIndex = c.getColumnIndex(MediaStore.Images.Media._ID);
            Long id = c.getLong(fieldIndex);
            return ContentUris.withAppendedId(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id);
        }
    }
}
