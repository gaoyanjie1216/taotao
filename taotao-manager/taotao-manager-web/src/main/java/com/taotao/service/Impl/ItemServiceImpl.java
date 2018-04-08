package com.taotao.service.Impl;

import com.taotao.PageHelper.PageBean;
import com.taotao.PageHelper.PageHelper;
import com.taotao.PageHelper.PageInfo;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.service.ItemService;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.EUDataGridResult;
import pojo.TaotaoResult;
import utils.IDUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by Gao on 2018/1/23.
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper itemMapper;

    @Autowired
    private TbItemDescMapper itemDescMapper;

    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;
    
    @Override
    public TbItem getItemById(Long itemId) {
        
        //TbItem item = itemMapper.selectByPrimaryKey(itemId);
        
        TbItemExample example = new TbItemExample();
        //创建查询条件
        TbItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(itemId);
        List<TbItem> list = itemMapper.selectByExample(example);
        //判断list中是否为空
        TbItem item = null;
        if (list != null && list.size() > 0) {
            item = list.get(0);
        }
        
        return item;
    }

    @Override
    public EUDataGridResult getItemList(int page, int rows) {
        //分页处理
        PageHelper.startPage(page, rows);
        //执行查询
        TbItemExample example = new TbItemExample();
        List<TbItem> list = itemMapper.selectByExample(example);
        //取分页信息
        //PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        //PageBean<TbItem> pageBean = new PageBean<>(list);
        /* 返回处理结果 */
        EUDataGridResult result = new EUDataGridResult();
        result.setTotal(list.size());
        result.setRows(list);

        return result;
    }

    @Override
    public TaotaoResult createItem(TbItem item, String desc, String itemParam) throws Exception {
        //item补全
        //生成商品ID
        Long itemId = IDUtils.genItemId();
        item.setId(itemId);
        // '商品状态，1-正常，2-下架，3-删除',
        item.setStatus((byte) 1);
        item.setCreated(new Date());
        item.setUpdated(new Date());
        //插入到数据库
        itemMapper.insert(item);
        //添加商品描述信息
        TaotaoResult result = insertItemDesc(itemId, desc);
        if (result.getStatus() != 200) {
            throw new Exception();
        }
        //添加规格参数
        result = insertItemParamItem(itemId, itemParam);
        if (result.getStatus() != 200) {
            throw new Exception();
        }
        return TaotaoResult.ok();
    }

    /**
     * 添加商品描述
     * <p>Title: insertItemDesc</p>
     * <p>Description: </p>
     * @param desc
     */
    private TaotaoResult insertItemDesc(Long itemId, String desc) {
        TbItemDesc itemDesc = new TbItemDesc();
        itemDesc.setItemId(itemId);
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(new Date());
        itemDesc.setUpdated(new Date());
        itemDescMapper.insert(itemDesc);
        return TaotaoResult.ok();
    }

    /**
     * 添加规格参数
     * <p>Title: insertItemParamItem</p>
     * <p>Description: </p>
     * @param itemId
     * @param itemParam
     * @return
     */
    private TaotaoResult insertItemParamItem(Long itemId, String itemParam) {
        //创建一个pojo
        TbItemParamItem itemParamItem = new TbItemParamItem();
        itemParamItem.setItemId(itemId);
        itemParamItem.setParamData(itemParam);
        itemParamItem.setCreated(new Date());
        itemParamItem.setUpdated(new Date());
        //向表中插入数据
        itemParamItemMapper.insert(itemParamItem);

        return TaotaoResult.ok();
    }

}
