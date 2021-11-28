package model;

import com.alibaba.fastjson.serializer.SerializeFilter;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 *
 * @ClassName Goods.scala
 * @author: lulong
 * @Date: 2021/10/25
 * @Time: 16:23
 */
@Data
public class Goods implements SerializeFilter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String goodsName;

    private BigDecimal price;

    private Integer stock;

    private Timestamp createdAt;

    private Timestamp updatedAt;

}
