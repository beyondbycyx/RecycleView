package com.example.hq.gallery;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * 水平gallery的图库的实现
 * */
public class MainActivity extends ActionBarActivity  implements OnItemClickListener {

    //存放图片R值的数组
    private List<Integer> mdatas;
    //设置顶部视图
    private ImageView img_top;
    private RecyclerView rv;
    //实现监听的接口
    @Override
    public void OnItemClick(View v, int position) {
        //我需要的是用position来设置与activity_main上的其他图片view的source
        if(img_top!=null && mdatas!=null)
            img_top.setImageResource(mdatas.get(position));
        else
            Toast.makeText(this,"点击Item无法响应",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化数据源
        initMyDatas();
        rv=(RecyclerView)findViewById(R.id.rv);
        img_top=(ImageView)findViewById(R.id.img_top);
        //撑大到parent布局里
        img_top.setScaleType(ImageView.ScaleType.CENTER_CROP);
        //设置布局管理器
        LinearLayoutManager linearLayout=new LinearLayoutManager(this);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        rv.setLayoutManager(linearLayout);
        //设置适配器
        RVAdapter rvAdapter=new RVAdapter(this,mdatas);
        //为activity设置回调监听
        rvAdapter.setOnItemClickListener(this);

        rv.setAdapter(rvAdapter);
        //添加分割线,只有通过这种add方法才可以设置分割线
      //  rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));


    }
    public void initMyDatas()
    {
        mdatas=new ArrayList<Integer>();
        mdatas.add(R.drawable.hotel_1);
        mdatas.add(R.drawable.hotel_2);
        mdatas.add(R.drawable.hotel_3);
        mdatas.add(R.drawable.hotel_4);
    }
    //适配器
    public class RVAdapter extends RecyclerView.Adapter<RVAdapter.MyViewHolder>
    {
        private LayoutInflater inflater;
        private List<Integer> mdatas;
        //持有activity的监听接口
         OnItemClickListener mClicklistener;
        //提供一个方法(接口)传入监听器
        public void setOnItemClickListener(OnItemClickListener mClicklistener)
        {
            this.mClicklistener=mClicklistener;
        }
        public RVAdapter(Context context,List<Integer> mdatas)
        {
            inflater=LayoutInflater.from(context);//layouInflater要用到
            this.mdatas=mdatas;//闯入数据源

        }
        @Override
        public void onBindViewHolder(final RVAdapter.MyViewHolder holder, final int position) {
                //绑定数据源和view
               holder.hotel_img.setImageResource(mdatas.get(position));
            //判断外界是否注册监听，才确定图片视图是否监听
            if(mClicklistener!=null) {
                //为子item视图里的图片添加监听器，
                holder.hotel_img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //调用activity中的监听器方法
                        mClicklistener.OnItemClick(holder.hotel_img,position);
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return mdatas.size();
        }

        @Override
        public void onViewRecycled(RVAdapter.MyViewHolder holder) {
            super.onViewRecycled(holder);
        }

        @Override
        public RVAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
           //创建子item的布局视图
            View  itemv=inflater.inflate(R.layout.img_item,parent,false);
           MyViewHolder viewHolder=new MyViewHolder(itemv);
            //返回创建的子视图holder
            return viewHolder;
        }
        //
        class MyViewHolder extends RecyclerView.ViewHolder {
            View  itemv;
            ImageView  hotel_img;
            public MyViewHolder (View itemv) {
                super(itemv);
                this.itemv=itemv;
                hotel_img=(ImageView)itemv.findViewById(R.id.hotel_img);
            }


        }
    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
//自定义监听器
 interface  OnItemClickListener
{
    void OnItemClick(View v,int position);
}

