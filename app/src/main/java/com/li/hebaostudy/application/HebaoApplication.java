package com.li.hebaostudy.application;


import android.app.Application;
import android.content.Context;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;


/**
  * 功能描述: 应用application的处理
  *   作 者:  li
  *   时 间： 2016/6/23 11:20 
 */
public class HebaoApplication extends Application {

    public static Context context = null;
    private static HebaoApplication application;
    public static boolean IS_LOG = true;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        context=getApplicationContext();
        //PreferencesUtil.init(application);
        //初始化数据库
        initImageLoader(application);
    }

    /**
     * 获取Application 运行实例
     * @return
     */
    public static HebaoApplication getInstance() {
        return application;
    }

    /**
     * 获取Context实力
     * @return
     */
    public static Context getContext(){
        if (null!=context) {
            return context;
        }
        return application.getApplicationContext();
    }
    /**
     * 初始化Imageloader
     *
     * @param context
     */
    public static void initImageLoader(Context context) {
        // 这个是你希望的缓存文件的目录：
        // File cacheDir = StorageUtils.getOwnCacheDirectory(context, "/xyyy/lxn/imageloader/Cache");
        //context.getExternalCacheDir()  /storage/emulated/0/Android/data/com.lxn.utilone/cache  这个目录在SD下 会随着系统卸载被删除
        File cacheDir = context.getExternalCacheDir();
        if(null==cacheDir){
            cacheDir= StorageUtils.getOwnCacheDirectory(context, "/lxn/study/imageloader/Cache");
        }
        //这个目录在  data/data/com.lxn.utilone/cache 需要root才能查看数据
        //File cacheDir1 = context.getCacheDir();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                // 设置线程优先级
                .threadPriority(Thread.NORM_PRIORITY )
                // 设置图片缓存路劲
                .diskCache(new UnlimitedDiskCache(cacheDir))
                /*
				 * //调用该方法会禁止在内存中缓存同一张图片的多个尺寸。当把本地图片加载到内存中时，
				 * 首先会把图片缩减至要显示的ImageView的大小，
				 * 因此可能会出现一种状况，就是会首先显示一张图的小图，然后再显示这张图的大图。这种情况下，
				 * 同一张图片的两种尺寸的Bitmap会被存储在内存中，这是默认的操作
				 * 该方法会确保删除已加载图片缓存在内存中的其他尺寸的缓存。
				 */
                .denyCacheImageMultipleSizesInMemory()
				/*
				 * ImageLoaderConfiguration
				 * 配置中的.discCacheFileNameGenerator()方法是将缓存下来的文件以什么方式命名
				 * 里面可以调用的方法有 1.new Md5FileNameGenerator() //使用MD5对UIL进行加密命名
				 * 2.new HashCodeFileNameGenerator()//使用HASHCODE对UIL进行加密命名
				 */
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .writeDebugLogs() // Remove for release app
                .build();
        ImageLoader.getInstance().init(config);
    }
}
