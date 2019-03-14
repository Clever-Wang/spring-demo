package spring.namespace.handler;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import com.foo.Component;
import com.foo.DefaultCheckingAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author: wangsaichao
 * @date: 2019/3/14
 * @description: 测试类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/spring/my-spring-schema.xml","/spring/schema-advanced-nesting.xml","/spring/schema-custom-attributes.xml"})
public class NameSpaceHandlerTest extends AbstractJUnit4SpringContextTests {

    static{
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        JoranConfigurator configurator = new JoranConfigurator();
        configurator.setContext(lc);
        lc.reset();
        try {
            configurator.doConfigure("/Users/wangsaichao/IdeaProjects/spring-demo/spring-namespace/spring-namespace-test/src/main/resources/logback-spring.xml");//加载logback配置文件
        } catch (JoranException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试 自定义标签 对应 resource/spring/my-spring-schema.xml
     */
    @Test
    public void test01(){

        SimpleDateFormat defaultDateFormat = (SimpleDateFormat)this.applicationContext.getBean("defaultDateFormat");
        String format = defaultDateFormat.format(new Date());
        System.out.println("====>"+format);

        SimpleDateFormat dateformat = (SimpleDateFormat)this.applicationContext.getBean("dateFormat");
        String format2 = dateformat.format(new Date());
        System.out.println("====>"+format2);
    }

    /**
     * 测试自定义标签嵌套 对应 resource/spring/schema-advanced-nesting.xml
     */
    @Test
    public void test02(){
        Component component = (Component)this.applicationContext.getBean("bionic-family");
        //递归打印
        getComponent(component);
    }

    /**
     * 测试自定义属性
     */
    @Test
    public void test03(){

        DefaultCheckingAccountService component = (DefaultCheckingAccountService)this.applicationContext.getBean("checkingAccountService");
        System.out.println("=====>"+component.getjCacheInitializer());
        System.out.println(applicationContext.getBean("checking.account-initializer"));
        System.out.println("=====>"+component.getUrl());

    }



    //递归调用
    public static void getComponent(Component component){
        if(component == null){
            return;
        }

        //打印 主 Component name
        System.out.println("=====>"+component.getName());

        //打印 child
        List<Component> components = component.getComponents();
        if(components == null || components.size() == 0){
            return ;
        }

        for (Component com:components) {
            getComponent(com);
        }

    }




}
