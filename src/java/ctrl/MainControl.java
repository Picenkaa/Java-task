package ctrl;

import org.springframework.stereotype.Controller;
import ds.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import static jdk.nashorn.internal.objects.NativeArray.map;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.core.convert.TypeDescriptor.array;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class MainControl {

    @RequestMapping(value = "/get", method = RequestMethod.GET) // pasitikrinimui grazina json lista
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody  //http://localhost:9999/WS_full/get
    public String get() {
        registry a = new registry();
        return a.gautiSarasa().toString();
    }
    


    @RequestMapping(value = "/tax_{reiksme}", method = RequestMethod.GET) // endpoint
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody //http://localhost:9999/WS_full/tax_Tadas
    public String getThat(@PathVariable("reiksme") String reiksme) {
      
        int skaiciavimas = 0;
        Double tax = null;
        registry a = new registry();
        ArrayList<buildings> b = a.tax(reiksme);
      
        HashMap<String, Double> map
                = new HashMap<>();
        for (buildings c : b) {
            switch (c.getProperty_type().toLowerCase()) {
                case "butas":
                    map.put(c.getProperty_type(), 0.07);
                    break;
                case "sodas":
                    map.put(c.getProperty_type(), 0.02);
                    break;
                case "kiemas":
                    map.put(c.getProperty_type(), 0.03);
                    break;
                case "namas":
                    map.put(c.getProperty_type(), 0.04);
                    break;
                default:
                    map.put(c.getProperty_type(), 0.05);
            }

            tax = map.get(c.getProperty_type());

            skaiciavimas += c.getMarket_value() * tax;
          

        }
        String s = String.valueOf(skaiciavimas);
        
      // model.addAttribute("s",s);
        return reiksme + " will pay " + s + " &#8364 total yearly real estate tax";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET) // get json list
    public ModelAndView listAction() {
        registry a = new registry();
        ArrayList<buildings> list = a.gautiSarasa();

        ModelAndView mv = new ModelAndView();

        mv.setViewName("index");
        mv.addObject("list", list);

        return mv;

    }
    
    
    

    @RequestMapping(value = "/add_{ad}_{ow}_{s}_{m}_{pt}", method = RequestMethod.POST) // add
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    //link'as perduodant parametrus atrodys taip: http://localhost:9999/WS_full/add_slenio_zigimantas_40_20000_sodas
    public ModelAndView add(@PathVariable String ad, @PathVariable String ow, @PathVariable int s, @PathVariable int m, @PathVariable String pt) { 
        registry a = new registry();
        try {
            boolean success = a.prideti(ad, ow, s, m, pt); //pridėjimas
            if (success) {
                return new ModelAndView("redirect");
            } else {
                return new ModelAndView("klaida");
            }
        } catch (Exception e) {
            return new ModelAndView("redirect:/klaida.htm");

        }
    }

    @RequestMapping(value = "/del_{addd}", method = RequestMethod.DELETE) //del
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody //http://localhost:9999/WS_full/del_slenio // addressa 
    public ModelAndView delete(@PathVariable String addd) {
        registry afv = new registry();
        try {
            boolean a = afv.salinti(addd);
            if (a) {
                return new ModelAndView("redirect:/index.htm");
            } else {
                return new ModelAndView("redirect:/klaida.htm");
            }
        } catch (Exception e) {
            return new ModelAndView("redirect:/klaida.htm");
        }
    }

   
    @RequestMapping(value = "/upd_{ad}_{nmv}", method = RequestMethod.PUT) // edit
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody //:http://localhost:9999/WS_full/upd_slenio_20
    public ModelAndView update(@PathVariable String ad, @PathVariable int nmv) {
        registry afv = new registry();
        try {
            boolean a = afv.keisti(ad, nmv);
            if (a) {
                return new ModelAndView("redirect:/index.htm");
            } else {
                return new ModelAndView("redirect:/klaida.htm");
            }
        } catch (Exception e) {
            return new ModelAndView("redirect:/klaida.htm");
        }
    }

}
