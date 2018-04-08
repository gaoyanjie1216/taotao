package com.taotao.portal.service;

import com.taotao.portal.pojo.SearchResult;

/**
 * Created by Gao on 2018/3/4.
 */
public interface SearchService {

    SearchResult search(String queryString, int page);
}
