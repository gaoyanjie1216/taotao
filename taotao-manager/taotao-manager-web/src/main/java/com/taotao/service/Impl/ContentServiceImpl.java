package com.taotao.service.Impl;

import com.taotao.PageHelper.PageHelper;
import com.taotao.PageHelper.PageInfo;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.EUDataGridResult;
import pojo.TaotaoResult;

import java.util.Date;
import java.util.List;

/**
 * Created by Gao on 2018/2/1.
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper contentMapper;
/*
    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;

    @Value("${REST_CONTENT_SYNC_URL}")
    private String REST_CONTENT_SYNC_URL;*/


    @Override
    public TaotaoResult insertContent(TbContent content) {
        //补全pojo内容
        content.setCreated(new Date());
        content.setUpdated(new Date());
        contentMapper.insert(content);

        //添加缓存同步逻辑
        try {
            //HttpClientUtil.doGet(REST_BASE_URL + REST_CONTENT_SYNC_URL + content.getCategoryId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return TaotaoResult.ok();
    }

    /**
     *  内容管理列表查询
     */
    @Override
    public EUDataGridResult getContentList(long categoryId, int page, int rows) {

        //根据categoryId查询
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        //分页管理
        PageHelper.startPage(page, rows);
        List<TbContent> list = contentMapper.selectByExample(example);
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        PageInfo<TbContent> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());

        return result;
    }

}
