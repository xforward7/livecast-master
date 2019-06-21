/*
 * Copyright 2017 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.aidingyun.ynlive.mvp.model.entity.base;

import com.aidingyun.ynlive.mvp.model.api.Api;


public class BaseResponse<T> {
    private int ec;
    private String em;
    protected T data;

    public int getEc() {
        return ec;
    }

    public BaseResponse<T> setEc(int ec) {
        this.ec = ec;
        return this;
    }

    public BaseResponse() {
    }

    public BaseResponse(int ec, String em) {
        this.ec = ec;
        this.em = em;
    }

    public String getEm() {
        return em;
    }

    public BaseResponse<T> setEm(String em) {
        this.em = em;
        return this;
    }

    /**
     * 请求是否成功
     *
     * @return
     */
    public boolean isSuccess() {
        return ec == Api.RequestSuccess;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
