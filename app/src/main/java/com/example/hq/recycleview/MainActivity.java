package com.example.hq.recycleview;

import android.app.Activity;
import android.appwidget.AppWidgetProviderInfo;
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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter myAdapter;
    private List<String > mDatas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化化数据源
        initDatas();
        recyclerView=(RecyclerView)findViewById(R.id.rv);
        //添加布局
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //添加适配器
        myAdapter=new MyRVAdapter();
        recyclerView.setAdapter(myAdapter);
        //添加分割线,只有通过这种add方法才可以设置分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
    }
    public void initDatas()
    {
        mDatas=new ArrayList<String>();
        for(char i='A';i<='Z';i++)
        {
            mDatas.add(i+"");
        }
    }
    //自定义适配器
      class MyRVAdapter extends RecyclerView.Adapter<MyRVAdapter.MyViewHolder>{

        //将数据源数据绑定到viewholder里
        @Override
        public void onBindViewHolder(MyViewHolder myViewHolder, int i) {

            myViewHolder.tv.setText(mDatas.get(i));
        }
        @Override
        public int getItemCount() {
            return mDatas.size();
        }
        //创建子布局控制器

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            MyViewHolder myViewHolder=new MyViewHolder(LayoutInflater.from(MainActivity.this
            ).inflate(R.layout.item,viewGroup,false));
            return myViewHolder;
        }

        //控制item子布局的控制器
        class MyViewHolder extends RecyclerView.ViewHolder{

            TextView tv;
            public MyViewHolder(View v)
            {
                super(v);
                tv=(TextView)v.findViewById(R.id.id_num);
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
