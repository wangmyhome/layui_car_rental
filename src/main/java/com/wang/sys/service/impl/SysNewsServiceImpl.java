package com.wang.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wang.sys.mapper.SysNewsMapper;
import com.wang.sys.domain.SysNews;
import com.wang.sys.service.SysNewsService;
import com.wang.sys.utils.DataGridView;
import com.wang.sys.vo.NewsVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author shanpeng
 * @ClassName SysNewsServiceImpl
 * @description TODO
 * @date 2020/1/5 18:06
 * @Version 1.0
 */
@Service
public class SysNewsServiceImpl implements SysNewsService {

    @Resource
    private SysNewsMapper newsMapper;

    /**
     * 查询所有
     * @param newsVo
     * @return
     */
    @Override
    public DataGridView queryAllNews(NewsVo newsVo) {
        Page<Object> page = PageHelper.startPage(newsVo.getPage(),newsVo.getLimit());
        List<SysNews> data = this.newsMapper.queryAllNews(newsVo);
        return new DataGridView(page.getTotal(),data);
    }

    /**
     * 添加公告
     * @param newsVo
     */
    @Override
    public void addNews(NewsVo newsVo) {
        this.newsMapper.insertSelective(newsVo);
    }

    /**
     * 删除一条公告
     * @param newsid
     */
    @Override
    public void deleteNews(Integer newsid) {
        this.newsMapper.deleteByPrimaryKey(newsid);
    }

    /**
     * 批量删除公告
     * @param ids
     */
    @Override
    public void deleteBatchNews(Integer[] ids) {
        for (Integer id : ids) {
            this.deleteNews(id);
        }
    }

    /**
     * 更新公告
     * @param newsVo
     */
    @Override
    public void updateNews(NewsVo newsVo) {
        this.newsMapper.updateByPrimaryKeySelective(newsVo);
    }

    /**
     * 通过id查询一条公告
     * @param id
     * @return
     */
    @Override
    public SysNews queryNewsById(Integer id) {
        return this.newsMapper.selectByPrimaryKey(id);
    }
}
