package com.aidingyun.ynlive.mvp.model.entity.base;

import java.util.ArrayList;


/**
 * <pre>
 *     project name: Ruisi
 *     author      : 翁嘉若
 *     create time : 2017/10/2 下午10:06
 *     desc        : 描述--//BaseListTModel 所有http请求的非列表数据的实体类基类,父类(本类)处理业务逻辑,子类封装数据
 * </pre>
 */
public class BaseListTModel<T> extends BaseResponse {

    public ArrayList<T> data;

    public ArrayList<T> getData() {
        return data;
    }

    public void setData(ArrayList<T> data) {
        this.data = data;
    }
}
