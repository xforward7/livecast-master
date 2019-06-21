package debug;

import com.billy.cc.core.component.CC;
import com.jess.arms.base.BaseApplication;

/**
 * @author billy.qi
 * @since 17/11/20 20:57
 */
public class WebviewDebugApp extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        CC.enableVerboseLog(true);
        CC.enableDebug(true);
        CC.enableRemoteCC(true);
    }
}
