package com.demo.test;

import com.demo.DemoApplication;
import com.demo.common.Header;
import com.demo.common.RequestPageEntity;
import com.demo.domain.TeaSubGradeForDirectorRequest;
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
    public void test() throws Exception {
        TeaSubGradeForDirectorRequest request = new TeaSubGradeForDirectorRequest();
        request.setPersonId("1");
       request.setPersonId("1");
        RequestPageEntity<TeaSubGradeForDirectorRequest> requestData = new RequestPageEntity<>();
        Header header = new Header();
        requestData.setHeader(header);
        requestData.setBody(null);
        demoService.selectTeaSubGradeForDirector(requestData);

    }
}
