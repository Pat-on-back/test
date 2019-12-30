package com.boot.renting.controller;

import com.boot.renting.entity.Menu;
import com.boot.renting.utils.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@Api(tags = "菜单表")
@RestController
@RequestMapping("menu")
public class MenuController {


    @ApiOperation(value = "菜单树")
    @GetMapping(value = "/getTree")
    public ResponseMessage<List<Menu>> getTree() {
        List<Menu> all = getList();
        List<Menu> baseLists = new ArrayList<>();
        dg(baseLists, all);
        return new ResponseMessage<>(baseLists);
    }

    private List<Menu> getChild(Integer pid, List<Menu> elements) {
        List<Menu> childs = new ArrayList<>();
        for (Menu e : elements) {
            if (e.getPId() != null) {
                if (e.getPId().equals(pid)) {
                    // 子菜单的下级菜单
                    childs.add(e);
                }
            }
        }
        // 把子菜单的子菜单再循环一遍
        for (Menu e : childs) {
            // 继续添加子元素
            e.setChilds(getChild(e.getId(), elements));
        }

        //停下来的条件，如果 没有子元素了，则停下来
        if (childs.size() == 0) {
            return null;
        }
        return childs;
    }

    private List<Menu> getList() {
        List<Menu> list = new ArrayList<>();
        Menu menu = new Menu();
        menu.setId(1);
        menu.setName("用户管理");
        menu.setIcon("el-icon-menu");

        Menu menu2 = new Menu();
        menu2.setId(2);
        menu2.setName("系统管理");
        menu2.setIcon("el-icon-setting");

        Menu m = new Menu();
        m.setId(100);
        m.setPId(1);
        m.setName("管理员列表");
        m.setIndexUrl("http://localhost/admin/adminList");

        Menu m1 = new Menu();
        m1.setId(101);
        m1.setPId(1);
        m1.setName("租客列表");
        m1.setIndexUrl("http://localhost/admin/userList");

        Menu m2 = new Menu();
        m2.setId(102);
        m2.setPId(1);
        m2.setName("房东列表");
        m2.setIndexUrl("http://localhost/admin/landlordList");

        Menu m3 = new Menu();
        m3.setId(103);
        m3.setPId(1);
        m3.setName("房源列表");
        m3.setIndexUrl("http://localhost/admin/houseList");

        Menu m4 = new Menu();
        m4.setId(104);
        m4.setPId(1);
        m4.setName("交易列表");
        m4.setIndexUrl("http://localhost/admin/orderList");

        Menu m21 = new Menu();
        m21.setId(200);
        m21.setPId(2);
        m21.setName("接口文档");
        m21.setIndexUrl("http://localhost/doc.html");


        list.add(menu);
        list.add(menu2);

        list.add(m);
        list.add(m1);
        list.add(m2);
        list.add(m3);
        list.add(m4);

        list.add(m21);
        return list;
    }

    private void dg(List<Menu> baseLists, List<Menu> all) {
        // 总菜单，出一级菜单，一级菜单没有父id
        for (Menu e : all) {
            if (e.getPId() == null) {
                baseLists.add(e);
            }
        }
        // 遍历一级菜单
        for (Menu e : baseLists) {
            // 将子元素 set进一级菜单里面
            e.setChilds(getChild(e.getId(), all));
        }
    }

}
