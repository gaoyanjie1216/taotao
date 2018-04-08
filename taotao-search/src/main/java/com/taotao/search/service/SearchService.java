package com.taotao.search.service;

import com.taotao.search.pojo.SearchResult;

/**
 * Created by Gao on 2018/2/27.
 */
public interface SearchService {

    SearchResult search(String queryString, int page, int rows) throws Exception;
}
