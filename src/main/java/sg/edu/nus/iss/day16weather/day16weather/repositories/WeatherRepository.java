package sg.edu.nus.iss.day16weather.day16weather.repositories;

import java.time.Duration;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;


@Repository
public class WeatherRepository {

    @Value("${weather.cache.duration}")
    private Long cacheTime;
    
    @Autowired
    @Qualifier("redislab")
    private RedisTemplate<String,String> redisTemplate;

    //Redis is KEY VALUE
    //setting the value for the key city
    public void save(String city, String payload) {
        //valueOp now carries the value 
        ValueOperations<String, String> valueOp = redisTemplate.opsForValue();
        valueOp.set(city.toLowerCase(), payload, Duration.ofMinutes(cacheTime));
    }

    public Optional<String> get(String city) {
        ValueOperations<String, String> valueOp = redisTemplate.opsForValue();
        String value = valueOp.get(city.toLowerCase());
        if(null == value) {
            return Optional.empty(); //empty box
        }
        return Optional.of(value); //box with data
    }
}
