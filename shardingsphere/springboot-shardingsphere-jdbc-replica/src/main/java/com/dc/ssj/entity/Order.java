package com.dc.ssj.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 * 实体类
 *
 * @author duchao
 */
@Data
@TableName(value = "t_order")
public class Order extends Model<Order> {

    @TableId(value = "id")
    private Long id;
    @TableField(value = "user_id")
    private Long userId;
}
