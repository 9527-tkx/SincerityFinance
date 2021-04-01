package me.hgj.jetpackmvvm.demo.data.model.bean

import android.annotation.SuppressLint
import android.os.Parcelable
import com.vf.sincerityfinance.bean.ApiPagerResponse
import com.vf.sincerityfinance.bean.AriticleResponse
import com.vf.sincerityfinance.bean.CoinInfoResponse
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class ShareResponse(
    var coinInfo: CoinInfoResponse,
    var shareArticles: ApiPagerResponse<ArrayList<AriticleResponse>>
) : Parcelable