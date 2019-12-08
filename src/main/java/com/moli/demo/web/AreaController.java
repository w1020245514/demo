package com.moli.demo.web;

import com.moli.demo.entity.Area;
import com.moli.demo.service.AreaService;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AreaController {
    @Autowired
    private AreaService areaService;

    /**
     * 获取所有的区域信息
     *
     * @return
     */
    @RequestMapping(value = "/listarea", method = RequestMethod.GET)
    private Map<String, Object> listArea() {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        List<Area> list = new ArrayList<Area>();
        // 获取区域列表
        list = areaService.getAreaList();
        modelMap.put("areaList", list);
        return modelMap;
    }

    /**
     * 通过区域Id获取区域信息
     *
     * @return
     */
    @RequestMapping(value = "/getareabyid", method = RequestMethod.POST)
    private Map<String, Object> getAreaById(Integer areaId) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        // 获取区域信息
        Area area = areaService.getAreaById(areaId);
        modelMap.put("area", area);
        return modelMap;
    }

    /**
     * 添加区域信息
     *
     */
    @RequestMapping(value = "/addarea", method = RequestMethod.POST)
    private Map<String, Object> addArea(@RequestBody Area area) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        // 添加区域信息
        modelMap.put("result", areaService.addArea(area));
        return modelMap;
    }

    /**
     * 修改区域信息，主要修改名字
     *
     */
    @RequestMapping(value = "/modifyarea", method = RequestMethod.POST)
    private Map<String, Object> modifyArea(@RequestBody Area area) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        // 修改区域信息
        modelMap.put("result", areaService.modifyArea(area));
        return modelMap;
    }

    @RequestMapping(value = "/removearea", method = RequestMethod.GET)
    private Map<String, Object> removeArea(Integer areaId) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        // 修改区域信息
        modelMap.put("result", areaService.deleteArea(areaId));
        return modelMap;
    }

    @RequestMapping("/hello")
    public String helloSpringBoot() {
        return "Hello SpringBoot Project.";
    }
    /*
    * 微信测试账号推送
    * */
    @RequestMapping("/push")
    public String push() {
        //1，配置
        WxMpInMemoryConfigStorage wxStorage = new WxMpInMemoryConfigStorage();
        wxStorage.setAppId("wx2e167ddb746cbfb8");//appid
        wxStorage.setSecret("d7e51cfdd784a969a9f4a42577f51dd7");//appsecret
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxStorage);

        //2,推送消息
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser("oQU6E53SOk0h1uQyTBPobeHF-Ung")//要推送的用户openid
                .templateId("2ohjRc2UoVdkhWfeG5_BF6k5Uu4k5qB3LucxFrkilAI")//模版id
                .url("https://30paotui.com/")//点击模版消息要访问的网址
                .build();
        //3,如果是正式版发送模版消息，这里需要配置你的信息
        //        templateMessage.addData(new WxMpTemplateData("name", "value", "#FF00FF"));
        //                templateMessage.addData(new WxMpTemplateData(name2, value2, color2));
        templateMessage.addData(new WxMpTemplateData("keyword1","213213","#FF00FF"));

        //发起推送
        try {
            String msg = wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
            System.out.println("推送成功：" + msg);
            return "推送成功：" + msg;
        } catch (Exception e) {
            System.out.println("推送失败：" + e.getMessage());
            e.printStackTrace();
        }
        return "推送失败";

    }
}
