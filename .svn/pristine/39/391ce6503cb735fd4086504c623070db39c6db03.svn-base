package hxzy.ptb.hxzyappkit.domain;


import android.graphics.Bitmap;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class AppModule {
    private String name; //模块名称
    private String image_normal; //未点击时图片资源名称
    private String image_active; //点击后图片资源名称
    private int num; //该模块当前工作数
    private String id; //模块ID
    private String parentId;//模块父ID
    private int ordernum;
    @JSONField(serialize = false,deserialize = false)
    private Bitmap bitmap_normal;
    @JSONField(serialize = false,deserialize = false)
    private Bitmap bitmap_active;
    private List<AppModule> children;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage_normal() {
        return image_normal;
    }

    public void setImage_normal(String image_normal) {
        this.image_normal = image_normal;
    }

    public String getImage_active() {
        return image_active;
    }

    public void setImage_active(String image_active) {
        this.image_active = image_active;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Bitmap getBitmap_normal() {
        return bitmap_normal;
    }

    public void setBitmap_normal(Bitmap bitmap_normal) {
        this.bitmap_normal = bitmap_normal;
    }

    public Bitmap getBitmap_active() {
        return bitmap_active;
    }

    public void setBitmap_active(Bitmap bitmap_active) {
        this.bitmap_active = bitmap_active;
    }

    public List<AppModule> getChildren() {
        return children;
    }

    public void setChildren(List<AppModule> children) {
        this.children = children;
    }

    public int getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(int ordernum) {
        this.ordernum = ordernum;
    }
}
