package com.taotao.service.Impl;

import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.EUTreeNode;
import pojo.TaotaoResult;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 内容分类管理
 * Created by Gao on 2018/2/1.
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private TbContentCategoryMapper contentCategoryMapper;

    @Override
    public List<EUTreeNode> getCategoryList(Long parentId) {
        //根据parentid查询节点列表
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        //执行查询
        List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
        List<EUTreeNode> resultList = new ArrayList<>();
        for (TbContentCategory tbContentCategory : list) {
            //创建一个节点
            EUTreeNode node = new EUTreeNode();
            node.setId(tbContentCategory.getId());
            node.setText(tbContentCategory.getName());
            node.setState(tbContentCategory.getIsParent() ? "closed" : "open");

            resultList.add(node);
        }

        return resultList;
    }

    @Override
    public TaotaoResult insertContentCategory(Long parentId, String name) {

        //创建一个pojo
        TbContentCategory contentCategory = new TbContentCategory();
        contentCategory.setName(name);
        contentCategory.setIsParent(false);
        //'状态。可选值:1(正常),2(删除)',
        contentCategory.setStatus(1);
        contentCategory.setParentId(parentId);
        //排列序号，表示同级类目的展示次序，如数值相等则按名称次序排序。取值范围为大于0的整数
        contentCategory.setSortOrder(1);
        contentCategory.setCreated(new Date());
        contentCategory.setUpdated(new Date());
        //添加记录
        contentCategoryMapper.insert(contentCategory);
        //查看父节点的isParent列是否为true，如果不是true改成true
        TbContentCategory parentCat = contentCategoryMapper.selectByPrimaryKey(parentId);
        //判断是否为true
        if (!parentCat.getIsParent()) {
            parentCat.setIsParent(true);
            //更新父节点
            contentCategoryMapper.updateByPrimaryKey(parentCat);
        }

        //返回结果
        return TaotaoResult.ok(contentCategory);
    }

    @Override
    public TaotaoResult updateContentCategory(Long nodeId, String name) {

        //通过id查询节点对象
        TbContentCategory contentCategory = contentCategoryMapper.selectByPrimaryKey(nodeId);
        //判断新的name值与原来的值是否相同，如果相同则不用更新
        if (name != null && name.equals(contentCategory.getName())) {
            return TaotaoResult.ok();
        }
        contentCategory.setName(name);
        //设置更新时间
        contentCategory.setUpdated(new Date());
        //更新数据库
        contentCategoryMapper.updateByPrimaryKey(contentCategory);
        //返回结果
        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult deleteContentCategory(Long id) {

        //删除分类，就是改节点的状态为2
        TbContentCategory contentCategory = contentCategoryMapper.selectByPrimaryKey(id);
        //状态。可选值:1(正常),2(删除)
        contentCategory.setStatus(2);
        contentCategoryMapper.updateByPrimaryKey(contentCategory);
        //我们还需要判断一下要删除的这个节点是否是父节点，如果是父节点，那么就级联
        //删除这个父节点下的所有子节点（采用递归的方式删除）
        if (contentCategory.getIsParent()) {
            deleteNode(contentCategory.getId());
        }
        //需要判断父节点当前还有没有子节点，如果有子节点就不用做修改
        //如果父节点没有子节点了，那么要修改父节点的isParent属性为false即变为叶子节点
        TbContentCategory parent = contentCategoryMapper.selectByPrimaryKey(contentCategory.getParentId());
        List<TbContentCategory> list = getContentCategoryListByParentId(parent.getId());
        //判断父节点是否有子节点是判断这个父节点下的所有子节点的状态，如果状态都是2就说明
        //没有子节点了，否则就是有子节点。
        boolean flag = false;
        for (TbContentCategory tbContentCategory : list) {
            if (tbContentCategory.getStatus() == 0) {
                flag = true;
                break;
            }
        }
        //如果没有子节点了
        if (!flag) {
            parent.setIsParent(false);
            contentCategoryMapper.updateByPrimaryKey(parent);
        }
        //返回结果
        return TaotaoResult.ok();
    }

    //通过父节点id来查询所有子节点，这是抽离出来的公共方法
    private List<TbContentCategory> getContentCategoryListByParentId(long parentId) {
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);

        return list;
    }

    //递归删除节点
    private void deleteNode(long parentId) {
        List<TbContentCategory> list = getContentCategoryListByParentId(parentId);
        for (TbContentCategory contentCategory : list) {
            contentCategory.setStatus(2);
            contentCategoryMapper.updateByPrimaryKey(contentCategory);
            if (contentCategory.getIsParent()) {
                deleteNode(contentCategory.getId());
            }
        }
    }

}
