package com.demo.test;

import com.demo.DemoApplication;
import com.demo.domain.Request;
import com.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

/**
 * demo的测试类,尝试使用testNG
 */
@SpringBootTest(classes = {DemoApplication.class})
public class DemoTest extends AbstractTestNGSpringContextTests{

    @Autowired
    private DemoService demoService;

    @Test
    public void test() {
        Request request = new Request();
        request.setPersonId("1");
        request.setTerm("1");
        request.setTeaId(1);
        demoService.getGrade(request);

    }
}
