package com.vf.sincerityfinance.adapter;

import com.vf.sincerityfinance.R;
import com.vf.sincerityfinance.databinding.ItemSlideModeBinding;
import com.zhpan.bannerview.BaseBannerAdapter;
import com.zhpan.bannerview.BaseViewHolder;

/**
 * @program: SincerityFinance
 * @description: banner的adapter
 * @author: tkx
 * @create: 2021-04-07 18:08
 **/
public class ViewBindingSampleAdapter extends BaseBannerAdapter<Integer> {

    private final int mRoundCorner;

    public ViewBindingSampleAdapter(int roundCorner) {
        mRoundCorner = roundCorner;
    }

    @Override
    protected void bindData(BaseViewHolder<Integer> holder, Integer data, int position, int pageSize) {
        //示例使用ViewBinding
        ItemSlideModeBinding viewBinding = ItemSlideModeBinding.bind(holder.itemView);
        viewBinding.bannerImage.setRoundCorner(mRoundCorner);
        viewBinding.bannerImage.setImageResource(data);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_slide_mode;
    }
}
