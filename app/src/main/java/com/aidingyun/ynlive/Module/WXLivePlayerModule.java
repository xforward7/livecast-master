package com.aidingyun.ynlive.Module;

import android.os.Bundle;

import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.common.WXModule;
import com.tencent.rtmp.ITXLivePlayListener;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.rtmp.TXLivePlayConfig;
import com.tencent.rtmp.TXLivePlayer;
import com.tencent.rtmp.TXLog;
import com.tencent.rtmp.ui.TXCloudVideoView;

public class WXLivePlayerModule extends WXModule {

    private TXCloudVideoView mTXCloudVideoView;
    private TXLivePlayer mTXLivePlayer;
    private TXLivePlayConfig mTXLivePlayConfig;

    private boolean mPausing = false;
    private boolean mPlaying = false;

    /**
     * 添加View、创建Player
     */
    @JSMethod
    public void getVideoView() {

        mTXCloudVideoView = new TXCloudVideoView(mWXSDKInstance.getContext());
        mTXCloudVideoView.setLogMargin(10, 10, 45, 55);

        mTXLivePlayer = new TXLivePlayer(mWXSDKInstance.getContext());
        mTXLivePlayConfig.setAutoAdjustCacheTime(true);
        mTXLivePlayConfig.setMaxAutoAdjustCacheTime(2.0f);
        mTXLivePlayConfig.setMinAutoAdjustCacheTime(2.0f);
        mTXLivePlayer.setConfig(mTXLivePlayConfig);
        mTXLivePlayer.setRenderMode(TXLiveConstants.RENDER_MODE_FULL_FILL_SCREEN);
        mTXLivePlayer.setPlayListener(new ITXLivePlayListener() {
            @Override
            public void onPlayEvent(int event, Bundle param) {
                if (event == TXLiveConstants.PLAY_ERR_NET_DISCONNECT) {

                } else if (event == TXLiveConstants.PLAY_EVT_CHANGE_RESOLUTION) {
                    int width = param.getInt(TXLiveConstants.EVT_PARAM1, 0);
                    int height = param.getInt(TXLiveConstants.EVT_PARAM2, 0);
                    if (width > 0 && height > 0) {
                        float ratio = (float) height / width;
                        //pc上混流后的宽高比为4:5，这种情况下填充模式会把左右的小主播窗口截掉一部分，用适应模式比较合适
                        if (ratio > 1.3f) {
                            mTXLivePlayer.setRenderMode(TXLiveConstants.RENDER_MODE_FULL_FILL_SCREEN);
                        } else {
                            mTXLivePlayer.setRenderMode(TXLiveConstants.RENDER_MODE_ADJUST_RESOLUTION);
                        }
                    }
                }
            }

            @Override
            public void onNetStatus(Bundle status) {

            }
        });
    }

    /**
     * 播放
     *
     * @param mixedPlayUrl 房间创建者的拉流地址（实时模式下不使用该字段；直播模式下就是主播的拉流地址；连麦模式下就是混流地址）
//     * @param renderMode 填充模式(0和1) 0：铺满整个屏幕 1：画面局中显示
     */
    @JSMethod
    public void startPlay(String mixedPlayUrl) {

        if (mPlaying) {
            return;
        }
        if (mixedPlayUrl != null && mixedPlayUrl.length() > 0) {
            int playType = getPlayType(mixedPlayUrl);
            if (null == mTXCloudVideoView) {
                getVideoView();
            }

            if (mTXLivePlayer != null)
            {
                mTXLivePlayer.setPlayerView(mTXCloudVideoView);
                mTXLivePlayer.startPlay(mixedPlayUrl, playType);
            }
        }
        else {

        }
        mPlaying = true;
    }

    /**
     * 画面调整
     *
     * @param renderMode
     * @param renderRotation
     */
    @JSMethod
    public void setRenderModeAndRotation(int renderMode, int renderRotation)
    {
        // 设置填充模式
        mTXLivePlayer.setRenderMode(renderMode);
        // 设置画面渲染方向
        mTXLivePlayer.setRenderRotation(renderRotation);
    }

    /**
     * 暂停和继续播放
     *
     * @param flag 暂停或者继续
     */
    @JSMethod
    public void pauseOnContinuePlay(boolean flag) {
        if (mTXLivePlayer != null)
        {
            if (flag)
            {
                mTXLivePlayer.pause();
            }
            else
            {
                mTXLivePlayer.resume();
            }
        }
    }

    /**
     * 停止播放
     *
     * @param clearLastFrame 是否清除最后一帧(暂停播放)
     */
    @JSMethod
    public void stopPlay(boolean clearLastFrame) {
        if (mPlaying) {
            // 结束普通流播放
            if (mTXLivePlayer != null) {
                mTXLivePlayer.stopPlay(clearLastFrame);
                mTXLivePlayer.setPlayerView(null);
            }

            if (mTXCloudVideoView != null)
            {
                mTXCloudVideoView.onDestroy();
            }

            mPlaying = false;
        }
    }

    /**
     * 根据播放地址判断拉流类型
     *
     * @param playUrl 播放地址
     * @return 返回拉流类型
     */
    private int getPlayType(String playUrl) {
        int playType = TXLivePlayer.PLAY_TYPE_LIVE_RTMP;
        if (playUrl.startsWith("rtmp://")) {
            playType = TXLivePlayer.PLAY_TYPE_LIVE_RTMP;
        } else if ((playUrl.startsWith("http://") || playUrl.startsWith("https://")) && playUrl.contains(".flv")) {
            playType = TXLivePlayer.PLAY_TYPE_LIVE_FLV;
        }
        return playType;
    }

}
