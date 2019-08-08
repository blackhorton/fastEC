package com.ming.latte.util.file;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.TextView;

import com.ming.latte.app.Latte;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author Hortons
 * on 2019/7/28
 */

/**
 * 处理文件的工具类
 */
public class FileUtil {

    /**
     * 默认本地上传图片目录
     */
    public static final String UPLOAD_PHOTO_DIR =
            Environment.getExternalStorageDirectory().getPath() + "/a_upload_photo/";
    /**
     * 网页缓存地址
     */
    public static final String WEB_CACHE_DIR =
            Environment.getExternalStorageDirectory().getPath() + "/app_web_cache/";
    /**
     * 系统相机目录
     */
    public static final String CAMERA_PHOTO_DIR =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getPath() + "/Camera/";
    /**
     * 格式化的模板
     */
    private static final String TIME_FORMAT = "_yyyyMMdd_HHmmss";
    /**
     * 本地路径
     */
    private static final String SDCARD_DIR =
            Environment.getExternalStorageDirectory().getPath();

    private static String getTimeFormatName(String timeFormatHeader) {
        final Date date = new Date(System.currentTimeMillis());
        //必须要加上单引号,否则会报错
        final SimpleDateFormat dateFormat = new SimpleDateFormat("'" + timeFormatHeader + "'" + TIME_FORMAT, Locale.getDefault());
        return dateFormat.format(date);
    }

    /**
     * @param timeFormatHeader 格式化的头（除去时间部分）
     * @param extension        后缀名
     * @return 返回时间格式化后的文件名
     */
    public static String getFileNameByTime(String timeFormatHeader, String extension) {
        return getTimeFormatName(timeFormatHeader) + "." + extension;
    }

    private static File createDir(String sdcardDirName) {
        final String dir = SDCARD_DIR + "/" + sdcardDirName + "/";
        final File fileDir = new File(dir);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        return fileDir;
    }

    public static File createFile(String sdcardDirName, String fileName) {
        return new File(createDir(sdcardDirName), fileName);
    }

    private static File createFileByTime(String sdcardDirName, String timeFormatHeader, String extension) {
        final String fileName = getFileNameByTime(timeFormatHeader, extension);
        return createFile(sdcardDirName, fileName);
    }

    public static String getExtension(String filePath) {
        String suffix = "";
        final File file = new File(filePath);
        final String name = file.getName();
        final int index = name.lastIndexOf(".");
        if (index > 0) {
            suffix = name.substring(index + 1);
        }
        return suffix;
    }

    /**
     * @param mBitmap
     * @param dir
     * @param compress
     * @return
     */
    public static File saveBitmap(Bitmap mBitmap, String dir, int compress) {
        final String sdStatus = Environment.getExternalStorageState();
        //检测sd是否可用，MEDIA_MOUNTED为SD卡正常挂载
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) {
            return null;
        }
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        File fileName = createFileByTime(dir, "DOWN_LOAD", "jpg");
        try {
            fos = new FileOutputStream(fileName);
            bos = new BufferedOutputStream(fos);
            //将Bitmap写入文件中
            mBitmap.compress(Bitmap.CompressFormat.JPEG, compress, bos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bos != null) {
                    //使输出流中的数据全部传入目标流中，目标流同样输出所有数据，确保文件的完整性
                    bos.flush();
                }
                if (bos != null) {
                    bos.close();
                }
                if (fos != null) {
                    fos.flush();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        refreshDCIM();
        return fileName;
    }

    public static File writeToDisk(InputStream is, String dir, String name) {
        final File file = createFile(dir, name);
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;

        try {
            bis = new BufferedInputStream(is);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);

            byte[] data = new byte[1024];

            int count;
            while ((count = bis.read(data)) != -1) {
                bos.write(data, 0, count);
            }

            bos.flush();
            fos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bos != null) {
                    bos.close();
                }
                if (fos != null) {
                    fos.close();
                }
                if (bis != null) {
                    bis.close();
                }
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }


    public static File writeToDisk(InputStream is, String dir, String prefix, String extension) {
        final File file = createFileByTime(dir, prefix, extension);
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;

        try {
            bis = new BufferedInputStream(is);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);

            byte[] data = new byte[1024];

            int count;

            while ((count = bis.read(data)) != -1) {
                bos.write(data, 0, count);
            }

            bos.flush();
            fos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bos != null) {
                    bos.close();
                }
                if (fos != null) {
                    fos.close();
                }
                if (bis != null) {
                    bis.close();
                }
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return file;
    }

    /**
     * 通知系统刷新系统相册，使照片展现出来
     */
    private static void refreshDCIM() {
        if (Build.VERSION.SDK_INT >= 19) {
            //兼容android4.4版本，只扫描存放照片的目录
            MediaScannerConnection.scanFile(Latte.getApplicationContext(),
                    new String[]{Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getPath()},
                    null, null);
        } else {
            //扫描整个SD卡来更新系统图库，当文件很多时用户体验不佳，且不适合4.4以上版本0
            Latte.getApplicationContext().sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED,
                    Uri.parse("file://" + Environment.getExternalStorageDirectory())));
        }
    }

    /**
     * 读取raw目录中的文件，并返回为字符串
     *
     * @param id
     * @return
     */
    public static String getRawFile(int id) {
        final InputStream is = Latte.getApplicationContext().getResources().openRawResource(id);
        final BufferedInputStream bis = new BufferedInputStream(is);
        final InputStreamReader isr = new InputStreamReader(bis);
        final BufferedReader br = new BufferedReader(isr);
        final StringBuilder stringBuilder = new StringBuilder();
        String string;
        try {
            while ((string = br.readLine()) != null) {
                stringBuilder.append(string);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                isr.close();
                bis.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }

    public static void setIconFont(String path, TextView textView) {
        final Typeface typeface = Typeface.createFromAsset(Latte.getApplicationContext().getAssets(), path);
        textView.setTypeface(typeface);
    }

    /**
     * 读取assets目录下的文件，并返回字符串
     *
     * @param name
     * @return
     */
    public static String getAssetsFile(String name) {
        InputStream is = null;
        BufferedInputStream bis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        StringBuilder stringBuilder = null;
        final AssetManager assetManager = Latte.getApplicationContext().getAssets();
        try {
            is = assetManager.open(name);
            bis = new BufferedInputStream(is);
            isr = new InputStreamReader(bis);
            br = new BufferedReader(isr);
            stringBuilder = new StringBuilder();
            String string;
            while ((string = br.readLine()) != null) {
                stringBuilder.append(string);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (isr != null) {
                    isr.close();
                }
                if (bis != null) {
                    bis.close();
                }
                if (is != null) {
                    is.close();
                }
                assetManager.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (stringBuilder != null) {
            return stringBuilder.toString();
        } else {
            return null;
        }
    }

    public static String getRealFilePath(final Context context, final Uri uri) {
        if (null == uri) {
            return null;
        }
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            final Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    final int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }
}