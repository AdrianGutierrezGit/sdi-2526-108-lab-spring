package com.uniovi.sdi.grademanager;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Sdi2526108SpringApplicationTests {

    static String PathFirefox = "C:\\Program Files\\WindowsApps\\Mozilla.Firefox_148.0.0.0_x64__n80bbvh6b1yt2\\VFS\\ProgramFiles\\Firefox Package Root\\firefox.exe";
    static String Geckodriver = "C:\\Dev\\Tools\\selenium\\geckodriver.exe";

    static WebDriver driver = getDriver(PathFirefox, Geckodriver);
    static String URL = "http://localhost:8090";

    public static WebDriver getDriver(String PathFirefox, String Geckodriver) {
        System.setProperty("webdriver.firefox.bin", PathFirefox);
        System.setProperty("webdriver.gecko.driver", Geckodriver);
        driver = new FirefoxDriver();
        return driver;
    }

    @BeforeEach
    public void setUp(){
        driver.navigate().to(URL);
    }
    //Después de cada prueba se borran las cookies del navegador
    @AfterEach
    public void tearDown(){
        driver.manage().deleteAllCookies();
    }
    //Antes de la primera prueba
    @BeforeAll
    static public void begin() {}
    //Al finalizar la última prueba
    @AfterAll
    static public void end() {
        //Cerramos el navegador al finalizar las pruebas
        driver.quit();
    }

    @Test
    @Order(1)
    void PRO1(){
        Assertions.assertTrue(true);
    }

    @Test
    @Order(2)
    void PRO2(){
        Assertions.assertTrue(true);
    }

    @Test
    @Order(3)
    void PRO3(){
        Assertions.assertTrue(true);
    }

    @Test
    @Order(4)
    void PRO4(){
        Assertions.assertTrue(true);
    }

    @Test
    @Order(5)
    void PRO5(){
        Assertions.assertTrue(true);
    }

    @Test
    @Order(6)
    void PRO6(){
        Assertions.assertTrue(true);
    }

    @Test
    @Order(7)
    void PRO7(){
        Assertions.assertTrue(true);
    }

    @Test
    @Order(8)
    void PRO8(){
        Assertions.assertTrue(true);
    }

    @Test
    @Order(9)
    void PRO9(){
        Assertions.assertTrue(true);
    }

    @Test
    @Order(10)
    void PRO10(){
        Assertions.assertTrue(true);
    }
}