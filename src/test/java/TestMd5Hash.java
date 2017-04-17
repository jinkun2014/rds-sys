import me.jinkun.rds.common.utils.UtilMd5;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;

import java.text.SimpleDateFormat;

/**
 * Description: HelloWorld！ <br/>
 * Autor: Created by jinkun on 2017/1/12.
 */
public class TestMd5Hash {
    @Test
    public void testMd5() throws Exception {
        //md5加密，不加盐
        String password_md5 = new Md5Hash("111111").toString();
        System.out.println("md5加密，不加盐=" + password_md5);

        //md5加密，加盐，一次散列
        String password_md5_sale_1 = new Md5Hash("admin", "admin", 1).toString();
        System.out.println("password_md5_sale_1=" + password_md5_sale_1);
        String password_md5_sale_2 = new Md5Hash("111111", "uiwueylm", 1).toString();
        System.out.println("password_md5_sale_2=" + password_md5_sale_2);
        //两次散列相当于md5(md5())

        //使用SimpleHash
        String simpleHash = new SimpleHash("MD5", "111111", "eteokues", 1).toString();
        System.out.println(simpleHash);


        String salt = "a1b2c3";
        System.out.println(new Md5Hash("123", salt, 1).toString());


        //admin密码-123456
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(UtilMd5.md5("123456", String.valueOf(sdf.parse("2017-01-09 10:52:59").getTime())));
    }
}
