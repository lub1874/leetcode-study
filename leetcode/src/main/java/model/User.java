package model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 *
 * @ClassName User.scala
 * @author: lulong
 * @Date: 2021/10/22
 * @Time: 14:38
 */
@Entity(name = "user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;

    private String password;

    private String phone;

    private Timestamp createAt;

    private Timestamp updateAt;
}
