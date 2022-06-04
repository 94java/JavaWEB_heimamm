package top.hellocode.service.store;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import top.hellocode.domain.store.Company;
import top.hellocode.service.store.impl.CompanyServiceImpl;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年05月16日 20:27
 */
public class CompanyServiceTest {
    private static CompanyService companyService = null;
    @BeforeClass
    public static void init(){
        companyService = new CompanyServiceImpl();
    }
    @Test
    public void testSave(){
        Company company = new Company();
        company.setName("测试数据");
        companyService.save(company);
    }
    @Test
    public void testFindAll(){
        Company company = new Company();
        System.out.println(companyService.findAll(1, 100));
    }
    @AfterClass
    public static void destory(){
        companyService = null;
    }
}
