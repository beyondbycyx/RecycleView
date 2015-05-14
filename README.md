# RecycleView
    private RecyclerView recyclerView;
    private RecyclerView.Adapter myAdapter;
    private List<String > mDatas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //1.初始化化数据源
        initDatas();
        recyclerView=(RecyclerView)findViewById(R.id.rv);
        //2.添加布局
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //3.添加适配器
        myAdapter=new MyRVAdapter();
        recyclerView.setAdapter(myAdapter);
        //4.添加分割线,只有通过这种add方法才可以设置分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
    }
