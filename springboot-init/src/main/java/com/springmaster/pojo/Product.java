package com.springmaster.pojo;

import org.springframework.beans.factory.BeanNameAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.math.BigDecimal;
import java.util.Date;

public class Product implements BeanNameAware {
    private Integer id;

    private Integer categoryId;

    private String name;

    private String subtitle;

    private BigDecimal price;

    private Integer stock;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    public Product(Integer id, Integer categoryId, String name, String subtitle, BigDecimal price, Integer stock, Integer status, Date createTime, Date updateTime) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.subtitle = subtitle;
        this.price = price;
        this.stock = stock;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Product() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
      System.out.println("get name");
      return name;
    }

    public void setName(String name) {
      System.out.println("set name");
      this.name = name == null ? null : name.trim();
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle == null ? null : subtitle.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

  @Override
  public void setBeanName(String s) {
    System.out.println("bean name is " + s);
  }

  @PostConstruct
  void myInit() {
    System.out.println("init1");
  }
  @PostConstruct
  void myInit2() {
    System.out.println("init2");
  }
  @PreDestroy
  void myDestroy() {
    System.out.println("my destroy");
  }

}