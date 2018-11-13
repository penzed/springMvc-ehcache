package com.pinux.web;

import com.pinux.dto.Result;
import com.pinux.util.SpringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　　┣┓
 * 　　┃　　　　　　　┏┛
 * 　　┗┓┓┏━┳┓┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 * Created by pinux on 2018/4/24 14:50.
 */
@Controller
@RequestMapping("/cacheController")
public class CacheController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 删除所有的缓存
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/removeAllCache")
    @ResponseBody
    public Result<String> removeAllCache(HttpServletRequest request, HttpServletResponse response) {
        Result<String> stringResult = new Result<>();
        EhCacheCacheManager ehCacheCacheManager = (EhCacheCacheManager) SpringUtils.getBean("cacheManager");
        String[] names = ehCacheCacheManager.getCacheManager().getCacheNames();
        if (names.length > 0) {
            for (int i = 0; i < names.length; i++) {
                Cache cache = ehCacheCacheManager.getCache(names[i]);
                cache.clear();
            }
            stringResult.setSuccess(true);
            stringResult.setData("cache removeAll success!");
        }
        return stringResult;
    }

    /**
     * 查看所有的缓存列表
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/getAllCacheList")
    @ResponseBody
    public Result<Map> getAllCacheList(HttpServletRequest request, HttpServletResponse response) {
        Result<Map> mapResult = new Result<>();
        Map map = new HashMap();
        EhCacheCacheManager ehCacheCacheManager = (EhCacheCacheManager) SpringUtils.getBean("cacheManager");
        String[] names = ehCacheCacheManager.getCacheManager().getCacheNames();
        if (names.length > 0) {
            for (int i = 0; i < names.length; i++) {
                Cache cache = ehCacheCacheManager.getCache(names[i]);
                List list = ((EhCacheCache) cache).getNativeCache().getKeys();
                if (list != null && list.size() > 0) {
                    for (int j = 0; j < list.size(); j++) {
                        map.put(names[i] + "-" + list.get(j), cache.get(list.get(j)).get());
                    }
                }
                mapResult.setData(map);
                mapResult.setSuccess(true);
            }
        }

        return mapResult;
    }

}
