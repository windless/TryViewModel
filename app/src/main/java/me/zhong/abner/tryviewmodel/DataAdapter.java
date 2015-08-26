package me.zhong.abner.tryviewmodel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Abner on 15/8/26.
 */
public abstract class DataAdapter<D, V> extends BaseAdapter implements AdapterView.OnItemClickListener {
    private final Context _context;
    private final int     _resId;

    private List<D> _dataList;

    public DataAdapter(Context context, int resId) {
        _context = context;
        _resId = resId;
    }

    public void setDataList(List<D> dataList) {
        _dataList = dataList;
        notifyDataSetChanged();
    }

    public List<D> getDataList() {
        return _dataList;
    }

    public D getDataItem(int position) {
        return _dataList.get(position);
    }

    @Override
    public int getCount() {
        return _dataList == null ? 0 : _dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return _dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(_context).inflate(_resId, parent, false);
            convertView.setTag(Binder.create());
        }

        Binder binder = (Binder) convertView.getTag();
        binder.clear();

        bind(getDataItem(position), (V) convertView, binder);
        return convertView;
    }

    @Override
    public void onItemClick(@NonNull AdapterView<?> parent, @NonNull View view, int position, long id) {
        onClick(getDataItem(position));
    }

    abstract void bind(D item, V view, Binder binder);

    abstract void onClick(D item);
}
