/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.aidingyun.ynlive.app;

import android.os.Handler;
import android.os.Message;

public class WXHttpManager {

  private static WXHttpManager wxHttpManager;
  private WXOkHttpDispatcher mOkHttpDispatcher;
  private Handler mHandler = new Handler(new Handler.Callback() {

    @Override
    public boolean handleMessage(Message msg) {
      WXHttpTask httpTask = (WXHttpTask) msg.obj;
      if (httpTask == null || httpTask.requestListener == null) {
        return true;
      }
      WXHttpResponse response = httpTask.response;
      if (response == null || response.code >= 300) {
        httpTask.requestListener.onError(httpTask);
      } else {
        httpTask.requestListener.onSuccess(httpTask);
      }
      return true;
    }
  });

  private WXHttpManager() {
    mOkHttpDispatcher = new WXOkHttpDispatcher(mHandler);
  }

  public static WXHttpManager getInstance() {
    if (wxHttpManager == null) {
      wxHttpManager = new WXHttpManager();
    }
    return wxHttpManager;
  }

  public void sendRequest(WXHttpTask httpTask) {
    mOkHttpDispatcher.dispatchSubmit(httpTask);
  }
}
