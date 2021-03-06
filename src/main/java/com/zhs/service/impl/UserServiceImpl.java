package com.zhs.service.impl;

import com.github.pagehelper.PageHelper;
import com.zhs.mapper.TtRoleMapper;
import com.zhs.mapper.TtUserMapper;
import com.zhs.mapper.TtUserRoleMapper;
import com.zhs.pojo.TtPermission;
import com.zhs.pojo.TtUser;
import com.zhs.pojo.TtUserRole;
import com.zhs.service.UserService;
import com.zhs.util.PageInfo;
import com.zhs.util.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IDEA
 * author:周华生
 * Date:2018/8/18 20:06
 * 描述: 用户相关操作的实现类
 **/
@Service
@Slf4j
public class UserServiceImpl implements UserService {


    @Autowired
    private TtUserMapper userDao;

    @Autowired
    private TtUserRoleMapper trDao;

    @Autowired
    private TtRoleMapper roleDao;
    @Override
    public TtUser findUserByUserName(String username) {

        return userDao.findUserByUserName(username);
    }

    @Override
    @Transactional
    public ResultData addUser(TtUser user) {
        TtUser ur=new TtUser();
        if(userDao.findUserByUserName(user.getUsername())!=null){
          return  ResultData.ofFail("该用户名已被注册");
        }else {
            ur.setUsername(user.getUsername());
            ur.setRealname(user.getRealname());
            ur.setPassword(user.getPassword());
            ur.setPhone(user.getPhone());
            ur.setEnable(0);
            ur.setCreatetime(new Date());
            ur.setUpdatetime(new Date());
            userDao.insert(ur);
            TtUserRole tur = new TtUserRole();
            tur.setUserid(ur.getId());
            tur.setRoleid(2);
            tur.setDisable(0);
            tur.setCreatetime(new Date());
            tur.setUpdatetime(new Date());
            trDao.insert(tur);
            return ResultData.ofSuccess("");
        }
    }

    @Override
    @Transactional
    public ResultData delUser(Integer id) {
        userDao.delUser(id);
        List<TtUserRole> list=trDao.selbyuserid(id);
        for(TtUserRole tur:list){
            trDao.delUserRole(tur.getId());
        }
        return ResultData.ofSuccess("删除成功");
    }

    @Override
    public ResultData searchUser(TtUser user, Integer currentPage, Integer pageSize) {

        int totalRecords=0;
        List<TtUser> list=new ArrayList<>();
        if(user.getRealname()==null&&user.getPhone()==null&&user.getUsername()==null){
            totalRecords= userDao.count();
            PageHelper.startPage(currentPage, pageSize);
            list =userDao.searchUser(user);
        }else{
            PageHelper.startPage(currentPage, pageSize);
            list =userDao.searchUser(user);
            totalRecords=list.size();
        }
        PageInfo<TtUser> pageInfo=new PageInfo<>(totalRecords,currentPage,pageSize,list);
        return ResultData.ofSuccess(pageInfo);
    }

    @Override
    public ResultData updateUser(TtUser user) {
        user.setUpdatetime(new Date());
                userDao.updateByPrimaryKeySelective(user);
        return ResultData.ofSuccess("");
    }

    @Override
    public ResultData huifuUser(Integer id) {
        userDao.huifuUser(id);
        List<TtUserRole> list=trDao.selbyuserid(id);
        for(TtUserRole tur:list){
            trDao.huifuUserRole(tur.getId());
        }
        return ResultData.ofSuccess("恢复成功");
    }

    @Override
    public ResultData findUserById(Integer id) {
        TtUser user=userDao.selectByPrimaryKey(id);
        return ResultData.ofSuccess(user);
    }

    @Override
    public ResultData findRoleByUserId(int userid) {
        return ResultData.ofSuccess(  roleDao.findRoleById(userid));
    }

    @Override
    public ResultData getAll(TtUser user) {
        return ResultData.ofSuccess(userDao.searchUser(user));
    }


}
