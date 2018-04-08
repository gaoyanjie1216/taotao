package com.taotao.service;

import com.taotao.pojo.TbItemParam;
import pojo.TaotaoResult;

/**
 * Created by Gao on 2018/1/27.
 */
public interface ItemParamService {

    TaotaoResult getItemParamByCid(long cid);

    TaotaoResult insertItemParam(TbItemParam itemParam);
}