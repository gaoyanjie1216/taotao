package utils;

import java.util.HashMap;
import java.util.Map;

/**
 * MsgUtil
 * Created by Gao on 2018/2/5.
 */
public class MsgUtil {

    /**
     * 创建失败结果
     */
    public static Map<String, Object> createFailMap(final String errorMsg) {
        return new HashMap<String, Object>() {{
            put("success", false);
            put("refresh", false);
            put("msg", errorMsg);
        }};
    }

    /**
     * 创建成功结果
     */
    public static Map<String, Object> createSuccessMap() {
        return new HashMap<String, Object>() {{
            put("success", true);
            put("refresh", false);
            put("msg", "操作成功！");
        }};
    }

    /**
     * 创建成功结果
     */
    public static Map<String, Object> createSuccessMap(final boolean refreshFlag, final String msg) {
        return new HashMap<String, Object>() {{
            put("success", true);
            put("refresh", refreshFlag);
            put("msg", msg);
        }};
    }

    /**
     * 创建成功结果
     */
    public static Map<String, Object> createSuccessMap(final String msg) {
        return new HashMap<String, Object>() {{
            put("success", true);
            put("refresh", false);
            put("msg", msg);
        }};
    }

    public static Map<String, Object> createMap(final String key, final String str) {
        return new HashMap<String, Object>() {{
            put("success", true);
            put("refresh", false);
            put("msg", "操作成功！");
            put(key, str);
        }};
    }

    public static Map<String, Object> createMap(String msg) {
        if (msg == null)
            return createSuccessMap();
        else
            return createFailMap(msg);
    }

    /**
     * 接口返回失败结果
     */
    public static Map<String, Object> createFailRetMap(final String errorMsg) {
        return new HashMap<String, Object>() {{
            put("ret", "fail");
            put("msg", errorMsg);
        }};
    }

    /**
     * 接口返回失败结果
     */
    public static Map<String, Object> createSuccessRetMap() {
        return new HashMap<String, Object>() {{
            put("ret", "success");
            put("msg", "");
        }};
    }

    public static Map<String, Object> createRetMap(String msg) {
        if (msg == null)
            return createSuccessRetMap();
        else
            return createFailRetMap(msg);
    }

}
