package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

@SpringBootTest
public class demo {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    String cityGeoKey = "site";

    @Test
    public void test(){
        //redisTemplate.opsForValue().set("001","001");
        //String msg = (String)redisTemplate.opsForValue().get("001");
        //System.out.println(msg);

        /*Point point1 = new Point(113.461210,22.287328);
        Point point2 = new Point(113.463181,22.277526);
        redisTemplate.opsForGeo().add("0001",point1,"广东省中山市碧源路1号锦绣丹枫苑");
        redisTemplate.opsForGeo().add("0002",point2,"广东省中山市公安局坦洲分局");*/

        /*List<Point> positions = redisTemplate.opsForGeo().position("0001","广东省中山市碧源路1号锦绣丹枫苑");
        System.out.println(positions.get(0).toString());*/
    }

    @Test
    public void testAdd(){
        Long addedNum = redisTemplate.opsForGeo()
                .add(cityGeoKey,new Point(113.384750,22.511960),"深圳");
        System.out.println(addedNum);
    }

    @Test
    public void testGeoGet(){
        List<Point> points = redisTemplate.opsForGeo().position(cityGeoKey,"北京","上海","深圳");
        System.out.println(points);
    }

    @Test
    public void testDist(){
        Distance distance = redisTemplate.opsForGeo()
                .distance(cityGeoKey,"北京","上海", RedisGeoCommands.DistanceUnit.KILOMETERS);
        System.out.println(distance);
    }

    @Test
    public void testNearByXY(){
        //longitude,latitude
        Circle circle = new Circle(116.405285,39.904989, Metrics.KILOMETERS.getMultiplier());
        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs().includeDistance().includeCoordinates().sortAscending().limit(5);
        GeoResults<RedisGeoCommands.GeoLocation<String>>  results = redisTemplate.opsForGeo()
                .radius(cityGeoKey,circle,args);
        System.out.println(results);
    }

    @Test
    public void testNearByPlace(){
        Distance distance = new Distance(5, Metrics.KILOMETERS);
        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs().includeDistance().includeCoordinates().sortAscending().limit(5);
        GeoResults<RedisGeoCommands.GeoLocation<String>>  results = redisTemplate.opsForGeo()
                .radius(cityGeoKey,"北京",distance,args);
        System.out.println(results);
    }

    @Test
    public void testGeoHash(){
        List<String> results = redisTemplate.opsForGeo()
                .hash(cityGeoKey,"北京","上海","深圳");
        System.out.println(results);
    }

}
