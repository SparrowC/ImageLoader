package com.kun.imageloader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.kun.imageloader.utils.ImageLoader;
import com.kun.imageloader.utils.cache.DoubleCache;

public class MainActivity extends AppCompatActivity {

    public static String cachePath=null;
    private ListView list;
    String[] urls = {"http://imgsrc.baidu.com/forum/w=580/sign=2752d85bfdedab6474724dc8c737af81/f71c9bcb39dbb6fdd13b43b60f24ab18962b3700.jpg",
            "http://b.hiphotos.baidu.com/zhidao/wh%3D450%2C600/sign=02ca97f19113b07ebde8580c39e7bd15/a8014c086e061d95f34327e879f40ad163d9cadd.jpg",
            "http://a0.att.hudong.com/20/74/28300542186053138017740129473_s.jpg",
            "http://img5.imgtn.bdimg.com/it/u=1631221319,1876250310&fm=21&gp=0.jpg",
            "http://img3.imgtn.bdimg.com/it/u=2139928581,1119141396&fm=21&gp=0.jpg",
            "http://imgsrc.baidu.com/forum/pic/item/72f082025aafa40f4838157bab64034f79f0199d.jpg",
            "http://img0.imgtn.bdimg.com/it/u=3441222060,3976920538&fm=21&gp=0.jpg",
            "http://i4.3conline.com/images/piclib/201206/12/batch/1/137475/1339492891855zpthy5e1b5_medium.jpg",
            "http://imgsrc.baidu.com/forum/w=580/sign=ec796ebf4734970a47731027a5cbd1c0/3fe3a6315c6034a81a751e11cd1349540b2376d5.jpg",
            "http://cdn.duitang.com/uploads/item/201510/21/20151021102654_mBSXc.thumb.700_0.jpeg",
            "http://img5.imgtn.bdimg.com/it/u=2141920033,3715781534&fm=21&gp=0.jpg",
            "http://f.hiphotos.baidu.com/zhidao/wh%3D600%2C800/sign=e68607af42a98226b8942321bab29539/c8177f3e6709c93d7d8d8b2d9e3df8dcd00054e8.jpg",
            "http://imgsrc.baidu.com/forum/w=580/sign=c9182e74edf81a4c2632ecc1e72a6029/be850c30e924b8996c2ae53868061d950b7bf6de.jpg",
            "http://imgsrc.baidu.com/forum/w%3D580/sign=16f4b045caef76093c0b99971edca301/563d73ee3d6d55fba8fbc2fe6c224f4a21a4dd57.jpg",
            "http://img1.imgtn.bdimg.com/it/u=1241982794,2047736627&fm=21&gp=0.jpg"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (ListView) findViewById(R.id.list);

        cachePath=this.getCacheDir().getPath();
        list.setAdapter(new MyAdapter());
    }


    private class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return urls.length;
        }

        @Override
        public Object getItem(int position) {
            return urls[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(MainActivity.this)
                        .inflate(R.layout.list_item, null);
                viewHolder = new ViewHolder();
                viewHolder.iv = (ImageView) convertView.findViewById(R.id.iv);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            ImageLoader imageLoader = ImageLoader.getInstance();
            imageLoader.setCache(new DoubleCache());
            imageLoader.displayImage(urls[position], viewHolder.iv);
            return convertView;
        }
    }

    private class ViewHolder {
        ImageView iv;
        TextView tv;
    }
}
