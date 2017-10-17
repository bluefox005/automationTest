package com.ecvictor.api.rest.assured.o2o.wordpress;

import com.ecvictor.api.rest.assured.o2o.RestAssuredUtil;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Properties;

import static io.restassured.RestAssured.expect;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by samsung on 2017/4/8.
 */
public class WordpressProductTest {
    private String token;
    Properties prop = RestAssuredUtil.getProperties();

    @BeforeClass
    public static void setup() {
        RestAssuredUtil.setup();
        {
            RestAssuredUtil.setup();

        }
    }

     /**
     * Should not be able to access.  .body("results[0].address_components[2].long_name", equalTo("Sherbrooke Street West"))
     */
    @Test
    public void testGetUserTimeline() {
        expect().statusCode(200);
        expect().body("id", hasItem(equalTo(503)))
                .contentType("application/json; charset=UTF-8")
                .given()
                .when().get("http://demo.wp-api.org/wp-json/wp/v2/posts");
    }
}
