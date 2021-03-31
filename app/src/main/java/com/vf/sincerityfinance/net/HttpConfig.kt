package com.vf.sincerityfinance.net

/**
 * @program: SincerityFinance
 *
 * @description:一些基础配置
 *
 * @author: tkx
 *
 * @create: 2021-03-31 14:15
 **/
object HttpConfig {

    const val BASE_URL = "https://restapi.amap.com/v3/"

    const val KEY = "key"

    const val KEY_MAP = "fb0a1b0d89f3b93adca639f0a29dbf23"

    //服务端返回的 code 以 CODE_SERVER 开头
    const val CODE_SERVER_SUCCESS = 1


    //url 管理
    fun RequestUrl(requestURL: Int) = when (requestURL) {
        UrlIds.LOGIN, UrlIds.REGISTER -> "${BASE_URL}breeze-user-restapi/app/breeze/user/getLoginInfo?"
        UrlIds.GET_VERIFICATION_CODE -> "${BASE_URL}breeze-user-restapi/app/breeze/user/send?"
        UrlIds.GET_COMPANY_LIST -> "${BASE_URL}assets-maintain-restapi/app/assets/common/companyList"
        UrlIds.POST_FILES_TO_SERVER -> "${BASE_URL}breeze-user-restapi/app/breeze/common/fileUpload"
        UrlIds.GET_LOCATION_LIST -> "${BASE_URL}assets-maintain-restapi/app/assets/common/locationList"
        UrlIds.GET_ASSETS_TYPE_LIST -> "${BASE_URL}assets-maintain-restapi/app/assets/common/assetsTypeList"
        UrlIds.GET_ADMIN_LIST -> "${BASE_URL}breeze-user-restapi/app/breeze/user/adminList"
        UrlIds.POST_INPUT_ASSETS -> "${BASE_URL}assets-maintain-restapi/web/assets/manage/addAssets"
        UrlIds.test -> "${BASE_URL}breeze-user-restapi/app/breezeassets/reverse/getAllocationList"
        UrlIds.test1 -> "${BASE_URL}breeze-user-restapi/app/breeze/app/message/getMessageCenterInfo"
        UrlIds.GET_LOGIN_CHECK -> "${BASE_URL}breeze-user-restapi/app/breeze/user/applyAddCompany"

        else -> ""
    }


}
