package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a123456.zhonggu.CartListInfoMsgActivity;
import com.example.a123456.zhonggu.R;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import adapter.MyLvAdapter;
import bean.BUCartListBeanNUm;
import bean.BuCartListBean;
import bean.CarBean;
import bean.UserBean;
import jiekou.getInterface;
import View.GetJsonUtils;

public class Fragment1 extends Fragment implements AdapterView.OnItemClickListener,View.OnClickListener{
    private View view;
    private ImageView img_topleft,img_topright;
    private TextView tv_topcenter;
    RecyclerView recyclerView;
    RefreshLayout refreshLayout;
    ListView lv;
    private List<BuCartListBean> list;
    private MyLvAdapter adapter;
    private int count;
    private int i=1;//默认加载第一页数据
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        list=new ArrayList<BuCartListBean>();

        getBuCartList(i);

        view=inflater.inflate(R.layout.fragment_fragment1, container, false);
         img_topleft=view.findViewById(R.id.img_left);
         img_topright=view.findViewById(R.id.img_right);
         tv_topcenter=view.findViewById(R.id.tv_center);

         img_topleft.setVisibility(View.GONE);
//         tv_topcenter.setText("待补充车源");
         img_topright.setImageResource(R.mipmap.saoyisao);
         initView();

         img_topright.setOnClickListener(this);
         return view;
    }

    private void initView() {
//        setDate();
        refreshLayout = (RefreshLayout)view.findViewById(R.id.refreshLayout);

        lv=view.findViewById(R.id.lv);
        lv.setOnItemClickListener(this);

//        refreshLayout.setEnableAutoLoadmore(true);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000);
                Log.e("TAG","上拉刷新");
                refreshlayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(i>1){
                            i--;
                            getBuCartList(i);
                        }
                    }
                },3000);

            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {

                Log.e("TAG","上拉加载");
                refreshlayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        CarBean carBean=new CarBean();
//                        for(int i=count;i<=count+20;i++){
//                            carBean.tv_name="大众---"+i;
//                            carBean.tv_company_name="中古测试---"+i;
//                            carBean.tv_num1="12321sdfsfdsfsfs---"+i;
//                            carBean.tv_num2="进123rew---"+i;
//                            list.add(carBean);
//                        }
//                        count=list.size();
                        if(i<Integer.parseInt(BUCartListBeanNUm.last_page)) {
                            i++;
                            getBuCartList(i);
                        }else{
                            Toast.makeText(getContext(),"数据加载完毕",Toast.LENGTH_SHORT).show();
                        }
                    }
                },3000);

                refreshlayout.finishLoadmore(2000);
            }
        });
    }
    //设置数据源
//    private void setDate(){
//        list=new ArrayList<BuCartListBean>();
//        for(int i=0;i<20;i++){
//            CarBean carBean=new CarBean();
//            carBean.tv_name="大众---"+i;
//            carBean.tv_company_name="中古测试---"+i;
//            carBean.tv_num1="12321sdfsfdsfsfs---"+i;
//            carBean.tv_num2="进123rew---"+i;
//            list.add(carBean);
//            count=i;
//        }
//}

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(getContext(),"点击第+"+i+"条数据",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(getContext(), CartListInfoMsgActivity.class);
        intent.putExtra("vin"+i,list.get(i).vin);
        intent.putExtra("cardType"+i,list.get(i).cardType);
        intent.putExtra("licensePlate"+i,list.get(i).licensePlate);
        intent.putExtra("name"+i,list.get(i).name);
        intent.putExtra("i",""+i);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_right:
                Toast.makeText(getContext(),"扫描二维码",Toast.LENGTH_SHORT).show();
                break;
        }
    }
    //设置刷新
//    private void setPullRefresher(){
////        //设置 Header 为 MaterialHeader
////        refreshLayout.setRefreshHeader(new MaterialHeader(this));
////        //设置 Footer 为 经典样式
////        refreshLayout.setRefreshFooter(new ClassicsFooter(getContext()));
//
//        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onRefresh(RefreshLayout refreshlayout) {
//                //在这里执行上拉刷新时的具体操作(网络请求、更新UI等)
//
//                //模拟网络请求到的数据
//                ArrayList<ItemBean> newList = new ArrayList<ItemBean>();
//                for (int i=0;i<20;i++){
//                    newList.add(new ItemBean(
//                            R.mipmap.ic_launcher,
//                            "newTitle"+i,
//                            System.currentTimeMillis()+""
//                    ));
//                }
//                myAdapter.refresh(newList);
//                refreshlayout.finishRefresh(2000/*,false*/);
//                //不传时间则立即停止刷新    传入false表示刷新失败
//            }
//        });
//        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
//            @Override
//            public void onLoadmore(RefreshLayout refreshlayout) {
//
//                //模拟网络请求到的数据
//                ArrayList<ItemBean> newList = new ArrayList<ItemBean>();
//                for (int i=0;i<20;i++){
//                    newList.add(new ItemBean(
//                            R.mipmap.ic_launcher,
//                            "addTitle"+i,
//                            System.currentTimeMillis()+""
//                    ));
//                }
//                myAdapter.add(newList);
//                //在这里执行下拉加载时的具体操作(网络请求、更新UI等)
//                refreshlayout.finishLoadmore(2000/*,false*/);//不传时间则立即停止刷新    传入false表示加载失败
//            }
//        });
//    }
    //网络请求列表
    private void getBuCartList(int current_page){
        RequestParams requestParams=new RequestParams(getInterface.getBuCartList);
//        json=1&pagesize=10&where=blu=1 and groupid in(5,3,2) and status = 1
        requestParams.addBodyParameter("json","1");
        requestParams.addBodyParameter("pagesize","10");
        requestParams.addBodyParameter("current_page",current_page+"");
        requestParams.addBodyParameter("where","blu=0 and groupid in("+ UserBean.groupids+") and status=1");
       Log.e("TAG","requestParams接口拼接地址为=="+requestParams+"");
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("TAG","resulr=="+result);

                List<BuCartListBean>listBeans=new ArrayList<BuCartListBean>();
                listBeans= GetJsonUtils.getBuCartList(getActivity(),result);
                list.addAll(listBeans);
                if (list!=null) {
                    adapter = new MyLvAdapter(list, getActivity());
                    lv.setAdapter(adapter);
                }
//                List<CarBean>list=new ArrayList<CarBean>();
//                list=GetJsonUtils.getBuCartList(getActivity(),"");
//                for(int i=0;i<list.size();i++){
//                    Log.e("TAG","list数据name=="+list.get(i).tv_name);
//                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                tv_topcenter.setText(BUCartListBeanNUm.total);//设置title
                Log.e("TAG","title=="+BUCartListBeanNUm.total);
            }
        });
    }
}
