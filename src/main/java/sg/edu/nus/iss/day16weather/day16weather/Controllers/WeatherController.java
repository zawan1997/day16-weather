package sg.edu.nus.iss.day16weather.day16weather.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.nus.iss.day16weather.day16weather.Models.Weather;
import sg.edu.nus.iss.day16weather.day16weather.Services.WeatherService;

@Controller
@RequestMapping(path={"/weather"})
public class WeatherController {

    @Autowired
    private WeatherService weatherSvc;

    @GetMapping
    //city is the key
    public String getWeatherName(Model model, @RequestParam String city) {
        List<Weather> weather = weatherSvc.getWeather(city);
        model.addAttribute("city", city);
        model.addAttribute("weather", weather);
        return "country";
    }

    // @PostMapping 
    // public String postDescription(Model model, @ModelAttribute Weather form){
    //      String main = form.getMain();
    //      String description = form.getDescription();
    //      String icon = form.getIcon();
    //      model.addAttribute("description", description);
    //      model.addAttribute("main", main);
    //      model.addAttribute("icon", icon);

    //      return "country";
    //  }
    

}
