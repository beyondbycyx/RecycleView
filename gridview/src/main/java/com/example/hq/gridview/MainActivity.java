package com.example.hq.gridview;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private RecyclerView rv;

    private RecyclerView.Adapter myAdapter;
    private List<String > mDatas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv=(RecyclerView)findViewById(R.id.rv);

        initDatas();
        rv.setLayoutManager(new GridLayoutManager(this,2));
        rv.setAdapter(new MyRVAdapter(this,mDatas));
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

        private List<String> mDatas;
        private Context mcontext;
        public MyRVAdapter(Context context,List<String> mDatas)
        {
            this.mcontext=context;
            this.mDatas=mDatas;
        }
        //将数据源数据绑定到viewholder里
        @Override
        public void onBindViewHolder(MyViewHolder myViewHolder, int i) {

            myViewHolder.tv.setText(mDatas.get(i));
            //对textview进行监听
        }
        @Override
        public int getItemCount() {
            return mDatas.size();
        }
        //创建子布局控制器

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            MyViewHolder myViewHolder=new MyViewHolder(LayoutInflater.from(mcontext
            ).inflate(R.layout.item,viewGroup,false));
            return myViewHolder;
        }

        //控制item子布局的控制器
        class MyViewHolder extends RecyclerView.ViewHolder {

            TextView tv;
            public MyViewHolder(View v)
            {
                super(v);

                tv=(TextView)v.findViewById(R.id.id_num);
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.v("------", tv.getText() + "被点击了");
                    }
                });
                tv.setOnGenericMotionListener(new View.OnGenericMotionListener() {
                    @Override
                    public boolean onGenericMotion(View v, MotionEvent event) {
                        return false;
                    }
                });
                tv.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return false;
                    }
                });


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
