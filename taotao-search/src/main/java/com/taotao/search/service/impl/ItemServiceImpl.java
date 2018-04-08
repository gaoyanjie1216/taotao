package com.taotao.search.service.impl;

import com.taotao.search.mapper.ItemMapper;
import com.taotao.search.pojo.Item;
import com.taotao.search.service.ItemService;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.TaotaoResult;
import utils.ExceptionUtil;

import java.io.IOException;
import java.util.List;

import static utils.solrServerUtil.getServer;

/**
 * Created by Gao on 2018/2/27.
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemMapper itemMapper;

    //指定solr服务器的地址
    private final static String SOLR_URL = "http://localhost:8983/solr/solr_taotao";

    /**
     * 创建SolrServer对象
     * 该对象有两个可以使用，都是线程安全的
     * 1、CommonsHttpSolrServer：启动web服务器使用的，通过http请求的
     * 2、 EmbeddedSolrServer：内嵌式的，导入solr的jar包就可以使用了
     * 3、solr 4.0之后好像添加了不少东西，其中CommonsHttpSolrServer这个类改名为HttpSolrClient
     *
     * @return
     */
    public HttpSolrClient createSolrServer() {

        return new HttpSolrClient(SOLR_URL);
    }

    @Override
    public TaotaoResult importAllItems() throws SolrServerException, IOException {
          try {
            //查询商品列表
            List<Item> list = itemMapper.getItemList();
            HttpSolrClient solr = createSolrServer();
            //把商品信息写入索引库
              for (Item item : list) {
                  //创建一个SolrInputDocument对象
                  SolrInputDocument document = new SolrInputDocument();
                  document.addField("id", item.getId());
                  document.addField("item_title", item.getTitle());
                  //加上getSell_point和getItem_des后报错，再研究
                  //document.addField("item_sell_point", item.getSell_point());
                  document.addField("item_price", item.getPrice());
                  document.addField("item_image", item.getImage());
                  document.addField("item_category_name", item.getCategory_name());
                  //document.addField("item_desc", item.getItem_des());

                  //写入索引库
                  solr.add(document);
              }
              //提交修改
              solr.commit();
              solr.close();
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        return TaotaoResult.ok();
    }
}
