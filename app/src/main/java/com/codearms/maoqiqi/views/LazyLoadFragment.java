package com.codearms.maoqiqi.views;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 懒加载
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/8/24 11:37
 */
public class LazyLoadFragment extends Fragment {

    private final String TAG = getClass().getSimpleName();

    /**
     * 上下文
     */
    protected Context mContext;

    /**
     * 控件是否初始化完成
     */
    private boolean isViewCreated = false;

    /**
     * 数据是否已加载完毕
     */
    private boolean isLoadDataCompleted = false;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.d(TAG, TAG + "-->setUserVisibleHint(boolean isVisibleToUser) = " + isVisibleToUser + ",isViewCreated = " + isViewCreated + ",isLoadDataCompleted = " + isLoadDataCompleted);
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint() && isViewCreated && !isLoadDataCompleted) {
            isLoadDataCompleted = true;
            loadData();
        }
    }

    @Override
    public void onAttach(Context context) {
        Log.d(TAG, TAG + "-->onAttach(Context context)");
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, TAG + "-->onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, TAG + "-->onViewCreated(View view, Bundle savedInstanceState)");
        super.onViewCreated(view, savedInstanceState);
        isViewCreated = true;
    }

    // 为什么 loadData() 会在两个地方执行? 在 setUserVisibleHint 方法里执行我还能理解,为什么 onActivityCreated 也要执行呢?
    // 因为,ViewPager 默认显示第一页,第一页肯定要先加载数据,而且 setUserVisibleHint 的执行顺序又是在 onCreateView 之前,
    // 同时 onCreateView 需要初始化界面和修改 isViewCreated 的值。所以就需要在 onActivityCreated 里执行一次。
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, TAG + "-->onActivityCreated(Bundle savedInstanceState)");
        super.onActivityCreated(savedInstanceState);
        if (getUserVisibleHint() && isViewCreated && !isLoadDataCompleted) {
            isLoadDataCompleted = true;
            loadData();
        }
    }

    @Override
    public void onStart() {
        Log.d(TAG, TAG + "-->onStart()");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.d(TAG, TAG + "-->onResume()");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.d(TAG, TAG + "-->onPause()");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.d(TAG, TAG + "-->onStop()");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.d(TAG, TAG + "-->onDestroyView()");
        super.onDestroyView();
        isViewCreated = false;
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, TAG + "-->onDestroy()");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.d(TAG, TAG + "-->onDetach()");
        super.onDetach();
        mContext = null;
    }

    /***
     * 子类实现加载数据的方法
     */
    public void loadData() {
        Log.d(TAG, TAG + "-->loadData()");
    }
}
